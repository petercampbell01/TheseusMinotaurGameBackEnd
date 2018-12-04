package petercampbell;

public class Minotaur extends GameCharacter {

	public Minotaur(Position placement, Game theGame) {
		super(placement, theGame);
		noOfMoves = 2;

	}
	
	public Position moves() {
		//if(isTheseus) {
		//	return false;
		//}	
		for(int countMoves = 0; countMoves < noOfMoves; countMoves ++) {
			//1.get direction for Minotaur moves
			//2.check if can move
			//3.move
			//4. update location
			Direction [] guideToTheseusLocation = findTheseus();
			if(guideToTheseusLocation[0] != null) {
				Direction minotaurMove = guideToTheseusLocation[0];
				boolean attemptMove = move(minotaurMove);
				if(attemptMove == false && guideToTheseusLocation[1] != null) {
					minotaurMove = guideToTheseusLocation[1];
					move(minotaurMove);
				}
			}else if(guideToTheseusLocation[1] != null) {
				Direction minotaurMove = guideToTheseusLocation[1];
				move(minotaurMove);
			}
		}
		return location;
	}
	
	
	public Direction [] findTheseus() {
		//return array of two directions Theseus is in. Minotaur must move sideways
		//before moving up and down. If y-axis is the same then it can only move 
		//sideways
		Direction [] findTheseus = new Direction [2];
		Position theseusPosition = myGame.getTheseusLocation();
		//calculate left or right movement ie x axis.
		int myX = location.x;
		int tX = theseusPosition.x;
		int directionX = tX - myX;
		if(directionX > 0) {
			//movement should be to right
			findTheseus[0] = Direction.RIGHT;
		}
		if(directionX < 0 ) {
			//movement should be to left
			findTheseus[0] = Direction.LEFT;
		}
			
		//calculate up and down movement ie y-axis
		int myY = location.y;
		int tY = theseusPosition.y;
		int directionY = myY - tY;
		if(directionY < 0) {
			//Movement down
			findTheseus[1] = Direction.DOWN;
		}
		if(directionY > 0) {
			findTheseus[1] = Direction.UP;
		}
			
		return findTheseus;
	}

	
}
