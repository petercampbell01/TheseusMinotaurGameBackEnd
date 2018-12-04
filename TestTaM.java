package petercampbell;


import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Test;

public class TestTaM {
	
	// Run tests on Position class to prove everything works correctly.
	@Test
	public void testPositionInitialisation() {
		Position vt = new Position(5,10);
		boolean expected = true;
		boolean actual = vt.x == 5;
		String errorMessage = "Expected: " + expected + " but got " + actual;

		assertEquals(errorMessage, expected, actual);
		
	}
	
	@Test
	public void testChangePositionMethodFalse() {
		Position vt = new Position(5,10);
		vt.changePosition(10, 10);
		boolean expected = false;
		boolean actual = vt.x == 5;
		String errorMessage = "Expected: " + expected + " but got " + actual;

		assertEquals(errorMessage, expected, actual);
		
	}
	@Test
	public void testChangePositionMethodTrue() {
		Position vt = new Position(5,10);
		vt.changePosition(10, 10);
		boolean expected = true;
		boolean actual = vt.x == 15;
		String errorMessage = "Expected: " + expected + " but got " + actual;

		assertEquals(errorMessage, expected, actual);
		
	}
	@Test
	public void testSetPositionMethodTrue() {
		Position vt = new Position(5,10);
		vt.setPosition(10, 10);
		boolean expected = true;
		boolean actual = vt.x == 10;
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
		
	}
	
	
	@Test
	public void testTheseusInitialisation() {
		GameCharacter theseus = new GameCharacter (new Position(10,10), new Game());
		String expected = "X: 10 Y: 10";
		String actual =  theseus.location.toString();
		String errorMessage = "Expected: " + expected + " but got " + actual;

		assertEquals(errorMessage, expected, actual);
	}
		
	@Test
	public void testTheseusMoveLEFT() {
		GameCharacter theseus = new GameCharacter (new Position(10,10), new Game());
		theseus.moveLEFT();
		String expected = "X: 9 Y: 10";
		String actual =  theseus.location.toString();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	}

	@Test
	public void testTheseusMoveRIGHT() {
		GameCharacter theseus = new GameCharacter (new Position(10,10), new Game());
		theseus.moveRIGHT();
		String expected = "X: 11 Y: 10";
		String actual =  theseus.location.toString();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	}
	
	@Test
	public void testTheseusMovementIsNOTBlocked() {
		GameCharacter theseus = new GameCharacter (new Position(10,10), new Game());
		boolean expected = false;
		boolean actual = theseus.isBlocked(Direction.LEFT);
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	}

	@Test
	public void testTheseusMoveUPIsNOTBlocked() {
		GameCharacter theseus = new GameCharacter (new Position(10,10), new Game());
		boolean expected = false;
		boolean actual = theseus.isBlocked(Direction.UP);
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	}
	
	@Test
	public void testTheseusMoveUP() {
		GameCharacter theseus = new GameCharacter (new Position(10,10), new Game());
		boolean expected = true;
		boolean actual = theseus.moveUP();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	}

	@Test
	public void testTheseusMoveDOWN() {
		GameCharacter theseus = new GameCharacter (new Position(10,10), new Game());
		boolean expected = true;
		boolean actual = theseus.moveDOWN();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	}

	@Test
	public void testMinotaurInitialisation() {
		Position pos = new Position(5,5);
		Minotaur minotaur = new Minotaur (pos, new Game());
		String expected = pos.toString();
		String actual = minotaur.getLocation().toString();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	}
	
	@Test
	public void testMinotaurNoOfMoves() {
		Minotaur minotaur = new Minotaur (new Position(5,5), new Game());
		int expected = 2;
		int actual = minotaur.noOfMoves;
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	}
	
	@Test
	public void testMinotaurFindTheseusMethod() {
		GameCharacter theseus = new GameCharacter (new Position(10,10), new Game());
		Minotaur minotaur = new Minotaur (new Position(5,5), new Game());
		Direction [] arr = minotaur.findTheseus();
		int expected = 2;
		int actual = arr.length;
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	}
	
