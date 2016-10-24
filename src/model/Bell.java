package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

public class Bell extends Items{

	private static final String NAME = "bell";
	private static final int SCORE = 3000;
	private static List<Integer> level = Arrays.asList(11,12);
	public static BufferedImage img;
	private int x, y;
	private boolean isTaken = false; //permet de savoir si le PACMAN l'a mangé ou non
	
	static{
		try{
			img = ImageIO.read(new File("path"));
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public Bell(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public String getName(){
		return this.NAME;
	}
	
	/*public Items generateItems(int x, int y){
		Bell b = new Bell(x,y);
		return b;
	}*/
}
