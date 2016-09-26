package presenter;

import java.io.IOException;

import model.Model;
import view.View;

/**
 * The Class Command_character_move.
 */
public class Command_character_move implements Command{
	
	/** The view. */
	private View view;
	
	/** The model. */
	private Model model;
	
	/**
	 * Instantiates a new command character move.
	 *
	 * @param view the view
	 * @param model the model
	 */
	public Command_character_move(View view,Model model){
		this.view=view;
		this.model=model;
	}

	/* (non-Javadoc)
	 * @see presenter.Command#doCommand(java.lang.String)
	 */
	@Override
	public void doCommand(String string) throws IOException {
		view.move(model.getCurrentPosition());
	}

}
