/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package github.pkgfinal;

import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author fuche
 */
public class Person {
    int x;
    int y;
    String person;
    PImage image;
    PApplet app;
    int width;
    int height;
    public Person(int x, int y, String person, PApplet app, String imagePath){
        this.x = x * 48 + 144;
        this.y = y * 48;
        this.person = person;
        this.app = app;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }
    public void draw(){
        app.image(image,x,y);
    }
    public boolean isClicked(int mouseX, int mouseY){
        return mouseX >= x && mouseX <= x + image.pixelWidth &&
           mouseY >= y && mouseY <= y + image.pixelHeight;
    }
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
