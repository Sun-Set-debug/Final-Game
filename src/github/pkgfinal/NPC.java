/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package github.pkgfinal;
import processing.core.PApplet;

/**
 * NPC class for non-player characters.
 * Handles dialogue interaction and message display.
 * 
 * @author fuche
 */
public class NPC extends Person{
    private String dialogue;
    boolean speak;
    /**
    * Creates an NPC with dialogue.
    * 
    * @param x grid x coordinate
    * @param y grid y coordinate
    * @param person NPC name
    * @param app Processing application
    * @param imagePath NPC image path
    * @param dialogue text displayed when interacting
    */
    public NPC(int x, int y, String person, PApplet app, String imagePath, String dialogue){
        super(x,y,person,app,imagePath);
        this.dialogue = dialogue;
        this.speak = false;
    }
    /**
    * Displays NPC dialogue box on screen.
    * 
    * @param app Processing application
    */
    public void speak(PApplet app){
        app.fill(255);
        app.stroke(0);
        app.strokeWeight(1);
        app.rect(200, 432, 416, 96, 10);
        app.fill(0);
        app.text(person +  ':', 220, 455);
        app.text(dialogue, 220, 477);
    }
    /**
    * Checks collision between monster and another object.
    * 
    * @param other object to check collision with
    * @return true if collision happens
    */
    public boolean isCollidingWithR(Person other) {
        // Check if the bounding boxes of the two persons intersect
        boolean isLeftOfOtherRight = x < other.x + other.width;
        boolean isRightOfOtherLeft = x + width > other.x;
        boolean isAboveOtherBottom = y < other.y + other.height;
        boolean isBelowOtherTop = y + height > other.y;
        return isLeftOfOtherRight && isRightOfOtherLeft 
          && isAboveOtherBottom && isBelowOtherTop;
    }
    /**
    * Returns x position of door.
    * 
    * @return x coordinate
    */
    public int getX(){
        return x;
    }
    /**
    * Returns y position of door.
    * 
    * @return y coordinate
    */
    public int getY(){
        return y;
    }
}
