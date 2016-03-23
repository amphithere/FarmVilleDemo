package Model;

import java.util.ArrayList;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.Timer;

public class Game extends Observable{
	private ArrayList<Animal> animals;
	private ArrayList<Animal> hungry;
	private ArrayList<Crop> crops;
	private Timer wheelOfTime;
	private int totalCorn = 0;
	private int totalWheat = 0;
	private int totalPotatoes = 0;

	public Game(){
		animals = new ArrayList<Animal>();
		hungry = new ArrayList<Animal>();
		crops = new ArrayList<Crop>();
		wheelOfTime = new Timer(1000, new TimerActionListener());
		wheelOfTime.start();
		Cow cow = new Cow();
		Cow cow2 = new Cow();
		Chicken chick = new Chicken();
		Chicken chick2 = new Chicken();
		Pig pig = new Pig();
		animals.add(pig);
		animals.add(cow);
		animals.add(chick);
		animals.add(cow2);
		animals.add(chick2);
	}

	private class TimerActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			hungry.clear();
			for (int i = 0; i < animals.size(); i++){
				if (animals.get(i).isDead()){
					killAnimal(animals.get(i));
				}
			}
			for (Animal a: animals){
				a.move();
			}
			
			for (Animal a: animals){
				if (a.isHungry()){
					hungry.add(a);
				}
			}
			setChanged();
			notifyObservers();
		}
	}

	private void killAnimal(Animal animal){
		animals.remove(animal);
	}

	public ArrayList<Animal> getAnimals() {
		return animals;
	}

	public ArrayList<Crop> getCrops(){
		return crops;
	}
	
	private void removeSpecificCrop(Crop crop){
		for (Crop c: crops){
			if (c.getClass().equals(crop.getClass())){
				crops.remove(c);
				return;
			}
		}
	}
	
	public void plant(Crop crop){
		if (crop.getClass().equals(Corn.class)){
			setTotalCorn(getTotalCorn() - 20);
			crops.add(crop);
		}
		else if (crop.getClass().equals(Wheat.class)){
			setTotalWheat(getTotalWheat() - 20);
			crops.add(crop);
		}
		else if (crop.getClass().equals(Potato.class)){
			setTotalPotatoes(getTotalPotatoes() - 20);
			crops.add(crop);
		}
		else{
			System.out.println("Can't plant that!");
			crops.add(crop);
		}
	}
	
	public void harvest(Crop crop){
		if (crop.getClass().equals(Corn.class)){
			crops.remove(crop);
			setTotalCorn(getTotalCorn() + 30);
		}
		else if (crop.getClass().equals(Wheat.class)){
			crops.remove(crop);
			setTotalWheat(getTotalWheat() + 30);
		}
		else if (crop.getClass().equals(Potato.class)){
			crops.remove(crop);
			setTotalPotatoes(getTotalPotatoes() + 30);
		}
		else{
			System.out.println("Can't harvest that!");
		}
	}
	
	public void feed(Animal a){
		a.feed();
		if (a.getClass().equals(Chicken.class)){
			setTotalCorn(getTotalCorn() - 10);
		}
		else if (a.getClass().equals(Cow.class)){
			setTotalWheat(getTotalWheat() - 10);
		}
		else{ // Pig
			setTotalPotatoes(getTotalPotatoes() - 10);
		}
	}

	public ArrayList<Animal> getHungryAnimals() {
		return hungry;
	}

	public int getTotalPotatoes() {
		return totalPotatoes;
	}

	public void setTotalPotatoes(int totalPotatoes) {
		this.totalPotatoes = totalPotatoes;
	}

	public int getTotalWheat() {
		return totalWheat;
	}

	public void setTotalWheat(int totalWheat) {
		this.totalWheat = totalWheat;
	}

	public int getTotalCorn() {
		return totalCorn;
	}

	public void setTotalCorn(int totalCorn) {
		this.totalCorn = totalCorn;
	}
}
