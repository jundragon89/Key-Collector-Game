//Class that defines the key
public class Key extends Identity{
	
	//content
	private Movement movePattern;		//movement pattern of key
	private String description;			//description of key
	
	//constructor
	public Key(Identity identity, Movement movePattern, String description)
	{
		super(identity);
		this.movePattern = movePattern;
		this.description = description;
	}

	//getter
	public Movement getMovePattern() {
		return movePattern;
	}
	
	public String getDescription() {
		return description;
	}
	
	//setter
	public void setMovePattern(Movement movePattern) {
		this.movePattern = movePattern;
	}
	
	public void setDesciption(String description) {
		this.description = description;
	}	
	
}
