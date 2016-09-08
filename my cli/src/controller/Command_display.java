package controller;

public class Command_display implements Command {

	@Override
	public void doCommand(String string) {
		view.display(string);
	}

}
