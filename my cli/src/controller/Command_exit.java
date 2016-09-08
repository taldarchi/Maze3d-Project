package controller;

import java.io.IOException;

import view.View;

public class Command_exit implements Command{
	private View view;
	public Command_exit(View view){
		this.view=view;
	}
	
	@Override
	public void doCommand(String string) throws IOException {
		view.exit();
	}

}
