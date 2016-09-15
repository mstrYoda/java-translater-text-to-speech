package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by macbook on 16.09.2016.
 */
public class Popup extends JFrame {

    public Popup(String text){

        setSize(300,125);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setLayout(new GridBagLayout());

        Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();// size of the screen
        Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());// height of the task bar
        setLocation(scrSize.width - getWidth(), scrSize.height - toolHeight.bottom - getHeight());

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;

        JLabel headingLabel = new JLabel("Translate Result");
        //headingLabel.setIcon(headingIcon); // --- use image icon you want to be as heading image.
        headingLabel.setOpaque(false);

        add(headingLabel, constraints);

        constraints.gridx++;
        constraints.weightx = 0f;
        constraints.weighty = 0f;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.NORTH;

        JButton cloesButton = new JButton(new AbstractAction("x") {

            public void actionPerformed(final ActionEvent e) {
                dispose();
            }
        });

        cloesButton.setMargin(new Insets(1, 4, 1, 4));
        cloesButton.setFocusable(false);

        add(cloesButton, constraints);

        constraints.gridx = 0;
        constraints.gridy++;
        constraints.weightx = 1.0f;
        constraints.weighty = 1.0f;
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.fill = GridBagConstraints.BOTH;

        JLabel messageLabel = new JLabel("<HtMl>" + text);

        add(messageLabel, constraints);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);



    }



}
