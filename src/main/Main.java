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
		frame.setSize(wall.longueur*22-4,(wall.hauteur+1)*24-6);
		frame.setResizable(true);
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Field field = new Field();
		frame.setContentPane(field);
		frame.setVisible(true);
		
		 
		
	}
	
	

}
