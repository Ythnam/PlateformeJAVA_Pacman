package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Items {
	
	protected int x, y;
	protected boolean isTaken = false; //permet de savoir si le PACMAN l'a mang� ou non

	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public abstract String getName();
	public abstract int getScore();
	public abstract List<Integer> getLevel();
	//public abstract Items generateItems();
}
