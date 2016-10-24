package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

public class Orange extends Items{

	private static final String NAME = "orange";
	private static final int SCORE = 500;
	private static List<Integer> level = Arrays.asList(3,4);
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
	
	public Orange(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public String getName(){
		return this.NAME;
	}
	
	public Items generateItems(int x, int y){
		Orange o = new Orange(x,y);
		return o;
	}
	
}
