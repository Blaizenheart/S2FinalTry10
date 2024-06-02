-------------------------------------------------------------------------------------------------------
# DESCRIPTION:

This is a text-based-dungeon-crawling-role-playing-game that is strongly inspired by Dungeons & Dragons
with minimal visual graphics. There is an overaching storyline to the game as well as 4 different endings 
which are entirely baesd on the player's decision in the ending scene (as well as a secret ending which
is determined by certain dialogues the player can experience), but the player is allowed to free roam 
through the dungeon taking any path that they'd like.

-------------------------------------------------------------------------------------------------------
# HOW TO PLAY:

The player is given a set of commands that they can use to interact with rooms, objects, and entities.

- tutorial: explains how the game works
- help: brings up the list of commands
- palette: lets you set the palette/theme of the game
- use/eat/consume/drink/give: use an item or give to party member to use
	> "eat pretzel"

 	> "give pretzel to	sylvie"
- attack: attacks enemies in a room
- look: prints a description of the room
- move north/south/east/west: move in that direction
	> "move north"

 	> "move e"
- map: displays the map and where you are
- talk: talks to someone in the room or in your party
	> "talk to saltine"

	> "talk"
- open: opens something
	> "open chest"
- examine: prints description of a person/item/monster
	> "examine everest"

	> "examine self"

	> "examine feral rat"
- loot/search: searches a corpse for items
	> "loot wolf corpse"
- party: check up on the people in your party
- rest: take a long rest to restore HP, MP and clear status effects
- inventory/inv/i: prints your inventory
- equip: equips a weapon from your inventory onto a specified person
	> "equip spear"

	> "equip glaive on henry"
- toggle: turns on/off passive aggressive and possibly hateful flavor text (largely unfinished, BTW)

-------------------------------------------------------------------------------------------------------
# SPECIAL FEATURES:
## Dialogue

The dialogue system allows the player to talk to one of 5 different characters that can be found in the game. 
Dialogue is initiateed through using the talk command with the character's name. 

> "talk to dain"

