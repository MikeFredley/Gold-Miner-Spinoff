import javax.swing.*;

public class SplashScreen extends JWindow{

    private final int HEIGHT = 672;
    private final int WIDTH=1176;

    /******************************************************************
     *
     ******************************************************************/

    public SplashScreen(){

        this.setSize(WIDTH, HEIGHT);
        this.setLocation(400,230);
        this.getContentPane().add(new JLabel("", new ImageIcon("SplashScreen.png"),SwingConstants.CENTER));
        this.setVisible(true);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.setVisible(false);
        this.dispose();
    }
}
