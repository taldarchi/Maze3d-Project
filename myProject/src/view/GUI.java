package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class GUI extends BaseWindow {

	private MazeDisplay mazeDisplay;
	private Character character;
	private MyView view;
	
	public GUI(MyView view) {
		this.view=view;
	}

	@Override
	protected void initWidgets() {
		GridLayout grid = new GridLayout(2, true);
		shell.setLayout(grid);
		
		Composite buttons = new Composite(shell, SWT.NONE);
		RowLayout rowLayout = new RowLayout(SWT.HORIZONTAL);
		buttons.setLayout(rowLayout);
		
		Button btnDir = new Button(buttons, SWT.PUSH);
		btnDir.setText("Display directory in system");
		
		btnDir.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DirWindow win = new DirWindow(view);				
				win.start(display);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button btnGenerateMaze = new Button(buttons, SWT.PUSH);
		btnGenerateMaze.setText("Generate 3d maze");
		
		btnGenerateMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				GenerateMazeWindow win = new GenerateMazeWindow(view);				
				win.start(display);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button btnDisplayCrossSection = new Button(buttons, SWT.PUSH);
		btnDisplayCrossSection.setText("Display cross section");
		
		btnDisplayCrossSection.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DisplayCrossSectionWindow win = new DisplayCrossSectionWindow(view);				
				win.start(display);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		Button btnDisplayMaze = new Button(buttons, SWT.PUSH);
		btnDisplayMaze.setText("Display maze");
		
		btnDisplayMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DisplayMazeWindow win = new DisplayMazeWindow(view);				
				win.start(display);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		Button btnSaveMaze = new Button(buttons, SWT.PUSH);
		btnSaveMaze.setText("Save maze");
		
		btnSaveMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SaveMazeWindow win = new SaveMazeWindow(view);				
				win.start(display);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button btnLoadMaze = new Button(buttons, SWT.PUSH);
		btnLoadMaze.setText("Load maze");
		
		btnLoadMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				LoadMazeWindow win = new LoadMazeWindow(view);				
				win.start(display);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button btnDisplaySolution = new Button(buttons, SWT.PUSH);
		btnDisplaySolution.setText("Display solution");
		
		btnDisplaySolution.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DisplaySolutionWindow win = new DisplaySolutionWindow(view);				
				win.start(display);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		Button btnSolveMaze = new Button(buttons, SWT.PUSH);
		btnSolveMaze.setText("Solve maze");
		
		btnSolveMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SolveMazeWindow win = new SolveMazeWindow(view);				
				win.start(display);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button btnSaveSolutionMap = new Button(buttons, SWT.PUSH);
		btnSaveSolutionMap.setText("Save solution map");
		
		btnSaveSolutionMap.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SaveSolutionMapWindow win = new SaveSolutionMapWindow(view);				
				win.start(display);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button btnLoadSolutionMap = new Button(buttons, SWT.PUSH);
		btnLoadSolutionMap.setText("Load solution map");
		
		btnLoadSolutionMap.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				LoadSolutionMapWindow win = new LoadSolutionMapWindow(view);				
				win.start(display);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button btnExit = new Button(buttons, SWT.PUSH);
		btnExit.setText("Exit");
		
		btnExit.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String s="exit";
				view.executeCommand(s);	
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
//		mazeDisplay = new MazeDisplay(shell, SWT.BORDER);	
//		mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
//		mazeDisplay.setFocus();
	}

}
