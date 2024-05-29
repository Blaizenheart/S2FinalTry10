import java.util.List;

public class Person extends Entity
{
    // instance variables
    private int approval;

    // constructor
    public Person(String name, String desc, boolean alive, int lvl, int xp, int maxHp, int currentHp, int atk, int def, int ev,
                  int maxMp, int currentMp, Weapon weapon, List<String> statusEffects,
                  List<String> weakTypes, List<String> resTypes, List<String> immTypes,
                  List<Item> inv, List<Spell> spells)
    {
        super(name, desc, alive, lvl, xp, maxHp, currentHp, atk, def, ev, maxMp, currentMp, weapon, statusEffects, weakTypes, resTypes, immTypes, inv, spells);
        approval = 0;
    }

    // getters
    public int getApproval()
    {
        return approval;
    }

    // setters
    public void approve()
    {
        MainPanel.updatePanel(name + " approves.");
        approval++;
    }

    public void disapprove()
    {
        MainPanel.updatePanel(name + " disapproves.");
        approval--;
    }

    public void addXp(int xp)
    {
        this.xp += xp;
        while (this.xp >= lvl * 50) //xp hit max or overflow
        {
            // level up
            xp = xp - lvl * 50; // reset xp based on overflow
            lvl++;
            MainPanel.updatePanel(name + " leveled up to Level " + lvl + "!");
            atk += 3;
            hpCapAdjustment(); // adjusts the hp cap
            mpCapAdjustment(); // adjusts the hp cap
        }
    }

    public void hpCapAdjustment()
    {
        maxHp += 5*lvl; // hp increases by scaled amount
        currentHp = maxHp; // full heal
    }

    public void mpCapAdjustment()
    {
        maxMp += 10; // mp increases by scaled amount
        currentMp = maxMp; // full mp restore
    }

    public String examine()
    {
        String output = super.examine();
        if (this != ObjectFactory.player)
        {
            if (approval < 0) // less than 0
            {
                output += "\n" + name + " is not too fond of you.";
            }
            else if (approval >= 0 && approval <= 1) // 0 - 1
            {
                output += "\n" + name + " is indifferent towards you.";
            }
            else if (approval >= 2 && approval <= 3) // 2-3
            {
                output += "\n" + name + " appreciates your company.";
            }
            else
            {
                output += "\n" + name + " is very fond of you.";
            }
        }
        return output;
    }
}
