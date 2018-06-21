package com.eun1310434.designpattern.flyweight;

//The Flyweight design pattern is used when you need to create a large number of similar objects
//To reduce memory this pattern shares Objects that are the same rather than creating new ones

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class FlyWeightTest extends JFrame{

	private static final long serialVersionUID = 1L;

	int windowWidth = 1750;
	int windowHeight = 1000;
	
	// A new rectangle is created only if a new color is needed
	Color[] shapeColor = {
			Color.orange, 
			Color.red, 
			Color.yellow,
			Color.blue, 
			Color.pink, 
			Color.cyan, 
			Color.magenta,
			Color.black, 
			Color.gray};
	
	public static void main(String[] args){
		new FlyWeightTest();
	}
	
	public FlyWeightTest(){
		
		// Create the frame, position it and handle closing it
		this.setSize(windowWidth,windowHeight);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Flyweight Test");
		
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		
		JPanel  drawingPanel  = new JPanel();

		JPanel btnPanel = new JPanel();
        
		JButton Btn_Example = new JButton("Btn_Example");
		Btn_Example.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						Graphics g = drawingPanel.getGraphics();
						long startTime = System.currentTimeMillis();
						
						for (int i = 0; i < 100000; ++i) {
							//Example_A(g);		
							//Example_B(g);	
							
							
							// Uses rectangles stored in the HashMap to speed up the program
							Example_C(g);
						}

						long endTime = System.currentTimeMillis();
						System.out.println("Example : " + (endTime - startTime) + " milliseconds");
					}
		});
		btnPanel.add(Btn_Example);
		
		contentPane.add(drawingPanel,  BorderLayout.CENTER);
		contentPane.add(btnPanel,  BorderLayout.SOUTH);

		this.add(contentPane);
		this.setVisible(true);
	}
	

	//
	private void Example_A(Graphics _g){
		_g.setColor(getRandColor()); 
		_g.fillRect(
				getRandX(),
				getRandY(),
				getRandX(),
				getRandY());
	}
	
	private void Example_B(Graphics _g){
		MyRect_B rectB = new MyRect_B(
				getRandColor(), 
				getRandX(),
				getRandY(), 
				getRandX(), 
				getRandY()); 
		rectB.draw(_g);
	}
	
	private void Example_C(Graphics _g){
		//9개의 색상을 중심으로 이미 만들어진 그래픽의 경우 새롭게 생성하지 않고 재사용한다.-> 메모리 효율을 높임
		MyRect_C rectC = RectFactory.getRect(getRandColor());
		rectC.draw(
				_g, 
				getRandX(), 
				getRandY(), 
				getRandX(), 
				getRandY());
	}
	
	
	// Picks random x & y coordinates
	private int getRandX() {
		return (int) (Math.random() * windowWidth);
	}

	private int getRandY() {
		return (int) (Math.random() * windowHeight);
	}

	// Picks a random Color from the 9 available
	private Color getRandColor() {
		Random randomGenerator = new Random();
		int randInt = randomGenerator.nextInt(9);
		return shapeColor[randInt];
	}

}