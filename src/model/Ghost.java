package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.Main;
import View.Field;

public class Ghost implements Runnable {

	public static BufferedImage ghostRED;
	public static BufferedImage ghostBLUE;
	public static BufferedImage ghostGREEN;
	public static BufferedImage ghostORANGE;
	private int x, y;
	private double distance; // donnera sa distance par rapport au Pacman et permettra de le faire "chasser" à partir
							// d'une certaine distance
	private Field field;
	private Random rand = new Random();
	
	static{
		try{
			ghostRED = ImageIO.read(new File(/*"image/ghost_red.png"*/"image/red20x20.png"));
			ghostBLUE = ImageIO.read(new File(/*"image/ghost_blue.png"*/"image/blue20x20.png"));
			ghostGREEN = ImageIO.read(new File(/*"image/ghost_green.png"*/"image/green20x20.png"));
			ghostORANGE = ImageIO.read(new File(/*"image/ghost_orange.png"*/"image/orange20x20.png"));

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
	
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
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
				
				tryToMove();
				
				field.repaint();
				if(Main.counter==0){
					Thread.sleep(1000000);
				}
			}
		}  catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Cette méthode permet de faire avancer les fantomes en aléatoire
	 */
	private void tryToMove(){
		int choice = rand.nextInt(4);
		switch(choice){
		case 0 : 
			if(this.x<this.field.getXMAX()-1){
				
				if(Main.tab[y-1][x+1]=='0'){this.x++;}
			}
			else {
				if(Main.tab[y-1][0]=='0'){
					this.x=0;
				}
			}
			break;
		case 1 :
			if(this.x>0){
				if(Main.tab[y-1][x-1]=='0'){this.x--;}
			}
			else {
				if(Main.tab[y-1][this.field.getXMAX()-1]=='0'){
					this.x = this.field.getXMAX()-1;
				}
			}
			break;
		case 2 :
			if(this.y+1 < this.field.getYMAX()){
				if(Main.tab[y][x]=='0'){
					this.y++;
				}
			}
			else {
				if(Main.tab[0][x]=='0'){
					this.y=1;
				}
			}
			break;
		case 3 :
			if(this.y-1> 0){
				if(Main.tab[y-2][x]=='0') this.y--;
			}
			else {
				if(Main.tab[(this.field.getYMAX())-2][x]=='0'){
				this.y = this.field.getYMAX()-1;
				}
			}
			break;
		}
	}
}
