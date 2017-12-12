import java.util.ArrayList;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;


public class Game extends JFrame implements ActionListener  {
	
	// Game objects
	private Tile[][] tiles = new Tile[9][9];		//9x9 tiles
	private ArrayList<Player>players = new ArrayList<Player>();	//list of player
	private ArrayList<Key>keys= new ArrayList<Key>();			//list of key
	private Treasure chest;				//treasure
	private Player currentPlayer;		//current player
	private Tile currentTile;			//tile of current player
	private int turn = 0;				//initial turn
	private Boolean endGame = false;	//end of game	
	
	//Create an single static instance of this class
	private static Game singleInstance = new Game();

	//Method to retrieve the only object instantiated
	public static Game getInstance() {
		return singleInstance;
	}
	
	//GUI components
	//Create main panel
	private JPanel gamePanel = new JPanel(new BorderLayout());	
	//Create board panel that contain 9x9 tiles
	private JPanel boardPanel = new JPanel(new GridLayout(9, 9));	
	//Create menu panel that contain start, save, and load
	private JPanel menuPanel = new JPanel(new GridLayout(1, 3));	
	//Create east panel that contain score panel and action panel
	private JPanel eastPanel = new JPanel(new BorderLayout());	
	//Create score panel that contain player collected key (nested from east panel)	
	private JPanel scorePanel = new JPanel(new GridLayout(4, 6));	
	JLabel []playerAvatarLabel = new JLabel[4];		//label to display player avatar
	JLabel [][]playerKeyLabel = new JLabel[4][5];	//label to display player collected key	
	ImageIcon emptyKey = new ImageIcon("picture/empty.png");	//empty key		
	//Create info panel (nested from east panel)
	private JPanel infoPanel = new JPanel();
	JLabel displayTurn = new JLabel();		//label to display turn
	JLabel displayPlayer = new JLabel();	//label to display player in play
	
