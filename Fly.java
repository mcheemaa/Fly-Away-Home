/**
 * Muhammad Ahmed Cheema
 * Fly.java
 **/



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Contains several methods that aid in the<br>
 * display and movement of Mosca
 */
public class Fly extends Creature
{
    private static final String imgFile = "fly.jpg";
    

        
    /**
     * Creates a new Fly object.<br>
     * The image file for a fly is fly.jpg<br>
     *
     * @param loc a GridLocation
     * @param fw the FlyWorld the fly is in
     */
    public Fly(GridLocation loc, FlyWorld fw)
    {

	   
	location = loc ;
	
	world = fw ;
	    

	
	// Sets the image instance variable
	try
	{
	    image = ImageIO.read(new File(imgFile));
	}
	catch (IOException ioe)
	{
	    System.out.println("Unable to read image file: " + imgFile);
	    System.exit(0);
	}

	// Puts the fly on the GridLocation so image displays

	location.setCreature(this);
    }

 

    /**
    * Returns a string representation of this Fly showing
    * the location coordinates and the world.
    *
    * @return the string representation
    */
    public String toString(){
    	String s = "Fly in world:  " + this.world + "  at location (" + this.location.getRow() + ", " + this.location.getColumn() + ")";
    	return s;
    }
    
    /**
     * This method <strong>updates</strong> the fly's location in<br>
     * the <strong>world</strong><br>
     * The fly can move in one of the four cardinal (N, S, E, W) directions.<br>
     * You need to make sure that the <strong>updated</strong> location<br>
     * is within the bounds of the world before<br>
     * changing the location of the fly<br>
     *
     * @param direction one of the four cardinal directions<br>
     *        Given as constants in FlyWorldGUI<br><br>
     *        They are:<br>
     *        FlyWorldGUI.NORTH<br>
     *        FlyWorldGUI.SOUTH<br>
     *        FlyWorldGUI.EAST<br>
     *        FlyWorldGUI.WEST<br>
     */
    public void update(int direction)
    {
		
	int d = direction ; // getss direction and if its north it is 0 and so on and then passes it

	int rowFly = location.getRow() ; // gets the row number of the fly
	int colFly = location.getColumn() ; // gets the column number of the fly 

		
	//north 
	if ( d == 0 ) {

	    int nR = rowFly -1 ; 
	    int nC = colFly ;
	    	        
	    if (world.isValidLoc (nR, nC)) {

		location.removeCreature() ;
		location = world.getLocation(nR, nC) ; // gets the new gridlocation for those numbers
		location.setCreature(this); // sets the fly to the new location 
				
	    }
	}

	// south 
	if ( d == 1 ) {

	    int nR = rowFly + 1 ;
	    int nC = colFly ;
	    	    
	    if (world.isValidLoc (nR, nC) ) {
		
		location.removeCreature() ;
		location = world.getLocation(nR, nC) ;
		location.setCreature(this);
						
	    }
	}
	
	// east
	if ( d == 2 ) {

	    int nR = rowFly ;
	    int nC = colFly + 1 ;
	    	    	    
	    if (world.isValidLoc (nR, nC) ) {

		location.removeCreature() ;
		location = world.getLocation(nR, nC) ;
		location.setCreature(this);
	    }
	}

	// west
	if ( d == 3 ) {

	    int nR = rowFly  ;
	    int nC = colFly - 1 ;
	    	    
	    if (world.isValidLoc (nR, nC) ) {

		location.removeCreature() ;
		location = world.getLocation(nR, nC) ;
		location.setCreature(this);
	    }
	}
    }
}
