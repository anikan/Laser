import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MainGame extends JPanel implements Runnable
{
    ImageLoader imageLoader = new ImageLoader();
    static ArrayList<Character> charList = new ArrayList<Character>(); //List of characters in turn order.
    static ArrayList<Wall> wallList = new ArrayList<Wall>(); //List of walls that lasers need to reflect off of.
    static ArrayList<Laser> laserList = new ArrayList<Laser>(); // List of active lasers.
    final int DELAY = 25; //Each frame occurs after 25 ms; 55 fps.
    private Thread animator;

    public GameBuilder builder = new GameBuilder();
    public static BufferedImage playerImage;
    public MainGame()
    {
        playerImage = imageLoader.loadImage("player.png");
        builder.build(10); //Prepare stuff.

    }

    public static void main(String[] args)
    {
        MainGame game = new MainGame();

        while (true)
        {
            for(int i = 0; i < game.charList.size(); i++)
            {
                game.charList.get(i).act();
            }
        }

    }

    //Runs after the window is created, allowing for drawing.
    public void addNotify()
    {
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }

    public void paintComponent(Graphics g)
    {
        for (int i = 0; i < charList.size(); i++)
        {
            g.drawImage(charList.get(i).getImage(), charList.get(i).xPos, charList.get(i).yPos, null);
        }
    }

    @Override
    public void run()
    {
        long previousTime, sleepTime, timeDifference;
        long counter = 0;
        previousTime = System.currentTimeMillis();
        while (true)
        {
            repaint();
            timeDifference = System.currentTimeMillis() - previousTime;
            sleepTime = DELAY - timeDifference;
            if (sleepTime < 0)
            {
                sleepTime = 2;
            }
            try
            {
                Thread.sleep(sleepTime);
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            previousTime = System.currentTimeMillis();
            counter++;
        }

    }
}
