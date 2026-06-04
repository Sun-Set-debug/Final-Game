/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package github.pkgfinal;
import processing.core.PApplet;
import processing.core.PImage;
/**
 *
 * @author fuche
 */
public class MySketch extends PApplet{
    private Player user;
    private NPC [] npcs = new NPC[1];
    private Monster [] monsters = new Monster[1];
    private Button startButton;
    private Person village;
    private PImage bg;
    String map = "main";;
    
    @Override 
    public void settings(){
        size(672,528);
    }
    @Override 
    public void setup(){
        background(255);
        village = new Person(0, 0, "village", this, "images/village.png");
        textSize(20);
        //characters
        user = new Player(250,250,"Houyi", this, "images/houyi.png", 1000, 10, 10);
        npcs[0] = new NPC(200,100,"Kevin", this, "images/person.png", "Hou Yi's kindness can never be repaid!\n\\(^0^)/\\(^0^)/\\(^0^)/\\(^0^)/\\(^0^)/");
        monsters[0] = new Monster(420,0,"bird", this, "images/defeatedBird.png", 1000, 20 , 0, 20);
        startButton = new Button(this, 50, 50, "images/start-button.png");
    }
    @Override
    public void draw(){
        background(255);
        fill(0);
        switch (map){
            case "main" -> {
                startButton.draw();
            }
            case "village" -> {
                village.draw();
                user.draw();
            }
            default -> {
            }
        }
        if (!map.equals("main")){
            text("HP: " + user.hp, 50, 20);
            text("Atk: " + user.atk, 50, 40);
            text("Def: " + user.def, 50, 60);
            text("Gold: " + Player.gold, 50, 80);
            text(Player.keyWood, 50, 100);
            text(Player.keyStone, 80, 100);
            for (NPC npc: npcs){
                npc.draw();
                if (npc.speak == true){
                    npc.speak(this);
                }
            }
            for (Monster m : monsters){
                if (m.defeated == false){
                    m.draw();
                    if (m.showInfo == true){
                        m.display();
                    }
                    if (m.isCollidingWithR((Player) user)){
                        m.fight((Player) user);
                    }
                } 
            }
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
        for(NPC npc: npcs){
            npc.speak = npc.isClicked(mouseX, mouseY);
        }
        for(Monster m : monsters){
            m.showInfo = m.isClicked(mouseX, mouseY);
        }
    }
    
}
