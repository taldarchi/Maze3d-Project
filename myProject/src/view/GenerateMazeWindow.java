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
import org.eclipse.swt.widgets.Text;

public class GenerateMazeWindow extends DialogWindow {
	
	private MyView view;
	
	public GenerateMazeWindow(MyView view) {
		this.view=view;
	}

	@Override
	protected void initWidgets() {
		
		shell.setText("Generate maze window");
		shell.setSize(350, 270);		
				
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
		
		Label lblFloors = new Label(shell, SWT.NONE);
		lblFloors.setText("Floors: ");
		
		Text txtFloors = new Text(shell, SWT.BORDER);
		txtFloors.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblRows = new Label(shell, SWT.NONE);
		lblRows.setText("Rows: ");
		
		Text txtRows = new Text(shell, SWT.BORDER);
		txtRows.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblColumns = new Label(shell, SWT.NONE);
		lblColumns.setText("Columns: ");
		
		Text txtColumns = new Text(shell, SWT.BORDER);
		txtColumns.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblAlgorithm = new Label(shell, SWT.NONE);
		lblAlgorithm.setText("Algorithm (optional): ");
		
		Composite buttons = new Composite(shell, SWT.NONE);
		buttons.setLayout(new GridLayout(1, false));
		
		Composite cmpGen = new Composite(buttons, SWT.NONE);
		cmpGen.setLayout(new GridLayout(1, false));
		cmpGen.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 13));
		
		Combo cmbGenAlgo = new Combo(cmpGen, SWT.READ_ONLY | SWT.FILL);
		String algorithms[] = {"","growing_tree_last", "growing_tree_random","simple"};
		cmbGenAlgo.setItems(algorithms);
		cmbGenAlgo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnGenerateMaze = new Button(shell, SWT.PUSH);
		shell.setDefaultButton(btnGenerateMaze);
		btnGenerateMaze.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		btnGenerateMaze.setText("Generate maze!");
		
		btnGenerateMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {				
				MessageBox msg = new MessageBox(shell, SWT.OK);
				msg.setText("Generating...");
				String name=txtName.getText();
				try{
				int floors=Integer.parseInt(txtFloors.getText());
				int rows = Integer.parseInt(txtRows.getText());
				int cols = Integer.parseInt(txtColumns.getText());

				String algorithm=cmbGenAlgo.getText();
				
				if(algorithm.isEmpty()){
					msg.setMessage("Generating maze "+name+ " with "+floors+" floors, "+rows+" rows and "+cols+" columns.");
					msg.open();
					String s="generate_3d_maze "+name+" "+floors+","+rows+","+cols;
					view.executeCommand(s);	
				}
				else{
					msg.setMessage("Generating maze "+name+ " with "+floors+" floors, "+rows+" rows and "+cols+" columns with "+algorithm+" algorithm");
					msg.open();
					String s="generate_3d_maze "+name+" "+floors+","+rows+","+cols+" "+algorithm;
					view.executeCommand(s);	
				}
				}
				catch(NumberFormatException e){
					view.printMessage("Bad parameters, try again");
				}
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {			
				
			}
		});	
		
	}

}
