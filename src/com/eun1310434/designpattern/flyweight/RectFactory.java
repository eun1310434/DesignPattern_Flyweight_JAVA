package com.eun1310434.designpattern.flyweight;
//This factory only creates a new rectangle if it 
//uses a color not previously used

//Intrinsic State: Color
//Extrinsic State: X & Y Values

import java.util.HashMap;
import java.awt.Color;
public class RectFactory {
	
	// The HashMap uses the color as the key for every 
	// rectangle it will make up to 8 total
	private static final HashMap<Color, MyRect_C> rectsByColor = new HashMap<Color, MyRect_C>();

	public static MyRect_C getRect(Color color) {

		//9개의 색상을 중심으로 이미 만들어진 그래픽의 경우 새롭게 생성하지 않고 재사용한다.-> 메모리 효율을 높임
		// Checks if a rectangle with a specific color has been made. 
		MyRect_C rect = (MyRect_C)rectsByColor.get(color);
		
		if(rect == null) {
			//If not it makes a new one, otherwise it returns one made already
			
			rect = new MyRect_C(color);
			// Add new rectangle to HashMap
			rectsByColor.put(color, rect);
		}
		return rect;
	}
}