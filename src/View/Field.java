package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import main.Main;
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

public class Field extends JPanel{

	private Model model = new Model();
	private Controller controller;
	private int XMAX = Main.longueur;
	private int YMAX = Main.hauteur+1; // ici on a 10x10 cases soit 100 -> nombre à changer
	private int step = 20; // celui-la devra être égal à la taille qu'on mettra pour une image -> ici : 64x64
	private Random random = new Random();
	

	public Field(){
		generatePacmanRandomly();
		generateGhostRandomly(3);
		//generateItemsRandomly(10, 5, 4, 3, 5, 1, 1, 1); // en test random pour les valeurs
		this.controller = defautController(this.model);
		this.controller.setView(this);
		this.addKeyListener(this.controller);
		

	}
    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
	
	/*public Field(int n, int apple, int bell, int cherry, int galboss, int key, int melon, int orange, int stawberry){
		generatePacmanRandomly();
		generateGhostRandomly(n);
		generateItemsRandomly(apple, bell, cherry, galboss, key, melon, orange, stawberry);
	}*/
	
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
			int a=random.nextInt(XMAX);
			int b=random.nextInt(YMAX);
			
			if(b>0){
				Ghost ghost = new Ghost(a, b, this );
			
			this.model.addToAlGhost(ghost);
			new Thread(ghost).start();
			}
			else i--;
		}
	}

	/**
	 * Cette fonction permet dans un premier temps de tester le programme en mettant le Pacman n'importe où
	 * avant de l'instancier à un endroit précis
	 * @param n est le nombre de Ghost qu'on veut dans le model
	 */
	public void generatePacmanRandomly(){
		boolean bool = true;
		int b = 0;
		while (bool){
			b=random.nextInt(YMAX);
			if(b>0) bool = false;
		}
		Pacman pacman = Pacman.getInstance(random.nextInt(XMAX), b, this);
		this.model.setPacman(pacman);
		//new Thread(pacman).start();		
	}


	/**
	 * Méthode permettant d'instancier tous les Items en même temps
	 * @param apple : le nombre d'apple qu'il y aura
	 * @param bell : le nombre de bell qu'il y aura
	 * @param cherry : le nombre de cherry qu'il y aura
	 * @param galboss : le nombre galboss qu'il y aura
	 * @param key : le nombre de key qu'il y aura
	 * @param melon : le nombre de melon qu'il y aura
	 * @param orange : le nombre d'orange qu'il y aura
	 * @param strawberry : le nombre de strawberry qu'il y aura
	 */
	public void generateItemsRandomly(int apple, int bell, int cherry, int galboss, int key, int melon, int orange, int strawberry){
		generateAppleRandomly(apple);
		generateBellRandomly(bell);
		generateCherryRandomly(cherry);
		generateGalbossRandomly(galboss);
		generateKeyRandomly(key);
		generateMelonRandomly(melon);
		generateOrangeRandomly(orange);
		generateStrawberryRandomly(strawberry);
	}

	/**
	 * Méthode permettant d'instancier le nombre d'apple qu'on veut dans le niveau
	 * @param n : le nombre d'apple voulu
	 */
	public void generateAppleRandomly(int n){
		for(int i = 0; i < n; i++){
			Apple apple = new Apple(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(apple);	
		}
	}

	/**
	 * Méthode permettant d'instancier le nombre de bell qu'on veut dans le niveau
	 * @param n : le nombre de bell voulu
	 */
	public void generateBellRandomly(int n){
		for(int i = 0; i < n; i++){
			Bell bell = new Bell(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(bell);	
		}
	}

	
	/**
	 * Méthode permettant d'instancier le nombre de cherry qu'on veut dans le niveau
	 * @param n : le nombre de cherry voulu
	 */
	public void generateCherryRandomly(int n){
		for(int i = 0; i < n; i++){
			Cherry cherry = new Cherry(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(cherry);	
		}	
	}

	
	/**
	 * Méthode permettant d'instancier le nombre de galboss qu'on veut dans le niveau
	 * @param n : le nombre de galboss voulu
	 */
	public void generateGalbossRandomly(int n){
		for(int i = 0; i < n; i++){
			Galboss galboss = new Galboss(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(galboss);	
		}
	}

	/**
	 * Méthode permettant d'instancier le nombre de key qu'on veut dans le niveau
	 * @param n : le nombre de key voulu
	 */
	public void generateKeyRandomly(int n){
		for(int i = 0; i < n; i++){
			Key key = new Key(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(key);	
		}
	}

	
	/**
	 * Méthode permettant d'instancier le nombre de melon qu'on veut dans le niveau
	 * @param n : le nombre de melon voulu
	 */
	public void generateMelonRandomly(int n){
		for(int i = 0; i < n; i++){
			Melon melon = new Melon(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(melon);	
		}
	}

	
	/**
	 * Méthode permettant d'instancier le nombre d'orange qu'on veut dans le niveau
	 * @param n : le nombre d'orange voulu
	 */
	public void generateOrangeRandomly(int n){
		for(int i = 0; i < n; i++){
			Orange orange = new Orange(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(orange);
		}
	}

	
	/**
	 * Méthode permettant d'instancier le nombre de strawberry qu'on veut dans le niveau
	 * @param n : le nombre de strawberry voulu
	 */
	public void generateStrawberryRandomly(int n){
		for(int i = 0; i < n; i++){
			Strawberry strawberry = new Strawberry(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(strawberry);
		}
	}
	
	public Controller defautController(Model model){
		return new Controller(model);
	}
	
	public void setModel(Model model) {
		this.model = model;
		this.controller.setModel(model);
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		// creation du niveau 
				BufferedImage wall= null;
				BufferedImage other= null;
				 try {
					wall = ImageIO.read(new File("image/brique.png"));
					other = ImageIO.read(new File("image/map.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int k=0;k<Main.hauteur;k++){
					for (int l=0;l<Main.longueur;l++){
						if (Main.tab[k][l]=='0'){
							g2.drawImage(other,l*step,(k+1)*step,null);
						}
						else {
							g2.drawImage(wall,l*step,(k+1)*step,null);
						}
					}
				}
		
		
		g2.drawImage(Ghost.getGhostORANGE(), model.getAlGhost().get(0).getX()*step, model.getAlGhost().get(0).getY()*step, null);
		g2.drawImage(Ghost.getGhostRED(), model.getAlGhost().get(1).getX()*step, model.getAlGhost().get(1).getY()*step, null);
		g2.drawImage(Ghost.getGhostGREEN(), model.getAlGhost().get(2).getX()*step, model.getAlGhost().get(2).getY()*step, null);
		//g2.drawImage(Pacman.getImageIcon(), model.getPacman().getX(), model.getPacman().getY(), null);
		model.getPacman().getImageIcon().paintIcon(this, g2, model.getPacman().getX()*step, model.getPacman().getY()*step);
		
		
	}
	

	

}
