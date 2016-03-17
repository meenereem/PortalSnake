/**
 * The Apple class represents an apple in the game.
 * 
 * The Game class instantiates this class once for each apple present when a new
 * level is loaded.
 */
public class Apple 
{
	// Create private field to hold the GraphicObject associated with this apple
	private GraphicObject apples;
	/**
	 * Initializes a new Apple object.
	 * 
	 * TODO: Implement this.
	 * 
	 * @param x		the x position of the apple
	 * @param y		the y position of the apple
	 */
	//this constructor sets the apple
	public Apple(float x, float y)
	{
		// Initialize this apple's associated GraphicObject with type "APPLE" at
		// this apple's x and y coordinates
		apples = new GraphicObject ("APPLE",x,y);
		
	}
			
	/**
	 * Checks if this apple is colliding with the specified snake.
	 * 
	 * If the GraphicObject associated with this apple is colliding with the
	 * GraphicObject associated with the specified snake's head, grows the snake
	 * destroys the GraphicObject associated with this apple (causing it to
	 * disappear from the screen), and returns true. Otherwise, returns false.
	 * 
	 * TODO: Implement this.
	 * 
	 * @param snake		snake to check for collisions with
	 * @return true after eating an apple, otherwise false
	 */
	//gives apple functionality so that snake grows if it eats an apple
	public boolean getEatenBySnakeIfColliding(Snake snake)
	{
		// If this apple is colliding with the snake's head's GraphicObject,
		// grow the snake once and destroy this apple's GraphicObject, then
		// return true
		
		if (snake.getGraphicObject().isCollidingWith(apples)) {
			snake.grow();
			apples.destroy();
			return true;
		}
		// Otherwise, return false
		return false;
	}	
}