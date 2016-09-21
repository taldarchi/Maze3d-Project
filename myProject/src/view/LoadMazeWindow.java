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

public class LoadMazeWindow extends DialogWindow{

	private MyView view;
	
	public LoadMazeWindow(MyView view){
		this.view=view;
	}
	@Override
	protected void initWidgets() {
		shell.setText("Load maze window");
		shell.setSize(350, 125);		
				
		shell.setLayout(new GridLayout(2, false));	
		
		Label lblFileName = new Label(shell, SWT.NONE);
		lblFileName.setText("Filename to load: ");
		
		Text txtFileName = new Text(shell, SWT.BORDER);
		txtFileName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setText("Choose name: ");
		
		Text txtName = new Text(shell, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
				
		Button btnLoadMaze = new Button(shell, SWT.PUSH);
		shell.setDefaultButton(btnLoadMaze);
		btnLoadMaze.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		btnLoadMaze.setText("Load maze");
		
		btnLoadMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {				
				MessageBox msg = new MessageBox(shell, SWT.OK);
				msg.setText("Loading...");
				//msg.setMessage("Button was clicked");
				String fileName=txtFileName.getText();
				String name=txtName.getText();		
				
				msg.setMessage("Loading maze: "+fileName+" as: "+name);
				msg.open();
				String s="load_maze "+fileName+" "+name;
				view.executeCommand(s);
				
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {			
				
			}
		});	
		
	}
}

