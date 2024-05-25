import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainPanel extends JPanel
{
    public static MainPanel main = new MainPanel(); // panel

    // all the components... sorry, there were some budget cuts
    private static JTextField inputField;
    private static JTextArea outputArea;
    private static JTextPane xtraArea;
    private static JScrollPane scroll, scroll2;
    private static Border padding;
    public static Font font;
    public static Color dark = new Color(CustomColors.black.getRGB());
    public static Color light = new Color(CustomColors.white.getRGB());
    public static String palette;
    private static Timer timer;

    public MainPanel()
    {
        try
        {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("jogan-soft-font/JoganSoft-rgwKy.otf")).deriveFont(20f);
        }
        catch (Exception e)
        {
            font = new Font("Bodoni MT", Font.PLAIN, 16);
        }

        padding = BorderFactory.createEmptyBorder(10, 10, 10, 10); // padding for input/output area
        // input field
        inputField = new JTextField (5);
        inputField.setBorder(padding);
        inputField.setFont(font);
        inputField.setHorizontalAlignment(JTextField.CENTER);
        inputField.setToolTipText ("Enter commands here. Enter HELP to get a list of commands.");

        // output field
        outputArea = new JTextArea();
        outputArea.setBorder(padding);
        outputArea.setFont(font);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setEnabled (false);

        // additional field for a different thingy
        xtraArea = new JTextPane();
        xtraArea.setBorder(padding);
        xtraArea.setFont(font);
        xtraArea.setEnabled (false);

        scroll = new JScrollPane(outputArea); // now we can scroll the output up and down
        scroll2 = new JScrollPane(xtraArea);

        setPreferredSize(new Dimension(1200, 750));
        setLayout(null);

        // adds to the main panel
        add(inputField);
        add(scroll);
        add(scroll2);

        // sets the bounds for the different components
        inputField.setBounds (0, 500, 1200, 250);
        scroll.setBounds (400, 0, 800, 500);
        scroll2.setBounds (0, 0, 400, 500);

        // ACTION LISTENERS
        // for when the player inputs commands in the text area
        inputField.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (e.getModifiers() == 0)
                {
                    if (Dialogue.inDialogue()) // in a dialogue
                    {
                            Dialogue.processCommand(inputField.getText().toLowerCase().trim());
                    }
                    else if (Battle.inBattle()) // in a battle
                    {
                        Battle.processCommand(inputField.getText().toLowerCase().trim());
                    }
                    else // free roaming
                    {
                        Game.processCommand(inputField.getText().toLowerCase().trim()); // calls method in main with the text from the input field as an argument
                    }
                    inputField.setText(""); // resets the text
                }
            }
        });
    }

    public static void updatePanel(String text) // updates the output area
    {
        outputArea.setText(outputArea.getText() + "\n" + text);
    }

    public static void typePanel(String text, Runnable callback) // types the text
    {
        String original = outputArea.getText();
        timer = new Timer(1, new ActionListener()
        {
            int index = 0;
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (index > text.length())
                {
                    timer.stop();
                    if (callback != null)
                    {
                        callback.run();
                    }
                }
                else
                {
                    outputArea.setText(original + "\n" + text.substring(0, index));
                    index++;
                }
            }
        });
        timer.start();
    }

    public static void clearPanel2() // updates the second output area
    {
        xtraArea.setText("");
    }

    public static void clearPanel() // updates the second output area
    {
        outputArea.setText("");
    }

    public static void updatePanel2(ImageIcon image) // updates the second output area
    {
        xtraArea.insertIcon(image);
    }

    public static void updatePanel2(String text) // updates the second output area
    {
        xtraArea.setText(text);
    }

    public static void updateColors()
    {
        main.setBackground(light);
        inputField.setBackground(light);
        inputField.setForeground(dark);
        outputArea.setBackground(dark);
        outputArea.setForeground(light);
        xtraArea.setBackground(light);
        xtraArea.setForeground(dark);
    }

    public static void updateColorsBattle()
    {
        // cant figure out why the jtextpane isnt setting the foreground color right so this is a workaround
        xtraArea.setBackground(dark);
    }

    public static void setPalette(String palette) // sets the "dark" and "light" colors of the palette
    {
        MainPanel.palette = palette;
        MainPanel.updatePanel("(Palette set to " + palette + ".)");
        switch(palette)
        {
            case "Grayscale":
                dark = new Color(CustomColors.black.getRGB());
                light = new Color(CustomColors.white.getRGB());
                break;
            case "Abyssal":
                dark = new Color(CustomColors.abyssal1.getRGB());
                light = new Color(CustomColors.abyssal2.getRGB());
                break;
            case "Spiral":
                dark = new Color(CustomColors.spiral1.getRGB());
                light = new Color(CustomColors.spiral2.getRGB());
                break;
            case "Flesh":
                dark = new Color(CustomColors.flesh1.getRGB());
                light = new Color(CustomColors.flesh2.getRGB());
                break;
            case "Mythos":
                dark = new Color(CustomColors.mythos1.getRGB());
                light = new Color(CustomColors.mythos2.getRGB());
                break;
            case "Skel King":
                dark = new Color(CustomColors.skelKing1.getRGB());
                light = new Color(CustomColors.skelKing2.getRGB());
                break;
            case "Tides":
                dark = new Color(CustomColors.tides1.getRGB());
                light = new Color(CustomColors.tides2.getRGB());
                break;
            case "Seaside":
                dark = new Color(CustomColors.seaside1.getRGB());
                light = new Color(CustomColors.seaside2.getRGB());
                break;
            default:
                System.out.println("Invalid palette");
                break;
        }
        System.out.println("Changed palette to " + palette);
        updateColors();
    }

}
