import java.util.ArrayList;

public class Battle
{
    // instance variables
    private static ArrayList<Person> party;
    private static ArrayList<Monster> enemyParty;
    private static int activeChar; // 0 1 2 3
    private static int turn; // counts turns
    private static boolean inBattle;
    private static boolean partyTurn; // if it's the party's turn to act

    // constructor
    public Battle(ArrayList<Person> party, ArrayList<Monster> enemyParty, boolean ambushed)
    {
        Battle.party = party;
        Battle.enemyParty = enemyParty;
        activeChar = 0;
        inBattle = true;
        turn = 0;
        if (ambushed)
        {
            partyTurn = false;
            enemyTurn();
        }
        else
        {
            partyTurn = true;
            startTurn();
        }
    }

    // getters
    public static boolean inBattle()
    {
        return inBattle;
    }

    public static ArrayList<Monster> getEnemyParty()
    {
        return enemyParty;
    }

    public static ArrayList<Person> getParty()
    {
        return party;
    }

    public static boolean enemyPartyDead()
    {
        boolean output = true;
        for (Monster monster: enemyParty)
        {
            if (monster.isAlive())
            {
                output = false;
            }
        }
        return output;
    }

    public static boolean partyDead()
    {
        boolean output = true;
        for (Person person: party)
        {
            if (person.isAlive())
            {
                output = false;
            }
        }
        return output;
    }

    // setters
    public static void setInBattle(boolean state)
    {
        inBattle = state;
    }

    // brain methods
    public static void processCommand(String input)
    {
        boolean found = false;
        boolean invalid = false;
        Entity target = null;
        MainPanel.updatePanel(">" + input + "\n"); // records the user's input
        if(partyTurn && !(activeChar == party.size())) // only take command when its the party's turn
        {
            if (input.contains("attack") || input.contains("hit"))
            {
                // searches for a target
                for (Monster monster: enemyParty)
                {
                    if (input.contains(monster.getName().toLowerCase()))
                    {
                        found = true;
                        target = monster;
                    }
                }
                if (found)
                {
                    party.get(activeChar).attack(target);
                }
                else
                {
                    party.get(activeChar).attack(enemyParty.get(0));
                }
                activeChar++;
                turn++;
            }
            else if (input.equals("spells") || input.equals("spell"))
            {
                MainPanel.updatePanel(party.get(activeChar).printSpells()); // prints the person's list of spells
            }
            else if (input.contains("spell") || input.contains("cast"))
            {
                boolean casted = false;
                Spell spell = null;
                for (int i = 0; i < party.get(activeChar).getSpells().size(); i++)
                {
                    if (input.contains(party.get(activeChar).getSpells().get(i).getName().toLowerCase()))
                    {
                        spell = party.get(activeChar).getSpells().get(i);
                        i = party.get(activeChar).getSpells().size(); // to exit
                    }
                }
                if (spell != null)
                {
                    if (spell instanceof HealingSpell)
                    {
                        for (Person person: party)
                        {
                            if (input.contains(person.getName().toLowerCase()))
                            {
                                target = person;
                                if (party.get(activeChar).getCurrentMp() >= spell.getMpCost())
                                {
                                    spell.cast(party.get(activeChar), target);
                                    casted = true;
                                }
                                else
                                {
                                    MainPanel.updatePanel("Not enough MP!");
                                }
                            }
                        }
                        if (target == null)
                        {
                            // target has not been specified, assume cast on self
                            if (party.get(activeChar).getCurrentMp() >= spell.getMpCost())
                            {
                                spell.cast(party.get(activeChar));
                                casted = true;
                            }
                            else
                            {
                                MainPanel.updatePanel("Not enough MP!");
                            }
                        }
                    }
                    /* ADD MORE CODE HERE SHAYLA
                    if (spell instanceof BuffSpell) // prolly combine with healing spell
                    { // whole purpose is to either check party or enemy party for the spell target

                    }
                    if (spell instanceof DebuffSpell)
                    {

                    }
                    if (spell instanceof AttackSpell)
                    {

                    }
                     */
                    if (casted) // only increments if spell was sucessfully cast
                    {
                        activeChar++;
                        turn++;
                    }
                }
                else
                {
                    MainPanel.updatePanel("Please specify what spell you want to use. (Ex: use spell cure wounds on dain)");
                }
            }
            else if (input.contains("item"))
            {
                activeChar++;
                turn++;
            }
            else if (input.contains("run") || input.contains("flee"))
            {
                activeChar++;
                turn++;
            }
            else if (input.contains("help"))
            {
                MainPanel.updatePanel("In battle, you have a limited amount of things you can do. You can \"ATTACK\" a monster," +
                        " specifying the name of the monster you wish to attack, unless you want to just attack the first monster there. " +
                        "\n\nYou can \"CAST\" a \"SPELL\" on your party or on the enemy, depending on what kind of spell it is. Specify" +
                        " WHAT spell it is and WHO you want to cast it on. With spells that target more than one entity, you do not" +
                        " need to specify. To bring up the list of spells that you have, just input \"SPELL\". \n\nYou can use an item from your inventory, by" +
                        " including the command \"ITEM\" and specifying WHAT item you want to use and WHO you want to use it on. If" +
                        " you don't specify anyone, you will automatically use it on yourself if it is a consumable or use it on an enemy" +
                        " if it deals damage. You can use \"INVENTORY\" to print the list of items you have. " +
                        "\n\nYou can also try to \"FLEE\" or \"RUN\" from the battle." +
                        "\n\nEXAMPLES OF HOW TO USE COMMANDS:" +
                        "\n>attack stirge" +
                        "\n>use spell healing wounds on dain" +
                        "\n>use item health potion on todd" +
                        "\n>flee");
            }
            else
            {
                MainPanel.updatePanel("You can only attack, use a spell, use an item, or try to run away.");
                invalid = true;
            }
            if (!invalid && !(activeChar == party.size()))
            {
                startTurn();
            }
        }
        if (activeChar == party.size())
        {
            partyTurn = false;
            activeChar = 0;
            enemyTurn();
        }
    }

