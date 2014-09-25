
public class Wall implements Comparable<Wall>
{
    int startX;
    int startY;
    int endX;
    int endY;
    double wallAngle;
    
    public Wall(int startX, int startY, int endX, int endY)
    {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        //Handle edge case of vertical wall
        if (endX == startX)
        {
            wallAngle = Double.POSITIVE_INFINITY;
        }
        else
        {
            wallAngle = Math.atan((endY - startY) / (endX - startX));
        }
    }

    @Override
    // In a list, lower x values should come first. Then with duplicate x values, lower y values come first.
    public int compareTo(Wall other)
    {
        
        if (this == other)
            return 0;
        //First compare startX then startY.
        if (startX < other.startX)
            return -1;
        else if (startX > other.startX)
            return 1;
        else
        {
            if (startY < other.startY)
                return -1;
            if (startY > other.startY)
                return 1;
            return 0;
        }
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + endX;
        result = prime * result + endY;
        result = prime * result + startX;
        result = prime * result + startY;
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Wall other = (Wall) obj;
        if (endX != other.endX)
            return false;
        if (endY != other.endY)
            return false;
        if (startX != other.startX)
            return false;
        if (startY != other.startY)
            return false;
        return true;
    }
    
}
