/*****************************************************************************************************************************
 * File Name: Objects.java
 * Authors: Chrissy Cotton, Michael Fredley
 * Purpose: To create methods and attributes for the Objects class that has child classes, therefore it needs to have all of
 *  the attributes or methods that any object might have.
 *****************************************************************************************************************************/

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Objects {

    protected int height;
    protected int width;
    protected int locationX;
    protected int locationY;
    private GUI gui;
    private int velocityX = 10;
    protected int velocityY = 0;
    private boolean moveRight = false;
    private boolean moveLeft = true;
    private String soundFileName;
    private BufferedImage picture;

    public Objects()
    {

    }

    /******************************************************************
     * Method: Objects()
     * Purpose: The parent constructor for each of the objects that are
     *  drawn on the screen
     * Input: NEED TO DELETE SOME PARAMETERS LATER
     * Output: Sets values for each of the variables
     ******************************************************************/

    public Objects(Jody jody, GUI pGui, String soundFileName, String pictureFileName)
    {

        this.gui = pGui;
        this.soundFileName = soundFileName;
        if (this instanceof Pepsi)
        {
            this.height = 75;
            this.width = 55;
        }
        else if(this instanceof JavaScript){
            this.height = 65;
            this.width = 65;
        }
        else if(this instanceof Steak){
            this.height = 75;
            this.width = 55;
        }
        else if (this instanceof  Wyatt){
            this.height = 85;
            this.width = 65;
        }
        else if (this instanceof OneHundred){
            this.height = 40;
            this.width = 50;
        }
        else if (this instanceof Finger){
            this.height = 65;
            this.width = 65;
        }
        else
        {
            this.height = RandomizeHW();
            this.width = RandomizeHW();
        }

        this.locationX = RandomizeX();
        if(this instanceof Wyatt){
            this.locationY = 650;
        }
        else if (this instanceof OneHundred){
            this.locationY = 675;
        }
        else
        {
            this.locationY = RandomizeY() + jody.getHEIGHT() + 50;
        }

        //try to read in file for image
        try
        {
            picture = ImageIO.read(new File(pictureFileName));

        }
        //if there is an error loading the picture, tell user
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "Error Loading File");
        }
    }

    /******************************************************************
     * Method: RandomizeHW()
     * Purpose: To randomize the height and width of each of the
     *  objects that are generated onto the screen
     * Input: None
     * Output: A random Integer
     ******************************************************************/

    public int RandomizeHW(){
       Random rnd = new Random();
       int pNum = rnd.nextInt(50)+ 25;
       return pNum;
    }

    /******************************************************************
     * Method: RandomizeX()
     * Purpose: To randomize the X location of each of the objects on
     *  the screen
     * Input: None
     * Output: A Random Integer
     ******************************************************************/

    public int RandomizeX(){
        Random rnd = new Random();
        int pNum = rnd.nextInt(1200) + 5;
        return pNum;
    }

    /******************************************************************
     * Method: RandomizeY()
     * Purpose: To randomize the Y Location for each of the objects on
     *  the screen
     * Input: None
     * Output: A Random Integer
     ******************************************************************/

    public int RandomizeY(){
        Random rnd = new Random();
        int pNum = rnd.nextInt(500);
        return pNum;
    }

    /******************************************************************
     * Method: playSound()
     * Purpose: To play the correct sound for each object in the game
     * Input: None
     * Output: The objects sound
     ******************************************************************/

    public void playSound()
    {
        try {
            // Open an audio input stream.
            File soundFile = new File(soundFileName);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);

            // Get a sound clip resource.
            Clip clip = AudioSystem.getClip();
            // Open audio clip and load samples from the audio input stream.
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /******************************************************************
     * Method: getBoundingBox()
     * Purpose: To set up the hit box for each of the objects
     * Input: None
     * Output: A Rectangle2D object to act as a hit box
     ******************************************************************/

    public Rectangle2D getBoundingBox() {
        Rectangle2D.Double r;
        r = new Rectangle2D.Double(locationX, locationY, width, height);
        return r;
    }

    /******************************************************************
     * Method: UpdateJG()
     * Purpose: To move Jody back and forth on the screen
     * Input: None
     * Output: Jody moves back and forth on the screen
     ******************************************************************/

    public void UpdateJG(){
        if(moveLeft) {
            gui.getGamePanel().setPlayerLocationX(gui.getGamePanel().getPlayerLocationX() - velocityX);
            if(gui.getGamePanel().getPlayerLocationX() == 0){
                moveLeft = false;
                moveRight = true;
            }
        }
        else if(moveRight)
        {
            gui.getGamePanel().setPlayerLocationX(gui.getGamePanel().getPlayerLocationX() + velocityX);
            if(gui.getGamePanel().getPlayerLocationX() == 1250){
                moveRight = false;
                moveLeft = true;
            }
        }
    }

    /************************************************
     * Method Name: Draw()
     * Purpose: To Draw the picture for each object
     * Input: Graphics g
     * Output: The objects picture
     ************************************************/

    public void Draw(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(picture, locationX, locationY, width, height, null);
    }

    /************************************************
     * Method Name: Accessors and Mutators
     * Purpose: To get or set certain variables
     * Input: Values
     * Output: Values
     ************************************************/

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }
}
