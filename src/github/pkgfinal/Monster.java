/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package github.pkgfinal;

import processing.core.PApplet;

/**
 * Monster class for enemy objects in the game.
 * Handles combat calculation, damage display,
 * rewards and collision detection.
 * 
 * @author fuche
 */
public class Monster extends Person{
    // Monster health points
    int hp;
    // Attack and defense stats
    private int atk;
    private int def;
    // Gold rewarded after defeating monster
    private int reward;
    // Whether player can defeat monster
    boolean fight = true;
    // Monster state
    boolean defeated;
    boolean showInfo;
    // Predicted damage to player
    int damage;
    String damageExt;
    /**
    * Creates a monster with combat attributes.
    * 
    * @param x grid x coordinate
    * @param y grid y coordinate
    * @param person monster name
    * @param app Processing application
    * @param imagePath monster image path
    * @param hp monster health
    * @param atk monster attack
    * @param def monster defense
    * @param reward gold reward after battle
    */
    public Monster(int x, int y, String person, PApplet app, String imagePath, int hp, int atk, int def, int reward){
        super(x,y,person,app,imagePath);
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.reward = reward;
        this.defeated = false;
        this.showInfo = false;
    }
    /**
    * Calculates damage player will receive in battle.
    * Determines whether monster can be defeated.
    * 
    * @param player current player object
    */
    public void damage(Player player){
        // Simulate battle without changing actual monster health
        damage = 0;
        int health = hp;
        // Player cannot break monster defense
        if (player.atk <= def){
            fight = false;
            damageExt = "???";
        }else{
            fight = true;
            // Calculate turn-based combat damage
            while (health > 0){
                health -= Math.max(0,player.atk - def);
                if (health<=0) break;
                damage += Math.max(0,atk - player.def);
            }
            damageExt = damage + "";
        }
    }
    /**
     * Starts combat and updates player stats after battle.
     * 
     * @param player current player object
     */
    public void fight(Player player){
        if (fight){
            player.hp -= damage;
            Player.gold += reward;
        }else{
            // If player cannot win, force game over
            player.hp = -9999;
        }
        defeated = true;
    }
    /**
    * Checks collision between monster and another object.
    * 
    * @param other object to check collision with
    * @return true if collision happens
    */
    @Override
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
    * Displays monster combat information on side panel.
    */
    public void display(){
        app.fill(0);
        app.text(hp, 90, 405);
        app.text(atk, 90, 430);
        app.text(def, 90, 455);
        app.text(reward, 100, 480);
        app.text(damageExt, 100, 505);
    }
}
