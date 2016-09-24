package view;

import java.util.Arrays;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
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
		wall = new Image(null,"images/wall.jpg");
		arrowup=new Image(null,"images/arrowup.png");
		arrowdown=new Image(null,"images/arrowdown.png");
		arrowupanddown=new Image(null,"images/arrowupanddown.png");
		gold=new Image(null,"images/gold.jpg");
		arrowtothegold=new Image(null,"images/arrowtothegold.png");
		finish=false;
						
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
						break;
					
					case SWT.ARROW_LEFT:	
						command="left "+mazeName;
						break;
						
					case SWT.ARROW_UP:			
						command="backwards "+mazeName;
						break;
						
					case SWT.ARROW_DOWN:			
						command="forward "+mazeName;
						break;
						
					case SWT.PAGE_UP:		
						command="up "+mazeName;
						break;
						
					case SWT.PAGE_DOWN:		
						command="down "+mazeName;
						break;
						
					default: 
						break;
					}
				view.executeCommand(command);
				redraw();
				}
			}
		});
		
		this.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				e.gc.setForeground(new Color(null,200,100,0));
				e.gc.setBackground(new Color(null,0,0,0));
				   
				int x,y;
				   
				int width=getSize().x;
				int height=getSize().y;
				int cellWidth=width / crossSection[0].length;
				int cellHeight= height / crossSection.length;
				   
				for (int i = 0; i < crossSection.length; i++) {
					for (int j = 0; j < crossSection[i].length; j++) {
						x = j * cellWidth;
						y = i * cellHeight;
						if (crossSection[i][j] != 0)
							e.gc.drawImage(wall, 0, 0, wall.getBounds().width, wall.getBounds().height, x, y, cellWidth, cellHeight);
						if(mazeLoaded){
							if(maze.getGoalPosition().equals(new Position(currentPosition.getZ(),i,j)))
								e.gc.drawImage(gold, 0, 0, gold.getBounds().width, gold.getBounds().height, x, y, cellWidth, cellHeight);
							Position temp=new Position(maze.getGoalPosition().getZ()-2,maze.getGoalPosition().getX(),maze.getGoalPosition().getY());				
							if(temp.equals(new Position(currentPosition.getZ(),i,j)))
								e.gc.drawImage(arrowtothegold, 0, 0, arrowtothegold.getBounds().width, arrowtothegold.getBounds().height, x, y, cellWidth, cellHeight);
							else if(Arrays.asList(maze.getPossibleMoves(new Position(currentPosition.getZ(),i,j))).contains("Up") && Arrays.asList(maze.getPossibleMoves(new Position(currentPosition.getZ(),i,j))).contains("Down"))
								e.gc.drawImage(arrowupanddown, 0, 0, arrowupanddown.getBounds().width, arrowupanddown.getBounds().height, x, y, cellWidth, cellHeight);
							else if(Arrays.asList(maze.getPossibleMoves(new Position(currentPosition.getZ(),i,j))).contains("Up") && !Arrays.asList(maze.getPossibleMoves(new Position(currentPosition.getZ(),i,j))).contains("Down"))
								e.gc.drawImage(arrowup, 0, 0, arrowup.getBounds().width, arrowup.getBounds().height, x, y, cellWidth, cellHeight);
							else if(!Arrays.asList(maze.getPossibleMoves(new Position(currentPosition.getZ(),i,j))).contains("Up") && Arrays.asList(maze.getPossibleMoves(new Position(currentPosition.getZ(),i,j))).contains("Down")&&!maze.getGoalPosition().equals(new Position(currentPosition.getZ(),i,j)))
								e.gc.drawImage(arrowdown, 0, 0, arrowdown.getBounds().width, arrowdown.getBounds().height, x, y, cellWidth, cellHeight);
						}
					}
				}
				character.draw(cellWidth, cellHeight, e.gc);
			}
		});
	}
	public void setCharacterPosition(Position p) {
		this.character.setPos(p);
		if(p.equals(maze.getGoalPosition()))
			finish=true;
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

//	public void drawHint(Position hintPosition){
//		this.hint = true;
//		this.hintPosition = hintPosition;
//		redrawObject();
//	}
}

