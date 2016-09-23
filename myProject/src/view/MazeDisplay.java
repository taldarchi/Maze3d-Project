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

public class MazeDisplay extends Canvas {
	
	private Maze3d maze;
	private Character character;
	private int[][] crossSection = { {0}, {0} };
	private Image wall;

	public MazeDisplay(Composite parent, int style) {
		super(parent, style);
		maze=new Maze3d();
		character = new Character();
		character.setPos(maze.getStartPosition());
		wall = new Image(null,"images/wall.jpg");
						
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.keyCode) {
				case SWT.ARROW_RIGHT:					
					character.moveRight();
					redraw();
					break;
				
				case SWT.ARROW_LEFT:					
					character.moveLeft();
					redraw();
					break;
					
				case SWT.ARROW_UP:					
					character.moveForward();
					redraw();
					break;
					
				case SWT.ARROW_DOWN:					
					character.moveBackwards();
					redraw();
					break;
					
				case SWT.PAGE_UP:					
					character.moveUp();
					redraw();
					break;
				case SWT.PAGE_DOWN:					
					character.moveDown();
					redraw();
					break;
				}
			}
		});
		
		this.addPaintListener(new PaintListener() {
			
			@Override
			public void paintControl(PaintEvent e) {
				e.gc.setForeground(new Color(null,0,0,0));
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


				   //character.draw(w, h, e.gc);
				
			}
		});
		
}
}
