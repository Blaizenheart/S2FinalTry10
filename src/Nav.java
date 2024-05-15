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
                    Main.currentRoom = ObjectFactory.roomA;
                    break;
                case "b":
                    Main.currentRoom = ObjectFactory.hall1;
                    break;
                case "e":
                    Main.currentRoom = ObjectFactory.hall2;
                    break;
                case "hall2":
                    Main.currentRoom = ObjectFactory.roomG;
                    break;
                case "h":
                    Main.currentRoom = ObjectFactory.hall4;
                    break;
                case "hall3":
                    Main.currentRoom = ObjectFactory.roomH;
                    break;
                case "i":
                    Main.currentRoom = ObjectFactory.hall3;
                    break;
                case "k":
                    Main.currentRoom = ObjectFactory.roomJ;
                    break;
                case "l":
                    Main.currentRoom = ObjectFactory.roomK;
                    break;
                case "m":
                    Main.currentRoom = ObjectFactory.roomN;
                    break;
                case "p":
                    Main.currentRoom = ObjectFactory.roomAA;
                    break;
                case "q":
                    Main.currentRoom = ObjectFactory.roomV;
                    break;
                case "r":
                    Main.currentRoom = ObjectFactory.roomS;
                    break;
                case "s":
                    Main.currentRoom = ObjectFactory.hall5;
                    break;
                case "hall5":
                    Main.currentRoom = ObjectFactory.roomU;
                    break;
                case "v":
                    Main.currentRoom = ObjectFactory.roomW;
                    break;
                case "y":
                    Main.currentRoom = ObjectFactory.roomX;
                    break;
                case "hall4":
                    Main.currentRoom = ObjectFactory.roomAB;
                    break;
                case "aa":
                    Main.currentRoom = ObjectFactory.roomZ;
                    break;
                case "ad":
                    Main.currentRoom = ObjectFactory.roomAE;
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
                    Main.currentRoom = ObjectFactory.hall1;
                    break;
                case "hall1":
                    Main.currentRoom = ObjectFactory.roomB;
                    break;
                case "hall2":
                    Main.currentRoom = ObjectFactory.roomE;
                    break;
                case "g":
                    Main.currentRoom = ObjectFactory.hall2;
                    break;
                case "h":
                    Main.currentRoom = ObjectFactory.hall3;
                    break;
                case "hall3":
                    Main.currentRoom = ObjectFactory.roomI;
                    break;
                case "j":
                    Main.currentRoom = ObjectFactory.roomK;
                    break;
                case "k":
                    Main.currentRoom = ObjectFactory.roomL;
                    break;
                case "n":
                    Main.currentRoom = ObjectFactory.roomM;
                    break;
                case "s":
                    Main.currentRoom = ObjectFactory.roomR;
                    break;
                case "hall5":
                    Main.currentRoom = ObjectFactory.roomS;
                    break;
                case "u":
                    Main.currentRoom = ObjectFactory.hall5;
                    break;
                case "v":
                    Main.currentRoom = ObjectFactory.roomQ;
                    break;
                case "w":
                    Main.currentRoom = ObjectFactory.roomS;
                    break;
                case "x":
                    Main.currentRoom = ObjectFactory.roomY;
                    break;
                case "z":
                    Main.currentRoom = ObjectFactory.roomAA;
                    break;
                case "hall4":
                    Main.currentRoom = ObjectFactory.roomH;
                    break;
                case "aa":
                    Main.currentRoom = ObjectFactory.roomP;
                    break;
                case "ab":
                    Main.currentRoom = ObjectFactory.hall4;
                    break;
                case "ae":
                    Main.currentRoom = ObjectFactory.roomAD;
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
                    Main.currentRoom = ObjectFactory.hall1;
                    break;
                case "d":
                    Main.currentRoom = ObjectFactory.roomB;
                    break;
                case "e":
                    Main.currentRoom = ObjectFactory.roomD;
                    break;
                case "hall2":
                    Main.currentRoom = ObjectFactory.roomF;
                    break;
                case "f":
                    Main.currentRoom = ObjectFactory.roomC;
                    break;
                case "h":
                    Main.currentRoom = ObjectFactory.roomG;
                    break;
                case "hall3":
                    Main.currentRoom = ObjectFactory.roomJ;
                    break;
                case "j":
                    Main.currentRoom = ObjectFactory.hall3;
                    break;
                case "m":
                    Main.currentRoom = ObjectFactory.roomK;
                    break;
                case "n":
                    //EXIT.. ADD MORE CODE HERE LATER
                    break;
                case "p":
                    Main.currentRoom = ObjectFactory.roomQ;
                    break;
                case "q":
                    Main.currentRoom = ObjectFactory.roomR;
                    break;
                case "hall5":
                    Main.currentRoom = ObjectFactory.roomT;
                    break;
                case "v":
                    Main.currentRoom = ObjectFactory.hall5;
                    break;
                case "w":
                    Main.currentRoom = ObjectFactory.roomU;
                    break;
                case "z":
                    Main.currentRoom = ObjectFactory.roomV;
                    break;
                case "hall4":
                    Main.currentRoom = ObjectFactory.roomAA;
                    break;
                case "ab":
                    Main.currentRoom = ObjectFactory.roomZ;
                    break;
                case "ac":
                    Main.currentRoom = ObjectFactory.roomAB;
                    break;
                case "ae":
                    Main.currentRoom = ObjectFactory.roomW;
                    break;
                case "af":
                    Main.currentRoom = ObjectFactory.roomAE;
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
                    Main.currentRoom = ObjectFactory.roomC;
                    break;
                case "b":
                    Main.currentRoom = ObjectFactory.roomD;
                    break;
                case "c":
                    Main.currentRoom = ObjectFactory.roomF;
                    break;
                case "d":
                    Main.currentRoom = ObjectFactory.roomE;
                    break;
                case "f":
                    Main.currentRoom = ObjectFactory.hall2;
                    break;
                case "g":
                    Main.currentRoom = ObjectFactory.roomH;
                    break;
                case "k":
                    Main.currentRoom = ObjectFactory.roomM;
                    break;
                case "q":
                    Main.currentRoom = ObjectFactory.roomP;
                    break;
                case "r":
                    Main.currentRoom = ObjectFactory.roomQ;
                    break;
                case "hall5":
                    Main.currentRoom = ObjectFactory.roomV;
                    break;
                case "t":
                    Main.currentRoom = ObjectFactory.hall5;
                    break;
                case "u":
                    Main.currentRoom = ObjectFactory.roomW;
                    break;
                case "v":
                    Main.currentRoom = ObjectFactory.roomZ;
                    break;
                case "w":
                    Main.currentRoom = ObjectFactory.roomAE;
                    break;
                case "y":
                    Main.currentRoom = ObjectFactory.roomAD;
                    break;
                case "z":
                    Main.currentRoom = ObjectFactory.roomAB;
                    break;
                case "aa":
                    Main.currentRoom = ObjectFactory.hall4;
                    break;
                case "ab":
                    Main.currentRoom = ObjectFactory.roomAC;
                    break;
                case "ad":
                    Main.currentRoom = ObjectFactory.roomY;
                    break;
                case "ae":
                    Main.currentRoom = ObjectFactory.roomAF;
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
