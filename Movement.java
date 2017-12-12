//Class that defines the Movement Pattern
public class Movement{

	//content
	protected int north;		//up
	protected int south;		//down
	protected int east;			//right
	protected int west;			//left
	protected int northEast;	//right up
	protected int northWest;	//left up
	protected int southEast;	//right down
	protected int southWest;	//left down
	protected Boolean skip;		//able to skip
	
	//default constructor to set all zero
	public Movement() {
		north = 0;
		south = 0;		
		east = 0;		
		west = 0;		
		northEast = 0;
		northWest = 0;	
		southEast = 0;	
		southWest = 0;	
		skip = true;
	}
	
	//constructor to set all direction
	public Movement(int north, int south, int east, int west, int northEast, int northWest, int southEast, int southWest,  Boolean skip) {
		this.north = north;
		this.south = south;		
		this.east = east;		
		this.west = west;		
		this.northEast = northEast;
		this.northWest = northWest;	
		this.southEast = southEast;	
		this.southWest = southWest;
		this.skip = skip;
	}
	
	//constructor to set diagonal(x) or vertical+horizontal(t)
	public Movement(int a, int b, int c, int d, Boolean skip, char type) {
		if(type == 'x')		//set diagonal direction
		{
			north = 0;
			south = 0;		
			east = 0;		
			west = 0;			
			northEast = a;
			northWest = b;	
			southEast = c;	
			southWest = d;
			this.skip = skip;
		}
		else if(type == 't')	//set vertical and horizontal direction
		{
			north = a;
			south = b;		
			east = c;		
			west = d;			
			northEast = 0;
			northWest = 0;	
			southEast = 0;	
			southWest = 0;
			this.skip = skip;
		}
	}

	
	//Setter
	public void setNorth(int north) {
		this.north = north;
	}	
	
	public void setSouth(int south) {
		this.south = south;
	}
	
	public void setEast(int east) {
		this.east = east;
	}
	
	public void setWest(int west) {
		this.west = west;
	}	
	
	public void setNorthEast(int northEast) {
		this.northEast = northEast;
	}	
	
	public void setNorthWest(int northWest) {
		this.northWest = northWest;
	}	
	
	public void setSouthEast(int southEast){
		this.southEast = southEast;
	}
	
	public void setSouthWest(int southWest) {
		this.southWest = southWest;
	}	

	public void setSkip(Boolean skip) {
		this.skip = skip;
	}

	public void setMovement(Movement newMovement) {
		north = newMovement.north;
		south = newMovement.south;		
		east = newMovement.east;		
		west = newMovement.west;		
		northEast = newMovement.northEast;
		northWest = newMovement.northWest;	
		southEast = newMovement.southEast;	
		southWest = newMovement.southWest;
		skip = newMovement.skip;
	}
	
	//getter
	public int getNorth() {
		return north;
	}	

	public int getSouth() {
		return south;
	}
	
	public int getEast() {
		return east;
	}
	
	public int getWest() {
		return west;
	}
	

	public int getNorthEast() {
		return northEast;
	}

	public int getNorthWest() {
		return northWest;
	}

	public int getSouthEast() {
		return southEast;
	}
	
	public int getSouthWest() {
		return southWest;
	}

	public Boolean getSkip() {
		return skip;
	}
	
	public Movement getMovePattern() {
		return this;
	}
	
}
