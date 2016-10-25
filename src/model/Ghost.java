package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import View.Field;

public class Ghost implements Runnable {

	public static BufferedImage ghostRED;
	public static BufferedImage ghostBLUE;
	public static BufferedImage ghostGREEN;
	public static BufferedImage ghostORANGE;

	private int x, y;
	private double distance;
	private Field field;
	private Random rand = new Random();
	
	static{
		try{
			ghostRED = ImageIO.read(new File("image/ghost_red.png"));
			ghostBLUE = ImageIO.read(new File("image/ghost_blue.png"));
			ghostGREEN = ImageIO.read(new File("image/ghost_green.png"));
			ghostORANGE = ImageIO.read(new File("image/ghost_orange.png"));

		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public Ghost(int x, int y, Field field){
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
