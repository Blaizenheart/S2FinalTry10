public class Status // keeps track of status effects
{
    private Entity target;
    private String status = "";
    private int endingTurn = 1; // turn # that the status will be removed upon

    // constructor
    public Status (Entity target, String status, int duration)
    {
        this.target = target;
        this.status = status;
        this.endingTurn = Battle.getTurn() + duration;
    }

    public Entity getTarget()
    {
        return target;
    }

    public String getStatus()
    {
        return status;
    }

    public int getEndingTurn()
    {
        return endingTurn;
    }

}
