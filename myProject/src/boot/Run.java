/**
 * @file Run.java
 * 
 * @author Tal Darchi and Sharon Lapidot
 * 
 * @description main method implementation
 * 				
 * @date    11/09/2016
 */
package boot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

import model.MyModel;
import presenter.Presenter;
import utils.PropertiesFile;
import view.MyView;

/**
 * The Class Run.
 */
public class Run {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		//PropertiesFile.writeProperties();
		PropertiesFile.readProperties("properties.xml");
	    MyView view=new MyView(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out, true));
		MyModel model=new MyModel();
		Presenter p=new Presenter(view,model);
		view.addObserver(p);
		model.addObserver(p);
		view.start();

	}

}
