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
    public static Weapon dagger = new Weapon("dagger", "A small dagger.", 5, "a ", d4, 1, "piercing");
    public static Weapon sickle = new Weapon("sickle", "A rusted sickle.", 5, "a ", d4, 1, "slashing");
    public static Weapon shortsword = new Weapon("shortsword", "A rusty shortsword.", 10, "a ", d6, 1, "slashing");
    public static Weapon spear = new Weapon("spear", "A pointy spear.", 10, "a ", d6, 1, "piercing");
    public static Weapon crossbow = new Weapon("crossbow", "A wooden crossbow.", 15, "a ", d8, 1, "piercing");
    public static Weapon club = new Weapon("club", "A hard club.", 5, "a ", d4, 1, "bludgeoning");
    public static Weapon greatclub = new Weapon("greatclub", "An even bigger club.", 15, "a ", d8, 1, "bludgeoning");
    public static Weapon claws = new Weapon("claws", "rawr", 0, "", d4, 1, "slashing");
    public static Weapon fangs = new Weapon("fangs", "rawr", 0, "", d4, 1, "piercing");
    public static Weapon glaive = new Weapon("glaive", "A heavy glaive.", 20, "", d10, 1, "slashing");
    public static Weapon greataxe = new Weapon("glaive", "A large greataxe.", 25, "", d12, 1, "slashing");
    public static Weapon maul = new Weapon("maul", "A spiked maul.", 15, "a ", d6, 2, "bludgeoning");

    public static ArrayList<Item> gameWeapons = new ArrayList<Item>(List.of(dagger, shortsword, claws, fangs, spear, club, greatclub, crossbow, sickle, glaive, greataxe, maul)); // arraylist holding weapons

    // CONSUMABLES
    public static Item pretzel = new Consumable("pretzel","A very small but delicious pretzel that restores 5 hp.", 2, "a",
            true, 5, false, 0,false,"","eat");
    public static Item bun = new Consumable("bun","A medium-sized bun that restores 10 hp.", 5, "a",
            true, 10, false, 0,false,"","eat");
    public static Item cookie = new Consumable("cookie","A comically large cookie that restores 20 hp.", 11, "a",
            true, 20, false, 0,false,"","eat");
    public static Item bagel = new Consumable("bagel","A bagel that is imbued with magic that can restore 10 mp.", 10, "a",
            false, 0, true, 10,false,"","eat");
    public static Item donut = new Consumable("donut","A donut (a better bagel) that is imbued with magic that can restore 15 mp.", 17, "a",
            false, 0, true, 15,false,"","eat");
    public static Item bearClaw = new Consumable("bear claw","A sweet pastry that is imbued with magic that can restore 20 mp and remove poison.", 25, "a",
            false, 0, true, 20,true,"poison","eat");

    public static ArrayList<Item> saltineWares = new ArrayList<>(List.of(pretzel, bun, cookie, bagel, donut, bearClaw));

    public static Item medkit = new Consumable("medkit","A medkit that restores 15 hp and stops bleeding.", 15, "a",
            true, 15, false, 0,true,"bleeding","use");
    public static ArrayList<Item> gameConsumables = new ArrayList<>(List.of(pretzel, bun, cookie, bagel, donut, bearClaw, medkit)); // arraylist holding consumables

    // SPELLS
    public static Spell cureWounds = new HealingSpell("Cure Wounds", "Heals a single target for 1d8 hp.",
            5, d8, 1,false);
    public static Spell massCureWounds = new HealingSpell("Mass Cure Wounds", "Heals the entire party for 1d8 hp.",
            15, d8, 1,true);
    public static Spell charm = new DebuffSpell("Charm Person", "Charms an enemy, making them immobile for 1d4 rounds.",
            5, "charm", false, 2);
    public static Spell bless = new BuffSpell("Bless", "Blesses an ally by increasing their attack by 1d4.",
            2, "blessed", false, 2);
    public static Spell resistance = new BuffSpell("Resistance", "Increases an ally's resistance to attack by 1d4.",
            2, "resistant", true, 2);
    public static Spell firebolt = new AttackSpell("Firebolt", "Hurls a small bolt of fire at an enemy for 1d8 fire damage.", 5, false, "fire", d8, 1);

    public static ArrayList<Spell> gameSpells = new ArrayList<>(List.of(massCureWounds, cureWounds, charm, bless, resistance, firebolt)); // arraylist holding spells

    // SPELL SCROLLS
    public static Item fireboltScroll = new SpellScroll("firebolt scroll", "Teaches firebolt.", 7, "a", firebolt);

    public static ArrayList<Item> gameSpellScrolls = new ArrayList<>(List.of(fireboltScroll)); // arraylist holding scrolls

    // CONTAINERS
    // chest in room b
    public static Item chestH1 = new Container("chest", "",0,"a",false,false,null, new ArrayList<>(List.of(bun)));
    public static Item chestJ1 = new Container("chest", "",0,"a",false,false,null, new ArrayList<>(List.of(fireboltScroll, medkit)));

    // IMPORTANT QUEST ITEMS
    public static Item whiteScale = new QuestItem("white scale", "A shimmery, almost iridescent pearly white scale that had been plucked off of a white dragon.", 0, "a");
    public static Item blackScale = new QuestItem("black scale", "A dark, reflective obsidian colored scale that had been taken from a black dragon.", 0, "a");

    // arraylist for all the items in the game
    public static ArrayList<Item> gameItems = new ArrayList<>(List.of(dagger, shortsword, claws, pretzel, cookie, bagel, donut, bearClaw, medkit, fireboltScroll, whiteScale, blackScale)); // arraylist holding all the game items

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
            new ArrayList<>(List.of(charm, firebolt)));
    public static Person saltine = new Person("Saltine","a blonde freckled girl with a large pink ribbon adorned in her hair",
            true, 1,0,25,25,3,5, 10,15,15, dagger,
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>());
    public static ArrayList<Person> gamePeople = new ArrayList<Person>(List.of(player, dain, everest, henry, sylvie, saltine));

    // MONSTERS
    public static Monster wolf = new Monster("Wolf", "A large wolf with sharp fangs.", true, 3,0,50,
            50,5,5,10,0, 0, claws, new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "bleeding", 30, 3);
    public static Monster grick = new Monster("Grick", "A large worm-like monster with barbed tentacles.", true, 3,0,75,
            75,5,5,10,0, 0, fangs, new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "bleeding", 40, 3);
    public static Monster rat1 = new Monster("Feral Rat", "A scraggly rat with red eyes.", true, 1,0,10,
            10,2,1,50,0, 0, fangs, new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "bleeding", 40, 3);
    public static Monster rat2 = new Monster("Big Rat", "A large rat with a stubbed tail.", true, 1,0,10,
            10,2,1,50,0, 0, fangs, new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "bleeding", 40, 3);
    public static Monster possum = new Monster("Skeletal Possum", "An animated set of bones vaguely resembling a possum or a rat.", true, 1,0,30,
            30,3,1,30,0, 0, fangs, new ArrayList<>(), new ArrayList<>(List.of("bludgeoning")),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "bleeding", 40, 3);
    public static Monster spider1 = new Monster("Black Spider", "A large black spider.", true, 2,0,30,
            30,3,1,30,0, 0, fangs, new ArrayList<>(), new ArrayList<>(List.of("bludgeoning")),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "poisoned", 10, 3);
    public static Monster spider2 = new Monster("Green Spider", "A small green spider.", true, 2,0,10,
            10,4,1,30,0, 0, fangs, new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "poisoned", 50, 1);
    public static Monster spider3 = new Monster("White Spider", "A medium-sized white spider.", true, 2,0,20,
            20,3,6,30,0, 0, fangs, new ArrayList<>(), new ArrayList<>(List.of("slashing")),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "poisoned", 5, 3);
    public static Monster spider4 = new Monster("Brown Spider", "A large fuzzy brown spider.", true, 2,0,25,
            25,2,3,30,0, 0, fangs, new ArrayList<>(), new ArrayList<>(List.of("piercing")),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "poisoned", 15, 2);


    // BOSSES !!
    public static Monster lightDragon = new Monster("Light Dragon", "A large dragon with shimmering scales. It is emitting a bright light.", true,
            10,0, 350, 350, 5, 20, 0,0,0, claws, new ArrayList<>(), new ArrayList<>(List.of("dark")),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(List.of(whiteScale)), new ArrayList<>(), "blinded", 20, 2);
    public static Monster darkDragon = new Monster("Shadow Dragon", "A large dragon with dark, obsidian scales and air of smog surrounding it.",true, 10,
            0, 350, 350, 5, 20, 0,0,0, claws, new ArrayList<>(), new ArrayList<>(List.of("light")),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(List.of(blackScale)), new ArrayList<>(), "blinded", 20, 2);

    public static ArrayList<Monster> gameMonsters = new ArrayList<Monster>(List.of(wolf, rat1, rat2, grick, lightDragon, darkDragon));

    // ROOMS (im going to die)
    public static Room roomA = new Room("a", "The small room has cobbled walls and a stale odor.", new ArrayList<>(List.of("S")),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(List.of(dain)));
    public static Room roomB = new Room("b", "The walls are lined with arch indents. The floor here is tiled and slightly polished, " +
            "although there are still spots where the tiles are eroding into dust.", new ArrayList<>(List.of("N","W")), new ArrayList<>(List.of(chestH1)), new ArrayList<>(), new ArrayList<>());
    public static Room roomC = new Room("c", "There are intricate pillars seemingly sculpted into the corners of the room. " +
            "A faint scent of alcohol lingers in the air. It seems to be getting stronger to the west.", new ArrayList<>(List.of("E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomD = new Room("d","The room is nauseatingly narrow. It is unclear what the purpose of this room might’ve been. The smell of gore seems to lead to the west.",
            new ArrayList<>(List.of("E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomE = new Room("e","This room is littered with various animal bones, some cleaner than others. Traces of what must’ve been a campfire lie in the middle of the room.",
            new ArrayList<>(List.of("N","E")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomF = new Room("f","The stench of liquor is incredibly strong here. Barrels of alcohol, presumably, are scattered across the room.",
            new ArrayList<>(List.of("E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(List.of(everest)));
    public static Room roomG = new Room("g","This room is considerably smaller than the rest. Long eroded skeletons of small critters poke out of small holes near the sides of the walls.",
            new ArrayList<>(List.of("S","W")), new ArrayList<>(), new ArrayList<>(List.of(possum)), new ArrayList<>());
    public static Room roomH = new Room("h","The room is lined with several terraces that sprout a great amount of grass and flowers. A dried up fountain sits in the middle of the room. " +
            "An aura of arcana surrounds the greenery, keeping it alive despite the lack of water.", new ArrayList<>(List.of("N","S","E")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(List.of(saltine)));
    public static Room roomI = new Room("i","Large cobwebs cover the corners of the room, some loose webbing dangling down from the ceiling. The entire room is very dusty.",
            new ArrayList<>(List.of("N")), new ArrayList<>(), new ArrayList<>(List.of(spider1, spider2, spider3, spider4)), new ArrayList<>());
    public static Room roomJ = new Room("j","The room is considerably small. Near the door to the south is a rusted metal plaque with the words 'Here sleeps Miasma' etched onto it.",
            new ArrayList<>(List.of("E","S")), new ArrayList<>(List.of(chestJ1)), new ArrayList<>(), new ArrayList<>());
    public static Room roomK = new Room("k","", new ArrayList<>(List.of("N","S","W")), new ArrayList<>(), new ArrayList<>(List.of(darkDragon)), new ArrayList<>());
    public static Room roomL = new Room("l","", new ArrayList<>(List.of("N")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomM = new Room("m","", new ArrayList<>(List.of("N","E")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomN = new Room("n","", new ArrayList<>(List.of("S","E")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomO = new Room("o","", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomP = new Room("p","", new ArrayList<>(List.of("N", "E")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomQ = new Room("q","", new ArrayList<>(List.of("E", "W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomR = new Room("r","", new ArrayList<>(List.of("N", "W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomS = new Room("s","", new ArrayList<>(List.of("N", "S")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(List.of(sylvie)));
    public static Room roomT = new Room("t","", new ArrayList<>(List.of("W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomU = new Room("u","", new ArrayList<>(List.of("S","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomV = new Room("v","This room has a very grand feel to it. There are slightly chipped and worn down marble statues at the corners of the room. " +
            "At the center of the room, upon a slightly raised stone platform, is a statue of a skeletal hand holding a balancing scale. You notice that the scales are functional. You could" +
            " try to PLACE something onto them...",
            new ArrayList<>(List.of("N","S","E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomW = new Room("w","", new ArrayList<>(List.of("S", "E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(List.of(saltine)));
    public static Room roomX = new Room("x","", new ArrayList<>(List.of("S")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomY = new Room("y","", new ArrayList<>(List.of("N", "W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomZ = new Room("z","", new ArrayList<>(List.of("S", "E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

    public static Room hall1 = new Room("hall1","The walls of the hallway are slightly crumbling.", new ArrayList<>(List.of("N", "S","W")), new ArrayList<>(List.of(chestH1)), new ArrayList<>(List.of(rat1, rat2)), new ArrayList<>());
    public static Room hall2 = new Room("hall2","Dim lanterns light up the hallway. Slight foliage forms at the corners of the room.", new ArrayList<>(List.of("N", "S","E")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room hall3 = new Room("hall3","The hallway is wide, with a low ceiling.", new ArrayList<>(List.of("N", "S","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room hall4 = new Room("hall4","", new ArrayList<>(List.of("N", "S","E")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room hall5 = new Room("hall5","", new ArrayList<>(List.of("N", "S","E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomAA = new Room("aa","", new ArrayList<>(List.of("N", "S","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomAB = new Room("ab","", new ArrayList<>(List.of("S", "E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomAC = new Room("ac","", new ArrayList<>(List.of("E")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(List.of(henry)));
    public static Room roomAD = new Room("ad","", new ArrayList<>(List.of("N", "W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomAE = new Room("ae","", new ArrayList<>(List.of("S", "E","W")), new ArrayList<>(), new ArrayList<>(List.of(lightDragon)), new ArrayList<>());
    public static Room roomAF = new Room("af","", new ArrayList<>(List.of("E")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

    public static Room[] gameRooms = {roomA, roomB, roomC, roomD, roomE, roomF, roomG, roomH, roomI, roomJ, roomK, roomL, roomM, roomN, roomO, roomP, roomQ, roomR, roomS, roomT, roomU, roomV, roomW, roomX, roomY, roomZ, hall1, hall2, hall3, hall4, hall5, roomAA, roomAB, roomAC, roomAD, roomAE, roomAF};

    public static String printShop() // prints all the items in Saltine's shop
    {
        String output = "";
        int count = 1;
        for (Item shopItem: saltineWares)
        {
            output += count + ") " + shopItem.getName() + " - " + shopItem.getDesc() + " - " + shopItem.getValue() + "G" + "\n";
            count++;
        }
        output += "GOLD: " + Game.getGold() + "\n";
        output += "(Enter 0 to leave the shop.)";
        return output;
    }
}
