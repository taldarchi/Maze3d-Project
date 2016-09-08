package controller;

import view.View;

public class Command_dir implements Command{
	private View view;
	
	public Command_dir(View view){
		this.view=view;
	}
	
	@Override
	public void doCommand(String string) {
		view.dirPath(string);
	}
}
