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
public class Monster extends Person{
    private int hp;
    private int atk;
    private int def;
    private int reward;
    boolean fight = true;
    boolean defeated;
    boolean showInfo;
    int damage;
    String damageExt;
    public Monster(int x, int y, String person, PApplet app, String imagePath, int hp, int atk, int def, int reward){
        super(x,y,person,app,imagePath);
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.reward = reward;
        this.defeated = false;
        this.showInfo = false;
    }
    public void damage(Player player){
        damage = 0;
        int health = hp;
        if (player.atk <= def){
            fight = false;
            damageExt = "???";
        }else{
            fight = true;
            while (health > 0){
                health -= Math.max(0,player.atk - def);
                if (health<=0) break;
                damage += Math.max(0,atk - player.def);
            }
            damageExt = damage + "";
        }
    }
    public void fight(Player player){
        if (fight){
            player.hp -= damage;
            Player.gold += reward;
        }else{
            player.hp = -9999;
        }
        defeated = true;
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
    public void display(){
        app.fill(0);
        app.text(hp, 90, 405);
        app.text(atk, 90, 430);
        app.text(def, 90, 455);
        app.text(reward, 100, 480);
        app.text(damageExt, 100, 505);
    }
    public void setSite(int x, int y){
        this.x = x * 48;
        this.y = y * 48;
    }
}
