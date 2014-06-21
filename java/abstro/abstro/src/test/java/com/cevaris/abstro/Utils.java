package com.cevaris.abstro;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Utils {

	/** Read the object from Base64 string. */
	public static Object decode(String s) {
		Object obj = null;
		try {
			byte b[] = s.getBytes();
			ByteArrayInputStream bi = new ByteArrayInputStream(b);
			ObjectInputStream si = new ObjectInputStream(bi);
			obj = si.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}

	/** Write the object to a Base64 string. */
	public static String encode(Serializable o) {
		String result = null;
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream so = new ObjectOutputStream(bo);
			so.writeObject(o);
			so.flush();
			result = bo.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
