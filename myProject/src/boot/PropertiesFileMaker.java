package boot;

import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import presenter.Properties;

public class PropertiesFileMaker {

	public static void main(String[] args) {
		XMLEncoder encoder;
		try {
			encoder = new XMLEncoder(new FileOutputStream("properties.xml"));
			Properties properties = new Properties();
			properties.setGeneratorAlgorithm("growing_tree_last");
			properties.setSearchAlgorithm("bfs");
			properties.setThreadsNum(5);
			encoder.writeObject(properties);
			encoder.close();
		} catch (FileNotFoundException e) {
		}	

	}

}
