package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;

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
	private boolean onPause = false;
	
	static{
		try{
			ghostRED = ImageIO.read(new File("image/ghost_red20x20.png"));
			ghostBLUE = ImageIO.read(new File("image/ghost_blue20x20.png"));
			ghostGREEN = ImageIO.read(new File("image/ghost_green20x20.png"));
			ghostORANGE = ImageIO.read(new File("image/ghost_orange20x20.png"));

		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	//test pour l'IA des ghost   
			private int cache = -10;
			
	/**
	 * Cette fonction permet de savoir ce qui se trouve autour du ghost
	 * @return un tableau de 4 éléments permettant de savoir s'il y a un mur ou non autour de lui
	 */
			public char[] getWallAroundGhost(){
				char[][] cacheWallMap = this.field.getModel().getMap().getTab(); // récupère le tableau des chemins et murs
				/*for(int i=0;i<=9;i++){
						System.out.println(cacheWallMap[i][0]+""+cacheWallMap[i][1]+""+cacheWallMap[i][2]+""+cacheWallMap[i][3]+""+cacheWallMap[i][4]+""+cacheWallMap[i][5]+""+cacheWallMap[i][6]+""+cacheWallMap[i][7]+""+cacheWallMap[i][8]+""+cacheWallMap[i][9]);
					
				}*/
				char[] aroundAvailable = new char[4];
				if(this.x == this.field.getXMAX()-1){
					aroundAvailable[0] = cacheWallMap[this.y][0]; // droite
				}else{
					aroundAvailable[0] = cacheWallMap[this.y][this.x+1]; //droite
				}
				if(this.x == 0){
					aroundAvailable[1] = cacheWallMap[this.y][this.field.getXMAX()-1]; //gauche
				}else{
					aroundAvailable[1] = cacheWallMap[this.y][this.x-1]; //gauche
				}
				if(this.y == this.field.getYMAX()-1){
					aroundAvailable[2] = cacheWallMap[0][this.x]; //bas
				}else{
					aroundAvailable[2] = cacheWallMap[this.y+1][this.x]; //bas
				}
				if(this.y == 0){
					aroundAvailable[3] = cacheWallMap[this.field.getYMAX()-1][this.x]; //haut
				}else{
					aroundAvailable[3] = cacheWallMap[this.y-1][this.x]; //haut
				}
				
				//System.out.println("position"+this.x+" "+this.y+"droite:"+aroundAvailable[0]+" gauche:"+aroundAvailable[1]+" bas:"+aroundAvailable[2]+" haut"+aroundAvailable[3]);
				return aroundAvailable;
			}
			
			public void mySwitch(int i){
				switch(i){
				case 0 : 
					if(this.x<this.field.getXMAX()-1){
						if(Map.getTab()[y][x+1]!='1'){this.x++;}
					}
					else {
						if(Map.getTab()[y][0]!='1'){
							this.x=0;
						}
					}
					break;
				case 1 :
					if(this.x>0){
						if(Map.getTab()[y][x-1]!='1'){this.x--;}
					}
					else {
						if(Map.getTab()[y][this.field.getXMAX()-1]!='1'){
							this.x = this.field.getXMAX()-1;
						}
					}
					break;
				case 2 :
					if(this.y < this.field.getYMAX()-1){
						if(Map.getTab()[y+1][x]!='1'){
							this.y++;
						}
					}
					else {
						if(Map.getTab()[0][x]!='1'){
							this.y=0;
						}
					}
					break;
				case 3 :
					if(this.y> 0){
						if(Map.getTab()[y-1][x]!='1') this.y--;
					}
					else {
						if(Map.getTab()[(this.field.getYMAX()-1)][x]!='1'){
						this.y = this.field.getYMAX()-1;
						}
					}
					break;
				}
			}

			public void ghostIA(){

				char[] wallAround = this.getWallAroundGhost();
				ArrayList<Integer> alI = new ArrayList();
				int count = 0;		
				for(char wall : wallAround){
					//System.out.println(wall);
					if(wall !='1'){ //wall =1 -> wall est un mur
						alI.add(count); // je récupère le numéro des cases ou les ghosts peuvent passer (1=top, 2=bot, 3=droite, 4=gauche);
						//System.out.println(alI);
					}
					count++;
				}
				
				Iterator itI = alI.iterator();
				
				switch(alI.size()){
				case 1 :
					int position1 = (int) itI.next();
					//System.out.println("position 1 : "+position1);
					mySwitch(position1);
					//tryToMove(position1);
					break;
				case 2 :
					int position2 = alI.get(rand.nextInt(alI.size()));
					//System.out.println("Position :"+position2);
					//System.out.println("cache :"+this.cache);
					/*while(position2 != this.cache){
						position2 = alI.get(this.rand.nextInt(alI.size()));
						//System.out.println("p2 :"+position2);
					}*/
					//System.out.println("position 2 : "+position2);
					mySwitch(position2);
					//tryToMove(position2);
					break;
				case 3 :
					int position3 = alI.get(rand.nextInt(alI.size()));
					//while(position3 != this.cache) position3 = alI.get(rand.nextInt(alI.size()));
					//System.out.println("position 3 : "+position3);
					mySwitch(position3);
					//tryToMove(position3);
					break;
				case 4 :
					int position4 = alI.get(rand.nextInt(alI.size()));
					//while(position4 != this.cache) position4 = alI.get(rand.nextInt(alI.size()));
					mySwitch(position4);
					break;
				}
				
			}
			
			// Fin test IA
	
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
				while(isOnPause()){
					Thread.sleep(500);
				}
				Thread.sleep(300);
				
				//tryToMove();
				ghostIA();
				//this.field.getController().loose();
				
				field.repaint();
				if(this.field.getModel().getMap().getCounter()==0){
					Thread.sleep(10);
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
				if(Map.getTab()[y][x+1]!='1'){this.x++;}
			}
			else {
				if(Map.getTab()[y][0]!='1'){
					this.x=0;
				}
			}
			break;
		case 1 :
			if(this.x>0){
				if(Map.getTab()[y][x-1]!='1'){this.x--;}
			}
			else {
				if(Map.getTab()[y][this.field.getXMAX()-1]!='1'){
					this.x = this.field.getXMAX()-1;
				}
			}
			break;
		case 2 :
			if(this.y < this.field.getYMAX()-1){
				if(Map.getTab()[y+1][x]!='1'){
					this.y++;
				}
			}
			else {
				if(Map.getTab()[0][x]!='1'){
					this.y=0;
				}
			}
			break;
		case 3 :
			if(this.y> 0){
				if(Map.getTab()[y-1][x]!='1') this.y--;
			}
			else {
				if(Map.getTab()[(this.field.getYMAX()-1)][x]!='1'){
				this.y = this.field.getYMAX()-1;
				}
			}
			break;
		}
	}

	public boolean isOnPause() {
		return onPause;
	}

	public void setOnPause(boolean onPause) {
		this.onPause = onPause;
	}

	public void eat() {
		// TODO Auto-generated method stub
		this.x = this.field.getModel().getMap().getSpawnGhost().x;
		this.y = this.field.getModel().getMap().getSpawnGhost().y;
		
	}
}