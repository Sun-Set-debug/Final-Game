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
public class Player extends Person{
    int hp;
    int atk;
    int def;
    static int gold = 0;
    static int keyWood = 0;
    static int keyStone = 0;
    public Player(int x, int y, String person, PApplet app, String imagePath, int hp, int atk, int def){
        super(x,y,person,app,imagePath);
        this.hp = hp;
        this.atk = atk;
        this.def = def;
    }
    public void move(int dx, int dy){
        x += dx;
        y += dy;
    }
    public void pickUp(Resource item){
        switch (item.type) {
            case "hp" -> hp += item.value;
            case "atk" -> atk += item.value;
            case "def" -> def += item.value;
            case "keyWood" -> keyWood += item.value;
            case "keyStone" -> keyStone += item.value;
            default -> {
            }
        }
    }
}
