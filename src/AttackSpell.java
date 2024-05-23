import java.util.Random;

public class AttackSpell extends Spell
{
    // instance variables
    private Random rand = new Random();
    private String dmgType;
    private Dice dice;
    private int diceRolls;

    // constructor
    public AttackSpell(String name, String desc, int mpCost, boolean aoe, String dmgType, Dice dice, int diceRolls)
    {
        super(name, desc, mpCost, aoe);
        this.dmgType = dmgType;
        this.dice = dice;
        this.diceRolls = diceRolls;
    }

    //getters
    public String getDmgType()
    {
        return dmgType;
    }

    public Dice getDice()
    {
        return dice;
    }

    public int getDiceRolls()
    {
        return diceRolls;
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
            // algorithm to determine attack damage
            int baseDmg = dice.roll(diceRolls) + caster.getLvl()*2; // adds entity's base atk with the weapons atk
            // checks target's weak/imm/res types
            if (target.getImmTypes().contains(dmgType)) // spell damage type matches immunity
            {
                baseDmg = 0;
                MainPanel.updatePanel(target.getName() + " is immune to " + dmgType + "!");
            }
            else if (target.getResTypes().contains(dmgType))
            {
                if (caster.getStatusEffects().contains("blessed"))
                {
                    baseDmg += ObjectFactory.d4.roll(1);
                }
                baseDmg /= 2; // divided by two
                MainPanel.updatePanel(target.getName() + " is resistant to " + dmgType + "!");
                baseDmg -= (int) Math.ceil(baseDmg - (baseDmg * (target.getDef() * 0.01)));
                if (target.getStatusEffects().contains("resistant"))
                {
                    baseDmg -= ObjectFactory.d4.roll(1);
                }
                if (baseDmg <= 0)
                {
                    baseDmg = 1;
                }
            }
            else if (target.getStatusEffects().contains(dmgType))
            {
                if (caster.getStatusEffects().contains("blessed"))
                {
                    baseDmg += ObjectFactory.d4.roll(1);
                }
                baseDmg *= 2; // multiplied by two
                MainPanel.updatePanel(target.getName() + " is weak against " + dmgType + "!");
                baseDmg -= (int) Math.ceil(baseDmg - (baseDmg * (target.getDef() * 0.01)));
                if (target.getStatusEffects().contains("resistant"))
                {
                    baseDmg -= ObjectFactory.d4.roll(1);
                }
                if (baseDmg <= 0)
                {
                    baseDmg = 1;
                }

            }
            MainPanel.updatePanel(target.getName() + " takes " + baseDmg + " " + dmgType + " damage!");
            target.subHp(baseDmg);
        }
        caster.subMp(mpCost);
    }

    public void cast(Entity caster, Entity[] targets) // single target
    {
        for (Entity target: targets)
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
                // algorithm to determine attack damage
                int baseDmg = dice.roll(diceRolls) + caster.getLvl()*2; // adds entity's base atk with the weapons atk
                // checks target's weak/imm/res types
                if (target.getImmTypes().contains(dmgType)) // spell damage type matches immunity
                {
                    baseDmg = 0;
                    MainPanel.updatePanel(target.getName() + " is immune to " + dmgType + "!");
                }
                else if (target.getResTypes().contains(dmgType))
                {
                    if (caster.getStatusEffects().contains("blessed"))
                    {
                        baseDmg += ObjectFactory.d4.roll(1);
                    }
                    baseDmg /= 2; // divided by two
                    MainPanel.updatePanel(target.getName() + " is resistant to " + dmgType + "!");
                    baseDmg -= (int) Math.ceil(baseDmg - (baseDmg * (target.getDef() * 0.01)));
                    if (target.getStatusEffects().contains("resistant"))
                    {
                        baseDmg -= ObjectFactory.d4.roll(1);
                    }
                    if (baseDmg <= 0)
                    {
                        baseDmg = 1;
                    }
                }
                else if (target.getStatusEffects().contains(dmgType))
                {
                    if (caster.getStatusEffects().contains("blessed"))
                    {
                        baseDmg += ObjectFactory.d4.roll(1);
                    }
                    baseDmg *= 2; // multiplied by two
                    MainPanel.updatePanel(target.getName() + " is weak against " + dmgType + "!");
                    baseDmg -= (int) Math.ceil(baseDmg - (baseDmg * (target.getDef() * 0.01)));
                    if (target.getStatusEffects().contains("resistant"))
                    {
                        baseDmg -= ObjectFactory.d4.roll(1);
                    }
                    if (baseDmg <= 0)
                    {
                        baseDmg = 1;
                    }

                }
                MainPanel.updatePanel(target.getName() + " takes " + baseDmg + " " + dmgType + " damage!");
                target.subHp(baseDmg);
            }
        }
        caster.subMp(mpCost);
    }
}
