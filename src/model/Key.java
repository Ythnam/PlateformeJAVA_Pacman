package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

public class Key extends Items{

	private static final String NAME = "key";
	private static final int SCORE = 5000;
	private static List<Integer> level = Arrays.asList(13,14,15); //ici 13+
	public static BufferedImage img;
	private int x, y;
	private boolean isTaken = false; //permet de savoir si le PACMAN l'a mang� ou non
	
	static{
		try{
			img = ImageIO.read(new File("path"));
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public Key(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public String getName(){
		return this.NAME;
	}
	
	public Items generateItems(int x, int y){
		Key k = new Key(x,y);
		return k;
	}
}