	//Tests to verify that the algorithm for determining Minotaur direction are correct
	@Test
	public void testMinotaurFindTheseusAlgorithm01() {
		Game game = new Game();
		game.theseus.location = new Position(10,10);
		game.minotaur.location = new Position(5,5);
		Direction [] arr = game.minotaur.findTheseus();
		Direction expected = Direction.RIGHT;
		Direction actual = arr[0];
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	}
	
	@Test
	public void testMinotaurFindTheseusAlgorithm02() {
		Game game = new Game();
		game.theseus.location = new Position(10,10);
		game.minotaur.location = new Position(5,5);
		Direction [] arr = game.minotaur.findTheseus();
		Direction expected = Direction.DOWN;
		Direction actual = arr[1];
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	}

	// Test to verify minotaur moves in direction of Theseus and takes two moves
	@Test
	public void testMinotaurMovement() {
		Game game = new Game();
		game.theseus.location = new Position(10,10);
		game.minotaur.location = new Position(5,5);
		game.minotaur.moves();
		String expected = "X: 7 Y: 5";
		String actual =  game.minotaur.location.toString();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	}
	
	//Run tests on Game class to verify all works
	//Constructs game correctly
	@Test
	public void testGameInitialisation() {
		Game game = new Game();
		int expected = 0;
		int actual = game.currentLevel;
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	}

	
	//find exit
	@Test
	public void testLevelLoadingWithExitPosition() {
		Game game = new Game();
		game.loadLevel(0);
		String expected = "X: 6 Y: 3";
		String actual = game.exitLocation.toString();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	}
	
	//find theseus
	@Test
	public void testTheseusPlacement() {
		Game game = new Game();
		game.loadLevel(0);
		String expected = "X: 2 Y: 5";
		String actual = game.theseus.location.toString();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	}
	
	//find minotaur
	@Test
	public void testMinotaurSetupAndPlaced() {
		Game game = new Game();
		game.loadLevel(0);
		String expected = "X: 2 Y: 1";
		String actual = game.minotaur.location.toString();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	}

	//Test find minotaur from calling only the constructor
	@Test
	public void testConstructorInitialisation() {
		Game game = new Game();
		String expected = "X: 2 Y: 1";
		String actual = game.minotaur.location.toString();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	}

	//Test to see if Theseus will move into a wall
	@Test
	public void testTheseusMovementWhenBlocked() {
		Game game = new Game();
		boolean expected = false;
		boolean actual = game.theseus.moveUP();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	}
	
	//Test to check that walls have been setup
	@Test
	public void testWallSetupArrayList() {
		Game game = new Game();
		ArrayList<Position> walls = game.allMyWalls;
		int expected = 26;
		int actual = walls.size();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	}
	//Test that theseus can see blocked routes
	@Test
	public void testTheseusSeesWalls() {
		Game game = new Game();
		boolean expected = game.theseus.isBlocked(Direction.DOWN);
		boolean actual = true;
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	}
	//Test that GameCharacter positions can be set in map
	@Test
	public void testTheseusSetLocationMethod() {
		Game game = new Game();
		String expected = "X: 3 Y: 2";
		game.theseus.setLocation(new Position(3,2));
		String actual = game.theseus.location.toString();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
		
	}
	@Test
	public void testMinotaurSetLocationOverloading() {
		Game game = new Game();
		String expected = "X: 3 Y: 2";
		game.minotaur.setLocation(3,2);
		Position mpos = game.minotaur.location;
		String actual = mpos.toString();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
		
	}
	//Verify hasLost()
	@Test
	public void testGameHasLostMethodTrue() {
		Game game = new Game();
		boolean expected = true;
		game.minotaur.setLocation(3,2);
		game.theseus.setLocation(3,2);
		boolean actual = game.hasLost();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
		
	}
	@Test
	public void testGameHasLostFalse() {
		Game game = new Game();
		boolean expected = false;
		boolean actual = game.hasLost();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
		
	}
	
	//verify hasWon()
	@Test
	public void testGameHasWonTrue() {
		Game game = new Game();
		boolean expected = true;
		game.theseus.setLocation(game.exitLocation);
		boolean actual = game.hasWon();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
		
	}

