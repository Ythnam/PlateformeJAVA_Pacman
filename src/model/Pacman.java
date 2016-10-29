package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import View.Field;

public class Pacman implements Runnable{

	private static Pacman instance; // Pacman est un singleton
	
	public static BufferedImage img;
	private int x, y;
	private Field field;
	private boolean isPowerUp = false; // booleen pour le faire entrer ne état ou il bas les fantomes
	private Random rand = new Random(); // Sert juste pour les test tant qu'on n'a pas le clavier
	public static ImageIcon imageIcon = new ImageIcon("image/pacman-haut.gif");
	
	/*static{
		try{
			img = ImageIO.read(new File("path"));
		} catch (IOException e){
			e.printStackTrace();
		}
	}*/
	
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
	
	//Fin du code pour le singleton
	
	@Override
	public void run(){
		try{
			while(true){
				Thread.sleep(500);
				
				tryToMove();
				
				field.repaint();
			}
		}  catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Cette méthode permet de faire avancer le pacman en aléatoire
	 */
	private void tryToMove(){
		int choice = rand.nextInt(4);
		switch(choice){
		case 0 : 
			if(this.x+1 < this.field.getXMAX()) x++;
			break;
		case 1 :
			if(this.y+1 < this.field.getYMAX()) y++;
			break;
		case 2 :
			if(this.x > 0) x--;
			break;
		case 3 :
			if(this.y > 0) y--;
		}
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
