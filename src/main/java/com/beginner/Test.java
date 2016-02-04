package com.beginner;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.slf4j.LoggerFactory;

import com.beginner.system.bean.user.User;

public class Test {

	private static org.slf4j.Logger log = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) {
		User u = new User();
		u.setUserMail("1");
		//		System.out.println(validateModel(u));
		//		int[] arrays = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		//		mark: {
		//			for (int i = 0; i < arrays.length; i++) {
		//				for (int j = 0; j < arrays.length; j++) {
		//					if (arrays[j] == 5) {
		//						System.out.println(arrays[i]);
		//						System.out.println(arrays[j]);
		//						break mark;
		//					}
		//				}
		//			}
		//		}
		log.debug("继续运行~！debug");
		log.info("继续运行~！info");
		log.warn("继续运行~！info");
		log.error("error");
		log.trace("trace!");

		//		String s = "1,2,3,4,5";
		//		char[] c = s.toCharArray();
		//		for (char d : c) {
		//			System.out.println(d);
		//		}
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
