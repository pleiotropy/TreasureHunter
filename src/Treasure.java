/**
 * This class represents the treasures for which the Hunter is hunting.
 */
public class Treasure {
    // instance variable(s)
    private String treasureType;

    // static variables
    private static boolean foundGem = false;
    private static boolean foundBananas = false;
    private static boolean foundComputer = false;

    // constructor(s)
    // create a new treasure in the town when hunter enters the town
    public Treasure(String treasureType) {
        this.treasureType = treasureType;
    }

    // methods
    // check for duplicate treasure
    public static boolean duplicateTreasure(String treasure)
    {
        if (treasure.equals("a glittery gem") && foundGem)
        {
            return true;
        }
        if (treasure.equals("bananas") && foundBananas)
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
            else if (treasure.equals("bananas"))
            {
                foundBananas = true;
            }
            else
            {
                foundComputer = true;
            }
            Hunter.addTreasureToList(treasure);
        }
        else
        {
            System.out.println("You already found a treasure like this one. You can't carry another.");
        }
    }

    // check if all treasures have been found
    public static boolean allTreasuresFound()
    {
        return foundComputer && foundBananas && foundGem;
    }

    public String toString()
    {
        return treasureType;
    }

}
