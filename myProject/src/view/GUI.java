package view;

import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;

/**
 * The Class GUI.
 */
public class GUI extends BaseWindow {

	/** The maze display. */
	private MazeDisplay mazeDisplay;

	/** The view. */
	private MyView view;
	
	/** The maze name. */
	private String mazeName;

	/** The maze. */
	private Maze3d maze;
	
	/** The cross section. */
	private int[][] crossSection;
	
	/** The current position. */
	private Position currentPosition;
	
	/** The animation. */
	private TimerTask animation;
	
	/** The timing. */
	private Timer timing;

	/**
	 * Instantiates a new gui.
	 *
	 * @param view the view
	 */
	public GUI(MyView view) {
		this.view=view;
	}

	/* (non-Javadoc)
	 * @see view.BaseWindow#initWidgets()
	 */
	@Override
	protected void initWidgets() {
		GridLayout grid = new GridLayout(2, false);
		shell.setLayout(grid);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackgroundImage(new Image(null, "resources/images/background.jpg"));
		
		Composite buttons = new Composite(shell, SWT.NONE);
		buttons.setLayout(new GridLayout(1, false));
		
		//center of screen
		Rectangle bounds = display.getPrimaryMonitor().getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
	
		
		Button btnGenerateMaze = new Button(buttons, SWT.PUSH);
		btnGenerateMaze.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
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
		btnDisplayMaze.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
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
		btnHint.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnHint.setText("Hint");
		
		btnHint.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (mazeName == null)
					view.printMessage("Generate/Load a maze first!");
				else 
					view.executeCommand("hint " + mazeName);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});
		
		
		Button btnSolveMaze = new Button(buttons, SWT.PUSH);
		btnSolveMaze.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnSolveMaze.setText("Solve maze");
		
		btnSolveMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				SolveMazeWindow win = new SolveMazeWindow(view,mazeName);				
				win.start(display);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});
		
		Button btnOpenProperties = new Button(buttons, SWT.PUSH);
		btnOpenProperties.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
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
		btnSaveMaze.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		btnSaveMaze.setText("Save maze");
		
		btnSaveMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				MessageBox msg = new MessageBox(shell, SWT.OK);
				msg.setText("Saving...");
				String fileName=mazeName+".maz";
				String name=mazeName;		
				if (mazeName == null)
					view.printMessage("Generate/Load a maze first!");
				else{
					String s="save_maze "+name+" "+fileName;
					view.executeCommand(s);
					msg.setMessage("Saved "+name+" as: "+fileName);
					msg.open();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				
			}
		});
		
		Button btnLoadMaze = new Button(buttons, SWT.PUSH);
		btnLoadMaze.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
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
		btnExit.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
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
	
	/**
	 * Display current maze.
	 *
	 * @param maze the maze
	 * @param mazeName the maze name
	 */
	public void displayCurrentMaze(Maze3d maze, String mazeName){
		this.mazeName=mazeName;
		this.maze=maze;
		this.crossSection=maze.getCrossSectionByZ(0);
		this.mazeDisplay.setCharacterPosition(maze.getStartPosition());
		this.mazeDisplay.setCrossSection(this.crossSection);
		this.mazeDisplay.setMazeName(this.mazeName);
		this.mazeDisplay.setMaze(this.maze);
		this.mazeDisplay.setMazeLoaded();
		this.mazeDisplay.setCurrentPosition(maze.getStartPosition());
		this.mazeDisplay.setFinish();

	}
	
	/**
	 * Message.
	 *
	 * @param string the string
	 */
	public void message(String string){
		MessageBox msg = new MessageBox(shell, SWT.OK);
		msg.setText("Message");
		msg.setMessage(string);
		msg.open();
	}

	/**
	 * Gets the current position.
	 *
	 * @return the current position
	 */
	public Position getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * Sets the current position.
	 *
	 * @param currentPosition the new current position
	 */
	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}
	
	/**
	 * Sets the character position in maze display.
	 *
	 * @param p the new character position in maze display
	 */
	public void setCharacterPositionInMazeDisplay(Position p){
		this.mazeDisplay.setCharacterPosition(p);
	}
	
	/**
	 * Move.
	 *
	 * @param p the p
	 */
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
	
	/**
	 * Solution animation.
	 *
	 * @param solution the solution
	 */
	public void solutionAnimation(Solution<Position> solution){
		animation = new TimerTask() {
			int i = 0;
			
			@Override
			public void run() {
				if (i < solution.getSolution().size()-1)
					move(solution.getSolution().get(i).getState());
				else {
					display.syncExec(new Runnable() {

						@Override
						public void run() {
							move(solution.getSolution().get(i).getState());
						}
						
					});
					cancel();
			}
				i++;
			}
		};
		timing = new Timer();
		timing.scheduleAtFixedRate(animation, 0, 500);
	}
	
	/**
	 * Sets the hint position maze display.
	 *
	 * @param pos the new hint position maze display
	 */
	public void setHintPositionMazeDisplay(Position pos){
		this.mazeDisplay.setHint(pos);
	}
	
}
