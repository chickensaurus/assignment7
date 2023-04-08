package edu.nyu.cs;

import processing.core.PApplet;
import processing.core.PImage;

public class Mole {
    // instance properties
    private Game app; // will hold a reference to the main Game object
    private PImage img; // will hold a reference to an image of a mole
    private int x; // will hold the x coordinate of this object on the screen
    private int y; // will hold the y coordinate of this object on the screen
    private Boolean visible; // holds boolean for whether the mole is up and visible
    private int moleScore; // holds score each mole will give when hit

    /**
     * Constructor to create a Mole object at a specific position on the screen
     * @param app a reference to the Game object that created this object
     * @param imgFilePath the file path for the image
     * @param x the x coordinate of this object on the screen
     * @param y the y coordinate of this object on the screen
     */
    public Mole(Game app, String imgFilePath, int x, int y) {
        this.app = app; // store a reference to the main game object
        this.visible = false;
        this.moleScore=300;

        // load the specified image
        this.img = app.loadImage(imgFilePath);

        // store the x and y coordinates of this object on the screen
        this.x = x;
        this.y = y;
    }

    /**
     * Draw this mole's image to the screen at the appropriate coordinates
     */
    public void draw() {
        // draw this object's image at its x and y coordinates
        if (visible==true) {
            this.app.imageMode(PApplet.CENTER); // setting so the image is drawn centered on the specified x and y coordinates
            this.app.image(this.img, this.x, this.y);
        }
    }

    /**
     * each mole randomly appears, stays for a random duration, then disappears
     * the score given to the player for hitting each mole will decrease the longer has appeared
     */
    public void appear() {
        double rand = Math.random();
        this.moleScore-=1;
        if (rand<0.01 && this.visible==false) {
            this.visible=true;
            this.moleScore=240;
        }
        else if (rand>0.99 && this.visible==true) {
            this.visible=false;
        }
    }

    /**
    * getter for how many points each mole will give
    * gives minimum of 50 points
    @return int for number of points
	*/
    public int getMoleScore() {
        if (this.moleScore>50) {
            return this.moleScore;
        }
        else {
            return 50;
        }
    }

    /**
    * getter for the visibility of mole
    @return whether mole is visible
	*/
    public boolean getVisibility() {
        return this.visible;
    }

    /**
    * removes moles by setting visible to false
	*/
    public void remove() {
        this.visible=false;
    }

    /**
     * Determines whehter a given x, y coordinate overlaps with this Mole.
     * @param x The x coordinate of interest.
     * @param y The y coordinate of interest.
     * @param fudgeFactor An amount by which to expand the area we consider overlap
     * @return Boolean true if the x,y coordinate overlaps with this mole, false otherwise.
     */
    public boolean overlaps(int x, int y, int fudgeFactor) {
        // get the coordinates of all edges of this Mole's image
        int l = this.x - this.img.width/2 - fudgeFactor; // the left edge's x coord
        int r = this.x + this.img.width/2 + fudgeFactor; // the right edge's x coord
        int t = this.y - this.img.height/2 - fudgeFactor; // the top edge's y coord
        int b = this.y + this.img.height/2 + fudgeFactor; // the bottom edge's y coord
        // return whether the x,y coords are within the bounds of this Mole's image
        return (x > l && x < r && y > t && y < b);
    }

}
