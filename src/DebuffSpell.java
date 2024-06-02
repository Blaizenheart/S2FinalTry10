import java.util.Random;

public class DebuffSpell extends Spell
{
    // instance variables
    private Random rand = new Random();
    private String status; // the status the debuff spell applies
    private int duration;

    // constructor
    public DebuffSpell(String name, String desc, int mpCost, String status, boolean aoe, int duration)
    {
        super(name, desc, mpCost, aoe);
        this.status = status;
        this.duration = duration;
    }


    //brain methods
    public void cast(Entity caster, Entity target) // single target
    {
        int num = rand.nextInt(100);
        Status battleStatus = new Status(target, status, duration);
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
            Battle.addBattleStatus(battleStatus);
        }
        caster.subMp(mpCost);
    }

    public void cast(Entity caster, Entity[] targets) // single target
    {
        int num = rand.nextInt(100);
        for (Entity target: targets)
        {
            Status battleStatus = new Status(target, status, duration);
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
                Battle.addBattleStatus(battleStatus);
            }
        }
        caster.subMp(mpCost);
    }
}
