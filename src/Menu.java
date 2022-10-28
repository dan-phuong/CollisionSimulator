package src;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {

    JMenu menu, submenu, submenu2;
    JMenuItem restartButton, slowSpeed, mediumSpeed, fastSpeed, smallRadius, mediumRadius, bigRadius, largeRadius;
    Game game = new Game(1200, 600);
    JFrame frame = new JFrame("Billiard Ball Collision Simulation");

    /**
     * JFrame contains both our menu and our table (game).
     */
    public Menu() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menubar = new JMenuBar();
        menu = new JMenu("Menu");
        submenu = new JMenu("Speed");
        submenu2 = new JMenu("Change Radius");

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

        menu.add(submenu);
        menu.add(submenu2);
        menubar.add(menu);
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
        } else if (source == slowSpeed)
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
    }
}
