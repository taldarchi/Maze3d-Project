package view;

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
	private Position goalPosition;
	private Position hintPosition;
	private boolean finish;
	private boolean hint;

	public MazeDisplay(Composite parent, int style,MyView view) {
		super(parent, style);
		maze=new Maze3d();
		character = new Character();
		character.setPos(new Position(-1, -1, -1));
		wall = new Image(null,"images/wall.jpg");
		finish=false;
						
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
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
					}
				}
				
				character.draw(cellWidth, cellHeight, e.gc);
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
	

//	public void drawHint(Position hintPosition){
//		this.hint = true;
//		this.hintPosition = hintPosition;
//		redrawObject();
//	}
}

