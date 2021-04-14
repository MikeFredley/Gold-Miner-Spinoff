/*********************************************************************
 * File Name: Gryffn.java
 * Authors: Chrissy Cotton, Michael Fredley
 * Purpose: To hold the attributes and methods for the Gryffn object.
 *********************************************************************/

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Gryffn extends Objects
{
    private int varSpeed = 2;
    private int speed = 0;

    private Jody jody;
    private BufferedImage picture;
    private final int height;
    private final int width;
    private int locationX;
    private int locationY;
    private GamePanel gamePanel;
    private boolean noCollision = true;
    private int incrementer = 0;


    /******************************************************************
     * Method: Gryffn
     * Purpose: The constructor for a Gryffn object
     * Input: A Jody object and a GamePanel object
     * Output: Gryffn and his functionality
     ******************************************************************/

    public Gryffn(Jody pJody, GamePanel pGPanel)
    {
        jody = pJody;
        gamePanel = pGPanel;
        height = 50;
        width = 50;

        try
        {
            picture = ImageIO.read(new File("Gryffn.png"));
        }
        //if there is an error loading the picture, tell user
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "Error Loading File");
        }
    }

    /******************************************************************
     * Method: Draw()
     * Purpose: To draw Gryffn's picture on the screen wherever
     *  it is needed
     * Input: Graphics g
     * Output: Gryffn's picture
     ******************************************************************/

    public void Draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(picture, jody.getLocationX(), (jody.getLocationY() + jody.getHEIGHT()) + velocityY, width, height, null); // + 200 in height to move him down
        locationX = jody.getLocationX();
        locationY = (jody.getLocationY() + jody.getHEIGHT()) + velocityY;
    }

    /******************************************************************
     * Method: getBoundingBox()
     * Purpose: Creates the hit box for the Gryffn object
     * Input: None
     * Output: A Rectangle2D object
     ******************************************************************/

    public Rectangle2D getBoundingBox() {
        Rectangle2D.Double r;
            r = new Rectangle2D.Double(locationX, locationY , width, height);
            return r;

    }

    /******************************************************************
     * Method: Drop()
     * Purpose: This is the method that causes him to go down when
     *  the Fetch button is pressed
     * Input: None
     * Output: Gryffn goes down
     ******************************************************************/

    public boolean Drop()
    {
        // this sets the speed to the variable speed before gryffn starts to drop
        // this will prevent gryffn from going crazy when we change the speed with
        // an item
        speed = varSpeed;

        while(noCollision){
            // Gryffn keeps going down at the rate of SPEED
            velocityY += speed;
            // This keeps adding SPEED to the incrementor so Gryffn
            // Knows how to go back up later
            incrementer += speed;
            // Slows down the loop so Gryffn doesn't go up and down instantly
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Repaints the panel every time he moves
            gamePanel.repaint();
            // Checks for collision with an object
            boolean boom = gamePanel.checkCollisions();
            // If he collides with something he goes back up
            if(boom) {
                Up();
                noCollision = false;
            }
        }
        noCollision = true;
        return false;
    }

    /******************************************************************
     * Method: Up()
     * Purpose: To make Gryffn go back to Jody
     * Input: None
     * Output: Gryffn goes back to Jody
     ******************************************************************/

    public void Up(){
        // Uses the incrementor in the for loop and
        // Subtracts SPEED from it until it reaches 0 again
        for(int i = incrementer; i != 0; i-=speed)
        {
            // Subtracts SPEED from the velocity making him go up
            velocityY -= speed;
            // Slows the loop so the process isn't instant
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Repaints the panel to show him moving
            gamePanel.repaint();
            // Sets the drop variable back to false
            gamePanel.setDrop(false);
        }
        incrementer = 0;
    }

    /******************************************************************
     * Method: Accessors and Mutators
     * Purpose: To change various values
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

    public int getVarSpeed() {
        return varSpeed;
    }

    public void setVarSpeed(int varSpeed) {
        this.varSpeed = varSpeed;
    }

}
