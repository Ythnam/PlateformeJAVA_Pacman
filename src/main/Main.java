package main;

import javax.swing.JFrame;
import model.Wall;
import View.Field;

public class Main {






	public static void main(String[] args) {
		Wall wall = new Wall();
		wall.lecture();
		wall.createstring();
		JFrame frame = new JFrame();
Field field = new Field();
		//frame.setSize(wall.longueur*21-(int)(wall.longueur/2),(wall.hauteur+1)*22);
		frame.setSize((wall.longueur+1)*field.getStep()-5,(wall.hauteur+3)*field.getStep()-1);
		frame.setResizable(true);
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setContentPane(field);
		frame.setVisible(true);
		
		 
		
	}
	
	

}
