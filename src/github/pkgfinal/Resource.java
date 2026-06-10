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
public class Resource extends MapItem{
    int value;
    String type;
    boolean picked = false;
    public Resource(int x, int y, String person, PApplet app, String imagePath, int value, String type){
        super(x,y,person,app,imagePath);
        this.value = value;
        this.type = type;
        this.width = image.width;
        this.height = image.height;
    }
    public void pickUp(Player player){
        switch (type) {
            case "hp" -> player.hp += value;
            case "atk" -> player.atk += value;
            case "def" -> player.def += value;
            case "keyWood" -> Player.keyWood += value;
            case "keyStone" -> Player.keyStone += value;
            case "keyGold" -> Player.keyGold += value;
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
}
