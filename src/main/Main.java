package main;

import javax.swing.JFrame;

import View.Field;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(640,665);
		frame.setResizable(true);
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Field field = new Field();
		frame.setContentPane(field);
		frame.setVisible(true);

	}

}
