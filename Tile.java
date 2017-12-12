
import java.awt.Color;
import javax.swing.*;

//class that define single tile on board
public class Tile extends JButton{

	//content
	private Player player = null;		//may or may not contain player
	private Key key = null;				//may or may not contain key
	private Treasure treasure = null;	//may or may not contain treasure
	private Coordinate location;		//coordinate of this tile
	private Boolean movable = false;	//movable location for player
	
	//constructor for empty tile
	public Tile(Coordinate location) {
		this.location = location;
		this.key = null;
		this.player = null;
		this.treasure = null;
		this.key = null;
		this.setBackground(new Color(224, 224, 224));	//grey color
		movable = false;
	}
	
	//Return false if no player in tile, else true
	public Boolean existPlayer() {
		if(player == null){
			return false;
		}
		else {
			return true;
		}
	}
	
	//Return false if no key in tile, else true
	public Boolean existKey() {
		if(key == null){
			return false;
		}
		else {
			return true;
		}
	}	

	//Return false if no treasure in tile, else true
	public Boolean existTreasure() {
		if(treasure == null){
			return false;
		}
		else {
			return true;
		}
	}	
	
	//set movable
	public void setMovable(Boolean movable) {
		this.movable = movable;
	}
	
	//set player on tile with checking condition, add key to player if key exist
	public void setPlayer(Player player) {
		if(existPlayer() == false)
		{
			player.setCoordinate(location);
			if(existKey() == true) {
				player.addKey(key);
			}
			this.player = player;
			this.setIcon(player.getIcon());
			this.movable = false;
		}
		else
		{
			System.out.println("Error: Set multiple players at " + location.getInfo() + "!");
		}
		
	}
	
	//remove player from tile
	public void removePlayer() {
		player = null;
		if(existKey() == true) {
			this.setIcon(key.getIcon());
		}
		else {
			this.setIcon(null);
		}
	}
	
	//set key on tile with checking condition
	public void setKey(Key key){
		if(existPlayer() == true){
			System.out.println("Error: Set key on player tile at " + location.getInfo() + " !");
		}
		else if(existTreasure() == true) {
			System.out.println("Error: Set key on treasure tile at " + location.getInfo() + " !");
		}
		else if(existKey() == true) {
			System.out.println("Error: Set multiple keys at " + location.getInfo() + "!");
		}
		else{
			this.key = key;
			this.setIcon(key.getIcon());
			this.setToolTipText(key.getName() + ": " + key.getDescription());
		}
	}
	
	//remove key from tile
	public void removeKey() {
		if(existKey() == true) {
			this.key = null;
			this.setIcon(null);
			this.setToolTipText(null);
		}
		else {
			System.out.println("Error: Remove empty key at " + location.getInfo() + "!");
		}
	}
	
	//set treasure on tile with checking condition
	public void setTreasure(Treasure treasure) {
		if(existPlayer() == true) {
			System.out.println("Error: Set treasure on player tile at " + location.getInfo() + " !");
		}
		else if(existKey() == true) {
			System.out.println("Error: Set treasure on key tile at " + location.getInfo() + " !");
		}
		else if(existTreasure() == true) {
			System.out.println("Error: Set multiple keys at " + location.getInfo() + "!");
		}
		else {
			this.treasure = treasure;
			this.setIcon(treasure.getIcon());
		}
	}	
	
	//remove treasure from tile
	public void removeTreasure() {
		if(existTreasure() == true) {
			this.treasure = null;
			this.setIcon(null);
			this.movable = true;
		}
		else {
			System.out.println("Error: Remove empty treasure at " + location.getInfo() + "!");
		}		
	}
	
	//move player from source to destination
	public void movePlayer(Tile source) {
		if(source.existPlayer() == false) {
			System.out.println("Error: No player in source tile at " + source.getTileLocation().getInfo() + "!");
		}
		else {
			this.setPlayer(source.getPlayer());
			source.removePlayer();
		}
	}
	
	//get Location
	public Coordinate getTileLocation() {
		return location;
	}
	
	//get Player in tile
	public Player getPlayer() {
		return player;
	}

	//get Key in tile
	public Key getKey() {
		return key;
	}	

	//get Treasure in tile
	public Treasure getTreasure() {
		return treasure;
	}	
	
	//get Movable in tile
	public Boolean getMovable() {
		return movable;
	}
}

