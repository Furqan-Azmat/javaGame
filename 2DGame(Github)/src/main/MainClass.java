package main;

import javax.swing.SwingUtilities;

/**
 * 
 * Main class from where out game runs 
 * 
 * 
 * 
 * @author Furqan, Licia, Farhana
 *
 */

public class MainClass {
	public static void main(String[] args) {
		 SwingUtilities.invokeLater(() -> {
		        new GameWindow();
		    });
	}	

}
