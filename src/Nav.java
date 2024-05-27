public class Nav//igation
{
    // handles moving from room to room
    public static void move(Room currentRoom, String direction)
    {
        if (direction.equals("North"))
        {
            switch(currentRoom.getRoomName())
            {
                case "hall1":
                    Game.currentRoom = ObjectFactory.roomA;
                    break;
                case "b":
                    Game.currentRoom = ObjectFactory.hall1;
                    break;
                case "e":
                    Game.currentRoom = ObjectFactory.hall2;
                    break;
                case "hall2":
                    Game.currentRoom = ObjectFactory.roomG;
                    break;
                case "h":
                    Game.currentRoom = ObjectFactory.hall4;
                    break;
                case "hall3":
                    Game.currentRoom = ObjectFactory.roomH;
                    break;
                case "i":
                    Game.currentRoom = ObjectFactory.hall3;
                    break;
                case "k":
                    Game.currentRoom = ObjectFactory.roomJ;
                    break;
                case "l":
                    Game.currentRoom = ObjectFactory.roomK;
                    break;
                case "m":
                    Game.currentRoom = ObjectFactory.roomN;
                    break;
                case "p":
                    Game.currentRoom = ObjectFactory.roomAA;
                    break;
                case "q":
                    Game.currentRoom = ObjectFactory.roomV;
                    break;
                case "r":
                    Game.currentRoom = ObjectFactory.roomS;
                    break;
                case "s":
                    Game.currentRoom = ObjectFactory.hall5;
                    break;
                case "hall5":
                    Game.currentRoom = ObjectFactory.roomU;
                    break;
                case "v":
                    Game.currentRoom = ObjectFactory.roomW;
                    break;
                case "y":
                    Game.currentRoom = ObjectFactory.roomX;
                    break;
                case "hall4":
                    Game.currentRoom = ObjectFactory.roomAB;
                    break;
                case "aa":
                    Game.currentRoom = ObjectFactory.roomZ;
                    break;
                case "ad":
                    Game.currentRoom = ObjectFactory.roomAE;
                    break;
                default:
                    break;
            }
        }
        else if (direction.equals("South"))
        {
            switch(currentRoom.getRoomName())
            {
                case "a":
                    Game.currentRoom = ObjectFactory.hall1;
                    break;
                case "hall1":
                    Game.currentRoom = ObjectFactory.roomB;
                    break;
                case "hall2":
                    Game.currentRoom = ObjectFactory.roomE;
                    break;
                case "g":
                    Game.currentRoom = ObjectFactory.hall2;
                    break;
                case "h":
                    Game.currentRoom = ObjectFactory.hall3;
                    break;
                case "hall3":
                    Game.currentRoom = ObjectFactory.roomI;
                    break;
                case "j":
                    Game.currentRoom = ObjectFactory.roomK;
                    break;
                case "k":
                    Game.currentRoom = ObjectFactory.roomL;
                    break;
                case "n":
                    Game.currentRoom = ObjectFactory.roomM;
                    break;
                case "s":
                    Game.currentRoom = ObjectFactory.roomR;
                    break;
                case "hall5":
                    Game.currentRoom = ObjectFactory.roomS;
                    break;
                case "u":
                    Game.currentRoom = ObjectFactory.hall5;
                    break;
                case "v":
                    Game.currentRoom = ObjectFactory.roomQ;
                    break;
                case "w":
                    Game.currentRoom = ObjectFactory.roomS;
                    break;
                case "x":
                    Game.currentRoom = ObjectFactory.roomY;
                    break;
                case "z":
                    Game.currentRoom = ObjectFactory.roomAA;
                    break;
                case "hall4":
                    Game.currentRoom = ObjectFactory.roomH;
                    break;
                case "aa":
                    Game.currentRoom = ObjectFactory.roomP;
                    break;
                case "ab":
                    Game.currentRoom = ObjectFactory.hall4;
                    break;
                case "ae":
                    Game.currentRoom = ObjectFactory.roomAD;
                    break;
                default:
                    break;
            }
        }
        else if (direction.equals("East"))
        {
            switch(currentRoom.getRoomName())
            {
                case "c":
                    Game.currentRoom = ObjectFactory.hall1;
                    break;
                case "d":
                    Game.currentRoom = ObjectFactory.roomB;
                    break;
                case "e":
                    Game.currentRoom = ObjectFactory.roomD;
                    break;
                case "hall2":
                    Game.currentRoom = ObjectFactory.roomF;
                    break;
                case "f":
                    Game.currentRoom = ObjectFactory.roomC;
                    break;
                case "h":
                    Game.currentRoom = ObjectFactory.roomG;
                    break;
                case "j":
                    Game.currentRoom = ObjectFactory.hall3;
                    break;
                case "m":
                    Game.currentRoom = ObjectFactory.roomK;
                    break;
                case "n":
                    if (Game.placedWhite && Game.placedBlack)
                    {
                        // enters dialogue
                        Dialogue.setInDialogue(true);
                        Dialogue.getDialogue(null);
                    }
                    else
                    {
                        MainPanel.updatePanel("There is a steel gate obstructing your path out. It seems like something has to be" +
                                " done somewhere else before the gate can rise...");
                    }
                    //EXIT.. ADD MORE CODE HERE LATER
                    break;
                case "p":
                    Game.currentRoom = ObjectFactory.roomQ;
                    break;
                case "q":
                    Game.currentRoom = ObjectFactory.roomR;
                    break;
                case "hall5":
                    Game.currentRoom = ObjectFactory.roomT;
                    break;
                case "v":
                    Game.currentRoom = ObjectFactory.hall5;
                    break;
                case "w":
                    Game.currentRoom = ObjectFactory.roomU;
                    break;
                case "z":
                    Game.currentRoom = ObjectFactory.roomV;
                    break;
                case "hall4":
                    Game.currentRoom = ObjectFactory.roomAA;
                    break;
                case "ab":
                    Game.currentRoom = ObjectFactory.roomZ;
                    break;
                case "ac":
                    Game.currentRoom = ObjectFactory.roomAB;
                    break;
                case "ae":
                    Game.currentRoom = ObjectFactory.roomW;
                    break;
                case "af":
                    Game.currentRoom = ObjectFactory.roomAE;
                    break;
                default:
                    break;
            }
        }
        else if (direction.equals("West"))
        {
            switch(currentRoom.getRoomName())
            {
                case "hall1":
                    Game.currentRoom = ObjectFactory.roomC;
                    break;
                case "hall3":
                    Game.currentRoom = ObjectFactory.roomJ;
                    break;
                case "b":
                    Game.currentRoom = ObjectFactory.roomD;
                    break;
                case "c":
                    Game.currentRoom = ObjectFactory.roomF;
                    break;
                case "d":
                    Game.currentRoom = ObjectFactory.roomE;
                    break;
                case "f":
                    Game.currentRoom = ObjectFactory.hall2;
                    break;
                case "g":
                    Game.currentRoom = ObjectFactory.roomH;
                    break;
                case "k":
                    Game.currentRoom = ObjectFactory.roomM;
                    break;
                case "q":
                    Game.currentRoom = ObjectFactory.roomP;
                    break;
                case "r":
                    Game.currentRoom = ObjectFactory.roomQ;
                    break;
                case "hall5":
                    Game.currentRoom = ObjectFactory.roomV;
                    break;
                case "t":
                    Game.currentRoom = ObjectFactory.hall5;
                    break;
                case "u":
                    Game.currentRoom = ObjectFactory.roomW;
                    break;
                case "v":
                    Game.currentRoom = ObjectFactory.roomZ;
                    break;
                case "w":
                    Game.currentRoom = ObjectFactory.roomAE;
                    break;
                case "y":
                    Game.currentRoom = ObjectFactory.roomAD;
                    break;
                case "z":
                    Game.currentRoom = ObjectFactory.roomAB;
                    break;
                case "aa":
                    Game.currentRoom = ObjectFactory.hall4;
                    break;
                case "ab":
                    Game.currentRoom = ObjectFactory.roomAC;
                    break;
                case "ad":
                    Game.currentRoom = ObjectFactory.roomY;
                    break;
                case "ae":
                    Game.currentRoom = ObjectFactory.roomAF;
                    break;
                default:
                    break;
            }
        }
        else
        {
            System.out.println("uh ohh error in Nav class");
        }
    }
}
