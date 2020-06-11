/**
 * Muhammad Ahmed Cheema
 * Creature.java
 **/

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;



public abstract class Creature { // super class which contains common instant variables and methods 
    
    GridLocation location ;
    FlyWorld world ;
    BufferedImage image ;
    
 
    public BufferedImage getImage() {
	
	return image;
    }
    
    
    public GridLocation getLocation() {
	
	return location;
	
    }
	
    public boolean isPredator(){
	
	return false;
	
    }
    
}

