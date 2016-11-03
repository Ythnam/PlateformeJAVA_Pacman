package main;

import javax.swing.JFrame;
import model.Map;
import View.Field;

public class Main {






	public static void main(String[] args) {
		Map wall = new Map();
		JFrame frame = new JFrame();
		Field field = new Field();
		//frame.setSize(wall.longueur*21-(int)(wall.longueur/2),(wall.hauteur+1)*22);
		frame.setSize((wall.getLongueur()+1)*field.getStep()-5,(wall.getHauteur()+3)*field.getStep()-1);
		frame.setResizable(true);
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setContentPane(field);
		frame.setVisible(true);
		
		
		 
		
	}
	
	

}
