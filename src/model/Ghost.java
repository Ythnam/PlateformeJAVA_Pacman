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
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public static BufferedImage getGhostBLUE() {
		return ghostBLUE;
	}
	
	public static BufferedImage getGhostGREEN() {
		return ghostGREEN;
	}
	
	public static BufferedImage getGhostORANGE() {
		return ghostORANGE;
	}
	
	public static BufferedImage getGhostRED() {
		return ghostRED;
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
