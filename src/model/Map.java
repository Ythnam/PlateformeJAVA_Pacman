

package model;


public class Map {
	private static  int longueur =10;
	private static  int hauteur =10;
	private static  char[][] tab;
	private static  boolean[][] bol;
	private static  int counter;
	private  long now = 0;
	public static int getLongueur() {
		return longueur;
	}
	
	public Map(){
		
	}
	
	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}
	public static int getHauteur() {
		return hauteur;
	}
	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
	public static char[][] getTab() {
		return tab;
	}
	public void setTab(char[][] tab) {
		this.tab = tab;
	}
	public static boolean[][] getBol() {
		return bol;
	}
	public void setBol(boolean[][] bol) {
		this.bol = bol;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public long getNow() {
		return now;
	}
	public void setNow(long now) {
		this.now = now;
	}


	
}
