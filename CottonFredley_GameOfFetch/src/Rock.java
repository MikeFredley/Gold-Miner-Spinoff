/***************************************************************************
 * File Name: Rock.java
 * Authors: Chrissy Cotton, Michael Fredley
 * Purpose: To declare the attributes and methods that a Rock object needs.
 ***************************************************************************/

public class Rock extends Objects {

    /******************************************************************
     * Method: Rock()
     * Purpose: The constructor for the Rock object
     * Input: A Jody object and a GUI object
     * Output: A Rock object
     ******************************************************************/

    public Rock(Jody jody, GUI gui) {
        super(jody, gui,"Whine.wav", "Rock.png");
    }
}
