package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

public class DirWindow extends DialogWindow{
	private MyView view;
	
	public DirWindow(MyView view) {
		this.view=view;
	}
	@Override
	protected void initWidgets() {
		shell.setText("display directory window");
		shell.setSize(350, 100);		
				
		shell.setLayout(new GridLayout(2, false));	
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setText("Choose directory in your system: ");
		
		Text txtName = new Text(shell, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
				
		Button btnLoadSolutionMap = new Button(shell, SWT.PUSH);
		shell.setDefaultButton(btnLoadSolutionMap);
		btnLoadSolutionMap.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		btnLoadSolutionMap.setText("Display files/folder in directory");
		
		btnLoadSolutionMap.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {				
				MessageBox msg = new MessageBox(shell, SWT.OK);
				msg.setText("Displaying...");
				//msg.setMessage("Button was clicked");
				String name=txtName.getText();		
				msg.setMessage("Displaying files in "+name);
				msg.open();
				String s="dir "+name;
				view.executeCommand(s);
				
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {			
				
			}
		});	
		
	}

}
