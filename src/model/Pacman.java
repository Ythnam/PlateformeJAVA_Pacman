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

public class Pacman {

	private static Pacman instance; // Pacman est un singleton
	
	public static BufferedImage img;
	private int x, y;
	private Field field;
	private boolean isPowerUp = false; // booleen pour le faire entrer ne état ou il bas les fantomes
	private Random rand = new Random(); // Sert juste pour les test tant qu'on n'a pas le clavier
	public static ImageIcon imageIcon = new ImageIcon("image/pacman-haut.gif");
	 private long pacmanScore = 0;
	 private int pacmanLives = 3;


	
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
                    instance = new Pacman(x, y, field);
            }
        }
        return instance;
    }
	
	private Pacman(int x, int y, Field field){
		
		this.x = x;
		this.y = y;
		this.field = field;
	}
	



	public void goLeft(){
		if(this.x>0){
			if(Wall.tab[y-1][x-1]=='0'){
				if(Wall.bol[y-1][x-1] == true){ pacmanScore+=100 ;this.field.updateScoreAndLife();Wall.counter--;}
				Wall.bol[y-1][x-1]=false;
				this.x--;
			}
			
		}
		else {
			if(Wall.tab[y-1][this.field.getXMAX()-1]=='0'){
				if(Wall.bol[y-1][this.field.getXMAX()-1] == true){ pacmanScore+=100 ;this.field.updateScoreAndLife();Wall.counter--;}
				Wall.bol[y-1][this.field.getXMAX()-1]=false;
				this.x = this.field.getXMAX()-1;
			}
		}
	}
	

	public void goRight(){
		if(this.x<this.field.getXMAX()-1){
			
			if(Wall.tab[y-1][x+1]=='0'){
				if(Wall.bol[y-1][x+1] == true){ pacmanScore+=100 ;this.field.updateScoreAndLife();Wall.counter--;}
				Wall.bol[y-1][x+1]=false;
				this.x++;
			}
		}
		else {
			if(Wall.tab[y-1][0]=='0'){
				if(Wall.bol[y-1][0] == true){ pacmanScore+=100 ;this.field.updateScoreAndLife();Wall.counter--;}
				Wall.bol[y-1][0]=false;
				this.x=0;
			}
		}
	}
	
	public void goBot(){
		if(this.y+1 < this.field.getYMAX()){
			if(Wall.tab[y][x]=='0'){
				if(Wall.bol[y][x] == true) { pacmanScore+=100 ;this.field.updateScoreAndLife();Wall.counter--;}
				Wall.bol[y][x]= false;
				
				this.y++;
			}
		}
		else {
			if(Wall.tab[0][x]=='0'){
				if(Wall.bol[0][x] == true) { pacmanScore+=100 ;this.field.updateScoreAndLife();Wall.counter--;}
				Wall.bol[0][x]=false;
				this.y=1;
			}
		}
	}
	
	public void goTop(){
		if(this.y-1> 0){
			if(Wall.tab[y-2][x]=='0'){
				if(Wall.bol[y-2][x] == true){ pacmanScore+=100 ;this.field.updateScoreAndLife();Wall.counter--;}
				Wall.bol[y-2][x]=false;
				this.y--;
			}
		}
		else {
			if(Wall.tab[(this.field.getYMAX())-2][x]=='0'){
				if(Wall.bol[this.field.getYMAX()-2][x] == true) { pacmanScore+=100 ;this.field.updateScoreAndLife();Wall.counter--;}
				Wall.bol[this.field.getYMAX()-2][x]=false;
			this.y = this.field.getYMAX()-1;
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


}