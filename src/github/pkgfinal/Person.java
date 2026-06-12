/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package github.pkgfinal;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Base class for all character objects in the game.
 * Stores position, image, size, and common interaction methods.
 * Parent class of Player, NPC and Monster.
 * 
 * @author fuche
 */
public class Person {
    // Screen position
    int x;
    int y;
    // Object name and image
    String person;
    PImage image;
    PApplet app;
    // Object dimensions
    int width;
    int height;
    /**
    * Creates a game character object.
    * Converts grid coordinates into screen coordinates.
    * 
    * @param x grid x coordinate
    * @param y grid y coordinate
    * @param person character name
    * @param app Processing application
    * @param imagePath image file path
    */
    public Person(int x, int y, String person, PApplet app, String imagePath){
        this.x = x * 48 + 144;
        this.y = y * 48;
        this.person = person;
        this.app = app;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }
    /**
    * Draws character image on screen.
    */
    public void draw(){
        app.image(image,x,y);
    }
    /**
    * Checks whether mouse clicks on the object.
    * 
    * @param mouseX mouse x coordinate
    * @param mouseY mouse y coordinate
    * @return true if object is clicked
    */
    public boolean isClicked(int mouseX, int mouseY){
        return mouseX >= x && mouseX <= x + image.pixelWidth &&
           mouseY >= y && mouseY <= y + image.pixelHeight;
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
}
