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
    private boolean defeated;
    public Monster(int x, int y, String person, PApplet app, String imagePath, int hp, int atk, int def, int reward){
        super(x,y,person,app,imagePath);
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.reward = reward;
        this.defeated = false;
    }
    public void fight(Player player){
        while (hp > 0){
            hp -= player.atk - def;
            if (hp <= 0){
                break;
            }
            player.hp -= atk - player.def;
        }
        Player.gold += reward;
        defeated = true;
    }
}
