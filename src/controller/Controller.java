package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import View.Field;
import model.Model;

public class Controller implements KeyListener {

	private Model model;
	private Field view;
	
	public Controller(Model newModel){
		this.model = newModel;
	}
	
	public void setView(Field view){
		this.view = view;
	}
	
	final public Field getView(){
		return this.view;
	}
	
	public void setModel(Model model){
		this.model = model;
	}
	
	public Model getModel(){
		return this.model;
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		int source = e.getKeyCode();
		System.out.println("KeyPressed");
		if(source==KeyEvent.VK_UP)
			this.model.getPacman().goTop();
		else if(source==KeyEvent.VK_DOWN)
			this.model.getPacman().goBot();
		else if(source==KeyEvent.VK_RIGHT)
			this.model.getPacman().goRight();
		else if(source==KeyEvent.VK_LEFT)
			this.model.getPacman().goLeft();
		
		this.getView().setModel(this.getModel());
		this.getView().repaint();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("released");
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
