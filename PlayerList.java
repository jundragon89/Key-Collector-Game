
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

//class that define player list (read player.txt)
public class PlayerList {
	
	//content
	private ArrayList<Player>players = new ArrayList<Player>();	
	private int size = 0;
	
	//Default constructor
	public PlayerList() {
		//empty list
	}
	
	//Constructor to load player.txt
	PlayerList(String fileName) throws IOException {
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
			int x = fin.nextInt();
			int y = fin.nextInt();
			buffer = fin.nextLine();
			
			Coordinate playerLocation = new Coordinate(x,y);
			Identity playerIdentity = new Identity(name,iconPath,playerLocation);
			MovementController playerMovement = new MovementController(playerLocation,direction[0],direction[1],direction[2],direction[3],direction[4],direction[5],direction[6],direction[7],skip);
			Player newPlayer = new Player(playerIdentity,playerMovement);
			players.add(newPlayer);
			this.size = this.size + 1;
		}		
		fin.close();
	}
	
	public ArrayList<Player> getPlayerList() {
		return players;
	}
	
	public int getPlayerListSize() {
		return size;
	}
	
}
