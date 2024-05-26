import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Game
{
    public static JFrame frame = new JFrame("Dungeons and Dummies");
    private static final int FPS = 60; // frames per second

    private static CardLayout cl; // card layout to manage different screens
    private static JPanel mainPanel, badEnd1Panel; // panels will be added here

    private static ArrayList<String> inputLog = new ArrayList<String>(); // log of inputs

    public static Room currentRoom;
    private static boolean toggle = false; // secret passive aggressive mode
    public static Timer timer;

    private static int gold = 0;
    public static boolean placedWhite = false;
    public static boolean placedBlack = false;

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
            badEnd1Panel = new JPanel();

            mainPanel.add(MainPanel.main, "Main Panel"); // adds the main panel

            frame.add(mainPanel); // adds the card panel to the frame
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null); //centers

            cl.show(mainPanel,"Main Panel");

            MainPanel.setPalette("Grayscale");
            MainPanel.clearPanel();

            Dialogue.reset(); // marks all booleans false
            Party.addMember(ObjectFactory.player); //adds player to party
            MainPanel.clearPanel();

            currentRoom = ObjectFactory.roomA; // starting area

            // intro dialogue
            Dialogue.setInDialogue(true);
            Dialogue.getDialogue(null,null);
            gameLoop(); //starts the gameLoop
        });
    } // end main method

    public static void addGold(int gold)
    {
        Game.gold += gold;
    }

    public static void subGold(int gold)
    {
        Game.gold -= gold;
    }

    public static int getGold()
    {
        return gold;
    }

    public static void gameLoop() // update the GUI's text box
    {
        Timer timer = new Timer(1000 / FPS, e ->
        {
            if (!ObjectFactory.player.isAlive()) // dead...
            {
                MainPanel.updatePanel(ObjectFactory.player.getName() + " is knocked out! Battle ends!");
                Battle.endBattle(false);
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
        MainPanel.clearPanel2();
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
                    "\nuse/eat/consume/drink/give: use an item or give to party member to use" +
                    "\nattack: attacks enemies in a room" +
                    "\nlook: prints a description of the room" +
                    "\nmove north/south/east/west: move in that direction" +
                    "\nmap: displays the map and where you are" +
                    "\ntalk: talks to someone in the room or in your party" +
                    "\nopen/close: opens/closes something" +
                    "\nexamine: prints description of a person/item/monster" +
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

        if (input.contains("place")) // for room V
        {
            QuestItem item = null;
            if (currentRoom == ObjectFactory.roomV)
            {
                if (!(placedWhite && placedBlack))
                {
                    if (ObjectFactory.player.getInv().contains(ObjectFactory.whiteScale))
                    {
                        MainPanel.updatePanel("After a moment of contemplation, you place the white scale you had" +
                                " obtained from the dragon onto one side of the scale.");
                        placedWhite = true;
                        if (placedBlack)
                        {
                            MainPanel.updatePanel("As soon as you place it down, the scales shift to a balanced state.");
                        }
                    }
                    else if (ObjectFactory.player.getInv().contains(ObjectFactory.blackScale))
                    {
                        MainPanel.updatePanel("After a moment of contemplation, you place the black scale you had" +
                                " obtained from the dragon onto one side of the scale.");
                        placedBlack = true;
                        if (placedWhite)
                        {
                            MainPanel.updatePanel("As soon as you place it down, the scales shift to a balanced state.");
                        }
                    }
                    else
                    {
                        MainPanel.updatePanel("You don't have anything to place onto the scales.");
                    }
                    if (placedBlack && placedWhite) // completed!
                    {
                        MainPanel.updatePanel("A strong magical force surrounds the balancing scale, engulfing the black scale in a bright," +
                                " white light, and the white scale in a cloud of darkness. After the magical effects dissipate, there is a field of" +
                                " magic around the balancing scale. Something tells you that there is nothing more to do in this room.");
                    }
                }
                else // both scales have been placed on the scales
                {
                    MainPanel.updatePanel("You have already placed both of the scales on the balance. It seems like" +
                            " they cannot be moved any longer, held in place by some arcanic force. Something tells you that" +
                            " there is nothing more to do in this room.");
                }
            }
        }

        if (input.equals("look") || input.contains("look around")) // prints description of the room + items + enemies + people
        {
            MainPanel.updatePanel(currentRoom.toString());
        }

        if (input.contains("inventory") || input.contains("inv") || input.equals("i"))
        {
            MainPanel.updatePanel(ObjectFactory.player.printInv());
        }

        if (input.contains("map"))
        {
            ImgFinder.openMap(currentRoom);
        }

        if (input.contains("spell") || input.contains("cast"))
        {
            Person caster = null;
            Person target = null;
            Spell spell = null;
            for (Person person: Party.getParty())
            {
                for (Spell s: person.getSpells())
                {
                    if (input.contains(s.getName().toLowerCase()))
                    {
                        caster = person; // determine caster
                        spell = s;
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

        if (input.contains("examine") || input.contains("look at self"))
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
                for (Person person: currentRoom.getPeople()) // sees if the person is in the room
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
                    found = false;
                    Item targetItem = null;
                    for (Item item: ObjectFactory.player.getInv()) // sees if the item is in inventory
                    {
                        if (input.contains(item.getName().toLowerCase()))
                        {
                            found = true;
                            targetItem = item;
                            break;
                        }
                    }
                    for (Item item: currentRoom.getItems()) // sees if item is in room
                    {
                        if (input.contains(item.getName().toLowerCase()))
                        {
                            found = true;
                            targetItem = item;
                            break;
                        }
                    }
                    if (found)
                    {
                        MainPanel.updatePanel(targetItem.getDesc());
                    }
                    else
                    {
                        found = false;
                        Monster targetMonster = null;
                        for (Monster monster: currentRoom.getMonsters()) // sees if monster is in room
                        {
                            if (input.contains(monster.getName().toLowerCase()))
                            {
                                found = true;
                                targetMonster = monster;
                                break;
                            }
                        }
                        if (found)
                        {
                            MainPanel.updatePanel(targetMonster.getDesc());
                        }
                        else
                        {
                            if (toggle)
                            {
                                MainPanel.updatePanel("What are you talking about? You can only examine yourself, another person, an item, or a monster.");
                            }
                            else
                            {
                                MainPanel.updatePanel("Please include a valid person/monster/item that is present in the room or in your inventory to examine it. You can also examine yourself.");
                            }
                        }
                    }
                }
            }
        }

        if (input.contains("move") || input.contains("go"))
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
            System.out.println(currentRoom.getRoomName());
        }

        //meant for consumables
        if (input.contains("eat") || input.contains("use") || input.contains("consume") || input.contains("drink") || input.contains("give"))
        {
            boolean found = false;
            boolean hasItem = false;
            boolean itemInRoom = false;
            Person target = null;
            Item item = null;
            // tries to retrieve the item
            for (int i = 0; i < ObjectFactory.gameConsumables.size(); i++)
            {
                if(input.contains(ObjectFactory.gameConsumables.get(i).getName().toLowerCase())) // see if it matches the name of any of the game items
                {
                    found = true;
                    item = ObjectFactory.gameConsumables.get(i);
                }
            }
            for (int i = 0; i < ObjectFactory.gameSpellScrolls.size(); i++)
            {
                if(input.contains(ObjectFactory.gameSpellScrolls.get(i).getName().toLowerCase())) // see if it matches the name of any of the game items
                {
                    found = true;
                    item = ObjectFactory.gameSpellScrolls.get(i);
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
                    boolean used = false;
                    //checks to see if target is specified
                    for (int i = 0; i < Party.getParty().size(); i++)
                    {
                        if(input.contains(Party.getParty().get(i).getName().toLowerCase()))
                        {
                            target = Party.getParty().get(i);
                            if (item instanceof Consumable)
                            {
                                used = true; // found target already
                                ((Consumable) item).use(target);
                                i = ObjectFactory.gamePeople.size(); // exit
                            }
                        }
                    }
                    if (!used)
                    {
                        if (item instanceof Consumable)
                        {
                            ((Consumable) item).use();
                        }
                        if (item instanceof SpellScroll)
                        {
                            ((SpellScroll) item).use();
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
                            " in your inventory or in this room.");
                }
            }
        }

        // attack!!
        if (input.contains("attack") || input.contains("fight"))
        {
            if (!currentRoom.getMonsters().isEmpty())
            {
                ArrayList<Monster> enemies = new ArrayList<Monster>();
                // populates list with alive monsters
                for (Monster monster: currentRoom.getMonsters())
                {
                    if (monster.isAlive())
                    {
                        enemies.add(monster);
                    }
                }
                if (enemies.isEmpty()) //current room is filled w/ only corpses
                {
                    if (toggle)
                    {
                        MainPanel.updatePanel("You've already murdered everything in this room! Did you forget?");
                    } else
                    {
                        MainPanel.updatePanel("There are only corpses in this room.");
                    }
                }
                else
                {
                    // starts battle
                    new Battle(Party.getParty(), new ArrayList<Monster>(currentRoom.getMonsters()), false);
                    MainPanel.updateColorsBattle();
                }
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

        // searching through corpses
        if (input.contains("search") || input.contains("loot"))
        {
            ArrayList<Monster> corpses = new ArrayList<Monster>();
            Monster target = null;
            // populates list with corpses
            for (Monster monster : currentRoom.getMonsters())
            {
                if (monster.getName().contains("Corpse"))
                {
                    corpses.add(monster);
                }
            }
            for (Monster monster : corpses)
            {
                if (input.contains(monster.getName()) || input.contains(monster.getName().substring(0, monster.getName().length()-6).trim().toLowerCase()))
                {
                    target = monster;
                }
            }
            if (target == null)
            {
                MainPanel.updatePanel("There aren't any corpses in this room to search through.");
            }
            else
            {
                if (target.getInv().isEmpty())
                {
                    MainPanel.updatePanel("The corpse holds nothing of worth.");
                }
                else
                {
                    MainPanel.updatePanel("You found:");
                    for (Item item: target.getInv())
                    {
                        MainPanel.updatePanel("[" + item.getName() + "] ");
                        ObjectFactory.player.addInvItem(item);
                    }
                    target.clearInv(); // removes item from corpse's inventory
                }
            }
        }

        if (input.contains("equip")) // changing weapons
        {
            // find weapon in inventory to switch
            ArrayList<Weapon> weaps = new ArrayList<>();
            for (Item item: ObjectFactory.player.getInv())
            {
                if (item instanceof Weapon)
                {
                    weaps.add((Weapon) item);
                }
            }
            if (weaps.isEmpty())
            {
                MainPanel.updatePanel("You don't have any weapons in your inventory to equip!");
            }
            else
            {
                Weapon weapon = null;
                for (Weapon weap : weaps)
                {
                    if (input.contains(weap.getName()))
                    {
                        weapon = weap;
                    }
                }
                if (weapon == null)
                {
                    MainPanel.updatePanel("Specify what weapon you're trying to equip.");
                }
                else
                {
                    Person person = null;
                    for (Person partyMember : Party.getParty())
                    {
                        if (input.contains(partyMember.getName()))
                        {
                            person = partyMember;
                        }
                    }
                    if (input.contains("self"))
                    {
                        person = ObjectFactory.player;
                        MainPanel.updatePanel("You equip the " + weapon.getName() + "!");
                        ObjectFactory.player.getInv().remove(weapon);
                        ObjectFactory.player.getInv().add(person.getWeapon());
                        person.setWeapon(weapon);

                    }
                    else if (person == null)
                    {
                        MainPanel.updatePanel("Specify who you're trying to equip the weapon onto.");
                    }
                    else
                    {
                        MainPanel.updatePanel(person + " equips the " + weapon + "!");
                        ObjectFactory.player.getInv().remove(weapon); // removes the weapon from player inventory
                        ObjectFactory.player.getInv().add(person.getWeapon()); // adds the person's current weapon to the player's inventory
                        person.setWeapon(weapon); // sets the new weapon
                    }
                }
            }
        }

        if (input.contains("open"))
        {
            // opens a container object and adds its items to the room
            Container con = null;
            for (Item item : currentRoom.getItems())
            {
                if (item instanceof Container) // if the item is a container object
                {
                    con = (Container) item;
                    if (input.contains(item.getName()))
                    {
                        con.open();
                    }
                }
            }
        }

        if (input.contains("pick up") || input.contains("take") || input.contains("grab")) // takes an item from the room
        {
            for (Item item: currentRoom.getItems())
            {
                if (input.contains(item.getName()) && !(item instanceof Container)) // can take any item except container
                {
                    MainPanel.updatePanel("You picked up the " + item.getName());
                    ObjectFactory.player.addInvItem(item); // adds to player inventory
                    currentRoom.removeItem(item); // removes from room
                }
            }
        }

        if (input.contains("unlock"))
        {
            Container con = null;
            for (Item item : currentRoom.getItems())
            {
                if (item instanceof Container) // if the item is a container object
                {
                    con = (Container) item;
                    if (input.contains(con.getName()))
                    {
                        if (con.isLocked())
                        {
                            con.tryUnlock();
                        }
                        else
                        {
                            con.open();
                        }
                    }
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