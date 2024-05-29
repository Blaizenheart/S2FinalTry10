import java.util.ArrayList;
public class Container extends Item
{
    private boolean locked; // whether the container is locked
    private Item key; // the item used to unlock it if it is locked
    private boolean opened; // whether the container has been opened
    private ArrayList<Item> items = new ArrayList<Item>();

    // constructor
    public Container(String name, String desc, int value, String article, boolean locked, boolean opened, Item key, ArrayList<Item> items)
    {
        super(name, desc, value, article);
        this.locked = locked;
        this.opened = opened;
        this.key = key;
        this.items = items;
    }

    // getters
    public boolean isLocked()
    {
        return locked;
    }

    public boolean isOpened()
    {
        return opened;
    }

    public Item getKeyItem()
    {
        return key;
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }

    // setters
    public void setLocked(boolean locked)
    {
        this.locked = locked;
    }

    public void setOpened(boolean opened)
    {
        this.opened = opened;
    }

    // brain methods
    public void tryUnlock()
    {
        boolean success = false;
        // sees if player has the key item
        for (Item item: ObjectFactory.player.getInv())
        {
            if (item == key)
            {
                success = true;
            }
        }
        if (success)
        {
            MainPanel.updatePanel("You unlocked the " + name + " with the " + key.getName() + "!");
            ObjectFactory.player.removeInvItem(key);
            locked = false;
        }
    }

    public void open()
    {
        if (locked)
        {
            MainPanel.updatePanel("The " + name + " is locked!");
        }
        else
        {
            if (opened)
            {
                MainPanel.updatePanel("You've already opened the " + name + ".");
            }
            else
            {
                MainPanel.updatePanel("You open the " + name + "!");
                MainPanel.updatePanel("You found:");
                for (Item item: items)
                {
                    MainPanel.updatePanel("- " + item.getName());
                    ObjectFactory.player.addInvItem(item);
                }
                items.clear();
            }
        }
    }
}
