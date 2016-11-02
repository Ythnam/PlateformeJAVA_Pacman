package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.Controller;
import model.Apple;
import model.Bell;
import model.Cherry;
import model.Chrono;
import model.Galboss;
import model.Ghost;
import model.Key;
import model.Melon;
import model.Model;
import model.Orange;
import model.Pacman;
import model.Strawberry;
import model.Map;


public class Field extends JPanel implements ActionListener{

	private double chrono = 0;
	Chrono chron = new Chrono();
	private Model model = new Model();
	private Controller controller;
	private int XMAX = this.model.getMap().getLongueur();
	private int YMAX = this.model.getMap().getHauteur()+1; // ici on a 10x10 cases soit 100 -> nombre � changer
	private int step = 20; // celui-la devra �tre �gal � la taille qu'on mettra pour une image -> ici : 64x64
	private Random random = new Random();
	private JLabel scoreLabel;
	private JLabel livesLabel;
	private JLabel timeLabel;
	private Timer timer;
	private JLabel label;
	

	public Field(){
		
		generatePacmanRandomly();
		generateGhostRandomly(3);
		//generateItemsRandomly(10, 5, 4, 3, 5, 1, 1, 1); // en test random pour les valeurs
		this.controller = defautController(this.model);
		this.controller.setView(this);
		this.addKeyListener(this.controller);
		
		
		scoreLabel = new JLabel("Score: " + this.model.getPacman().getPacmanScore());
		scoreLabel.setFont(new Font("Serif", Font.PLAIN, 10));
		scoreLabel.setForeground(Color.white);
		scoreLabel.setBackground(Color.BLACK);
		scoreLabel.setOpaque(true);
		add(scoreLabel);
		
		livesLabel = new JLabel(" " + "Lives: " + this.model.getPacman().getPacmanLives());
		livesLabel.setFont(new Font("Serif", Font.PLAIN, 10));
		livesLabel.setForeground(Color.white);
		livesLabel.setOpaque(true);
		livesLabel.setBackground(Color.BLACK);
		add(livesLabel);
		
		timeLabel = new JLabel("Timer: " + getChrono());
		Dimension d = new Dimension(85,15);
		timeLabel.setPreferredSize(d);
		timeLabel.setFont(new Font("Serif", Font.PLAIN, 10));
		timeLabel.setForeground(Color.white);
		timeLabel.setBackground(Color.BLACK);
		timeLabel.setOpaque(true);
		add(timeLabel);
		
		timer = new Timer(1, this);
		timer.start();
		chron.start();

	}
	
	public  Model getModel() {
		return model;
	}

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
    
