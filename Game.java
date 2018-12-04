package petercampbell;

import java.io.*;
import java.util.ArrayList;
public class Game {
	
	public String [][] allMyLevels;
	public String [] currentLevelPlan;
	public ArrayList<Position> allMyWalls;
	public int currentLevel;
	public int currentLength;
	public int currentWidth;
	public Position exitLocation;
	public GameCharacter theseus;
	public Minotaur minotaur;
	public String saveGameFilename;
	public String mapFilename;
	
	public Game() {
		saveGameFilename = "TaM.dat";
		mapFilename = "TaMMaps.dat";
		initializeGame();
		loadLevel(0);
	}
	
		
	public void initializeGame() {
		///set all maze maps
		currentLevel = 0;
		if(!loadMapsFromFile()) {
			loadMapsFromCode();
		}
	}
	
	
	public void loadLevel (int level) {
		currentLevel = level;
		currentLevelPlan = allMyLevels[currentLevel];
		currentWidth = currentLevelPlan[0].length();
		currentLength = currentLevelPlan.length;
		exitLocation = findInMaze('e');
		theseus = new GameCharacter(findInMaze('t'), this);
		minotaur = new Minotaur(findInMaze('m'), this);
		setWalls();
	}
	
	public boolean isBlocked(Position intendedMove) {
		//Return true if wall is present
		for (Position aWall : allMyWalls){
			if(aWall.x == intendedMove.x && aWall.y == intendedMove.y) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasWon() {
		Position thesPos = theseus.getLocation(); 
		String exit = exitLocation.toString();
		String theseus = thesPos.toString();
		return theseus.equals(exit);
	}
	
	
	public boolean hasLost() {
		Position theseusLocation = theseus.getLocation();
		Position minotaurLocation = minotaur.getLocation();
		String theseus = theseusLocation.toString();
		String minotaur = minotaurLocation.toString();
		return theseus.equals(minotaur);
	}
	
	//needed for Minotaur.findTheseus() 
	public Position getTheseusLocation() {
		return theseus.getLocation();
	}
	
	public void setTheseusLocation(Position newLocation) {
		theseus.location = newLocation;
	}
	
	public void setMinotaurLocation(Position newLocation) {
		minotaur.setLocation(newLocation);
	}
	
	public void setSaveGameFilename(String newsaveGameFilename) {
		saveGameFilename = newsaveGameFilename;
	}
	public void setMapFilename( String newFilename) {
		mapFilename = newFilename;
	}
	
	public boolean loadMapsFromFile() {
		String inputStr = null;
		int count = 0;
		int maxLines=0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(mapFilename));
			while(br.readLine()!=null) {
				maxLines ++;
			}
			br.close();
			br = new BufferedReader(new FileReader(mapFilename));
			allMyLevels = new String[maxLines][];
			while( (inputStr = br.readLine()) !=null) {
				allMyLevels[count] = inputStr.split(",");
				count ++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not find the required file. Game could not load");
			return false;
		}catch (IOException e) {
			System.out.println("Problem reading current files. Game could not load");
			return false;
		}
		return true;
	}
	private void loadMapsFromCode() {
		allMyLevels = new String[][] {
			{"xxxxxx","x m  x","x xx x","x  x  e","x xx x","x t  x","xxxxxx"},
			{"xxxxxxxxxxx","x         x","xmx xt    x","x xxx     x","x     x x x","x     xxx x","x         x","xx xxxxxxxx"," xex"},
			{"xxxxxxxx","x  m   x","x  x   xx","x  t    e","x x    xx","xxx    x","x      x","xxxxxxxx"},
			{"     xex","xxxxxx xxxx","x xt      x","x   x  xx x","x  xx   xmx","x     x   x","x   xxx   x","x     x x x","x     xxx x","xxxxxxxxxxx"},
			{"        xex","xxxxxxxxx xxx","x  t   x  x x","x x    x xx x","x xx x x    x","x    x   xx x","x   xx    x x","x   xm x xx x","x   xxxxxx  x","x           x","xxxxxxxxxxxxx"}
		};
		
	}
		/*Information to save is: Level, Theseus Position, Minotaur Position*/	
	public boolean saveGame() {

		String line01 = "Level," + currentLevel;
		String line02 = "Theseus Position," + theseus.location.x + "," + theseus.location.y;
		String line03 = "Minotaur Position," + minotaur.location.x + "," + minotaur.location.y;
		try {
			String file = saveGameFilename;
			FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(line01);
            bufferedWriter.newLine();
            bufferedWriter.write(line02);
            bufferedWriter.newLine();
            bufferedWriter.write(line03);
            bufferedWriter.newLine();
            bufferedWriter.close();
 		}catch(IOException ex) {
 			return false;
		}
		
		return true;
	}
	
	/*Loads information from file on level, Theseus Position, Minotaur Position
	 *Then loads map and sets walls, resets position of Theseus and Minotaur*/
	public boolean loadSavedGame() {
		String [] inputArr = new String [3];
		String inputStr = null;
		String line01, line02, line03;
		//get input from file
		String file = "TaM.dat";
		int count = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((inputStr = br.readLine())!=null) {
				inputArr[count] = inputStr;
				count ++;
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not find the required file. Game could not load");
			return false;
		}catch (IOException e) {
			System.out.println("Problem reading current files. Game could not load");
			return false;
		}
		
		//get key data
		line01 = inputArr[0];
		String [] getDataArr = line01.split(","); 
		int level = Integer.parseInt(getDataArr[1]);
		line02 = inputArr[1];
		getDataArr = line02.split(",");
		int tX = Integer.parseInt(getDataArr[1]);
		int tY = Integer.parseInt(getDataArr[2]);
		Position theseusPosition = new Position(tX,tY);
		line03 = inputArr[1];
		getDataArr = line03.split(",");
		int mX = Integer.parseInt(getDataArr[1]);
		int mY = Integer.parseInt(getDataArr[2]);
		Position minotaurPosition = new Position(mX,mY);
		
		
		//load level information
		loadLevel(level);
		theseus.setLocation(theseusPosition);
		minotaur.setLocation(minotaurPosition);
		return true;
	}
	
	
	//returns location of sought for item (m for minotaur, t for theseus, e for exit). If null found returns (-1,-1)
	public Position findInMaze(char searchKey) {
		int x;
		int y;
		for (int yAxisSearch = 0; yAxisSearch < currentLength; yAxisSearch ++) {
			for (int xAxisSearch = 0; xAxisSearch < currentLevelPlan[yAxisSearch].length(); xAxisSearch ++) {
				if(searchKey == currentLevelPlan[yAxisSearch].charAt(xAxisSearch)) {
					x = xAxisSearch;
					y = yAxisSearch;
					return new Position(x,y);
				}
			}
		}
		return new Position(-1,-1);	
		
	}
	public void setWalls() {
		//empty arrayList
		allMyWalls = new ArrayList<Position>();
		//goThroughArray = y axis; goThroughString = x axis
		for (int goThroughArray = 0; goThroughArray < currentLength; goThroughArray ++) {
			String data = currentLevelPlan[goThroughArray];
			for (int goThroughString = 0; goThroughString < data.length(); goThroughString ++) {
				if (data.charAt(goThroughString) == 'x') {
					allMyWalls.add(new Position(goThroughString, goThroughArray));	
				}
				
			}
			
		}
		
	}
	
	
	
}
