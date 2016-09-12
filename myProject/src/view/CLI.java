/**
 * @file CLI.java
 * 
 * @author Tal Darchi and Sharon Lapidot
 * 
 * @description represents the command line interface with the user
 * 				
 * @date    08/09/2016
 */
package view;

import java.io.IOException;

/**
 * The Class CLI.
 */
public class CLI{
	
	/** The view. */
	private MyView view;
	
	/**
	 * Instantiates a new cli.
	 *
	 * @param view the view
	 */
	public CLI(MyView view){
		this.view=view;
	}
	
	/**
	 * Start.
	 * gets commands from user and analyses them.
	 * runs on a partial thread.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void start() throws IOException{

	//new Thread(new Runnable(){
	//	@Override
	//	public void run() {
			printMenu();
			while(true){
				String command=getLineFromView();
				view.executeCommand(command);
			}
		}
	//}).start();	
	
	//}
	
	/**
	 * Prints the menu.
	 */
	public void printMenu(){
	    this.view.printMessage("\nPlease Choose a Command:\n\n"
	    		+ "dir <path> - Display files/folders in the desired path\n"
	    		+ "generate_3d_maze <name> <X,Y,Z> <algorithm> - Create a maze with desired parameters\n"
	    		+ "display <name> - Print maze\n"
	    		+ "display_cross_section <name> <X/Y/Z> <index> - Display 2d array of the desired index in a maze\n"
	    		+ "save_maze <name> <file name> - Save maze\n"
	    		+ "load_maze <file name> <name> - Load maze\n"
	    		+ "solve <name> <algorithm> - Solve desired maze with desired algorithm\n"
	    		+ "display_solution <name> - Display solution\n"
	    		+ "save_solution_map <file name> - Save existing solution map to a file called <file name>\n"
	    		+ "load_solution_map <file name> - Load solution map from a file called <file_name>\n"
	    		+ "exit - Close and exit everything\n");
	}
	
	/**
	 * Gets the line from view.
	 *
	 * @return the line from view
	 */
	public String getLineFromView() {
		return this.view.getLine();
}
}