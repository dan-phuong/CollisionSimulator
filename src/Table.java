package src;


import java.awt.*;

public class Table {
    int minX, maxX, minY, maxY; // <-- MinX = starting X, MaxX = final X
    public static final int POCKET_NUMBER = 6;
    public static final int WINDOW_SIDE = 18;
    public  Pocket[] topPockets = new Pocket[POCKET_NUMBER / 2];
    public  Pocket[] bottomPockets = new Pocket[POCKET_NUMBER / 2];

    public Table(int x, int y, int width, int height) {
        minX = x;
        minY = y;
        maxX = x + width - 1;
        maxY = y + height - 1;
    }

    public void set(int x, int y, int width, int height) {
        minX = x;
        minY = y;
        maxX = x + width - 1;
        maxY = y + height - 1;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(113, 121, 126));
        g.fillRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
        g.drawRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
        drawPockets(g);

    }

    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }

    private void drawPockets(Graphics g) {
        for (int i = 0; i < POCKET_NUMBER / 2; i++) {
            topPockets[i] = new Pocket(i * ((maxX / 2) - WINDOW_SIDE), minY);
            topPockets[i].draw(g);

            bottomPockets[i] = new Pocket(i * ((maxX / 2) - WINDOW_SIDE), maxY - 2 * WINDOW_SIDE);
            bottomPockets[i].draw(g);
        }
    }
}

