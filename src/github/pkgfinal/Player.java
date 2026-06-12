/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package github.pkgfinal;

import processing.core.PApplet;

/**
 * Player class representing the main controllable character.
 * Stores player attributes, inventory resources,
 * movement and position management.
 * 
 * Extends Person as a specialized game character.
 * 
 * @author fuche
 */
public class Player extends Person{
    //player information
    int hp = 1;
    int atk;
    int def;
    // Shared inventory resources
    static int gold = 0;
    static int keyWood = 0;
    static int keyStone = 0;
    static int keyGold = 0;
    /**
    * Creates player object with initial combat stats.
    * 
    * @param x grid x coordinate
    * @param y grid y coordinate
    * @param person player name
    * @param app Processing application
    * @param imagePath player image path
    * @param hp starting health points
    * @param atk starting attack value
    * @param def starting defense value
    */
    public Player(int x, int y, String person, PApplet app, String imagePath, int hp, int atk, int def){
        super(x,y,person,app,imagePath);
        this.hp = hp;
        this.atk = atk;
        this.def = def;
    }
    /**
    * Moves player by grid coordinates.
    * 
    * @param dx horizontal movement
    * @param dy vertical movement
    */
    public void move(int dx, int dy){
        // Movement based on tile size (48 pixels)
        x += dx * 48;
        y += dy * 48;
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
    /**
    * Sets player position directly.
    * Used when changing maps.
    * 
    * @param dx new grid x coordinate
    * @param dy new grid y coordinate
    */
    public void setSite(int dx, int dy){
        // Convert grid coordinates to screen coordinates
        this.x = dx * 48 + 144;
        this.y = dy * 48;
    }
}
