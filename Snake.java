import java.util.ArrayList;

/**
 * The Snake class represents the player-controlled snake. 
 *
 * The Game class instantiates this class exactly once, when a new level is
 * loaded.
 */

public class Snake 
{
	// Private variables to hold the GraphicObject associated 
	// with the snake's head
	// and an ArrayList of GraphicObject associated with the snake's body
	private GraphicObject head;
	private ArrayList<GraphicObject> body;
	private GraphicObject grows;
	private boolean dead = false;
	/**
	 * Initializes a new Snake object at the specified (x,y) position.
	 * 
	 * TODO: Implement this.
	 * 
	 * @param x		the initial x position of the snake
	 * @param y		the initial y position of the snake
	 */
	//this constructor sets up the snake
	public Snake(float x, float y)
	{
		// Initialize new ArrayList to hold body segments
		body = new ArrayList<>();
		// Initialize the head
		head = new GraphicObject ("HEAD",x,y);
		// Set the speed of the head
		head.setSpeed(2);
		// Set the direction of the head
		head.setDirection(90);
		// Add the head to the list of body segments
		body.add(head);
		// Add four body segments (grow the snake four times)
		grow();
		grow();
		grow();
		grow();
	}

	/**
	 * Returns the GraphicObject associated with the head of this snake.
	 *  
	 * TODO: Implement this.
	 * 
	 * @return the GraphicObject associated with the head of this snake
	 */
	//prints the snake
	public GraphicObject getGraphicObject() {
		return head;
	}

	/**
	 * Grows the snake by one body segment.
	 * 
	 * TODO: Implement this.
	 */
	//allows snake the grow when eating an apple
	public void grow()
	{
		
		grows = new GraphicObject("BODY", 0, 0);
		// Create a new GraphicObject with type "BODY"		
		// Find the last body segment in the list of body segments
		// Set the leader of the new body segment to be the last body segment
		// Add the new body segment to the end of the list of body segments
		grows.setLeader(body.get(body.size()-1));
		body.add(grows);
	}

	/**
	 * Reads keyboard input and changes the snake's direction as necessary.
	 * 
	 * TODO: Implement this.
	 * 
	 * @param controlType - 1: classic, 2: analog, 3: slither
	 */
	public void updateMoveDirection(int controlType)
	{
		// if controlType is one
			// implementation for controlType one
		// if controlType is two
			// implementation for controlType two
		// if controlType is three
			// implementation for controlType three
		if(controlType == 1){
			if(Engine.isKeyPressed("LEFT"))
				head.setDirection(90+head.getDirection());
			else if(Engine.isKeyPressed("RIGHT"))
					head.setDirection(head.getDirection()-90);
		}
		if(controlType == 2){
			if(Engine.isKeyHeld("LEFT"))
				head.setDirection(6+head.getDirection());
			else if(Engine.isKeyHeld("RIGHT"))
				head.setDirection(head.getDirection()-6);
		}
		if(controlType == 3){
			if(Engine.isKeyHeld("SPACE"))
				head.setDirection(6+head.getDirection());
			else 
				head.setDirection(head.getDirection()-6);
		}
		}
	

	/**
	 * Kills the snake if the head is colliding with any of the body segments.
	 * 
	 * TODO: Implement this.
	 */
	public void dieIfCollidingWithOwnBody()
	{
		// For each game object in the body...
			// if the head is colliding with this segment...
				// tell the snake to die.
		for (int i = 0 ; i < body.size(); i++) {
		if (head.isCollidingWith(body.get(i)))
			die();
		}
	}

	/**
	 * Kills the snake.
	 * 
	 * TODO: Implement this.
	 */
	public void die()
	{
		// Set the head's type to "DEAD"
		// For each GraphicObject in the snake's body, set its type to "DEAD"
		head.setType("DEAD");
		for (int i = 0 ; i < body.size() ; i++) {
			body.get(i).setType("DEAD");
		}
		dead = true;
	}

	/**
	 * Returns true if the snake is dead.
	 * 
	 * TODO: Implement this.
	 * 
	 * @return true if the snake is dead, false otherwise
	 */
	public boolean isDead() {
		return dead;
	}
}