/*Processing 2.1
 * PeasyCam 2
 * ControlP5 
 * 
 * 
 */

package SkyscraperOptimization;

import javax.swing.*;
import java.io.*;
import peasy.*;
import processing.opengl.*;
import processing.core.*;
import controlP5.*;

public class Main extends PApplet {

	PeasyCam cam;
	ControlP5 cp5;
	//PMatrix3D currCameraMatrix;
	//PGraphics3D g3;

	int feet = 12;
	int def;

	ControlGroup messageBox;
	int messageBoxResult = -1;
	String messageBoxString = "";
	float t;

	public void setup() {

		size(1200, 900, OPENGL);
		//g3 = (PGraphics3D) g;
		cam = new PeasyCam(this, 300 * feet);
		//cam.setMinimumDistance(2 * feet);
		//cam.setMaximumDistance(2000 * feet);

		cp5 = new ControlP5(this);

		Group g1 = cp5.addGroup("g1")
				.setPosition(50, 50)
				.setBackgroundHeight(100)
				.setBackgroundColor(color(250,50)).bringToFront()
				;

		cp5.addBang("A-1")
		.setPosition(10, 20)
		.setSize(80, 20)
		.setGroup(g1)
		;

		cp5.addBang("A-2")
		.setPosition(10, 60)
		.setSize(80, 20)
		.setGroup(g1)
		;


		Group g2 = cp5.addGroup("g2")
				.setPosition(50,150)
				.setWidth(300)
				.activateEvent(true)
				.setBackgroundColor(color(50,100))
				.setBackgroundHeight(100)
				.setLabel("Hello World.")
				.bringToFront()
				.setMoveable(true)
				;

		cp5.addSlider("S-1")
		.setPosition(80,10)
		.setSize(180,9)
		.setGroup(g2)
		;

		cp5.addSlider("S-2")
		.setPosition(80,20)
		.setSize(180,9)
		.setGroup(g2)
		;

		cp5.addRadioButton("radio")
		.setPosition(10,10)
		.setSize(20,9)
		.addItem("black",0)
		.addItem("red",1)
		.addItem("green",2)
		.addItem("blue",3)
		.addItem("grey",4)
		.setGroup(g2)
		;
		
		cp5.setAutoDraw(false);

	}


	public void draw() {
		
		background(100);
		
		//hint(ENABLE_DEPTH_TEST);
		pushMatrix();
		translate(width/2,height/2,mouseX);
		rotateY(t+=0.1);
		fill(255);
		rect(-50,-50,100,100);
		popMatrix();
		//hint(DISABLE_DEPTH_TEST);
		gui();
		//camera();
	}
	
	void gui() {
		hint(DISABLE_DEPTH_TEST);
		cam.beginHUD();
		cp5.draw();
		cam.endHUD();
		hint(ENABLE_DEPTH_TEST);
	}

	public void controlEvent(ControlEvent theEvent) {
		if(theEvent.isGroup()) {
			println("got an event from group "
					+theEvent.getGroup().getName()
					+", isOpen? "+theEvent.getGroup().isOpen()
					);

		} else if (theEvent.isController()){
			println("got something from a controller "
					+theEvent.getController().getName()
					);
		}
	}


	public void keyPressed() {
		if(key==' ') {
			if(cp5.getGroup("g1")!=null) {
				cp5.getGroup("g1").remove();
			}
		}
	}
	
}
