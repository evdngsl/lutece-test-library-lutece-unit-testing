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

    /**
     * Get the value of the {@linkplain Field field} with the given {@code name} from the provided {@code targetObject}.
     * <p>
     * This method delegates to {@link #getField(Object, Class, String)}, supplying {@code null} for the {@code targetClass} argument.
     * 
     * @param targetObject the target object from which to get the field; never {@code null}
     * @param name         the name of the field to get; never {@code null}
     * @return the field's current value
     * @see #getField(Class, String)
     */

//    public static Object getField(Object targetObject, String name)
//    {
//        return getField(targetObject, null, name);
//    }

    /**
     * Get the value of the static {@linkplain Field field} with the given {@code name} from the provided {@code targetClass}.
     * <p>
     * This method delegates to {@link #getField(Object, Class, String)}, supplying {@code null} for the {@code targetObject} argument.
     * 
     * @param targetClass the target class from which to get the static field; never {@code null}
     * @param name        the name of the field to get; never {@code null}
     * @return the field's current value
     * @since 4.2
     * @see #getField(Object, String)
     */

//    public static Object getField(Class<?> targetClass, String name)
//    {
//        return getField(null, targetClass, name);
//    }

    /**
     * Get the value of the {@linkplain Field field} with the given {@code name} from the provided {@code targetObject}/{@code targetClass}.
     * <p>
     * If the supplied {@code targetObject} is a <em>proxy</em>, it will be {@linkplain AopTestUtils#getUltimateTargetObject unwrapped} allowing the field to be
     * retrieved from the ultimate target of the proxy.
     * <p>
     * This method traverses the class hierarchy in search of the desired field. In addition, an attempt will be made to make non-{@code public} fields
     * <em>accessible</em>, thus allowing one to get {@code protected}, {@code private}, and <em>package-private</em> fields.
     * 
     * @param targetObject the target object from which to get the field; may be {@code null} if the field is static
     * @param targetClass  the target class from which to get the field; may be {@code null} if the field is an instance field
     * @param name         the name of the field to get; never {@code null}
     * @return the field's current value
     * @since 4.2
     * @see #getField(Object, String)
     * @see #getField(Class, String)
     * @see ReflectionUtils#findField(Class, String, Class)
     * @see ReflectionUtils#makeAccessible(Field)
     * @see ReflectionUtils#getField(Field, Object)
     * @see AopTestUtils#getUltimateTargetObject(Object)
     */

//    public static Object getField(Object targetObject, Class<?> targetClass, String name)
//    {
//        if (targetClass == null)
//        {
//            targetClass = targetObject.getClass();
//        }
//        Field field = findField(targetClass, name);
//        if (field == null)
//        {
//            throw new IllegalArgumentException(
//                    String.format("Could not find field '%s' on %s or target class [%s]", name, safeToString(targetObject), targetClass));
//        }
//
//        if (logger.isDebugEnabled())
//        {
//            logger.debug(String.format("Getting field '%s' from %s or target class [%s]", name, safeToString(targetObject), targetClass));
//        }
//        ReflectionUtils.makeAccessible(field);
//        return ReflectionUtils.getField(field, targetObject);
//    }

}
