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
        else if (name.equals("Dain"))
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

    public static void openMap(Room room)
    {
        if (room == ObjectFactory.roomA)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/a.png"));
        }
        else if (room == ObjectFactory.roomAA)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/aa.png"));
        }
        else if (room == ObjectFactory.roomAB)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/ab.png"));
        }
        else if (room == ObjectFactory.roomAC)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/ac.png"));
        }
        else if (room == ObjectFactory.roomAD)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/ad.png"));
        }
        else if (room == ObjectFactory.roomAE)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/ae.png"));
        }
        else if (room == ObjectFactory.roomAF)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/af.png"));
        }
        else if (room == ObjectFactory.roomB)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/b.png"));
        }
        else if (room == ObjectFactory.roomC)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/c.png"));
        }
        else if (room == ObjectFactory.roomD)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/d.png"));
        }
        else if (room == ObjectFactory.roomE)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/e.png"));
        }
        else if (room == ObjectFactory.roomF)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/f.png"));
        }
        else if (room == ObjectFactory.roomG)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/g.png"));
        }
        else if (room == ObjectFactory.roomH)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/h.png"));
        }
        else if (room == ObjectFactory.hall1)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/hall1.png"));
        }
        else if (room == ObjectFactory.hall2)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/hall2.png"));
        }
        else if (room == ObjectFactory.hall3)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/hall3.png"));
        }
        else if (room == ObjectFactory.hall4)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/hall4.png"));
        }
        else if (room == ObjectFactory.hall5)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/hall5.png"));
        }
        else if (room == ObjectFactory.roomI)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/i.png"));
        }
        else if (room == ObjectFactory.roomJ)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/j.png"));
        }
        else if (room == ObjectFactory.roomK)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/k.png"));
        }
        else if (room == ObjectFactory.roomL)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/l.png"));
        }
        else if (room == ObjectFactory.roomM)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/m.png"));
        }
        else if (room == ObjectFactory.roomN)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/n.png"));
        }
        else if (room == ObjectFactory.roomO)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/o.png"));
        }
        else if (room == ObjectFactory.roomP)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/p.png"));
        }
        else if (room == ObjectFactory.roomQ)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/q.png"));
        }
        else if (room == ObjectFactory.roomR)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/r.png"));
        }
        else if (room == ObjectFactory.roomS)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/s.png"));
        }
        else if (room == ObjectFactory.roomT)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/t.png"));
        }
        else if (room == ObjectFactory.roomU)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/u.png"));
        }
        else if (room == ObjectFactory.roomV)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/v.png"));
        }
        else if (room == ObjectFactory.roomW)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/w.png"));
        }
        else if (room == ObjectFactory.roomX)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/x.png"));
        }
        else if (room == ObjectFactory.roomY)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/y.png"));
        }
        else if (room == ObjectFactory.roomZ)
        {
            MainPanel.updatePanel2(new ImageIcon("mapImg/z.png"));
        }
        else
        {
            System.out.println("error retrieving map image!!");
        }
    }
}
