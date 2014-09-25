import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Character
{
    int Health; //Have this to not die
    int xPos;
    int yPos;
    
    //Variables to keep track of hitbox of character.
    final int xLength = 50; 
    final int yLength = 50;
    int faction; //Will support mutliple factions, 0 is player, 1 is first enemy.
    ArrayList<Weapon> wepList = new ArrayList<Weapon>();
    
    public Character()
    {
        
    }
    
    public void act()
    {
        
    }
    
    public BufferedImage getImage()
    {
        
        return MainGame.playerImage;
    }
    
}
