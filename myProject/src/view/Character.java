package view;


import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

import algorithms.mazeGenerators.Position;

public class Character {
	private Position pos;
	private Image img;
	
	public Character() {
		img = new Image(null, "images/character.png");
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}
	
	public void draw(int x, int y, GC gc) {
		gc.drawImage(img, 0, 0, img.getBounds().width, img.getBounds().height, x * pos.getX(), y * pos.getZ(), x, y);
	}
	
	public void moveUp() {
		pos.setZ(pos.getZ()+1);
	}
	public void moveDown() {
		pos.setZ(pos.getZ()-1);	
	}
	public void moveForward() {
		pos.setX(pos.getX()+1);	
	}
	public void moveBackwards() {
		pos.setX(pos.getX()-1);	
	}
	public void moveRight() {
		pos.setY(pos.getY()+1);	
	}
	public void moveLeft() {
		pos.setY(pos.getY()-1);
	}
	

}
