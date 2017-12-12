import java.util.ArrayList;

public class MovementController extends Movement{
	
	//content 
	private final int min = 0;		//board size min index
	private final int max = 8;		//board size max index
	private int x;		//coordinate x of source location
	private int y;		//coordinate y of source location
	private ArrayList<Coordinate> destinations = new ArrayList<Coordinate>();	//array of next available destinations

	//constructor to set all direction
	public MovementController(Coordinate source,int north, int south, int east, int west, int northEast, int northWest, int southEast, int southWest, Boolean skip) {
		super(north,south,east,west,northEast,northWest,southEast,southWest,skip);
		x = source.getX();
		y = source.getY();
		updateDestination();
	}

	//constructor to set diagonal(d) or vertical+horizontal(o)
	public MovementController(Coordinate source, int a, int b, int c, int d, Boolean skip, char type) {
		super(a,b,c,d,skip,type);
		x = source.getX();
		y = source.getY();
		updateDestination();
	}	
	
	//add next location to destination array if not beyond min and max index of board
	public void addNext(int x, int y) {
		if((x >= min) && (y >= min) && (x <= max) && (y <= max)){
			destinations.add(new Coordinate(x,y));
		}
	}
	
	//update destinations based on each direction
	public void updateDestination() {
		destinations.clear();	//reset destinations
		if(skip == false)	//add all possible destinations
		{
			if(north > 0){
				for(int i = 1; i <= north; i++){
					addNext(x,y-i);
				}
			}		
			if(south > 0){
				for(int i = 1; i <= south; i++){
					addNext(x,y+i);
				}
			}
			if(east > 0){
				for(int i = 1; i <= east; i++){
					addNext(x+i,y);
				}
			}		
			if(west > 0){
				for(int i = 1; i <= west; i++){
					addNext(x-i,y);
				}
			}
			if(northEast > 0){
				for(int i = 1; i <= northEast; i++){
					addNext(x+i,y-i);
				}
			}		
			if(northWest > 0){
				for(int i = 1; i <= northWest; i++){
					addNext(x-i,y-i);
				}
			}
			if(southEast > 0){
				for(int i = 1; i <= southEast; i++){
					addNext(x+i,y+i);
				}
			}		
			if(southWest > 0){
				for(int i = 1; i <= southWest; i++){
					addNext(x-i,y+i);
				}
			}	
		}
		else	//must skip, add final destinations only
		{	
			if(north > 0){
				addNext(x,y-north);
			}		
			if(south > 0){
				addNext(x,y+south);
			}
			if(east > 0){
				addNext(x+east,y);
			}		
			if(west > 0){
				addNext(x-west,y);
			}
			if(northEast > 0){
				addNext(x+northEast,y-northEast);
			}		
			if(northWest > 0){
				addNext(x-northWest,y-northWest);
			}
			if(southEast > 0){
				addNext(x+southEast,y+southEast);
			}		
			if(southWest > 0){
				addNext(x-southWest,y+southWest);
			}				
		}
	}

	//update destinations based on each direction with source location
	public void updateDestination(Coordinate source) {
		x = source.getX();
		y = source.getY();
		updateDestination();
	}

	//update movement by key
	/*public void updateMovement(Coordinate source, Movement newMovePattern){		
		destinations.clear();	//reset destinations
		x = source.getX();
		y = source.getY();
		north = newMovePattern.north;
		south = newMovePattern.south;		
		east = newMovePattern.east;		
		west = newMovePattern.west;		
		northEast = newMovePattern.northEast;
		northWest = newMovePattern.northWest;	
		southEast = newMovePattern.southEast;	
		southWest = newMovePattern.southWest;
		skip = newMovePattern.skip;
		updateNext();
	}*/
	
	//get destinations
	public ArrayList<Coordinate> getDestination() {
		return destinations;
	}
}

