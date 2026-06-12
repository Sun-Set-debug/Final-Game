/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package github.pkgfinal;

import processing.core.PApplet;
import processing.core.PImage;
/**
 * Parent class for all interactive map objects.
 * Stores position, image data and common behaviors
 * shared by doors and resources.
 * 
 * @author fuche
 */
public class MapItem {
    int x;
    int y;
    String person;
    PImage image;
    private PApplet app;
    int width;
    int height;
    /**
    * Creates a map object with position and image.
    * 
    * @param x grid x coordinate
    * @param y grid y coordinate
    * @param person object name
    * @param app Processing application
    * @param imagePath image file path
    */
    public MapItem(int x, int y, String person, PApplet app, String imagePath){
        this.x = x * 48 + 144;
        this.y = y * 48;
        this.person = person;
        this.app = app;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }
    /**
    * Draws the object on screen.
    */
    public void draw(){
        app.image(image,x,y);
    }
    /**
    * Defines interaction behavior when player touches object.
    * This method is overridden by child classes.
    * 
    * @param player current player object
    */
    public void interact(Player player) {}
}
