package test;

import javax.swing.JFrame;

import View.Field;
import model.Ghost;
import model.Model;
import model.Orange;

public class MainTest {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		Field field = new Field(f);
		Orange o = new Orange(1,2, field);
		Model model = new Model();
		
		model.addToAlItems(o);
		
		System.out.println(model.getAlItems());
		System.out.println(model.getAlItems().get(0).getX());
		System.out.println(model.getAlItems().get(0).getY());
		System.out.println(model.getAlItems().get(0).getName());
		System.out.println(model.getAlItems().get(0).getScore());
		System.out.println(model.getAlItems().get(0).getLevel().get(0));


		Ghost g1 = new Ghost(1,1,field);
		Ghost g2 = new Ghost(2,1,field);
		Ghost g3 = new Ghost(1,2,field);
		
		model.addToAlGhost(g1);
		model.addToAlGhost(g2);
		model.addToAlGhost(g3);
		
		System.out.println(model.getAlGhost());
		/* itération a l'arache pas de critique sur cet itérateur) */
		for(int i=0; i<3; i++){
			System.out.println(model.getAlGhost().get(i));
			System.out.println("Ghost x : "+model.getAlGhost().get(i).getX());
			System.out.println("Ghost y : "+model.getAlGhost().get(i).getY());


		}
		




	}

}
