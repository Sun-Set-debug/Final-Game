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
public class Door extends MapItem{
    String doorType;
    boolean passable = false;
    int sx, sy, mapChange;
    public Door(int x, int y, String person, PApplet app, String imagePath, String doorType){
        super(x,y,person,app,imagePath);
        this.doorType = doorType;
    }
    public Door(int x, int y, String person, PApplet app, String imagePath, int sx, int sy, int mapChange){
        super(x,y,person,app,imagePath);
        doorType = "nextMap";
        passable = true;
        this.sx = sx;
        this.sy = sy;
        this.mapChange = mapChange;
    }
    public void open(){
        switch (doorType) {
            case "Wood" -> {
                if (Player.keyWood > 0 && !passable){
                    Player.keyWood -= 1;
                    passable = true;
                }
            }
            case "Stone" -> {
                if(Player.keyStone > 0 && !passable){
                    Player.keyStone -= 1;
                    passable = true;
                }
            }
            case "Gold" -> {
                if(Player.keyGold > 0 && !passable){
                    Player.keyGold -=1;
                    passable = true;
                }
            }
            default -> {
            }
        }
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
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