    public void updateScoreAndLife() {
 	   scoreLabel.setText("Score: " + this.model.getPacman().getPacmanScore());
 	   livesLabel.setText(" " + "Lives: " + this.model.getPacman().getPacmanLives() );
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
	 * Cette fonction permet dans un premier temps de tester le programme en mettant des fantomes n'importe o�
	 * avant de les instancier � un endroit pr�cis
	 * @param n est le nombre de Ghost qu'on veut dans le model
	 */
	public void generateGhostRandomly(int n){
		for(int i = 0; i < n; i++){		
			Point p = generate();
				 
			Ghost ghost = new Ghost(p.x, p.y, this );
			
			this.model.addToAlGhost(ghost);
			new Thread(ghost).start();
			
		}
	}

	/**
	 * Cette fonction permet dans un premier temps de tester le programme en mettant le Pacman n'importe o�
	 * avant de l'instancier � un endroit pr�cis
	 * @param n est le nombre de Ghost qu'on veut dans le model
	 */
	public void generatePacmanRandomly(){
		boolean bool = true;
		boolean b2 = true;
		Point p = generate();
		
			Pacman pacman = Pacman.getInstance(p.x, p.y, this);
			this.model.setPacman(pacman);
		//new Thread(pacman).start();		
	}

	public Point generate(){
		boolean bo= true;
		int a = 0;
		int b = 0;
		while(bo){
			
				b=random.nextInt(YMAX-1)+1;
				a = random.nextInt(XMAX-1);
				if(canSpawn(a, b)) bo = false;
		}
		Point p = new Point(a,b);
		return p;
	}
	public boolean canSpawn(int x, int y){
		if(Map.getBol()[y-1][x]==true)
			return true;
		
		return false;
		
	}

	/**
	 * M�thode permettant d'instancier tous les Items en m�me temps
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
	 * M�thode permettant d'instancier le nombre d'apple qu'on veut dans le niveau
	 * @param n : le nombre d'apple voulu
	 */
	public void generateAppleRandomly(int n){
		for(int i = 0; i < n; i++){
			Apple apple = new Apple(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(apple);	
		}
	}

	/**
	 * M�thode permettant d'instancier le nombre de bell qu'on veut dans le niveau
	 * @param n : le nombre de bell voulu
	 */
	public void generateBellRandomly(int n){
		for(int i = 0; i < n; i++){
			Bell bell = new Bell(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(bell);	
		}
	}

	
	/**
	 * M�thode permettant d'instancier le nombre de cherry qu'on veut dans le niveau
	 * @param n : le nombre de cherry voulu
	 */
	public void generateCherryRandomly(int n){
		for(int i = 0; i < n; i++){
			Cherry cherry = new Cherry(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(cherry);	
		}	
	}

	
	/**
	 * M�thode permettant d'instancier le nombre de galboss qu'on veut dans le niveau
	 * @param n : le nombre de galboss voulu
	 */
	public void generateGalbossRandomly(int n){
		for(int i = 0; i < n; i++){
			Galboss galboss = new Galboss(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(galboss);	
		}
	}

	/**
	 * M�thode permettant d'instancier le nombre de key qu'on veut dans le niveau
	 * @param n : le nombre de key voulu
	 */
	public void generateKeyRandomly(int n){
		for(int i = 0; i < n; i++){
			Key key = new Key(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(key);	
		}
	}

	
	/**
	 * M�thode permettant d'instancier le nombre de melon qu'on veut dans le niveau
	 * @param n : le nombre de melon voulu
	 */
	public void generateMelonRandomly(int n){
		for(int i = 0; i < n; i++){
			Melon melon = new Melon(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(melon);	
		}
	}

	
	/**
	 * M�thode permettant d'instancier le nombre d'orange qu'on veut dans le niveau
	 * @param n : le nombre d'orange voulu
	 */
	public void generateOrangeRandomly(int n){
		for(int i = 0; i < n; i++){
			Orange orange = new Orange(random.nextInt(XMAX), random.nextInt(YMAX), this);
			model.addToAlItems(orange);
		}
	}

	
	/**
	 * M�thode permettant d'instancier le nombre de strawberry qu'on veut dans le niveau
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
				BufferedImage full= null;
				BufferedImage empty= null;
				 try {
					wall = ImageIO.read(new File("image/brique.png"));
					full = ImageIO.read(new File("image/mapFull.png"));
					empty = ImageIO.read(new File("image/map.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (int k=0;k<Map.getHauteur();k++){
					for (int l=0;l<Map.getLongueur();l++){
						if (Map.getTab()[k][l]=='0'){
							if(Map.getBol()[k][l]==true){
							g2.drawImage(full,l*step,(k+1)*step,null);
							}
							else{
								g2.drawImage(empty,l*step,(k+1)*step,null);
							}
						}
						else {
							g2.drawImage(wall,l*step,(k+1)*step,null);
						}
					}
				}
		
		
		g2.drawImage(Ghost.getGhostORANGE(), model.getAlGhost().get(0).getX()*step, model.getAlGhost().get(0).getY()*step, null);
		g2.drawImage(Ghost.getGhostRED(), model.getAlGhost().get(1).getX()*step, model.getAlGhost().get(1).getY()*step, null);
		g2.drawImage(Ghost.getGhostGREEN(), model.getAlGhost().get(2).getX()*step, model.getAlGhost().get(2).getY()*step, null);
		model.getPacman().getImageIcon().paintIcon(this, g2, model.getPacman().getX()*step, model.getPacman().getY()*step);
		
		
	}
	@Override
	public void actionPerformed (ActionEvent e){
		if(this.model.getMap().getCounter()!=0){
		chron.pause();
		setChrono(chron.getDureeSec()); // affichage en secondes
		chron.resume();
		updatetimer();
		}
	}

	public void pop(){
		JFrame frame = new JFrame();
		frame.setSize(300,300);
		frame.setResizable(true);
		frame.setLocation(300, 300);
		frame.setVisible(true);
		
		
		label = new JLabel("lkhjklnlknkln");
		label.setFont(new Font("Serif", Font.PLAIN, 24));
		label.setForeground(Color.white);
		label.setBackground(Color.BLACK);
		label.setOpaque(true);
		JPanel b2 = new JPanel();
		
		//frame.add(label);
		JPanel b3 = new JPanel();
		b3.add(label);
	    //Idem pour cette ligne
	    b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
	    b3.add(new JButton("Bouton 4"));
	    b3.add(new JButton("Bouton 5"));
	    b3.add(new JButton("Bouton 6"));

	    //JPanel b4 = new JPanel();
	    //On positionne maintenant ces trois lignes en colonne
	    //b4.setLayout(new BoxLayout(b4, BoxLayout.LINE_AXIS));
	    //b4.add(b3);
	    //b4.add(label);
	    //b4.setLocation(0, 0);
		//frame.add(b2);
	    frame.add(b3);

		// ok = new JButton("ok",BorderLayout.SOUTH);
		//ok.setText("ok");
		//ok.setBounds(100, 100, 100, 100);
		//JButton recommencer = new JButton();
		//JButton suivant = new JButton();
		
		//frame.add(ok);
		//frame.add(recommencer);
		//frame.add(suivant);
	}
	
	private void updatetimer() {
		timeLabel.setText("Time: " + getChrono());
	}

	public double getChrono() {
		return chrono;
	}

	public void setChrono(double chrono) {
		this.chrono = chrono;
	}

}
