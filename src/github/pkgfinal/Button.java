/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package github.pkgfinal;
/**
 *
 * @author fuche
 */
import processing.core.PApplet;
import processing.core.PImage;

public class Button {
  private PApplet p;
  private float x, y;
  private PImage img;

  // Constructor accepts the path to the PNG image file
  public Button(PApplet p, float x, float y, String imagePath) {
    this.p = p;
    this.x = x;
    this.y = y;
    this.img = p.loadImage(imagePath); // Load the PNG
  }

  public void draw() {
    // Draw the image at its designated x and y coordinates
    
p.image(img, x, y);
  }

  // Rectangular collision detection using image dimensions
  public boolean isClicked(float mx, float my) {
    return (mx >= x && mx <= x + img.width && my >= y && my <= y + img.height);
  }
}
