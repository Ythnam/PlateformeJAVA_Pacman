package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.Main;
import View.Field;

public class Pacman {

	private static Pacman instance; // Pacman est un singleton
	
	public static BufferedImage img;
	private int x, y;
	private Field field;
	private boolean isPowerUp = false; // booleen pour le faire entrer ne état ou il bas les fantomes
	private Random rand = new Random(); // Sert juste pour les test tant qu'on n'a pas le clavier
	public static ImageIcon imageIcon = new ImageIcon("image/pacman-haut.gif");
	 private final JLabel scoreLabel; 
	 private final JLabel livesLabel;
	 private long pacmanScore;
	 private int pacmanLives;
	
	public static ImageIcon getImageIcon() {
		return imageIcon;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	
	
	// Ce code pour le singleton a été récupéré sur le site suivant :
	//http://christophej.developpez.com/tutoriel/java/singleton/multithread/
	/**
	 * C'est une méthode nécessaire pour instancier un singleton
	 * @param x la coordonnée x du pacman à l'instanciation
	 * @param y la coordonnée y du pacman à l'instanciation
	 * @param field le field sur lequel il agira
	 * @return
	 */
	public static Pacman getInstance(int x, int y, Field field) {
        if (null == instance) { // Premier appel
            synchronized(Pacman.class) {
                    instance = new Pacman(x, y, field,0,3);
            }
        }
        return instance;
    }
	
	private Pacman(int x, int y, Field field,long score,int lives){
		
		this.x = x;
		this.y = y;
		this.field = field;
		/*try {
			wall();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		this.pacmanScore =score;
		this.pacmanLives =lives;
		scoreLabel = new JLabel("Score: " + pacmanScore);
		scoreLabel.setFont(new Font("Serif", Font.PLAIN, 10));
		scoreLabel.setForeground(Color.white);
		scoreLabel.setBackground(Color.BLACK);
		scoreLabel.setOpaque(true);
		this.field.add(scoreLabel);
		
		livesLabel = new JLabel(" " + "Lives: " + pacmanLives);
		livesLabel.setFont(new Font("Serif", Font.PLAIN, 10));
		livesLabel.setForeground(Color.white);
		livesLabel.setOpaque(true);
		livesLabel.setBackground(Color.BLACK);
		this.field.add(livesLabel);
		
		
	}
	



	public void goLeft(){
		if(this.x>0){
			if(Main.tab[y-1][x-1]=='0'){
				Main.bol[y-1][x]=false;
				Main.bol[y-1][x-1]=false;
				this.x--;
			}
			
		}
		else {
			if(Main.tab[y-1][this.field.getXMAX()-1]=='0'){
				Main.bol[y-1][x]=false;
				Main.bol[y-1][this.field.getXMAX()-1]=false;
				this.x = this.field.getXMAX()-1;
			}
		}
	}
	
	public void goRight(){
		if(this.x<this.field.getXMAX()-1){
			
			if(Main.tab[y-1][x+1]=='0'){
				Main.bol[y-1][x]=false;
				Main.bol[y-1][x+1]=false;
				this.x++;
			}
		}
		else {
			if(Main.tab[y-1][0]=='0'){
				Main.bol[y-1][x]=false;
				Main.bol[y-1][0]=false;
				this.x=0;
			}
		}
	}
	
	public void goBot(){
		if(this.y+1 < this.field.getYMAX()){
			if(Main.tab[y][x]=='0'){
				Main.bol[y][x]= false;
				Main.bol[y-1][x]= false;
				this.y++;
			}
		}
		else {
			if(Main.tab[0][x]=='0'){
				Main.bol[y-1][x]=false;
				Main.bol[0][x]=false;
				this.y=1;
			}
		}
	}
	
	public void goTop(){
		if(this.y-1> 0){
			if(Main.tab[y-2][x]=='0'){
				Main.bol[y-1][x]=false;
				Main.bol[y-2][x]=false;
				this.y--;
			}
		}
		else {
			if(Main.tab[(this.field.getYMAX())-2][x]=='0'){
				Main.bol[1][x]=false;
				Main.bol[this.field.getYMAX()-2][x]=false;
			this.y = this.field.getYMAX()-1;
			}
		}
	}
	
	private void updateScoreAndLife() {
	   scoreLabel.setText("Score: " + pacmanScore);
	   livesLabel.setText(" " + "Lives: " + pacmanLives + "     ");
	}
	
	/*public void wall() throws IOException{
		BufferedImage myPicture;
		 myPicture = ImageIO.read(new File("image/brique.png"));
	     JLabel picLabel = new JLabel(new ImageIcon(myPicture));
	     picLabel.setLocation(new Point(25,25));
	     this.field.add(picLabel);
	}*/

}