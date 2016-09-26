package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * The Class PropertiesWindow.
 */
public class PropertiesWindow extends DialogWindow{
	
	/** The view. */
	private MyView view;
	
	/**
	 * Instantiates a new properties window.
	 *
	 * @param view the view
	 */
	public PropertiesWindow(MyView view) {
		this.view=view;
	}
	
	/* (non-Javadoc)
	 * @see view.DialogWindow#initWidgets()
	 */
	@Override
	protected void initWidgets() {
		shell.setText("Properties window");
		shell.setSize(350, 100);		
				
		shell.setLayout(new GridLayout(2, false));	
		
		Rectangle bounds = display.getPrimaryMonitor().getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setText("Properties XML to load: ");
		
		Text txtName = new Text(shell, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
				
		Button btnLoadProperties = new Button(shell, SWT.PUSH);
		shell.setDefaultButton(btnLoadProperties);
		btnLoadProperties.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		btnLoadProperties.setText("Load properties");
		
		btnLoadProperties.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {				
	
				String s="load_properties "+txtName.getText();
				view.executeCommand(s);
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {			
				
			}
		});	
		
	}

}
