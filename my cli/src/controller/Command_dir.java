package controller;

public class Command_dir implements Command{
	
	@Override
	public void doCommand(String string) {
		view.dirPath(string);
	}
}
