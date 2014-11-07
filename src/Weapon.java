import java.awt.image.BufferedImage;


public abstract class Weapon
{
    
    public void dealDamage(int amount, Character damagedCharacter)
    {
        damagedCharacter.Health -= amount;
    }
    
    public BufferedImage getProjectileImage()
    {
        System.out.println("Error, abstract method called");
        return null;
    }
}
