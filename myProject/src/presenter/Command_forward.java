package presenter;

import java.io.IOException;

import model.Model;
import view.View;

public class Command_forward implements Command{
	private View view;
	private Model model;
	
	public Command_forward(View view,Model model){
		this.view=view;
		this.model=model;
	}

	@Override
	public void doCommand(String string) throws IOException {
		String[] strings=string.split(" ");
		if(strings.length!=1)
			view.printMessage("Bad parameters, try again");
		else{
			model.forward(string);
		}
		
	}
	
}
