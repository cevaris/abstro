package com.cevaris.abstro;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.codec.binary.Base64;

public class Utils {
	
	private static final Base64 b64 = new Base64();
	
	public static String encode(Object object) {
		String encoded = null;
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					byteArrayOutputStream);
			objectOutputStream.writeObject(object);
			objectOutputStream.close();
			
			encoded = new String(b64.encode(byteArrayOutputStream.toByteArray()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return encoded;
	}

	@SuppressWarnings("unchecked")
	public static <t extends Object, T> T decode(String string, Class<t> clazz) {
		byte[] bytes = b64.decode(string.getBytes());
		T object = null;
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(
					new ByteArrayInputStream(bytes));
			object = (T) objectInputStream.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		return object;
	}

//	/** Read the object from Base64 string. */
//	public static Object decode(String s) {
//		Object obj = null;
//		try {
//			byte b[] = s.getBytes();
//			ByteArrayInputStream bi = new ByteArrayInputStream(b);
//			ObjectInputStream si = new ObjectInputStream(bi);
//			obj = si.readObject();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return obj;
//	}
//
//	/** Write the object to a Base64 string. */
//	public static String encode(Object o) {
//		String result = null;
//		try {
//			ByteArrayOutputStream bo = new ByteArrayOutputStream();
//			ObjectOutputStream so = new ObjectOutputStream(bo);
//			so.writeObject(o);
//			so.flush();
//			result = bo.toString();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}

}
