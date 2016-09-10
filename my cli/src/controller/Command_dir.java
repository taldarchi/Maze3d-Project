/**
 * @file Command_dir.java
 * 
 * @author Tal Darchi
 * 
 * @description represents a command responsible to 
 *              show the folders and/or files in desired dir.
 * 				
 * @date    08/09/2016
 */

package controller;

import view.View;

/**
 * The Class Command_dir.
 */
public class Command_dir implements Command{
	
	/** The view. */
	private View view;
	
	/**
	 * Instantiates a new command dir.
	 *
	 * @param view the view
	 */
	public Command_dir(View view){
		this.view=view;
	}
	
	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String)
	 */
	@Override
	public void doCommand(String string) {
		view.dirPath(string);
	}
}
