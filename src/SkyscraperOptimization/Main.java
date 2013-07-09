/*Processing 2.1
 * PeasyCam 2
 * ControlP5 
 * 
 * 
 */

package SkyscraperOptimization;

import peasy.*;
import processing.opengl.*;
import processing.core.*;
import controlP5.*;

public class Main extends PApplet {

	PeasyCam cam;
	PMatrix3D currCameraMatrix;
	PGraphics3D p3;
	
	//ControlP5 stuff
	//ControlP5 cp5;
	cp5GUI cp5;

	//ControlGroup is for inside the main applet
	ControlGroup messageBox;
	ControlFrame cf;
	int messageBoxResult = -1;
	String messageBoxString = "";
	float t;
	
	
	//Skyscraper Parts
	int feet = 12;
	int def;
	LevelStack myLevels;
	Level lev;
	Skyscraper mySkyscraper;

	public void setup() {

		size(1200, 800, OPENGL);
		p3 = (PGraphics3D) g;
		cam = new PeasyCam(this, 300 * feet);
		//cam.setMinimumDistance(2 * feet);
		//cam.setMaximumDistance(2000 * feet);

		cp5 = new cp5GUI(this);
		cp5.inCP5();
		cp5.initialize();
		
		System.out.println(cp5.lw + "   " + cp5.nl);
		
		mySkyscraper = new Skyscraper(cp5.nl, cp5.lw, this);

	}


	public void draw() {
		
		background(250);
		
		//hint(ENABLE_DEPTH_TEST);
		pushMatrix();
		mySkyscraper.myLevels.drawStack();
		
		popMatrix();
		//hint(DISABLE_DEPTH_TEST);
		gui();
		
		cam.setActive(true);
		if(cp5.cp5.isMouseOver()){
			cam.setActive(false);
		}
	}
	
	//This setup is to disable PeasyCam when ControlP5 is being used. 
	void gui() {
		
		hint(DISABLE_DEPTH_TEST);
		cam.beginHUD();
		currCameraMatrix  = new PMatrix3D(p3.camera);
		camera();
		cp5.cp5.draw();
		p3.camera = currCameraMatrix;
		cam.endHUD();
		hint(ENABLE_DEPTH_TEST);
		
	}
	
	public void numLevels(String theText){
		mySkyscraper.myLevels.flush();
		cp5.nl = Integer.parseInt(theText);
		mySkyscraper.myLevels = new LevelStack(cp5.nl, cp5.lw, this);
	}
	
	public void lvlWidth(String theText){
		cp5.lW = theText;
		cp5.lw = Integer.parseInt(cp5.lW)*feet;
		for (int i = 0; i < mySkyscraper.myLevels.myLevels.size(); i++){
			lev = (Level)mySkyscraper.myLevels.myLevels.get(i);
			lev.levelWidth = cp5.lw;
		}
	}
	
	
	/*For external window
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
	*/
	
}
