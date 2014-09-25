
public abstract class Weapon
{
    
    public void dealDamage(int amount, Character damagedCharacter)
    {
        damagedCharacter.Health -= amount;
    }
}
