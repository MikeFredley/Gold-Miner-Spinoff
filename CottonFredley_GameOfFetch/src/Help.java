/************************************************************************************************
 * File Name: Help.java
 * Authors: Chrissy Cotton, Michael Fredley
 * Purpose: To create the window for the help button when invoked.
 * This window will have information on how to play the game and explain to the user what to do.
 ************************************************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Help extends JFrame
{
    private JTextArea text;
    private String info;
    private JPanel panel;
    private JButton closeButton;
    private helpListener listener;
    private boolean isStart = true;
    private final int HEIGHT = 410;
    private final int WIDTH = 550;
    private final int LOCX = 600;
    private final int LOCY = 300;

    /******************************************************************
     * Method: Help()
     * Purpose: Constructor that sets up everything that is needed
     *  for the tutorial window
     * Input: None
     * Output: Makes the tutorial window appear
     ******************************************************************/

    public Help()
    {
        this.setSize(WIDTH,HEIGHT);
        this.setTitle("Help");
        // NEED TO UPDATE INFO FOR NEW ITEMS AND WHAT THEY DO
        info = "\n                       **  **  **  **  Welcome to Game of Fetch!  **  **  **  **" +
                "\n How To Play: \n    When Jody and Gryffn move over an item, click the fetch button \n    and Gryffn will drop to fetch the item." +
                "\n Scoring: \n * Bone: 5 points \n * Rock: 1 point \n * Steak: 20 points \n * JavaScript: -10 points \n * Pepsi Can: 10 points + a speed boost" +
                "\n * Wyatt: Clears away some rocks \n * Jody Emoji: -100 points \n * 100%: 100 points "+
                "\n How To Win: \n    You must reach the goal of 100 points in 60 seconds. \n    If the timer runs out and you do not have enough points, you lose. ";
        panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(87, 181, 72));
        closeButton = new JButton("Go Fetch!");
        closeButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        text = new JTextArea(info);
        text.setFont(new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 15));
        listener = new helpListener();
        text.setBackground(new Color(87,181,72));
        text.setForeground(Color.WHITE);
        text.setEditable(false);
        panel.add(text, BorderLayout.NORTH);
        closeButton.setSize(125, 75);
        closeButton.addActionListener(listener);
        panel.add(closeButton, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocation(LOCX, LOCY);
        this.add(panel);
        //this.pack();
        this.setVisible(true);
    }

    /******************************************************************
     * Class: helpListener
     * Purpose: The Actionlistener for the button in the help screen
     * Input: A button Click
     * Output: Closes the screen
     ******************************************************************/

    public class helpListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == closeButton)
            {
                if (!isStart)
                {
                    dispose();
                }
                else
                {
                    isStart = false;
                    GUI gui = new GUI();
                    dispose();
                }

            }
        }
    }

    public void setStart(boolean start) {
        isStart = start;
    }

}
