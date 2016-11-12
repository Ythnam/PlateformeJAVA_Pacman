package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import View.Field;

public class RandomItemPop implements Runnable{

	private Field field;
	private int x;
	private int y;
	
	public RandomItemPop(Field field){
		this.field = field;
	}

	public RandomItemPop(int x, int y, Field field){
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
	
	
	@Override
	public void run() {
		try{
			while(true){
				Thread.sleep(10000);
				
				this.typeOfItemsSelected();
				this.field.repaint();
			}
		} catch (InterruptedException e){
			e.printStackTrace();
		}

	}


	private void pointItemPop(){
		char[][] WallMap = this.field.getModel().getMap().getTab(); // récupère le tableau des chemins et murs
		ArrayList<Point> alP = new ArrayList<Point>();

		for(int i = 0; i < this.field.getXMAX(); i++){
			for(int j = 0; j < this.field.getYMAX(); j++){
				if(WallMap[j][i] != '1'){
					alP.add(new Point(i, j));
				}
			}
		}
		// J'ai récupéré les points où les Items peuvent pop

		Random rand = new Random();
		int randomIntPop = rand.nextInt(alP.size());

		this.x = (int) alP.get(randomIntPop).getX();
		this.y = (int) alP.get(randomIntPop).getY();
	}

	private void typeOfItemsSelected(){
		int level = this.field.getModel().getLvl();
		Items items = new Apple(0,0,this.field);

		this.pointItemPop();
		
		switch(level){
		case 1 :
			items = new Cherry(this.x, this.y, this.field);
			break;
		case 2 :
			items = new Strawberry(this.x, this.y, this.field);
			break;
		case 3 :
			items = new Orange(this.x, this.y, this.field);
			break;
		case 4 :
			items = new Orange(this.x, this.y, this.field);
			break;
		case 5 :
			items = new Apple(this.x, this.y, this.field);
			break;
		case 6 :
			items = new Apple(this.x, this.y, this.field);
			break;
		case 7 :
			items = new Melon(this.x, this.y, this.field);
			break;
		case 8 :
			items = new Melon(this.x, this.y, this.field);
			break;
		case 9 : 
			items = new Galboss(this.x, this.y, this.field);
			break;
		case 10 :
			items = new Galboss(this.x, this.y, this.field);
			break;
		case 11 :
			items = new Bell(this.x, this.y, this.field);
			break;
		case 12 :
			items = new Bell(this.x, this.y, this.field);
			break;
		default :
			items = new Key(this.x, this.y, this.field);
			break;

		}
		this.field.getModel().setItem(items);
	}


}
