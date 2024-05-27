import com.sun.jdi.ObjectReference;
import com.sun.tools.javac.Main;

import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class Dialogue
{
    // good news, you can talk to people
    // bad news-- i have to write and code all of this

    private static boolean inDialogue; // command processing will be different in dialogue and normal commands will not be avaliable
    public static boolean[] flags = new boolean[50];
    // flags correspond to certain dialogues, if flag is false then the dialogue hasn't been played yet
    // mostly meant for one time dialogues
    public static boolean knowDain = false;
    public static boolean knowSaltine = false;
    public static boolean knowSylvie = false;
    public static boolean knowTodd = false;
    public static boolean knowEverest = false;
    public static boolean knowHenry = false;
    public static boolean waitInput = false;
    private static int clues = 0;

    private static int currentDialogue;

    private static boolean intro = true;
    public static boolean inDialogue()
    {
        return inDialogue;
    }

    // setters

    public static void setInDialogue(boolean state)
    {
        inDialogue = state;
    }

    public static void reset() // configures dialogueFlags array
    {
        Arrays.fill(flags, false);
    }

    // brain methods

    public static void getDialogue(Entity target)
    {
        if (intro)
        {
            MainPanel.updatePanel("You suddenly awaken in an unfamiliar place, staring at the rocky ceiling of the room. " +
                    "You gather your bearings and take a moment to reflect. How did you even get here? " +
                    "\nMaybe you should start off with an easier question to answer. What's your name?");
            currentDialogue = -1000;
        }
        else if (Game.currentRoom == ObjectFactory.roomN && (Game.placedBlack && Game.placedWhite)) // TRYING TO LEAVE AFTER GATE IS UNLOCKED
        {
            ImgFinder.updateImage(ObjectFactory.player);
            MainPanel.updatePanel("You have placed both the black and white scale onto the balancing scale in the other room. However," +
                    " the gates are not yet open. What's going on?");
            if (!(Party.getParty().contains(ObjectFactory.dain) && Party.getParty().contains(ObjectFactory.sylvie)
                    && knowSaltine && Party.getParty().contains(ObjectFactory.henry) && Party.getParty().contains(ObjectFactory.everest))) // havent recruited everyone yet
            {
                MainPanel.updatePanel("You have a feeling that you might be missing someone." +
                        " If you choose to proceed now, there may be no going back." +
                        "\n1) I'm not ready yet.");
                currentDialogue = 999;
            }
            else
            {
                MainPanel.updatePanel("1) Proceed." +
                        "\n2) I'm not ready yet.");
                currentDialogue = 1000;
            }
        }
        else
        {
            // DAIN DIALOGUES
            if (target.getName().equals("Dain"))
            {
                if (!ObjectFactory.player.isAlive()) // PLAYER IS DEAD
                {
                    MainPanel.clearPanel2();
                    ImgFinder.updateImage(ObjectFactory.dain);
                    if (knowDain)
                    {
                        MainPanel.updatePanel("You awaken to see Dain peering over you." +
                                "\n \"Oh good, you're awake. I was beginning to worry I'd lost you.\"" +
                                "\n1) Did you save me?");
                        currentDialogue = 10;
                    }
                    else // dont know dain yet
                    {
                        MainPanel.updatePanel("You awaken to see a pale man peering over you." +
                                "\n \"Oh good, you're awake. I was beginning to worry I'd lost you.\"" +
                                "\n1) Who are you?" +
                                "\n2) What happened?");
                        currentDialogue = 11;
                    }
                    ObjectFactory.player.addHp(1);
                    ObjectFactory.player.alive = true;
                }
                else if (Game.currentRoom == ObjectFactory.roomV)
                {
                    if (!flags[14]) // HAVENT TALKED TO DAIN ABOUT THE ROOM YET
                    {
                        MainPanel.updatePanel("As you look around the grand room, you notice that Dain is staring" +
                                " very analytically at the marble statues. His gaze turns towards the skeletal arm and" +
                                " the balancing scale in the middle of the room." +
                                "\n\"Hm. I believe these statues are of Kelemvor,\" Dain observes out loud. \"The skeleton arm" +
                                " with the scale of balance is his holy symbol.\"" +
                                "\n1) Who is Kelemvor?" +
                                "\n2) Why are these statues here?" +
                                "\n3) How do you know this?");
                    }
                    else
                    {
                        MainPanel.updatePanel("\"The statues of Kelemvor...\" Dain mutters." +
                                "\n1) Who is Kelemvor?" +
                                "\n2) Why are these statues here?" +
                                "\n3) How do you know this?");
                    }
                    currentDialogue = 16;

                }
                else if (!flags[0]) // NOT MET DAIN YET
                {
                    currentDialogue = 0;
                    MainPanel.updatePanel("You approach the eyepatched man." +
                            "\n\"Oh. You... are also stuck down here?\"" +
                            "\n\"My name is Dain. I was travelling with some others before I got separated from them." +
                            " They are surely around these dungeons somewhere. Could I travel with you for the time being?" +
                            " I am a doctor... surely my medical knowledge will be useful to you.\"" +
                            "\n\n1) Yes." +
                            "\n2) Not right now.");
                    knowDain = true;
                    flags[0] = true;
                }
                else if (!flags[1]) // MET DAIN AND REJECTED REQUEST TO JOIN
                {
                    currentDialogue = 1;
                    MainPanel.updatePanel("You approach Dain." +
                            "\n\"Could I travel with you for the time being?" +
                            " I am a doctor... surely my medical knowledge will be useful to you.\"" +
                            "\n\n1) Yes." +
                            "\n2) Not right now.");
                }
                else // DEFAULT DIALOGUE
                {
                    currentDialogue = -1;
                    MainPanel.updatePanel("You turn to Dain." +
                            "\n\"...?\"" +
                            "\nHe doesn't say anything. You can't tell if he's refraining from speaking " +
                            "out of distaste or because he's an awkward person." +
                            "\n1) Is everything okay?" +
                            "\n2) What's on your mind?" +
                            "\n3) Tell me about yourself.");
                }
            }
            // EVEREST DIALOGUES
            else if (target.getName().equals("Everest"))
            {
                if (!flags[2]) // MET EVEREST
                {
                    // different dialogues if you've recruited dain yet
                    if (Party.getParty().contains(ObjectFactory.dain) && !flags[3]) // DAIN IN PARTY AND YOU HAVENT ALREADY HELPED EVEREST UP
                    {
                        currentDialogue = 2;
                        MainPanel.updatePanel("You approach the small white-haired halfling." +
                                " For some reason, he is lying completely prone underneath a pile of wine barrels." +
                                "\nDain seems to recognize him, letting out a long and uncharacteristically extremely exasperated groan." +
                                "\n\"Everest, what the hells!\"" +
                                "\nDain turns towards you. \"Forgive me. That is one of my party members. He's" +
                                " not the sharpest, nor the sober...est.\"" +
                                "\n1) We should help him." +
                                "\n2) Honestly, me neither." +
                                "\n3) We should leave him.");
                        knowEverest = true;
                        flags[2] = true;
                    }
                    else if (!flags[3]) // HAVENT HELPED EVEREST UP YET
                    {
                        currentDialogue = 3;
                        MainPanel.updatePanel("You approach the small white-haired halfling." +
                                " For some reason, he is lying completely prone underneath a mount of wine barrels." +
                                "\n1) Help him up." +
                                "\n2) He can help himself up...");
                    }
                }
                else if (flags[3] && !flags[4]) // HELPED EVEREST UP, EVEREST NOT IN PARTY
                {
                    MainPanel.updatePanel("\"Oh, helloo again.\"" +
                            "\n1) Want to come along with me?" +
                            "\n2) See you around.");
                    if (Party.getParty().contains(ObjectFactory.dain))
                    {
                        currentDialogue = 6;
                    }
                    else
                    {
                        currentDialogue = 4;
                    }
                }
                else if (!flags[5] && Game.currentRoom == ObjectFactory.roomF) // EVEREST TALKS ABT WINE ROOM
                {
                    MainPanel.updatePanel("You turn towards Everest. (press ENTER to continue)");
                    currentDialogue = 5;
                }
                else // DEFAULT DIALOGUE
                {
                    currentDialogue = -2;
                    MainPanel.updatePanel("You turn to Everest." +
                            "\n\"Hallooo.\"" +
                            "\nHe reeks of alcohol, but you get the feeling this is very normal for him." +
                            "\n1) Is everything okay?" +
                            "\n2) What's on your mind?" +
                            "\n3) Tell me about yourself.");
                }
            }
            // HENRY DIALOGUES
            else if (target.getName().equals("Henry"))
            {
                if (!flags[6]) // FIRST TIME TALKING TO HENRY
                {
                    MainPanel.updatePanel("You approach the tall man. You would be lying if you said he wasn't intimidating." +
                            " With broad shoulders and visible muscles, he towers over you. You also can't ignore the fact that" +
                            " he's eating meat from one of the animal bones off of the floor. How much of the carnage was there before him?" +
                            "\n \"Oh, hallo.\" He greets, looking up from his meal. He licks his lips, before tearing off the last chunk" +
                            " of flesh on the bone. \"Sorry. Henry name is Henry. It good to see nice people in this place.\"");
                    // PARTY MEMBER RESPONSES TO "FIRST" MEETING HENRY !!!
                    if (Party.getParty().contains(ObjectFactory.dain))
                    {
                        MainPanel.updatePanel("Dain looks over at you, a slight frown on his face. " +
                                        " \"Well. It's up to you... if you want to bring him with us or not.\"");
                    }
                    if (Party.getParty().contains(ObjectFactory.everest))
                    {
                        MainPanel.updatePanel("From behind you, Everest squints half-heartedly at Henry. He tilts his head. " +
                                "\"Hm.. wait, hey... No, nevermind. I'm too drunk right now.\"");
                    }
                    if (Party.getParty().contains(ObjectFactory.sylvie))
                    {
                        MainPanel.updatePanel("Sylvie frowns. \"Have we...?\" She shakes her head. \"Weird...\"");
                    }
                    MainPanel.updatePanel("\n1) Do you want to come with us?" +
                            "\n2) Uhh, okay... well, see you around?");

                    flags[6] = true;
                    currentDialogue = 8;
                }
                else if (!flags[9]) // DEFAULT DIALOGUE WHEN NOT RECRUITED THE FIRST TIME
                {
                    MainPanel.updatePanel("\n1) Do you want to come with us?" +
                            "\n2) Uhh, okay... well, see you around?");
                    currentDialogue = 8;
                }
                else // DEFAULT DIALOGUE
                {
                    currentDialogue = -3;
                    MainPanel.updatePanel("You look over at Henry." +
                            "\n\"Hai, friend!\" Henry enthusiastically grins." +
                            "\n1) Is everything okay?" +
                            "\n2) What's on your mind?" +
                            "\n3) Tell me about yourself.");
                }
            }
            // SALTINE DIALOGUES
            else if (target.getName().equals("Saltine"))
            {
                if (!flags[8])
                {
                    MainPanel.updatePanel("You approach the cheerful blonde. She seems to have set herself up in some kind of" +
                            " vendor stall, with various uniquely shaped and flavored pretzels on display. The aroma of" +
                            " freshly made pretzels hits your nose. You wonder how she even got all of it into this place." +
                            "\n \"Hi!\" She grins, clasping her hands together. \"I thought I would never see an actual customer around these parts! My name is Saltine." +
                            " I sell all kinds of pretzels!\"");
                    knowSaltine = true;
                    if (Party.getParty().contains(ObjectFactory.dain))
                    {
                        MainPanel.updatePanel("Saltine's eyes move past you and towards the eye-patched man around the back of the room." +
                                "\n\"Omigosh, Dain??\" Saltine exclaims, her eyes lighting up. " +
                                "\n\"Hi, Saltine.\" Dain responds. Despite his dry response, there is a warm expression on his face.");
                        if (!Party.getParty().contains(ObjectFactory.everest) && !flags[2]) // no everest in party and didn't meet him yet
                        {
                            MainPanel.updatePanel("\"Where's Everest, Dain?\" Saltine inquires." +
                                    "\nDain shakes his head. \"No clue. I've been worried si-- I mean, I've been mildly worried. Hopefully, we find him soon.\"" +
                                    "\nSaltine nods. \"Yes, I'm sure he's around here somewhere! Good luck on finding him.\"");
                        }
                        else
                        {
                            MainPanel.updatePanel("Saltine spots Everest. \"Aw, and there's Everest too! Figured the two of you would be together :)\"" +
                                    "\nEverest waves. \"Hallooo.\"");
                        }
                    }
                    else if (Party.getParty().contains(ObjectFactory.everest))
                    {
                        MainPanel.updatePanel("With some difficulty, Saltine eventually spots the small white-haired halfling standing behind you." +
                                "\n\"Hi Everest!!\" Saltine chirps. \"\"");
                    }
                    if (Party.getParty().contains(ObjectFactory.sylvie))
                    {
                        MainPanel.updatePanel("Sylvie stands to your left. She greets Saltine with a smile. \"Hi Saltine!\"" +
                                "\nSaltine responds enthusiastically. \"HELLOO SYLVIEE!!\"");
                    }
                    if (Party.getParty().contains(ObjectFactory.henry))
                    {
                        MainPanel.updatePanel("Henry has been distracted by bug crawling up the wall farther back in the room. Saltine doesn't seem to" +
                                " notice his presence over her excitement from meeting you.");
                    }
                    currentDialogue = 9;
                    MainPanel.updatePanel("\n1) Will you join us?" +
                           "\n2) What are you selling?");
                }
                else
                {
                    MainPanel.updatePanel("Saltine greets you with a cheerful wave. \"I have pretzels, fresh out of the oven!\"" +
                            "\n1) Show me your wares." +
                            "\n2) Is everything okay?" +
                            "\n3) What's on your mind?" +
                            "\n4) Tell me about yourself.");
                    currentDialogue = 12;
                }
            }
            // SYLVIE DIALOGUES
            else if (target.getName().equals("Sylvie"))
            {
                if (!flags[12]) // FIRST TIME TALKING TO SYLVIE
                {
                    MainPanel.updatePanel("You approach the long, pink haired woman ." +
                            "\n\"Oh! Hey there. My name is Sylvie.\"" +
                            "\nShe has a friendly expression on her face.\"");
                    if (Party.getParty().contains(ObjectFactory.dain))
                    {
                        MainPanel.updatePanel("Her eyes flit over to the eye-patched man standing by your side. " +
                                "\"Oh, Dain! How's it going?\"" +
                                "\nDain sighs. \"What do you think?\"");
                        if (Party.getParty().contains(ObjectFactory.everest))
                        {
                            MainPanel.updatePanel("\"And you're here too, Everest!\" Sylvie exclaims." +
                                    "\n\"Yepp,\" Everest nods in greeting.");
                        }
                    }
                    else if (Party.getParty().contains(ObjectFactory.everest))
                    {
                        MainPanel.updatePanel("\"Oh, hey there Everest.\" Sylvie smiles." +
                                "\nEverest nods in greeting, shooting finger guns.");
                    }
                    if (Party.getParty().contains(ObjectFactory.henry))
                    {
                        MainPanel.updatePanel("\"Henry...?\" Sylvie frowns, her eyes squinting in confused recognition." +
                                "\nHenry waves excitedly. \"Hi Sylvie!!!\"");
                    }
                    MainPanel.updatePanel("1) Want to join us?" +
                            "\n2) We'll see you around!");
                    knowSylvie = true;
                    currentDialogue = 14;
                }
                else if (!flags[13]) // MET BUT HAVENT RECRUITED
                {
                    MainPanel.updatePanel("You approach Sylvie." +
                            "\n1) Want to join us?" +
                            "\n2) We'll see you around!");
                    currentDialogue = 14;
                }
                if (!flags[16] && Game.currentRoom == ObjectFactory.roomI)
                {
                    MainPanel.updatePanel("Sylvie shivers, carefully stepping over all of the webs." +
                            "\n\"Ugh. This is all so gross...\"" +
                            "\n1) I like spiders." +
                            "\n2) Spiders are awful.");
                    currentDialogue = 17;
                }
                else // DEFAULT SYLVIE DIALOGUE
                {
                    currentDialogue = 15;
                    MainPanel.updatePanel("You strike up a conversation with Sylvie." +
                            "\n\"Hey, " + ObjectFactory.player.getName() + "!\" Sylvie greets." +
                            "\n1) Is everything okay?" +
                            "\n2) What's on your mind?" +
                            "\n3) Tell me about yourself.");
                }
            }
        }
    }

    // for processing commands during dialogue scenes
    public static void processCommand(String input)
    {
        MainPanel.updatePanel(">" + input + "\n"); // records the user's input

        if (waitInput) // portrait does not disappear until player presses enter after finishing dialogue
        {
            inDialogue = false;
            MainPanel.clearPanel2();
            waitInput = false;
            MainPanel.updatePanel(Game.currentRoom.toString());
        }
        else
        {
            switch(currentDialogue)
            {
                case -1000: // INTRO DIALOGUE
                    MainPanel.updatePanel("Ah, yes, that was your name: " + input.toUpperCase() + ".");
                    ObjectFactory.player.setName(input.substring(0, 1).toUpperCase() + input.substring(1));
                    MainPanel.updatePanel("And for how you got here... you still have no clue. What you do" +
                            " know now, is that you should probably find a way out of this place..." +
                            "\nYou take a moment to look around.\n");
                    inDialogue = false;
                    intro = false;
                    MainPanel.updatePanel(Game.currentRoom.toString());
                    break;

                case -3: // DEFAULT HENRY DIALOGUE
                    if (input.equals("1"))
                    {
                        MainPanel.updatePanel("\"Everything is going okay!\" Henry reassures.");
                        waitInput = true;
                    }
                    else if (input.equals("2"))
                    {
                        MainPanel.updatePanel("\"Oh. Let Henry think.\"" +
                                "\nHe hums, before sighing. \"Can't think of anything.\"");
                        waitInput = true;
                    }
                    else if (input.equals("3"))
                    {
                        MainPanel.updatePanel("\"Henry's Henry,\" he responds simply.");
                        waitInput = true;
                    }
                    break;
                case -2: // DEFAULT EVEREST DIALOGUE
                    if (input.equals("1"))
                    {
                        MainPanel.updatePanel("\"Everythings pretttty good!\"" +
                                "\nHe hiccups. \"I could useeee another drink..!\"");
                        waitInput = true;
                    }
                    else if (input.equals("2"))
                    {
                        MainPanel.updatePanel("\"Mmm...\"" +
                                "\nHe pauses to think. \"I'm hoping we get outta here soooon. I miss the pub...\"" +
                                "\nIs all he thinks about seriously just alcohol?");
                        waitInput = true;
                    }
                    else if (input.equals("3"))
                    {
                        MainPanel.updatePanel("\"Ahh...\"" +
                                "\nHe looks at you cheekily. \"I like a good drink! That's alllll there is to it.\"" +
                                " Somehow, you feel like there is way more to it than that, but it would be too difficult " +
                                "to wrangle the truth out of him.");
                        waitInput = true;
                    }
                    break;

                case -1: // DEFAULT DAIN DIALOGUE
                    if (input.equals("1"))
                    {
                        MainPanel.updatePanel("\"Oh. Yes, I suppose things could be worse.\"" +
                                "\nHe shifts awkwardly. \"Let's keep moving.\"");
                        waitInput = true;
                    }
                    else if (input.equals("2"))
                    {
                        MainPanel.updatePanel("\"Well...\"" +
                                "\nHe pauses to think. \"I'm rather anxious to get out of here. That's it.\"" +
                                "\nIt seems like there might be more to it than that, but you decide not to" +
                                " press him.");
                        waitInput = true;
                    }
                    else if (input.equals("3"))
                    {
                        if (flags[15]) // saltine dialogue about dead party members has been triggered
                        {
                            MainPanel.updatePanel("Dain regards you a bit warily, before relenting. \"I guess I could tell you " +
                                    "a little bit about myself. Well, for starters, I am a doctor. Mostly physical, I'm not good at " +
                                    "mental doctoring.\"" +
                                    "\nHe continues. \"I became acquainted with this party fairly recently. I happened to be there when" +
                                    " one of their friends died of a dragon attack. Quite awful wounding, honestly. There were deep" +
                                    " lacerations from the dragon's talons across his chest.\"" +
                                    "\nDain narrows his eyes, frowning. \"I... I can't seem to remember his face.\"");
                            flags[10] = true;
                        }
                        else
                        {
                            MainPanel.updatePanel("\"U-Uh...\"" +
                                    "\nHe scrunches his eyebrows and looks away. \"There's nothing that special about me. I am..." +
                                    " just a doctor.\"");
                            waitInput = true;
                        }
                    }
                    break;

                case 0: // DAIN FIRST DIALOGUE
                    if (input.equals("1"))
                    {
                        MainPanel.updatePanel("Dain joins the party!");
                        Party.addMember(ObjectFactory.dain);
                        Game.currentRoom.removePerson(ObjectFactory.dain);
                        flags[1] = true;
                        waitInput = true;
                    }
                    else if (input.equals("2"))
                    {
                        MainPanel.updatePanel("The man looks mildly disappointed. \"Oh. Very well then. I remain around here if you change your mind.\"");
                        waitInput = true;
                    }
                    break;

                case 1: // DAIN DIALOGUE WHEN REJECTED FIRST TIME
                    if (input.equals("1"))
                    {
                        MainPanel.updatePanel("Dain joins the party!");
                        Party.addMember(ObjectFactory.dain);
                        flags[1] = true;
                        Game.currentRoom.removePerson(ObjectFactory.dain);
                        waitInput = true;
                    }
                    else if (input.equals("2"))
                    {
                        MainPanel.updatePanel("The man looks mildly disappointed. \"Oh. Very well then. I remain around here if you change your mind.\"");
                        waitInput = true;
                    }
                    break;

                case 2: // dain and everest
                    if (input.equals("1"))
                    {
                        ObjectFactory.dain.approve();
                        MainPanel.updatePanel("Dain nods. \"Indeed. Just leave it to me, " + ObjectFactory.player.getName() + "." +
                                " I'm... used to doing this.\"");
                        flags[2] = true;
                        currentDialogue = 7;
                    }
                    else if (input.equals("2"))
                    {
                        ObjectFactory.everest.approve();
                        ObjectFactory.dain.disapprove();
                        MainPanel.updatePanel("Dain frowns. \"Well, don't even think about laying your hands on any of the" +
                                " wine in this room. I already have one drunkard to worry about.\"" +
                                "\nHe carefully removes the barrels, precisely unstacking them as to not cause them all to" +
                                " collapse onto Everest.");
                        flags[2] = true;
                        currentDialogue = 7;
                    }
                    else if (input.equals("3"))
                    {
                        ObjectFactory.dain.disapprove();
                        MainPanel.updatePanel("Dain shakes his head. \"We cannot leave him. He is... slightly significant to me, I guess.\"" +
                                "\nHe seems to rethink his words. \"I mean, whatever. That's uh, I am uh... Whatever. I'm going to help him.\"" +
                                "\nHe carefully removes the barrels, precisely unstacking them as to not cause them all to" +
                                " collapse onto Everest.");
                        flags[2] = true;
                        currentDialogue = 7;
                    }
                    if (currentDialogue == 7)
                    {
                        ImgFinder.updateImage(ObjectFactory.everest);
                        MainPanel.updatePanel("\n\"U-ugh.. Ohh, hiii Danishh!!\" Everest smiles, turning to face your companion." +
                                "\nFor a second, it almost seems like Dain is about to smile, but if so, he has quickly concealed it. " +
                                "\n\"Tch. I'm glad you're safe.\"");
                        MainPanel.updatePanel("Everest joins the party!");
                        Party.addMember(ObjectFactory.everest);
                        Game.currentRoom.removePerson(ObjectFactory.everest);
                        waitInput = true;
                    }
                    break;
                case 3: // HELP EVEREST UP
                    if (input.equals("1"))
                    {
                        MainPanel.updatePanel("You try pulling off the barrels piled on top of the halfling carefully, but" +
                                " the second barrel you attempt to pull seems to trigger a chain reaction of falling barrels." +
                                "\n\"A-aH,, uerhhhhhghh whaaaa?\" The halfling groans." +
                                "\nHe tries to stand up, but topples over several times. Finally, as he stands upright, he" +
                                " looks around perplexed." +
                                "\n\"Oh, helloo.. my name is Everest. Whereee am I?\"" +
                                "\n1) No clue. Want to come along with me?" +
                                "\n2) I'll get back to you on that.");
                        knowEverest = true;
                        currentDialogue = 4;
                        flags[3] = true;
                        flags[2] = true;

                    }
                    else if (input.equals("2"))
                    {
                        MainPanel.updatePanel("You decide to leave it up to future-you to deal with the drunkard.");
                        waitInput = true;
                    }
                    break;
                case 4: // ASKING EVEREST TO JOIN
                    if (input.equals("1"))
                    {
                        MainPanel.updatePanel("Everest tilts his head. \"Ohh, uh, sure?\"");
                        MainPanel.updatePanel("Everest joins the party!");
                        Party.addMember(ObjectFactory.everest);
                        Game.currentRoom.removePerson(ObjectFactory.everest);
                        flags[4] = true;
                        waitInput = true;
                    }
                    else if (input.equals("2"))
                    {
                        MainPanel.updatePanel("Everest blinks. \"Ohh okaaay.\"");
                        waitInput = true;
                    }
                    break;
                case 5: // EVEREST TALKS ABT WINE ROOM
                    MainPanel.updatePanel("Everest paces around the room, searching for... more alcohol, probably." +
                            " Most of the wine in the barrels has been spilt to the floor, however, so there doesn't seem" +
                            " like there is any that he could consume without compromising the little dignity he has.");
                    if (Party.getParty().contains(ObjectFactory.dain))
                    {
                        MainPanel.updatePanel("\n\"Mm... maybe I could drink some off the--\"" +
                                "\n\"NO, EVEREST, DO NOT.\" Dain scowls.");
                    }
                    else
                    {
                        MainPanel.updatePanel("\"Mm... aww...\" Everest groans, visibly saddened.");
                    }
                    flags[5] = true;
                    waitInput = true;
                    break;
                case 6: // DAIN IN PARTY, EVEREST HAS BEEN HELPED UP, DID NOT ASK TO JOIN FIRST TIME
                    if (input.equals("1"))
                    {
                        MainPanel.updatePanel("Everest turns to Dain. \"Hi Danish! I didn't see you theerree!\"" +
                                "\nDain looks away. \"I'm glad you're sa-- Ugh, whatever...\"");
                        MainPanel.updatePanel("Everest joins the party!");
                        Party.addMember(ObjectFactory.everest);
                        Game.currentRoom.removePerson(ObjectFactory.everest);
                        flags[4] = true;
                        waitInput = true;
                    }
                    else if (input.equals("2"))
                    {
                        MainPanel.updatePanel("Everest blinks. \"Ohh okaaay.\"" +
                                "\nDain sighs. \"We're not bringing him with us? Actually, that might be a good idea..." +
                                " He might be safer here.\"");
                        waitInput = true;
                    }
                    break;
                case 8: // FIRST TIME TALKING TO HENRY
                    if (input.equals("1"))
                    {
                        MainPanel.updatePanel("Henry grins. \"Okay, Henry go with you. Yay! :D\"");
                        Party.addMember(ObjectFactory.henry);
                        Game.currentRoom.removePerson(ObjectFactory.henry);
                        flags[7] = true;
                        flags[9] = true;
                        waitInput = true;
                    } else if (input.equals("2"))
                    {
                        MainPanel.updatePanel("Henry grins. \"Bye bye!\"");
                        waitInput = true;
                    }
                    break;
                case 9: // FIRST TIME TALKING TO SALTINE
                    if (input.equals("1"))
                    {
                        MainPanel.updatePanel("Saltine grins widely. \"I would love to go with you! However...." +
                                " I am unfortunately occupied with manning my pretzel stand... Business is actually" +
                                " booming here! All the critters love my pretzels. I will probably remain here until you" +
                                " guys can find a way out. But if you need me, I'm selling pretzels of all kinds!\"");
                        flags[8] = true;
                        waitInput = true;
                    } else if (input.equals("2"))
                    {
                        MainPanel.updatePanel("\"I sell a wide variety of baked goods, but my specialty is pretzels.\"" +
                                "\nSaltine places a hand on her chest. \"Just talk to me whenever you want to buy something!\"");
                        flags[8] = true;
                        waitInput = true;
                    }
                    break;
                case 10: // DEATH
                    if (input.equals("1"))
                    {
                        MainPanel.updatePanel("\"Of course I did.\" He says. \"Why wouldn't I?\"");
                        if (!Party.getParty().contains(ObjectFactory.dain)) // dain isn't in party yet
                        {
                            MainPanel.updatePanel("If you don't mind... I'll join you for now. It seems like you could use a doctor.");
                            flags[1] = true;
                            ObjectFactory.roomA.removePerson(ObjectFactory.dain);
                            Party.addMember(ObjectFactory.dain);
                        }
                        waitInput = true;
                    }
                    break;
                case 11: // DEATH (but you dont know dain yet)
                    if (input.equals("1"))
                    {
                        MainPanel.updatePanel("\"I am a doctor.\" He explains. \"My name is Dain.\"");
                        if (!Party.getParty().contains(ObjectFactory.dain)) // dain isn't in party yet
                        {
                            MainPanel.updatePanel("If you don't mind... I'll join you for now. It seems like you could use a doctor.");
                            flags[1] = true;
                            ObjectFactory.roomA.removePerson(ObjectFactory.dain);
                            Party.addMember(ObjectFactory.dain);
                        }
                        flags[0] = true;
                        waitInput = true;
                    }
                    else if (input.equals("2"))
                    {
                        MainPanel.updatePanel("\"I found you here unconscious,\" he explains. \"My name is Dain, by the way. I'm a doctor.\"");
                        if (!Party.getParty().contains(ObjectFactory.dain)) // dain isn't in party yet
                        {
                            MainPanel.updatePanel("If you don't mind... I'll join you for now. It seems like you could use a doctor.");
                            flags[1] = true;
                            ObjectFactory.roomA.removePerson(ObjectFactory.dain);
                            Party.addMember(ObjectFactory.dain);
                        }
                        flags[0] = true;
                        waitInput = true;
                    }
                    knowDain = true;
                    break;
                case 12:
                    if (input.equals("1"))
                    {
                        // SHOW ME YOUR WARES, MERCHANT!
                        MainPanel.updatePanel("Saltine gasps. \"Of course!\"");
                        MainPanel.clearPanel2();
                        MainPanel.updatePanel2(ObjectFactory.printShop());
                        MainPanel.updateColorsBattle();
                        currentDialogue = 13; // enters shop dialogue
                    }
                    else if (input.equals("2"))
                    {
                        MainPanel.updatePanel("Saltine gives you a cheery smile. \"Everything is mostly fine! Sometimes, the rats" +
                                " here scamper towards me, but I've discovered that throwing scraps at them distracts them for for an entire night.\"");
                        waitInput = true;
                    }
                    else if (input.equals("3"))
                    {
                        if (!flags[15]) // not trigger yet
                        {
                            MainPanel.updatePanel("A thoughtful expression emerges on her face. \"I wonder what happened to the rest of our party." +
                                    " Despite what you might think, we had a wayyyy bigger party before we ended up here! Honestly I'm not even sure when or how" +
                                    " we got stuck down here...\"" +
                                    "\nSaltine seems to reminisce about her friends. \"We've been through a lot. We recently lost a few members of our party. There was Aris, and...\"" +
                                    "\nHer brows suddenly scrunch up, and she frowns. \"Actually... I can't... I can't remember what he looked like. But he was very... oh pretzels." +
                                    " I'm so sorry, I'll tell you about it once I can remember... \"");
                            flags[15] = true;
                            waitInput = true;
                        }
                        else if (flags[10]) // triggered saltine dialogue and dain dialogue
                        {
                            MainPanel.updatePanel("\"Oh, yes, remember where we last left off, " + ObjectFactory.player.getName() + "?\"" +
                                    " Saltine inquires. \"Well, I remembered a bit more! Dain joined around the time of the first death in the" +
                                    " party.\"" +
                                    "\n Saltine's expression falls. \"I still can't remember his face. But I remember vaguely what he was like!" +
                                    " Always happy and wholesome, although he did have this problem of eating things that frankly should not be eaten...\"" +
                                    "\nSaltine grimaces.");
                            flags[11] = true;
                            waitInput = true;
                        }
                        else
                        {
                            MainPanel.updatePanel("Saltine smiles, tilting her head. \"What's on my mind? Baking, of course!\"");
                            waitInput = true;
                        }
                    }
                    else if (input.equals("4"))
                    {
                        MainPanel.updatePanel("Saltine seems delighted that you want to know more about her." +
                                " \"Ah, okay! So, my name is Saltine. I loveee making all kinds of pastries and breads" +
                                " but pretzels are my speciality! I actually come from a whole lineage of various" +
                                " bread artisans. My uncle makes baguettes, for example...\"" +
                                "\nShe continues to rant, even after you've clearly lost interest.");
                        waitInput = true;
                    }
                    break;
                case 13: // SHOPPING AT SALTINES CART
                    boolean isNum = false;
                    try
                    {
                        isNum = true;
                    }
                    catch (Exception e)
                    {
                        System.out.println("not an int");
                    }
                    if (input.equals("0"))
                    {
                        MainPanel.updatePanel("Saltine waves. \"See you around!\"");
                        MainPanel.clearPanel2();
                        MainPanel.updateColors();
                        waitInput = true;
                    }
                    else if (input.contains("inv") || input.contains("inventory"))
                    {
                        MainPanel.updatePanel(ObjectFactory.player.printInv());
                    }
                    else if (isNum) // is a number
                    {
                        if (parseInt(input) <= ObjectFactory.saltineWares.size() && parseInt(input) > 0) // valid index
                        {
                            Item item = ObjectFactory.saltineWares.get(parseInt(input) - 1);
                            System.out.println(item.getName());
                            // checks if player has enough gold
                            if (Game.getGold() >= item.getValue())
                            {
                                MainPanel.updatePanel("You bought a " + item.getName() + "!");
                                Game.subGold(item.getValue());
                                System.out.println(Game.getGold());
                                ObjectFactory.player.addInvItem(item);
                                MainPanel.clearPanel2();
                                MainPanel.updatePanel2(ObjectFactory.printShop()); // reprints shop to have updated gold counter
                            }
                            else
                            {
                                MainPanel.updatePanel("You don't have enough gold for that!");
                            }
                        }
                        else
                        {
                            MainPanel.updatePanel("Enter a valid number!");
                        }
                    }
                    break;
                case 14:
                    if (input.equals("1"))
                    {
                        MainPanel.updatePanel("\"Of course. We'll have strength in numbers, afterall,\" Sylvie responds.");
                        MainPanel.updatePanel("Sylvie joins the party!");
                        Party.addMember(ObjectFactory.sylvie);
                        Game.currentRoom.removePerson(ObjectFactory.sylvie);
                        waitInput = true;
                        flags[12] = true;
                        flags[13] = true;

                    }
                    else if (input.equals("2"))
                    {
                        MainPanel.updatePanel("Sylvie frowns slightly, but quickly masks it with a small smile. " +
                                "\"Oh, I see. Well, don't leave me here for too long, okay?\"");
                        waitInput = true;
                        flags[12] = true;
                    }
                    break;
                case 15:
                    if (input.equals("1"))
                    {
                        MainPanel.updatePanel("Sylvie nods. \"We've gotten into worse circumstances. If you really think about it," +
                                " it could be way worse!\"");
                        waitInput = true;
                    }
                    else if (input.equals("2"))
                    {
                        MainPanel.updatePanel("Sylvie gives you a thoughtful look. \"Nothing much. I'm just thinking of a way out of here...\"");
                        waitInput = true;
                    }
                    else if (input.equals("3"))
                    {
                        MainPanel.updatePanel("\"Oh, about me?\" Sylvie repeats, pondering it over. \"Well... I am a water genasi. People don't see much of" +
                                " my kind around very much, so I thought you might be curious about that.\"");
                        waitInput = true;
                    }
                    break;
                case 16:
                    flags[14] = true;
                    if (input.equals("1"))
                    {
                        MainPanel.updatePanel("\"Kelemvor is a greater deity of the Death and Grave domains. He has several titles," +
                                " some being 'Lord of the Dead', 'Judge of the Damned', et cetera,\" Dain explains to you. " +
                                "\"Despite what you might think, he is a lawful neutral god. He believed that death was a natural" +
                                " part of life which shouldn't be feared.\"");
                        waitInput = true;
                    }
                    else if (input.equals("2"))
                    {
                        MainPanel.updatePanel("Dain ponders your question for a few minutes. \"There's no way for me to know for" +
                                " sure, but... there's always been a lot of forgotten old shrines that become ruins. Perhaps this was once" +
                                " a place of worship for Kelemvor.\"");
                        waitInput = true;
                    }
                    else if (input.equals("3"))
                    {
                        MainPanel.updatePanel("\"Hm?\" Dain stares blankly at you, before processing your question. " +
                                "\"Oh. I forgot to tell you that I am a cleric, so I have some knowledge on the gods.\"");
                        waitInput = true;
                    }
                    break;
                case 17:
                    flags[17] = true;
                    if (input.equals("1"))
                    {
                        ObjectFactory.sylvie.approve();
                        MainPanel.updatePanel("Sylvie smiles, which surprises you, given her disgusted demeanor a minute earlier" +
                                " towards the webbing. \"No, they're definitely very interesting critters. I quite like them." +
                                " However... these are new heels...\"");
                        waitInput = true;
                    }
                    else if (input.equals("2"))
                    {
                        MainPanel.updatePanel("Sylvie's eyes widen. \"Oh no, I don't dislike spiders. I just don't like the sticky" +
                                " webbing on my heels.\"");
                        waitInput = true;
                    }
                    break;
                case 999: // when not everyone has been recruited to the party
                    MainPanel.updatePanel("You decide that you aren't ready yet. You just can't shake the feeling that" +
                            " you might be missing someone...");
                    waitInput = true;
                    break;
                case 1000:
                    if (input.equals("1")) // FINAL SCENE...
                    {
                        MainPanel.clearPanel();
                        MainPanel.updatePanel("Okay then. Beware that you have entered a point of no return." +
                                "\nYou decided to look around the room, trying to understand why the gates haven't lifted." +
                                " After a few moments, you realize that there is a button near the entrance of the room that " +
                                "lifts the gate... However, once your finger leaves the button, the gate quickly descends again." +
                                " You try using a makeshift contraption to keep one of your items stuck to the button, but it" +
                                " seems like it takes a person to press it." +
                                "\nSaltine appears from the door, pushing her pretzel (and other baked goods) cart" +
                                " into the room at a leisurely pace. It seems like she's done selling her pretzels to the critters of the dungeon." +
                                "She turns to you, smiling brightly. \"OH! You found the way out?\"" +
                                "\nYour party members gather around, and you explain your findings." +
                                "\n\"Hm, I see,\" Sylvie murmurs. \"Does this mean that one of us will have to" +
                                " stay behind to hold the button...?\"" +
                                "\nEverest remains silent, but he seems to understand the implications." +
                                "\n\"W-Wha?...\" Henry's eyes widen." +
                                "\nDain frowns. \"This is... awful. I don't even want to think about having to" +
                                " make the decision on who to leave behind...\"" +
                                "\nSaltine's eyes begin to tear up a bit, and you see everyone's faces fall as" +
                                " they process what Dain has said. A decision must be made, but no one is willing to suggest someone" +
                                " else to be sacrificed.");
                        currentDialogue = 1001;
                    }
                    else if (input.equals("2")) // GOO BACKKK
                    {
                        MainPanel.updatePanel("You decide that you aren't ready yet.");
                        waitInput = true;
                    }
                    break;
                case 1001:
                    MainPanel.updatePanel("\"I can do it.\"" +
                            "\nYou look up. It seems like Dain, Saltine, and Henry had spoken in unison. They looked" +
                            " at each other, surprised." +
                            "\n\"Oh, nonono. I'm not letting either of you sacrifice yourself,\" Dain argues, gritting his teeth." +
                            " He seems upset, a contrast to his usual stoic demeanor." +
                            "\nSaltine begins to cry. \"No, please, Dain... It's... It's best if you stay alive. You're our" +
                            " doctor, after all... A-And, I...\"" +
                            "\nShe gulps, before frantic words begin to burst out of her. \"I'm sorry for hiding this from everyone! " +
                            "But I don't have very long to live! I'm... I'm sick! It's a terminal disease and it's been eating away" +
                            " at my health for years and years and I think it's just best that I be the one...\"" +
                            "\nEveryone seems shocked at the revelation. It seems that they had never discovered her secret, " +
                            "despite travelling with Saltine for so long." +
                            "\nHenry patted her head. \"Henry... Henry doesn't want to leave Saltine to die. Eyepatch man nice, too." +
                            " Henry is okay with dying... familiar feeling...\"" +
                            "\n\nIt seems like the three of them are willing to die. It might be about time that you spoke up.\n" +
                            "\nI think it should be..." +
                            "\n1) Dain." +
                            "\n2) Saltine." +
                            "\n3) Henry." +
                            "\n4) Me.");
                    currentDialogue = 1002;
                    break;
                case 1002:
                    if (input.equals("1"))
                    {
                        ImgFinder.updateImage(ObjectFactory.dain);
                        MainPanel.updatePanel("\"There you go,\" Dain announces. \"If none of us can make a decision," +
                                " it should be up to our friend here.\"" +
                                "\nDespite what he says, you have a feeling that he would've argued against you if you" +
                                " had suggested anyone else." +
                                "\nAfter a few minutes of silence, Dain clears his throat. It's clear that he's been" +
                                " thinking of what to say to his friends. " +
                                "\n\"I'm... thankful I got to know you all. But I always knew death was inevitable for me." +
                                " I've just... done too much. It's always felt unfair that I, of all people, get to continue" +
                                " living...\" Dain says, placing his hand over his heart. You aren't sure exactly what he" +
                                " is referring to, but you can sense there is some kind of darkness in his past." +
                                "\nHe turns to Everest, hesitant. \"Everest, I...\"" +
                                "\nEverest tilts his head. \"What is it?\"" +
                                "\n\n\"N-Nothing,\" Dain mutters, turning towards you instead. \"I will go and push the button now." +
                                " If you have no other obligations elsewhere, please take care of them in my place...\"");
                        currentDialogue = 1005;
                    }
                    else if (input.equals("2"))
                    {
                        ImgFinder.updateImage(ObjectFactory.saltine);
                        MainPanel.updatePanel("Saltine smiles weakly. \"Thank you, " + ObjectFactory.player.getName() + "." +
                                "\nShe turns to everyone else. \"I... I love you guys. Thank you for everything, really. Tell the" +
                                " others once you get back to the surface that, too... and if you ever run into my family, tell them" +
                                " that I did my best to live up to their name...\"" +
                                "\nSaltine begins to address each of them individually, tears rushing down her cheek. " +
                                "\"Sylvie... thank you for being my friend. I remember all those times where we would sing together" +
                                " around the campfire. And Dain, you don't seem to realize how awesome you are! You're always" +
                                " taking care of people, even when they're not very grateful for it...\" She shoots Everest a pointed look." +
                                "\n\"And Everest. Don't you DARE hurt Dain's feelings, okay?\"" +
                                "\nThen, Saltine gives Henry a hesitant look. Her eyes widen, as if suddenly recalling something." +
                                "\"Oh, Henry... You were such a good friend. I still have the ring you left me,\" Saltine whispers," +
                                " fidgeting with the ruby ring on her left hand." +
                                "\nSaltine turns back to look at you. \"Well then. I'll push the button now...\"");
                        currentDialogue = 1004;
                    }
                    else if (input.equals("3"))
                    {
                        ImgFinder.updateImage(ObjectFactory.henry);
                        // tallies up all the clues the player has seen
                        if (flags[9])
                        {
                            clues++;
                        }
                        if (flags[10])
                        {
                            clues++;
                        }
                        if (flags[11])
                        {
                            clues++;
                        }
                        if (clues >= 2) // 66% or higher
                        {
                            // THE BIG TWIST
                            MainPanel.updatePanel("You've realized something had been off about him this entire time." +
                                    " It was a bit difficult to place, since you weren't ever part of their party prior to" +
                                    " ending up in these dungeons, but..." +
                                    "\n\"Henry... you're not alive, are you?\"" +
                                    "\nEveryone turns to stare at you, your comment having taken them all off guard." +
                                    "\nHenry tilts his head at you. \"What are you talking about, " + ObjectFactory.player.getName() + "?" +
                                    "\nYou clear your throat, mentally preparing your defense. \"You guys lost a few party members recently," +
                                    " didn't you?\"" +
                                    "\nSaltine nods, remembering what she had told you. \"That's... that's right. We lost three people in total." +
                                    " Aris, Moria... and...\"" +
                                    "\nShe frowned, her eyes widening. \"I still can't remember.\"" +
                                    "\n\"Yes. It appears that this place has some kind of magical effect that has caused" +
                                    " all of you to have blurry memories,\" you point out. \"That's why, when Henry" +
                                    " joined the party, all of you seemed a bit perplexed, but you ultimately shrugged it off." +
                                    " HOWEVER, Dain was an exception.\"" +
                                    "\nDain turned to look at Henry, and then back at you. \"Correct. I vaguely recalled his" +
                                    " appearance, but I don't believe I knew him prior to all of this.\"" +
                                    "\nSaltine gasped, clasping her hand over her mouth. \"And... Dain joined around the time of" +
                                    "the first death. I remember telling you that, " + ObjectFactory.player.getName() + "!" +
                                    "\n\"So he wouldn't have known Henry... but then why did he recognize his appearance?\" Sylvie interjects, folding her arms." +
                                    "\n\n1) Because he had seen Henry from his past." +
                                    "\n2) Because he was confused. He doesn't actually remember Henry's appearance." +
                                    "\n3) Because he had performed the autopsy.");
                            currentDialogue = 1003;
                        }
                        else
                        {
                            MainPanel.updatePanel("Henry smiles at you, his eyes softening. It seems like he had come to some sort of" +
                                    " realization. \"Then it will be Henry. Okay. Henry will go press " +
                                    "button now.\"" +
                                    "\nSaltine grits her teeth, tears streaming down her cheek. \"That's it, Henry? You're just" +
                                    " going to accept your fate, just like that?\"" +
                                    "\nEverest tilts his head, staring at Henry. Then, his eyes widen a little. \"Oh." +
                                    " The alcohol wore off... I remember now.\"" +
                                    "\nHe made no further attempt to elaborate." +
                                    "\nHenry pats Saltine gently on the head, before turning to Sylvie. \"Sorry, Sylvie... you" +
                                    " were good friend too. Henry thought you like candles, but Henry was wrong.\"" +
                                    "\nSylvie frowned. \"What do you mean?\"" +
                                    "\nBut Henry doesn't explain himself. He only turns away, reaching for the button. " +
                                    "\"You should all go now. Goodbye, friends. It was nice seeing you all again.\"" +
                                    "\nSomething about the way he spoke changed, as if he was dropping his naive facade. Instead," +
                                    " an understanding, wiser expression enveloped his rough features.");
                            currentDialogue = 1006;
                        }
                    }
                    else if (input.equals("4"))
                    {
                        ImgFinder.updateImage(ObjectFactory.player);
                        MainPanel.updatePanel("Everyone turns to look at you, shocked." +
                                "\n\"B-But... you barely know us, " + ObjectFactory.player.getName() + "!\" Saltine exclaims." +
                                "\nEverest sighs, turning away from everyone else. \"If they are willing to do it, why are" +
                                " we arguing with their decision?\"" +
                                "\n\"I agree with Everest,\" Sylvie says. She gives you an apologetic smile." +
                                "\nDain's eyebrows scrunch, but it doesn't seem like he's willing to argue against this" +
                                " decision." +
                                "\n\"Okay,\" Dain mutters, seeming a bit ashamed of himself for making what he probably" +
                                " sees as a selfish decision. \"Thank you for everything, " + ObjectFactory.player.getName() + "." +
                                "\nAfter solemn goodbyes are exchanged, with Saltine enveloping you in a big hug," +
                                " you hold the button, watching as the party slowly descends. Without you." +
                                "\nBut it was your decision afterall, wasn't it?" +
                                "\nHow could you blame them?" +
                                "\n\nENDING 4: Sacrifice" +
                                "\n\n1) See other endings." +
                                "\n2) Exit the game.");
                        currentDialogue = 1008;
                    }
                    // gag options
                    else if (input.equalsIgnoreCase("everest"))
                    {
                        MainPanel.updatePanel("Everest laughs, as if you were suggesting a reallllyyy funny joke." +
                                " \"That's funny, " + ObjectFactory.player.getName() + "!\"" +
                                "\nDain frowns. \"Let's not sacrifice Everest. I lo... nevermind. Just no.\"");
                    }
                    else if (input.equalsIgnoreCase("sylvie"))
                    {
                        MainPanel.updatePanel("Sylvie gives you an incredulous stare. \"No way! I'm... I've survived this far." +
                                " I don't want to leave anyone else behind, but I'm not giving up here!\"");
                    }
                    break;
                case 1003: // HENRY PATH, QUESTION
                    if (input.equals("1"))
                    {
                        MainPanel.updatePanel("No, that doesn't seem correct.");
                    }
                    else if (input.equals("2"))
                    {
                        MainPanel.updatePanel("No, that doesn't seem correct.");
                    }
                    else if (input.equals("3"))
                    {
                        MainPanel.updatePanel("Yes! That seems right." +
                                "\"Dain recognized his appearance because he was the one to perform Henry's autopsy,\"" +
                                " you explain." +
                                "Everyone is rather quiet, processing what you've said. Ever since they had entered the " +
                                "dungeon, their memories had been fuzzy, but it seemed to be slowly clearing up again..." +
                                "\nHenry suddenly laughs, breaking the silence. \"Henry is a ghost, then! So... it won't" +
                                " hurt for Henry to return to the dead, right?\"" +
                                "\n1) Everything's going to be okay." +
                                "\n2) I don't know.");
                        currentDialogue = 1007;
                    }
                    break;
                case 1004:
                    MainPanel.clearPanel2();
                    MainPanel.updatePanel("The party solemnly ascends the stairs in silence, mourning the loss of Saltine." +
                            " Sure, she wasn't dead just yet, but if she was to be trapped in these dungeons... it was only a" +
                            " matter of time until her food supply ran out. It was a harsh truth everyone would have to live with." +
                            " Dain seems like he is barely holding everything together, Sylvie is clutching onto her sleeves," +
                            " gaze cast downwards, Henry has a blank expression on his face, and Everest... well, he was drinking. As usual." +
                            "\n\"She was... one of the first people I considered my friend,\" Dain mutters, looking at no one in particular as he" +
                            " spoke. \"Saltine...\"" +
                            "\nYou can't help but wonder if this was the correct decision. Was there even a right decision, to" +
                            " begin with?" +
                            "\nAs soon as the five of you step into the sunlight, you notice that Henry is gone." +
                            "\n\nENDING 1: Goodbye, Pretzel Girl" +
                            "\n\n1) See other endings." +
                            "\n2) Exit the game.");
                    currentDialogue = 1008;
                    break;
                case 1005:
                    MainPanel.clearPanel2();
                    MainPanel.updatePanel("The party solemnly ascends the stairs in silence, mourning the loss of Dain." +
                            " Saltine is sobbing furiously, Sylvie averts her gaze everytime you try to look at her, Henry has" +
                            " a blank expression plastered onto his face, and Everest... looks rather unaffected by the loss of Dain." +
                            "\n\"What's wrong with you?\" Amidst her tears, Saltine suddenly lashes out at Everest." +
                            "\n\"Hm?\" Everest responds nonchalantly." +
                            "\n\"You're the worst, Everest,\" Saltine grits her teeth. \"Don't you know that he... nevermind. You would never understand.\"" +
                            "\nYou can't help but wonder if this was the correct decision. Was there even a right decision, to" +
                            " begin with?" +
                            "\nAs soon as the five of you step into the sunlight, you notice that Henry is gone." +
                            "\n\nENDING 2: Goodbye, My Danish Sweetheart" +
                            "\n\n1) See other endings." +
                            "\n2) Exit the game.");
                    currentDialogue = 1008;
                    break;
                case 1006:
                    MainPanel.clearPanel2();
                    MainPanel.updatePanel("The party solemnly ascends the stairs in silence, mourning the loss of Henry." +
                            " Dain and Everest seem less affected than Saltine and Sylvie, who seem to have both come to some" +
                            " sort of realization." +
                            "\n\"Oh, Henry...\" Sylvie sobs. \"I remember the candles now——how could I have not realized it...?\"" +
                            "\nSaltine is also crying. \"The ring... he gave me... I had it on me this entire time and I also" +
                            " didn't realize... it's okay, Sylvie. I'm sure he's happy...\"" +
                            "\nSylvie shakes her head. \"I didn't process it quick enough. If I did, I would've given him a hug or" +
                            " something... thank him for the candles, and apologize for misunderstanding him...\"" +
                            "\nYou're not completely sure what they're talking about." +
                            "\nAs soon as the five of you step into the sunlight, you feel the cool breeze on your face." +
                            " With one final glance back at the stairs which you ascended from, you continue forth with your new acquaintances." +
                            "\nBut still... you can't help but feel like there was something you were missing." +
                            "\n\nENDING 3: Goodbye, Best Boy" +
                            "\n\n1) See other endings. (Other Henry ending has been unlocked as well)" +
                            "\n2) Exit the game.");
                    clues = 3;
                    currentDialogue = 1008;
                    break;
                case 1007:
                    MainPanel.clearPanel2();
                    MainPanel.updatePanel("The party solemnly ascends the stairs in silence, mourning the loss of Henry." +
                            " It seems like the revelation has hit Saltine and Sylvie pretty hard. Everest seems to be thinking in" +
                            " silence, while Dain is talking to himself about Kelemvor and the nature of the dungeons." +
                            "\n\"Oh, Henry...\" Sylvie sobs. \"I remember the candles now——how could I have not realized it...?\"" +
                            "\nSaltine is also crying. \"The ring... he gave me... I had it on me this entire time and I also" +
                            " didn't realize... it's okay, Sylvie. I'm sure he's happy...\"" +
                            "\nSylvie shakes her head. \"I didn't process it quick enough. If I did, I would've given him a hug or" +
                            " something... thank him for the candles, and apologize for misunderstanding him...\"" +
                            "\n'Rest in peace, Henry,' you think to yourself. He seemed like a very genuine man. You wonder" +
                            " when exactly it was that he had realized he was not alive..." +
                            "\nAs soon as the five of you step into the sunlight, you feel the cool breeze on your face." +
                            " With one final glance back at the stairs which you ascended from, you continue forth with your new friends." +
                            "\n\n\"So, ah, Everest...\" Dain began, before quickly looking away." +
                            "\n\"What is it, Danish?\" Everest raises an eyebrow. \"You look like you're about to confe——\"" +
                            "\n\"W-W-What?!? No, OF COURSE NOT,\" Dain stammers. \"I just think you have pretty eyes!\"" +
                            "\n\"——a secret.\" Everest finishes, before staring at Dain. \"Wait, what?\"" +
                            "\n\nWhat adventures will you embark on next?" +
                            "\n\nBEST ENDING: Final Goodbye" +
                            "\n\n1) See other endings. (Other Henry ending has been unlocked as well)" +
                            "\n2) Exit the game.");
                    clues = 1;
                    currentDialogue = 1008;
                    break;
                case 1008: // OPTION TO SEE OTHER ENDINGS OR EXIT
                    MainPanel.clearPanel2();
                    if (input.equals("1")) // SEE OTHER ENDINGS
                    {
                        currentDialogue = 1001;
                    }
                    else if (input.equals("2")) // EXIT GAME
                    {
                        Game.frame.dispose();
                        System.exit(0);
                    }
                    break;
                default:
                    break;
            }
            if (waitInput)
            {
                MainPanel.updatePanel("(Press ENTER)");
            }
        }
    }
}
