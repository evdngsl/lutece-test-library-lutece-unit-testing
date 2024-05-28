/*
 * Copyright (c) 2002-2016, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.test;

import static java.util.Arrays.stream;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jboss.weld.environment.se.ContainerLifecycleObserver;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAlternativeStereotypes;
import org.jboss.weld.junit5.auto.EnableAlternatives;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.platform.commons.support.AnnotationSupport;

import fr.paris.lutece.TestPackageMarker;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.literal.InjectLiteral;

/**
 * This class is the base class for Lutece test case. It provides all services initialization.
 * 
 */
@EnableWeld
// we need PER_CLASS because lutece uses a lot of static calls, static fields, etc..
@TestInstance(Lifecycle.PER_CLASS)
// this class extends Assertions to allow old assertions to compile without re-importing packages
//   this should be removed if possible
public abstract class LuteceTestCase extends org.junit.jupiter.api.Assertions
{
    // method names
    private static final String TEAR_DOWN = "tearDown";
    private static final String SET_UP = "setUp";

    protected final Weld weld = configure(customize(new Weld()));
    // some tests still need this :
    private final String resourcesDir;

    protected LuteceTestCase()
    {
        resourcesDir = getClass().getResource("/").toString().replaceFirst("file:", "").replaceFirst("target/.*", "target/lutece/");
    }

    public String getResourcesDir()
    {
        return resourcesDir;
    }

    @WeldSetup
    public WeldInitiator init = customizeWeldInitiatorBuilder(WeldInitiator.from(weld) // the one above
            .activate(ApplicationScoped.class)) // docs say it should be there already
            .build();

    /**
     * Called before each Test/TestFactory.
     * 
     * Initializes all LuteceTestCaseInit implementations.
     * 
     * In practice : lutece-core declares one, which is used to call AppInit. This mechanism allows for loose coupling between this project and the core.
     */
    @BeforeEach
    public void initCore() throws Exception
    {
        if (needsCoreInit() && !coreInitDone)
        {
            coreInitDone = true;
            TestLogService.info("LuteceTestCase initCore");
            ServiceLoader<LuteceTestCaseInit> loader = ServiceLoader.load(LuteceTestCaseInit.class);
            loader.forEach(LuteceTestCaseInit::initTests);
            TestLogService.info("LuteceTestCase initCore done");
        }
    }

    // we check that we do it only once
    // so that behavior remains the same for @Test methods and junit-3 tests
    private boolean coreInitDone = false;

    /**
     * Can be customized by tests. Customizes weld for all tests of the class.
     * 
     * @param weld the Weld instance
     * @return Always return weld
     */
    protected Weld customize(Weld weld)
    {
        return weld;
    }

    /**
     * Can be customized by tests. Customizes the weld initiator builder for all tests of the class.
     * 
     * @param initiatorBuilder the builder instance
     * @return Always return the builder
     */
    protected WeldInitiator.Builder customizeWeldInitiatorBuilder(WeldInitiator.Builder initiatorBuilder)
    {
        return initiatorBuilder;
    }

    /**
     * Configures the Weld instance with support for:
     * <ul>
     * <li>@Alternative beans
     * <li>injected @Resource
     * <li>@EnableAlternatives and @EnableAlternativeStereotypes on the test class
     * <li>@AddPackages (if the current package is not enough)
     * </ul>
     * 
     * @param weld the Weld instance
     * @return the (same) weld instance
     */
    protected Weld configure(Weld weld)
    {
        // without this line, the synthetic bean archive is not built by WELD, and @Alternative annotations will not work as expected
        weld.addPackages(isDefaultPackageAddedRecursively(), getClass());
        // the following line works as expected with Eclipse (recursively add all beans), but not with maven on the command line ...
        // weld.addPackages(isDefaultPackageAddedRecursively(), TestPackageMarker.class);
        if (shouldRecursivelyAddAllBeans())
            // hard-coding "fr.paris.lutece" below would not work reliably: the package must be loaded by the classloader.
            weld.addPackages(true, getClass().getClassLoader().getDefinedPackage(TestPackageMarker.class.getPackageName()));
        // In Java SE, force the whole classpath to be a single "bean archive"
        // Isolation must be disabled in order to support several possible @Alternative annotations for a given bean.
        weld.disableIsolation();
        // support @EnableAlternatives and @EnableAlternativeStereotypes on class
        AnnotationSupport.findRepeatableAnnotations(getClass(), EnableAlternatives.class).stream().flatMap(ann -> stream(ann.value())).distinct()
                .forEach(weld::addAlternative);

        AnnotationSupport.findRepeatableAnnotations(getClass(), EnableAlternativeStereotypes.class).stream().flatMap(ann -> stream(ann.value())).distinct()
                .forEach(weld::addAlternativeStereotype);
        // in some cases, we want alternate beans that are defined in some other package
        AnnotationSupport.findRepeatableAnnotations(getClass(), AddPackages.class)
                .forEach(ann -> stream(ann.value()).distinct().forEach(cls -> weld.addPackage(ann.recursively(), cls)));

        // work around for @Resource not working in java SE
        weld.addContainerLifecycleObserver(ContainerLifecycleObserver.processAnnotatedType().notify(pat -> pat.configureAnnotatedType()
                // we replace each Resource ...
                .filterFields(m -> m.isAnnotationPresent(Resource.class))
                // ... by @Inject (which works out of the box)
                .forEach(m -> m.add(InjectLiteral.INSTANCE))));
        return weld;
    }

