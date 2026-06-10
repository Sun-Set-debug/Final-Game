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
    int timecount = 0;
    String[] mapLines;
    char[][][] mapData = new char [4][][];
    private Player user;
    private NPC [][] npcs = new NPC[5][10];
    private Button startButton;
    private Person village;
    private Person UI;
    private Monster [][] monsters = new Monster[6][10];
    private Resource [][] keys = new Resource[6][10];
    private Resource [][] blood = new Resource[6][10];
    private Resource [][] property = new Resource[6][10];
    private Door [][] doors = new Door[6][10];
    boolean shop = false;
    int map = -1;//main map
    int shopcost;
    PImage wallImg;
    @Override 
    public void settings(){
        size(672,528);
    }
    @Override 
    public void setup(){
        background(255);
        village = new Person(-3, 0, "village", this, "images/village.png");
        UI = new Person(-3,0,"UI", this, "images/UI.png");
        wallImg = loadImage("images/person.png");
        textSize(20);
        startButton = new Button(this, 300, 350, "images/start-button.png");
        user = new Player(5,5,"Houyi", this, "images/houyi.png", 1000, 5, 5);
        shopcost = 10;
        mapLines = loadStrings("maps/map0.txt");
        setUpMap0();
        setUpMap1();
        setUpMap2();
        setUpMap3();
        loadMap(0,"maps/map0.txt");
        loadMap(1,"maps/map1.txt");
        loadMap(2,"maps/map2.txt");
        loadMap(3,"maps/map3.txt");
    }
    public void loadMap(int mapIndex,String fileName){

        mapLines = loadStrings(fileName);

        mapData[mapIndex] = new char[mapLines.length][];

        for(int i=0;i<mapLines.length;i++){
        mapData[mapIndex][i] = mapLines[i].toCharArray();
        }
    }
    @Override
    public void draw(){
        if (timecount > 0) timecount--;
        if (user.hp <= 0){
            map = -2;//death
        }
        background(255);
        fill(0);
        switch (map){
            case -1 -> {//main
                startButton.draw();
            }
            case 0 -> {//village
                village.draw();
            }
            case 1 -> {
                
            }
            case -2 -> {//death
                lose();
            }
            default -> {
            }
        }
        if (map >= 0){
            shop();
            if (!shop){
                information();
                drawMap();
                userMove();
            }else{
                drawShop();
            }
        }
    }
    @Override
    public void mousePressed(){
        if (map == -1){//main map
            if (startButton.isClicked(mouseX, mouseY)){
                map = 0;//village
            }
        }
        if (map >= 0){
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
    }
    public void shop(){
        if (shop == true){
            drawShop();
            if (keyPressed && Player.gold >= shopcost && timecount <= 0){
                switch(key){
                    case '1' -> {user.hp += 100;
                    Player.gold -= shopcost;
                    shopcost++;}
                    case '2' -> {user.atk += 2;
                    Player.gold -= shopcost;
                    shopcost++;}
                    case '3' -> {user.def += 2;
                    Player.gold -= shopcost;
                    shopcost++;}
                }
                timecount = 10;
            }
        }
        if (keyPressed && key == 'v' && timecount <= 0){
            shop = !shop;
            timecount = 10;
        }
    }
    
    public void drawShop(){
        background(30);
        fill(255);
        textSize(30);
        text("Strengthening requires " + shopcost + " materials", 150, 100);
        textSize(18);
        text("Material:" + Player.gold, 250, 150);
        text("1. +100 HP", 250, 220);
        text("2. +2 ATK", 250, 260);
        text("3. +2 DEF", 250, 300);
        text("Press V to exit", 250, 380);
    }
    
    public void drawMap(){
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
                if (m.isCollidingWithR(user)){
                    m.fight(user);
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
            if (door != null && !door.passable && !door.doorType.equals("nextMap")){
                door.draw();
                if (door.isCollidingWithR(user)){
                    door.open();
                }
            }
            if (door != null && door.doorType.equals("nextMap")){
                door.draw();
                if (door.isCollidingWithR(user)){
                    map = door.mapChange;
                    user.setSite(door.sx,door.sy);
                    break;
                }
            }
        }
        for(int row=0;row<mapData[map].length;row++){
            for(int col=0;col<mapData[map][row].length;col++){
                if(mapData[map][row][col]=='w'){
                    image(
                        wallImg,
                        col*48 + 144,
                        row*48,
                        48,
                        48
                    );
                }
            }
        }
    }
    public boolean canMove(int x, int y){
        if (x < 144 || x >= 14 * 48 || y < 0 || y >= 11 * 48) {
            return false;
        }
        int col = (x - 144) / 48;
        int row = y / 48;

        if(mapData[map][row][col] == 'w'){
            return false;
        }
        for(NPC npc : npcs[map]){
            if(npc != null &&
                npc.getX() == x &&
                npc.getY() == y){
                return false;
            }
        }
        for(Door door : doors[map]){
            if(door != null &&
                door.getX() == x &&
                door.getY() == y){
                if(door.doorType.equals("wall")){
                    return false;
                }
                door.open();
                return door.passable;
            }
        }
        return true;
    }
    public void userMove(){
        if (keyPressed && timecount <= 0){
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
            timecount = 10;
        }
    }
    public void lose(){
        background(0);
        fill(255);
        textSize(50);
        text("GAME OVER",200,250);
    }
    public void information(){
        UI.draw();
        text(user.hp, 80, 70);
        text(user.atk, 80, 100);
        text(user.def, 80, 130);
        text(Player.gold, 100, 160);
        text(Player.keyWood, 110, 250);
        text(Player.keyStone, 110, 280);
        text(Player.keyGold, 110, 310);
    }
    public void setUpMap0(){
        npcs[0][5] = new NPC(4,2,"Kevin", this, "images/person.png", "Hou Yi's kindness can never be repaid!\n\\(^0^)/\\(^0^)/\\(^0^)/\\(^0^)/\\(^0^)/");
        npcs[0][0] = new NPC(4, 2, "StoryNPC1", this, "images/person.png",
            "十日并出，天下大乱。");
        npcs[0][1] = new NPC(6, 2, "StoryNPC2", this, "images/person.png",
            "你需要一路前往山巅击落金乌。");
        npcs[0][2] = new NPC(3, 3, "BattleGuide", this, "images/person.png",
            "点击怪物可以查看属性，进入战斗前请确认是否能击败。");
        npcs[0][3] = new NPC(7, 3, "ShopGuide", this, "images/person.png",
            "铁匠可以强化你的属性，但需要素材。");
        npcs[0][4] = new NPC(8, 4, "Blacksmith", this, "images/person.png",
            "我可以强化你的能力（按V进入商店）。");
        blood[0][0] = new Resource(2, 6, "redBlood", this, "images/person.png", 50, "hp");
        blood[0][1] = new Resource(3, 6, "yellowBlood", this, "images/person.png", 100, "hp");
        blood[0][2] = new Resource(4, 6, "greenBlood", this, "images/person.png", 200, "hp");
        property[0][0] = new Resource(6, 6, "atkStone", this, "images/person.png", 1, "atk");
        property[0][1] = new Resource(7, 6, "defStone", this, "images/person.png", 1, "def");
        doors[0][0] = new Door(6, 0, "exit", this, "images/person.png", 6, 9, 1);
    }
    public void setUpMap1(){
        doors[1][0] = new Door(6,10,"return", this, "images/person.png", 6, 1, 0);
        npcs[1][0] = new NPC(7,8,"keyIntroduction", this, "images/person.png", "不同的障碍需要不同的箭矢来清除。");
        npcs[1][1] = new NPC(8,5,"passIntroduction", this, "images/person.png", "有些东西是不需要拿取的");
        npcs[1][2] = new NPC(2,6,"doorIntroduction", this, "images/person.png", "不是所有的门都需要开启");
        doors[1][1] = new Door(1,9,"goldDoor", this, "images/person.png", "Gold");
        doors[1][2] = new Door(3,9,"stoneDoor", this, "images/person.png", "Stone");
        doors[1][3] = new Door(5,9,"Wooddoor", this, "images/person.png", "Wood");
        doors[1][4] = new Door(4,5,"Wooddoor", this, "images/person.png", "Wood");
        doors[1][5] = new Door(6,6,"Wooddoor", this, "images/person.png", "Wood");
        doors[1][6] = new Door(7,2,"Wooddoor", this, "images/person.png", "Wood");
        doors[1][7] = new Door(9,2,"Wooddoor", this, "images/person.png", "Wood");
        doors[1][8] = new Door(1,2,"stoneDoor", this, "images/person.png", "Stone");
        keys[1][0] = new Resource(8,8,"woodKey", this, "images/person.png", 1, "keyWood");
        keys[1][1] = new Resource(9,8,"stoneKey", this, "images/person.png", 1, "keyStone");
        keys[1][2] = new Resource(9,9,"goldKey", this, "images/person.png", 1, "keyGold");
        keys[1][3] = new Resource(2,5,"woodKey", this, "images/person.png", 1, "keyWood");
        keys[1][4] = new Resource(8,3,"woodKey", this, "images/person.png", 1, "keyWood");
        keys[1][5] = new Resource(2,3,"stoneKey", this, "images/person.png", 1, "keyStone");
        keys[1][6] = new Resource(9,1,"goldKey", this, "images/person.png", 1, "keyGold");
        monsters[1][0] = new Monster(1,7,"monster1", this, "images/person.png", 100,9,3,1);
        monsters[1][1] = new Monster(4,7,"monster1", this, "images/person.png", 100,9,3,1);
        monsters[1][2] = new Monster(6,3,"monster2", this, "images/person.png", 70,15,2,2);
        monsters[1][4] = new Monster(3,3,"monster3", this, "images/person.png", 120,15,6,3);
        monsters[1][5] = new Monster(2,1,"monster4", this, "images/person.png", 220,16,8,4);
        blood[1][0] = new Resource(1, 6, "greenBlood", this, "images/person.png", 200, "hp");
        blood[1][1] = new Resource(1, 1, "greenBlood", this, "images/person.png", 200, "hp");
        blood[1][2] = new Resource(8, 6, "greenBlood", this, "images/person.png", 200, "hp");
        property[1][0] = new Resource(9, 6, "atkStone", this, "images/person.png", 1, "atk");
        property[1][1] = new Resource(9, 5, "defStone", this, "images/person.png", 1, "def");
        property[1][2] = new Resource(7, 1, "atkStone", this, "images/person.png", 1, "atk");
        property[1][3] = new Resource(4, 1, "atkStone", this, "images/person.png", 1, "atk");
        property[1][4] = new Resource(5, 1, "defStone", this, "images/person.png", 1, "def");
        doors [1][9] = new Door(1 ,0,"return", this, "images/person.png", 1, 9, 2);
    }
    public void setUpMap2(){
        doors [2][0] = new Door(1 ,10,"return", this, "images/person.png", 1, 1, 1);
        doors[2][1] = new Door(1,2,"stoneDoor", this, "images/person.png", "Stone");
        doors[2][2] = new Door(9,6,"Wooddoor", this, "images/person.png", "Wood");
        doors[2][4] = new Door(1,3,"Wooddoor", this, "images/person.png", "Wood");
        keys[2][0] = new Resource(7,8,"woodKey", this, "images/person.png", 1, "keyWood");
        keys[2][1] = new Resource(7,7,"stoneKey", this, "images/person.png", 1, "keyStone");
        keys[2][2] = new Resource(9,9,"woodKey", this, "images/person.png", 1, "keyWood");
        keys[2][3] = new Resource(3,3,"woodKey", this, "images/person.png", 1, "keyWood");
        keys[2][4] = new Resource(5,7,"woodKey", this, "images/person.png", 1, "keyWood");
        blood[2][0] = new Resource(1, 6, "yellowBlood", this, "images/person.png", 100, "hp");
        blood[2][1] = new Resource(2, 7, "greenBlood", this, "images/person.png", 200, "hp");
        blood[2][2] = new Resource(2, 6, "greenBlood", this, "images/person.png", 200, "hp");
        blood[2][3] = new Resource(1, 4, "greenBlood", this, "images/person.png", 200, "hp");
        blood[2][4] = new Resource(4, 3, "greenBlood", this, "images/person.png", 200, "hp");
        blood[2][5] = new Resource(8, 1, "greenBlood", this, "images/person.png", 200, "hp");
        blood[2][6] = new Resource(9, 1, "greenBlood", this, "images/person.png", 200, "hp");
        blood[2][8] = new Resource(9, 8, "redBlood", this, "images/person.png", 50, "hp");
        monsters[2][0] = new Monster(1,8,"monster2", this, "images/person.png", 70,15,2,2);
        monsters[2][1] = new Monster(4,8,"monster2", this, "images/person.png", 70,15,2,2);
        monsters[2][3] = new Monster(9,7,"monster2", this, "images/person.png", 70,15,2,2);
        monsters[2][4] = new Monster(4,4,"monster3", this, "images/person.png", 120,15,6,3);
        monsters[2][5] = new Monster(6,3,"monster4", this, "images/person.png", 220,16,8,4);
        monsters[2][6] = new Monster(6,9,"monster5", this, "images/person.png", 120,11,5,2);
        monsters[2][7] = new Monster(4,2,"monster6", this, "images/person.png", 150,25,4,4);
        monsters[2][8] = new Monster(6,1,"monster7", this, "images/person.png", 320,20,10,5);
        property[2][0] = new Resource(2, 1, "atkStone", this, "images/person.png", 2, "atk");
        property[2][1] = new Resource(3, 1, "defStone", this, "images/person.png", 2, "def");
        property[2][2] = new Resource(4, 6, "atkStone", this, "images/person.png", 2, "atk");
        property[2][3] = new Resource(4, 7, "atkStone", this, "images/person.png", 2, "atk");
        property[2][4] = new Resource(8, 3, "atkStone", this, "images/person.png", 2, "atk");
        property[2][5] = new Resource(9, 3, "defStone", this, "images/person.png", 2, "def");
        doors[2][6] = new Door(6 ,0,"return", this, "images/person.png", 6, 9, 3);
    }
    public void setUpMap3(){
        doors[3][0] = new Door(6 ,10,"return", this, "images/person.png", 6, 1, 2);
        blood[3][0] = new Resource(7, 7, "yellowBlood", this, "images/person.png", 100, "hp");
        blood[3][1] = new Resource(8, 9, "yellowBlood", this, "images/person.png", 100, "hp");
        blood[3][2] = new Resource(2, 9, "redBlood", this, "images/person.png", 50, "hp");
        blood[3][3] = new Resource(3, 9, "greenBlood", this, "images/person.png", 200, "hp");
        blood[3][4] = new Resource(3, 7, "yellowBlood", this, "images/person.png", 100, "hp");
        doors[3][1] = new Door(5,5,"golddoor", this, "images/person.png", "Gold");
        doors[3][2] = new Door(6,7,"Wooddoor", this, "images/person.png", "Wood");
        doors[3][3] = new Door(7,9,"Wooddoor", this, "images/person.png", "Wood");
        doors[3][4] = new Door(3,8,"Wooddoor", this, "images/person.png", "Wood");
        doors[3][5] = new Door(4,9,"stoneDoor", this, "images/person.png", "Stone");
        monsters[3][0] = new Monster(3,2,"Boss", this, "images/sunbird.png", 400,30,10,50);
    }
}
