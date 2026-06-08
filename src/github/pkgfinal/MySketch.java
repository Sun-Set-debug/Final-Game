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
    int count = 0;
    private Player user;
    private NPC [][] npcs = new NPC[5][10];
    private Button startButton;
    private Person village;
    private Monster [][] monsters = new Monster[6][10];
    private Resource [][] keys = new Resource[6][10];
    private Resource [][] blood = new Resource[6][10];
    private Resource [][] property = new Resource[6][10];
    private Door [][] doors = new Door[6][10];
    
    int map = -1;//main map
    
    @Override 
    public void settings(){
        size(672,528);
    }
    @Override 
    public void setup(){
        background(255);
        village = new Person(0, 0, "village", this, "images/village.png");
        textSize(20);
        setUpMap0();
    }
    @Override
    public void draw(){
        
        if (user.death == true){
            lose();
        }
        background(255);
        fill(0);
        switch (map){
            case -1 -> {
                startButton.draw();
            }
            case 0 -> {//village
                village.draw();
            }
            default -> {
            }
        }
        if (map != -1){
            drawMap();
            userMove();
        }
    }
    @Override
    public void mousePressed(){
        if (map == -1){//main map
            if (startButton.isClicked(mouseX, mouseY)){
                map = 0;//village
            }
        }
        for(NPC npc: npcs[map]){
            if (npc != null){
                npc.speak = npc.isClicked(mouseX, mouseY);
            }
        }
        for(Monster m : monsters[map]){
            if (m != null){
                m.showInfo = m.isClicked(mouseX, mouseY);
            }
        }
    }
    public void drawMap(){
        text("HP: " + user.hp, 50, 20);
        text("Atk: " + user.atk, 50, 40);
        text("Def: " + user.def, 50, 60);
        text("Gold: " + Player.gold, 50, 80);
        text(Player.keyWood, 50, 100);
        text(Player.keyStone, 80, 100);
        user.draw();
        for (Resource k: keys[map]){
            if (k != null && !k.picked){
                k.draw();
                if (k.isCollidingWithR(user)){
                    k.pickUp(user);
                    k.picked = true;
                }
            }
        }
        for (Resource bl: blood[map]){
            if (bl != null && !bl.picked){
                bl.draw();
                if (bl.isCollidingWithR(user)){
                    bl.pickUp(user);
                    bl.picked = true;
                }
            }
        }
        for (Resource pro: property[map]){
            if (pro != null && !pro.picked){
                pro.draw();
                if (pro.isCollidingWithR(user)){
                    pro.pickUp(user);
                    pro.picked = true;
                }
            }
        }
        for (Monster m : monsters[map]){
            if (m != null && m.defeated == false){
                m.draw();
                m.damage(user);
                if (m.showInfo == true){
                    m.display();
                }
                if (m.isCollidingWithR((Player) user)){
                    m.fight((Player) user);
                }
            } 
        }
        for (NPC npc: npcs[map]){
            if (npc != null){
                npc.draw();
                if (npc.speak == true){
                    npc.speak(this);
                }
            }
        }
        for (Door door: doors[map]){
            if (door != null && !door.passable){
                door.draw();
                if (door.isCollidingWithR(user)){
                    door.open(user);
                }
            }
        }
    }
    public boolean canMove(int x, int y){
        for(Door door : doors[map]){
            if(door != null &&
                door.getX() == x &&
                door.getY() == y){
                if(door.doorType.equals("wall")){
                    return false;
                }
                if(door.open(user)){
                    door.passable = true;
                    return true;
                }
                return false;
            }
        }
        return true;
    }
    public void userMove(){
        count--;
        if (keyPressed && count <= 0){
            switch (keyCode) {
                case LEFT -> {
                    int nextX = user.getX() - 48;
                    int nextY = user.getY();
                    if(canMove(nextX,nextY)){
                        user.move(-1,0);
                    }
                }
                case RIGHT -> {
                    int nextX = user.getX() + 48;
                    int nextY = user.getY();
                    if(canMove(nextX,nextY)){
                        user.move(1,0);
                    }
                }
                case UP -> {
                    int nextX = user.getX();
                    int nextY = user.getY() - 48;
                    if(canMove(nextX,nextY)){
                        user.move(0,-1);
                    }
                }
                case DOWN -> {
                    int nextX = user.getX();
                    int nextY = user.getY() + 48;
                    if(canMove(nextX,nextY)){
                        user.move(0,1);
                    }
                }
                default -> {
                }
            }
            count = 10;
        }
    }
    public void lose(){
        if (user.hp <= 0){
            user.death = true;
        }
        map = -2;
        background(0);
        fill(255);
        textSize(50);
        text("GAME OVER",200,250);
    }
    public void setUpMap0(){
        startButton = new Button(this, 350, 350, "images/start-button.png");
        user = new Player(5,5,"Houyi", this, "images/houyi.png", 1000, 10, 10);
        npcs[0][0] = new NPC(4,2,"Kevin", this, "images/person.png", "Hou Yi's kindness can never be repaid!\n\\(^0^)/\\(^0^)/\\(^0^)/\\(^0^)/\\(^0^)/");
        monsters[0][0] = new Monster(2,8,"sunbird", this, "images/sunbird.png", 1000, 20 , 10, 20);
        monsters[0][1] = new Monster(8,0,"monster1", this, "images/defeatedBird.png", 100, 10, 0, 5);
        keys[0][0] = new Resource(8,1,"woodKey", this, "images/person.png", 1, "keyWood");
        keys[0][1] = new Resource(8,2,"stoneKey", this, "images/person.png", 1, "keyStone");
        blood[0][0] = new Resource(8,3,"redBlood", this, "images/person.png", 50, "hp");
        blood[0][1] = new Resource(8,4,"yellowBlood", this, "images/person.png", 100, "hp");
        blood[0][2] = new Resource(8,5,"greenBlood", this, "images/person.png", 200, "hp");
        property[0][0] = new Resource(8,6,"atk", this, "images/person.png", 10, "atk");
        property[0][1] = new Resource(8,7,"def", this, "images/person.png", 10, "def");
        doors[0][0] = new Door(8,8,"woodDoor", this, "images/person.png", "wood");
        doors[0][1] = new Door(8,9,"stoneDoor", this, "images/person.png", "stone");
    }
}
