public class Weapon extends Item
{
    // instance variables
    private Dice dice; // used for attack rolls
    private int diceRolls; // amount of time to roll dice
    private String damageType; // type of damage (piercing/blunt/slashing)

    // constructor
    public Weapon (String name, String desc, int value, String article, Dice dice, int diceRolls, String damageType)
    {
        super(name, desc, value, article);
        this.dice = dice;
        this.diceRolls = diceRolls;
        this.damageType = damageType;
    }

    //getters
    public String getDamageType()
    {
        return damageType;
    }

    // brain method
    public int rollDmg()
    {
        return dice.roll(diceRolls);
    }

    // overriden toString method
    public String toString()
    {
        String output = name.toUpperCase() + " - " + value + " - " + dice.getName() + " " + getDamageType() + " damage" + " - " + desc;
        return output;
    }
}
