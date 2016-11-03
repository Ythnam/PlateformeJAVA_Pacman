package model;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Model {

	private Pacman pacman;
	private Map map = new Map();
	private ArrayList<Ghost> alGhost = new ArrayList<>();
	private ArrayList<Items> alItems = new ArrayList<>();
	
	public Model(){
		lecture();
		createstring();
	}
	
	public ArrayList<Ghost> getAlGhost() {
		return alGhost;
	}
	
	public ArrayList<Items> getAlItems() {
		return alItems;
	}
	
	public Pacman getPacman() {
		return pacman;
	}
	
	public void setAlGhost(ArrayList<Ghost> alGhost) {
		this.alGhost = alGhost;
	}
	
	public void setAlItems(ArrayList<Items> alItems) {
		this.alItems = alItems;
	}
	
	public void setPacman(Pacman pacman) {
		this.pacman = pacman;
	}
	
	public void addToAlGhost(Ghost g){
		this.alGhost.add(g);
	}
	
	public void addToAlItems(Items i){
		this.alItems.add(i);
	}

	public Map getMap() {
		return map;
	}


public void createstring () {
	String fichier="texte/lvl1.txt";
	map.setTab(new char[map.getHauteur()][map.getLongueur()]);
	map.setBol(new boolean[map.getHauteur()][map.getLongueur()]);
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
						map.getTab()[i][j] = c;
						if(c=='1'){
							map.getBol()[i][j] = false;
						}
						else {
							count++;
							map.getBol()[i][j] = true;
						}
						 
						
						if(c=='2'){
							this.map.setSpawn(new Point(i,j));
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
map.setCounter(count);
}


public void lecture () {
	int longueur1 = 0;
    int hauteur1 = 0;
	String fichier="texte/lvl1.txt";
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
			//System.out.println(hauteur1); 
			this.map.setHauteur(hauteur1);
			//System.out.println(longueur1);
			this.map.setLongueur(longueur1);		
}

	

}
