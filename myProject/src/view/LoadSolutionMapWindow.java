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

public class LoadSolutionMapWindow extends DialogWindow{
	private MyView view;
	
	public LoadSolutionMapWindow(MyView view) {
		this.view=view;
	}
	@Override
	protected void initWidgets() {
		shell.setText("Load solution map window");
		shell.setSize(350, 100);		
				
		shell.setLayout(new GridLayout(2, false));	
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setText("Solution map filename: ");
		
		Text txtName = new Text(shell, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
				
		Button btnLoadSolutionMap = new Button(shell, SWT.PUSH);
		shell.setDefaultButton(btnLoadSolutionMap);
		btnLoadSolutionMap.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		btnLoadSolutionMap.setText("Load solution map");
		
		btnLoadSolutionMap.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {				
				MessageBox msg = new MessageBox(shell, SWT.OK);
				msg.setText("Loading...");
				//msg.setMessage("Button was clicked");
				String name=txtName.getText();		
				msg.setMessage("Loading solution map "+name);
				msg.open();
				String s="load_solution_map "+name;
				view.executeCommand(s);
				
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {			
				
			}
		});	
		
	}

}
