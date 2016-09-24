package presenter;

import java.io.IOException;

import algorithms.mazeGenerators.Position;
import model.Model;
import view.View;

public class Command_character_move implements Command{
	private View view;
	private Model model;
	
	public Command_character_move(View view,Model model){
		this.view=view;
		this.model=model;
	}

	@Override
	public void doCommand(String string) throws IOException {
		Position currentPosition = model.getCurrentPosition();
		view.move(currentPosition);
	}

}
