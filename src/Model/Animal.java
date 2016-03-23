package Model;

import java.awt.Point;
import java.util.Random;

public abstract class Animal {
	private int hunger;
	private Point location;
	
	public Animal(){
		this.hunger = 100;
		this.location = new Point(0,0);
	}
	
	public void feed(){
		hunger += 10;
		System.out.println(getHunger());
		System.out.println("I've been fed!");
	}
	
	public abstract String getName();
	
	public int getHunger(){
		return hunger;
	}
	
	public Point getLocation(){
		return location;
	}
	
	public void move(){
		Random r = new Random();
		int newLocation = r.nextInt(4);
		if(newLocation == 0){
		   location.x = (location.x + 1 + 12) %12;
		   hunger--;
		}
		if(newLocation == 1){
			location.x = (location.x - 1 + 12) %12;
			hunger--;
		}
		if(newLocation == 2){
			location.y = (location.y + 1 + 10) %10;
			hunger--;
		}
		if(newLocation == 3){
			location.y = (location.y - 1+10) %10;
			hunger--;
		}
	}
	public boolean isHungry(){
		if (hunger <= 90){
			return true;
		}
		return false;
	}
	
	public boolean isFull(){
		if (hunger == 100){
			return true;
		}
		return false;
	}
	
	public boolean isDead(){
		if (hunger < 1){
			return true;
		}
		return false;
	}

}
