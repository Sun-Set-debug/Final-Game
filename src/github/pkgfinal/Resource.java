/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package github.pkgfinal;

import processing.core.PApplet;

/**
 * Resource class representing collectible or usable items in the game.
 * Includes HP recovery, stat upgrades, and key items.
 * Supports interaction with Player and modifies player attributes.
 * 
 * Extends MapItem as a world object.
 * 
 * @author fuche
 */
public class Resource extends MapItem{
    // Value of the resource (healing amount, stat increase, or key count)
    int value;
    // Type of resource (hp, atk, def, keyWood, keyStone, keyGold)
    String type;
    // Whether the resource has already been collected
    boolean picked = false;
    /**
    * Creates a resource item on the map.
    * 
    * @param x grid x position
    * @param y grid y position
    * @param person resource name
    * @param app Processing app
    * @param imagePath image path
    * @param value effect value of resource
    * @param type type of resource effect
    */
    public Resource(int x, int y, String person, PApplet app, String imagePath, int value, String type){
        super(x,y,person,app,imagePath);
        this.value = value;
        this.type = type;
        this.width = image.width;
        this.height = image.height;
    }
    /**
    * Defines interaction behavior when player collides with resource.
    * Automatically triggers pickup.
    */
    @Override
    public void interact(Player player){
        pickUp(player);
    }
    /**
    * Applies resource effect to player.
    * Supports HP, attack, defense, and key inventory.
    * 
    * @param player target player
    */
    public void pickUp(Player player){
        // Increase player stats based on resource type
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
