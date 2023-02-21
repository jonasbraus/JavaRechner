package GUI;

import Calculator.Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.Objects;

public class MainWindow extends JFrame
{
    private JTextPane history = new JTextPane();
    private JTextField display;
    private JButton[][] buttons = new JButton[4][4];
    private JButton deleteButton = new JButton();
    private Font font = new Font("Arial", 0, 25);

    private JButton[] operatorButtons = new JButton[4];

    private JButton solve = new JButton();
    private JButton negative = new JButton("-");

    private JButton backButton = new JButton("<<");

    private JPanel panel = new JPanel();

    public void loadData()
    {
        try
        {
            File f = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Save.jcc");

            if(!f.exists())
            {
                f.createNewFile();
            }

            BufferedReader reader = new BufferedReader(new FileReader(f));

            String s = null;
            while((s = reader.readLine()) != null)
            {
                history.setText(history.getText() + s + "\n");
            }

            reader.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void saveData()
    {
        try
        {
            File f = new File("C:\\Users\\" + System.getProperty("user.name") + "\\Save.jcc");
            PrintWriter writer = new PrintWriter(new FileWriter(f));
            writer.write(history.getText());
            writer.flush();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void createWindow()
    {
        setTitle("Calculator");
        setResizable(false);
        getContentPane().setPreferredSize(new Dimension(370, 600));
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(229, 192, 184));

        display = new JTextField();
        display.setBounds(10, 10, 350, 40);
        display.setBackground(new Color(243, 224, 221));
        display.setFont(font);
        display.setBorder(null);
        display.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    String temp = history.getText();
                    try
                    {
                        float value = new Calculator().calculate(display.getText());
                        history.setText(display.getText());
                        history.setText(history.getText() + " = " + value);
                        display.setText(value + "");
                        history.setText(history.getText() + "\n" + temp);
                        saveData();
                    }
                    catch(Exception ex)
                    {
                        display.setText("Invalid!");
                    }

                }
            }
        });
        history.setBounds(10, 270, 350, 320);
        add(history);
        history.setBackground(null);
        history.setFont(font);
        history.setEditable(false);

        int count = 0;

        for (int x = 3; x >= 0; x--)
        {
            for (int y = 0; y < 3; y++)
            {

                if(x == 3 && y == 1 || x < 3)
                {
                    JButton temp = buttons[y][x] = new JButton();
                    temp.setBounds(10 + 50 * y, 60 + 50 * x, 50, 50);
                    add(temp);
                    temp.setText(count + "");
                    temp.setFont(font);
                    temp.setBackground(new Color(243, 224, 221));
                    temp.setBorder(null);
                    temp.setFocusPainted(false);
                    count++;

                    temp.addActionListener(new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent e)
                        {
                            display.setText(display.getText() + temp.getText());
                        }
                    });
                }


            }
        }

        String[] ops = new String[]{"+", "-", "*", "/"};
        for (int i = 0; i < 4; i++)
        {
            JButton temp = operatorButtons[i] = new JButton();
            temp.setBounds(260, 60 + 50 * i, 50, 50);
            add(temp);
            temp.setText(ops[i] + "");
            temp.setFont(font);
            temp.setBackground(new Color(243, 224, 221));
            temp.setBorder(null);
            temp.setFocusPainted(false);
            count++;

            temp.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    display.setText(display.getText() + temp.getText());
                }
            });
        }

        solve.setBounds(310, 210, 50, 50);
        add(solve);
        solve.setText("=");
        solve.setFont(font);
        solve.setBackground(new Color(243, 224, 221));
        solve.setBorder(null);
        solve.setFocusPainted(false);
        solve.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String temp = history.getText();
                try
                {
                    float value = new Calculator().calculate(display.getText());
                    history.setText(display.getText());
                    history.setText(history.getText() + " = " + value);
                    display.setText(value + "");
                    history.setText(history.getText() + "\n" + temp);
                    saveData();
                }
                catch(Exception ex)
                {
                    display.setText("Invalid!");
                }
            }
        });

        deleteButton.setBounds(310, 160, 50, 50);
        add(deleteButton);
        deleteButton.setText("c");
        deleteButton.setFont(font);
        deleteButton.setBackground(new Color(243, 224, 221));
        deleteButton.setBorder(null);
        deleteButton.setFocusPainted(false);
        deleteButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                display.setText("");
            }
        });

        add(display);
        setVisible(true);

        negative.setBounds(10, 210, 50, 50);
        negative.setFocusPainted(false);
        negative.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(display.getText().length() == 0)
                {
                    display.setText(display.getText() + "0-");
                }
            }
        });
        negative.setFont(font);
        negative.setBackground(new Color(243, 224, 221));
        negative.setBorder(null);
        add(negative);

        backButton.setBounds(160, 60, 50, 50);
        add(backButton);
        backButton.setBackground(new Color(243, 224, 221));
        backButton.setBorder(null);
        backButton.setFocusPainted(false);
        backButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(display.getText().length() > 0)
                {
                    display.setText(display.getText().substring(0, display.getText().length() - 1));
                }
            }
        });

        panel.setBounds(10, 60, 350, 200);
        panel.setBackground(new Color(243, 224, 221));
        add(panel);
    }
}
