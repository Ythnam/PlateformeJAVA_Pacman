package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import View.Field;

public class Pacman implements Runnable{

	public static BufferedImage img;
	private int x, y;
	private double distance;
	private Field field;
	
	static{
		try{
			img = ImageIO.read(new File("path"));
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public Pacman(int x, int y, Field field){
		this.x = x;
		this.y = y;
		this.field = field;
	}
	
	@Override
	public void run(){
		try{
			while(true){
				Thread.sleep(500);
				
				// Entrer le code pendant le RUn 
				
				field.repaint();
			}
		}  catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
}
