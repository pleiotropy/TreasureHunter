/**
 * This class represents the treasures for which the Hunter is hunting.
 */
public class Treasure {
    // instance variable(s)
    private String treasureType;

    // static variables
    private static boolean foundGem, foundGold, foundComputer;

    // constructor(s)
    // create a new treasure in the town when hunter enters the town
    public Treasure(String type) {
        treasureType = type;
    }

    // methods
    // check for duplicate treasure
    public static boolean duplicateTreasure(String treasure)
    {
        if (treasure.equals("a glittery gem") && foundGem)
        {
            return true;
        }
        if (treasure.equals("more gold than you can carry") && foundGold)
        {
            return true;
        }
        if (treasure.equals("a computer") && foundComputer)
        {
            return true;
        }
        return false;
    }

    // mark treasure as found
    public static void markTreasureAsFound(String treasure)
    {
        if (!duplicateTreasure(treasure))
        {
            if (treasure.equals("a glittery gem"))
            {
                foundGem = true;
            }
            else if (treasure.equals("more gold than you can carry"))
            {
                foundGold = true;
            }
            else
            {
                foundComputer = true;
            }
        }
        else
        {
            System.out.println("You already found a treasure like this one. You can't carry another.");
        }
    }

    // check if all treasures have been found
    public static boolean allTreasuresFound()
    {
        return foundComputer && foundGold && foundGem;
    }

    public String toString()
    {
        return treasureType;
    }

}
