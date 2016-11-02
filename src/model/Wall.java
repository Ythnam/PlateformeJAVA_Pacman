package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Wall {
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
	int count = 0 ;
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
							count++;
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
counter = count;
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

	
	
}
