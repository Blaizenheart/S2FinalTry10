import java.util.Random;

public class Dice
{
    private static Random rand = new Random();
    // instance variables
    private String name = "";
    private int sides; // number of sides on the dice

    // everything comes back to the dice...
    // constructor
    public Dice(String name, int sides)
    {
        this.name = name;
        this.sides = sides;
    }

    //getters
    public String getName()
    {
        return name;
    }

    // brain methods
    public int roll(int rolls) // r-roll the dice
    {
        int sum = 0;
        for (int i = 0; i < rolls; i ++)
        {
            sum += rand.nextInt(sides) + 1;
        }
        return sum;
    }
}
