import java.io.File;
import javax.swing.ImageIcon;

//Class that define identity (name,icon,location)
public class Identity {

	//content
	private String name;			//name
	private ImageIcon icon;			//icon
	private Coordinate location;	//coordinate of location

	//constructor with name and icon
	public Identity(String name, ImageIcon icon) {
		this.name = name;
		this.icon = icon;
	}	

	//constructor with name and path
	public Identity(String name, String path) {
		this.name = name;
		if(fileCheck(path)) {
			this.icon = new ImageIcon(path);
		}
	}
	
	//constructor with name, icon and location
	public Identity(String name, ImageIcon icon, Coordinate location) {
		this.name = name;
		this.icon = icon;
		this.location = location;
	}
	
	//constructor with name, path of icon and location
	public Identity(String name, String path, Coordinate location) {
		this.name = name;
		this.location = location;	
		if(fileCheck(path)) {
			this.icon = new ImageIcon(path);
		}
	}
	
	//copy constructor
	public Identity(Identity newIdentity) {
		name = newIdentity.name;
		location = newIdentity.location;	
		icon = newIdentity.icon;
	}
	
	//file check function
	public Boolean fileCheck(String path) {
		File newFile = new File(path);
		if(newFile.exists()){
			return true;
		}
	    else {
	    	System.out.println("Error: Image file in '" + path + "' not found!");
	    	return false;
	    }	
	}
	
	//setter
	public void setName(String name) {
		this.name = name;
	}
	
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}	
	
	public void setIcon(String path) {
		if(fileCheck(path)) {
			this.icon = new ImageIcon(path);
		}
	}

	public void setCoordinate(Coordinate location) {
		this.location = location;
	}
	
	//getter
	public final String getName() {
		return name;
	}
	
	public final ImageIcon getIcon() {
		return icon;
	}		
	
	public final Coordinate getLocation() {
		return location;
	}
}

