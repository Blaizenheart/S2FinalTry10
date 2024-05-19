import java.util.ArrayList;
import java.util.List;

public class ObjectFactory
{ // all objects in the game are made here to organize
    // DICE
    public static Dice d4 = new Dice("D4", 4);
    public static Dice d6 = new Dice("D6",6);
    public static Dice d8 = new Dice("D8",8);
    public static Dice d10 = new Dice("D10",10);
    public static Dice d12 = new Dice("D12",12);
    public static Dice d20 = new Dice("D20",20);

    // WEAPONS
    public static Weapon dagger = new Weapon("dagger", "A small dagger.", 5, "a", d4, 1, "slashing");
    public static Weapon shortsword = new Weapon("shortsword", "A rusty shortsword.", 10, "a", d6, 1, "slashing");
    public static Weapon claws = new Weapon("claws", "rawr", 0, "", d4, 1, "slashing");
    public static ArrayList<Item> gameWeapons = new ArrayList<Item>(List.of(dagger)); // arraylist holding weapons

    // CONSUMABLES
    public static Consumable pretzel = new Consumable("pretzel","A very delicious pretzel.", 2, "a",
            true, 5, false, 0,false,"","eat");
    public static Consumable medkit = new Consumable("medkit","A medkit.", 10, "a",
            true, 15, false, 0,true,"bleeding","eat");
    public static ArrayList<Consumable> gameConsumables = new ArrayList<Consumable>(List.of(pretzel, medkit)); // arraylist holding consumables

    // arraylist for all items
    public static ArrayList<Item> gameItems = new ArrayList<Item>(List.of(dagger, pretzel, medkit)); // arraylist holding weapons

    // SPELLS
    public static HealingSpell cureWounds = new HealingSpell("Cure Wounds", "Heals a single target for 1d8 hp.",
            5, d8, 1,false);
    public static HealingSpell massCureWounds = new HealingSpell("Mass Cure Wounds", "Heals the entire party for 1d8 hp.",
            15, d8, 1,true);
    public static DebuffSpell charm = new DebuffSpell("Charm Person", "Charms an enemy, making them immobile for 1d4 rounds.",
            5, "charm", false, 2);
    public static BuffSpell bless = new BuffSpell("Bless", "Blesses an ally by increasing their attack by 1d4.",
            2, "blessed", false, 2);
    public static BuffSpell resistance = new BuffSpell("Resistance", "Increases an ally's resistance to attack by 1d4.",
            2, "resistant", true, 2);

    public static ArrayList<Spell> gameSpells = new ArrayList<Spell>(List.of(massCureWounds, cureWounds, charm, bless, resistance)); // arraylist holding weapons

