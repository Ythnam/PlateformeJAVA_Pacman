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
import model.Wall;

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
			if(Wall.counter == 0){
				try {
					name = JOptionPane.showInputDialog(view,"Entrez votre pseudo", null);
					JOptionPane.showMessageDialog(view, "Felicitations " + name+ ", vous avez fini la partie en " + this.view.getChrono() + " avec " + this.model.getPacman().getPacmanScore() + " points");
					savescore();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(source==KeyEvent.VK_DOWN){
			this.model.getPacman().goBot();
			if(Wall.counter == 0){
				try {
					name = JOptionPane.showInputDialog(view,"Entrez votre pseudo", null);					
					JOptionPane.showMessageDialog(view, "Felicitations " + name+ ", vous avez fini la partie en " + this.view.getChrono() + " avec " + this.model.getPacman().getPacmanScore() + " points");
					savescore();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else if(source==KeyEvent.VK_RIGHT){
			this.model.getPacman().goRight();
			if(Wall.counter == 0){
				try {
					name = JOptionPane.showInputDialog(view,"Entrez votre pseudo", null);
					JOptionPane.showMessageDialog(view, "Felicitations " + name+ ", vous avez fini la partie en " + this.view.getChrono() + " avec " + this.model.getPacman().getPacmanScore() + " points");
					savescore();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else if(source==KeyEvent.VK_LEFT){
			this.model.getPacman().goLeft();
			if(Wall.counter == 0){
				try {
					name = JOptionPane.showInputDialog(view,"Entrez votre pseudo", null);
					JOptionPane.showMessageDialog(view, "Felicitations " + name+ ", vous avez fini la partie en " + this.view.getChrono() + " avec " + this.model.getPacman().getPacmanScore() + " points");
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
		int z =0;
		String classement ="";
		data = new long[11];
		names = new String[11];
		File f = new File ("texte/classement.txt");
		if(f.exists()){
		
		try {
            FileReader c = new FileReader(f); 
            BufferedReader r = new BufferedReader(c);
 
            String line = r.readLine();
             
            int i =0;
           while (line != null) {
                String[] decompose = line.split(";");
             //    

           String bla = decompose[0];
           String[] mots = bla.split (" " );
           data[i]= Integer.parseInt(mots[1]);
           System.out.println(mots[0]);
           names[i]= mots[0];
           i++;

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
		    int l =0;
		    for (int i=0;i<11;i++)
		    {
		    	
		    	if(this.model.getPacman().getPacmanScore()>data[l] && bool){
		    		 fw.write (name+" " + this.model.getPacman().getPacmanScore());
		    		 classement+=z + " - "+ name+" " + this.model.getPacman().getPacmanScore() + "\n";
		    		 z++;
		    		 fw.write ("\r\n");
		    		 bool = false;
		    	}
		    	if(data[l]!=0.0 ){
		    		fw.write (String.valueOf(names[l]) +"" +  String.valueOf (data[l]));
		    		classement+=z + " - "+ names[l] + " : " + data[l] + "\n";
		    		z++;
		    		fw.write ("\r\n");
		    	}

		    	l++;
		    }
		 
		    fw.close();
		}
		catch (IOException exception)
		{
		    System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
		}
		
		/*if(f.exists()){	
		try {
            FileReader c1 = new FileReader(f); 
            BufferedReader r1 = new BufferedReader(c1);
 
            String line = r1.readLine();

           while (line != null) {
        	   line = r1.readLine(); 
           }
           r1.close();
		}catch (IOException exception)
		{
		    System.out.println ("Erreur lors de l'affichage : " + exception.getMessage());
		}
		}
		*/
		
		//for(int k =0; k<11;k++){
			//if(data[k]!=0.0)
		//	classement+=k + " - "+ names[k] + " : " + data[k] + "\n";
		//}
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
