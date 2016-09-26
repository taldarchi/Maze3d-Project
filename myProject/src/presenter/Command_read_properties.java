package presenter;

import java.io.IOException;

import model.Model;
import view.View;

/**
 * The Class Command_read_properties.
 */
public class Command_read_properties implements Command{
	
	/** The model. */
	private Model model;
	
	/**
	 * Instantiates a new command read properties.
	 *
	 * @param view the view
	 * @param model the model
	 */
	public Command_read_properties(View view,Model model){
		this.model=model;
	}

	/* (non-Javadoc)
	 * @see presenter.Command#doCommand(java.lang.String)
	 */
	@Override
	public void doCommand(String string) throws IOException {
		model.readProperties(string);
	}
}