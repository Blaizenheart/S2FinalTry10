public class Spell // several child classes - attackSpell, buffSpell, healingSpell, debuffSpell
{
    // instance variables
    public String name = "";
    public String desc = "";
    public int mpCost;
    public boolean aoe;

    // constructor
    public Spell(String name, String desc, int mpCost, boolean aoe)
    {
        this.name = name;
        this.desc = desc;
        this.mpCost = mpCost;
        this.aoe = aoe;
    }

    //getters
    public String getName()
    {
        return name;
    }

    public String getDesc()
    {
        return desc;
    }

    public int getMpCost()
    {
        return mpCost;
    }

    public boolean getAoe()
    {
        return aoe;
    }

    //brain methods
    public void cast(Entity caster) // self
    {
        MainPanel.updatePanel(caster.getName() + " casts " + name + " on themselves!");
    }
    public void cast(Entity caster, Entity target) // single target
    {
        MainPanel.updatePanel(caster.getName() + " casts " + name + " on " + target.getName() + "!");
    }
    public void cast(Entity caster, Entity[] targets) // im too lazy to make code to have this format grammatically
    {
        MainPanel.updatePanel(caster.getName() + " casts " + name + "!");
    }
}