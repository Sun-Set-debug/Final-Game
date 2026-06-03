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
public class MapItem {
    int x;
    int y;
    String person;
    PImage image;
    private PApplet app;
    private int width, height;
    public MapItem(int x, int y, String person, PApplet app, String imagePath){
        this.x = x;
        this.y = y;
        this.person = person;
        this.app = app;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }
    public void draw(){
        app.image(image,x,y);
    }
}
