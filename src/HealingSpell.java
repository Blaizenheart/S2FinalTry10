public class HealingSpell extends Spell
{
    // instance variables
    private Dice dice; // dice to roll for healing
    private int diceRolls; // amount of times to roll said dice

    // constructor
    public HealingSpell(String name, String desc, int mpCost, Dice dice, int diceRolls, boolean aoe)
    {
        super(name, desc, mpCost, aoe);
        this.dice = dice;
        this.diceRolls = diceRolls;
    }

    //brain methods
    public void cast(Entity caster) // self
    {
        super.cast(caster);
        int healAmt = dice.roll(diceRolls); // rolls the dice
        MainPanel.updatePanel(caster.getName() + " regains " + healAmt + " hp!");
        caster.addHp(healAmt);
        caster.subMp(mpCost);
    }
    public void cast(Entity caster, Entity target) // single target
    {
        super.cast(caster, target);
        int healAmt = dice.roll(diceRolls); // rolls the dice
        MainPanel.updatePanel(target.getName() + " regains " + healAmt + " hp!");
        target.addHp(healAmt);
        caster.subMp(mpCost);
    }
    public void cast(Entity caster, Entity[] targets) // multiple targets
    {
        super.cast(caster, targets);
        int healAmt = dice.roll(diceRolls); // rolls the dice
        for (Entity target: targets)
        {
            MainPanel.updatePanel(target.getName() + " regains " + healAmt + " hp!");
            target.addHp(healAmt);
        }
        caster.subMp(mpCost);
    }
}
