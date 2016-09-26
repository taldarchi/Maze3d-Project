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

public class MazeDisplay extends Canvas {
	
	private Maze3d maze;
	private String mazeName;
	private Character character;
	private int[][] crossSection = {{0},{0}};
	private Image wall;
	private Image arrowup;
	private Image arrowdown;
	private Image arrowupanddown;
	private Image gold;
	private Image arrowtothegold;
	private Image winner;
	private Image hintimage;
	private Position goalPosition;
	private Position hintPosition;
	private boolean finish;
	private boolean hint;
	private Position currentPosition;
	private boolean mazeLoaded;

	public MazeDisplay(Composite parent, int style,MyView view) {
		super(parent, style);
		maze=new Maze3d();
		character = new Character();
		character.setPos(new Position(-1, -1, -1));
		wall = new Image(null,"images/wall.png");
		arrowup=new Image(null,"images/arrowup.png");
		arrowdown=new Image(null,"images/arrowdown.png");
		arrowupanddown=new Image(null,"images/arrowupanddown.png");
		gold=new Image(null,"images/gold.jpg");
		arrowtothegold=new Image(null,"images/arrowtothegold.png");
		winner=new Image(null,"images/winner.png");
		hintimage=new Image(null,"images/hint.png");
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
	
	public void setCharacterPosition(Position p) {
		this.character.setPos(p);
		redrawObject();
	}
	
	public void setCrossSection(int [][] cs){
		this.crossSection=cs;
		redrawObject();
	}
	
	public void setGoalPosition(Position p) {
		this.goalPosition=p;
	}
	
	public void setMazeName(String name) {
		this.mazeName=name;
	}
	private void redrawObject() {
		getDisplay().syncExec(new Runnable() {

			@Override
			public void run() {
				setEnabled(true);
				redraw();
			}
				
		});
	}
	public void setMaze(Maze3d maze) {
		this.maze=maze;
		
	}
	public void setCurrentPosition(Position p){
		this.currentPosition=new Position(p);

	}

	public void setMazeLoaded() {
		this.mazeLoaded = true;
	}
	
	public void setFinish() {
		this.finish = false;
	}

	public void setHint(Position hintPosition){
		this.hint = true;
		this.hintPosition = hintPosition;
		redrawObject();
	}
}

