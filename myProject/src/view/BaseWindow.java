package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * The Class BaseWindow.
 */
public abstract class BaseWindow {
	
	/** The display. */
	protected Display display;
	
	/** The shell. */
	protected Shell shell;
	
	/**
	 * Inits the widgets.
	 */
	protected abstract void initWidgets();
	
	/**
	 * Start.
	 */
	public void start() {
		display = new Display();
		shell = new Shell(display);
		
		initWidgets();
		
		shell.open();		
		
		// main event loop
		while(!shell.isDisposed()){ // window isn't closed
			if(!display.readAndDispatch()){
				display.sleep();
			}
		}
		display.dispose();
	}
}