    // PEOPLE
    public static Person player = new Person("Adventurer", "Despite everything, it's still you.",
            true, 1,0,20,20,5,3,10,20,20,
            dagger, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(List.of(pretzel)),
            new ArrayList<>());
    public static Person dain = new Person("Dain","a human with ghostly pale skin and dark hair who dons an eyepatch over his left eye",
            true, 1,0,20,20,5,3, 10,20,20, dagger,
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(List.of(cureWounds, bless, resistance)));
    public static Person everest = new Person("Everest","a halfling with milky skin and white hair that reeks of liquor",
            true, 1,0,20,20,3,5, 15,0,0, shortsword,
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Person henry = new Person("Henry","a tall and muscular human with long, ruffled silver hair, several scars marking his face and shoulders",
            true, 1,0,20,20,5,3, 15,0,0, shortsword,
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Person sylvie = new Person("Sylvie","a very beautiful woman with long wavy pink hair and a watery sheen to her skin",
            true, 1,0,25,25,3,8, 10,15,15, dagger,
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(List.of(charm)));
    public static Person saltine = new Person("Saltine","a blonde freckled girl with a large pink ribbon adorned in her hair",
            true, 1,0,25,25,3,5, 10,15,15, dagger,
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>());
    public static Person todd = new Person("Todd","a hooded figure with an ominous presence",
            true, 1,0,25,25,5,5, 15,15,15, dagger,
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>());
    public static ArrayList<Person> gamePeople = new ArrayList<Person>(List.of(player, dain, everest, henry, sylvie));

    // MONSTERS
    public static Monster testDummy = new Monster("Wolf", "A large wolf with sharp fangs.", true, 1,0,50,
            50,5,5,10,0, 0, claws, new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(), new ArrayList<>(), List.of(dagger), new ArrayList<>(), "bleeding", 30, 3);
    public static ArrayList<Monster> gameMonsters = new ArrayList<Monster>(List.of(testDummy));

    // ROOMS (im going to die)
    public static Room roomA = new Room("a", "The small room has cobbled walls and a stale odor.", new ArrayList<>(List.of("S")),
            new ArrayList<>(), new ArrayList<>(List.of(testDummy)), new ArrayList<>(List.of(dain)));
    public static Room roomB = new Room("b", "The walls are lined with arch indents. The floor here is tiled and slightly polished, " +
            "although there are still spots where the tiles are eroding into dust.", new ArrayList<>(List.of("N","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomC = new Room("c", "There are intricate pillars seemingly sculpted into the corners of the room. " +
            "A faint scent of alcohol lingers in the air. It seems to be getting stronger to the west.", new ArrayList<>(List.of("E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomD = new Room("d","The room is nauseatingly narrow. It is unclear what the purpose of this room might’ve been. The smell of gore seems to lead to the west.",
            new ArrayList<>(List.of("E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomE = new Room("e","This room is littered with various animal bones, some cleaner than others. Traces of what must’ve been a campfire lie in the middle of the room.",
            new ArrayList<>(List.of("N","E")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(List.of(henry)));
    public static Room roomF = new Room("f","The stench of liquor is incredibly strong here. Barrels of alcohol, presumably, are scattered across the room.",
            new ArrayList<>(List.of("E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(List.of(everest)));
    public static Room roomG = new Room("g","", new ArrayList<>(List.of("S","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomH = new Room("h","", new ArrayList<>(List.of("N","S","E")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomI = new Room("i","", new ArrayList<>(List.of("N")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomJ = new Room("j","", new ArrayList<>(List.of("E","S")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomK = new Room("k","", new ArrayList<>(List.of("N","S","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomL = new Room("l","", new ArrayList<>(List.of("N")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomM = new Room("m","", new ArrayList<>(List.of("N","E")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomN = new Room("n","", new ArrayList<>(List.of("S","E")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomO = new Room("o","", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomP = new Room("p","", new ArrayList<>(List.of("N", "E")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomQ = new Room("q","", new ArrayList<>(List.of("E", "W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomR = new Room("r","", new ArrayList<>(List.of("N", "W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomS = new Room("s","", new ArrayList<>(List.of("N", "S")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomT = new Room("t","", new ArrayList<>(List.of("W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomU = new Room("u","", new ArrayList<>(List.of("S","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomV = new Room("v","", new ArrayList<>(List.of("N","S","E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomW = new Room("w","", new ArrayList<>(List.of("S", "E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomX = new Room("x","", new ArrayList<>(List.of("S")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomY = new Room("y","", new ArrayList<>(List.of("N", "W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomZ = new Room("z","", new ArrayList<>(List.of("S", "E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

    public static Room hall1 = new Room("hall1","The walls of the hallway are slightly crumbling.", new ArrayList<>(List.of("N", "S","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room hall2 = new Room("hall2","", new ArrayList<>(List.of("N", "S","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room hall3 = new Room("hall3","", new ArrayList<>(List.of("N", "S","E")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room hall4 = new Room("hall4","", new ArrayList<>(List.of("N", "S","E")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room hall5 = new Room("hall5","", new ArrayList<>(List.of("N", "S","E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

    public static Room roomAA = new Room("aa","", new ArrayList<>(List.of("N", "S","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomAB = new Room("ab","", new ArrayList<>(List.of("S", "E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomAC = new Room("ac","", new ArrayList<>(List.of("E")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomAD = new Room("ad","", new ArrayList<>(List.of("N", "W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomAE = new Room("ae","", new ArrayList<>(List.of("S", "E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomAF = new Room("af","", new ArrayList<>(List.of("E")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

    //public static Room[] gameRooms = {roomA, roomB, roomC, roomD, roomE, roomF, roomG, roomH, roomI, roomJ, roomK, roomL, roomM, roomN, roomO, roomP, roomQ, roomR, roomS, roomT, roomU, roomV, roomW, roomX, roomY, roomZ, hall1, hall2, hall3, hall4, hall5, roomAA, roomAB, roomAC, roomAD, roomAE, roomAF};

}
