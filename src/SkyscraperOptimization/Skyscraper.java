package SkyscraperOptimization;

import processing.core.*;

public class Skyscraper {
	
	//PApplet parent; // The parent PApplet that we will render ourselves onto
    LevelStack myLevels;

    int feet = 12;
    int typicalLevelHeight = (14*feet)+4; // 14feet 4inches
    
    public Skyscraper(int nl, int lw, PApplet p){
    	myLevels = new LevelStack(nl, lw, p); // initialize with 0 Levels
    }

}
