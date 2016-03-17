/**
 * The PortalPair class represents a pair of portals.
 * 
 * The Game class instantiates this class once for each pair of portals present
 * when a new level is loaded.
 */
public class PortalPair 
{
	// Create private field to hold the GraphicObject 
	//associated with the blue portal
	// Create private field to hold the GraphicObject 
	//associated with the orange portal
	private GraphicObject blueP;
	private GraphicObject orangeP;
	
	/**
	 * TODO: Implement this
	 * 
	 * @param name		name displayed on each end of the portal pair
	 * @param blueX		the x position of the blue portal
	 * @param blueY		the y position of the blue portal
	 * @param orangeX	the x position of the orange portal
	 * @param orangeY	the y position of the orange portal
	 */
	//sets up the portals
	public PortalPair(String name, float blueX, float blueY, 
			                       float orangeX, float orangeY)
	{
		// Initialize the GraphicObjects associated with the blue and orange
		// portals, setting the type to "BLUE_PORTAL_name" or
		// "ORANGE_PORTAL_name", respectively, and setting their x and y
		// coordinates appropriately
		blueP = new GraphicObject ("BLUE_PORTAL_"+name,blueX,blueY);
		orangeP = new GraphicObject ("ORANGE_PORTAL_"+name,orangeX,orangeY);
	}
		
	/**
	 * Checks if either end of this portal pair is colliding with the specified
	 * snake.
	 * 
	 * If either end of this portal pair is colliding with the snake, moves the
	 * snake past the other end of the portal.
	 * 
	 * TODO: Implement this.
	 * 
	 * @param snake		the snake to check for collisions with
	 */
	//gives portals functionality by teleporting snake if it enters a portal to 
	//another portal with game name
	public void teleportSnakeIfColliding(Snake snake)
	{
		// If the blue portal is colliding with the snake's head's GraphicObject
		// move the snake's head's GraphicObject past the orange portal
		if (snake.getGraphicObject().isCollidingWith(blueP)) {
			snake.getGraphicObject().movePast(orangeP);
		}
		// If the orange portal is colliding with the snake's head's 
		// GraphicObject, move the snake's head's GraphicObject past the 
		// blue portal
		else if (snake.getGraphicObject().isCollidingWith(orangeP)) {
			snake.getGraphicObject().movePast(blueP);
		}
	}
}