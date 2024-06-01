public class Consumable extends Item
{ // for items you can CONSUME!!

    // instance variables
    // different things consumables can do: restore hp, restore mp, remove status effect
    boolean restoreHp, restoreMp, removeStatus;
    int hp;
    int mp;
    String status = "";
    String verb = ""; // what verb is used to describe using the consumable
    public Consumable(String name, String desc, int value, String article, boolean restoreHp, int hp,
                      boolean restoreMp, int mp, boolean removeStatus, String status, String verb)
    {
        super(name, desc, value, article);
        this.restoreHp = restoreHp;
        this.hp = hp;
        this.restoreMp = restoreMp;
        this.mp = mp;
        this.removeStatus = removeStatus;
        this.status = status;
        this.verb = verb;
    }

    // brain methods
    public void use() // player
    {
        MainPanel.updatePanel("You " + verb + " the " + name + ".");
        if (restoreHp)
        {
            ObjectFactory.player.addHp(hp);
            MainPanel.updatePanel("Restored " + hp + " hp!");
        }
        if (restoreMp)
        {
            ObjectFactory.player.addHp(mp);
            MainPanel.updatePanel("Restored " + mp + " mp!");
        }
        if (removeStatus)
        {
            ObjectFactory.player.removeStatusEffect(status);
            MainPanel.updatePanel("Removed condition: " + status + ".");
        }
    }

    public void use(Entity target) // used on a party member
    {
        MainPanel.updatePanel(target.getName() + " " + verb + "s the " + name + ".");
        if (restoreHp)
        {
            target.addHp(hp);
            MainPanel.updatePanel(target.getName() + " restored " + hp + " hp!");
        }
        if (restoreMp)
        {
            target.addHp(mp);
            MainPanel.updatePanel(target.getName() + " restored " + mp + " mp!");
        }
        if (removeStatus)
        {
            target.removeStatusEffect(status);
            MainPanel.updatePanel("Removed condition on " + target.getName() + ": " + status + ".");
        }
    }
}
