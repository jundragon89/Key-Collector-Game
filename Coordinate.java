//Class that define coordinate x and y
public class Coordinate {

	//content
	private int x;
	private int y;
	
	//default constructor
	public Coordinate() {
	}
	
	//constructor
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//setter methods
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	//getter methods
	public final int getX() {
		return x;
	}
	
	public final int getY() {
		return y;
	}
	
	//display coordinate info
	public final String getInfo() {
		String info = "x:" + x + ",y:" + y;
		return info;
	}

}

