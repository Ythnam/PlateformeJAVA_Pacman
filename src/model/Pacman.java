package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import View.Field;

public class Pacman implements Runnable{

	private static Pacman instance; // Pacman est un singleton
	
	public static BufferedImage img;
	private int x, y;
	private Field field;
	private boolean isPowerUp = false; // booleen pour le faire entrer ne état ou il bas les fantomes
	
	static{
		try{
			img = ImageIO.read(new File("path"));
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	/*public Pacman(int x, int y, Field field){
		this.x = x;
		this.y = y;
		this.field = field;
	}*/
	private Pacman(int x, int y, Field field){
		this.x = x;
		this.y = y;
		this.field = field;
	}
	
	
	// Ce code pour le singleton a été récupéré sur le site suivant :
	//http://christophej.developpez.com/tutoriel/java/singleton/multithread/
	public static Pacman getInstance(int x, int y, Field field) {
        if (null == instance) { // Premier appel
            synchronized(Pacman.class) {
                    instance = new Pacman(x, y, field);
            }
        }
        return instance;
    }
	
	@Override
	public void run(){
		try{
			while(true){
				Thread.sleep(500);
				
				// Entrer le code pendant le RUn --> dépend des entrées clavier ici
				
				field.repaint();
			}
		}  catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
}
