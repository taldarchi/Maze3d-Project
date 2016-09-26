package presenter;

import java.io.IOException;

import model.Model;
import utils.PropertiesFile;
import view.View;

/**
 * The Class Command_hint.
 */
public class Command_hint implements Command{
	
	/** The model. */
	private Model model;
	
	/**
	 * Instantiates a new command hint.
	 *
	 * @param view the view
	 * @param model the model
	 */
	public Command_hint(View view,Model model){
		this.model=model;
	}

	/* (non-Javadoc)
	 * @see presenter.Command#doCommand(java.lang.String)
	 */
	@Override
	public void doCommand(String string) throws IOException {
		model.solveMaze(string, PropertiesFile.getProperties().getSearchAlgorithm());
		model.hint(string);
	}
	
	

}
