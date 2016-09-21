package view;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public abstract class BaseWindow {
	protected Display display;
	protected Shell shell;	
	
	protected abstract void initWidgets();
	
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
