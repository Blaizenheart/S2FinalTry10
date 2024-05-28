import java.util.ArrayList;

public class Room
{
    // gonna skip out on naming these rooms because i don't think i can come up with that many room names
    // i know, budget cuts

    // instance variables
    private String roomName = "";
    private String roomDesc = ""; // description of the room
    private ArrayList<String> exits = new ArrayList<String>(); // list of exits
    private ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<Monster> monsters = new ArrayList<Monster>();
    private ArrayList<Person> people = new ArrayList<Person>();

    // constructor
    public Room(String roomName, String roomDesc, ArrayList<String> exits, ArrayList<Item> items, ArrayList<Monster> monsters, ArrayList<Person> people)
    {
        this.roomName = roomName;
        this.roomDesc = roomDesc;
        this.exits = exits;
        this.items = items;
        this.monsters = monsters;
        this.people = people;
    }

    //getters
    public String getRoomName()
    {
        return roomName;
    }
    public String getRoomDesc()
    {
        return roomDesc;
    }
    public ArrayList<String> getExits()
    {
        return exits;
    }

    public ArrayList<Monster> getMonsters()
    {
        return monsters;
    }

    public ArrayList<Person> getPeople()
    {
        return people;
    }

    public Item getItem(String itemName)
    {
        int num = -1; // not found yet
        for (int i = 0; i < items.size(); i++) // iterates through and tries to match item
        {
            if (itemName.equals(items.get(i).getName()))
            {
                num = i;
            }
        }
        return items.get(num); //get the index
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }

    // setters

    public void removeExit(String exit)
    {
        exits.remove(exit);
    }

    public void addExit(String exit)
    {
        exits.add(exit);
    }

    public void removeMonster(Monster monster)
    {
        monsters.remove(monster);
    }

    public void addMonster(Monster monster)
    {
        monsters.remove(monster);
    }

    public void removePerson(Person person)
    {
        people.remove(person);
    }

    public void addPerson(Person person)
    {
        people.remove(person);
    }

