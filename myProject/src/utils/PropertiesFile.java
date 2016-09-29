package utils;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import presenter.Properties;

/**
 * The Class PropertiesFile.
 */
public class PropertiesFile {

	/** The properties. */
	private static Properties properties = new Properties();
	
	/**
	 * Write properties.
	 */
	public static void writeProperties() {
		XMLEncoder encoder;
		try {
			encoder = new XMLEncoder(new FileOutputStream("resources/properties.xml"));
			properties.setGeneratorAlgorithm("growing_tree_last");
			properties.setSearchAlgorithm("bfs");
			properties.setThreadsNum(5);
			properties.setUserInterface("gui");
			encoder.writeObject(properties);
			encoder.close();
		} catch (FileNotFoundException e) {
		}
	}
	
	/**
	 * Read properties.
	 *
	 * @param filename the filename
	 * @throws FileNotFoundException the file not found exception
	 */
	public static void readProperties(String filename) throws FileNotFoundException {
		XMLDecoder decoder;
			decoder = new XMLDecoder(PropertiesFile.class.getClassLoader().getResourceAsStream("resources/"+filename));
			properties=(Properties) decoder.readObject();
			decoder.close();
	}

	/**
	 * Gets the properties.
	 *
	 * @return the properties
	 */
	public static Properties getProperties(){
		return properties;
	}
}
