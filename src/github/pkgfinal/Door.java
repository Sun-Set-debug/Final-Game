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
    public Door(int x, int y, String person, PApplet app, String imagePath, String doorType){
        super(x,y,person,app,imagePath);
        this.doorType = doorType;
    }
    public boolean open(Player player){
        if (doorType.equals("wood")){
            if (player.keyWood > 0){
                player.keyWood -= 1;
                passable = true;
                return true;
            }
        }else if (doorType.equals("stone")){
            if(player.keyStone > 0){
                player.keyStone -= 1;
                passable = true;
                return true;
            }
        }
        return false;
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
