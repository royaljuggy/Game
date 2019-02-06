package com.game;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -650585914685456797L;

	public Window(int width, int height, String title, Game game) {
		
		JFrame frame = new JFrame(title); // creating a JFrame, which is the application window
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // makes the 'x' button in the top right actually work - without this, the window would close but the game was still be running in the background
		frame.setResizable(false); // resizing would cause problems
		frame.setLocationRelativeTo(null); // without this, the window would start in the top left
		frame.add(game); // adds the game into the frame
		frame.setVisible(true);		
		game.start();
		
	}
}
