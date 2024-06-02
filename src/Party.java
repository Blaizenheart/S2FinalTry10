import java.util.ArrayList;

public class Party
{
    private static ArrayList<Person> party = new ArrayList<Person>();

    // getter
    public static ArrayList<Person> getParty()
    {
        return party;
    }

    // setter
    public static void addMember(Person member)
    {
        party.add(member);
        member.addXp(ObjectFactory.player.getXp());

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
