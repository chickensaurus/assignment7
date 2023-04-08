package edu.nyu.cs;

import processing.core.PApplet;
import processing.core.PImage;

public class Player {
    private Game app;
    private int x;
    private int y;
    private PImage imgL;
    private PImage imgR;
    private boolean direction;

    /**
     * Constructor to create a Player object at a specific position on the screen
     * @param app a reference to the Game object that created this object
     * @param imgFilePathL image of player character when moving left
     * @param imgFilePathR image of player character when moving right
     * @param x the x coordinate of this object on the screen
     * @param y the y coordinate of this object on the screen
     */
    public Player(Game app, String imgFilePathL, String imgFilePathR, int x, int y) {
        this.app = app; // store a reference to the main game object

        // load the specified image
        this.imgL = app.loadImage(imgFilePathL);
        this.imgR = app.loadImage(imgFilePathR);

        // store the x and y coordinates of this object on the screen
        this.x = x;
        this.y = y;
    }
    /**
    * updates the player's current position and direction it is facing
	*/
    public void draw() {
        // draw this object's image at its x and y coordinates
        this.app.imageMode(PApplet.CENTER); // setting so the image is drawn centered on the specified x and y coordinates
        if (direction==true) {
            this.app.image(this.imgL, this.x, this.y);
        }
        else {
            this.app.image(this.imgR, this.x, this.y);
        }
    }

    /**
    * setter for the direction of the player
	*/
    public void setDirection(boolean a) {
        this.direction=a;
    }

    /**
    * getter for the x coordinate of the player
    @return x coordinate of player
	*/
    public int getX() {
        return this.x;
    }

    /**
    * getter for the y coordinate of the player
    @return y coordinate of player
	*/
    public int getY() {
        return this.y;
    }

    /**
    * moves the player up by decreasing by y coordinate if flag is on
	*/
    public void moveUp(boolean flag) {
        if (this.y>40 && flag) {
            this.y-=25;
        }
    }

    /**
    * moves the player down by increasing by y coordinate if flag is on
	*/
    public void moveDown(boolean flag) {
        if (this.y<610 && flag) {
            this.y+=25;
        }
    }

    /**
    * moves the player left by decreasing by x coordinate if flag is on
	*/
    public void moveLeft(boolean flag) {
        if (this.x>40 && flag) {
            this.x-=25;
        }
    }

    /**
    * moves the player right by increasing by x coordinate if flag is on
	*/
    public void moveRight(boolean flag) {
        if (this.x<610 && flag) {
            this.x+=25;
        }
    }

}