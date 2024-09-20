/*
 * Copyright (c) 2002-2024, Mairie de Paris
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

import java.lang.reflect.Field;

import org.junit.platform.commons.util.ReflectionUtils;

public class ReflectionTestUtils
{
    /**
     * Set the {@linkplain Field field} with the given {@code name} on the provided {@code targetObject} to the supplied {@code value}.
     * <p>
     * This method delegates to {@link #setField(Object, String, Object, Class)}, supplying {@code null} for the {@code type} argument.
     * 
     * @param targetObject the target object on which to set the field; never {@code null}
     * @param name         the name of the field to set; never {@code null}
     * @param value        the value to set
     */
    public static void setField(Object targetObject, String name, Object value)
    {
        setField(targetObject, name, value, null);
    }

    /**
     * Set the {@linkplain Field field} with the given {@code name}/{@code type} on the provided {@code targetObject} to the supplied {@code value}.
     * <p>
     * This method delegates to {@link #setField(Object, Class, String, Object, Class)}, supplying {@code null} for the {@code targetClass} argument.
     * 
     * @param targetObject the target object on which to set the field; never {@code null}
     * @param name         the name of the field to set; may be {@code null} if {@code type} is specified
     * @param value        the value to set
     * @param type         the type of the field to set; may be {@code null} if {@code name} is specified
     */
    public static void setField(Object targetObject, String name, Object value, Class<?> type)
    {
        setField(targetObject, null, name, value, type);
    }

    /**
     * Set the static {@linkplain Field field} with the given {@code name} on the provided {@code targetClass} to the supplied {@code value}.
     * <p>
     * This method delegates to {@link #setField(Object, Class, String, Object, Class)}, supplying {@code null} for the {@code targetObject} and {@code type}
     * arguments.
     * <p>
     * This method does not support setting {@code static final} fields.
     * 
     * @param targetClass the target class on which to set the static field; never {@code null}
     * @param name        the name of the field to set; never {@code null}
     * @param value       the value to set
     * @since 4.2
     */
    public static void setField(Class<?> targetClass, String name, Object value)
    {
        setField(null, targetClass, name, value, null);
    }

    /**
     * Set the static {@linkplain Field field} with the given {@code name}/{@code type} on the provided {@code targetClass} to the supplied {@code value}.
     * <p>
     * This method delegates to {@link #setField(Object, Class, String, Object, Class)}, supplying {@code null} for the {@code targetObject} argument.
     * <p>
     * This method does not support setting {@code static final} fields.
     * 
     * @param targetClass the target class on which to set the static field; never {@code null}
     * @param name        the name of the field to set; may be {@code null} if {@code type} is specified
     * @param value       the value to set
     * @param type        the type of the field to set; may be {@code null} if {@code name} is specified
     * @since 4.2
     */
    public static void setField(Class<?> targetClass, String name, Object value, Class<?> type)
    {

        setField(null, targetClass, name, value, type);
    }

    /**
     * Set the {@linkplain Field field} with the given {@code name}/{@code type} on the provided {@code targetObject}/{@code targetClass} to the supplied
     * {@code value}.
     * <p>
     * If the supplied {@code targetObject} is a <em>proxy</em>, it will be {@linkplain AopTestUtils#getUltimateTargetObject unwrapped} allowing the field to be
     * set on the ultimate target of the proxy.
     * <p>
     * This method traverses the class hierarchy in search of the desired field. In addition, an attempt will be made to make non-{@code public} fields
     * <em>accessible</em>, thus allowing one to set {@code protected}, {@code private}, and <em>package-private</em> fields.
     * <p>
     * This method does not support setting {@code static final} fields.
     * 
     * @param targetObject the target object on which to set the field; may be {@code null} if the field is static
     * @param targetClass  the target class on which to set the field; may be {@code null} if the field is an instance field
     * @param name         the name of the field to set; may be {@code null} if {@code type} is specified
     * @param value        the value to set
     * @param type         the type of the field to set; may be {@code null} if {@code name} is specified
     * @since 4.2
     * @see ReflectionUtils#findField(Class, String, Class)
     * @see ReflectionUtils#makeAccessible(Field)
     * @see ReflectionUtils#setField(Field, Object, Object)
     * @see AopTestUtils#getUltimateTargetObject(Object)
     */
    public static void setField(Object targetObject, Class<?> targetClass, String name, Object value, Class<?> type)
    {

        if (targetClass == null)
        {
            targetClass = targetObject.getClass();
        }

        try
        {
            Field field = findField(targetClass, name);
            makeAccessible(field);
            field.set(targetObject, value);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    private static Field findField(Class<?> targetClass, String name)
    {
        Field field = null;
        try
        {
            field = targetClass.getDeclaredField(name);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        if (field == null)
        {
            throw new IllegalArgumentException(String.format("Could not find field '%s' for target class [%s]", name, targetClass));
        }
        return field;
    }

    private static void makeAccessible(Field field)
    {

        field.setAccessible(true);
    }

}
