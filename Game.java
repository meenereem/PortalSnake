import java.util.*;

/**
 * The Game class represents a running instance of the PortalSnake game. It
 * keeps track of the Snake object, lists of Apple, Rock, and PortalPair
 * objects, the current score, and whether the player has won.
 * 
 * The game engine (which we've written for you) will create a new instance of
 * this class when the player chooses a level to play. 
 * 
 * At each iteration of the game loop, the game engine calls the update() method
 * in the Game class. The update() method tells each of the objects in the game
 * to update itself based on the rules of the game. It then checks if the game
 * is over or not.
 */
public class Game 
{	
	// Store the instances of the game objects that you create for your game
	// in these member variables: 
	// These instances, Array lists, and variables are used to implement the 
	// other classes
	private Snake snake;
	private ArrayList<Apple> apples;		
	private ArrayList<Rock> rocks;			
	private ArrayList<PortalPair> portals;	
	private Random rng = new Random();
	private int controlType;
	private int score = 0;
	// Create member variables to track the controlType, score (ie number
	// of apples eaten by the snake), and whether the game has been won
	// or lost here.
	
	/**
	 * 
	 * TODO: Have students implement this
	 * 
	 * @param level - "RANDOM" or descriptions of the object to load
	 * @param controlType - 1: classic, 2: analog, 3: slither
	 */
	// this constructor is used to control the game
	public Game(String level, int controlType) {
		
		// Initialize all member variables
		rocks = new ArrayList<Rock>();
		apples = new ArrayList<Apple>();
		portals = new ArrayList<PortalPair>();
		this.controlType = controlType;
		
		// Create a random level when level contains: RANDOM
		// Otherwise load the objects described in the string level
		if (level.contains("RANDOM"))
			createRandomLevel();
		else
			loadLevel(level);
	}

	//creates random level by printing out the rocks, apples, and portals in 
	//random locations 
	public void createRandomLevel()
	{
		Rock rock;
		Apple apple;
		PortalPair portal;
		// create a snake: positioned as specified in the write-up
		snake = new Snake(Engine.getWidth()/2,Engine.getHeight()/2);
		// create 20 randomly positioned rocks
		// create 8 randomly positioned apples
		// create 3 randomly positioned portal pairs
		for(int i = 0; i<20; i++){
		    rock = new Rock(rng.nextFloat()*Engine.getWidth(),
		    		rng.nextFloat()*Engine.getHeight());
			rocks.add(rock);
		}
		for(int i = 0; i<8; i++){
			apple = new Apple(rng.nextFloat()*Engine.getWidth(),
					rng.nextFloat()*Engine.getHeight());
			apples.add(apple);
		}
		for (int i = 0; i<3; i++){
			int portal_name = i+'A';
			char a = (char)portal_name; 
			portal = new PortalPair(Character.toString(a),
					rng.nextFloat()*Engine.getWidth(),
					rng.nextFloat()*Engine.getHeight(),
					rng.nextFloat()*Engine.getWidth(),
					rng.nextFloat()*Engine.getHeight());
			portals.add(portal);
		}
		
	}
	

