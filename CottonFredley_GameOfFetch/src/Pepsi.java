/***************************************************************************
 * File Name: Pepsi.java
 * Authors: Chrissy Cotton, Michael Fredley
 * Purpose: To declare the attributes and methods that a Pepsi object needs.
 ***************************************************************************/

public class Pepsi extends Objects {

    /******************************************************************
     * Method: Pepsi()
     * Purpose: The constructor for the Pepsi object
     * Input: A Jody object and a GUI object
     * Output: A Pepsi object
     ******************************************************************/

    public Pepsi(Jody jody, GUI gui) {
        super(jody, gui, "Can.wav", "Pepsi.png");
    }
}
