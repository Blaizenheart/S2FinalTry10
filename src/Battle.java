import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Battle
{
    // instance variables
    private static Random rand = new Random();
    private static ArrayList<Person> party;
    private static ArrayList<Monster> enemyParty;
    private static int activeChar; // 0 1 2 3
    private static int turn; // counts turns
    private static boolean inBattle;
    private static boolean partyTurn; // if it's the party's turn to act
    private static ArrayList<Status> statuses = new ArrayList<>(); // keeps track of all the status effects on the entities in the battle

    // constructor
    public Battle(ArrayList<Person> party, ArrayList<Monster> enemyParty, boolean ambushed)
    {
        Battle.party = party;
        Battle.enemyParty = enemyParty;
        statuses = new ArrayList<>();
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

    public static int getTurn() // returns the number of turns
    {
        return turn;
    }

    // setters
    public static void setInBattle(boolean state)
    {
        inBattle = state;
    }

    public static void addBattleStatus(Status status)
    {
        statuses.add(status);
    }

    // brain methods

    public static void statusUpdate() // checks statuses to remove any if they're ended already
    {
        for (int i = 0; i < statuses.size(); i++)
        {
            if (statuses.get(i).getEndingTurn() <= turn) // status expired
            {
                statuses.get(i).getTarget().removeStatusEffect(statuses.get(i).getStatus()); // removes the status
                statuses.remove(i);
            }
        }
        checkStatus();
        Battle.printStatus(); // updates status
    }

    public static void checkStatus() // checks the status of each ally/enemy at the beginning of the party/enemy turn
    {
        Entity target;
        String status;
        for (int i = 0; i < statuses.size(); i++)
        {
            target = statuses.get(i).getTarget();
            status = statuses.get(i).getStatus();
            // poisoned, charmed, bleeding ?
            if (status.equals("poisoned"))
            {
                MainPanel.updatePanel(target.getName() + " takes " + rand.nextInt(5) + " poison damage!");
            }
            else if (status.equals("bleeding"))
            {
                MainPanel.updatePanel(target.getName() + " takes " + rand.nextInt(5) + " damage from bleeding!");
            }
        }
    }

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
                        if (spell.getAoe()) // affects all
                        {
                            if (party.get(activeChar).getCurrentMp() >= spell.getMpCost())
                            {
                                for (Person person: party)
                                {
                                    target = person;
                                    spell.cast(party.get(activeChar), target);
                                    casted = true;
                                }
                            }
                            else
                            {
                                MainPanel.updatePanel("Not enough MP!");
                            }
                        }
                        else
                        {
                            // searches for target within party
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
                        }
                        if (target == null)
                        {
                            MainPanel.updatePanel("Please specify a target.");
                        }
                    }
                    else if (spell instanceof DebuffSpell)
                    {
                        if (spell.getAoe()) // affects all
                        {
                            if (party.get(activeChar).getCurrentMp() >= spell.getMpCost())
                            {
                                spell.cast(party.get(activeChar), (Entity) List.of(enemyParty));
                                casted = true;
                            }
                            else
                            {
                                MainPanel.updatePanel("Not enough MP!");
                            }
                        }
                        else
                        {
                            for (Monster monster: enemyParty)
                            {
                                if (input.contains(monster.getName().toLowerCase()))
                                {
                                    target = monster;
                                    if (party.get(activeChar).getCurrentMp() >= spell.getMpCost())
                                    {
                                        spell.cast(party.get(activeChar), target);
                                        casted = true;
                                    }
                                    else
                                    {
                                        MainPanel.updatePanel("Not enough MP!");
                                    }
                                    break;
                                }
                            }
                        }
                        if (target == null)
                        {
                            MainPanel.updatePanel("Specify who you want to cast it on!");
                        }
                    }
                    if (spell instanceof BuffSpell)
                    {
                        if (spell.getAoe())
                        {
                            if (party.get(activeChar).getCurrentMp() >= spell.getMpCost())
                            {
                                spell.cast(party.get(activeChar), (Entity) List.of(party));
                                casted = true;
                            }
                            else
                            {
                                MainPanel.updatePanel("Not enough MP!");
                            }
                        }
                        else
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
                                    break;
                                }
                            }
                        }
                        if (target == null)
                        {
                            MainPanel.updatePanel("Specify who you want to cast it on!");
                        }
                    }
                    /*
                    if (spell instanceof AttackSpell)
                    {

                    }
                     */
                    if (casted) // only increments if spell was sucessfully cast
                    {
                        activeChar++;
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
            }
            else if (input.contains("run") || input.contains("flee"))
            {
                activeChar++;
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
            turn++;
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
        statusUpdate();
        Battle.printStatus(); // updates status
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
            for (Person person: party)
            {
                person.clearStatus(); // fixes all statuses
            }
        }
        for (Monster monster: enemyParty)
        {
            if (!monster.isAlive())
            {
                Main.currentRoom.removeMonster(monster); // removes dead monsters from room
            }
            //ADD MORE CODE HERE FOR CORPSES
        }
        if (!win)
        {
            Dialogue.setInDialogue(true);
            Dialogue.getDialogue(Main.currentRoom, ObjectFactory.dain);
        }
        MainPanel.clearPanel2();
        MainPanel.updateColors(); // reset colors
    }

    // toString
    public static void printStatus() // updates output area 2 with the status of party and enemies
    {
        String output = "";
        output += "YOUR PARTY-------------------------\n";
        for (Person person: getParty())
        {
            output += person.printBattle() + "\n";
        }
        output += "\n";
        output += "ENEMY PARTY------------------------\n";
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
