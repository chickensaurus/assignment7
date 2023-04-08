package edu.nyu.cs;

import java.nio.file.Paths;
import java.util.ArrayList;

import org.apache.commons.lang3.SystemUtils;

import processing.core.*; // import the base Processing library
import processing.sound.*; // import the processing sound library

/**
 * a game of whack a mole with a player controlled character
 * uses w, a, s, d to move and space bar to whack
 * score appears on bottom of screen
 * 
 * @author Linyi Huang (github: chickensaurus)
 * @version 0.1
 */
public class Game extends PApplet {

  private SoundFile soundClick; // will refer to a sound file to play when the user clicks the mouse
  private PImage imgMe; // will hold a photo of me
  private ArrayList<Mole> moles; // will hold an ArrayList of Mole objects
  private int score = 0; // the user's score
  private Player player; // holds player


	/**
	 * This method will be automatically called by Processing when the program runs.
   * - Use it to set up the initial state of any instance properties you may use in the draw method.
	 */
	public void setup() {

    // load up a sound file and play it once when the user clicks
    String cwd = Paths.get("").toAbsolutePath().toString(); // the current working directory as an absolute path
		String path = Paths.get(cwd, "sounds", "ow.wav").toString();
    this.soundClick = new SoundFile(this, path);
    
    // load up an image of me
		path = Paths.get(cwd, "images", "background.png").toString();
    this.imgMe = loadImage(path);

    // some basic settings for when we draw shapes
    this.ellipseMode(PApplet.CENTER); // setting so ellipses radiate away from the x and y coordinates we specify.
    this.imageMode(PApplet.CENTER); // setting so the ellipse radiates away from the x and y coordinates we specify.

    // create some moles
    moles = new ArrayList<Mole>();
    for (int i=0; i<6; i++) {
      // create a mole and add it to the array list
      int[] x = {160,145,320,330,500,500}; //sets the coordinates where moles are to be drawn
      int[] y = {90,350,230,540,100,350};
  		path = Paths.get(cwd, "images", "mole.png").toString();
      Mole mole = new Mole(this, path, x[i], y[i]);
      this.moles.add(mole);
    }

    path = Paths.get(cwd, "images", "hammerL.png").toString();
    String path2 = Paths.get(cwd, "images", "hammerR.png").toString();
    player = new Player(this, path, path2, 325,325);
	}

	/**
	 * This method is called automatically by Processing every 1/60th of a second by default.
   * Draws background, each mole, player, and score
	 */
	public void draw() {
    image(this.imgMe, this.width / 2, this.height/2); // draw image to center of window

    // draw all moles to their current position
    for (int i=0; i<this.moles.size(); i++) {
      Mole mole = this.moles.get(i); // get the current Mole object from the ArrayList
      mole.appear(); 
      mole.draw(); // draw the mole to the screen
    }

    player.draw(); // draw player

    // show the score at the bottom of the window
    String scoreString = String.format("SCORE: %d", this.score);
    text(scoreString, this.width/2, this.height-50);

	}

	/**
	 * This method is automatically called by Processing every time the user presses a key.
	 * Checks if key is w, a, s, d, or space to move accordingly or to whack mole
	 */
	public void keyPressed() {
    // the `key` variable holds the char of the key that was pressed, the `keyCode` variable holds the ASCII/Unicode numeric code for that key.
		if (key=='w') {
      player.moveUp(true);
    }
    if (key=='a') {
      player.moveLeft(true);
      player.setDirection(true);
    }
    if (key=='s') {
      player.moveDown(true);
    }
    if (key=='d') {
      player.moveRight(true);
      player.setDirection(false);
    }
    
    if (key==' ') {
      for (int i=0; i<this.moles.size(); i++) {
        Mole mole = this.moles.get(i); // get the current Mole object from the ArrayList
        // check whether the position where the user clicked was within this mole's boundaries
        if (mole.overlaps(player.getX(), player.getY(), 50) && mole.getVisibility()==true) {
          mole.remove();
          // if so, award the user some points
          score += mole.getMoleScore();
          // play a ow sound
          this.soundClick.play();
        }
      }
    }
	}  
  /**
	 * This method is automatically called by Processing every time the user releases a key.
	 * Checks if key is w, a, s, or d to stop moving when user releases key.
	 */
  public void keyReleased() {
    if (key=='w') {
      player.moveUp(false);
    }
    if (key=='a') {
      player.moveLeft(false);
    }
    if (key=='s') {
      player.moveDown(false);
    }
    if (key=='d') {
      player.moveRight(false);
    }
  }

  /**
   * A method that can be used to modify settings of the window, such as set its size.
   * This method shouldn't really be used for anything else.  
   * Use the setup() method for most other tasks to perform when the program first runs.
   */
  public void settings() {
		size(650, 650); // set the map window size, using the OpenGL 2D rendering engine
		System.out.println(String.format("Set up the window size: %d, %d.", width, height));    
  }

  /**
   * The main function is automatically called first in a Java program.
   * When using the Processing library, this method must call PApplet's main method and pass it the full class name, including package.
   * You shouldn't need to modify this method.
   * 
   * @param args An array of any command-line arguments.
   */
  public static void main(String[] args) {
    // make sure we're using Java 1.8
		System.out.printf("\n###  JDK IN USE ###\n- Version: %s\n- Location: %s\n### ^JDK IN USE ###\n\n", SystemUtils.JAVA_VERSION, SystemUtils.getJavaHome());
		boolean isGoodJDK = SystemUtils.IS_JAVA_1_8;
		if (!isGoodJDK) {
			System.out.printf("Fatal Error: YOU MUST USE JAVA 1.8, not %s!!!\n", SystemUtils.JAVA_VERSION);
		}
		else {
			PApplet.main("edu.nyu.cs.Game"); // do not modify this!
		}
  }

}
