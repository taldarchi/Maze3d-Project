package presenter;

import java.io.IOException;

import model.Model;
import view.View;

/**
 * The Class Command_left.
 */
public class Command_left implements Command{
	
	/** The view. */
	private View view;
	
	/** The model. */
	private Model model;
	
	/**
	 * Instantiates a new command left.
	 *
	 * @param view the view
	 * @param model the model
	 */
	public Command_left(View view,Model model){
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
			model.left(string);
	}

}
