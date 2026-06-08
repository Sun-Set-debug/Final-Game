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
    boolean defeated;
    boolean showInfo;
    int damage;
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
        while (health > 0){
            health -= Math.max(0,player.atk - def);
            if (player.atk <= def){
                damage = 999999;
                break;
            }
            damage += Math.max(0,atk - player.def);
        }
    }
    public void fight(Player player){
        player.hp -= damage;
        Player.gold += reward;
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
        app.text("Hp: " + hp + ", Atk: " + atk, x + 50, y + 20);
        app.text("Gold: " + reward + ", Def: " + def, x + 50, y + 40);
        app.text("Damage: " + damage, x + 50, y + 60);
    }
    public void setSite(int x, int y){
        this.x = x * 48;
        this.y = y * 48;
    }
}
