/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package github.pkgfinal;
/**
 * Button class for clickable UI elements.
 * Handles button drawing and mouse click detection.
 * 
 * @author fuche
 */
import processing.core.PApplet;
import processing.core.PImage;
//creat Button
public class Button {
  private PApplet p;
  private float x, y;
  private PImage img;

    /**
    * Creates a button object with image and position.
    * 
    * @param p Processing application
    * @param x x position
    * @param y y position
    * @param imagePath button image file path
    */
    public Button(PApplet p, float x, float y, String imagePath) {
        this.p = p;
        this.x = x;
        this.y = y;
        this.img = p.loadImage(imagePath); // Load the PNG
    }

    public void draw() {
        // Draw the image at its designated x and y coordinates
        p.image(img, x, y);
    }

    /**
    * Checks whether mouse clicked inside button area.
    * 
    * @param mx mouse x coordinate
    * @param my mouse y coordinate
    * @return true if button is clicked
    */
    public boolean isClicked(float mx, float my) {
        return (mx >= x && mx <= x + img.width && my >= y && my <= y + img.height);
    }
}