	@Test
	public void testGameHasWonFalse() {
		Game game = new Game();
		boolean expected = false;
		boolean actual = game.hasWon();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
		
	}

	
	//Verify minotaur movement
/*  "xxxxxx",
 *  "xomoox", 
 *  "xoxxox", 
 *  "xooxooe", 
 *  "xoxxox", 
 *  "xotoox", 
 *  "xxxxxx"
*/	
	@Test
	public void testGameMinotaurMovement() {
		Game game = new Game();
		game.theseus.setLocation(new Position(2,1));
		Position minPos = game.minotaur.moves(); 
		String expected = "X: 2 Y: 1";
		String actual = minPos.toString();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
		
	}
	
	//Test minotaur movement with hasWon
	@Test
	public void testGameMinotaurMovementWithHasWonFalse() {
		Game game = new Game();
		game.theseus.setLocation(2,1);
		Position minPos = game.minotaur.moves(); 
		boolean expected = false;
		boolean actual = game.hasWon();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
		
	}
	//Test minotaur movement with hasLost
	@Test
	public void testGameMinotaurMovementWithHasLostTrue() {
		Game game = new Game();
		game.theseus.setLocation(2,1);
		game.minotaur.moves(); 
		boolean expected = true;
		boolean actual = game.hasLost();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
		
	}
	//Verify minotaur movement
/*  "xxxxxx",
 *  "xomoox", 
 *  "xoxxox", 
 *  "xooxooe", 
 *  "xoxxox", 
 *  "xotoox", 
 *  "xxxxxx"
*/	
	@Test
	public void testGameMinotaurMovementTowardsTheseus() {
		Game game = new Game();
		game.theseus.setLocation(new Position(4,3));
		Position minPos = game.minotaur.moves(); 
		String expected = "X: 4 Y: 1";
		String actual = minPos.toString();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
		
	}
	//Test two sets of moves by Minotaur
	@Test
	public void testGameMoreComplicatedMinotaurMovement() {
		Game game = new Game();
		game.theseus.setLocation(4,3);
		game.minotaur.moves();
		Position minPos = game.minotaur.moves(); 
		String expected = "X: 4 Y: 3";
		String actual = minPos.toString();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
		
	}
	//Test two sets of moves by Minotaur and test hasLost()
	@Test
	public void testGameComplexMinotaurMovementWithHasLostTrue() {
		Game game = new Game();
		game.theseus.setLocation(4,3);
		game.minotaur.moves(); 
		game.minotaur.moves(); 
		boolean expected = true;
		boolean actual = game.hasLost();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
		
	}

	//Test SaveGame
	@Test
	public void testSaveGameMethod() {
		Game game = new Game();
		game.theseus.setLocation(4,3);
		boolean expected = true;
		boolean actual = game.saveGame();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
		
	}

