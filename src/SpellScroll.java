public class SpellScroll extends Item // teaches the player a spell
{
    // instance variables
    private Spell spell;

    // constructor
    public SpellScroll(String name, String desc, int value, String article, Spell spell)
    {
        super(name, desc, value, article);
        this.spell = spell;
    }

    // getters
    public Spell getSpell()
    {
        return spell;
    }

    // brain methods
    public void use()
    {
        boolean hasSpell = false;
        MainPanel.updatePanel("You open the " + name + " and try to decipher the text within it.");
        for (Spell spell : ObjectFactory.player.getSpells())
        {
            if (this.spell == spell)
            {
                hasSpell = true;
            }
        }
        if (hasSpell)
        {
            MainPanel.updatePanel("Unfortunately, you already have this spell. Which shouldn't be possible because there's" +
                    " only one spell scroll for each spell, but whatever...");
        }
        else
        {
            MainPanel.updatePanel("You learn " + spell.getName() + "!");
            if (Game.currentRoom.getItems().contains(this))
            {
                Game.currentRoom.removeItem(this);
            }
            if (ObjectFactory.player.getInv().contains(this))
            {
                ObjectFactory.player.removeInvItem(this);
            }
            ObjectFactory.player.addSpell(spell);
        }
    }
}
