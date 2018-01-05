package core.util;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 *
 * @author chenwenju
 *
 * @since May 29, 2015
 */
@SuppressWarnings({ "rawtypes", "unchecked"})
public class PropDescriptor {


    public PropDescriptor(Class beanType, Class propType, String propName)
    {
        if(beanType == null)
            throw new IllegalArgumentException("Bean Class can not be null!");
        if(propName == null)
            throw new IllegalArgumentException("Bean Property name can not be null!");
        this.propType = propType;
        this.beanType = beanType;
        name = propName;
        if(name.startsWith("m_") && name.length() > 2)
            baseName = StringUtils.capitalize(name.substring(2));
        else
            baseName = StringUtils.capitalize(propName);
    }

    public synchronized Method getReadMethod(Class currBean)
    {
        String readMethodName = null;
        if(propType == Boolean.TYPE || propType == null)
            readMethodName = (new StringBuilder()).append("is").append(baseName).toString();
        else
            readMethodName = (new StringBuilder()).append("get").append(baseName).toString();
        Class classStart = currBean;
        if(classStart == null)
            classStart = beanType;
        Method readMethod = findMemberMethod(classStart, readMethodName, 0, null);
        if(readMethod == null && readMethodName.startsWith("is"))
        {
            readMethodName = (new StringBuilder()).append("get").append(baseName).toString();
            readMethod = findMemberMethod(classStart, readMethodName, 0, null);
        }
        if(readMethod != null)
        {
            int mf = readMethod.getModifiers();
            if(!Modifier.isPublic(mf))
                return null;
            Class retType = readMethod.getReturnType();
            if(!propType.isAssignableFrom(retType)){
            	//记录日志
            }
        }
        return readMethod;
    }

    public synchronized Method getWriteMethod(Class currBean)
    {
        String writeMethodName = null;
        if(propType == null)
            propType = findPropertyType(getReadMethod(currBean), null);
        if(writeMethodName == null)
            writeMethodName = (new StringBuilder()).append("set").append(baseName).toString();
        Class classStart = currBean;
        if(classStart == null)
            classStart = beanType;
        Method writeMethod = findMemberMethod(classStart, writeMethodName, 1, propType != null ? (new Class[] {
            propType
        }) : null);
        if(writeMethod != null)
        {
            int mf = writeMethod.getModifiers();
            if(!Modifier.isPublic(mf) || Modifier.isStatic(mf))
                writeMethod = null;
        }
        return writeMethod;
    }

    private Class findPropertyType(Method readMethod, Method writeMethod)
    {
        Class propertyType = null;
        if(readMethod != null)
            propertyType = readMethod.getReturnType();
        if(propertyType == null && writeMethod != null)
        {
            Class params[] = writeMethod.getParameterTypes();
            propertyType = params[0];
        }
        return propertyType;
    }

    private Method findMemberMethod(Class beanClass, String mName, int num, Class args[])
    {
        Method foundM = null;
        Method methods[] = beanClass.getDeclaredMethods();
        if(methods.length < 0)
            return null;
        Method arr$[] = methods;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            Method method = arr$[i$];
            if(!method.getName().equalsIgnoreCase(mName))
                continue;
            Class paramTypes[] = method.getParameterTypes();
            if(paramTypes.length != num)
                continue;
            boolean match = true;
            int i = 0;
            do
            {
                if(i >= num)
                    break;
                if(!args[i].isAssignableFrom(paramTypes[i]))
                {
                    match = false;
                    break;
                }
                i++;
            } while(true);
            if(!match)
                continue;
            foundM = method;
            break;
        }

        if(foundM == null && beanClass.getSuperclass() != null)
            foundM = findMemberMethod(beanClass.getSuperclass(), mName, num, args);
        return foundM;
    }

    public String getName()
    {
        return name;
    }

    private Class beanType;
    private Class propType;
    private String name;
    private String baseName;

}
