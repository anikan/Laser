import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MainGame extends JPanel implements Runnable
{
    ImageLoader imageLoader = new ImageLoader();
    final int DELAY = 25; //Each frame occurs after 25 ms; 55 fps.
    private Thread animator;

    public GameBuilder builder = new GameBuilder();
    
    static ArrayList<Character> charList = builder.characterList//List of characters in turn order.
    static ArrayList<Wall> wallList = new ArrayList<Wall>(); //List of walls that lasers need to reflect off of.
    static ArrayList<Weapon> weaponList = new ArrayList<Weapon>(); // List of active weapons.
    
    public static BufferedImage playerImage;
    public static BufferedImage redLaserBase;
    public static BufferedImage blueLaserBase;
    public static BufferedImage greenLaserBase;
    public static BufferedImage yellowLaserBase;
    public static BufferedImage laserRed;
    public static BufferedImage laserBlue;
    public static BufferedImage laserGreen;
    public static BufferedImage laserYellow;

    public MainGame()
    {
        playerImage = imageLoader.loadImage("images/player.png");
        redLaserBase = imageLoader.loadImage("images/redLaserBase.png");
        blueLaserBase = imageLoader.loadImage("images/blueLaserBase.png");
        greenLaserBase = imageLoader.loadImage("images/greenLaserBase.png");
        yellowLaserBase = imageLoader.loadImage("images/yellowLaserBase.png");
        laserRed = imageLoader.loadImage("images/redLaser.png");
        laserBlue = imageLoader.loadImage("images/blueLaser.png");
        laserGreen = imageLoader.loadImage("images/greenLaser.png");
        laserYellow = imageLoader.loadImage("images/yellowLaser.png");

        builder.buildVS(10, 2); //Prepare stuff.

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
    /*
     * Draw the various components
     */
    public void paintComponent(Graphics g)
    {
        //Using Graphics2D so that we can rotate.
        Graphics2D g2d = (Graphics2D) g; 
        //Keep track of old angle.
        AffineTransform old = g2d.getTransform();
        for (int i = 0; i < charList.size(); i++)
        {
            g2d.drawImage(charList.get(i).getImage(), charList.get(i).xPos, charList.get(i).yPos, null);
        }
        
        for (int i = 0; i < laserList.size(); i++)
        {
            
            g2d.rotate(laserList.get(i).angle);
            g2d.drawImage(laserList.get(i).getProjectileImage(), charList.get(i).xPos, charList.get(i).yPos, null);
            g2d.setTransform(old);
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
