package src;

import javax.swing.*;

public class Main {
    /**
     * Main function for program
     * @param args
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(()-> {
            {
                new Menu();
            }
        });
    }
}