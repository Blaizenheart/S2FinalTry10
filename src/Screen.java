import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen // screens for different graphics
{
    public static JPanel screen1;
    private static int step = 0;
    private static Timer timer;

    // for death screen
    static ImageIcon death1 = new ImageIcon("screenImg/deathScreen.png");
    static ImageIcon death2 = new ImageIcon("screenImg/deathScreenTalk1.png");
    static ImageIcon death3 = new ImageIcon("screenImg/deathScreenTalk2.png");
    static ImageIcon black = new ImageIcon("screenImg/black.png");
    static JLabel label1 = new JLabel(black);

    // for bad end 1 screen
    static ImageIcon frame1 = new ImageIcon("screenImg/badEnd1/1.png");
    static ImageIcon frame2 = new ImageIcon("screenImg/badEnd1/2.png");
    static ImageIcon frame3 = new ImageIcon("screenImg/badEnd1/3.png");
    static ImageIcon frame4 = new ImageIcon("screenImg/badEnd1/4.png");
    static ImageIcon frame5 = new ImageIcon("screenImg/badEnd1/5.png");
    static ImageIcon frame6 = new ImageIcon("screenImg/badEnd1/6.png");
    static ImageIcon frame7 = new ImageIcon("screenImg/badEnd1/7.png");
    static ImageIcon frame8 = new ImageIcon("screenImg/badEnd1/8.png");
    static ImageIcon frame9 = new ImageIcon("screenImg/badEnd1/9.png");
    static ImageIcon frame10 = new ImageIcon("screenImg/badEnd1/10.png");
    static ImageIcon frame11 = new ImageIcon("screenImg/badEnd1/11.png");
    static ImageIcon frame12 = new ImageIcon("screenImg/badEnd1/12.png");
    static ImageIcon frame13 = new ImageIcon("screenImg/badEnd1/13.png");
    static ImageIcon frame14 = new ImageIcon("screenImg/badEnd1/14.png");
    static JLabel label2 = new JLabel(frame1);

    public static JPanel getScreen(int num)
    {
        screen1 = new JPanel();
        switch(num)
        {
            case 1:
                screen1.add(label1);
                break;
            case 2:
                screen1.add(label2);
                break;
            default:
                break;
        }
        return screen1;
    }

    public static void playGraphic1()
    {
        timer = new Timer(3000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                switch(step)
                {
                    case 0:
                        label1.setIcon(death1);
                        break;
                    case 1:
                        label1.setIcon(death2);
                        break;
                    case 2:
                        label1.setIcon(death3);
                        break;
                    case 3:
                        label1.setIcon(death1);
                        break;
                    default:
                        break;
                }
                step++;
                if (step > 3)
                {
                    timer.stop();
                    Battle.endBattle(false);
                    Game.openMainPanel();
                }
            }
        });
        timer.start();
        label1.setIcon(black); // resets
        step = 0;
        MainPanel.updatePanel(Game.currentRoom.toString());
    }

    public static void playBadEnd1()
    {
        timer = new Timer(2000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                switch(step)
                {
                    case 0:
                        label1.setIcon(frame2);
                        break;
                    case 1:
                        label1.setIcon(frame3);
                        break;
                    case 2:
                        label1.setIcon(frame4);
                        break;
                    case 3:
                        label1.setIcon(frame5);
                        break;
                    case 4:
                        label1.setIcon(frame6);
                        break;
                    case 5:
                        label1.setIcon(frame7);
                        break;
                    case 6:
                        label1.setIcon(frame8);
                        break;
                    case 7:
                        label1.setIcon(frame9);
                        break;
                    case 8:
                        label1.setIcon(frame10);
                        break;
                    case 9:
                        label1.setIcon(frame11);
                        break;
                    case 10:
                        label1.setIcon(frame12);
                        break;
                    case 11:
                        label1.setIcon(frame13);
                        break;
                    case 12:
                        label1.setIcon(frame14);
                        break;
                    case 13:
                        label1.setIcon(black);
                        break;
                    default:
                        break;
                }
                step++;
                if (step > 13)
                {
                    timer.stop();
                    Game.openMainPanel();
                }
            }
        });
        timer.start();
        label2.setIcon(black); // resets
        step = 0;
        MainPanel.updatePanel(Game.currentRoom.toString());
    }
}