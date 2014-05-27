package apcs.shoppingMaul;

import apcs.katechon.basicGameObjects.ControlScheme;
import apcs.katechon.rendering.Layer;

/**
 * This is it. The game board. It keeps the player in the middle and when you move up and down it scrolls the view around to keep your character in the middle.
 * @author Matt
 *
 */
public class Board extends LeopardPack
{
	public Board(ControlScheme controlScheme, int speed, int amountOfLeopards, Layer layer, int width, int height)
	{
		super(controlScheme, width / 2, height / 2, speed, amountOfLeopards);
	}
}
