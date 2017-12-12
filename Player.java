import java.util.ArrayList;

public class Player extends Identity{
	
	//content
	private MovementController moveControl;				//player movement control method
	private ArrayList<Key> keys = new ArrayList<Key>();	//list of key of player

	//constructor for player with default movement pattern
	public Player(Identity identity) {
		super(identity);
		moveControl = new MovementController(this.getLocation(),2,2,2,2,2,2,2,2,false);
	}

	//constructor for player assigned movement pattern
	public Player(Identity identity, MovementController movement) {
		super(identity);
		moveControl = movement;
	}	
	
	//add key and set new movement and destination
	public void addKey(Key k) {
		if(keys.contains(k))
		{
			keys.remove(k);	//if list of key already having same key, remove it
		}
		keys.add(k);	//add key
		moveControl.setMovement(k.getMovePattern());
		moveControl.updateDestination(this.getLocation());
	}
	
	//add key and set new movement and destination by key name and array
	public void addKey(String keyName, ArrayList<Key> keyList) {
		for(int i = 0; i < keyList.size(); i++) {
			if(keyName.equals(keyList.get(i).getName())) {
				if(keys.contains(keyList.get(i)))
				{
					keys.remove(keyList.get(i));	//if list of key already having same key, remove it
				}
				keys.add(keyList.get(i));	//add key
				moveControl.setMovement(keyList.get(i).getMovePattern());
				moveControl.updateDestination(this.getLocation());							
			}
		}
	}	
	
	//update new destination based on current location
	public void updateDestination() {
		moveControl.updateDestination(this.getLocation());
	}
	
	//get key list
	public final ArrayList<Key> getKeys() { 
		return keys;
	}
	
	//get player available destination
	public final ArrayList<Coordinate> getDestination() { 
		return moveControl.getDestination();
	}	

}

