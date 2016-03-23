package Controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import Model.Animal;
import Model.Chicken;
import Model.Corn;
import Model.Cow;
import Model.Crop;
import Model.Game;
import Model.Pig;
import Model.Potato;
import Model.Wheat;

public class ViewController extends JPanel implements Observer{
	private Game game;
	private ArrayList<Animal> animals;
	private ArrayList<Crop> crops;
	private Image cow, chicken, pig, corn, wheat, potato, grass;
	private Animal selectedAnimal;

	// questionable
	public ViewController(){
		this.setVisible(true);
	}
	public ViewController(Game game){
		this.game = game;
		try {
			cow = ImageIO.read(new File("./images/cow.png"));
			chicken = ImageIO.read(new File("./images/chicken.png"));
			wheat = ImageIO.read(new File("./images/wheat.png"));
			corn = ImageIO.read(new File("./images/corn.png"));
			pig = ImageIO.read(new File("./images/pig.png"));
			potato = ImageIO.read(new File("./images/potato.png"));
			grass = ImageIO.read(new File("./images/grass.jpg"));
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public ArrayList<Animal> getAnimals(){
		return animals;
	}

	public void selectAnimal(Point p){
		for (Animal c: animals){
			if (c.getLocation().x == p.x/50 && c.getLocation().y == p.y/50){
				selectedAnimal = c;
				System.out.println(selectedAnimal);
			}
		}
	}

	public Animal getSelectedAnimal(){
		return selectedAnimal;
	}

	public void paintComponent(Graphics g){
		Graphics g2 = (Graphics2D) g;
		for (int r = 0; r < 13*50; r+=50){
			for(int c = 0; c < 11*50; c+=50){
				g2.drawImage(grass, r, c, null);
			}
		}
		for (Animal c: game.getAnimals()){
			if (c.getClass().equals(Chicken.class)){
				g2.drawImage(chicken, c.getLocation().x *50, c.getLocation().y*50, null);
			}
			else if(c.getClass().equals(Cow.class)){
				g2.drawImage(cow, c.getLocation().x *50, c.getLocation().y*50, null);
			}
			else if(c.getClass().equals(Pig.class)){
				g2.drawImage(pig, c.getLocation().x *50, c.getLocation().y*50, null);
			}
			else {
				System.out.println("Not an animal!");
			}
		}
		for (Crop crop: game.getCrops()){
			if (crop.getClass().equals(Corn.class)){
				g2.drawImage(corn, crop.getLocation().x *50, crop.getLocation().y*50, null);
			}
			else if(crop.getClass().equals(Wheat.class)){
				g2.drawImage(wheat, crop.getLocation().x *50, crop.getLocation().y*50, null);
			}
			else if(crop.getClass().equals(Potato.class)){
				g2.drawImage(potato, crop.getLocation().x *50, crop.getLocation().y*50, null);
			}
			else {
				System.out.println("Not an animal!");
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		game = (Game) o;
		animals = game.getAnimals();
		crops = game.getCrops();
		repaint();	
	}

}

