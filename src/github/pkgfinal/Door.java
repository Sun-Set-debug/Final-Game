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
public class Door extends MapItem{
    String doorType;
    boolean passable;
    public Door(int x, int y, String person, PApplet app, String imagePath, String doorType, boolean passable){
        super(x,y,person,app,imagePath);
        this.doorType = doorType;
        passable = false;
    }
    public void open(Player player){
        if (doorType.equals("Wood")){
            if (player.keyWood > 0){
                player.keyWood -= 1;
                passable = true;
            }
        }else if (doorType.equals("Stone")){
            if(player.keyStone > 0){
                player.keyStone -= 1;
                passable = true;
            }
        }
    }
}
