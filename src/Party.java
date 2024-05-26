import java.util.ArrayList;

public class Party
{
    private static ArrayList<Person> party = new ArrayList<Person>();

    // getters
    public static int size()
    {
        return party.size();
    }

    public static ArrayList<Person> getParty()
    {
        return party;
    }

    // setters

    public static void addMember(Person member)
    {
        party.add(member);
        member.addXp(ObjectFactory.player.getXp());

    }

    public static void removeMember(Person member)
    {
        party.add(member);
    }

    // toString

    public static String print()
    {
        String output = "";
        output += "YOUR PARTY:\n";
        for (Person person: party)
        {
            output += person.getName() + "\n";
        }
        return output;
    }

}
