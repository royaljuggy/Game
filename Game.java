package com.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

	/**
	 * https://www.youtube.com/watch?v=0T1U0kbu1Sk <- part 2
	 * game loop is needed to allow the game to be able to update itself
	 */
	private static final long serialVersionUID = -1930825029999864569L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	private Thread thread; //how the entire game runs
	private boolean running = false; // tells us if the thread is running
	
	public Game() {
		new Window(WIDTH, HEIGHT, "Let's Build a Game!", this);
	}
	
	public static void main(String[] args) {
		new Game();

	}

	public synchronized void start() { //starts the thread
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() { //stops the thread
		
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		//game loop below, allows game to update itself
		//very popular, Notch even uses it
		
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while (delta >= 1) {
				tick();
				delta--;
			}
			
			if (running) {
				render();
				frames++;
			}
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
			
		}
		
		stop();
		
	}
	
	private void tick() {
		
	}
	
	private void render() {
		// what is a buffer strategy??
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3); // 3 is recommended, don't go higher - this statement creates 3 buffers in the game
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.dispose();
		bs.show();
	}
	
}