	//Test LoadGame
	@Test
	public void testLoadGameMethod() {
		Game game = new Game();
		boolean expected = true;
		boolean actual = game.loadSavedGame();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
		
	}
	//Test values of loaded game
	@Test
	public void testLoadedGameValues() {
		Game game = new Game();
		game.loadSavedGame();
		String expected = "X: 4 Y: 3";
		String actual = game.theseus.location.toString();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
		
	}
	//Test model entire game
	// Intended positions
	/*  "xxxxxx",
	 *  "xoooox", 
	 *  "xmxxox", 
	 *  "xomxooe", 
	 *  "xoxxox", 
	 *  "xttoox", 
	 *  "xxxxxx"
	*/	
	//Tests replicate how controller would operate
	//player moves have been predetermined for sake of test
	//in this test minotaur should be trapped in cul-de-sac
	@Test
	public void testGameLogicMiniControllerSim01() {
		Game game = new Game();
		String expected = game.theseus.location.toString();
		game.theseus.moveLEFT();
		game.minotaur.moves();
		if(game.hasWon())
			System.out.println("Great! You escaped!!!");
		if(game.hasLost())
			System.out.println("You were tasty");
		game.theseus.moveRIGHT();
		game.minotaur.moves();
		if(game.hasWon())
			System.out.println("Great! You escaped!!!");
		if(game.hasLost())
			System.out.println("You were tasty");
		String actual = game.theseus.location.toString();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
		
	}
	// In this test minotaur should be trapped in cul-de-sac
	// and theseus should escape and win game
	@Test
	public void testGameLogicMiniControllerTheseusWins() {
		Game game = new Game();
		game.theseus.moveLEFT();
		game.minotaur.moves();
		if(game.hasWon())
			System.out.println("Great! You escaped!!!");
		if(game.hasLost())
			System.out.println("You were tasty");
		game.theseus.moveRIGHT();
		game.minotaur.moves();
		if(game.hasWon())
			System.out.println("Great! You escaped!!!");
		if(game.hasLost())
			System.out.println("You were tasty");
		game.theseus.moveRIGHT();
		game.minotaur.moves();
		if(game.hasWon())
			System.out.println("Great! You escaped!!!");
		if(game.hasLost())
			System.out.println("You were tasty");
		game.theseus.moveRIGHT();
		game.minotaur.moves();
		if(game.hasWon())
			System.out.println("Great! You escaped!!!");
		if(game.hasLost())
			System.out.println("You were tasty");
		game.theseus.moveUP();
		game.minotaur.moves();
		if(game.hasWon())
			System.out.println("Great! You escaped!!!");
		if(game.hasLost())
			System.out.println("You were tasty");
		game.theseus.moveUP();
		game.minotaur.moves();
		if(game.hasWon())
			System.out.println("Great! You escaped!!!");
		if(game.hasLost())
			System.out.println("You were tasty");
		game.theseus.moveRIGHT();
		game.minotaur.moves();
		if(game.hasWon())
			System.out.println("Great! You escaped!!!");
		if(game.hasLost())
			System.out.println("You were tasty");
		game.theseus.moveRIGHT();
		game.minotaur.moves();
/*		if(game.hasWon())
			System.out.println("Great! You escaped!!!");
		if(game.hasLost())
			System.out.println("You were tasty");
*/
		boolean expected = true;
		boolean actual = game.hasWon();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
		
	}
	//In this test minotaur catches theseus and eats him
	@Test
	public void testGameSimMinotaurCatchesTheseus() {
		Game game = new Game();
		game.theseus.moveRIGHT();
		game.minotaur.moves();
		game.theseus.moveRIGHT();
		game.minotaur.moves();
		game.theseus.moveRIGHT();
		game.minotaur.moves();
		game.theseus.moveUP();
		game.minotaur.moves();
		boolean expected = true;
		boolean actual = game.hasLost();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	}

	//Test loading new level and check Theseus position
	@Test
	public void testLoadNewLevel() {
		Game game = new Game();
		game.loadLevel(1);
		String expected = "X: 5 Y: 2";
		String actual = game.theseus.location.toString();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
		
	}
	
	//Test minotaur position on new level
	@Test
	public void testMinotaurPlacement() {
		Game game = new Game();
		game.loadLevel(1);
		String expected = "X: 1 Y: 2";
		String actual = game.minotaur.location.toString();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
		
	}
	//Test minotaur move on newly loaded level
	@Test
	public void testMinotaurMoveNewLevel01() {
		Game game = new Game();
		game.loadLevel(1);
		game.minotaur.moves();
		String expected = "X: 1 Y: 2";
		String actual = game.minotaur.location.toString();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
		
	}
	
	@Test
	public void testMinotaurMoveNewLevel02() {
		Game game = new Game();
		game.loadLevel(1);
		game.loadLevel(2);
		game.theseus.moveRIGHT();
		game.minotaur.moves();
		game.minotaur.moves();
		boolean expected = true;
		boolean actual = game.hasLost();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
		
	}
	
	//Test minotaur move and hasLost
	@Test
	public void testMinotaurMoveWithHasLostTrue() {
		Game game = new Game();
		game.loadLevel(1);
		game.theseus.moveUP();
		game.minotaur.moves();
		game.minotaur.moves();
		game.minotaur.moves();
		boolean expected = true;
		boolean actual = game.hasLost();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	
	}
	//Test hasWon method
	@Test
	public void testNewLevelHaveWonMethod() {
		Game game = new Game();
		game.loadLevel(1);
		game.theseus.location = game.exitLocation;
		boolean expected = true;
		boolean actual = game.hasWon();
		String errorMessage = "Expected: " + expected + " but got " + actual;
		assertEquals(errorMessage, expected, actual);
	
	}
	
	
}
