/*********************************************************************************
 * File Name: Finger.java
 * Authors: Chrissy Cotton, Michael Fredley
 * Purpose: To declare the attributes and methods that a Finger object needs.
 ********************************************************************************/

public class Finger extends Objects {

    /******************************************************************
     * Method: Finger()
     * Purpose: The constructor for the Finger object
     * Input: A Jody object and a GUI object
     * Output: A Finger object
     ******************************************************************/

    public Finger(Jody jody, GUI gui) {
        super(jody, gui, "Sad.wav", "Finger.png");
    }
}
