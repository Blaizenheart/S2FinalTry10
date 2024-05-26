import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monster extends Entity
{
    // instance variables
    String attackStatus = null; // status that an attack can inflict
    int statusChance; // chance that the status is applied (1-100)
    int statusDuration;

    // constructor
    public Monster(String name, String desc, boolean alive, int lvl, int xp, int maxHp, int currentHp, int atk, int def, int ev, int maxMp,
                   int currentMp, Weapon weapon, List<String> statusEffects, List<String> weakTypes,
                   List<String> resTypes, List<String> immTypes, List<Item> inv, List<Spell> spells, String attackStatus, int statusChance, int statusDuration)
    {
        super(name, desc, alive, lvl, xp, maxHp, currentHp, atk, def, ev, maxMp, currentMp, weapon, statusEffects, weakTypes, resTypes, immTypes, inv, spells);
        this.attackStatus = attackStatus;
        this.statusChance = statusChance;
        this.statusDuration = statusDuration;
    }
    // getters

    public String getAttackStatus()
    {
        return attackStatus;
    }

    public int getStatusChance()
    {
        return statusChance;
    }

    public int getStatusDuration()
    {
        return statusDuration;
    }

    // brain methods
    public void attack()
    {
        Random rand = new Random();
        int target = rand.nextInt(Battle.getParty().size()); // get party array to attack random party member
        while (!Battle.getParty().get(target).isAlive())
        {
            target = rand.nextInt(Battle.getParty().size()); // if target is already dead, find different target
        }
        super.attack(Battle.getParty().get(target)); // super call to entity class's attack method
        // sees if status hits
        int chance = rand.nextInt(101);
        if (statusChance > chance)
        {
            if (attackStatus != null)
            {
                Status status = new Status(Battle.getParty().get(target), attackStatus, rand.nextInt(3));
                Battle.addBattleStatus(status);
                MainPanel.updatePanel(Battle.getParty().get(target).getName() + " is " + attackStatus + "!");
            }
        }
    }
}
