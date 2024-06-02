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

    // WEAPONS
    public static Weapon dagger = new Weapon("dagger", "A small dagger.", 5, "a ", d4, 1, "piercing");
    public static Weapon shortsword = new Weapon("shortsword", "A rusty shortsword.", 10, "a ", d6, 1, "slashing");
    public static Weapon spear = new Weapon("spear", "A pointy spear.", 10, "a ", d6, 1, "piercing");
    public static Weapon crossbow = new Weapon("crossbow", "A wooden crossbow.", 15, "a ", d8, 1, "piercing");
    public static Weapon club = new Weapon("club", "A hard club.", 5, "a ", d4, 1, "bludgeoning");
    public static Weapon claws = new Weapon("claws", "rawr", 0, "", d4, 1, "slashing");
    public static Weapon fangs = new Weapon("fangs", "rawr", 0, "", d4, 1, "piercing");
    public static Weapon glaive = new Weapon("glaive", "A heavy glaive.", 20, "", d10, 1, "slashing");
    public static Weapon greataxe = new Weapon("glaive", "A large greataxe.", 25, "", d12, 1, "slashing");
    public static Weapon maul = new Weapon("maul", "A spiked maul.", 15, "a ", d6, 2, "bludgeoning");

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
    public static Spell thunderclap = new AttackSpell("Thunderclap", "With a clap, damage all enemies for 2d8 thunder damage.", 10, false, "thunder", d8, 2);
    public static Spell lightSpray = new AttackSpell("Light Spray", "Sprays all enemies with rays of light for 3d10 light damage.", 15, true, "light", d10, 3);
    public static Spell acidSpray = new AttackSpell("Acid Spray", "Sprays all enemies with a spray of corrosive substance for 1d12 acid damage.", 10, true, "acid", d12, 1);


    // SPELL SCROLLS
    public static Item fireboltScroll = new SpellScroll("firebolt scroll", "Teaches firebolt.", 7, "a", firebolt);
    public static Item thunderScroll = new SpellScroll("thunderclap scroll", "Teaches thunderclap.", 13, "a", thunderclap);
    public static Item lightScroll = new SpellScroll("lightspray scroll", "Teaches light spray.", 78, "a", lightSpray);
    public static Item acidScroll = new SpellScroll("acidspray scroll", "Teaches acid spray.", 10, "an", acidSpray);


    public static ArrayList<Item> gameSpellScrolls = new ArrayList<>(List.of(fireboltScroll, thunderScroll, lightScroll, acidScroll)); // arraylist holding scrolls

    // IMPORTANT QUEST ITEMS
    public static Item whiteScale = new Item("white scale", "A shimmery, almost iridescent pearly white scale that had been plucked off of a white dragon.", 0, "a");
    public static Item blackScale = new Item("black scale", "A dark, reflective obsidian colored scale that had been taken from a black dragon.", 0, "a");

    // KEYS
    public static Item longKey = new Item("long key", "An elongated piece of metal vaguely resembling a key.", 0,"a");

    // CONTAINERS
    public static Item chestH1 = new Container("chest", "",0,"a",false,false,null, new ArrayList<>(List.of(bun, bun, pretzel)));
    public static Item chestJ1 = new Container("chest", "",0,"a",false,false,null, new ArrayList<>(List.of(fireboltScroll, medkit)));
    public static Item chestAF1 = new Container("big chest", "",0,"a",false,false,null, new ArrayList<>(List.of(medkit, medkit, thunderScroll)));
    public static Item chestAF2 = new Container("wooden chest", "",0,"a",false,false,null, new ArrayList<>(List.of(bun, lightScroll)));
    public static Item chestP1 = new Container("long chest", "",0,"a",true,false, longKey, new ArrayList<>(List.of(greataxe)));
    public static Item chestM1 = new Container("chest", "",0,"a",false,false,null, new ArrayList<>(List.of(pretzel, bun, medkit, donut)));
    public static Item chestAA1 = new Container("chest", "", 0, "a", false, false, null, new ArrayList<>(List.of(acidScroll)));

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
            true, 1,0,25,25,3,8, 10,30,30, dagger,
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(List.of(charm, firebolt)));
    public static Person saltine = new Person("Saltine","a blonde freckled girl with a large pink ribbon adorned in her hair",
            true, 1,0,25,25,3,5, 10,15,15, dagger,
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>());
    public static ArrayList<Person> gamePeople = new ArrayList<Person>(List.of(player, dain, everest, henry, sylvie, saltine));

    // MONSTERS
    public static Monster wolf = new Monster("Wolf", "A large wolf with sharp fangs.", true, 2,0,50,
            50,5,5,10,0, 0, claws, new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "bleeding", 30, 3);
    public static Monster grick = new Monster("Grick", "A large worm-like monster with barbed tentacles.", true, 3,0,75,
            75,5,5,10,0, 0, fangs, new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(List.of(longKey)), new ArrayList<>(), "bleeding", 40, 3);
    public static Monster rat1 = new Monster("Feral Rat", "A scraggly rat with red eyes.", true, 1,0,10,
            10,2,1,20,0, 0, fangs, new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "bleeding", 40, 3);
    public static Monster rat2 = new Monster("Big Rat", "A large rat with a stubbed tail.", true, 1,0,10,
            10,2,1,20,0, 0, fangs, new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "bleeding", 40, 3);
    public static Monster possum = new Monster("Skeletal Possum", "An animated set of bones vaguely resembling a possum or a rat.", true, 1,0,30,
            30,3,1,15,0, 0, fangs, new ArrayList<>(), new ArrayList<>(List.of("bludgeoning")),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "bleeding", 40, 3);
    public static Monster spider1 = new Monster("Black Spider", "A large black spider.", true, 1,0,30,
            30,3,1,10,0, 0, fangs, new ArrayList<>(), new ArrayList<>(List.of("bludgeoning")),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "poisoned", 10, 3);
    public static Monster spider2 = new Monster("Green Spider", "A small green spider.", true, 1,0,10,
            10,4,1,30,0, 0, fangs, new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "poisoned", 50, 1);
    public static Monster spider3 = new Monster("White Spider", "A medium-sized white spider.", true, 1,0,20,
            20,3,6,20,0, 0, fangs, new ArrayList<>(), new ArrayList<>(List.of("slashing")),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "poisoned", 5, 3);
    public static Monster spider4 = new Monster("Brown Spider", "A large fuzzy brown spider.", true, 1,0,25,
            25,2,3,10,0, 0, fangs, new ArrayList<>(), new ArrayList<>(List.of("piercing")),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "poisoned", 15, 2);
    public static Monster kruthnik = new Monster("Kruthnik", "A large creature with a reptilian head, scaly armored body, and four jagged insect legs.",
            true, 1,0,80, 80,2,3,10,0, 0, fangs, new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null, 15, 2);
    public static Monster twigBlight1 = new Monster("Small Twig Blight", "A creature that greatly resembles a dead shrub.", true, 1,0,25,
            25,2,1,20,0, 0, claws, new ArrayList<>(), new ArrayList<>(List.of("fire")),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null, 15, 2);
    public static Monster twigBlight2 = new Monster("Big Twig Blight", "A creature that greatly resembles a dead shrub.", true, 1,0,30,
            30,2,1,20,0, 0, claws, new ArrayList<>(), new ArrayList<>(List.of("fire")),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null, 15, 2);
    public static Monster twigBlight3 = new Monster("Odd Twig Blight", "A creature that greatly resembles a dead shrub.", true, 1,0,50,
            50,2,1,20,0, 0, claws, new ArrayList<>(), new ArrayList<>(List.of("fire")),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null, 15, 2);
    public static Monster fungi1 = new Monster("Smelly Shroom", "A fungi-like creature that emits a foul odor.", true, 1,0,50,
            30,2,1,10,0, 0, claws, new ArrayList<>(), new ArrayList<>(List.of("fire")),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "poisoned", 10, 2);
    public static Monster fungi2 = new Monster("Wilted Shroom", "A fungi-like creature that emits a foul odor.", true, 1,0,50,
            20,2,1,10,0, 0, claws, new ArrayList<>(), new ArrayList<>(List.of("fire")),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), "poisoned", 10, 2);
    public static Monster hobgoblin1 = new Monster("Young Hobgoblin", "A larger goblin.", true, 3,0,50,
            50,2,5,10,0, 0, maul, new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(List.of(maul, pretzel, bearClaw, bun)), new ArrayList<>(), "bleeding", 20, 3);
    public static Monster hobgoblin2 = new Monster("Big Hobgoblin", "A larger goblin.", true, 2,0,45,
            45,2,5,10,0, 0, glaive, new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(List.of(glaive, pretzel, pretzel, pretzel, pretzel, pretzel, pretzel)), new ArrayList<>(), "bleeding", 20, 3);


    // BOSSES !!
    public static Monster lightDragon = new Monster("Light Dragon", "A large dragon with shimmering scales. It is emitting a bright light.", true,
            10,0, 350, 350, 5, 20, 0,0,0, claws, new ArrayList<>(), new ArrayList<>(List.of("dark")),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(List.of(whiteScale)), new ArrayList<>(), "blinded", 20, 2);
    public static Monster darkDragon = new Monster("Shadow Dragon", "A large dragon with dark, obsidian scales and air of smog surrounding it.",true, 10,
            0, 350, 350, 5, 20, 0,0,0, claws, new ArrayList<>(), new ArrayList<>(List.of("light")),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(List.of(blackScale)), new ArrayList<>(), "blinded", 20, 2);

    // ROOMS (im going to die)
    public static Room roomA = new Room("a", "The small room has cobbled walls and a stale odor.", new ArrayList<>(List.of("S")),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(List.of(dain)));
    public static Room roomB = new Room("b", "The walls are lined with arch indents. The floor here is tiled and slightly polished, " +
            "although there are still spots where the tiles are eroding into dust.", new ArrayList<>(List.of("N","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomC = new Room("c", "There are intricate pillars seemingly sculpted into the corners of the room. " +
            "A faint scent of alcohol lingers in the air. It seems to be getting stronger to the west.", new ArrayList<>(List.of("E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomD = new Room("d","The room is nauseatingly narrow. It is unclear what the purpose of this room might’ve been. The smell of gore seems to lead to the west.",
            new ArrayList<>(List.of("E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomE = new Room("e","This room is littered with various animal bones, some cleaner than others. Traces of what must’ve been a campfire lie in the middle of the room.",
            new ArrayList<>(List.of("N","E")), new ArrayList<>(List.of(club)), new ArrayList<>(), new ArrayList<>());
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
    public static Room roomK = new Room("k","It looks like part of this massive room has caved in, a pile of rock and dust present in one of the room's corners.",
            new ArrayList<>(List.of("N","S","W")), new ArrayList<>(), new ArrayList<>(List.of(darkDragon)), new ArrayList<>());
    public static Room roomL = new Room("l","This room appears to be some sort of small treasure room. The floor is covered in a mixture of old yellowed bones and old coins that you don't recognize.",
            new ArrayList<>(List.of("N")), new ArrayList<>(List.of(maul, crossbow)), new ArrayList<>(), new ArrayList<>());
    public static Room roomM = new Room("m","This room is considerably cleaner and brighter than the rest, save for the bloodstains splattered across the walls. You almost didn't notice " +
            "them because of how well they blended into the dark stone.", new ArrayList<>(List.of("N","E")), new ArrayList<>(List.of(chestM1)), new ArrayList<>(), new ArrayList<>());
    public static Room roomN = new Room("n","This room is completely bland. The only thing of note here is the large steel gate that blocks off the exit to the east. Behind the gate seem to be ascending stairs.",
            new ArrayList<>(List.of("S","E")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomO = new Room("o","", new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomP = new Room("p","The room is filled with broken pots, and the odor of tar fills the room. Upon closer inspection, some of the pots contain dark, opaque liquid.",
            new ArrayList<>(List.of("N", "E")), new ArrayList<>(List.of(chestP1)), new ArrayList<>(), new ArrayList<>());
    public static Room roomQ = new Room("q","This room is slightly damp, the air having a weird musty tinge to its odor.", new ArrayList<>(List.of("N","E", "W")), new ArrayList<>(), new ArrayList<>(List.of(kruthnik)), new ArrayList<>());
    public static Room roomR = new Room("r","This room is completely covered in moss and vines. You can barely make out the walls because of the greenery." +
            " There is a bit of shrubbery sprouting from the corners of the room.", new ArrayList<>(List.of("N", "W")), new ArrayList<>(), new ArrayList<>(List.of(twigBlight1, twigBlight2, twigBlight3)), new ArrayList<>());
    public static Room roomS = new Room("s","This room is lined with several columns that extend from the floor to the ceiling. Vines ensnare the pillars.",
            new ArrayList<>(List.of("N", "S")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(List.of(sylvie)));
    public static Room roomT = new Room("t","This room is small, covered from the floor, walls, to ceiling in an odd mural. The mural seems to depict some sort of religious scene.",
            new ArrayList<>(List.of("W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomU = new Room("u","This room resembles some sort of worn down temple. There is a slightly raised platform at the center of the room that is led to by weathered stone stairs.",
            new ArrayList<>(List.of("S","W")), new ArrayList<>(), new ArrayList<>(List.of(grick)), new ArrayList<>());
    public static Room roomV = new Room("v","This room has a very grand feel to it. There are slightly chipped and worn down marble statues at the corners of the room. " +
            "At the center of the room, upon a slightly raised stone platform, is a statue of a skeletal hand holding a balancing scale. You notice that the scales are functional. You could" +
            " try to PLACE something onto them...",
            new ArrayList<>(List.of("N","S","E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomW = new Room("w","The cobblestone flooring of the room is crumbling as the earth underneath it sprout grass through the tiles. The room is well lit by some kind of magical chandelier.",
            new ArrayList<>(List.of("S", "E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(List.of(saltine)));
    public static Room roomX = new Room("x","This room contains a number of empty cages in a variety of different states. Some are " +
            "completely broken, others are jammed shut, and others are in pristine condition.", new ArrayList<>(List.of("S")), new ArrayList<>(), new ArrayList<>(List.of(wolf)), new ArrayList<>());
    public static Room roomY = new Room("y","This room is very, very cold. You feel a shiver go down your spine as you walk through the room. A small layer of white frost" +
            " coats everything within the room.",
            new ArrayList<>(List.of("N", "W")), new ArrayList<>(), new ArrayList<>(List.of(hobgoblin1, hobgoblin2)), new ArrayList<>());
    public static Room roomZ = new Room("z","This room is less of a room as more of a very small cavern. The walls of the room are" +
            " covered in various fungi and mushrooms. Several luminous plants light up the room.", new ArrayList<>(List.of("S", "E","W")), new ArrayList<>(), new ArrayList<>(List.of(fungi1, fungi2)), new ArrayList<>());
    public static Room hall1 = new Room("hall1","The walls of the hallway are slightly crumbling.", new ArrayList<>(List.of("N", "S","W")),
            new ArrayList<>(List.of(chestH1)), new ArrayList<>(List.of(rat1, rat2)), new ArrayList<>());
    public static Room hall2 = new Room("hall2","Dim lanterns light up the hallway. Slight foliage forms at the corners of the room.", new ArrayList<>(List.of("N", "S","E")),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room hall3 = new Room("hall3","The hallway is wide, with a low ceiling.", new ArrayList<>(List.of("N", "S","W")),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room hall4 = new Room("hall4","There are a variety of short and long chains hanging off of the ceiling of the hallway.", new ArrayList<>(List.of("N", "S","E")),
            new ArrayList<>(List.of(spear)), new ArrayList<>(), new ArrayList<>());
    public static Room hall5 = new Room("hall5","The walls of this hallway are complex, jutting in at seemingly random areas. It makes it only " +
            "slightly difficult to navigate through the hall.", new ArrayList<>(List.of("N", "S","E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomAA = new Room("aa","Unlike the other rooms in the dungeon, the floor of this room is completely brown dirt. A few gravestones lay here.",
            new ArrayList<>(List.of("N", "S","W")), new ArrayList<>(List.of(chestAA1)), new ArrayList<>(), new ArrayList<>());
    public static Room roomAB = new Room("ab","This room seems to be a library. There are books on the dusty shelves, but they immediately crumble under your touch.",
            new ArrayList<>(List.of("S", "E","W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomAC = new Room("ac","This room is filled with the the scattered bloodied remains of... some animals, probably. It is hard to tell because of how" +
            " much damage and tearing apart has been done to the bodies.", new ArrayList<>(List.of("E")),
            new ArrayList<>(), new ArrayList<>(), new ArrayList<>(List.of(henry)));
    public static Room roomAD = new Room("ad","This room contains a lot of clocks, all powered by some kind of magical energy.", new ArrayList<>(List.of("N", "W")), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    public static Room roomAE = new Room("ae","This is less of a room and more of a small cavern. The ceiling of the cavern is speckled with stalactites, and large pieces of rock jut out of the ground.",
            new ArrayList<>(List.of("S", "E","W")), new ArrayList<>(), new ArrayList<>(List.of(lightDragon)), new ArrayList<>());
    public static Room roomAF = new Room("af","The floor of this room is lined with neatly chiseled stone bricks, but its age is betrayed by the cracks in the wall.",
            new ArrayList<>(List.of("E")), new ArrayList<>(List.of(chestAF1, chestAF2)), new ArrayList<>(), new ArrayList<>());

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