    private static void enemyTurn()
    {
        if (!enemyPartyDead())
        {
            for (Monster enemy: enemyParty)
            {
                MainPanel.updatePanel("-------------------------------------------------------------");
                MainPanel.updatePanel(enemy.getName().toUpperCase() + "'S TURN!!!\n");
                enemy.attack();
            }
        }
        partyTurn = true;
        startTurn();
    }

    public static void endBattle(boolean win)
    {
        inBattle = false;
        if (win)
        {
            int totalXP = 0;
            // ADD MORE CODE to tally up xp or something
            for (Monster monster : enemyParty)
            {
                totalXP += monster.getLvl() * 20; // maybe change scaling later
            }
            for (Person person : party)
            {
                person.addXp(totalXP);
            }
            MainPanel.updatePanel("Party gained " + totalXP + " XP!");
        }
        for (Monster monster: enemyParty)
        {
            if (!monster.isAlive())
            {
                Main.currentRoom.removeMonster(monster); // removes dead monsters from room
            }
        }
        MainPanel.clearPanel2();
        MainPanel.updateColors(); // reset colors
    }

    // toString
    public static void printStatus() // updates output area 2 with the status of party and enemies
    {
        String output = "";
        output += "YOUR PARTY:\n";
        for (Person person: getParty())
        {
            output += person.printBattle() + "\n";
        }
        output += "\n";
        output += "ENEMY PARTY:\n";
        for (Monster monster: getEnemyParty())
        {
            output += monster.printBattle() + "\n";
        }
        MainPanel.updatePanel2(output);
    }

    private static void startTurn()
    {
        printStatus();
        if (!partyDead())
        {
            if (enemyPartyDead())
            {
                endBattle(true); // we won!
            }
            else if (!ObjectFactory.player.isAlive()) // player is dead, battle ends
            {
                endBattle(false);
            }
            else
            {
                if (activeChar == party.size() && !party.get(activeChar-1).isAlive())
                {
                    activeChar = 0;
                    enemyTurn();
                }
                else
                {
                    if (!party.get(activeChar).isAlive()) // conditions where cannot act
                    { // ADD MORE CODE LATER
                        MainPanel.updatePanel(party.get(activeChar).getName() + " is knocked out!");
                        if (activeChar < party.size()) // so we dont get index out of bounds
                        {
                            activeChar++;
                            startTurn(); // recursive call!?
                        }
                    }
                    else
                    {
                        MainPanel.updatePanel("-------------------------------------------------------------");
                        MainPanel.updatePanel(party.get(activeChar).getName().toUpperCase() + "'S TURN!!!\n");
                    }
                }
            }
        }
    }
}
