/******************************************************************************************
 * File Name: Jody.java
 * Authors: Chrissy Cotton, Michael Fredley
 * Purpose: To create the attributes and methods for the Jody class. Jody's job is to draw
 *  and move across the screen continuously.
 ******************************************************************************************/

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Jody extends Objects
{
    private BufferedImage picture;
    // The starting location of where Jody is on the panel
    private int locationX;
    private int locationY;

    // The height and width of jodys image - Will remain constant
    private final int HEIGHT = 125;
    private final int WIDTH = 75;

    /******************************************************************
     * Method: Jody()
     * Purpose: The constructor the the Jody Object
     * Input: an int for X and Y locations and a GUI object
     * Output: Jody appears on the screen
     ******************************************************************/

    public Jody(int locationX, int locationY, GUI pGui)
    {
        super.setGui(pGui);
        //try to read in file for image
        try
        {
            picture = ImageIO.read(new File("Jody.png"));
        }
        //if there is an error loading the picture, tell user
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "Error Loading File");
        }
        this.locationX = locationX;
        this.locationY = locationY;
    }

    /******************************************************************
     * Method: Draw()
     * Purpose: Draws Jody's picture onto the screen
     * Input: Graphics g
     * Output: Jody appears on the screen
     ******************************************************************/

    public void Draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(picture, locationX, locationY, WIDTH, HEIGHT, null);
    }

    /******************************************************************
     * Method: Accessors and Mutators
     * Purpose: Sets and gets various variables
     * Input: Values
     * Output: Values
     ******************************************************************/

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

}
