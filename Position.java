package petercampbell;

public class Position {

	public int x;
	public int y;
	
	public Position(int newX, int newY) {
		x = newX;
		y = newY;
		
	}
	
	public Position changePosition(int newX,int newY) {
		x += newX;
		y += newY;
	return this;
	}
	
	public void setPosition(int newX, int newY) {
		x = newX;
		y = newY;
	}
	
	public String toString() {
		return "X: " + x + " Y: " + y;
	}

}


