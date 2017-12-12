
import java.util.ArrayList;

//Class that defines the treasure
public class Treasure extends Identity{
	
	//content
	ArrayList <Key> locks = new ArrayList<Key>();
	
	//constructor with empty key
	public Treasure(Identity identity) {
		super(identity);
	}
	
	//constuctor with array of required key
	public Treasure(Identity identity, ArrayList<Key> locks) {
		super(identity);
		this.locks = locks;
	}	
	
	//return true if all locks are fulfill
	public Boolean checkOpenCondition(Player p) {
		//return false if player key size smaller than locks size
		if(p.getKeys().size() < locks.size())
		{
			return false;	
		}
		else
		{
			for(int i = 0; i< locks.size(); i++)
			{
				Key lock = locks.get(i);
				//if player keys not able to open lock, return false
				if(!p.getKeys().contains(lock))
				{
					return false;
				}
			}	
			return true;	//return true if all condition fulfill
		}

	}
	
	//add single lock
	public void addLock(Key lock) {
		locks.add(lock);
	}

	//clear all locks
	public void clearLock() {
		locks.clear();
	}
	
	//getter
	public ArrayList <Key> getLocks() {
		return locks;
	}
	
}