    /**
     * Whether the default package (current test) is added recursively or not.
     * 
     * By default : true, which means more compatible, but potentially slower
     * 
     * @return true by default
     */
    protected boolean isDefaultPackageAddedRecursively()
    {
        return true;
    }

    /**
     * Whether all test beans are added recursively or not, from "root" (fr.paris.lutece).
     * 
     * By default : true, which means more compatible, but potentially slower
     * 
     * @return true by default
     */
    protected boolean shouldRecursivelyAddAllBeans()
    {
        return true;
    }

    /**
     * Whether the core init must be run or not.
     * 
     * @return true by default
     */
    protected boolean needsCoreInit()
    {
        return true;
    }

    /**
     * Whether this base class tries to emulate the behavior of BeforeEach and AfterEach for JUNIT3 tests too
     * 
     * @return true by default
     */
    protected boolean emulateLifeCycleForJunit3()
    {
        return true;
    }

    /**
     * Simulates JUnit3 behaviour.
     * 
     * However, this entails a lots of limitations on selected methods:
     * 
     * <ul>
     * <li>no support for annotations on those methods
     * <li>existing but limited @BeforeEach support , etc...
     * </ul>
     * 
     * @Disabled is supported.
     * 
     * @return a stream of tests for the current class
     */
    @TestFactory
    public Stream<DynamicTest> dynamicTestsJunit3Style()
    {
        if (emulateLifeCycleForJunit3())
        {
            before = findMatchingMethods(BeforeEach.class, SET_UP);
            after = findMatchingMethods(AfterEach.class, TEAR_DOWN);
        }
        setUp_annotated = isMethodAnnotated(BeforeEach.class, SET_UP);
        tearDown_annotated = isMethodAnnotated(AfterEach.class, TEAR_DOWN);
        TestLogService.info("dynamicTestsJunit3Style");
        // declare methods as a temp list to be able to count them
        List<Method> testMethods = Arrays.asList(getClass().getMethods()).stream()
                // filter on wanted methods
                .filter(method -> method.getName().startsWith("test")// junit 3 compatibility
                        && !method.isAnnotationPresent(Disabled.class)// do not include @Disabled
                        && !method.isAnnotationPresent(Test.class))// do not re-include methods that will be included by JUnit
                .collect(Collectors.toList());
        junit3TestsCount = testMethods.size();
        junit3CurrentTest = 0;
        return testMethods.stream()// back to stream
                .map(method -> dynamicTest(getClass().getName() + ":" + method.getName(), () -> runJunit3Test(method)));
    }

    private int junit3TestsCount = -1, junit3CurrentTest = 0;

    /**
     * to be overridden by junit3-style test sub classes
     * 
     * @throws Exception
     */
    protected void setUp() throws Exception
    {
    }

    /**
     * to be overridden by junit3-style test sub classes
     * 
     * @throws Exception
     */
    protected void tearDown() throws Exception
    {
    }

    /**
     * Finds an annotation on the current class methods (not the LuteceTestCase parent).
     * 
     * As is, does not support intermediary level of class inheritance for tests
     * 
     * @param a        annotation to find
     * @param notNamed filter out this method name
     * @return list of matching methods
     */
    protected List<Method> findMatchingMethods(Class<? extends Annotation> a, String notNamed)
    {
        return Arrays.asList(getClass().getDeclaredMethods()).stream().filter(m -> !m.getName().equals(notNamed) && m.isAnnotationPresent(a))
                .collect(Collectors.toList());
    }

    protected boolean isMethodAnnotated(Class<? extends Annotation> a, String method)
    {
        return Arrays.asList(getClass().getDeclaredMethods()).stream().anyMatch(m -> m.getName().equals(method) && m.isAnnotationPresent(a));
    }

    private List<Method> before, after;
    private boolean setUp_annotated, tearDown_annotated;

    protected void runJunit3Test(Method method) throws Exception
    {
        // we do not call @BeforeEach on the first test, because JUnit does that for @TestFactory already
        if (emulateLifeCycleForJunit3() && junit3CurrentTest != 0)
            for (Method m : before)
            {
                m.setAccessible(true);
                m.invoke(this);
            }
        // we call setUp for each test, except if it is annotated ( in which case Junit called it already)
        if (!setUp_annotated || junit3CurrentTest != 0)
            setUp();
        method.invoke(this);
        // same for tearDown
        if (!tearDown_annotated || junit3CurrentTest != (junit3TestsCount - 1))
            tearDown();
        // we do not call @AfterEach on the last test
        if (emulateLifeCycleForJunit3() && junit3CurrentTest != (junit3TestsCount - 1))
            for (Method m : after)
            {
                m.setAccessible(true);
                m.invoke(this);
            }
        junit3CurrentTest++;
    }

    // assertXXX methods : compatibility for older syntax
    // should be removed if possible
    protected static void assertFalse(String comment, boolean condition)
    {
        assertFalse(condition, comment);
    }

    protected static void assertTrue(String comment, boolean condition)
    {
        assertTrue(condition, comment);
    }

    protected static void assertNotNull(String comment, Object o)
    {
        assertNotNull(o, comment);
    }

    protected static void assertEquals(String message, long expected, long actual)
    {
        assertEquals(expected, actual, message);
    }

}
