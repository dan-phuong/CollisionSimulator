package src;


import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {

    JMenu menu, submenu, submenu2, menu2;
    JMenuItem restartButton, slowSpeed, mediumSpeed, fastSpeed, smallRadius, mediumRadius, bigRadius, largeRadius;
    JMenuItem redColor, blueColor, greenColor, yellowColor, blackColor, whiteColor, randomColor;
    Game game = new Game(1200, 600);
    JFrame frame = new JFrame("Billiard Ball Collision Simulation");

    /**
     * JFrame contains both our menu and our table (game).
     */
    public Menu() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menubar = new JMenuBar();
        menu = new JMenu("Menu");
        menu2 = new JMenu("Color");
        submenu = new JMenu("Speed");
        submenu2 = new JMenu("Change Radius");

        redColor = new JMenuItem("Red");
        blueColor = new JMenuItem("Blue");
        greenColor = new JMenuItem("Green");
        yellowColor = new JMenuItem("Yellow");
        blackColor = new JMenuItem("Black");
        whiteColor = new JMenuItem("White");
        randomColor = new JMenuItem("Random");

        redColor.addActionListener(this);
        blueColor.addActionListener(this);
        greenColor.addActionListener(this);
        yellowColor.addActionListener(this);
        blackColor.addActionListener(this);
        whiteColor.addActionListener(this);
        randomColor.addActionListener(this);

        restartButton = new JMenuItem("Restart");
        slowSpeed = new JMenuItem("Slow");
        mediumSpeed = new JMenuItem("Medium");
        fastSpeed = new JMenuItem("Fast");

        smallRadius = new JMenuItem("0.5");
        mediumRadius = new JMenuItem("0.8");
        bigRadius = new JMenuItem("1.0");
        largeRadius = new JMenuItem("1.15");

        restartButton.addActionListener(this);
        slowSpeed.addActionListener(this);
        mediumSpeed.addActionListener(this);
        fastSpeed.addActionListener(this);
        smallRadius.addActionListener(this);
        mediumRadius.addActionListener(this);
        bigRadius.addActionListener(this);
        largeRadius.addActionListener(this);

        menu.add(restartButton);
        submenu.add(slowSpeed);
        submenu.add(mediumSpeed);
        submenu.add(fastSpeed);
        submenu2.add(smallRadius);
        submenu2.add(mediumRadius);
        submenu2.add(bigRadius);
        submenu2.add(largeRadius);

        menu2.add(redColor);
        menu2.add(blueColor);
        menu2.add(greenColor);
        menu2.add(yellowColor);
        menu2.add(blackColor);
        menu2.add(whiteColor);
        menu2.add(randomColor);

        menu.add(submenu);
        menu.add(submenu2);
        menubar.add(menu);
        menubar.add(menu2);
        frame.setJMenuBar(menubar);
        frame.setLayout(null);
        frame.setContentPane(game);
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource(); // For ease

        if (source == restartButton) {
            frame.setVisible(false);
            new Menu();
        } 
        else if (source == slowSpeed)
            game.setBallsSlow(0.95);

        else if (source == mediumSpeed)
            game.setBallsSlow(0.97);

        else if (source == fastSpeed)
            game.setBallsSlow(0.99);

        else if (source == smallRadius)
            game.setBallsRadius(game.getRadius() * 0.5);

        else if (source == mediumRadius)
            game.setBallsRadius(game.getRadius() * 0.8);

        else if (source == bigRadius)
            game.setBallsRadius(game.getRadius());

        else if (source == largeRadius)
            game.setBallsRadius(game.getRadius() * 1.15);

        else if (source == redColor)
            game.setBallsColor(Color.RED);

        else if (source == blueColor)
            game.setBallsColor(Color.BLUE);

        else if (source == greenColor)
            game.setBallsColor(Color.GREEN);

        else if (source == yellowColor)
            game.setBallsColor(Color.YELLOW);

        else if (source == blackColor)
            game.setBallsColor(Color.BLACK);

        else if (source == whiteColor)
            game.setBallsColor(Color.WHITE);

        else if (source == randomColor)
            game.setColorRandom();
    }
}
