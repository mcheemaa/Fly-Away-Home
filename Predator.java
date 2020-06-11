
import java.util.ArrayList;
import java.util.Random; 

public abstract class Predator extends Creature { // makes it subclass of creature

    public  Predator() {

	super() ; // invokes super class connstructor 

    }

    public abstract ArrayList<GridLocation> generateLegalMoves();


    public abstract boolean eatsFly();
    

    public void update()
    {
		
	ArrayList <GridLocation> legalM = this.generateLegalMoves(); // new array list for legal moves

	int size = legalM.size();

	if (size != 0) { // makes sure that the update method is only called when there is a move available and so prevents any run time errors 
	    
	    Random num = new Random () ;
	    int Number = (num.nextInt (size))  ; // gets random number in the range of thee size of list
	    
	    location.removeCreature() ; 
	    
	    location = legalM.get(Number) ; // gets a random location for the creature 
	    
	    location.setCreature(this) ; // sets the frog to that location 
	}
    }

    public boolean isPredator()
    {
	return true;
    }
    
	
	
	
   
}

