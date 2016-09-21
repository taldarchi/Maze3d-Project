package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;

public class MazeDisplay extends Canvas {
	
	private Maze3d maze;
	private Character character;

	public MazeDisplay(Composite parent, int style) {
		super(parent, style);
		
		
		maze=new Maze3d();
		character = new Character();
		character.setPos(maze.getStartPosition());
						
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
				   

				   int width=getSize().x;
				   int height=getSize().y;

				   int w=width/maze.getMaze3d().length;
				   int h=height/maze.getMaze3d()[0].length;

				   for(int i=0;i<maze.getMaze3d().length;i++)
				      for(int j=0;j<maze.getMaze3d()[i].length;j++){
				          int x=j*w;
				          int y=i*h;
				          if(maze.getMaze3d()[0][i][j]!=0)
				              e.gc.fillRectangle(x,y,w,h);
				      }
				   
				 
				   character.draw(w, h, e.gc);
				
			}
		});
		
}
}