	/**
	 * Loads a level from a String description.
	 * 
	 * Initializes all of the class private fields which hold the Snake object
	 * and the lists of Apple, Rock, and PortalPair objects from the provided
	 * String which contains  
	 * 
	 * TODO: Implement this method
	 * 
	 * @param level - a string containing the names and locations of objects
	 */
	//this method loads the level
	public void loadLevel(String level)
	{
		// Initialize Rock, Apple, and PortalPair ArrayLists
		 apples = new ArrayList<>();
		 rocks = new ArrayList<>();
		 portals = new ArrayList<>();
		
		// Create a new scanner to read the level description	
		 Scanner loadLevel = new Scanner(level);
		 
		// Loop through lines in the level description
		 while (loadLevel.hasNextLine()){
			 
		 
			// Get the next line
			 String x = loadLevel.nextLine();
			// Split the line into tokens
			 String [] strs = x.split(",");
			// Determine the type of object to add to the level
				// If it's a snake, create a new snake at the x 5					 
			 
			 	if (strs[0].equals("Snake"))
			 		snake = new Snake(Float.parseFloat(strs[1]),
			 				Float.parseFloat(strs[2]));
			
			 	// If it's an apple, create a new apple at the x and y
				// coordinates specified by the second and third tokens, and add
				// it to the list of apples
				 if (strs[0].equals("Apple")){
					 Apple apple;
					 apple = new Apple(Float.parseFloat(strs[1]), 
							 Float.parseFloat(strs[2]));
					 apples.add(apple);
				 }
				// If it's a rock, create a new rock at the x and y coordinates
				// specified by the second and third tokens and add it to the
				// list of rocks
				 if (strs[0].equals("Rock")){
					 Rock rock;
					 rock = new Rock(Float.parseFloat(strs[1]),
							 Float.parseFloat(strs[2]));
					 rocks.add(rock);
				 }	
				// If it's a portal pair, create a new PortalPair with the
				// name equal to the second token, with the first portal at the
				// x and y coordinates specified by the third and fourth
				// tokens, and the second portal at the x and y coordinates
				// specified by the fifth and sixth tokens
				 if (strs[0].equals("PortalPair")){
					 PortalPair portal;
					 portal = new PortalPair(strs[1], Float.parseFloat(strs[2]), 
							 Float.parseFloat(strs[3]),
							 Float.parseFloat(strs[4]), 
							 Float.parseFloat(strs[5]));
					 portals.add(portal);
				 }
		 }
		// Close the scanner		
	}

	/**
	 * Updates the game objects.
	 * 
	 * Goes through each of the objects--the snake, rocks, apples, and portals--
	 * and tells them to behave according to the rules of the game. This method
	 * returns true if the game should continue, or false if the game is over.
	 * 
	 * TODO: Implement this
	 * 
	 * @return - false if the game is over, otherwise true
	 */
	//updates the score and length of the snake
	//ends the game if the snake dies from either eating itself or
	//colliding in to a rock
	public boolean update()
	{
		// Tell the snake to update itself
		snake.updateMoveDirection(controlType);
		// Tell the snake to die if it's colliding with itself
		snake.dieIfCollidingWithOwnBody();
		
		// For each rock, tell the rock to kill the snake if the two are
		// colliding
		//System.out.println(snake.getGraphicObject().getX());
		//System.out.println(snake.getGraphicObject().getY());
		for (int i = 0; i < rocks.size(); i++) {
			rocks.get(i).killSnakeIfColliding(snake);
		}
		if (snake.isDead())
			return false;
		// For each apple, tell the apple to be eaten by the snake if the two
		// are colliding, and if so update the score
		for (int i = 0; i < apples.size(); i++) {
			if ( apples.get(i).getEatenBySnakeIfColliding(snake) ) { 
				score++;
				apples.remove(i);
				return true;
			}
		}
		// For each portal pair, tell the pair to teleport the snake if the two
		// are colliding
		for (int i = 0; i < portals.size(); i++) {
			portals.get(i).teleportSnakeIfColliding(snake);
		}
		// Check for win/loss
			// If the score is equal to the number of apples,
		//make sure playerHasWon()
			// will return true and then return false 
		if (apples.size() == 0) {
			//snake.getGraphicObject().setType("DEAD");
			return false;
		}
			// Else, if the snake is dead,
		//make sure playerHasWon() will return false
			// and then return false
		
		// If the game isn't over, return true
		return true;
	}

	/**
	 *  Returns true if the player has won
	 * 
	 * TODO: Implement this
	 * 
	 * @return true when the player has won, and false when they have lost or
	 * the game is not over
	 */
	//if snake is dead then player has not won but if snake is alive then
	//player has won
	public boolean playerHasWon()
	{	
		if (snake.isDead())
		return false;
		else
			return true;
	}
	
	/**
	 * Returns the player's score.
	 * 
	 * TODO: Implement this.
	 * 
	 * @return the current score (number of apples eaten)
	 */
	//returns the score of the player
	public int getScore() {
		return score;
	}	

	/**
	 * There is nothing left to implement in this method, it simply calls
	 * Engine.startEngineAndGame(), which in turn starts the Engine and creates
	 * an instance of this Game class.  The engine will then repeatedly call
	 * the update() method on this Game until it returns false.
	 * 
	 * If you want to turn off the logging you can change the parameter being
	 * passed to startEngineAndGame to false.  
	 *  
	 * @param args - command line arguments
	 */
	//starts the game
	public static void main(String[] args)
	{
		Application.startEngineAndGame(true);
	}
}