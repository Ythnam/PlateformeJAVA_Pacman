package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JPanel;

import model.Apple;
import model.Bell;
import model.Cherry;
import model.Galboss;
import model.Ghost;
import model.Key;
import model.Melon;
import model.Model;
import model.Orange;
import model.Pacman;
import model.Strawberry;

public class Field extends JPanel {

	private Model model = new Model();
	private int XMAX = 30;
	private int YMAX = 30; // ici on a 10x10 cases soit 100 -> nombre à changer
	private int step = 20; // celui-la devra être égal à la taille qu'on mettra pour une image -> ici : 64x64
	private Random random = new Random();

	public Field(){
		generatePacmanRandomly();
		generateGhostRandomly(3);
		//generateItemsRandomly(10, 5, 4, 3, 5, 1, 1, 1); // en test random pour les valeurs
	}
	
	public Field(int n, int apple, int bell, int cherry, int galboss, int key, int melon, int orange, int stawberry){
		generatePacmanRandomly();
		generateGhostRandomly(n);
		generateItemsRandomly(apple, bell, cherry, galboss, key, melon, orange, stawberry);
	}
	
	public int getYMAX() {
		return this.YMAX;
	}
	
	public int getXMAX() {
		return this.XMAX;
	}
	
	public int getStep() {
		return this.step;
	}
	
	public void setYMAX(int yMAX) {
		this.YMAX = yMAX;
	}
	
	public void setXMAX(int xMAX) {
		this.XMAX = xMAX;
	}
	
	public void setStep(int step) {
		this.step = step;
	}

	/**
	 * Cette fonction permet dans un premier temps de tester le programme en mettant des fantomes n'importe où
	 * avant de les instancier à un endroit précis
	 * @param n est le nombre de Ghost qu'on veut dans le model
	 */
	public void generateGhostRandomly(int n){
		for(int i = 0; i < n; i++){
			Ghost ghost = new Ghost(random.nextInt(XMAX), random.nextInt(YMAX), this );
			model.addToAlGhost(ghost);
			new Thread(ghost).start();
		}
	}

	/**
	 * Cette fonction permet dans un premier temps de tester le programme en mettant le Pacman n'importe où
	 * avant de l'instancier à un endroit précis
	 * @param n est le nombre de Ghost qu'on veut dans le model
	 */
	public void generatePacmanRandomly(){
		Pacman pacman = Pacman.getInstance(random.nextInt(XMAX), random.nextInt(YMAX), this);
		model.setPacman(pacman);
		new Thread(pacman).start();		
	}


	public void generateItemsRandomly(int apple, int bell, int cherry, int galboss, int key, int melon, int orange, int stawberry){
		generateAppleRandomly(apple);
		generateBellRandomly(bell);
		generateCherryRandomly(cherry);
		generateGalbossRandomly(galboss);
		generateKeyRandomly(key);
		generateMelonRandomly(melon);
		generateOrangeRandomly(orange);
		generateStrawberryRandomly(stawberry);
	}

	public void generateAppleRandomly(int n){
		for(int i = 0; i < n; i++){
			Apple apple = new Apple(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(apple);	
		}
	}

	public void generateBellRandomly(int n){
		for(int i = 0; i < n; i++){
			Bell bell = new Bell(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(bell);	
		}
	}

	public void generateCherryRandomly(int n){
		for(int i = 0; i < n; i++){
			Cherry cherry = new Cherry(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(cherry);	
		}	
	}

	public void generateGalbossRandomly(int n){
		for(int i = 0; i < n; i++){
			Galboss galboss = new Galboss(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(galboss);	
		}
	}

	public void generateKeyRandomly(int n){
		for(int i = 0; i < n; i++){
			Key key = new Key(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(key);	
		}
	}

	public void generateMelonRandomly(int n){
		for(int i = 0; i < n; i++){
			Melon melon = new Melon(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(melon);	
		}
	}

	public void generateOrangeRandomly(int n){
		for(int i = 0; i < n; i++){
			Orange orange = new Orange(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(orange);
		}
	}

	public void generateStrawberryRandomly(int n){
		for(int i = 0; i < n; i++){
			Strawberry strawberry = new Strawberry(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(strawberry);
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(Ghost.getGhostORANGE(), model.getAlGhost().get(0).getX()*step, model.getAlGhost().get(0).getY()*step, null);
		g2.drawImage(Ghost.getGhostRED(), model.getAlGhost().get(1).getX()*step, model.getAlGhost().get(1).getY()*step, null);
		g2.drawImage(Ghost.getGhostGREEN(), model.getAlGhost().get(2).getX()*step, model.getAlGhost().get(2).getY()*step, null);
		//g2.drawImage(Pacman.getImageIcon(), model.getPacman().getX(), model.getPacman().getY(), null);
		model.getPacman().getImageIcon().paintIcon(this, g2, model.getPacman().getX()*step, model.getPacman().getY()*step);
	}

}
