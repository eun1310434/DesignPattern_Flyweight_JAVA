package com.eun1310434.designpattern.flyweight;

import java.awt.*;
public class MyRect_C {
   private Color color = Color.black;
   
   //Example - C
   MyRect_C(Color color){
	      this.color = color;
   }
   
   public void draw(Graphics g, int upperX, int upperY, int lowerX, int lowerY) {
	      g.setColor(color);
	      g.fillRect(upperX, upperY, lowerX, lowerY);
   }
}