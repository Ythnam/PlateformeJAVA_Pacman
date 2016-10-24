package test;

import model.Model;
import model.Orange;

public class MainTest {

	public static void main(String[] args) {
		Orange o = new Orange(1,2);
		Model model = new Model();
		
		model.addToAlItems(o);
		System.out.println(model.getAlItems());
		System.out.println(model.getAlItems().get(0).getX());
		System.out.println(model.getAlItems().get(0).getY());
		System.out.println(model.getAlItems().get(0).getName());
		System.out.println(model.getAlItems().get(0).getScore());
		System.out.println(model.getAlItems().get(0).getLevel().get(0));




	}

}
