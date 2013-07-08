package SkyscraperOptimization;
import processing.core.*;
import controlP5.*;
public class ControlFrame extends PApplet{

	int w, h;

	int abc = 100;

	public void setup() {
		size(w, h);
		frameRate(25);
		cp5 = new ControlP5(this);
		cp5.addSlider("abc").setRange(0, 255).setPosition(10,10);
		cp5.addSlider("def").plugTo(parent,"def").setRange(0, 255).setPosition(10,30).setValue(250);
	}

	public void draw() {
		background(abc);
	}

	public ControlFrame() {
	}

	public ControlFrame(Object theParent, int theWidth, int theHeight) {
		parent = theParent;
		w = theWidth;
		h = theHeight;
	}


	public ControlP5 control() {
		return cp5;
	}


	ControlP5 cp5;

	Object parent;

}
