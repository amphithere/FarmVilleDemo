package Model;

import java.awt.Point;

public class Crop {
	private Point location;
	
	public Crop(Point location){
		int x = location.x/50;
		int y = location.y/50;
		this.location = new Point(x, y);
	}
	
	public Point getLocation(){
		return location;
	}
}
