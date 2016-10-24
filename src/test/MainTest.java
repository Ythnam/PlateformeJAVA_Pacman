package test;

import model.Model;
import model.Orange;

public class MainTest {

	public static void main(String[] args) {
		Orange o = new Orange(1,2);
		Model model = new Model();
		
		model.addToAlItems(o);
		System.out.println(model.getAlItems());
		

	}

}
