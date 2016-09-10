/**
 * @file Command_dir.java
 * 
 * @author Tal Darchi and Sharon Lapidot
 * 
 * @description represents a command responsible to make the project exit without any threads or streams open.
 * 				
 * @date    08/09/2016
 */
package controller;

import java.io.IOException;

import view.View;

/**
 * The Class Command_exit.
 */
public class Command_exit implements Command{
	
	/** The view. */
	private View view;
	
	/**
	 * Instantiates a new command exit.
	 *
	 * @param view the view
	 */
	public Command_exit(View view){
		this.view=view;
	}
	
	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String)
	 */
	@Override
	public void doCommand(String string) throws IOException {
		view.exit();
	}

}
