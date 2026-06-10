/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package github.pkgfinal;
import processing.core.PApplet;

/**
 *
 * @author fuche
 */
public class NPC extends Person{
    private String dialogue;
    boolean speak;
    public NPC(int x, int y, String person, PApplet app, String imagePath, String dialogue){
        super(x,y,person,app,imagePath);
        this.dialogue = dialogue;
        this.speak = false;
    }
    public void speak(PApplet app){
        app.fill(255);
        app.stroke(0);
        app.strokeWeight(1);
        app.rect(200, 432, 416, 96, 10);
        app.fill(0);
        app.text(person +  ':', 220, 455);
        app.text(dialogue, 220, 477);
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
