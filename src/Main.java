import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main
{
    public static JFrame frame = new JFrame("Dungeons and Dummies");
    private static final int FPS = 60; // frames per second

    private static CardLayout cl; // card layout to manage different screens
    private static JPanel mainPanel, deathPanel, badEnd1Panel; // panels will be added here

    private static ArrayList<String> inputLog = new ArrayList<String>(); // log of inputs

    public static Room currentRoom;
    private static boolean toggle = false; // secret passive aggressive mode
    public static Timer timer;

    public static void main(String[] args) // main method
    {
        SwingUtilities.invokeLater(() ->
        {
            ImageIcon icon = new ImageIcon("img/ICON.png");
            frame.setIconImage(icon.getImage());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);

            cl = new CardLayout(); // creates a new card layout
            mainPanel = new JPanel(cl); // creates new panel with card layout
            deathPanel = new JPanel();
            badEnd1Panel = new JPanel();

            mainPanel.add(MainPanel.main, "Main Panel"); // adds the main panel
            deathPanel.add(Screen.getScreen(1));
            mainPanel.add(deathPanel, "Death Panel");
            badEnd1Panel.add(Screen.getScreen(2));
            mainPanel.add(deathPanel, "Bad End 1 Panel");
            //more panels here for different endings ??!?!?!?!? or graphics ig

            frame.add(mainPanel); // adds the card panel to the frame
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null); //centers

            cl.show(mainPanel,"Main Panel");

            MainPanel.setPalette("Grayscale");
            MainPanel.clearPanel();

            Dialogue.reset(); // marks all booleans false
            Party.addMember(ObjectFactory.player); //adds player to party

            currentRoom = ObjectFactory.roomA; // starting area

            // intro dialogue
            Dialogue.setInDialogue(true);
            Dialogue.getDialogue(null,null);
            gameLoop(); //starts the gameLoop
        });
    } // end main method


    public static void gameLoop() // update the GUI's text box
    {
        Timer timer = new Timer(1000 / FPS, e ->
        {
            // these things will be run constantly
            // probably use this to check if certain conditions are met...
            if (Battle.inBattle())
            {
                Battle.printStatus(); // updates status
            }
            if (!ObjectFactory.player.isAlive()) // dead...
            {
                cl.show(mainPanel,"Death Panel");
                Screen.playGraphic1();
                ObjectFactory.player.addHp(1);
                ObjectFactory.player.alive = true;
            }
        });
        timer.start();
    }

    public static void openMainPanel()
    {
        cl.show(mainPanel,"Main Panel");
    }

    //process the different commands that can be inputted into the input area of the GUI
    public static void processCommand (String input)
    {
        MainPanel.updatePanel(">" + input + "\n"); // records the user's input
        inputLog.add(input);
        String prevInput = "";
        if ((inputLog.size() > 1))
        {
            prevInput = inputLog.get(inputLog.size() - 2); // finds the previous input
        }

        if (input.equals("help"))
        {
            if (toggle)
            {
                MainPanel.updatePanel("Oh, you need help? Can't you just figure everything out by yourself?");
            }
            MainPanel.updatePanel("List of commands:" +
                    "\ntutorial: explains how the game works" +
                    "\nhelp: brings up the list of commands" +
                    "\npalette: lets you set the palette/theme of the game" +
                    "\nuse/eat/consume/drink: use an item" +
                    "\nlook: prints a description of the room" +
                    "\nmove north/south/east/west: move in that direction" +
                    "\ntalk: talks to someone in the room or in your party" +
                    "\nopen/close: opens/closes something" +
                    "\nexamine: prints description of a person in your party" +
                    "\nparty: check up on the people in your party" +
                    "\ninventory/inv/i: prints your inventory"+
                    "\ntoggle: turns on/off passive aggressive and possibly hateful flavor text"); // ADD MORE
        }

        if (input.contains("tutorial"))
        {
            MainPanel.updatePanel("This is a TEXT ADVENTURE game, meaning that there are (almost) no visuals" +
                    " and the game is entirely text." +
                    " Here is a demonstration with the proper annotations." +
                    "\n\nDEMONSTRATION" +
                    "\nYou are in a ROOM. It is small. There is an APPLE. Exits: N E S" +
                    "\n(This is the room description. It gives you the description of the room as well as a list of the objects" +
                    " and entities in the room. You are also given a list of directions that you can move in.)" +
                    "\n\n>eat apple" +
                    "\n(When the user types in the command \"eat apple\", it is logged in this text box." +
                    "\n\nYou eat the apple." +
                    "\n(This is the outcome of the command that the user inputted.)" +
                    "\n\nIf for SOME REASON, this doesn't make any sense, type the \"help\" command later." +
                    "\n-------------------------------------------------------------------------\n");
        }

        if (input.equals("look") || input.contains("look around")) // prints description of the room + items + enemies + people
        {
            MainPanel.updatePanel(currentRoom.toString());
        }

        if (input.contains("inventory") || input.contains("inv") || input.equals("i"))
        {
            MainPanel.updatePanel(ObjectFactory.player.printInv());
        }

        if (input.contains("spell") || input.contains("cast"))
        {
            Person caster = null;
            Person target = null;
            boolean foundCaster = false;
            boolean foundTarget = false;
            Spell spell = null;
            for (Person person: Party.getParty())
            {
                for (Spell s: person.getSpells())
                {
                    if (input.contains(s.getName().toLowerCase()))
                    {
                        caster = person; // determine caster
                        spell = s;
                        foundCaster = true;
                    }
                }
            }
            if (spell != null)
            {
                if (spell instanceof HealingSpell)
                {
                    for (Person person : Party.getParty())
                    {
                        if (input.contains(person.getName().toLowerCase()))
                        {
                            target = person;
                        }
                    }
                }
                else
                {
                    MainPanel.updatePanel("Cannot cast that right now.");
                }
            }
            else
            {
                if (toggle)
                {
                    MainPanel.updatePanel("None of you dimwits have this spell.");
                }
                else
                {
                    MainPanel.updatePanel("Spell doesn't exist/no one in your party has it.");
                }
            }
            if (target != null)
            {
                if (caster.getCurrentMp() >= spell.getMpCost())
                {
                    spell.cast(caster, target);
                }
                else
                {
                    MainPanel.updatePanel("Not enough MP!");
                }
            }
            else
            {
                if (toggle)
                {
                    MainPanel.updatePanel("What?? Can you at LEAST specify who you're trying to cast that on?");
                }
                else
                {
                    MainPanel.updatePanel("No target specified.");
                }
            }
        }

        if (input.contains("party"))
        {
            MainPanel.updatePanel(Party.print());
        }

        if (input.contains("examine"))
        {
            if (input.contains("self"))
            {
                MainPanel.updatePanel(ObjectFactory.player.examine());
                ImgFinder.updateImage((Person) ObjectFactory.player);
                timer = new Timer(3000, new ActionListener()
                {
                    int step = 0;
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        step++;
                        if (step == 1)
                        {
                            MainPanel.clearPanel2();
                            timer.stop();
                        }
                    }
                });
                timer.start();
            }
            else
            {
                boolean found = false;
                Person target = null;
                for (Person person: Party.getParty()) // sees if the person is in the party
                {
                    if (input.contains(person.getName().toLowerCase()))
                    {
                        found = true;
                        target = person;
                        break;
                    }
                }
                for (Person person: currentRoom.getPeople()) // sees if the person is in the party
                {
                    if (input.contains(person.getName().toLowerCase()))
                    {
                        found = true;
                        target = person;
                        break;
                    }
                }
                if (found)
                {
                    if (target == ObjectFactory.player)
                    {
                        if (toggle)
                        {
                            MainPanel.updatePanel("There's no one to talk to but yourself, you know.");
                        }
                        else
                        {
                            MainPanel.updatePanel("No one to talk to but yourself.");
                        }
                    }
                    else
                    {
                        ImgFinder.updateImage((Person) target);
                        MainPanel.updatePanel(target.examine());
                        timer = new Timer(3000, new ActionListener()
                        {
                            int step = 0;
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                step++;
                                if (step == 1)
                                {
                                    MainPanel.clearPanel2();
                                    timer.stop();
                                }
                            }
                        });
                        timer.start();
                    }
                }
                else
                {
                    if (toggle)
                    {
                        MainPanel.updatePanel("What are you talking about? You can't examine someone who doesn't exist or isn't in your party.");
                    }
                    else
                    {
                        MainPanel.updatePanel("That person doesn't exist or isn't in your party.");
                    }
                }
            }
        }

        if (input.equals("p badend1"))
        {
            cl.show(mainPanel,"Bad End 1 Panel");
            Screen.playBadEnd1();
        }

        if (input.contains("move"))
        {
            boolean invalid = false;
            if (input.contains("north") || input.contains(" n"))
            {
               if (currentRoom.getExits().contains("N"))
               {
                   Nav.move(currentRoom,"North");
               }
               else
               {
                   invalid = true;
               }
            }
            else if (input.contains("south") || input.contains(" s"))
            {
                if (currentRoom.getExits().contains("S"))
                {
                    Nav.move(currentRoom,"South");
                }
                else
                {
                    invalid = true;
                }
            }
            else if (input.contains("east") || input.contains(" e"))
            {
                if (currentRoom.getExits().contains("E"))
                {
                    Nav.move(currentRoom,"East");
                }
                else
                {
                    invalid = true;
                }
            }
            else if (input.contains("west") || input.contains(" w"))
            {
                if (currentRoom.getExits().contains("W"))
                {
                    Nav.move(currentRoom,"West");
                }
                else
                {
                    invalid = true;
                }
            }
            else
            {
                if (toggle)
                {
                    MainPanel.updatePanel("...can you be more specific? north, south, east, or west??");
                }
                else
                {
                    MainPanel.updatePanel("Please specify what direction you want to move in!");
                }
            }
            if (invalid)
            {
                if (toggle)
                {
                    MainPanel.updatePanel("Hello? You can't walk that way.");
                }
                else
                {
                    MainPanel.updatePanel("Invalid exit. You can't move that way.");
                }
            }
            else
            {
                MainPanel.updatePanel(currentRoom.toString());
            }
        }

        //meant for consumables
        if (input.contains("eat") || input.contains("use") || input.contains("consume") || input.contains("drink"))
        {
            boolean found = false;
            boolean hasItem = false;
            boolean itemInRoom = false;
            Person target = null;
            Consumable item = null;
            // tries to retrieve the item
            for (int i = 0; i < ObjectFactory.gameConsumables.size(); i++)
            {
                if(input.contains(ObjectFactory.gameConsumables.get(i).getName().toLowerCase())) // see if it matches the name of any of the game items
                {
                    found = true;
                    item = ObjectFactory.gameConsumables.get(i);
                }
                else
                {
                    i = ObjectFactory.gameConsumables.size();
                }
            }
            if (found)
            {
                // check to see if player has this OR it is in the room
                if (ObjectFactory.player.getInv().contains(item))
                {
                    hasItem = true;
                    ObjectFactory.player.removeInvItem(item);
                }
                else if (currentRoom.getItems().contains(item))
                {
                    itemInRoom = true;
                    currentRoom.removeItem(item);
                }
                else
                {
                    if (toggle)
                    {
                        MainPanel.updatePanel("I don't see that anywhere. Try again, but with something that ACTUALLY exists.");
                    }
                    else
                    {
                        MainPanel.updatePanel("You don't have that item.");
                    }
                }
                if (hasItem || itemInRoom)
                {
                    //checks to see if target is specified
                    for (int i = 0; i < ObjectFactory.gamePeople.size(); i++)
                    {
                        if(input.contains(ObjectFactory.gamePeople.get(i).getName().toLowerCase()))
                        {
                            target = ObjectFactory.gamePeople.get(i);
                            item.use(target);
                        }
                        else
                        {
                            item.use(); // use on self since target not specified
                            i = ObjectFactory.gamePeople.size(); // exit
                        }
                    }
                }
            }
            else
            {
                if (toggle)
                {
                    MainPanel.updatePanel("What? Can't you use your brain or type a little better? Maybe SPECIFY what you want to use?");
                }
                else
                {
                    MainPanel.updatePanel("Make sure you aren't making any typos, and that the object you are trying to use exists" +
                            "in your inventory or in this room.");
                }
            }
        }

        // attack!!
        if (input.contains("attack") || input.contains("fight"))
        {
            if (!currentRoom.getMonsters().isEmpty())
            {
                new Battle(Party.getParty(), new ArrayList<Monster>(currentRoom.getMonsters()), false);
                MainPanel.updateColorsBattle();
            }
            else
            {
                if (toggle)
                {
                    MainPanel.updatePanel("You hear that?\nSilence.\nBecause there's no monster in this room.");
                } else
                {
                    MainPanel.updatePanel("There aren't any monsters in this room.");
                }
            }
        }

        // talking to people !
        if (input.contains("talk"))
        {
            boolean valid = false;
            boolean found = false;
            Entity target = null;
            for (int i = 0; i < ObjectFactory.gamePeople.size(); i++) // sees if a valid person's name is listed
            {
                if(input.contains(ObjectFactory.gamePeople.get(i).getName().toLowerCase()))
                {
                    valid = true;
                    target = ObjectFactory.gamePeople.get(i);
                    i = ObjectFactory.gamePeople.size(); // exit
                }
                else
                {
                    i = ObjectFactory.gamePeople.size(); // exit
                }
            }
            for (Person person: Party.getParty()) // sees if the person is in the party
            {
                if (input.contains(person.getName().toLowerCase()))
                {
                    valid = true;
                    found = true;
                    target = person;
                    break;
                }
            }
            for (Person person: currentRoom.getPeople()) // sees if the person is in the room
            {
                if (input.contains(person.getName().toLowerCase()))
                {
                    valid = true;
                    found = true;
                    target = person;
                    break;
                }
            }
            if (valid)
            {
                if (found)
                {
                    Dialogue.setInDialogue(true);
                    ImgFinder.updateImage((Person) target);
                    Dialogue.getDialogue(currentRoom, target); // this class will handle all the dialogue
                }
                else
                {
                    // person exists but isn't present
                    if (toggle)
                    {
                        MainPanel.updatePanel("That person isn't here. Maybe medication will help you with your hallucinations?");
                    }
                    else
                    {
                        MainPanel.updatePanel("You can only talk to people who are here.");
                    }
                }
            }
            else
            {
                // automatically talks to the person in the room
                if (!currentRoom.getPeople().isEmpty())
                {
                    Dialogue.setInDialogue(true);
                    ImgFinder.updateImage(currentRoom.getPeople().get(0));
                    Dialogue.getDialogue(currentRoom, currentRoom.getPeople().get(0));
                }
                else
                {
                    if (toggle)
                    {
                        MainPanel.updatePanel("Talking to imaginary friends? Perhaps you should get some real friends.");
                    }
                    else
                    {
                        MainPanel.updatePanel("Please specify who you wish to talk to.");
                    }
                }
            }
        }

        // change palette of the game
        if (input.contains("palette") || input.contains("theme"))
        {
            if (toggle)
            {
                MainPanel.updatePanel("Can't you just focus on playing the game?");
            }
            MainPanel.updatePanel("Input the corresponding # to set the palette." +
                    "\n1) Grayscale" +
                    "\n2) Abyssal" +
                    "\n3) Spiral" +
                    "\n4) Flesh" +
                    "\n5) Mythos" +
                    "\n6) Skel King" +
                    "\n7) Tides" +
                    "\n8) Seaside");
        }

        // sees if its an int
        try
        {
            int num = Integer.parseInt(input); // is an integer!
            //checks to see what the previous input was

            if (prevInput.contains("palette") || prevInput.contains("theme"))
            {
                 // numbers correspond to theme
                switch (num)
                {
                    case 1:
                        MainPanel.setPalette("Grayscale");
                        if (toggle)
                        {
                            MainPanel.updatePanel("BORING.");
                        }
                        break;
                    case 2:
                        MainPanel.setPalette("Abyssal");
                        if (toggle)
                        {
                            MainPanel.updatePanel("This one's a little nicer.");
                        }
                        break;
                    case 3:
                        MainPanel.setPalette("Spiral");
                        if (toggle)
                        {
                            MainPanel.updatePanel("Hmm okay.");
                        }
                        break;
                    case 4:
                        MainPanel.setPalette("Flesh");
                        if (toggle)
                        {
                            MainPanel.updatePanel("This is ugly. Change it.");
                        }
                        break;
                    case 5:
                        MainPanel.setPalette("Mythos");
                        if (toggle)
                        {
                            MainPanel.updatePanel("Meh.");
                        }
                        break;
                    case 6:
                        MainPanel.setPalette("Skel King");
                        if (toggle)
                        {
                            MainPanel.updatePanel("Ugh, the blue is so vivid.");
                        }
                        break;
                    case 7:
                        MainPanel.setPalette("Tides");
                        if (toggle)
                        {
                            MainPanel.updatePanel("Very calming.");
                        }
                        break;
                    case 8:
                        MainPanel.setPalette("Seaside");
                        if (toggle)
                        {
                            MainPanel.updatePanel("This is okay, I guess.");
                        }
                        break;
                    default:
                        if (toggle)
                        {
                            MainPanel.updatePanel("Ugh, you're supposd to type in a NUMBER.");
                        }
                        else
                        {
                            MainPanel.updatePanel("Invalid number.");
                        }
                        break;
                }
            }
        }
        catch (NumberFormatException e)
        {
            // not a number ig
        }

        if (input.contains("toggle"))
        {
            if (toggle)
            {
                toggle = false;
                MainPanel.updatePanel("Oh what, you couldn't handle it?");
            }
            else
            {
                toggle = true;
                MainPanel.updatePanel("Yikes, can't believe someone would actually do that.");
            }
        }
    }
} // end main class