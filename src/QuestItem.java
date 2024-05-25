public class QuestItem extends Item // special type of item that can only be used in the correct situations
{
    // constructor
    public QuestItem(String name, String desc, int value, String article)
    {
        super(name, desc, value, article);
    }

    // brain method
    public void use()
    {
        // custom code for each item
    }
}
