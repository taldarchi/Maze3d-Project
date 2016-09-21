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

public class DisplayCrossSectionWindow extends DialogWindow{
	private MyView view;
	
	public DisplayCrossSectionWindow(MyView view) {
		this.view=view;
	}
	@Override
	protected void initWidgets() {
		shell.setText("Display cross section");
		shell.setSize(300, 175);		
				
		shell.setLayout(new GridLayout(2, false));	
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setText("Maze name: ");
		
		Text txtName = new Text(shell, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
		Label lblAxis = new Label(shell, SWT.NONE);
		lblAxis.setText("Axis: ");
		
		Button z=new Button(shell, SWT.RADIO);
		z.setText("Z");
		Button x=new Button(shell, SWT.RADIO);
		x.setText("X");
		Button y=new Button(shell, SWT.RADIO);
		y.setText("Y");
		
		
		Label lblIndex = new Label(shell, SWT.NONE);
		lblIndex.setText("Index: ");
		
		Text txtIndex = new Text(shell, SWT.BORDER);
		txtIndex.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		
				
		Button btnDisplayCrossSection = new Button(shell, SWT.PUSH);
		shell.setDefaultButton(btnDisplayCrossSection);
		btnDisplayCrossSection.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 2, 1));
		btnDisplayCrossSection.setText("Display cross section");
		
		btnDisplayCrossSection.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {				
				MessageBox msg = new MessageBox(shell, SWT.OK);
				msg.setText("Displaying...");
				//msg.setMessage("Button was clicked");
				String name=txtName.getText();
				String axis =z.getText();
				int index=Integer.parseInt(txtIndex.getText());
				
				msg.setMessage("Displaying cross section of the maze "+name+ " on axis "+axis+" and index, "+index);
				msg.open();
				
				String s="display_cross_section "+name+" "+axis+" "+index;
				view.executeCommand(s);
				
				shell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {			
				
			}
		});	
		
	}


}
