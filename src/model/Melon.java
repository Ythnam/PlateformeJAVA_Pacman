package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import View.Field;

public class Melon extends Items{

	private static final String NAME = "melon";
	private static final int SCORE = 1000;
	private static List<Integer> level = Arrays.asList(7,8);
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
	
	public Melon(int x, int y, Field field){
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
		Melon m = new Melon(x,y);
		return m;
	}*/
}
