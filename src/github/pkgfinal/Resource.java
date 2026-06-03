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
public class Resource extends MapItem{
    int value;
    String type;
    public Resource(int x, int y, String person, PApplet app, String imagePath, int value, String type){
        super(x,y,person,app,imagePath);
        this.value = value;
        this.type = type;
    }
}
