/**
 * The Rock class represents a rock in the game.
 * 
 * The Game class instantiates this class once for each rock present when a new
 * level is loaded.
 */
public class Rock 
{
	// Create private field to hold the GraphicObject associated with this rock
	private GraphicObject stone;
	
	/**
	 * TODO: Implement this.
	 * 
	 * @param x		the x position of the rock
	 * @param y		the y position of the rock
	 */
	//this constructor sets the rock
	public Rock(float x, float y)
	{
		// Initialize this rock's associated GraphicObject with type "ROCK" at 
		// this rock's x and y coordinates
		stone = new GraphicObject ("ROCK",x,y);
	}
	
	/**
	 * Checks if this rock is colliding with the specified snake.
	 * 
	 * If the GraphicObject associated with this rock is colliding with the head 
	 * of the GraphicObject associated with the head of the snake, kills the 
	 * snake.
	 * 
	 * TODO: Implement this.
	 * 
	 * @param snake		snake to check for collisions with
	 */
	//this method kills snake if colliding with rock
	public void killSnakeIfColliding(Snake snake)
	{
		// If this rock is colliding with the snake's head's GraphicObject, kill
		// the snake
		
		if (snake.getGraphicObject().isCollidingWith(stone)) {
			snake.die();
		}
	}
}
