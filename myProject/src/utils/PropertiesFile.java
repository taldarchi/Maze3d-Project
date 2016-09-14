package utils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import presenter.Properties;

public class PropertiesFile {

	private static Properties properties = new Properties();;
	
	public static void writeProperties() {
		XMLEncoder encoder;
		try {
			encoder = new XMLEncoder(new FileOutputStream("properties.xml"));
			properties.setGeneratorAlgorithm("growing_tree_last");
			properties.setSearchAlgorithm("bfs");
			properties.setThreadsNum(5);
			encoder.writeObject(properties);
			encoder.close();
		} catch (FileNotFoundException e) {
		}
	}
	
	public static void readProperties() {
		XMLDecoder decoder;
		try {
			decoder = new XMLDecoder(new FileInputStream("properties.xml"));
			properties=(Properties) decoder.readObject();
			decoder.close();
		} catch (FileNotFoundException e) {
			System.out.println("Properties file not found");
			e.printStackTrace();
		}
	}

	public static Properties getProperties(){
		return properties;
	}
}