    public void removeItem(Item item)
    {
        items.remove(item);
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    //to string
    public String toString()
    {
        String output = roomDesc;
        if (!items.isEmpty())
        {
            output += " There is " + items.get(0).getArticle() + " " + items.get(0).getName();
            if (items.size() == 2) // two items, ___ and ___
            {
                output += " and " + items.get(0).getArticle() + " " + items.get(0).getName();
            }
            else if (items.size() > 2)
            {
                for (int i = 1; i < items.size() - 2; i++)
                {
                    output += ", " + items.get(i).getArticle() + " " + items.get(i).getName();
                }
                output += ", and " + items.get(items.size()-1).getArticle() + " " + items.get(items.size()-1).getName();
            }

            output += ".";
        }
        if (!monsters.isEmpty())
        {
            output += " There is a " + monsters.get(0).getName();
            if (monsters.size() == 2) // two items, ___ and ___
            {
                output += " and a " + monsters.get(1).getName();
            }
            else if (monsters.size() > 2)
            {
                for (int i = 1; i < monsters.size() - 2; i++)
                {
                    output += ", a " + monsters.get(i).getName();
                }
                output += ", and a " + monsters.get(monsters.size()-1).getName();
            }

            output += ".";
        }
        if (!people.isEmpty())
        {
            output += " There is";
            if (Dialogue.knowDain && people.get(0).getName().equals("Dain"))
            {
                output += " Dain";
            }
            else if (Dialogue.knowSylvie && people.get(0).getName().equals("Sylvie"))
            {
                output += " Sylvie";
            }
            else if (Dialogue.knowSaltine && people.get(0).getName().equals("Saltine"))
            {
                output += " Saltine";
            }
            else if (Dialogue.knowEverest && people.get(0).getName().equals("Everest"))
            {
                output += " Everest";
            }
            else if (Dialogue.knowHenry && people.get(0).getName().equals("Henry"))
            {
                output += " Henry";
            }
            else if (Dialogue.knowTodd && people.get(0).getName().equals("Todd"))
            {
                output += " Todd";
            }
            else
            {
                output += " " + people.get(0).getDesc();
            }
            if (people.size() == 2)
            {
                output += " and";
                if (Dialogue.knowDain && people.get(1).getName().equals("Dain"))
                {
                    output += " Dain";
                }
                else if (Dialogue.knowSylvie && people.get(1).getName().equals("Sylvie"))
                {
                    output += " Sylvie";
                }
                else if (Dialogue.knowSaltine && people.get(1).getName().equals("Saltine"))
                {
                    output += " Saltine";
                }
                else if (Dialogue.knowEverest && people.get(1).getName().equals("Everest"))
                {
                    output += " Everest";
                }
                else if (Dialogue.knowHenry && people.get(1).getName().equals("Henry"))
                {
                    output += " Henry";
                }
                else if (Dialogue.knowTodd && people.get(1).getName().equals("Todd"))
                {
                    output += " Todd";
                }
                else
                {
                    output += " " + people.get(1).getDesc();
                }
            }
            else if (people.size() > 2)
            {
                for (int i = 1; i < people.size() - 2; i++)
                {
                    output += ",";
                    if (Dialogue.knowDain && people.get(i).getName().equals("Dain"))
                    {
                        output += " Dain";
                    }
                    else if (Dialogue.knowSylvie && people.get(i).getName().equals("Sylvie"))
                    {
                        output += " Sylvie";
                    }
                    else if (Dialogue.knowSaltine && people.get(i).getName().equals("Saltine"))
                    {
                        output += " Saltine";
                    }
                    else if (Dialogue.knowEverest && people.get(i).getName().equals("Everest"))
                    {
                        output += " Everest";
                    }
                    else if (Dialogue.knowHenry && people.get(i).getName().equals("Henry"))
                    {
                        output += " Henry";
                    }
                    else if (Dialogue.knowTodd && people.get(i).getName().equals("Todd"))
                    {
                        output += " Todd";
                    }
                    else
                    {
                        output += " " + people.get(0).getDesc();
                    }
                }
                output += ", and";
                if (Dialogue.knowDain && people.get(people.size()-1).getName().equals("Dain"))
                {
                    output += " Dain";
                }
                else if (Dialogue.knowSylvie && people.get(people.size()-1).getName().equals("Sylvie"))
                {
                    output += " Sylvie";
                }
                else if (Dialogue.knowSaltine && people.get(people.size()-1).getName().equals("Saltine"))
                {
                    output += " Saltine";
                }
                else if (Dialogue.knowEverest && people.get(people.size()-1).getName().equals("Everest"))
                {
                    output += " Everest";
                }
                else if (Dialogue.knowHenry && people.get(people.size()-1).getName().equals("Henry"))
                {
                    output += " Henry";
                }
                else if (Dialogue.knowTodd && people.get(people.size()-1).getName().equals("Todd"))
                {
                    output += " Todd";
                }
                else
                {
                    output += " " + people.get(0).getDesc();
                }
            }
            output += ".";
        }
        output += "\nEXITS: ";
        for (String exit: exits)
        {
            output += exit + " ";
        }
        // checks dialogue flags to hint to player if they need to talk to anyone
        if (!Dialogue.flags[14] && Game.currentRoom == ObjectFactory.roomV &&
                Party.getParty().contains(ObjectFactory.dain)) // DAIN DIALOGUE ABOUT ROOM V
        {
            output += "\n[!] It seems like Dain has something to say.";
        }
        if (!Dialogue.flags[5]&& Game.currentRoom == ObjectFactory.roomF &&
                Party.getParty().contains(ObjectFactory.everest))
        {
            output += "\n[!] It seems like Everest has something to say.";
        }
        if (!Dialogue.flags[16]&& Game.currentRoom == ObjectFactory.roomI &&
                Party.getParty().contains(ObjectFactory.sylvie))
        {
            output += "\n[!] It seems like Sylvie has something to say.";
        }
        if (!Dialogue.flags[17]&& Game.currentRoom == ObjectFactory.roomJ &&
                Party.getParty().contains(ObjectFactory.henry))
        {
            output += "\n[!] It seems like Henry has something to say.";
        }
        return output;
    }
}
