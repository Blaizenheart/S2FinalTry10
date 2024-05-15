import java.util.Random;

public class DebuffSpell extends Spell
{
    // instance variables
    private Random rand = new Random();
    private Dice dice; // dice to roll for how many rounds it lasts
    private String status; // the status the debuff spell applies

    // constructor
    public DebuffSpell(String name, String desc, Dice dice, int mpCost, String status, boolean aoe)
    {
        super(name, desc, mpCost, aoe);
        this.status = status;
        this.dice = dice;
    }

    //getters
    public String getStatus()
    {
        return status;
    }

    public Dice getDice()
    {
        return dice;
    }

    //brain methods
    public void cast(Entity caster, Entity target) // single target
    {
        int num = rand.nextInt(100);
        super.cast(caster, target);
        if (target.getEv() >= num)
        {
            // target evades attack
            MainPanel.updatePanel(target.getName() + " dodges out of the way!");
        }
        else
        {
            MainPanel.updatePanel(target.getName() + " is " + status + "ed!");
            target.addStatusEffect(status);
        }
        caster.subHp(mpCost);
    }
    public void cast(Entity caster, Entity[] targets) // multiple targets
    {
        int num = rand.nextInt(100);
        super.cast(caster, targets);
        for (Entity target: targets)
        {
            if (target.getEv() >= num)
            {
                // target evades attack
                MainPanel.updatePanel(target.getName() + " dodges out of the way!");
            }
            else
            {
                MainPanel.updatePanel(target.getName() + " is " + status + "ed!");
                target.addStatusEffect(status);
            }
        }
        caster.subHp(mpCost);
    }
}
