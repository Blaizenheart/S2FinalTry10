import javax.swing.*;

public class ImgFinder
{
    // class to find images to update the second output area
    public static void updateImage(Person person) // searches for the person's image
    {
        String name = person.getName();
        if (person == ObjectFactory.player)
        {
            switch (MainPanel.palette)
            {
                case "Abyssal" -> MainPanel.updatePanel2(new ImageIcon("img/adventurer2.png"));
                case "Spiral" -> MainPanel.updatePanel2(new ImageIcon("img/adventurer3.png"));
                case "Flesh" -> MainPanel.updatePanel2(new ImageIcon("img/adventurer4.png"));
                case "Mythos" -> MainPanel.updatePanel2(new ImageIcon("img/adventurer5.png"));
                case "Skel King" -> MainPanel.updatePanel2(new ImageIcon("img/adventurer6.png"));
                case "Tides" -> MainPanel.updatePanel2(new ImageIcon("img/adventurer7.png"));
                case "Seaside" -> MainPanel.updatePanel2(new ImageIcon("img/adventurer8.png"));
                default -> MainPanel.updatePanel2(new ImageIcon("img/adventurer.png"));
            }
        }
        if (name.equals("Dain"))
        {
            switch (MainPanel.palette)
            {
                case "Abyssal" -> MainPanel.updatePanel2(new ImageIcon("img/dain2.png"));
                case "Spiral" -> MainPanel.updatePanel2(new ImageIcon("img/dain3.png"));
                case "Flesh" -> MainPanel.updatePanel2(new ImageIcon("img/dain4.png"));
                case "Mythos" -> MainPanel.updatePanel2(new ImageIcon("img/dain5.png"));
                case "Skel King" -> MainPanel.updatePanel2(new ImageIcon("img/dain6.png"));
                case "Tides" -> MainPanel.updatePanel2(new ImageIcon("img/dain7.png"));
                case "Seaside" -> MainPanel.updatePanel2(new ImageIcon("img/dain8.png"));
                default -> MainPanel.updatePanel2(new ImageIcon("img/dain.png"));
            }
        }
        else if (name.equals("Sylvie"))
        {
            switch (MainPanel.palette)
            {
                case "Abyssal" -> MainPanel.updatePanel2(new ImageIcon("img/sylvie2.png"));
                case "Spiral" -> MainPanel.updatePanel2(new ImageIcon("img/sylvie3.png"));
                case "Flesh" -> MainPanel.updatePanel2(new ImageIcon("img/sylvie4.png"));
                case "Mythos" -> MainPanel.updatePanel2(new ImageIcon("img/sylvie5.png"));
                case "Skel King" -> MainPanel.updatePanel2(new ImageIcon("img/sylvie6.png"));
                case "Tides" -> MainPanel.updatePanel2(new ImageIcon("img/sylvie7.png"));
                case "Seaside" -> MainPanel.updatePanel2(new ImageIcon("img/sylvie8.png"));
                default -> MainPanel.updatePanel2(new ImageIcon("img/sylvie.png"));
            }
        }
        else if (name.equals("Todd"))
        {
            switch (MainPanel.palette)
            {
                case "Abyssal" -> MainPanel.updatePanel2(new ImageIcon("img/todd2.png"));
                case "Spiral" -> MainPanel.updatePanel2(new ImageIcon("img/todd3.png"));
                case "Flesh" -> MainPanel.updatePanel2(new ImageIcon("img/todd4.png"));
                case "Mythos" -> MainPanel.updatePanel2(new ImageIcon("img/todd5.png"));
                case "Skel King" -> MainPanel.updatePanel2(new ImageIcon("img/todd6.png"));
                case "Tides" -> MainPanel.updatePanel2(new ImageIcon("img/todd7.png"));
                case "Seaside" -> MainPanel.updatePanel2(new ImageIcon("img/todd8.png"));
                default -> MainPanel.updatePanel2(new ImageIcon("img/todd.png"));
            }
        }
        else if (name.equals("Saltine"))
        {
            switch (MainPanel.palette)
            {
                case "Abyssal" -> MainPanel.updatePanel2(new ImageIcon("img/saltine2.png"));
                case "Spiral" -> MainPanel.updatePanel2(new ImageIcon("img/saltine3.png"));
                case "Flesh" -> MainPanel.updatePanel2(new ImageIcon("img/saltine4.png"));
                case "Mythos" -> MainPanel.updatePanel2(new ImageIcon("img/saltine5.png"));
                case "Skel King" -> MainPanel.updatePanel2(new ImageIcon("img/saltine6.png"));
                case "Tides" -> MainPanel.updatePanel2(new ImageIcon("img/saltine7.png"));
                case "Seaside" -> MainPanel.updatePanel2(new ImageIcon("img/saltine8.png"));
                default -> MainPanel.updatePanel2(new ImageIcon("img/saltine.png"));
            }

        }
        else if (name.equals("Henry"))
        {
            switch (MainPanel.palette)
            {
                case "Abyssal" -> MainPanel.updatePanel2(new ImageIcon("img/henry2.png"));
                case "Spiral" -> MainPanel.updatePanel2(new ImageIcon("img/henry3.png"));
                case "Flesh" -> MainPanel.updatePanel2(new ImageIcon("img/henry4.png"));
                case "Mythos" -> MainPanel.updatePanel2(new ImageIcon("img/henry5.png"));
                case "Skel King" -> MainPanel.updatePanel2(new ImageIcon("img/henry6.png"));
                case "Tides" -> MainPanel.updatePanel2(new ImageIcon("img/henry7.png"));
                case "Seaside" -> MainPanel.updatePanel2(new ImageIcon("img/henry8.png"));
                default -> MainPanel.updatePanel2(new ImageIcon("img/henry.png"));
            }
        }
        else if (name.equals("Everest"))
        {
            switch (MainPanel.palette)
            {
                case "Abyssal" -> MainPanel.updatePanel2(new ImageIcon("img/everest2.png"));
                case "Spiral" -> MainPanel.updatePanel2(new ImageIcon("img/everest3.png"));
                case "Flesh" -> MainPanel.updatePanel2(new ImageIcon("img/everest4.png"));
                case "Mythos" -> MainPanel.updatePanel2(new ImageIcon("img/everest5.png"));
                case "Skel King" -> MainPanel.updatePanel2(new ImageIcon("img/everest6.png"));
                case "Tides" -> MainPanel.updatePanel2(new ImageIcon("img/everest7.png"));
                case "Seaside" -> MainPanel.updatePanel2(new ImageIcon("img/everest8.png"));
                default -> MainPanel.updatePanel2(new ImageIcon("img/everest.png"));
            }
        }
        else if (name.equals("Eden"))
        {
            switch (MainPanel.palette)
            {
                case "Abyssal" -> MainPanel.updatePanel2(new ImageIcon("img/eden2.png"));
                case "Spiral" -> MainPanel.updatePanel2(new ImageIcon("img/eden3.png"));
                case "Flesh" -> MainPanel.updatePanel2(new ImageIcon("img/eden4.png"));
                case "Mythos" -> MainPanel.updatePanel2(new ImageIcon("img/eden5.png"));
                case "Skel King" -> MainPanel.updatePanel2(new ImageIcon("img/eden6.png"));
                case "Tides" -> MainPanel.updatePanel2(new ImageIcon("img/eden7.png"));
                case "Seaside" -> MainPanel.updatePanel2(new ImageIcon("img/eden8.png"));
                default -> MainPanel.updatePanel2(new ImageIcon("img/eden.png"));
            }
        }
        else if (name.equals("Lukana"))
        {
            switch (MainPanel.palette)
            {
                case "Abyssal" -> MainPanel.updatePanel2(new ImageIcon("img/lukana2.png"));
                case "Spiral" -> MainPanel.updatePanel2(new ImageIcon("img/lukana3.png"));
                case "Flesh" -> MainPanel.updatePanel2(new ImageIcon("img/lukana4.png"));
                case "Mythos" -> MainPanel.updatePanel2(new ImageIcon("img/lukana5.png"));
                case "Skel King" -> MainPanel.updatePanel2(new ImageIcon("img/lukana6.png"));
                case "Tides" -> MainPanel.updatePanel2(new ImageIcon("img/lukana7.png"));
                case "Seaside" -> MainPanel.updatePanel2(new ImageIcon("img/lukana8.png"));
                default -> MainPanel.updatePanel2(new ImageIcon("img/lukana.png"));
            }
        }
        else
        {
            System.out.println("error retrieving image!!");
        }

    }
}
