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
public class NPC extends Person{
    private String dialogue;
    boolean speak;
    public NPC(int x, int y, String person, PApplet app, String imagePath, String dialogue){
        super(x,y,person,app,imagePath);
        this.dialogue = dialogue;
        this.speak = false;
    }
    public void speak(PApplet app){
        app.fill(0);
        app.text(person +  ':', x, y-50);
        app.text(dialogue, x, y-30);
    }
    public void setSite(int x, int y){
        this.x = x * 48;
        this.y = y * 48;
    }
}
