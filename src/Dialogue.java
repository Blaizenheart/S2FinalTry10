import java.util.Arrays;

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
                if (!flags[0]) // NOT MET DAIN YET
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
                            "\n\"Hai friend!\" Henry enthusiastically grins." +
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
                    if (Party.getParty().contains(ObjectFactory.everest))
                    {
                        MainPanel.updatePanel("With some difficulty, Saltine eventually spots the small white-haired halfling standing behind you." +
                                "\n\"Hi Everest!!\" Saltine chirps. \"\"");
                    }
                    if (Party.getParty().contains(ObjectFactory.sylvie))
                    {
                        MainPanel.updatePanel("Sylvie stands to your left. She greets Saltine with a smile. \"Hi Saltine!\"" +
                                "\nSaltine responds enthusiastically. \"HELLOO SYLVIEE!!\"");
                    }
                    currentDialogue = 9;
                    MainPanel.updatePanel("\n1) Will you join us?" +
                           "\n2) What are you selling?");
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
                    ObjectFactory.player.setName(input.substring(0,1).toUpperCase() + input.substring(1));
                    MainPanel.updatePanel("And for how you got here... you still have no clue. What you do" +
                            " know now, is that you should probably find a way out of this place..." +
                            "\nYou take a moment to look around.\n");
                    inDialogue = false;
                    intro = false;
                    MainPanel.updatePanel(Main.currentRoom.toString());
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
                        MainPanel.updatePanel("\"Henry's Henry,\" he responded simply.");
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
                        MainPanel.updatePanel("\"U-Uh...\"" +
                                "\nHe scrunches his eyebrows and looks away. \"There's nothing that special about me. I am..." +
                                " just a doctor.\"");
                        waitInput = true;
                    }
                    break;

                case 0: // DAIN FIRST DIALOGUE
                    if (input.equals("1"))
                    {
                        MainPanel.updatePanel("Dain joins the party!");
                        Party.addMember(ObjectFactory.dain);
                        Main.currentRoom.removePerson(ObjectFactory.dain);
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
                        Main.currentRoom.removePerson(ObjectFactory.dain);
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
                        Main.currentRoom.removePerson(ObjectFactory.everest);
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
                        Main.currentRoom.removePerson(ObjectFactory.everest);
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
                                "\nDain looks away. \"I'm glad you're safe. I mean-- Ugh, whatever...\"");
                        MainPanel.updatePanel("Everest joins the party!");
                        Party.addMember(ObjectFactory.everest);
                        Main.currentRoom.removePerson(ObjectFactory.everest);
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
                default:
                    break;
                case 8: // FIRST TIME TALKING TO HENRY
                    if (input.equals("1"))
                    {
                        MainPanel.updatePanel("Henry grins. \"Okay, Henry go with you. Yay! :D\"");
                        Party.addMember(ObjectFactory.henry);
                        Main.currentRoom.removePerson(ObjectFactory.henry);
                        flags[7] = true;
                        flags[9] = true;
                        waitInput = true;
                    }
                    else if (input.equals("2"))
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
                    }
                    else if (input.equals("2"))
                    {
                        MainPanel.updatePanel("");
                        flags[8] = true;
                        waitInput = true;
                    }
            }
            if (waitInput)
            {
                MainPanel.updatePanel("(Press ENTER to leave)");
            }
        }
    }
}
