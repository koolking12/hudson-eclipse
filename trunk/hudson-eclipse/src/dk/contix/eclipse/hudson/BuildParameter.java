package dk.contix.eclipse.hudson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

public class BuildParameter implements Serializable {
	private String name;
	private String value;

	public BuildParameter(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	public BuildParameter() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	public static String serialize(List<BuildParameter> params) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(params);
			oos.close();
			
			byte[] b = Base64.encodeBase64(bos.toByteArray());
			
			return new String(b);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static List<BuildParameter> deserialize(String data) {
		if (data == null || "".equals(data)) return null;

		byte[] s = Base64.decodeBase64(data.getBytes());
		
		ByteArrayInputStream bis = new ByteArrayInputStream(s);
		try {
			ObjectInputStream ois = new ObjectInputStream(bis);
			return (List<BuildParameter>) ois.readObject();
		} catch (StreamCorruptedException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
