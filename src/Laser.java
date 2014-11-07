import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Laser extends Weapon
{
    //Keep track of the front of the laser. 
    int startX;
    int startY;
    
    //Keep track of the back of the laser.
    int endX;
    int endY;
    
    //Know speeds of the laser
    int velocity;
    int velX; //X component of movement, calculated with angle
    int velY; //Y component of movement, calculated with angle
   
    //The coordinates of the vertex where it hits a wall.
    int vertX;
    int vertY;
    
    //Damage it deals to a target.
    int damage = 5;
    
    //The angle the front of the laser is heading.
    double angle;
    int faction; //Decides color and damaging capability.
    
    public Laser()
    {
        //Setting components.
        velX = (int) (velocity * Math.cos(Math.toRadians(angle)));
        velY = (int) (velocity * Math.sin(Math.toRadians(angle)));
    }
    
    public void shoot(int startX, int startY, int angle)
    {
        
    }
    
    public void act()
    {

        for (int i = 0; i < MainGame.charList.size(); i++)
        {
            if (checkCollisionCharacter(MainGame.charList.get(i)))
            {
                return;
            }
        }
        
        boolean searching = true;
        
        //Starting from center of list. Binary searching to check only viable walls.
        int searchIndex = MainGame.wallList.size()/2;
        ArrayList<Wall> checkList = new ArrayList<Wall>();
        
        //Binary search to speed things up.
        while (searching)
        {
            
            if (startX > MainGame.wallList.get(searchIndex).endX)
            {
                
            }
        }
        
        startX += velX;
        startY += velY;
        endX += velX;
        endY += velX;
        
        if (endX == vertX || endY == vertY)
        {
            vertX = -1;
            vertY = -1;
        }
    }
    
    //Recursively looking for possibilities via binary search. TempWallList is a copy of the actual wallList
    public ArrayList<Wall> wallSearch(int firstIndex, int lastIndex, ArrayList<Wall> returnList, ArrayList<Wall> tempWallList)
    {
        if (firstIndex == lastIndex)
        {
            return returnList;
        }
        
        else
        {
            //Getting median entry in this part of the sorted list. If this wall's furthest right point is less than the x of the laser, then the possible wall cannot be further left; only need to check further right.
            if (startX > tempWallList.get((lastIndex + firstIndex )/ 2).endX)
            {
                return wallSearch((lastIndex + firstIndex )/ 2, lastIndex, returnList, tempWallList);
            }
            
            //Similar concept to the previous lines, if the furthest left part of the wall is still too far, it has to be in the lower part of the arraylist.
            else if (startX < tempWallList.get((lastIndex + firstIndex )/ 2).startX)
            {
                return wallSearch(firstIndex, (lastIndex + firstIndex )/ 2, returnList, tempWallList);
            }
            
            //Repeating with y components.
            //Getting median entry in this part of the sorted list. If this wall's furthest down point is less than the y of the laser, then the possible wall cannot be further up; only need to check further down.
            else if (startY > tempWallList.get((lastIndex + firstIndex )/ 2).endY)
            {
                return wallSearch((lastIndex + firstIndex )/ 2, lastIndex, returnList, tempWallList);
            }
                
            //Similar concept to the previous lines, if the furthest up part of the wall is still too far, it has to be in the lower part of the arraylist.
            else if (startY < MainGame.wallList.get((lastIndex + firstIndex )/ 2).startY)
            {
                return wallSearch(firstIndex, (lastIndex + firstIndex )/ 2, returnList, tempWallList);
            }
            
            else
            {
                returnList.add(MainGame.wallList.get((lastIndex + firstIndex ) / 2));
                tempWallList.remove((lastIndex + firstIndex ) / 2);
                return wallSearch(firstIndex, (lastIndex + firstIndex )/ 2, returnList, tempWallList);
            }
        }
    }
    
    
    public boolean checkCollisionCharacter(Character character)
    {
        if (startX > character.xPos && startX > character.xPos + character.xLength && startY > character.yPos && startY > character.yPos + character.yLength && this.faction != character.faction)
        {
            
            character.Health -= damage;
            //Destroy laser and remove fom list.
            MainGame.weaponList.remove(this);
            return true;
        }
        
        else
        {
            return false;
        }
    }
    
    public boolean checkCollisionWall(Wall wall)
    {
        //Check if the laser is in the wall's hit box. The startX must be on the left side and the endX must be on the right. StartY must be up (lower Y), endY must be below (higher Y).
        if (startX >= wall.startX && startX <= wall.endX && startY >= wall.startY && startY <= wall.endY && wallCheck(wall))
        {
            //Reflect if hitting the wall.
            wallBounce(wall.wallAngle);
            return true;
        }
        
        return false;
    }
    
    /*
     * This function will be called to check if this laser is colliding with this wall and reflect it it is..
     */
    public void wallBounce(double wallAngle)
    {
            //Return new angle of laser. From examples, I see a pattern that the angle after the bounce equals the wall angle minus the laser angle.
            double newAngle = 2 * wallAngle - angle;
            vertX = startX;
            vertY = startY;
            angle = newAngle;
    }
    
    private boolean wallCheck(Wall wall)
    {
        //Keep track of distance between the laser and the start of the wall. If the yDistance has the proportion as this distance times the tangent, then the laser is colliding with the wall
        int xDistance = startX - wall.startX;
        
        //A bit of trig to check if the laser's point collides with the line of the wall. Using fact that tan(theta) = yChange / xChange
        if (xDistance * Math.tan(Math.toRadians(wall.wallAngle)) == (startY - wall.startY))
        {
            return true;
        }
        
        else
        {
            return false;
        }
    }

    public BufferedImage getProjectileImage() {
        if (faction == 0)
        {
            return MainGame.laserRed;
        }
        
        else if (faction == 1)
        {
            return MainGame.laserBlue;
        }
        
        else if (faction == 2)
        {
            return MainGame.laserGreen;
        }
        
        else if (faction == 3)
        {
            return MainGame.laserYellow;
        }
        
        else
        {
            return null;
        }
    }
}
