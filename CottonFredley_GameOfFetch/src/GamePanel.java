/****************************************************************************************************************
 * File Name: GamePanel.java
 * Authors: Chrissy Cotton, Michael Fredley
 * Purpose: To build the gamePanel for the GUI. Hold all the attributes and methods needed to create the class.
 * The main animation thread is held here as well as paintComponent for all of the images.
 ****************************************************************************************************************/

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable
{
    //Instance variables that are necessary for the gamePanel class
    private Jody jody;
    private Gryffn gryffn;
    //Make constants for jody and gryffns's starting positions
    private final int LOCATIONX = 600;
    private final int LOCATIONY = 0;
    //boolean for if the drop button was clicked to stop the thread
    private boolean drop;
    //new thread for animation
    private Thread loop;
    //object list for the gamePanel's "ground"
    private ArrayList<Objects> objectList;


    //variable to set gameLoop and if it is over
    private boolean gameLoop =true;
    //needed to pass in to constructor
    private GUI gui;
    private boolean won;
    private final int BONESCORE= 5;
    private final int JAVASCORE= 20;
    private final int TREATSCORE= 10;
    private final int STEAKSCORE= 20;
    private final int ROCKSCORE= 1;

     /***
      * TODO
     * comment everythingggggggggggg
     */

    public GamePanel(GUI pGui)
    {
        //set window properties
        drop = false;
        this.setDoubleBuffered(true);
        //use the gui that was passed in through constructor
        this.gui = pGui;
        //instantiate the Jody object
        jody = new Jody(LOCATIONX, LOCATIONY, gui);
        //instantiate the Gryffn object by passing in the new Jody object
        gryffn = new Gryffn(jody, this);
        //instantiate and add 20 objects (10 rocks, 10 bones) to list
        objectList = new ArrayList<Objects>();
        for(int i=0;i<10;i++){
            //instantiate new objects
            objectList.add( new Rock(jody,gui));
            objectList.add( new Bone(jody, gui));
        }
        for(int i=0; i<5;i++){
            objectList.add(new Treat(jody, gui));
        }
        objectList.add(new Steak(jody, gui));
        objectList.add(new JavaScript(jody, gui));
        objectList.add(new Pepsi(jody, gui));
        objectList.add(new Wyatt(jody, gui));
        objectList.add(new OneHundred(jody, gui));
        objectList.add(new Finger(jody, gui));

        loop = new Thread(this);
        loop.start();
    }

    /******************************************************************
     * Method: paintComponent(Graphics g)
     * Purpose: Handles painting all of the pictures onto the panel
     * Input: Graphics g
     * Output: Pictures onto the screen
     ******************************************************************/

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //call the draw method for a Jody to draw his image
        jody.Draw(g);
        //call the craw method for a Jody to draw his image
        gryffn.Draw(g);

        //for each Objects object in the list, draw it randomly on the screen
        for (Objects obj : objectList)
        {
            obj.Draw(g);
        }
    }

    /******************************************************************
     * Method: Run()
     * Purpose: Holds and runs the gameLoop for the game
     * Input: None
     * Output: Runs the game
     ******************************************************************/

    @Override
    public void run() {
        while(gameLoop) {
            try {
                Thread.sleep(50);
                repaint();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Threading Error.");
            }
            // If the player doesn't want to drop Gryffn then
            // Jody just goes back and forth on the screen
            if(!drop){
                jody.UpdateJG();
            }
            // If the player does select to drop then
            // Gryffn goes down
            else{
                drop = gryffn.Drop();
                repaint();
            }
            // Checks if the player reaches the goal
            if(gui.getScore() >= gui.getGOAL()){
                won = true;
                gui.getTime().cancel(); // Stops the timer
                gameLoop = false; // Stops the gameLoop
                GameOver(); // Calls the method to display the JOptionPane
            }
        }
    }

    /******************************************************************
     * Method: GameOver()
     * Purpose: Handles ending the game when the player hits the goal
     *  or if the time runs out
     * Input: None
     * Output: A JOptionPane that asks you if you want to play again
     ******************************************************************/

    public void GameOver(){
        int decision;
        JFrame f = new JFrame();
        // Shows a JOptionPane of varying text when the player wins or loses
        if(won){
            playWinLoseSound(true);
            decision=JOptionPane.showConfirmDialog(f,"You won!\nDo you want to play again?","Game Over!", JOptionPane.YES_NO_OPTION);
        }
        else{
            playWinLoseSound(false);
            decision=JOptionPane.showConfirmDialog(f,"You lost.\nDo you want to play again?","Game Over!", JOptionPane.YES_NO_OPTION);
        }
        // if the player hits the yes button on the JOptionPane
        // It instantiates a new GUI and closes the old one
        if(decision==JOptionPane.YES_OPTION){
            GUI gui2 = new GUI();
            gui.dispose();

        }
        // If the player selects no it closes the game
        else if(decision==JOptionPane.NO_OPTION){
            gui.dispose();
            System.exit(0);
        }
    }

    /******************************************************************
     * Method: playWinLoseSound()
     * Purpose: To play the correct sound when ever the player wins
     *  or loses
     * Input: A boolean stating whether or not the player won or lost
     * Output: The respective sounds
     ******************************************************************/

    public void playWinLoseSound(boolean win)
    {
        // Open an audio input stream.
        File soundFile;
        if (win)
        {
            soundFile = new File("Clap.wav");
        }
        else
        {
            soundFile = new File("Boo.wav");
        }
        try {
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
     * Method: checkCollisions()
     * Purpose: Checks to see if the Gryffn object collides with any
     *  of the objects on the screen
     * Input: None
     * Output: A true or false boolean of whether he hits something
     ******************************************************************/

    public boolean checkCollisions(){
        for(int i = 0; i < objectList.size(); i++){
            Objects obj = objectList.get(i);
            if(gryffn.getBoundingBox().intersects(obj.getBoundingBox())){
                // Checks to see which item gryffn is hitting and changes the score appropriately
                if(obj instanceof Rock){
                    gui.setScore(gui.getScore() + ROCKSCORE);
                    obj.playSound();
                }
                else if (obj instanceof Bone){
                    gui.setScore(gui.getScore() + BONESCORE);
                    obj.playSound();
                }
                else if(obj instanceof Steak){
                    gui.setScore(gui.getScore() + STEAKSCORE);
                    obj.playSound();
                }
                else if(obj instanceof JavaScript){
                    gui.setScore(gui.getScore() - JAVASCORE);
                    obj.playSound();
                }
                else if(obj instanceof Pepsi)
                {
                    gryffn.setVarSpeed(gryffn.getVarSpeed() + 7);
                    obj.playSound();
                }
                else if(obj instanceof Treat){
                    gui.setScore(gui.getScore() + TREATSCORE);
                    gryffn.setVarSpeed(gryffn.getVarSpeed() + 1);
                    obj.playSound();
                }
                else if(obj instanceof Finger){
                    gui.setScore(gui.getScore() - gui.getGOAL());
                    obj.playSound();
                }
                else if(obj instanceof OneHundred)
                {
                    gui.setScore(gui.getScore() + gui.getGOAL());
                    obj.playSound();
                }
                else if (obj instanceof Wyatt){
                    //for each bone or special object you get, Wyatt will clear away (total objects fetched - 1) rocks
                    for (int j = 0; j < objectList.size(); j++)
                    {
                        if(objectList.get(j) instanceof Rock)
                        {
                            eraseObject(objectList.get(j)); // Erases the Rocks
                            objectList.remove(j);
                        }
                    }
                    obj.playSound();
                }
                gui.getScoreLabel().setText("Score: " + gui.getScore());
                eraseObject(obj); // Erases the object gryffn hits
                objectList.remove(obj); // Removes the object from the arraylist
                return true;
            }
            // Checks if gryffn hits the bottom of the screen
            else if(gryffn.getBoundingBox().getCenterY() >= getHeight())
            {
                return true;
            }
        }

        return false;
    }

    /******************************************************************
     * Method: These are the accessors and mutators
     * Purpose: To change and get various values
     * Input: values
     * Output: values
     ******************************************************************/

    public void eraseObject(Object obj){
        repaint();
    }

    public int getPlayerLocationX()
    {
        return jody.getLocationX();
    }

    public void setPlayerLocationX(int x)
    {
        jody.setLocationX(x);
    }

    public Jody getJody()
    {
        return jody;
    }

    public void setDrop(boolean drop) {
        this.drop = drop;
    }

    public void setGameLoop(boolean gameLoop) {
        this.gameLoop = gameLoop;
    }
}
