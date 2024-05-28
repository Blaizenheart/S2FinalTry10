import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Entity // two child classes, Person and Monster
{
    // instance variables
    public Random rand = new Random();
    public String name = "";
    public String desc;
    public boolean alive;
    public int lvl, xp, maxHp, currentHp, atk, def, ev, maxMp, currentMp; // basic stats
    public Weapon weapon;
    public ArrayList<String> statusEffects = new ArrayList<String>(); // afflicted statuses
    public ArrayList<String> weakTypes = new ArrayList<String>(); // weakness types
    public ArrayList<String> resTypes = new ArrayList<String>(); // resistant types
    public ArrayList<String> immTypes = new ArrayList<String>(); // immune types
    public ArrayList<Item> inv = new ArrayList<Item>(); // inventory
    public ArrayList<Spell> spells = new ArrayList<Spell>(); // list of spells

    // very long constructor
    public Entity(String name, String desc, boolean alive, int lvl, int xp, int maxHp, int currentHp, int atk, int def, int ac,
                  int maxMp, int currentMp, Weapon weapon, List<String> statusEffects, List<String> weakTypes,
                  List<String> resTypes, List<String> immTypes, List<Item> inv, List<Spell> spells)
    {
        this.lvl = lvl;
        this.desc = desc;
        this.alive = alive;
        this.xp = xp;
        this.name = name;
        this.maxHp = maxHp;
        this.currentHp = currentHp;
        this.atk = atk;
        this.def = def;
        this.ev = ac;
        this.maxMp = maxMp;
        this.currentMp = currentMp;
        this.weapon = weapon;
        this.statusEffects = new ArrayList<>(statusEffects);
        this.weakTypes = new ArrayList<>(weakTypes);
        this.resTypes = new ArrayList<>(resTypes);
        this.immTypes = new ArrayList<>(immTypes);
        this.inv = new ArrayList<>(inv);
        this.spells = new ArrayList<>(spells);
    }

    public String getName()
    {
        return name;
    }

    public String getDesc()
    {
        return desc;
    }

    public boolean isAlive()
    {
        return alive;
    }

    public int getLvl()
    {
        return lvl;
    }

    public int getXp()
    {
        return xp;
    }

    public int getMaxHp()
    {
        return maxHp;
    }

    public int getCurrentHp()
    {
        return currentHp;
    }

    public int getAtk()
    {
        return atk;
    }

    public int getDef()
    {
        return def;
    }

    public int getEv()
    {
        return ev;
    }

    public int getMaxMp()
    {
        return maxMp;
    }

    public int getCurrentMp()
    {
        return currentMp;
    }

    public Weapon getWeapon()
    {
        return weapon;
    }

    public ArrayList<String> getStatusEffects()
    {
        return statusEffects;
    }

    public ArrayList<String> getWeakTypes()
    {
        return weakTypes;
    }

    public ArrayList<String> getResTypes()
    {
        return resTypes;
    }

    public ArrayList<String> getImmTypes()
    {
        return immTypes;
    }

    public ArrayList<Item> getInv()
    {
        return inv;
    }

    public ArrayList<Spell> getSpells()
    {
        return spells;
    }

    // setters

    public void addLvl(int lvl)
    {
        this.lvl += lvl;
    }

    public void kill()
    {
        alive = false;
    }

    public void revive()
    {
        alive = false;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setXp(int xp)
    {
        this.xp = xp;
    }

    public void setMaxHp(int maxHp)
    {
        this.maxHp = maxHp;
    }

    public void setCurrentHp(int currentHp)
    {
        this.currentHp = currentHp;
    }

    public void addHp(int hp)
    {
        if (!alive)
        {
            alive = true; // welcome back!
        }
        currentHp += hp;
        if (currentHp > maxHp)
        {
            currentHp = maxHp;
        }
    }

    public void subHp(int hp)
    {
        currentHp -= hp;
        if (currentHp <= 0)
        {
            currentHp = 0;
            kill();
        }
    }

    public void setAtk(int atk)
    {
        this.atk = atk;
    }

    public void setDef(int def)
    {
        this.def = def;
    }

    public void setEv(int ev)
    {
        this.ev = ev;
    }

    public void setMaxMp(int maxMp)
    {
        this.maxMp = maxMp;
    }

    public void setCurrentMp(int currentMp)
    {
        this.currentMp = currentMp;
    }

    public void addMp(int mp)
    {
        currentMp += mp;
        if (currentMp > maxMp)
        {
            currentMp = maxMp;
        }
    }

    public void subMp(int mp)
    {
        currentMp -= mp;
    }

    public void setWeapon(Weapon weapon)
    {
        this.weapon = weapon;
    }

    public void removeWeapon() // unarmed strikes?
    {
        this.weapon = null;
    }

    public void addStatusEffect(String statusEffect)
    {
        this.statusEffects.add(statusEffect);
    }

    public void removeStatusEffect(String statusEffect)
    {
        this.statusEffects.remove(statusEffect);
    }

    public void clearStatus()
    {
        statusEffects.clear();
    }

    public void addWeakTypes(String weakTypes)
    {
        this.weakTypes.add(weakTypes);
    }

    public void addResTypes(String resTypes)
    {
        this.resTypes.add(resTypes);
    }

    public void setImmTypes(String immTypes)
    {
        this.immTypes.add(immTypes);
    }

    public void addInvItem(Item item)
    {
        this.inv.add(item);
    }

    public void clearInv()
    {
        inv.clear();
    }

    public void removeInvItem(Item item)
    {
        this.inv.remove(item);
    }

    public void addSpell(Spell spell)
    {
        this.spells.add(spell);
    }

    public void removeSpell(Spell spell)
    {
        this.spells.remove(spell);
    }

    // brain methods
    public void attack(Entity target) // basic weapon attack!!
    {
        int num = rand.nextInt(100);
        if (weapon != null)
        {
            MainPanel.updatePanel(name + " attacks " + target.getName() + " with " + weapon.getArticle()  + weapon.getName() + "!");
        }
        else
        {
            MainPanel.updatePanel(name + " attacks " + target.getName() + "!");
        }
        // sees if the target evades the attack
        if (target.getEv() >= num)
        {
            // target evades attack
            MainPanel.updatePanel(target.getName() + " dodges out of the way!");
        }
        else
        {
            // algorithm to determine attack damage
            int baseDmg = atk + weapon.rollDmg(); // adds entity's base atk with the weapons atk
            // checks target's weak/imm/res types
            if (target.getImmTypes().contains(weapon.getDamageType())) // weapon damage type matches immunity
            {
                baseDmg = 0;
                MainPanel.updatePanel(target.getName() + " is immune to " + weapon.getDamageType() + "!");
            }
            else if (target.getResTypes().contains(weapon.getDamageType()))
            {
                if (this.getStatusEffects().contains("blessed"))
                {
                    baseDmg += ObjectFactory.d4.roll(1);
                }
                baseDmg /= 2; // divided by two
                MainPanel.updatePanel(target.getName() + " is resistant to " + weapon.getDamageType() + "!");
                baseDmg -= (int) Math.ceil(baseDmg - (baseDmg * (target.getDef() * 0.01)));
                if (this.getStatusEffects().contains("resistant"))
                {
                    baseDmg -= ObjectFactory.d4.roll(1);
                }
                if (baseDmg <= 0)
                {
                    baseDmg = 1;
                }
            }
            else if (target.getWeakTypes().contains(weapon.getDamageType()))
            {
                if (this.getStatusEffects().contains("blessed"))
                {
                    baseDmg += ObjectFactory.d4.roll(1);
                }
                baseDmg *= 2; // multiplied by two
                MainPanel.updatePanel(target.getName() + " is weak against " + weapon.getDamageType() + "!");
                baseDmg -= (int) Math.ceil(baseDmg - (baseDmg * (target.getDef() * 0.01)));
                if (this.getStatusEffects().contains("resistant"))
                {
                    baseDmg -= ObjectFactory.d4.roll(1);
                }
                if (baseDmg <= 0)
                {
                    baseDmg = 1;
                }

            }
            MainPanel.updatePanel(target.getName() + " takes " + baseDmg + " " + weapon.getDamageType() + " damage!");
            target.subHp(baseDmg);
        }
    }

    // toString
    public String printBattle()
    {
        String output = "";
        output += name.toUpperCase() + "\n"; // name
        output += "HP: " + currentHp + "/" + maxHp + "\n"; // hp
        output += "MP: " + currentMp + "/" + maxMp + "\n"; // mp
        for (String status: statusEffects)
        {
            output += " [" + status.toUpperCase() + "] "; // status effects
        }
        return output;
    }

    public String printSpells()
    {
        String output= "";
        if (spells.isEmpty())
        {
            output = "You don't have any spells!";
        }
        else
        {
            output = name.toUpperCase() + " SPELLS:\n";
            for (Spell spell: spells)
            {
                output += "<" + spell.getName() + "> ";
            }
        }
        return output;
    }

    public String printInv() // prints all the items in the inventory
    {
        String output= "";
        if (inv.isEmpty())
        {
            output = "You don't have anything!";
        }
        else
        {
            output = "INVENTORY:";
            for (Item item: inv)
            {
                output += "\n  " + item.getName();
            }
        }
        return output;
    }

    public String examine()
    {
        String output = "";
        output += name.toUpperCase() + " - "; // name
        output += desc + ".\n";
        output += "LVL: " + lvl + "\n";
        output += "XP: " + xp + "/" + lvl*50 + "\n";
        output += "HP: " + currentHp + "/" + maxHp + "\n"; // hp
        output += "MP: " + currentMp + "/" + maxMp + "\n"; // mp
        if (this == ObjectFactory.player)
        {
            output += "GOLD: " + Game.getGold() + "\n";
        }
        output += "WEAPON: " + weapon.getName() + ", " + weapon.getDamageType() + " damage\n";
        output += "STATUS(ES): ";
        if (statusEffects.isEmpty())
        {
            output += "NONE\n";
        }
        else
        {
            for (String status: statusEffects)
            {
                output += "\t" + status + " ";
            }
            output += "\n";
        }
        output += "SPELLS:";
        if (spells.isEmpty())
        {
            output += " NONE";
        }
        else
        {
            output += "\n";
            for (Spell spell: spells)
            {
                output += "<" + spell.getName() + "> ";
            }
        }
        return output;
    }
}