package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import main.Main;
import View.Field;

public class Pacman implements ActionListener {

	private static Pacman instance; // Pacman est un singleton
	
	public static BufferedImage img;
	private int x, y;
	private Field field;
	private boolean isPowerUp = false; // booleen pour le faire entrer ne état ou il bas les fantomes
	private Random rand = new Random(); // Sert juste pour les test tant qu'on n'a pas le clavier
	public static ImageIcon imageIcon = new ImageIcon("image/pacman-haut.gif");
	double chrono = 0;
	Chrono chron = new Chrono();
	private final JLabel scoreLabel; 
	private JLabel timeLabel; 
	 private final JLabel livesLabel;
	 private long pacmanScore;
	 private int pacmanLives;
	 protected Timer timer;

	
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

		this.setPacmanScore(score);
		this.pacmanLives =lives;
		
		
		scoreLabel = new JLabel("Score: " + getPacmanScore());
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
		
		timeLabel = new JLabel("Timer: " + chrono);
		Dimension d = new Dimension(85,15);
		timeLabel.setPreferredSize(d);
		timeLabel.setFont(new Font("Serif", Font.PLAIN, 10));
		timeLabel.setForeground(Color.white);
		timeLabel.setBackground(Color.BLACK);
		timeLabel.setOpaque(true);
		this.field.add(timeLabel);
		
		timer = new Timer(1, this);
		timer.start();
		chron.start();
	}
	



	public void goLeft(){
		updatetimer();
		if(this.x>0){
			if(Main.tab[y-1][x-1]=='0'){
				if(Main.bol[y-1][x-1] == true){ pacmanScore+=100 ;updateScoreAndLife();}
				Main.bol[y-1][x-1]=false;
				this.x--;
			}
			
		}
		else {
			if(Main.tab[y-1][this.field.getXMAX()-1]=='0'){
				if(Main.bol[y-1][this.field.getXMAX()-1] == true){ pacmanScore+=100 ;updateScoreAndLife();}
				Main.bol[y-1][this.field.getXMAX()-1]=false;
				this.x = this.field.getXMAX()-1;
			}
		}
	}
	public void actionPerformed (ActionEvent e){
		chron.pause();
		chrono = chron.getDureeSec(); // affichage en secondes
		chron.resume();
		updatetimer();
	}

	
	private void updatetimer() {
		
		System.out.println(chrono);
		timeLabel.setText("Time: " + chrono);
	}

	public void goRight(){
		if(this.x<this.field.getXMAX()-1){
			
			if(Main.tab[y-1][x+1]=='0'){
				if(Main.bol[y-1][x+1] == true){ pacmanScore+=100 ;updateScoreAndLife();}
				Main.bol[y-1][x+1]=false;
				this.x++;
			}
		}
		else {
			if(Main.tab[y-1][0]=='0'){
				if(Main.bol[y-1][0] == true){ pacmanScore+=100 ;updateScoreAndLife();}
				Main.bol[y-1][0]=false;
				this.x=0;
			}
		}
	}
	
	public void goBot(){
		if(this.y+1 < this.field.getYMAX()){
			if(Main.tab[y][x]=='0'){
				if(Main.bol[y][x] == true) { pacmanScore+=100 ;updateScoreAndLife();}
				Main.bol[y][x]= false;
				
				this.y++;
			}
		}
		else {
			if(Main.tab[0][x]=='0'){
				if(Main.bol[0][x] == true) { pacmanScore+=100 ;updateScoreAndLife();}
				Main.bol[0][x]=false;
				this.y=1;
			}
		}
	}
	
	public void goTop(){
		if(this.y-1> 0){
			if(Main.tab[y-2][x]=='0'){
				if(Main.bol[y-2][x] == true){ pacmanScore+=100 ;updateScoreAndLife();}
				Main.bol[y-2][x]=false;
				this.y--;
			}
		}
		else {
			if(Main.tab[(this.field.getYMAX())-2][x]=='0'){
				if(Main.bol[this.field.getYMAX()-2][x] == true) { pacmanScore+=100 ;updateScoreAndLife();}
				Main.bol[this.field.getYMAX()-2][x]=false;
			this.y = this.field.getYMAX()-1;
			}
		}
	}
	
	public void updateScoreAndLife() {
	   scoreLabel.setText("Score: " + getPacmanScore());
	   livesLabel.setText(" " + "Lives: " + pacmanLives );
	}

	public long getPacmanScore() {
		return pacmanScore;
	}

	public void setPacmanScore(long pacmanScore) {
		this.pacmanScore = pacmanScore;
	}


}