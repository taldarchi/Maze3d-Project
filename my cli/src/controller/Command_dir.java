package controller;

import java.io.File;

public class Command_dir implements Command{

	@Override
	public void doCommand(String string) {
		File folder = new File(string);
		File[] listOfFiles = folder.listFiles();
		    for (int i = 0; i < listOfFiles.length; i++){
		      if (listOfFiles[i].isFile())
		        System.out.println("File " + listOfFiles[i].getName());
		      else if (listOfFiles[i].isDirectory()) 
		        System.out.println("Directory " + listOfFiles[i].getName());
		    }
	}
	

}
