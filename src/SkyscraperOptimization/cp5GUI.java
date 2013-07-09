package SkyscraperOptimization;

import processing.core.*;
import controlP5.*;

public class cp5GUI extends PApplet{
	PApplet parent;
	
	int feet = 12;
	
	ControlP5 cp5;
	Group g1, g2;
	
	Main main;
    Level lev;
	
	//Skyscraper data
		String lW, nL;
		int lw, nl;
	
	public cp5GUI(PApplet p){
		parent = p;
		cp5 = new ControlP5(parent);
	}
		
		//intialize and sync Skyscraper data and ControlP5
		public void initialize(){
			nl = getInt("numLevels");
			lw = getInt("lvlWidth")*feet;
			
		}
		
		public int getInt(String name){
			return Integer.parseInt(cp5.get(Textfield.class, name).getText());
		}
		//ControlP5 control events section
		
		//control events for textfields
		
		
		/*
		 * 
		 * ControlP5 section
		 * 
		 */
		
		public void inCP5(){
			g1 = cp5.addGroup("g1")
					.setPosition(0, 11)
					.setBackgroundHeight(100)
					.setBackgroundColor(color(50,100))//.bringToFront()
					.setLabel("Levels")
					;
			
			cp5.addTextfield("numLevels").setCaptionLabel("Number of Levels").setPosition(10,10).setSize(70, 15).setValue("1").setAutoClear(false).setGroup(g1);
			cp5.addTextfield("lvlWidth").setCaptionLabel("Level Width").setPosition(10, 40).setSize(70, 15).setValue("40").setAutoClear(false).setGroup(g1);
			cp5.addButton("addLevel").setCaptionLabel("Add Level").setPosition(10, 70).setSize(70, 15).setGroup(g1);

			g2 = cp5.addGroup("g2")
					.setPosition(0,121)
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
			/*cf = addControlFrame("extra", 200, 200);
			*cf = addControlFrame("blank", 300, 200);
			**/
		}
		
		//General control events
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
			} else if (theEvent.isFrom("Set")){
				noLoop();
				//theEvent.getController().get
				
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
