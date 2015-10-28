package com.beginner.utils;

import java.lang.reflect.Field;

/**
* <b>类名称：</b>反射工具类<br/>
* <b>类描述：</b><br/>
* <b>创建人：</b>尹枭凌工作室-Hsiao Lin<br/>
* <b>修改人：</b><br/>
* <b>修改时间：</b>2015年10月22日 下午4:11:22<br/>
* <b>修改备注：</b><br/>
* @version 1.0.0<br/>
*/
public class ReflectHelper {

	/**
	* getFieldByFieldName(方法描述：获取obj对象fieldName的Field) <br />
	* (方法适用条件描述： – 可选)
	* @param obj
	* @param fieldName
	* @return
	* Field
	* @exception
	* @since  1.0.0
	*/
	public static Field getFieldByFieldName(Object obj, String fieldName) {
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
			}
		}
		return null;
	}

	/**
	* getValueByFieldName(方法描述：获取obj对象fieldName的属性值) <br />
	* (方法适用条件描述： – 可选)
	* @param obj
	* @param fieldName
	* @return
	* @throws SecurityException
	* @throws NoSuchFieldException
	* @throws IllegalArgumentException
	* @throws IllegalAccessException
	* Object
	* @exception
	* @since  1.0.0
	*/
	public static Object getValueByFieldName(Object obj, String fieldName) throws SecurityException, NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		Field field = getFieldByFieldName(obj, fieldName);
		Object value = null;
		if (field != null) {
			if (field.isAccessible()) {
				value = field.get(obj);
			} else {
				field.setAccessible(true);
				value = field.get(obj);
				field.setAccessible(false);
			}
		}
		return value;
	}

	/**
	* setValueByFieldName(方法描述：设置obj对象fieldName的属性值) <br />
	* (方法适用条件描述： – 可选)
	* @param obj
	* @param fieldName
	* @param value
	* @throws SecurityException
	* @throws NoSuchFieldException
	* @throws IllegalArgumentException
	* @throws IllegalAccessException
	* void
	* @exception
	* @since  1.0.0
	*/
	public static void setValueByFieldName(Object obj, String fieldName, Object value) throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Field field = obj.getClass().getDeclaredField(fieldName);
		if (field.isAccessible()) {
			field.set(obj, value);
		} else {
			field.setAccessible(true);
			field.set(obj, value);
			field.setAccessible(false);
		}
	}
}
