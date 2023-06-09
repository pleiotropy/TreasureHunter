/**
 * This class is responsible for controlling the Treasure Hunter game.<p>
 * It handles the display of the menu and the processing of the player's choices.<p>
 * It handles all of the display based on the messages it receives from the Town object.
 *
 */
import java.util.Scanner;

public class TreasureHunter
{
    //Instance variables
    private Town currentTown;
    private Hunter hunter;
    private static String mode;
    private final int EASY_STARTING_GOLD = 15;
    private final int NORMAL_STARTING_GOLD = 10;
    private final int HARD_STARTING_GOLD = 10;

    //Constructor
    /**
     * Constructs the Treasure Hunter game.
     */
    public TreasureHunter()
    {
        // these will be initialized in the play method
        currentTown = null;
        hunter = null;
        mode = "";
    }

    // starts the game; this is the only public method
    public void play ()
    {
        welcomePlayer();
        enterTown();
        showMenu();
    }

    /**
     * Creates a hunter object at the beginning of the game and populates the class member variable with it.
     */
    private void welcomePlayer()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to TREASURE HUNTER!");
        System.out.println("Going hunting for the big treasure, eh?");
        System.out.print("What's your name, Hunter? ");
        String name = scanner.nextLine();

        System.out.print("(E)asy, (N)ormal, or (H)ard Mode?: ");
        String chosenMode = scanner.nextLine();
        if (chosenMode.equalsIgnoreCase("E"))
        {
            mode = "easy";
        }
        else if (chosenMode.equalsIgnoreCase("H"))
        {
            mode = "hard";
        }
        else if (chosenMode.equalsIgnoreCase("cheater"))
        {
            mode = "cheater";
        }
        else
        {
            mode = "normal";
        }

        // set hunter instance variable
        if (mode.equals("easy"))
        {
            hunter = new Hunter(name, EASY_STARTING_GOLD);
        }
        else if (mode.equals("normal") || mode.equals("cheater"))
        {
            hunter = new Hunter(name, NORMAL_STARTING_GOLD);
        }
        else
        {
            hunter = new Hunter(name, HARD_STARTING_GOLD);
        }
    }

    /**
     * Creates a new town and adds the Hunter to it.
     */
    private void enterTown()
    {
        double markdown = 0.25;
        double toughness = 0.4;
        if (mode.equals("hard"))
        {
            // in hard mode, you get less money back when you sell items
            markdown = 0.5;

            // and the town is "tougher"
            toughness = 0.75;
        }

        // note that we don't need to access the Shop object
        // outside of this method, so it isn't necessary to store it as an instance
        // variable; we can leave it as a local variable
        Shop shop = new Shop(markdown);

        // creating the new Town -- which we need to store as an instance
        // variable in this class, since we need to access the Town
        // object in other methods of this class
        currentTown = new Town(shop, toughness);

        // calling the hunterArrives method, which takes the Hunter
        // as a parameter; note this also could have been done in the
        // constructor for Town, but this illustrates another way to associate
        // an object with an object of a different class
        currentTown.hunterArrives(hunter);

    }

    /**
     * Displays the menu and receives the choice from the user.<p>
     * The choice is sent to the processChoice() method for parsing.<p>
     * This method will loop until the user chooses to exit.
     */
    private void showMenu()
    {
        Scanner scanner = new Scanner(System.in);
        String choice = "";

        while (!(choice.equals("X") || choice.equals("x")))
        {
            System.out.println();
            System.out.println(currentTown.getLatestNews());
            System.out.println("***");
            System.out.println(hunter);
            System.out.println(currentTown);
            System.out.println("(B)uy something at the shop.");
            System.out.println("(S)ell something at the shop.");
            System.out.println("(M)ove on to a different town.");
            System.out.println("(L)ook for trouble!");
            System.out.println("(H)unt for treasure!");
            System.out.println("Give up the hunt and e(X)it.");
            System.out.println();
            System.out.print("What's your next move? ");
            choice = scanner.nextLine();
            choice = choice.toUpperCase();
            processChoice(choice);
            // TH-1: When a hunter loses all their gold through a brawl, the game should end
            // and an appropriate message should be printed.
            if (choice.equals("L") && (hunter.getGold() == 0)) {
                System.out.println(currentTown.getLatestNews());
                System.out.println("You lost all of your gold in the brawl.");
                System.out.println("*******************");
                System.out.println(" G A M E   O V E R ");
                System.out.println("*******************");
                choice = "X";
            }
            if (choice.equals("H"))
            {
                if (Treasure.allTreasuresFound())
                {
                    System.out.println("You have found all three treasures!");
                    System.out.println("*******************");
                    System.out.println("  Y O U   W I N  ! ");
                    System.out.println("*******************");
                    choice = "X";
                }
            }
            if (!choice.equals("L")) {
                System.out.print("Hit Enter to continue! ");
                scanner.nextLine();
            }
            TreasureHunter.clearScreen();
        }
    }

    /**
     * Takes the choice received from the menu and calls the appropriate method to carry out the instructions.
     * @param choice The action to process.
     */
    private void processChoice(String choice)
    {
        if (choice.equals("B") || choice.equals("b") || choice.equals("S") || choice.equals("s"))
        {
            currentTown.enterShop(choice);
        }
        else if (choice.equals("M") || choice.equals("m"))
        {
            if (currentTown.leaveTown())
            {
                //This town is going away so print its news ahead of time.
                System.out.println(currentTown.getLatestNews());
                enterTown();
            }
        }
        else if (choice.equals("L") || choice.equals("l"))
        {
            currentTown.lookForTrouble();
        }
        else if (choice.equals("H") || choice.equals("h"))
        {
            currentTown.huntForTreasure();
        }
        else if (choice.equals("X") || choice.equals("x"))
        {
            System.out.println("Fare thee well, " + hunter.getHunterName() + "!");
        }
        else
        {
            System.out.println("Yikes! That's an invalid option! Try again.");
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String getMode() {
        return mode;
    }
}

