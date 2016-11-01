package main;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import View.Field;

public class Main {
public static int longueur = 0;
public static int hauteur = 0;
public static char[][] tab;
public static boolean[][] bol;
public static int counter;
public static long now = 0;


public static void createstring () {
	String fichier="image/lvl1.txt";
	tab = new char[hauteur][longueur];
	bol = new boolean[hauteur][longueur];
	counter = 0 ;
	int i =0;
	int j = 0 ;
			try{
				File ips = new File(fichier);
				FileReader ipsr = new FileReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				Scanner sc = null;
				try {
					sc = new Scanner(new File(fichier));
				while (sc.hasNextLine()) {
					for (char c : sc.next().toCharArray()) {
						tab[i][j] = c;
						if(c=='0'){
							counter++;
							bol[i][j] = true;
						}
						else {
							bol[i][j] = false;
						}
						j++;
					}
					i++;
					j=0;
				}
				} finally {
					if (sc != null)
						sc.close();
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
			}
}



public static void lecture () {
	int longueur1 = 0;
    int hauteur1 = 0;
	String fichier="image/lvl1.txt";
			try{
				File ips = new File(fichier);
				FileReader ipsr = new FileReader(ips);
				BufferedReader br=new BufferedReader(ipsr);
				Scanner sc = null;
				try {
					sc = new Scanner(new File(fichier));
				while (sc.hasNextLine()) {
					for (char c : sc.next().toCharArray()) {
						longueur1 += 1;
						System.out.println(c);
					}
					hauteur1 += 1 ;
				}
				} finally {
					if (sc != null)
						sc.close();
					}

				} catch (FileNotFoundException e) {
					e.printStackTrace();
			}
			
			longueur1 = longueur1/hauteur1;
			System.out.println(hauteur1); hauteur = hauteur1;
			System.out.println(longueur1); longueur = longueur1;
			
			
}
	public static void main(String[] args) {
		lecture();
		createstring();
		JFrame frame = new JFrame();
		frame.setSize(longueur*22-4,(hauteur+1)*24-6);
		frame.setResizable(true);
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Field field = new Field();
		frame.setContentPane(field);
		frame.setVisible(true);
		
		 
		
	}
	
	

}
