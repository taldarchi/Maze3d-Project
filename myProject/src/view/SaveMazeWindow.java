package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

public class SaveMazeWindow extends DialogWindow{
	private MyView view;
	
	public SaveMazeWindow(MyView view) {
		this.view=view;
	}
	@Override
	protected void initWidgets() {
		shell.setText("Save maze window");
		shell.setSize(350, 125);		
				
		shell.setLayout(new GridLayout(2, false));	
		
		Rectangle bounds = display.getPrimaryMonitor().getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setText("Maze name: ");
		
		Text txtName = new Text(shell, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblFileName = new Label(shell, SWT.NONE);
		lblFileName.setText("Save as filename: ");
		
		Text txtFileName = new Text(shell, SWT.BORDER);
		txtFileName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
				
		Button btnSaveMaze = new Button(shell, SWT.PUSH);
		shell.setDefaultButton(btnSaveMaze);
		btnSaveMaze.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		btnSaveMaze.setText("Save maze");
		
		btnSaveMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {				
				MessageBox msg = new MessageBox(shell, SWT.OK);
				msg.setText("Saving...");
				//msg.setMessage("Button was clicked");
				String fileName=txtFileName.getText();
				String name=txtName.getText();		
				
				msg.setMessage("Saving: "+name+" as: "+fileName);
				String s="save_maze "+name+" "+fileName;
				view.executeCommand(s);
				
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {			
				
			}
		});	
		
	}
}