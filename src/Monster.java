import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Monster extends Entity
{
    //instance variables
    // constructor
    public Monster(String name, String desc, boolean alive, int lvl, int xp, int maxHp, int currentHp, int atk, int def, int ev, int maxMp,
                   int currentMp, Weapon weapon, List<String> statusEffects, List<String> weakTypes,
                   List<String> resTypes, List<String> immTypes, List<Item> inv, List<Spell> spells)
    {
        super(name, desc, alive, lvl, xp, maxHp, currentHp, atk, def, ev, maxMp, currentMp, weapon, statusEffects, weakTypes, resTypes, immTypes, inv, spells);
    }
    //getters
    //setters
    //brain methods
    public void attack()
    {
        Random rand = new Random();
        int target = rand.nextInt(Battle.getParty().size()); // get party array to attack random party member
        super.attack(Battle.getParty().get(target)); // super call to entity class's attack method
    }
}
