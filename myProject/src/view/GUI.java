package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

public class GUI extends BaseWindow {

	private MazeDisplay mazeDisplay;
	private Character character;
	private MyView view;
	private String mazeName;
	private boolean hint;
	private Maze3d maze;
	private int[][] crossSection;
	private Position currentPosition;

	public GUI(MyView view) {
		this.view=view;
	}

	@Override
	protected void initWidgets() {
		GridLayout grid = new GridLayout(2, false);
		shell.setLayout(grid);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackgroundImage(new Image(null, "images/background.jpg"));
		
		Composite buttons = new Composite(shell, SWT.NONE);
		RowLayout rowLayout = new RowLayout(SWT.VERTICAL);
		buttons.setLayout(rowLayout);
		
		
		//center of screen
		Rectangle bounds = display.getPrimaryMonitor().getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
	
		
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
				
			}
		});
		
		Button btnDisplayMaze = new Button(buttons, SWT.PUSH);
		btnDisplayMaze.setText("Choose maze to play");
		
		btnDisplayMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				DisplayMazeWindow win = new DisplayMazeWindow(view);				
				win.start(display);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});
		
		Button btnHint = new Button(buttons, SWT.PUSH | SWT.FILL);
		btnHint.setText("Hint");
		
		btnHint.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (mazeName == null)
					view.printMessage("Generate/Load a maze first!");
				else {
					hint = true;
					view.executeCommand("hint " + mazeName);
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
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
				
			}
		});
		
		Button btnOpenProperties = new Button(buttons, SWT.PUSH);
		btnOpenProperties.setText("Load properties file");
		
		btnOpenProperties.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				PropertiesWindow win=new PropertiesWindow(view);
				win.start(display);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
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
				
			}
		});
		
		shell.addListener(SWT.Close, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				view.executeCommand("exit");
			}
		});

		mazeDisplay = new MazeDisplay(shell, SWT.BORDER,view);	
		mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		mazeDisplay.setFocus();
	}
	
	public void displayCurrentMaze(Maze3d maze, String mazeName){
		this.mazeName=mazeName;
		this.maze=maze;
		this.crossSection=maze.getCrossSectionByZ(0);
		this.mazeDisplay.setCharacterPosition(maze.getStartPosition());
		this.mazeDisplay.setCrossSection(this.crossSection);
		this.mazeDisplay.setGoalPosition(this.maze.getGoalPosition());
		this.mazeDisplay.setMazeName(this.mazeName);
		this.mazeDisplay.setMaze(this.maze);
		this.mazeDisplay.setMazeLoaded();
		this.mazeDisplay.setCurrentPosition(maze.getStartPosition());
		this.mazeDisplay.setFinish();
	}
	
	public void message(String string){
		MessageBox msg = new MessageBox(shell, SWT.OK);
		msg.setText("Message");
		msg.setMessage(string);
		msg.open();
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}
	
	public void setCharacterPositionInMazeDisplay(Position p){
		this.mazeDisplay.setCharacterPosition(p);
	}
	
	public void move(Position p){
		this.crossSection = this.maze.getCrossSectionByZ(p.getZ());
		this.mazeDisplay.setCrossSection(this.crossSection);
		this.mazeDisplay.setCharacterPosition(p);
		this.mazeDisplay.setCurrentPosition(p);
		if(p.equals(maze.getGoalPosition())){
			MessageBox msg = new MessageBox(shell, SWT.OK);
			msg.setText("Message");
	     	msg.setMessage("You Won!!!");
			msg.open();
		}
			
	}

}
