package com.ran.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {
	public static byte[] serialize(Object source) throws IOException {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		ObjectOutputStream objOut = null;
		try {
			objOut = new ObjectOutputStream(byteOut);
			objOut.writeObject(source);
			objOut.flush();
		}
		finally {
			if(objOut != null) {
				objOut.close();
			}
		}
		return byteOut.toByteArray();
	}

	public static Object deserialize(byte[] source) throws IOException, ClassNotFoundException {
		ObjectInputStream objIn = null;
		Object retVal = null;
	    try {
	    	ByteArrayInputStream byteIn = new ByteArrayInputStream(source);
	    	objIn = new ObjectInputStream(byteIn);
	    	retVal = objIn.readObject();
	    }
	    finally {
	        if(objIn != null) {
	        	objIn.close();
	        }
	    }
	    return retVal;
	}
}