	//default constructor (add components to panel and initialize)
	private Game() {
		super("Key Collector Game");

		//Add default tiles for board panel
		for (int j = 0; j < 9; j++) {
			for (int i = 0; i < 9; i++) {
				Coordinate location = new Coordinate(i,j);
				tiles[i][j] = new Tile(location);
				tiles[i][j].setFocusable(false);
				tiles[i][j].addActionListener(this);
				boardPanel.add(tiles[i][j]);
			}
		}
		
		//Add components to menu panel
		JButton newBtn = new JButton("New Game");
		JButton saveBtn = new JButton("Save Game");
		JButton loadBtn = new JButton("Load Game");
		newBtn.addActionListener(this);
		saveBtn.addActionListener(this);
		loadBtn.addActionListener(this);
		menuPanel.add(newBtn);
		menuPanel.add(saveBtn);
		menuPanel.add(loadBtn);
		
		//Add components to info panel
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		displayTurn.setFont(new Font("Monospaced", Font.BOLD, 25));
		displayPlayer.setFont(new Font("Monospaced", Font.BOLD, 25));
		infoPanel.add(displayTurn);
		infoPanel.add(displayPlayer);
				
		//Add components to score panel	
		for(int i = 0; i < 4; i++)
		{
			playerAvatarLabel[i] = new JLabel();
			playerAvatarLabel[i].setOpaque(true);
			playerAvatarLabel[i].setBorder(BorderFactory.createLineBorder(Color.black));
			scorePanel.add(playerAvatarLabel[i]);
			for(int j = 0; j < 5; j++)
			{
				playerKeyLabel[i][j] = new JLabel(emptyKey);
				playerKeyLabel[i][j].setOpaque(true);
				playerKeyLabel[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
				scorePanel.add(playerKeyLabel[i][j]);			
			}
		}		
		
		//Add all panels to east panel
		eastPanel.add(infoPanel,"North");
		eastPanel.add(scorePanel,"Center");
		
		//Add all panels to game panel
		gamePanel.add(menuPanel, "North");
		gamePanel.add(boardPanel, "Center");
		gamePanel.add(eastPanel, "East");

		// JFrame settings
		add(gamePanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setVisible(true);
		
		// Initialize game
		try {
			initialize();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//initialize all items
	//Done by Ong Kyle 1151101783
	private void initialize() throws IOException {

		//Reset game
		endGame = false;
		turn = 0;
		
		
		//Generate players
		
		//Read player list
		PlayerList playerList = new PlayerList("player.txt");								
		//Put players into an array
		for(int i = 0; i < playerList.getPlayerListSize(); i++) {
			players.add(playerList.getPlayerList().get(i));
		}		
		//Place players into tiles
		for(int i = 0; i < players.size(); i++) {
			int x = players.get(i).getLocation().getX();
			int y = players.get(i).getLocation().getY();
			tiles[x][y].setPlayer(players.get(i));
		}
		
		
		//Place treasure chest in the middle (empty lock)
		Identity iChest = new Identity("chest","picture/5.png",new Coordinate(4,4));	
		chest = new Treasure(iChest,keys);
		tiles[4][4].setTreasure(chest);


		//Generate key
	
		//Read key list
		KeyList keyList = new KeyList("key.txt", tiles);	
		//Put keys into an array
		for(int i = 0; i < keyList.getKeyListSize(); i++) {
			keys.add(keyList.getKeyList().get(i));
		}
		//Place keys on to tiles after randomize, add key as lock to treasure
		for(int i = 0; i < keys.size(); i++) {
			int x = keys.get(i).getLocation().getX();
			int y = keys.get(i).getLocation().getY();
			tiles[x][y].setKey(keys.get(i));
		}	
		
		//Reset chest tile
		chest = new Treasure(iChest,keys);
		tiles[4][4].removeTreasure();
		tiles[4][4].setTreasure(chest);
		
		
		//Initialize player score
		for(int i = 0; i < 4; i++)
		{
			ImageIcon playerAvatar = players.get(i).getIcon();
			playerAvatarLabel[i].setIcon(playerAvatar);
			playerAvatarLabel[i].setToolTipText(players.get(i).getName());
		}
		playerAvatarLabel[0].setBackground(new Color(0,255,255));	//blue
		
		// Initialize first player
		currentPlayer = players.get(0);
		currentTile = tiles[currentPlayer.getLocation().getX()][currentPlayer.getLocation().getY()];
		currentTile.setBackground(new Color(255,255,51));	//yellow
		displayTurn.setText("Turn " + (turn + 1));
		displayPlayer.setText(currentPlayer.getName());
		// Check movable tiles for first player
		checkMovable();
		
	}
			
	//verify player next available location
	//Done by Ong Kyle 1151101783
	private void checkMovable() {
		ArrayList<Coordinate> destinations = currentPlayer.getDestination();
		for(int i = 0; i < destinations.size(); i++)
		{
			int x = destinations.get(i).getX();
			int y = destinations.get(i).getY();
			if(tiles[x][y].existPlayer() == false)
			{
				if(x == 4 && y == 4){
					//verify player able to open treasure
					if(chest.checkOpenCondition(currentPlayer))
					{
						tiles[4][4].setMovable(true);
						tiles[4][4].setBackground(new Color(28, 255, 11));	//green
					}
				}
				else{
					tiles[x][y].setMovable(true);
					tiles[x][y].setBackground(new Color(28, 255, 11));	//green
				}
			}
		}
	}

	//start a new game
	//Done by Ng Kean Haur
	public void newGame() throws IOException {
		//reset player score
		resetScore();
		//Clear all tiles
		clearBoard();
		resetBoard();
		//Reinitialize
		initialize();
	}

	//reset player score
	//Done by Ng Kean Haur
	private void resetScore() {	
		if(players.size() > 0) {
			for(int i = 0; i < players.size(); i++) {
				playerAvatarLabel[i].setBackground(null);	//set null to player avatar
				for(int j = 0; j < 5; j++){
					playerKeyLabel[i][j].setIcon(emptyKey);	
					playerKeyLabel[i][j].setToolTipText(null);	
					playerKeyLabel[i][j].setBackground(null);	//set null to player collected key
				}				
			}				
		}
	}
	
	// clear all tiles in board
	//Done by Ng Kean Haur
	private void clearBoard() {
		//remove all players
		if(players.size() > 0) {
			for (int i = 0; i < players.size(); i++) {
				Coordinate playerLocation = players.get(i).getLocation();
				tiles[playerLocation.getX()][playerLocation.getY()].removePlayer();
			}
			players.clear();			
		}
		
		//remove all keys
		if(keys.size() > 0) {
			for (int i = 0; i < keys.size(); i++) {
				Coordinate keyLocation = keys.get(i).getLocation();			
				tiles[keyLocation.getX()][keyLocation.getY()].removeKey();
				tiles[keyLocation.getX()][keyLocation.getY()].setToolTipText(null);;
			}
			keys.clear();			
		}
		
		//remove treasure
		if(tiles[4][4].existTreasure() == true) {
			tiles[4][4].removeTreasure();			
		}
	}

	//reset all tiles as non movable and default background
	//Done by Ng Kean Haur
	private void resetBoard() {
		for(int j = 0; j < 9; j++){
			for(int i = 0; i < 9; i++) {
				tiles[i][j].setMovable(false);
				tiles[i][j].setBackground(new Color(224, 224, 224));	//grey
			}
		}
	}
	
	//update player score (collected keys)
	//Done by Ng Kean Haur
	private void updateScore(int index,Player player) {
		int numOfKey = player.getKeys().size();
		for (int i = 0; i < numOfKey; i++) {
			ImageIcon keyIcon = player.getKeys().get(i).getIcon();
			String description = player.getKeys().get(i).getDescription();
			String keyName = player.getKeys().get(i).getName();
			playerKeyLabel[index][i].setIcon(keyIcon);
			playerKeyLabel[index][i].setToolTipText(keyName + ": " + description);
			if(i == (numOfKey -1)) {
				playerKeyLabel[index][i].setBackground(new Color(255,102,255));	//latest key is pink
			}
			else {
				playerKeyLabel[index][i].setBackground(null);	//null
			}
		}
	}
	
	//save game
	//Done by Heng Jun Rong 1161301503
	private void saveGame() {
		String savePath = "save.txt";
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(savePath);
			bw = new BufferedWriter(fw);
			
			//record turn
			bw.write("turn:");
			bw.newLine();
			bw.write(String.valueOf(turn));
			bw.newLine();
			
			//key information
			bw.write("keyCount:");
			bw.newLine();
			bw.write(String.valueOf(keys.size()));
			bw.newLine();
			for(int i = 0; i < keys.size(); i++) {
				bw.write(keys.get(i).getName());	//key name
				bw.newLine();
				bw.write("location:");		
				bw.newLine();
				bw.write(keys.get(i).getLocation().getX() + " " + keys.get(i).getLocation().getY()); //key coordinate	
				bw.newLine();
			}	
			//player information
			bw.write("playerCount:");			
			bw.newLine();
			bw.write(String.valueOf(players.size()));	//player size
			bw.newLine();
			for(int i = 0; i < players.size(); i++) {
				bw.write(players.get(i).getName());	//player name
				bw.newLine();
				bw.write("location:");
				bw.newLine();
				bw.write(players.get(i).getLocation().getX() + " " + players.get(i).getLocation().getY()); //player coordinate
				bw.newLine();
				bw.write(String.valueOf(players.get(i).getKeys().size()));
				bw.newLine();
				for(int j = 0; j < players.get(i).getKeys().size(); j++) {
					bw.write(players.get(i).getKeys().get(j).getName());
					bw.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}		
	}
	
	//load game
	//Done by Heng Jun Rong 1161301503
	public void loadGame() throws IOException {		
		//reset player score
		resetScore();
		//Clear all tiles
		clearBoard();
		resetBoard();
		
		//place treasure
		tiles[4][4].setTreasure(chest); 
		
		//Read player list
		PlayerList playerList = new PlayerList("player.txt");								
		//Put players into an array
		for(int i = 0; i < playerList.getPlayerListSize(); i++) {
			players.add(playerList.getPlayerList().get(i));
		}
		//Read key list
		KeyList keyList = new KeyList("key.txt");	
		//Put keys into an array
		for(int i = 0; i < keyList.getKeyListSize(); i++) {
			keys.add(keyList.getKeyList().get(i));
		}
		
		//read input from file
		File file = new File("save.txt");
		try {
			Scanner fin = new Scanner(file);
			String buffer;
			
			buffer = fin.nextLine();	//turn:
			turn = fin.nextInt();		//get turn 
			buffer = fin.nextLine();	//new line
			
			buffer = fin.nextLine();		//key count:
			int keySize = fin.nextInt();	//get key size
			buffer = fin.nextLine();		//buffer
			for (int i=0; i < keySize; i++) {
				String keyName = fin.nextLine();	//get key name
				
				buffer = fin.next(); 				//Location:
				int x = fin.nextInt();				//get x coordinate of key
				int y = fin.nextInt();				//get y coordinate of key
				buffer = fin.nextLine();			//buffer

				for(int j = 0; j < keys.size(); j++) {
					if(keys.get(j).getName().equals(keyName)) {
						keys.get(j).setCoordinate(new Coordinate(x,y));
						tiles[x][y].setKey(keys.get(j));	//placing key to tiles
					}
				}
			}
			buffer = fin.nextLine();			//playerCount:
			int playerSize = fin.nextInt();		//get player size
			buffer = fin.nextLine();			//buffer
			
			for (int i=0; i < playerSize; i++) {
				String playerName = fin.nextLine();	//get player name
				buffer = fin.nextLine(); 			//location:
				int x = fin.nextInt();				//get x coordinate of key
				int y = fin.nextInt();				//get y coordinate of key
				
				
				int keyOwn = fin.nextInt();			//get num of key of player
				buffer = fin.nextLine(); 			//buffer
				String playerKeyList[] = new String[keyOwn];
				for(int j = 0; j < keyOwn; j++) {	//get list of player key
					playerKeyList[j] = fin.nextLine();
				}
				for(int k = 0; k < playerSize; k++) {
					if(players.get(k).getName().equals(playerName)) {	//find out player that match the name
						players.get(k).setCoordinate(new Coordinate(x,y));	//set player coordinate
						for(int l = 0; l < keyOwn; l++) {
							players.get(k).addKey(playerKeyList[l], keys);	//add player keys
						}
						players.get(k).updateDestination();
						tiles[x][y].setPlayer(players.get(k));	//place player into tiles
					}					
				}
				
			}	
			currentPlayer = players.get(turn%players.size());
			currentTile = tiles[currentPlayer.getLocation().getX()][currentPlayer.getLocation().getY()];
			currentTile.setBackground(new Color(255,255,51));	//yellow
			
			//update east panel
			displayTurn.setText("Turn " + (turn + 1));
			displayPlayer.setText(currentPlayer.getName());
			playerAvatarLabel[turn%players.size()].setBackground(new Color(0,255,255));	//blue
			for(int i = 0; i < players.size(); i++) {
				updateScore(i,players.get(i));
			}
			
			//continue to play
			checkMovable();
			//close file
			fin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	//action perform
	//Done by Heng Jun Rong 1161301503
	@Override
	public void actionPerformed(ActionEvent event) {
		JButton clickedButton = (JButton) event.getSource();
		
		if (clickedButton.getText().equals("New Game")) {	//start new game
			try {
				newGame();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		else if(clickedButton.getText().equals("Save Game")) {	//save game
			if(!endGame) {
				saveGame();
			}
		}
		else if(clickedButton.getText().equals("Load Game")) {	//load game
			try {
				loadGame();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {	//play game
			Tile clickedTile = (Tile)event.getSource();
			if(clickedTile.getMovable() == true)
			{
				clickedTile.movePlayer(currentTile);
				if(clickedTile.existKey() == true)	//if tile has key, add update score panel
				{
					updateScore(turn%players.size(),currentPlayer);
				}
				
				//Reset before goto next player
				playerAvatarLabel[turn%players.size()].setBackground(null);	//set player avatar to default background
				players.get(turn%players.size()).updateDestination();		//update this player movement
				
				//End game if current player unlock the tile with treasure
				endGame = (tiles[4][4].getPlayer() == currentPlayer);
				
				//if game not end, go to next player turn				
				if (!endGame) {
										
					//Increase turn
					turn++;
					
					//reset board
					resetBoard();
					//Set current player as next player
					currentPlayer = players.get(turn%4);
					currentTile = tiles[currentPlayer.getLocation().getX()][currentPlayer.getLocation().getY()];
					currentTile.setBackground(new Color(255,255,51));	//yellow
					
					//update east pane;
					displayTurn.setText("Turn " + (turn + 1));
					displayPlayer.setText(currentPlayer.getName());
					playerAvatarLabel[turn%players.size()].setBackground(new Color(0,255,255));	//blue
					
					//check new movement
					checkMovable();
				}
				else {	//end game and new game
					JOptionPane.showMessageDialog(this,"Player " + currentPlayer.getName() + " win the game in turn " + (turn + 1) +"!");
					try {
						newGame();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
	}	

	
}
