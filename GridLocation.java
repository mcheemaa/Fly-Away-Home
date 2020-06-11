import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * Represents a single grid location (i.e., one row/column position)
 * Within a 2D grid in a Graphical User Interface (GUI)
 */
public class GridLocation extends JPanel					     
{
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    
    private int row;
    private int col;

    private Color backgroundColor;
    private Creature creature;

    /**
     * Creates a new grid position at a specific location within the grid
     * with a white background color
     *
     * @param r, the row
     * @param c, the column
     */
    public GridLocation(int r, int c)
    {
	setBorder(new LineBorder(Color.BLACK));

	row = r;
	col = c;

	backgroundColor = Color.WHITE;
	setBackground(backgroundColor);

	creature = null;
    }

    /**
     * @return int
     */
    public int getRow()
    {
	return row;
    }

    /**
     * @return int
     */
    public int getColumn()
    {
	return col;
    }

    /**
     * Changes the background color of this location
     *
     * @param color
     */
    public void setBackgroundColor(Color color)
    {
	backgroundColor = color;
	setBackground(backgroundColor);
	repaint();
    }

     /**
     * Adds a Creature to this location
     *
     * @param c, the Creature to be added
     */
    public void setCreature(Creature c)
    {
	creature = c;
	repaint();
    }

    /**
     * Removes Creature from this location
     */
    public void removeCreature()
    {
	creature = null;
	repaint();
    }

    /**
     * Returns true if location contains a
     * predator, false otherwise.<br>
     * Useful because can't have two predators in same spot
     *
     * @return boolean
     */
    public boolean hasPredator()
    {
	if (creature != null)
	    return creature.isPredator();
	else
	    return false;
    }
 
    /**
     * Determines if two locations represent the same position
     * within the 2D grid
     *
     * @param o, another object which may or may not be a GridLocation
     *
     * @return boolean
     */
    @Override
    public boolean equals(Object o)
    {
	if (o == null)
	    return false;
	if (o == this)
	    return true;
	if (!(o instanceof GridLocation))
	    return false;

	GridLocation other = (GridLocation)o;

	return row == other.row && col == other.col;
    }

    @Override
    public String toString()
    {
	return "(" + row + ", " + col +")";
    }
    
    /**
     * This method is used to help ensure that the size
     * of the grid location is always a very specific size<br>
     * YOU DO NOT NEED TO WORRY ABOUT THIS METHOD OR CALL IT EVER
     */
    @Override
    public Dimension getPreferredSize()
    {
	return new Dimension(WIDTH, HEIGHT);
    }

    /**
     * This method actually draws the picture in
     * the location if there is one<br>
     * YOU NEVER NEED TO CALL THIS METHOD
     *
     * @param g, a Graphics object for drawing
     */
    @Override
    protected void paintComponent(Graphics g)
    {
	super.paintComponent(g);
	
	if (creature != null)
	    g.drawImage(creature.getImage(), 0, 0, null);
    }

    @Override
    public int hashCode()
    {
	int code = 0;
	code = (code * 397) ^ row;
	code = (code * 397) ^ col;

	return code;
    }
}
