package petercampbell;

public class GameCharacter {
	protected Game myGame;
	public int noOfMoves;
	public Position location;
	
	public GameCharacter(Position placement, Game theGame) {
		location = placement;
		myGame = theGame;
		noOfMoves = 1;
		
	}
	
	public boolean moveUP() {
			 return move(Direction.UP);
			 
	}

	public boolean moveDOWN() {
			 return move(Direction.DOWN);
	}
	
	public boolean moveLEFT() {
			 return move(Direction.LEFT);
	}
	
	public boolean moveRIGHT() {
			 return move(Direction.RIGHT);
	}
	
	public boolean move(Direction direct) {
		//need to convert direction into position.
		int xAxis = location.x + direct.x;
		int yAxis = location.y + direct.y;
		Position testLocation = new Position(xAxis, yAxis);
		if(!myGame.isBlocked(testLocation)) {
			location = location.changePosition(direct.x, direct.y);
			return true;
		}
		return false;
	}
	
	public boolean isBlocked(Direction direct) {
		//if intended direction of travel is blocked by wall, return true;
		int xAxis = location.x + direct.x;
		int yAxis = location.y + direct.y;
		Position testLocation = new Position(xAxis, yAxis);
		return myGame.isBlocked(testLocation);
	}
	
	public void setLocation(int newX, int newY) {
		location.setPosition(newX, newY);
	}
	public void setLocation(Position pos) {
		location = pos;
	}
	
	public Position getLocation() {
		return location;
	}

}
