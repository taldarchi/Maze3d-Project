package presenter;

import java.io.IOException;

import model.Model;
import view.View;

/**
 * The Class Command_up.
 */
public class Command_up implements Command {
	
	/** The view. */
	private View view;
	
	/** The model. */
	private Model model;
	
	/**
	 * Instantiates a new command up.
	 *
	 * @param view the view
	 * @param model the model
	 */
	public Command_up(View view,Model model){
		this.view=view;
		this.model=model;
	}

	/* (non-Javadoc)
	 * @see presenter.Command#doCommand(java.lang.String)
	 */
	@Override
	public void doCommand(String string) throws IOException {
		String[] strings=string.split(" ");
		if(strings.length!=1)
			view.printMessage("Bad parameters, try again");
		else
			model.up(string);
	}
}
