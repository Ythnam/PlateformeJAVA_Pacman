package model;

import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import View.Field;

public class Pacman {

	private static Pacman instance; // Pacman est un singleton
	
	public static BufferedImage img;
	private int x, y;
	private Field field;
	private boolean isPowerUp = false; // booleen pour le faire entrer ne �tat ou il bas les fantomes
	private Random rand = new Random(); // Sert juste pour les test tant qu'on n'a pas le clavier
	private ImageIcon imageIcon;
	private static ImageIcon imageIconTop = new ImageIcon("image/pacman-haut.gif");
	private static ImageIcon imageIconBot = new ImageIcon("image/pacman_bas.gif");
	private static ImageIcon imageIconLeft = new ImageIcon("image/pacman_gauche.gif");
	private static ImageIcon imageIconRight = new ImageIcon("image/pacman_droit.gif");
	private boolean onPause = false;
	 private long pacmanScore = 0;
	 private int pacmanLives = 3;

	 public void setImageIcon(ImageIcon imageIcon){
		 this.imageIcon = imageIcon;
	 }
	
	public ImageIcon getImageIcon() {
		return imageIcon;
	}
	
	public static ImageIcon getImageIconTop() {
		return imageIconTop;
	}
	
	public static ImageIcon getImageIconBot() {
		return imageIconBot;
	}
	
	public static ImageIcon getImageIconLeft() {
		return imageIconLeft;
	}
	
	public static ImageIcon getImageIconRight() {
		return imageIconRight;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	
	
	// Ce code pour le singleton a �t� r�cup�r� sur le site suivant :
	//http://christophej.developpez.com/tutoriel/java/singleton/multithread/
	/**
	 * C'est une m�thode n�cessaire pour instancier un singleton
	 * @param x la coordonn�e x du pacman � l'instanciation
	 * @param y la coordonn�e y du pacman � l'instanciation
	 * @param field le field sur lequel il agira
	 * @return
	 */
	public static Pacman getInstance(int x, int y, Field field) {
        if (null == instance) { // Premier appel
            synchronized(Pacman.class) {
                    instance = new Pacman(x, y, field);
            }
        }
        return instance;
    }
	
	private Pacman(int x, int y, Field field){
		
		this.x = x;
		this.y = y;
		this.field = field;
		this.imageIcon = getImageIconTop(); 
	}


	public void goLeft(){
		if(!isOnPause()){
		if(this.x>0){
			if(Map.getTab()[y][x-1]!='1'){
				if(Map.getBol()[y][x-1] == true){ pacmanScore+=100 ;this.field.updateScoreAndLife();this.field.getModel().getMap().setCounter(this.field.getModel().getMap().getCounter() - 1);}
				Map.getBol()[y][x-1]=false;
				this.x--;
			}
			
		}
		else {
			if(Map.getTab()[y-1][this.field.getXMAX()-1]!='1'){
				if(Map.getBol()[y-1][this.field.getXMAX()-1] == true){ pacmanScore+=100 ;this.field.updateScoreAndLife();this.field.getModel().getMap().setCounter(this.field.getModel().getMap().getCounter() - 1);}
				Map.getBol()[y-1][this.field.getXMAX()-1]=false;
				this.x = this.field.getXMAX()-1;
			}
		}
		}
	}
	

	public void goRight(){
		if(!isOnPause()){
		if(this.x<this.field.getXMAX()-1){
			
			if(Map.getTab()[y][x+1]!='1'){
				if(Map.getBol()[y][x+1] == true){ pacmanScore+=100 ;this.field.updateScoreAndLife();this.field.getModel().getMap().setCounter(this.field.getModel().getMap().getCounter() - 1);}
				Map.getBol()[y][x+1]=false;
				this.x++;
			}
		}
		else {
			if(Map.getTab()[y][0]!='1'){
				if(Map.getBol()[y][0] == true){ pacmanScore+=100 ;this.field.updateScoreAndLife();this.field.getModel().getMap().setCounter(this.field.getModel().getMap().getCounter() - 1);}
				Map.getBol()[y][0]=false;
				this.x=0;
			}
		}
		}
	}
	
	public void goBot(){
		if(!isOnPause()){
		if(this.y < this.field.getYMAX()-1){
			if(Map.getTab()[y+1][x]!='1'){
				if(Map.getBol()[y+1][x] == true) { pacmanScore+=100 ;this.field.updateScoreAndLife();this.field.getModel().getMap().setCounter(this.field.getModel().getMap().getCounter() - 1);}
				Map.getBol()[y+1][x]= false;
				
				this.y++;
			}
		}
		else {
			if(Map.getTab()[0][x]!='1'){
				if(Map.getBol()[0][x] == true) { pacmanScore+=100 ;this.field.updateScoreAndLife();this.field.getModel().getMap().setCounter(this.field.getModel().getMap().getCounter() - 1);}
				Map.getBol()[0][x]=false;
				this.y=0;
			}
		}
		}
	}
	
	public void goTop(){
		if(!isOnPause()){
		if(this.y> 0){
			if(Map.getTab()[y-1][x]!='1'){
				if(Map.getBol()[y-1][x] == true){ pacmanScore+=100 ;this.field.updateScoreAndLife();this.field.getModel().getMap().setCounter(this.field.getModel().getMap().getCounter() - 1);}
				Map.getBol()[y-1][x]=false;
				this.y--;
			}
		}
		else {
			if(Map.getTab()[(this.field.getYMAX())-1][x]!='1'){
				if(Map.getBol()[this.field.getYMAX()-1][x] == true) { pacmanScore+=100 ;this.field.updateScoreAndLife();this.field.getModel().getMap().setCounter(this.field.getModel().getMap().getCounter() - 1);}
				Map.getBol()[this.field.getYMAX()-1][x]=false;
			this.y = this.field.getYMAX()-1;
			}
		}
		}
	}
	
	

	public long getPacmanScore() {
		return pacmanScore;
}

	public void setPacmanScore(long pacmanScore) {
		this.pacmanScore = pacmanScore;
	}

	

	public int getPacmanLives() {
		return pacmanLives;
	}

	public void setPacmanLives(int pacmanLives) {
		this.pacmanLives = pacmanLives;
	}

	public boolean isOnPause() {
		return onPause;
	}

	public void setOnPause(boolean onPause) {
		this.onPause = onPause;
	}

	public void setX( int x) {
		// TODO Auto-generated method stub
		this.x = x;	
	}
	
	public void setY(int y){
		this.y = y;
	}


}