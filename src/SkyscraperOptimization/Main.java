/*Processing 2.1
 * PeasyCam 2
 * ControlP5 
 * 
 * 
 */

package SkyscraperOptimization;

import javax.swing.*;
import java.awt.Frame;
import java.awt.BorderLayout;
import java.io.*;
import peasy.*;
import processing.opengl.*;
import processing.core.*;
import controlP5.*;

public class Main extends PApplet {

	PeasyCam cam;
	ControlP5 cp5;
	PMatrix3D currCameraMatrix;
	PGraphics3D p3;

	int feet = 12;
	int def;
	Group g1, g2;

	//ControlGroup is for inside the main applet
	ControlGroup messageBox;
	
	ControlFrame cf;
	int messageBoxResult = -1;
	String messageBoxString = "";
	float t;

	public void setup() {

		size(1200, 800, OPENGL);
		p3 = (PGraphics3D) g;
		cam = new PeasyCam(this, 300 * feet);
		//cam.setMinimumDistance(2 * feet);
		//cam.setMaximumDistance(2000 * feet);

		cp5 = new ControlP5(this);

		g1 = cp5.addGroup("g1")
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


		g2 = cp5.addGroup("g2")
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
		
		//ControlFrame is for seperate windows
		cf = addControlFrame("extra", 200, 200);
		cf = addControlFrame("blank", 300, 200);

	}


	public void draw() {
		
		background(def);
		
		//hint(ENABLE_DEPTH_TEST);
		pushMatrix();
		translate(width/2,height/2,500);
		rotateY(t+=0.1);
		fill(255);
		rect(-50,-50,100,100);
		popMatrix();
		//hint(DISABLE_DEPTH_TEST);
		gui();
		
		cam.setActive(true);
		if(cp5.isMouseOver()){
			cam.setActive(false);
		}
	}
	
	void gui() {
		
		hint(DISABLE_DEPTH_TEST);
		cam.beginHUD();
		currCameraMatrix  = new PMatrix3D(p3.camera);
		camera();
		cp5.draw();
		p3.camera = currCameraMatrix;
		cam.endHUD();
		hint(ENABLE_DEPTH_TEST);
		
	}

	public void controlEvent(ControlEvent theEvent) {
		if(theEvent.isGroup()) {
			println("got an event from group "
					+theEvent.getGroup().getName()
					+", isOpen? "+theEvent.getGroup().isOpen()
					+"     " + theEvent.getGroup().isMouseOver()
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
	
	ControlFrame addControlFrame(String theName, int theWidth, int theHeight) {
		  Frame f = new Frame(theName);
		  ControlFrame p = new ControlFrame(this, theWidth, theHeight);
		  f.add(p);
		  p.init();
		  f.setTitle(theName);
		  f.setSize(p.w, p.h);
		  f.setLocation(100, 100);
		  f.setResizable(false);
		  f.setVisible(true);
		  return p;
		}
	
}