When talking to a character that has not been met yet (thus the player does not know the character's name),
the player can just use the talk command by itself.

> "talk"

There are certain rooms with special dialogues with certain characters. Whenever such dialogues exist,
it is indicated with the text "[!] It seems like ______ has something to say" which is also printed in the
description of the room.

>_Everest paces around the room, searching for... more alcohol, probably._

While in dialogue, the player may have the option to choose between several dialogue options. These choices
do not impact gameplay or plot, but may increase affinity with certain characters (although the affinity
system currently does not impact anything). When meeting characters for the first time, there are also
options to invite them into your party or not.

Saltine is an exception to the other characters found in the dungeon. She can be conversed with, but cannot
be added to the party. This is because she functions as a merchant. Talking to her allows the player an option
to enter a shop screen where the player can spend the gold they've obtained from defeating monsters to buy
various consumable items.

> _Saltine greets you with a cheerful wave. "I have pretzels, fresh out of the oven!"_

## Battle System

The player is allowed to initiate combat with enemies that may be present in a room. Because this game is
text-based, I couldn't really decide on how I would have enemies attack the player, so I opted to not have
them attack the player at all. If the player does not initiate combat, enemies never attack.

However, there are two boss enemies in the game that are necessary to fight in order to reach the game's
ending. This makes fighting other enemies important——there is no other way to obtain gold or XP——gold and
XP being necessary to purchase Saltine's goods and level up characters to increase their attack respectively.

In battle, the player's access to commands have been restricted to the ones applicable to being in battle.

- attack: attacks a specified target OR the first alive enemy with the active character's weapon 
- spells: brings up the active character's list of spells
- cast: casts a specified spell on a specified target or all targets if it is an AoE spell
- examine: examines enemies and prints their weaknesses/resistances/and immunities
- use: uses a specified item on a specified person——if unspecified, automatically uses it on the active character
- inventory: prints the player's inventory
- flee: there is a 50/50 chance of fleeing the battle——unless it's a boss fight

The spells, examine, and inventory commands do not take a turn.

Every weapon or spell attack has a certain attack damage type. The attack damage type may match with the 
target's weaknesses/resistances/immunities.

Weaknesses double the damage taken. Resistances halve the damage taken. Immunities negate all incoming damage.

The battle ends either when all enemies are dead (win), the player is down (loss), or the player has fled the 
battle.When the player has been downed, they will automatically enter a dialogue with Dain, who revives them 
and any other downed characters to 1 hit point. This dialogue plays regardless of whether the player knows or 
has invited Dain to the party. Dialogue is slightly different if the player has not talked to Dain before this 
point. Either way, Dain automatically joins the party if he isn't already in it.

> _"If you don't mind... I'll join you for now. It seems like you could use a doctor."_

There is technically no permanent consequences for losing a fight or party members being downed.

## Graphics

There are several graphics that appear on the left panel of the screen. There are character portraits, which
are displayed during dialogues with their respective characters or for a few seconds if the player is trying
to examine a character. There is also the map, which displays the image of the game's map when the map
command is inputted. An X marks the room the player is in.

-------------------------------------------------------------------------------------------------------
# "Walkthrough" + Ending Guide

This is less of a step-by-step walkthrough of the game, because there's no right path to reaching the
ending, but there are several things that are necessary to do that can be easily missed if the player is
not attentive enough to the clues provided in room descriptions and dialogues.
In order to leave the dungeon, the player has to place two items onto the scale in the room which has
the balancing scale AND recruit all four recuritable characters and have met and talked to Saltine.

![v](https://github.com/Blaizenheart/S2FinalTry10/assets/69056399/e1af20d2-1b51-4e54-9a3a-e00b24346530)

> place

Using this command by itself will work. The game automatically checks to see if either item is within
the player's inventory. These two items are obtained from looting the bodies of the two boss enemies.

![k](https://github.com/Blaizenheart/S2FinalTry10/assets/69056399/f515b3b1-3c6e-4535-928f-f8f0ea23ab68)

![ae](https://github.com/Blaizenheart/S2FinalTry10/assets/69056399/39dde126-2494-4d87-9fa0-45a25e7e4cd4)

After the two items have been placed on the scale, the player can head to the room with the gate to the
stairs leading out of the dungeon. To trigger the ending dialogue with the choices, the player has to
attempt to move east towards the stairs.

![n](https://github.com/Blaizenheart/S2FinalTry10/assets/69056399/91b55543-90b3-4214-a8db-084adc2ae31b)

From this point on, it is heavy dialogue and story.

> [!WARNING]
> You're entering SPOILER TERRITORY! Please please please don't look at this unless you've already tried
> the game and or have decided it is too much effort to go through with attempting to finish... Although,
> in my opinion, the game is very easy once you already know what direction to go in. I mean, you can't
> even actually die in this game! That's how easy it is!

Here are some images of the characters as a buffer zone.

### The Player
![adventurer](https://github.com/Blaizenheart/S2FinalTry10/assets/69056399/226fbef7-6c0c-4abf-8d0a-b91611cf6b0a)

### Dain
![dain7](https://github.com/Blaizenheart/S2FinalTry10/assets/69056399/fe3c154e-25ad-4b49-b089-2f130b8df5cc)
> _"Oh. Yes, I suppose things could be worse." He shifts awkwardly. "Let's keep moving."_

### Everest
![everest8](https://github.com/Blaizenheart/S2FinalTry10/assets/69056399/38ee6168-64f9-4287-831d-87927885b090)
> _"Everythings pretttty good!" He hiccups. "I could useeee another drink..!"_
 
### Saltine
![saltine5](https://github.com/Blaizenheart/S2FinalTry10/assets/69056399/a031feb2-4dd7-4016-b454-6abf8ba997ae)
> _"Everything is mostly fine! Sometimes, the rats here scamper towards me, but I've discovered that throwing scraps at them distracts them for for an entire night."_

### Sylvie
![sylvie4](https://github.com/Blaizenheart/S2FinalTry10/assets/69056399/44401a1a-5f0d-4ef0-a26f-984ed47afdf5)
> _"Oh, about me?" Sylvie repeats, pondering it over. "Well... I am a water genasi. People don't see much of my kind around very much, so I thought you might be curious about that."_

### Henry
![henry2](https://github.com/Blaizenheart/S2FinalTry10/assets/69056399/e4e21d3f-bbde-4bd9-b297-35cb558c9754)
> _"Oh. Let Henry think." He hums, before sighing. "Can't think of anything."_

## UNUSED IMAGES

I honestly wish I had time to implement all of these characters. But nooooooo, budget cuts. On a serious note,
the amount of dialogue that I would have to write would increase exponentially.

### Todd
![todd3](https://github.com/Blaizenheart/S2FinalTry10/assets/69056399/84f932e4-9868-411d-8b22-9661d3672409)

### Lukana
![lukana6](https://github.com/Blaizenheart/S2FinalTry10/assets/69056399/fe7347b5-c668-47b9-b435-10f37262ef39)

## Eden
![eden5](https://github.com/Blaizenheart/S2FinalTry10/assets/69056399/2ab0529f-d434-4036-8f43-b0e95d3e0883)

> Alright. Enough stalling. Here are the ending guides.

# ENDING GUIDES

When entering the ending dialogue scene, the party is presented with a conundrum. The button to lift the gate
which leads to the outside world works, now that the dragon scales have been placed on the balancing scale, but
they only work if someone is holding it down. Once it has been let go, the gates immediately fall back down. 
There's no other way to have it be held down——through magical means, it seems that it MUST be a person holding
it down——so the only way forwards is to choose someone to remain.

Remaining almost certainly means death. There is barely anything left in the dungeon but old bones and the
carcasses of the monsters you've slain. Any food that could be found is limited.

Anyone who remains would certainly die within a few days, or a week if they're lucky.

Three people volunteer to stay behind and hold the button.
> "I think it should be..."

(For funny responses, the player can try to type in "everest" or "sylvie")

## Ending 1: Goodbye, Pretzel Girl
> Our favorite pretzel seller.

Saltine confesses to having an uncurable, terminal illness. She had been hiding it from the party for a while.
She reasons that it would make the most sense for her to die here. She has some supplies left in her cart——so
maybe she could live off of those for a month or so——and she believes that her time is coming soon anyways.

To get this ending, the player agrees that Saltine should be the one to stay behind.

> Saltine smiles weakly. "Thank you."

> She turns to everyone else. "I... I love you guys. Thank you for everything, really. Tell the others once
> you get back to the surface that, too... and if you ever run into my family, tell them that I did my best
> to live up to their name..."

> Saltine begins to address each of them individually, tears rushing down her cheek.

> "Sylvie... thank you for being my friend. I remember all those times where we would sing together around the
> campfire. And Dain, you don't seem to realize how awesome you are! You're always taking care of people, even
> when they're not very grateful for it..." She shoots Everest a pointed look.

> "And Everest. Don't you DARE hurt Dain's feelings, okay?

> Then, Saltine gives Henry a hesitant look. Her eyes widen, as if suddenly recalling something.

> "Oh, Henry... You were such a good friend. I still have the ring you left me," Saltine whispers," fidgeting
> with the ruby ring on her left hand.

> Saltine turns back to look at you. "Well then. I'll push the button now..."

> The party solemnly ascends the stairs in silence, mourning the loss of Saltine. Sure, she wasn't dead just yet,
> but if she was to be trapped in these dungeons... it was only a matter of time until her food supply ran out.
> It was a harsh truth everyone would have to live with. Dain seems like he is barely holding everything together,
> Sylvie is clutching onto her sleeves, gaze cast downwards, Henry has a blank expression on his face, and Everest...
> well, he was drinking. As usual.

> "She was... one of the first people I considered my friend," Dain mutters, looking at no one in particular as he
> spoke. "Saltine..."

> You can't help but wonder if this was the correct decision. Was there even a right decision, to begin with?
> As soon as the five of you step into the sunlight, you notice that Henry is gone.

## Ending 2: Goodbye, My Danish Sweetheart
> That's a reference to a song, by the way.

A past full of sin and pain, Dain believes that he is the least worthy of staying alive. He suggests that he
stay behind. Perhaps his sacrifice now will help redeem him from his wrongdoings.

To get this ending, the player agrees that Dain should stay behind.

> "There you go," Dain announces. "If none of us can make a decision, it should be up to our friend here."

> Despite what he says, you have a feeling that he would've argued against you if you had suggested anyone else.
> After a few minutes of silence, Dain clears his throat. It's clear that he's been thinking of what to say to
> his friends.

> "I'm... thankful I got to know you all. But I always knew death was inevitable for me. I've just... done too much.
> It's always felt unfair that I, of all people, get to continue living..." Dain says, placing his hand over his heart.
> You aren't sure exactly what he is referring to, but you can sense there is some kind of darkness in his past.

> He turns to Everest, hesitant. "Everest, I..."

> Everest tilts his head. "What is it?"

> "N-Nothing," Dain mutters, turning towards you instead. "I will go and push the button now. If you have no other
> obligations elsewhere, please take care of them in my place..."

What a loser.

> The party solemnly ascends the stairs in silence, mourning the loss of Dain. Saltine is sobbing furiously,
> Sylvie averts her gaze everytime you try to look at her, Henry has a blank expression plastered onto his face,
> and Everest... looks rather unaffected by the loss of Dain.

> "What's wrong with you?\" Amidst her tears, Saltine suddenly lashes out at Everest.

> "Hm?\" Everest responds nonchalantly.

> "You're the worst, Everest," Saltine grits her teeth. "Don't you know that he... nevermind. You would never understand."
> You can't help but wonder if this was the correct decision. Was there even a right decision, to begin with? As soon as
> the five of you step into the sunlight, you notice that Henry is gone.

## Ending 3: Goodbye, Best Boy
> Woof.

Henry doesn't have a specific reason for sacrificing himself. It might just be in his nature—— perhaps he has done it before...
But regardless, he offers himself up.

You get this ending from choosing to have Henry stay behind. However, there is another ending that can be obtained through
choosing Henry and meeting other criteria... but that will be discussed later.

> Henry smiles at you, his eyes softening. It seems like he had come to some sort of realization. "Then it will be Henry.
> Okay. Henry will go press button now."

> Saltine grits her teeth, tears streaming down her cheek. "That's it, Henry? You're just going to accept your fate,
> just like that?"

> Everest tilts his head, staring at Henry. Then, his eyes widen a little. "Oh. The alcohol wore off... I remember now."

> He made no further attempt to elaborate.

> Henry pats Saltine gently on the head, before turning to Sylvie. "Sorry, Sylvie... you were good friend too. Henry thought
> you like candles, but Henry was wrong.

> Sylvie frowns. "What do you mean?"

> But Henry doesn't explain himself. He only turns away, reaching for the button. "You should all go now. Goodbye, friends.
> It was nice seeing you all again."

> Something about the way he spoke changed, as if he was dropping a naive facade. Instead, an understanding, wiser expression
> enveloped his rough features.

> The party solemnly ascends the stairs in silence, mourning the loss of Henry. Dain and Everest seem less affected than
> Saltine and Sylvie, who seem to have both come to some sort of realization.

> "Oh, Henry..." Sylvie sobs. "I remember the candles now——how could I have not realized it...?"

> Saltine is also crying. "The ring... he gave me... I had it on me this entire time and I also didn't realize... it's okay,
> Sylvie. I'm sure he's happy..."

> Sylvie shakes her head. "I didn't process it quick enough. If I did, I would've given him a hug or something... thank him
> for the candles, and apologize for misunderstanding him..."

> You're not completely sure what they're talking about. As soon as the five of you step into the sunlight, you feel the cool
> breeze on your face. With one final glance back at the stairs which you ascended from, you continue forth with your new
> acquaintances. But still... you can't help but feel like there was something you were missing.

## Ending 4: Sacrifice
> Honestly one of the most disappointing endings because everyone's reaction is realistic.

Did I forget to mention you can choose to sacrifice yourself too?

> Everyone turns to look at you, shocked.

> "B-But... you barely know us!" Saltine exclaims.

> Everest sighs, turning away from everyone else. "If they are willing to do it, why are we arguing with their decision?"

> "I agree with Everest," Sylvie says. She gives you an apologetic smile.

> Dain's eyebrows scrunch, but it doesn't seem like he's willing to argue against this decision.

> "Okay," Dain mutters, seeming a bit ashamed of himself for making what he probably sees as a selfish decision. "Thank you
> for everything."

> After solemn goodbyes are exchanged, with Saltine enveloping you in a big hug, you hold the button, watching as the party
> slowly ascends. Without you.

> But it was your decision afterall, wasn't it?

The main reason why this is disappointing as an ending is that they barely even know the player, which is realistic, because
this game takes place at most over a few days (if you take some long rests). The characters are thankful that you chose to
sacrifice yourself, but they don't know you that well that it has enough of an emotional response to make this ending worth it.

## Best Ending: Final Goodbye

When you choose to have Henry stay behind, points are tallied up for certain dialogue flags that acted like "clues" towards
the overarching mystery of the game.

I'm sure if people from the irl campaign this game is based on and its characters are from, would begin to suspect that
_one of these characters do not belong_. But for everyone else, there are a few clues from specific dialogues that can
raise some suspicions.

While talking about the rest of their party, Saltine begins to reminisce about her friends.

> We've been through a lot. We recently lost a few members of our party. There was Aris, and..." Her brows suddenly
> scrunch up, and she frowns. "Actually... I can't... I can't remember what he looked like. But he was very... oh pretzels."

> I'm so sorry, I'll tell you about it once I can remember..."

After triggering this dialogue with Saltine, there is a dialogue with Dain that is unlocked when asking him about himself.

> Dain regards you a bit warily, before relenting. "I guess I could tell you a little bit about myself. Well, for starters,
> I am a doctor. Mostly physical, I'm not good at mental doctoring."

> He continues. "I became acquainted with this party fairly recently. I happened to be there when one of their friends died
> of a dragon attack. Quite awful wounding, honestly. There were deep lacerations from the dragon's talons across his chest."

> Dain narrows his eyes, frowning. "I... I can't seem to remember his face."

Can you see where this is going?

When the player goes back to talk to Saltine, Saltine will have remembered some details.

> "Oh, yes, remember where we last left off?" Saltine inquires. "Well, I remembered a bit more! Dain joined around the time
> of the first death in the party."

> Saltine's expression falls. "I still can't remember his face. But I remember vaguely what he was like! Always happy and
> wholesome, although he did have this problem of eating things that frankly should not be eaten..." Saltine grimaces.

Seperate from these dialogues, which are somewhat dependent on each other, are two others.

After winning a fight against one of the bosses (both are dragons, by the way...), a dialogue with Henry will be
triggered if it hadn't already been before, and if Henry is in the party. 

> As you clean the remains of the dragon's blood off of your weapon, you notice that Henry has retreated to the
> corner of the room. He seems to be going through something: his hands are shaking and his nails dig into the
> flesh of his arms.

There are options provided to the player on what they could say to Henry (which can affect affinity with Henry and
other party members).

Lastly, there is a dialogue with Sylvie in the room with graves. When the player enters dialogue with her for the first
time in this room, they see her digging through the dirt of the graves with her bare hands. If the player chooses to
say that the dead should be respected (which does lower affinity with Sylvie), Sylvie will check the name on the grave.
It is mostly weathered away, but the letters "...ry..am" remain.

With at least three of these clues, the player can experience the secret ending by choosing Henry.

In this ending, the player comes to the realization that Henry is a ghost of some sort.

> Everyone is rather quiet, processing what you've said. Ever since they had entered the dungeon, their memories had been
> fuzzy, but it seemed to be slowly clearing up again...

> Henry suddenly laughs, breaking the silence. "Henry is a ghost, then! So... it won't hurt for Henry to return to the dead, right?"

The player can choose between saying "I don't know" and "Everything's going to be okay". This doesn't matter whatsoever tbh.

> The party solemnly ascends the stairs in silence, mourning the loss of Henry. It seems like the revelation has hit Saltine
> and Sylvie pretty hard. Everest seems to be thinking in  silence, while Dain is talking to himself about Kelemvor and the
> nature of the dungeons.

> "Oh, Henry..." Sylvie sobs. "I remember the candles now——how could I have not realized it...?"

> Saltine is also crying. "The ring... he gave me... I had it on me this entire time and I also didn't realize... it's okay,
> Sylvie. I'm sure he's happy..."

> Sylvie shakes her head. "I didn't process it quick enough. If I did, I would've given him a hug or something... thank him
> for the candles, and apologize for misunderstanding him..."

> 'Rest in peace, Henry,' you think to yourself. He seemed like a very genuine man. You wonder when exactly it was that
> he had realized he was not alive...

> As soon as the five of you step into the sunlight, you feel the cool breeze on your face.

> With one final glance back at the stairs which you ascended from, you continue forth with your new friends.

> "So, ah, Everest..." Dain began, before quickly looking away.

> "What is it, Danish?" Everest raises an eyebrow. "You look like you're about to confe——"

> "W-W-What?!? No, OF COURSE NOT," Dain stammers. "I just think you have pretty eyes!"

> "——a secret.\" Everest finishes, before staring at Dain. "Wait, what?"

> What adventures will you embark on next?

-------------------------------------------------------------------------------------------------------
After an ending has been achieved, the player can choose to go back and look at the other endings. 
I do indeed value the time of my players!!
