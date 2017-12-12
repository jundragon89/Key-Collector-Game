import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

//class that define key list (read key.txt)
public class KeyList {
	
	//content
	private ArrayList<Key>keys = new ArrayList<Key>();	
	private int size = 0;
	
	//Default constructor
	KeyList() {
		//empty list
	}	
	
	//Constructor to read key file
	KeyList(String fileName) {
		try {
			readFile(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//Constructor to read key file with randomize location
	public KeyList(String fileName, Tile[][] tiles) {
		try {
			readFile(fileName);
			//random placing of keys
			randomPlacing(tiles);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	}

	private void readFile(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		Scanner fin = new Scanner(file);
		String buffer;
		
		int size = fin.nextInt();		//read player size
		buffer = fin.nextLine();		//attempt to next line
		//System.out.println(size);
		for(int i = 0; i < size; i++) {
			String name = fin.nextLine();
			String iconPath = fin.nextLine();
			int [] direction = new int[8];
			for(int j = 0; j < 8; j++) {
				direction[j] = fin.nextInt();
			}
			Boolean skip = fin.nextBoolean();
			buffer = fin.nextLine();	//attempt to next line
			String description = fin.nextLine();
			
			Identity keyIdentity = new Identity(name,iconPath);
			Movement keyMovement = new Movement(direction[0],direction[1],direction[2],direction[3],direction[4],direction[5],direction[6],direction[7],skip);
			Key newKey = new Key(keyIdentity,keyMovement,description);
			keys.add(newKey);
			this.size = this.size + 1;	
		}
		fin.close();
	}
	
	public ArrayList<Key> getKeyList() {
		return keys;
	}
	
	public int getKeyListSize() {
		return size;
	}

	private void randomPlacing(Tile[][] tiles) {
		ArrayList<Coordinate> available = new ArrayList<Coordinate>();
		//put all available tile into array (no player, no key, no treasure)
		for(int j = 0; j < 9; j++) {
			for(int i = 0; i < 9; i++) {
				if(tiles[i][j].existKey() == false && tiles[i][j].existPlayer() == false && tiles[i][j].existTreasure() == false)
				{
					available.add(tiles[i][j].getTileLocation());
				}
			}
		}
		
		// Random seed
		Random rand = new Random();
		for(int i = 0; i < keys.size(); i++)
		{
			int n = rand.nextInt(available.size() - 1);		//random number from 0 to size of available tile
			keys.get(i).setCoordinate( available.get(n));	//set randomize coordinate into key location	
			available.remove(n);							//Take out coordinate that assign with key
		}
	}
	
}
