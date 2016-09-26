package view;

import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;

/**
 * The Class MazeDisplay.
 */
public class MazeDisplay extends Canvas {
	
	/** The maze. */
	private Maze3d maze;
	
	/** The maze name. */
	private String mazeName;
	
	/** The character. */
	private Character character;
	
	/** The cross section. */
	private int[][] crossSection = {{0},{0}};
	
	/** The wall. */
	private Image wall;
	
	/** The arrowup. */
	private Image arrowup;
	
	/** The arrowdown. */
	private Image arrowdown;
	
	/** The arrowupanddown. */
	private Image arrowupanddown;
	
	/** The gold. */
	private Image gold;
	
	/** The arrowtothegold. */
	private Image arrowtothegold;
	
	/** The winner. */
	private Image winner;
	
	/** The hintimage. */
	private Image hintimage;
	
	/** The hint position. */
	private Position hintPosition;
	
	/** The finish. */
	private boolean finish;
	
	/** The hint. */
	private boolean hint;
	
	/** The current position. */
	private Position currentPosition;
	
	/** The maze loaded. */
	private boolean mazeLoaded;

	/**
	 * Instantiates a new maze display.
	 *
	 * @param parent the parent
	 * @param style the style
	 * @param view the view
	 */
	public MazeDisplay(Composite parent, int style,MyView view) {
		super(parent, style);
		maze=new Maze3d();
		character = new Character();
		character.setPos(new Position(-1, -1, -1));
		wall = new Image(null,"resources/images/wall.png");
		arrowup=new Image(null,"resources/images/arrowup.png");
		arrowdown=new Image(null,"resources/images/arrowdown.png");
		arrowupanddown=new Image(null,"resources/images/arrowupanddown.png");
		gold=new Image(null,"resources/images/gold.jpg");
		arrowtothegold=new Image(null,"resources/images/arrowtothegold.png");
		winner=new Image(null,"resources/images/winner.png");
		hintimage=new Image(null,"resources/images/hint.png");
		finish=false;
		hint=false;
						
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {	
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				String command = null;
				if(mazeName != null){
					switch (e.keyCode) {
					case SWT.ARROW_RIGHT:
						command="right "+mazeName;
						view.executeCommand(command);
						break;
					
					case SWT.ARROW_LEFT:	
						command="left "+mazeName;
						view.executeCommand(command);
						break;
						
					case SWT.ARROW_UP:			
						command="backwards "+mazeName;
						view.executeCommand(command);
						break;
						
					case SWT.ARROW_DOWN:			
						command="forward "+mazeName;
						view.executeCommand(command);
						break;
						
					case SWT.PAGE_UP:		
						command="up "+mazeName;
						view.executeCommand(command);
						break;
						
					case SWT.PAGE_DOWN:		
						command="down "+mazeName;
						view.executeCommand(command);
						break;
						
					default: 
						break;
					}
				redraw();
				}
			}
		});
		
		//control+mousewheel listener bonus
		this.addMouseWheelListener(new MouseWheelListener() {

		    @Override
		    public void mouseScrolled(MouseEvent g) {
		        if((g.stateMask & SWT.CONTROL) == SWT.CONTROL) {
		        	if (g.count > 0)
		        		setSize(getSize().x+50, getSize().y+50);
		        	if (g.count < 0)
		        		setSize(getSize().x-50, getSize().y-50);
		        }
				redraw();
		    }
		});
		
		this.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
					int x,y;
					int width=getSize().x;
					int height=getSize().y;
					int cellWidth=width / crossSection[0].length;
					int cellHeight= height / crossSection.length;
					
				if(!finish){
					for (int i = 0; i < crossSection.length; i++) {
						for (int j = 0; j < crossSection[i].length; j++) {
							x = j * cellWidth;
							y = i * cellHeight;
							if (crossSection[i][j] != 0)
								e.gc.drawImage(wall, 0, 0, wall.getBounds().width, wall.getBounds().height, x, y, cellWidth, cellHeight);
							else if(mazeLoaded){
								Position current=new Position(currentPosition.getZ(),i,j);
								if(maze.getGoalPosition().equals(current)){
									e.gc.drawImage(gold, 0, 0, gold.getBounds().width, gold.getBounds().height, x, y, cellWidth, cellHeight);
									finish=true;
								}
								Position temp=new Position(maze.getGoalPosition().getZ()-2,maze.getGoalPosition().getX(),maze.getGoalPosition().getY());	
								if(hint && hintPosition.equals(current)){
									e.gc.drawImage(hintimage, 0, 0, hintimage.getBounds().width, hintimage.getBounds().height, x, y, cellWidth, cellHeight);
									hint=false;
								}
								if(temp.equals(current))
									e.gc.drawImage(arrowtothegold, 0, 0, arrowtothegold.getBounds().width, arrowtothegold.getBounds().height, x, y, cellWidth, cellHeight);
								else if(Arrays.asList(maze.getPossibleMoves(current)).contains("Up") && Arrays.asList(maze.getPossibleMoves(current)).contains("Down"))
									e.gc.drawImage(arrowupanddown, 0, 0, arrowupanddown.getBounds().width, arrowupanddown.getBounds().height, x, y, cellWidth, cellHeight);
								else if(Arrays.asList(maze.getPossibleMoves(current)).contains("Up") && !Arrays.asList(maze.getPossibleMoves(current)).contains("Down"))
									e.gc.drawImage(arrowup, 0, 0, arrowup.getBounds().width, arrowup.getBounds().height, x, y, cellWidth, cellHeight);
								else if(!Arrays.asList(maze.getPossibleMoves(current)).contains("Up") && Arrays.asList(maze.getPossibleMoves(current)).contains("Down")&&!maze.getGoalPosition().equals(current))
									e.gc.drawImage(arrowdown, 0, 0, arrowdown.getBounds().width, arrowdown.getBounds().height, x, y, cellWidth, cellHeight);
							}
						}
					}
						character.draw(cellWidth, cellHeight, e.gc);
				}
				else{
					e.gc.drawImage(winner, 0, 0, winner.getBounds().width, winner.getBounds().height,1, 1, 1250, 584);
				}
			}
		});
	}
	
	/**
	 * Sets the character position.
	 *
	 * @param p the new character position
	 */
	public void setCharacterPosition(Position p) {
		this.character.setPos(p);
		redrawObject();
	}
	
	/**
	 * Sets the cross section.
	 *
	 * @param cs the new cross section
	 */
	public void setCrossSection(int [][] cs){
		this.crossSection=cs;
		redrawObject();
	}

	/**
	 * Sets the maze name.
	 *
	 * @param name the new maze name
	 */
	public void setMazeName(String name) {
		this.mazeName=name;
	}
	
	/**
	 * Redraw object.
	 */
	private void redrawObject() {
		getDisplay().syncExec(new Runnable() {

			@Override
			public void run() {
				setEnabled(true);
				redraw();
			}
				
		});
	}
	
	/**
	 * Sets the maze.
	 *
	 * @param maze the new maze
	 */
	public void setMaze(Maze3d maze) {
		this.maze=maze;
		
	}
	
	/**
	 * Sets the current position.
	 *
	 * @param p the new current position
	 */
	public void setCurrentPosition(Position p){
		this.currentPosition=new Position(p);

	}

	/**
	 * Sets the maze loaded.
	 */
	public void setMazeLoaded() {
		this.mazeLoaded = true;
	}
	
	/**
	 * Sets the finish.
	 */
	public void setFinish() {
		this.finish = false;
	}

	/**
	 * Sets the hint.
	 *
	 * @param hintPosition the new hint
	 */
	public void setHint(Position hintPosition){
		this.hint = true;
		this.hintPosition = hintPosition;
		redrawObject();
	}
}

