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
public class MySketch extends PApplet{
    private Person user;
    private Person [] npcs = new Person[1];
    private Person [] monsters = new Person[1];
    private Button startButton;
    String map = "main";;
    
    @Override 
    public void settings(){
        size(672,528);
    }
    @Override 
    public void setup(){
        background(255);
        textSize(20);
        //characters
        user = new Player(100,100,"Houyi", this, "images/houyi.png", 1000, 10, 10);
        npcs[0] = new NPC(200,200,"Kevin", this, "images/person.png", "Hou Yi's kindness can never be repaid!\n\\(^0^)/");
        monsters[0] = new Monster(100,300,"bird", this, "images/defeatedBird.png", 1000, 20 , 0, 20);
        startButton = new Button(this, 50, 50, "images/start-button.png");
    }
    @Override
    public void draw(){
        switch (map){
            case "main" -> {
                background(255);
                fill(0);
                startButton.draw();
            }
            case "village" -> {
                background(240);
                fill(0);
                user.draw();
                npcs[0].draw();
                monsters[0].draw();
            }
            default -> {
            }
        }
        if (!map.equals("main")){
            if (keyPressed){
                Player player = (Player) user;
                switch (keyCode) {
                    case LEFT -> player.move(-5, 0);
                    case RIGHT -> player.move(5, 0);
                    case UP -> player.move(0, -5);
                    case DOWN -> player.move(0, 5);
                    default -> {
                }
            }
        }
        }
    }
    @Override
    public void mousePressed(){
        if (map.equals("main")){
            if (startButton.isClicked(mouseX, mouseY)){
                map = "village";
            }
        }
    }
}
