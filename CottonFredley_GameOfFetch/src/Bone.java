/*****************************************************************************************************************************
 * File Name: Bone.java
 * Authors: Chrissy Cotton, Michael Fredley
 * Purpose: To declare the attributes and methods of Bone objects, it extends the Objects class. Bone is one of four classes
 * that inherits from Objects. This class is where the Bone picture file is read in and drawn.
 *****************************************************************************************************************************/

public class Bone extends Objects {

    /******************************************************************
     * Method: Bone()
     * Purpose: The constructor for the Bone object
     * Input: A Jody object and a GUI object
     * Output: A Bone object
     ******************************************************************/

    public Bone(Jody jody, GUI gui) {
        super(jody, gui, "Bark.wav", "Bone.png");
    }
}
