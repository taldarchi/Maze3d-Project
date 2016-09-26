package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;

/**
 * The Class SolveMazeWindow.
 */
public class SolveMazeWindow extends DialogWindow {
	
	/** The view. */
	private MyView view;
	
	/** The maze name. */
	private String mazeName;
	
	/**
	 * Instantiates a new solve maze window.
	 *
	 * @param view the view
	 * @param mazeName the maze name
	 */
	public SolveMazeWindow(MyView view , String mazeName) {
		this.view=view;
		this.mazeName=mazeName;
	}
	
	/* (non-Javadoc)
	 * @see view.DialogWindow#initWidgets()
	 */
	@Override
	protected void initWidgets() {
		shell.setText("Solve maze window");
		shell.setSize(300, 170);		
				
		shell.setLayout(new GridLayout(2, false));
		
		Rectangle bounds = display.getPrimaryMonitor().getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
		
		Label lblAlgorithm = new Label(shell, SWT.NONE);
		lblAlgorithm.setText("Algorithm (optional): ");
		
		Composite buttons = new Composite(shell, SWT.NONE);
		buttons.setLayout(new GridLayout(1, false));
		
		Composite cmpSolve = new Composite(buttons, SWT.NONE);
		cmpSolve.setLayout(new GridLayout(1, false));
		cmpSolve.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 13));
		
		Combo cmbSolveAlgo = new Combo(cmpSolve, SWT.READ_ONLY | SWT.FILL);
		String algorithms[] = {"","BFS", "DFS"};
		cmbSolveAlgo.setItems(algorithms);
		cmbSolveAlgo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));	

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
				String algorithm=cmbSolveAlgo.getText();
				
				if(algorithm.isEmpty()){
					msg.setMessage("Solving maze "+mazeName);
					msg.open();
					String s="solve "+mazeName;
					view.executeCommand(s);
				}
				else{
					msg.setMessage("Solving maze "+mazeName+" with "+algorithm+" algorithm");
					msg.open();
					String s="solve "+mazeName+" "+algorithm;
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