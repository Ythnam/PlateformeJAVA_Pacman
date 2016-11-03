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
import model.Ghost;
import model.Model;

public class Controller implements KeyListener {

	private Model model;
	private Field view;
	public long[] data;
	public String[] names;
	String  name = "";
	//private static String classement ="";
	private static String classementhtml ="";
	
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
			this.model.getPacman().setImageIcon(this.model.getPacman().getImageIconTop());
			if(this.model.getMap().getCounter() == 0){
				try {
					name = JOptionPane.showInputDialog(view,"Entrez votre pseudo", null);
					//setClassement(getClassement() + "Fin du niveau en "+this.view.getChrono()+"s\nTotal "+this.model.getPacman().getPacmanScore()+"points\n\n\n\n");
					setClassementhtml("<html>" + "Fin du niveau en "+this.view.getChrono()+"s<br>Total "+this.model.getPacman().getPacmanScore()+"<br><br><br><br>");
					savescore();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(source==KeyEvent.VK_DOWN){
			this.model.getPacman().goBot();
			this.model.getPacman().setImageIcon(this.model.getPacman().getImageIconBot());
			if(this.model.getMap().getCounter() == 0){
				try {
					name = JOptionPane.showInputDialog(view,"Entrez votre pseudo", null);					
					//setClassement(getClassement() + "Fin du niveau en "+this.view.getChrono()+"s\nTotal "+this.model.getPacman().getPacmanScore()+" points\n\n\n\n");
					setClassementhtml("<html>" + "Fin du niveau en "+this.view.getChrono()+"s<br>Total "+this.model.getPacman().getPacmanScore()+"<br><br><br><br>");
					savescore();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else if(source==KeyEvent.VK_RIGHT){
			this.model.getPacman().goRight();
			this.model.getPacman().setImageIcon(this.model.getPacman().getImageIconRight());
			if(this.model.getMap().getCounter() == 0){
				try {
					name = JOptionPane.showInputDialog(view,"Entrez votre pseudo", null);
					//setClassement(getClassement() + "Fin du niveau en "+this.view.getChrono()+"s\nTotal "+this.model.getPacman().getPacmanScore()+" points\n\n\n\n");
					setClassementhtml("<html>" + "Fin du niveau en "+this.view.getChrono()+"s<br>Total "+this.model.getPacman().getPacmanScore()+"<br><br><br><br>");
					savescore();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else if(source==KeyEvent.VK_LEFT){
			this.model.getPacman().goLeft();
			this.model.getPacman().setImageIcon(this.model.getPacman().getImageIconLeft());
			if(this.model.getMap().getCounter() == 0){
				try {
					name = JOptionPane.showInputDialog(view,"Entrez votre pseudo", null);
					//setClassement(getClassement() + "Fin du niveau en "+this.view.getChrono()+"s\nTotal "+this.model.getPacman().getPacmanScore()+" points\n\n\n\n");
					setClassementhtml("<html>" + "Fin du niveau en "+this.view.getChrono()+"s<br>Total "+this.model.getPacman().getPacmanScore()+"<br><br><br><br>");
					savescore();
				} catch (IOException e1) {
					// TODO onAuto-generated catch block
					e1.printStackTrace();
				}
			}
		}else if(source==KeyEvent.VK_SPACE){
			for (Ghost g : this.model.getAlGhost()){
				g.setOnPause(!g.isOnPause());
			}
			this.model.getPacman().setOnPause(!this.model.getPacman().isOnPause());
			view.getChron().setOnPause(!view.getChron().isOnPause());
			view.setDelay(0);
			//this.model.getAlGhost().onPause = !this.model.getAlGhost().onPause;
		}
		this.getView().setModel(this.getModel());
		this.getView().repaint();

	}

	public void savescore() throws IOException{
		int z =1;
		
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
		    		// setClassement(getClassement() + z + " - "+ name+" " + this.model.getPacman().getPacmanScore() + "\n");
		    		 setClassementhtml(getClassementhtml() + z + " - "+ name+" " + this.model.getPacman().getPacmanScore() + "<br>");
						 z++;
		    		 fw.write ("\r\n");
		    		 bool = false;
		    	}
		    	if(data[l]!=0.0 ){
		    		fw.write (String.valueOf(names[l]) +" " +  String.valueOf (data[l]));
		    		//setClassement(getClassement() + z + " - "+ String.valueOf(names[l]) + " " + String.valueOf(data[l]) + "\n");
		    		setClassementhtml(getClassementhtml() + z + " - "+ String.valueOf(names[l]) + " " + String.valueOf(data[l]) + "<br>");
		    		z++;
		    		fw.write ("\r\n");
		    	}

		    	l++;
		    }
		    setClassementhtml(getClassementhtml() + "</html>");
		    fw.close();
		}
		catch (IOException exception)
		{
		    System.out.println ("Erreur lors de la lecture : " + exception.getMessage());
		}
		view.pop();	
	}
	@Override
	public void keyReleased(KeyEvent e) {
		//view.pop();
		// TODO Auto-generated method stub		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/*public static String getClassement() {
		return classement;
	}

	public void setClassement(String classement) {
		this.classement = classement;
	}
*/
	public static String getClassementhtml() {
		return classementhtml;
	}

	public static void setClassementhtml(String classementhtml) {
		Controller.classementhtml = classementhtml;
	}

	
}
