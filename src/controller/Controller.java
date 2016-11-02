package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import View.Field;
import main.Main;
import model.Model;

public class Controller implements KeyListener {

	private Model model;
	private Field view;
	public long[] data;
	public String[] names;
	String  name = "";
	
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
		if(source==KeyEvent.VK_UP){
			this.model.getPacman().goTop();
			if(Main.counter == 0){
				try {
					name = JOptionPane.showInputDialog(view,"Entrez votre pseudo", null);
					JOptionPane.showMessageDialog(view, "Felicitations " + name+ ", vous avez fini la partie en " + this.model.getPacman().getChrono() + " avec " + this.model.getPacman().getPacmanScore() + " points");
					savescore();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(source==KeyEvent.VK_DOWN){
			this.model.getPacman().goBot();
			if(Main.counter == 0){
				try {
					name = JOptionPane.showInputDialog(view,"Entrez votre pseudo", null);
					JOptionPane.showMessageDialog(view, "Felicitations " + name+ ", vous avez fini la partie en " + this.model.getPacman().getChrono() + " avec " + this.model.getPacman().getPacmanScore() + " points");
					savescore();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else if(source==KeyEvent.VK_RIGHT){
			this.model.getPacman().goRight();
			if(Main.counter == 0){
				try {
					name = JOptionPane.showInputDialog(view,"Entrez votre pseudo", null);
					JOptionPane.showMessageDialog(view, "Felicitations " + name+ ", vous avez fini la partie en " + this.model.getPacman().getChrono() + " avec " + this.model.getPacman().getPacmanScore() + " points");
					savescore();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else if(source==KeyEvent.VK_LEFT){
			this.model.getPacman().goLeft();
			if(Main.counter == 0){
				try {
					name = JOptionPane.showInputDialog(view,"Entrez votre pseudo", null);
					JOptionPane.showMessageDialog(view, "Felicitations " + name+ ", vous avez fini la partie en " + this.model.getPacman().getChrono() + " avec " + this.model.getPacman().getPacmanScore() + " points");
					savescore();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		this.getView().setModel(this.getModel());
		this.getView().repaint();

	}

	public void savescore() throws IOException{
		data = new long[11];
		names = new String[11];
		File f = new File ("image/temperatures.txt");
		if(f.exists()){
		
		try {
            FileReader c = new FileReader(f); 
            BufferedReader r = new BufferedReader(c);
 
            String line = r.readLine();
             
            int i =0;
           while (line != null) {
                String[] decompose = line.split(";");
             //    
// tu stock dans tes variables
           String bla = decompose[0];
           String[] mots = bla.split (" " );
// si tu veux un entier
           data[i]= Integer.parseInt(mots[1]);
           //System.out.println(mots[0]);
           names[i]= "user";
           i++;
 
//et après tu les stocks où tu veux
 
 
           line = r.readLine();
            }
 
          r.close();
 
        } catch (Exception exception) {
        	 System.out.println ("Erreur lors de la recuperation : " + exception.getMessage());
        }
		
		}else{
			FileWriter fw = new FileWriter (f);
			fw.write ("something");
		    fw.close();
		}
		
		
		 
		try
		{
		    FileWriter fw = new FileWriter (f);
		    boolean bool = true;
		 
		    for (double d : data)
		    {
		    	if(this.model.getPacman().getPacmanScore()>d && bool){
		    		 fw.write (name+" " + this.model.getPacman().getPacmanScore());
		    		 fw.write ("\r\n");
		    		 bool = false;
				        
		    	}
		    	if(d!=0.0){
		    		fw.write (String.valueOf (d));
		    		fw.write ("\r\n");
		    	}
		    }
		 
		    fw.close();
		}
		catch (IOException exception)
		{
		    System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
		}
		
		String classement ="";
		for(int k =0; k<11;k++){
			if(data[k]!=0.0)
			classement+=k + " - "+ names[k] + " : " + data[k] + "\n";
		}
		JOptionPane.showMessageDialog(view,classement);
		
		
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
