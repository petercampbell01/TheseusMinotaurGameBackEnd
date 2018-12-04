package petercampbell;

public enum Direction {
	//x = horizontal, y = vertical 
	UP (0, -1),
	DOWN (0, 1),
	LEFT (-1,0),
	RIGHT (1,0);
	
	public int x;
	public int y;
	
	Direction(int newX, int newY) {
		x = newX;
		y = newY;
		
	}
	public String toString() {
		return "X: "+ x + " Y: " + y;
	}
}
