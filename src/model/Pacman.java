package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import View.Field;

public class Pacman {

	private static Pacman instance; // Pacman est un singleton
	
	public static BufferedImage img;
	private int x, y;
	private Field field;
	private boolean isPowerUp = false; // booleen pour le faire entrer ne �tat ou il bas les fantomes
	private Random rand = new Random(); // Sert juste pour les test tant qu'on n'a pas le clavier
	public static ImageIcon imageIcon = new ImageIcon("image/pacman-haut.gif");
	

	
	public static ImageIcon getImageIcon() {
		return imageIcon;
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
	}
	


	public void goLeft(){
		if(this.x+1 < this.field.getXMAX()) this.x--;
	}
	
	public void goRight(){
		if(this.x > 0) this.x++;
	}
	
	public void goBot(){
		if(this.y+1 < this.field.getYMAX()) this.y++;
	}
	
	public void goTop(){
		if(this.y > 0) this.y--;
	}
	
}