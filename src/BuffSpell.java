
public class BuffSpell extends Spell
{
    // instance variables
    private String status; // the status the buff spell applies
    private int duration;

    // constructor
    public BuffSpell(String name, String desc, int mpCost, String status, boolean aoe, int duration)
    {
        super(name, desc, mpCost, aoe);
        this.status = status;
        this.duration = duration;
    }

    //brain methods
    public void cast(Entity caster, Entity target) // single target
    {
        Status battleStatus = new Status(target, status, duration);
        super.cast(caster, target);
        MainPanel.updatePanel(target.getName() + " is " + status + "!");
        target.addStatusEffect(status);
        Battle.addBattleStatus(battleStatus);
        caster.subMp(mpCost);
    }

    public void cast(Entity caster, Entity[] targets) // single target
    {
        for (Entity target: targets)
        {
            Status battleStatus = new Status(target, status, duration);
            super.cast(caster, target);
            MainPanel.updatePanel(target.getName() + " is " + status + "!");
            target.addStatusEffect(status);
            Battle.addBattleStatus(battleStatus);
        }
        caster.subMp(mpCost);
    }
}
