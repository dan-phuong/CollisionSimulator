package src;


import java.awt.*;

public class Pocket {
    private int diameter;
    private static Color pocketColor = new Color(0,0,0);
    private int xCorner;
    private int yCorner;


    public Pocket(int xCornerIn, int yCornerIn) {
        xCorner = xCornerIn;
        yCorner = yCornerIn;
        diameter = 36;
    }

    public int getxCorner() {
        return xCorner;
    }

    public int getyCorner() {
        return yCorner;
    }

    public int getDiameter() {
        return diameter;
    }

    public void draw(Graphics g) {
        g.setColor(pocketColor);
        g.fillOval(xCorner, yCorner, diameter, diameter);
    }
}
