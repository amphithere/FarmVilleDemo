package View;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import Model.*;
import Controller.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FarmVilleGUI extends JFrame implements Observer{
	private JPanel main = new JPanel();
	private ViewController display;
	private Game game;
	private JButton btnFeed = new JButton();
	private ArrayList<Animal> animals;
	private ArrayList<Animal> hungryAnimals;
	private ArrayList<Crop> harvestables;
	private JComboBox<Animal> hungry;
	private DefaultComboBoxModel<Animal> hungryAn;
	private JComboBox<String> crops;
	private Animal selectedAnimal;
	private String selectedPlant;
	private JButton btnPlant;
	private Point clicked;
	private JButton btnHarvest;
	String totalCorn, totalWheat, totalPotatoes;
	private JLabel lblTotalCorn;
	private JLabel lblTotalWheat;
	private JLabel lblTotalPotatoes;
	private JLabel lblCorn;
	private JLabel lblWheat;
	private JLabel lblPotatoes;
	
	public static void main(String[] args){
		new FarmVilleGUI().setVisible(true);
	}
	public FarmVilleGUI(){
		initGUI();
	}

	public void initGUI(){
		setSize(850, 630);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		game = new Game();
		getContentPane().add(main);
		main.setLayout(null);
		display = new ViewController(game);
		display.setLocation(33, 32);
		main.add(display);
		display.setSize(627,532);
		btnFeed.setText("Feed");
		btnFeed.setBounds(683, 147, 93, 29);
		main.add(btnFeed);

		btnPlant = new JButton("Plant");
		btnPlant.setBounds(690, 254, 86, 29);
		main.add(btnPlant);

		crops = new JComboBox<String>();
		crops.setBounds(672, 225, 122, 22);
		crops.addItem("Corn");
		crops.addItem("Wheat");
		crops.addItem("Potato");
		main.add(crops);

		hungryAn = new DefaultComboBoxModel<Animal>();
		hungry = new JComboBox<Animal>(hungryAn);
		hungry.setBounds(666, 119, 128, 24);
		main.add(hungry);
		btnHarvest = new JButton("Harvest");
		btnHarvest.setBounds(672, 312, 117, 29);
		main.add(btnHarvest);

		JLabel lblPlantCrops = new JLabel("Plant Crops");
		lblPlantCrops.setBounds(693, 197, 116, 16);
		main.add(lblPlantCrops);

		JLabel lblFeedAnimals = new JLabel("Feed Animals");
		lblFeedAnimals.setBounds(683, 92, 93, 16);
		main.add(lblFeedAnimals);
		
		lblTotalCorn = new JLabel("Total Corn:");
		lblTotalCorn.setBounds(666, 374, 75, 16);
		main.add(lblTotalCorn);
		
		lblTotalWheat = new JLabel("Total Wheat:");
		lblTotalWheat.setBounds(666, 415, 86, 16);
		main.add(lblTotalWheat);
		
		lblTotalPotatoes = new JLabel("Total Potatoes:");
		lblTotalPotatoes.setBounds(666, 454, 94, 16);
		main.add(lblTotalPotatoes);
		
		lblCorn = new JLabel(totalCorn);
		lblCorn.setBounds(745, 374, 36, 16);
		main.add(lblCorn);
		
		lblWheat = new JLabel(totalWheat);
		lblWheat.setBounds(748, 415, 46, 16);
		main.add(lblWheat);
		
		lblPotatoes = new JLabel(totalPotatoes);
		lblPotatoes.setBounds(765, 454, 44, 16);
		main.add(lblPotatoes);
		addObservers();
		registerListeners();
		this.setFocusable(true);
	}

	public void registerListeners(){
		display.addMouseListener(new UserClickListener());
		btnFeed.addActionListener(new ButtonListener());
		hungry.addActionListener(new HungrySelectListener());
		crops.addActionListener(new PlantSelectListener());
		btnPlant.addActionListener(new PlantListener());
		btnHarvest.addActionListener(new HarvestListener());
	}

	public void addObservers(){
		game.addObserver(display);
		game.addObserver(this);
	}

	private class PlantListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(selectedPlant);
			if (clicked != null && selectedPlant != null){
				switch(selectedPlant){
				case "Corn":
					game.plant(new Corn(clicked));
					break;
				case "Wheat":
					game.plant(new Wheat(clicked));
					break;
				case "Potato":
					game.plant(new Potato(clicked));
					break;
				default:
					break;
				}
				System.out.println(clicked);
			}
			else {
				JOptionPane.showMessageDialog(main, "Choose somewhere!");
			}
		}

	}

	private class HungrySelectListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			selectedAnimal = (Animal) hungry.getSelectedItem();
		}

	}
	private class HarvestListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (clicked != null){
				Point clicked2 = new Point(clicked.x/50, clicked.y/50);
				for (Crop c: harvestables){
					if (c.getLocation().x <= (clicked2.x-50) && c.getLocation().y <= (clicked2.y-50) || c.getLocation().x <= (clicked2.x+50) && c.getLocation().y <= (clicked2.y+50)){
						game.harvest(c);
					}
				}
			}
		}
		
	}
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(selectedAnimal != null){
				game.feed(selectedAnimal);
			}
			else {
				JOptionPane.showMessageDialog(main, "Choose an animal!");
			}
		}
	}

	private class PlantSelectListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			selectedPlant = (String)crops.getSelectedItem();
			System.out.println(selectedPlant);
		}

	}

	private class UserClickListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			clicked = e.getPoint();
			System.out.println(clicked);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public void updateLists(){
		hungry.removeAllItems();
		hungryAn.removeAllElements();
		for (Animal h: hungryAnimals){
			if (hungryAn.getIndexOf(h) < 0){
				hungry.addItem(h);
			}
		}
	}


	@Override
	public void update(Observable o, Object arg) {
		game = (Game) o;
		animals = game.getAnimals();	
		hungryAnimals = game.getHungryAnimals();
		harvestables = game.getCrops();
		totalCorn = String.valueOf(game.getTotalCorn());
		totalWheat = String.valueOf(game.getTotalWheat());
		totalPotatoes = String.valueOf(game.getTotalPotatoes());
		lblCorn.setText(totalCorn);
		lblWheat.setText(totalWheat);
		lblPotatoes.setText(totalPotatoes);
		updateLists();
	}
}
