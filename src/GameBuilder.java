import java.util.ArrayList;

public class GameBuilder //A 100 by 100 grid for everything. Players are in the edgemost 
{
    ArrayList<Wall> wallList = new ArrayList<Wall>(); //A list of walls. Sorted by x order then y order.
    ArrayList<Character> characterList = new ArrayList<Character>(); //List of characters, human, then AI.
    private static int wallLength = 20; 
    private static final int maxCharacters = 4; //Currently set 4 players in the area.
    private int size; //Size of stage. Not sure if needed.
    
    //Spawn the walls, characters, and such.
    //Walls do not collide. Characters are placed on opposite sides if possible.
    public void buildVS(int numWalls, int numPlayers, int numAI)
    {
        spawnPlayers(numPlayers, numAI);
        for (int i = 0; i < numWalls; i++)
        {
            //Walls should not collide
        }
    }
    
    private void spawnPlayers(int numPlayers, int numAI)
    {
        boolean[] playerCount = new boolean[4]; //This array stores where characters have been added.
        for (int i = 0; i < numPlayers; i++)
        {
            //Loop while trying to find a potential location for the character.
            //0 is top left, 1 is top right, 2 is bottom left, 3 is bottom right.
            int potentialLocationIndex = (int) (Math.random() * playerCount.length);
            while (playerCount[potentialLocationIndex] = false)
            {
                potentialLocationIndex = (int) (Math.random() * playerCount.length);
            }
            
            if (potentialLocationIndex == 0)
            {
                
            }
            
        }
    }
    
    
}
