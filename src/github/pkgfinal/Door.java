/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package github.pkgfinal;

import processing.core.PApplet;

/**
 * Door object in the game.
 * Handles locked doors, key checking,
 * and map transition between different areas.
 * 
 * @author fuche
 */
public class Door extends MapItem{
    String doorType;
    boolean passable = false;
    int sx, sy, mapChange;
    /**
    * Creates a locked door that requires a key.
    * 
    * @param x x position
    * @param y y position
    * @param person object name
    * @param app Processing application
    * @param imagePath image file path
    * @param doorType type of key required
    */
    public Door(int x, int y, String person, PApplet app, String imagePath, String doorType){
        super(x,y,person,app,imagePath);
        this.doorType = doorType;
    }
    /**
    * Creates a map transition door.
    * Player will move to another map when entering.
    * 
    * @param x x position
    * @param y y position
    * @param person object name
    * @param app Processing application
    * @param imagePath image file path
    * @param sx destination x coordinate
    * @param sy destination y coordinate
    * @param mapChange destination map index
    */
    public Door(int x, int y, String person, PApplet app, String imagePath, int sx, int sy, int mapChange){
        super(x,y,person,app,imagePath);
        doorType = "nextMap";
        passable = true;
        this.sx = sx;
        this.sy = sy;
        this.mapChange = mapChange;
    }
    /**
    * Overrides interact method from MapItem.
    * Opens the door when player touches it.
    * 
    * @param player current player object
    */
    @Override
    public void interact(Player player){
        open();
    }
    /**
    * Opens the door if player has correct key.
    * Consumes one key after opening.
    */
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
    /**
    * Checks collision between door and another object.
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
