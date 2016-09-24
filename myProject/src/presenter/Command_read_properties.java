package presenter;

import java.io.IOException;

import model.Model;
import view.View;

public class Command_read_properties implements Command{
	private Model model;
	
	public Command_read_properties(View view,Model model){
		this.model=model;
	}

	@Override
	public void doCommand(String string) throws IOException {
		model.readProperties(string);
	}
}