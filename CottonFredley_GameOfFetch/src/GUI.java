/**************************************************************************************************************************
 * File Name: GUI.java
 * Authors: Chrissy Cotton, Michael Fredley
 * Purpose: To create the main window for the game. It must hold and instantiate the panels for the window and the buttons
 * and their action listeners.
 **************************************************************************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class GUI extends JFrame
{
    private final int WIDTH = 1325;
    private final int HEIGHT = 900;
    private final int GOAL = 100;

    private GUI gui;
    private JPanel scorePanel;
    private GamePanel gamePanel;
    private JPanel labelPanel;
    private JPanel buttonPanel;
    private int interval = 60;
    private int score = 0;
    private JButton dropButton;
    private JButton helpButton;
    private JLabel scoreLabel;
    private JLabel goalLabel;
    private JLabel timeLabel;
    private Timer time;
    private Help help;
    private buttonListener listener;

    /******************************************************************
     * Method: GUI()
     * Purpose: The constructor the the GUI
     * Input: None
     * Output: Creates the GUI
     ******************************************************************/

    public GUI()
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLocation(300, 0);
        gui = this;
        listener = new buttonListener();

        // Sets up the timer
        time = new Timer();
        time.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int t;
                t = setInterval();
                timeLabel.setText("Time: " + t);
            }
        }, 1000,1000);
        // Creates the ScorePanel and adds it to the GUI
        CreateScorePanel();
        this.add(scorePanel, BorderLayout.NORTH);
        // Creates the GamePanel and adds it to the GUI
        gamePanel = new GamePanel(this);
        gamePanel.setBackground(new Color(87,141,72));
        this.add(gamePanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    /******************************************************************
     * Method: setInterval()
     * Purpose: Sets the interval for everyt ime the time the timer
     *  ticks and checks for when it hits 0 to stop the game
     * Input: None
     * Output: An Int for the timer
     ******************************************************************/

    public int setInterval(){
        if(interval == 0){
            gamePanel.setGameLoop(false);

            gamePanel.GameOver();
            time.cancel();
        }
        return --interval;
    }

    /******************************************************************
     * Method: CreateScorePanel()
     * Purpose: Handles everything thats needed to create the ScorePanel
     * Input: None
     * Output: Creates the ScorePanel
     ******************************************************************/

    public void CreateScorePanel()
    {

        //time = new Timer(10000, null);
        scorePanel = new JPanel();
        scorePanel.setBackground(new Color(151, 108, 74));
        labelPanel = new JPanel(new GridLayout(1,2));
        buttonPanel = new JPanel(new GridLayout(1,3));
        dropButton = new JButton("Fetch");
        dropButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        dropButton.addActionListener(listener);
        helpButton = new JButton("Help");
        helpButton.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        helpButton.addActionListener(listener);
        scoreLabel = new JLabel("Score: " + score + "       ");
        scoreLabel.setFont( new Font(Font.SANS_SERIF, Font.BOLD, 16));
        goalLabel = new JLabel("Goal: "+ GOAL + "       ");
        goalLabel.setFont( new Font(Font.SANS_SERIF, Font.BOLD, 16));
        timeLabel = new JLabel("Time: 0       " );
        timeLabel.setFont( new Font(Font.SANS_SERIF, Font.BOLD, 16));
        goalLabel.setForeground(Color.WHITE);
        timeLabel.setForeground(Color.WHITE);
        scoreLabel.setForeground(Color.WHITE);

        buttonPanel.add(dropButton);
        buttonPanel.add(helpButton);
        buttonPanel.setFocusable(true);
        labelPanel.add(scoreLabel);
        labelPanel.add(goalLabel);
        labelPanel.add(timeLabel);
        labelPanel.setBackground(new Color(151, 108, 74));
        scorePanel.add(labelPanel, BorderLayout.WEST);
        scorePanel.add(buttonPanel, BorderLayout.EAST);
    }

    /******************************************************************
     * Class: buttonListener
     * Purpose: this is the ActionListener for the two buttons that are
     *  in the ScorePanel
     * Input: None
     * Output: Whatever the buttons do
     ******************************************************************/

    public class buttonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == helpButton)
            {
                help = new Help();
                help.setStart(false);
            }
            else if(e.getSource() == dropButton) {
                gamePanel.setDrop(true);
            }
        }
    }

    /******************************************************************
     * Methods: The accessors and mutators
     * Purpose: to get and set various variables
     * Input: Values
     * Output: Values
     ******************************************************************/

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    public int getGOAL() {
        return GOAL;
    }

    public Timer getTime() {
        return time;
    }
}
