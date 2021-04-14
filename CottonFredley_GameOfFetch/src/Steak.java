/*********************************************************************************
 * File Name: Steak.java
 * Authors: Chrissy Cotton, Michael Fredley
 * Purpose: To declare the attributes and methods that a Steak object needs.
 ********************************************************************************/

public class Steak extends Objects {

    /******************************************************************
     * Method: Steak()
     * Purpose: The constructor for the Steak object
     * Input: A Jody object and a GUI object
     * Output: A steak object
     ******************************************************************/

    public Steak(Jody jody, GUI gui) {
        super(jody, gui, "Bark.wav", "Steak.png");
    }
}
