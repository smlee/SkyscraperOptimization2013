package SkyscraperOptimization;

//import SkyscraperOptimization.Level;
import processing.core.PApplet;
import java.util.*;

public class LevelStack {
	PApplet parent; // The parent PApplet that we will render ourselves onto
    ArrayList<Level> myLevels;
    Level lev;
    int feet = 12;
    int typicalLevelHeight = (14*feet)+4; // 14feet 4inches
    
    public LevelStack (int numLevels, int levelWidth, PApplet p ){
    	parent = p;
    	myLevels = new ArrayList<Level>();

    	for (int i=0; i<numLevels; i++){
    		myLevels.add(new Level(i*typicalLevelHeight, levelWidth, parent) );
    	}

    }

    public void drawStack(){
    	for (int i=0; i<myLevels.size(); i++){
    		lev = (Level) myLevels.get(i);
    		lev.drawLevel(i);
    	}
    }
    
    public void addLevel(){
    	int lastIndex = myLevels.size()-1;
    	myLevels.add(new Level(lastIndex*typicalLevelHeight, parent));
    }
    
    public void removeLevel(int i){
    	myLevels.remove(i);
    }
    
    public void flush(){
    	myLevels.clear();
    }
    
    public void setHeight(int h, int i){
    	lev = (Level) myLevels.get(i);
    	lev.elevation = h;
    }
    
    public void setWidthAll(int w){
    	for (int i = 0; i < myLevels.size(); i++) {
    		lev = (Level) myLevels.get(i);
    		lev.levelWidth = w;
    	}
    }
    
}

