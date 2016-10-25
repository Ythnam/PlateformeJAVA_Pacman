package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import View.Field;

public class Apple extends Items{

	
	private static final String NAME = "apple";
	private static final int SCORE = 700;
	private static List<Integer> level = Arrays.asList(5,6);
	public static BufferedImage img;
	//private int x, y;
	//private boolean isTaken = false; //permet de savoir si le PACMAN l'a mangé ou non
	
	static{
		try{
			img = ImageIO.read(new File("path"));
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public Apple(int x, int y, Field field){
		super.x = x;
		super.y = y;
		super.field = field;
	}
	
	public String getName(){
		return this.NAME;
	}
	
	public int getScore(){
		return this.SCORE;
	}
	
	public List<Integer> getLevel(){
		return this.level;
	}
	
	/*
	public Items generateItems(int x, int y){
		Apple a = new Apple(x,y);
		return a;
	}*/
}
