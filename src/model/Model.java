package model;

import java.util.ArrayList;

public class Model {

	private Pacman pacman;
	private ArrayList<Ghost> alGhost = new ArrayList<>();
	private ArrayList<Items> alItems = new ArrayList<>();
	
	public Model(){
		
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
}
