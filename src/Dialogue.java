import com.sun.jdi.ObjectReference;
import com.sun.tools.javac.Main;

import java.util.Arrays;

import static java.lang.Integer.parseInt;

public class Dialogue
{
    // good news, you can talk to people
    // bad news-- i have to write and code all of this

    private static boolean inDialogue; // command processing will be different in dialogue and normal commands will not be avaliable
    private static boolean[] flags = new boolean[50];
    // flags correspond to certain dialogues, if flag is false then the dialogue hasn't been played yet
    // mostly meant for one time dialogues
    public static boolean knowDain = false;
    public static boolean knowSaltine = false;
    public static boolean knowSylvie = false;
    public static boolean knowTodd = false;
    public static boolean knowEverest = false;
    public static boolean knowHenry = false;
    public static boolean waitInput = false;

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

    public static void getDialogue(Room room, Entity target)
    {
        if (intro)
        {
            MainPanel.updatePanel("You suddenly awaken in an unfamiliar place, staring at the rocky ceiling of the room. " +
                    "You gather your bearings and take a moment to reflect. How did you even get here? " +
                    "\nMaybe you should start off with an easier question to answer. What's your name?");
            currentDialogue = -1000;
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
                else if (!flags[5] && room == ObjectFactory.roomF) // EVEREST TALKS ABT WINE ROOM
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
                        if (flags[9]) // saltine dialogue about dead party members has been triggered
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
                        if (!flags[9]) // not trigger yet
                        {
                            MainPanel.updatePanel("A thoughtful expression emerges on her face. \"I wonder what happened to the rest of our party." +
                                    " Despite what you might think, we had a wayyyy bigger party before we ended up here! Honestly I'm not even sure when or how" +
                                    " we got stuck down here...\"" +
                                    "\nSaltine seems to reminisce about her friends. \"We've been through a lot. We recently lost a few members of our party. There was Aris, and...\"" +
                                    "\nHer brows suddenly scrunch up, and she frowns. \"Actually... I can't... I can't remember what he looked like. But he was very... oh pretzels." +
                                    " I'm so sorry, I'll tell you about it once I can remember... \"");
                            flags[9] = true;
                            waitInput = true;
                        }
                        else if (flags[10]) // triggered saltine dialogue and dain dialogue
                        {
                            MainPanel.updatePanel("\"Oh, yes, remember where we last left off, " + ObjectFactory.player.getName() + "?\"" +
                                    " Saltine inquires. \"Well, I remembered a bit more! Dain joined around the time of the second death in the" +
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
                                "\"Oh. I forgot to tell you that I am a cleric. I have some knowledge on the gods.\"");
                        waitInput = true;
                    }
                    break;
                default:
                    break;
            }
            if (waitInput)
            {
                MainPanel.updatePanel("(Press ENTER to leave)");
            }
        }
    }
}
