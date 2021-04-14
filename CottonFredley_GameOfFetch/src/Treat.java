/*********************************************************************************
 * File Name: Treat.java
 * Authors: Chrissy Cotton, Michael Fredley
 * Purpose: To declare the attributes and methods that a Treat object needs.
 ********************************************************************************/

public class Treat extends Objects {

    /******************************************************************
     * Method: Treat()
     * Purpose: The constructor for the Treat object
     * Input: A Jody object and a GUI object
     * Output: A Treat object
     ******************************************************************/

    public Treat(Jody jody, GUI gui) {
        super(jody, gui, "Bark.wav", "Treat.png");
    }
}
