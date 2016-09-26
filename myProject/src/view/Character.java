package view;


import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;

import algorithms.mazeGenerators.Position;

/**
 * The Class Character.
 */
public class Character {
	
	/** The pos. */
	private Position pos;
	
	/** The img. */
	private Image img;
	
	/**
	 * Instantiates a new character.
	 */
	public Character() {
		img = new Image(null, "resources/images/character.png");
	}

	/**
	 * Gets the pos.
	 *
	 * @return the pos
	 */
	public Position getPos() {
		return pos;
	}

	/**
	 * Sets the pos.
	 *
	 * @param pos the new pos
	 */
	public void setPos(Position pos) {
		this.pos = pos;
	}
	
	/**
	 * Draw.
	 *
	 * @param x the x
	 * @param y the y
	 * @param gc the gc
	 */
	public void draw(int x, int y, GC gc) {
		gc.drawImage(img, 0, 0, img.getBounds().width, img.getBounds().height, x * pos.getY(), y * pos.getX(), x, y);
	}

	/**
	 * Gets the img.
	 *
	 * @return the img
	 */
	public Image getImg() {
		return img;
	}

}
