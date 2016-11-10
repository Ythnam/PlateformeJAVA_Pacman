package View;

import java.awt.Color;
import java.awt.Container;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.Timer;

import controller.Controller;
import main.Main;
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
	private Chrono chron = new Chrono();
	private Model model = new Model();
	private Controller controller;
	private int XMAX = this.model.getMap().getLongueur();
	private int YMAX = this.model.getMap().getHauteur(); // ici on a 10x10 cases soit 100 -> nombre à changer
	private int step = 20; // celui-la devra être égal à la taille qu'on mettra pour une image -> ici : 64x64
	private Random random = new Random();
	private JLabel scoreLabel;
	private JLabel livesLabel;
	private JLabel timeLabel;
	private Timer timer;
	private JLabel label;
	public Container conten = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	TestPanel newPanel;
	JButton end;
    JButton retry;
    JButton nextlvl;
    JFrame frame = new JFrame();
    JFrame test = new JFrame();
  
	
	public Field(JFrame fram){
		this.test = fram;
		generatePacmanRandomly();
		generateGhostRandomly(3);
		//generateItemsRandomly(10, 5, 4, 3, 5, 1, 1, 1); // en test random pour les valeurs
		this.setController(defautController(this.model));
		this.getController().setView(this);
		this.addKeyListener(this.getController());
		
		
		JPanel Panel = new JPanel();
		
		
		scoreLabel = new JLabel( this.model.getPacman().getPacmanScore()+"");
		scoreLabel.setFont(new Font("Serif", Font.PLAIN, 11));
		scoreLabel.setForeground(Color.black);
		//scoreLabel.setBackground(Color.BLACK);
		scoreLabel.setOpaque(true);
		Panel.add(scoreLabel);
		
		livesLabel = new JLabel("Lives: " + this.model.getPacman().getPacmanLives());
		livesLabel.setFont(new Font("Serif", Font.PLAIN, 11));
		livesLabel.setForeground(Color.black);
		livesLabel.setOpaque(true);
		//livesLabel.setBackground(Color.BLACK);
		Panel.add(livesLabel);
		
		timeLabel = new JLabel(getChrono()+"s");
		Dimension d = new Dimension(85,14);
		timeLabel.setPreferredSize(d);
		timeLabel.setFont(new Font("Serif", Font.PLAIN, 11));
		timeLabel.setForeground(Color.black);
	//	timeLabel.setBackground(Color.BLACK);
		timeLabel.setOpaque(true);
		Panel.add(timeLabel);
		
		conten.add(Panel);
		

		this.newPanel = new TestPanel();
		Dimension preferredSize = new Dimension(YMAX*step,XMAX*step);
		newPanel.setPreferredSize(preferredSize );
		conten.add(newPanel);
		
		add(conten);
		
		timer = new Timer(1, this);
		timer.start();
		getChron().start();
		
		
		//((RootPaneContainer)this).setContentPane(newPanel);
	}
	
	public  Model getModel() {
		return model;
	}
	
	public void updatevalues(){
		  XMAX = this.model.getMap().getLongueur();
		 YMAX = this.model.getMap().getHauteur(); 
	}

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }
    
    public void updateScoreAndLife() {
 	   scoreLabel.setText(this.model.getPacman().getPacmanScore()+"");
 	   livesLabel.setText("Lives: " + this.model.getPacman().getPacmanLives() );
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
			
				 
			Ghost ghost = new Ghost(this.model.getMap().getSpawnGhost().y, this.model.getMap().getSpawnGhost().x, this );
			
			this.model.addToAlGhost(ghost);
			new Thread(ghost).start();
			
		}
	}

	/**
	 * Cette fonction permet dans un premier temps de tester le programme en mettant le Pacman n'importe où
	 * avant de l'instancier à un endroit précis
	 * @param n est le nombre de Ghost qu'on veut dans le model
	 */
	public void generatePacmanRandomly(){
		
Pacman pacman = new Pacman(this.model.getMap().getSpawnPacman().y, this.model.getMap().getSpawnPacman().x, this);
			this.model.setPacman(pacman);
		new Thread(pacman).start();		
	}

	public Point generate(){
		boolean bo= true;
		int a = 0;
		int b = 0;
		while(bo){
			
				b=random.nextInt(YMAX-1);
				a = random.nextInt(XMAX-1);
				if(canSpawn(a, b)) bo = false;
		}
		Point p = new Point(a,b);
		return p;
	}
	public boolean canSpawn(int x, int y){
		if(Map.getBol()[y][x]==true)
			return true;
		
		return false;
		
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
		this.getController().setModel(model);
		
	}
	
	@Override
	public void actionPerformed (ActionEvent e){
		if(!getChron().isOnPause()){
			
			//maj du chrono et du label associé
			if(this.model.getMap().getCounter()!=0){
				getChron().pause();
				setChrono(getChron().getDureeSec()); // affichage en secondes
				getChron().resume();
				updatetimer();
			}
		
		}else {
		//	if(b){
				getChron().gamepause();	
			//	b=false;
		//	}
			
		}
		
		
	        Object  source=e.getSource();
	        
	        if  (source==end)
	            end();
	        else if (source==retry)
	            retry();
	        else if (source == nextlvl)
	        	nextlvl();
	}

	@SuppressWarnings("deprecation")
	public void nextlvl() {
		System.out.println("next lvl");
		chrono = 0;
		this.model.getPacman().setPacmanScore(0);
		if(this.model.getLvl() == 1) this.model.setLvl(this.model.getLvl()+1);
		else  this.model.setLvl(this.model.getLvl()-1);
		
		this.model.updatefichier();
		System.out.println(this.model.getFichier());
		this.model.lecture();
		this.model.createstring();
		updatevalues();
		System.out.println(XMAX+""+ YMAX);
		//(Field)this = new Field();
		//JFrame f = new JFrame();
		//this.newPanel = new TestPanel();
		Dimension preferredSize = new Dimension(XMAX*step,YMAX*step);
		this.newPanel.setPreferredSize(preferredSize );
		test.pack();
		this.model.getPacman().setX(this.model.getMap().getSpawnPacman().y);
		this.model.getPacman().setY(this.model.getMap().getSpawnPacman().x);
		
		for (Ghost g : this.model.getAlGhost()){
			g.setX(this.model.getMap().getSpawnGhost().y);
			g.setY(this.model.getMap().getSpawnGhost().x);
		}
		
		
		//this.model.setPacman(Pacman.getInstance(this.model.getMap().getSpawnPacman().y, this.model.getMap().getSpawnPacman().x, this));
		/*updateScoreAndLife();
		updatetimer()
		frame.dispose();
		frame = new JFrame();
		this.chron.restart();
		this.chron.setOnPause(true);
		this.getController().gamePause();*/
	}

	private void retry() {
		//endbool = true;
		System.out.println("retry");
		chrono = 0;
		this.model.getPacman().setPacmanScore(0);
		this.model.getPacman().setPacmanLives(3);;
		this.model.lecture();
		this.model.createstring();
		this.model.getPacman().setX(this.model.getMap().getSpawnPacman().y);
		this.model.getPacman().setY(this.model.getMap().getSpawnPacman().x);
		
		for (Ghost g : this.model.getAlGhost()){
			g.setX(this.model.getMap().getSpawnGhost().y);
			g.setY(this.model.getMap().getSpawnGhost().x);
		}
		//this.model.setPacman(Pacman.getInstance(this.model.getMap().getSpawnPacman().y, this.model.getMap().getSpawnPacman().x, this));
		updateScoreAndLife();
		updatetimer();
		frame.dispose();
		frame = new JFrame();
		this.chron.restart();
		this.chron.setOnPause(true);
		this.getController().gamePause();
	}

	private void end() {
		frame.dispose();
		frame = new JFrame();
	}

	public void popLooseLife(){
		if(this.model.getPacman().getPacmanLives()!=0){
		int input = JOptionPane.showOptionDialog(null, "Vous avez perdu une vie, ok pour continuer ", "Informations", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
		
		if(input == 0)
		{
			this.model.getPacman().setX(this.model.getMap().getSpawnPacman().y);
			this.model.getPacman().setY(this.model.getMap().getSpawnPacman().x);
			
			for (Ghost g : this.model.getAlGhost()){
				g.setX(this.model.getMap().getSpawnGhost().y);
				g.setY(this.model.getMap().getSpawnGhost().x);
			}
			
		    this.getController().gamePause();
		}
		}
	}
	
	public void popLooseGame(){
		int input = JOptionPane.showOptionDialog(null, "Vous n'avez plus de vie la partie est terminee", "Game Over", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
		
		if(input == 0)
		{
			try {
				this.getController().savescore();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//JOptionPane.showM		Dialog(newPanel, "Vous avez perdu une vie, cliquez pour continuer");
	}
		
	public void popClassement(){
		
		frame.setSize(300,300);
		frame.setResizable(true);
		frame.setLocation(300, 300);
		frame.setVisible(true);
		
		Container contenu = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		
		
		label = new JLabel(getController().getClassementhtml());
		label.setFont(new Font("Serif", Font.PLAIN, 18));
		label.setForeground(Color.BLACK);
		label.setOpaque(true);
		JPanel textPanel = new JPanel();
		frame.getContentPane().add(contenu);

		JPanel buttonPanel = new JPanel();
		textPanel.add(label);
	    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		
		buttonPanel.add(end =new JButton("Terminer"));

		buttonPanel.add(retry = new JButton("Recommencer"));
	    
		buttonPanel.add(nextlvl = new JButton("Niveau Suivant"));

	    

	    contenu.add(textPanel);
	    contenu.add(buttonPanel);
	    frame.pack();
	    
	    end.addActionListener(this);
	    retry.addActionListener(this);
	    nextlvl.addActionListener(this);

	}
	
	private void updatetimer() {
		timeLabel.setText(getChrono()+"s");
	}

	public double getChrono() {
		return chrono;
	}

	public void setChrono(double chrono) {
		this.chrono = chrono;
	}

	public Chrono getChron() {
		return chron;
	}

	public void setChron(Chrono chron) {
		this.chron = chron;
	}

	/*public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
	*/

	
	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	private class TestPanel extends JPanel {
		
		public TestPanel(){
			
		}
		
		public void paintComponent(Graphics g){
			
			
			
			Graphics2D g2 = (Graphics2D) g;
			super.paintComponent(g2);
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
							if (Map.getTab()[k][l]=='0'||Map.getTab()[k][l]=='2'||Map.getTab()[k][l]=='3'){
								if(Map.getBol()[k][l]==true){
								g2.drawImage(full,l*step,k*step,null);
								}
								else{
									g2.drawImage(empty,l*step,k*step,null);
								}
							}
							else {
								g2.drawImage(wall,l*step,k*step,null);
							}
						}
					}
			
			
			g2.drawImage(Ghost.getGhostORANGE(), model.getAlGhost().get(0).getX()*step, model.getAlGhost().get(0).getY()*step, null);
			g2.drawImage(Ghost.getGhostRED(), model.getAlGhost().get(1).getX()*step, model.getAlGhost().get(1).getY()*step, null);
			g2.drawImage(Ghost.getGhostGREEN(), model.getAlGhost().get(2).getX()*step, model.getAlGhost().get(2).getY()*step, null);
			model.getPacman().getImageIcon().paintIcon(newPanel, g2, model.getPacman().getX()*step, model.getPacman().getY()*step);
			
			
		}
	}
	
	
	
}
