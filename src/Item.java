public class Item // multiple child classes, weapon, consumable, quest item
{
    // instance variables
    public String name;
    public String article; // grammar amirite
    public String desc;
    public int value;

    // constructor
    public Item(String name, String desc, int value, String article)
    {
        this.name = name;
        this.desc = desc;
        this.value = value;
        this.article = article;
    }

    //getters
    public String getName()
    {
        return name;
    }

    public String getArticle()
    {
        return article;
    }

    public String getDesc()
    {
        return desc;
    }

    public int getValue()
    {
        return value;
    }

    // toString
    public String toString()
    {
        String output = name.toUpperCase() + " - " + value + " - " + desc;
        return output;
    }
}
