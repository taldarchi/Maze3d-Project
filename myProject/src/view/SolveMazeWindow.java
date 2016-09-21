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

public class SolveMazeWindow extends DialogWindow {
	private MyView view;
	
	public SolveMazeWindow(MyView view) {
		this.view=view;
	}
	@Override
	protected void initWidgets() {
		shell.setText("Solve maze window");
		shell.setSize(400, 125);		
				
		shell.setLayout(new GridLayout(2, false));	
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setText("Maze name: ");
		
		Text txtName = new Text(shell, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblAlgorithm = new Label(shell, SWT.NONE);
		lblAlgorithm.setText("Algorithm (optional): ");
		
		Text txtAlgorithm = new Text(shell, SWT.BORDER);
		txtAlgorithm.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		

		Button btnSolveMaze = new Button(shell, SWT.PUSH);
		shell.setDefaultButton(btnSolveMaze);
		btnSolveMaze.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		btnSolveMaze.setText("Solve maze");
		
		btnSolveMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {				
				MessageBox msg = new MessageBox(shell, SWT.OK);
				msg.setText("Solving...");
				//msg.setMessage("Button was clicked");
				String name=txtName.getText();
				String algorithm=txtAlgorithm.getText();
				
				if(algorithm==null){
					msg.setMessage("Solving maze "+name);
					msg.open();
					String s="solve_maze "+name;
					view.executeCommand(s);
				}
				else{
					msg.setMessage("Solving maze "+name+" with "+algorithm+" algorithm");
					msg.open();
					String s="solve_maze "+name+" "+algorithm;
					view.executeCommand(s);
				}

				
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {			
				
			}
		});	
		
	}

}