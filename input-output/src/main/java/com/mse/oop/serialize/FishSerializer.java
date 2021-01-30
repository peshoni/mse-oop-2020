package com.mse.oop.serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FishSerializer {
	public static void serialize(Fish fish, String fileName) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fileName)))) {
			oos.writeObject(fish);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static Fish deserialize(String fileName) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(fileName)))) {
			Object o = ois.readObject();
			return (Fish) o;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
