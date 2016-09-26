package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * The Class DialogWindow.
 */
public abstract class DialogWindow extends MyView{

	/** The shell. */
	protected Shell shell;
	
	/** The display. */
	protected Display display;
	
	/**
	 * Inits the widgets.
	 */
	protected abstract void initWidgets();
	
	/**
	 * Start.
	 *
	 * @param display the display
	 */
	public void start(Display display) {	
		this.display=display;
		shell = new Shell(display);
		initWidgets();
		shell.open();		
	}
}
