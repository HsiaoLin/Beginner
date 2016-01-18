package com.beginner;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.beginner.system.bean.user.User;

public class Test {

	public static void main(String[] args) {
		User u = new User();
		u.setUserMail("1");
		System.out.println(validateModel(u));
	}

	public static String validateModel(Object obj) {//验证某一个对象  

		StringBuffer buffer = new StringBuffer(64);//用于存储验证后的错误信息  

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);//验证某个对象,，其实也可以只验证其中的某一个属性的  

		Iterator<ConstraintViolation<Object>> iter = constraintViolations.iterator();
		while (iter.hasNext()) {
			String message = iter.next().getMessage();
			buffer.append(message);
		}
		return buffer.toString();
	}
}
