package src;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;


public class Game extends JPanel implements MouseListener, MouseMotionListener {

    private static final int MAX_BALLS = 16;
    private Ball[] balls = new Ball[MAX_BALLS];
    private Table table;

    private DrawCanvas canvas;
    private int canvasWidth;
    private int canvasHeight;

    private double mouseX;
    private double mouseY;
    private boolean mouseClickedBall;
    private double speed;
    private double angle;
    private int radius;
    private int diameter;

    public int getRadius() {
        return radius;
    }

    /**
     * Sets our table up
     * @param width
     * @param height
     */
    public Game(int width, int height) {
        canvasWidth = width;
        canvasHeight = height;

        radius = 18;
        diameter = 2 * radius + 6;
        int x = width / 2;
        int y = height / 2;
        mouseX = x;
        mouseY = y;

        int speed = 0;
        int angleInDegree = 0;
        balls[0] = new Ball(x * 0.5, y, radius, speed, angleInDegree, Color.WHITE); // <-- Cue Ball
        balls[1] = new Ball(1.5 * x, y, radius, speed, angleInDegree, Color.RED);
        balls[2] = new Ball(1.5 * x, y + diameter, radius, speed, angleInDegree, Color.RED);
        balls[3] = new Ball(1.5 * x, y - diameter, radius, speed, angleInDegree, Color.RED);
        balls[4] = new Ball(1.5 * x, y + 2 * diameter, radius, speed, angleInDegree, Color.RED);
        balls[5] = new Ball(1.5 * x, y - 2 * diameter, radius, speed, angleInDegree, Color.RED);
        balls[6] = new Ball(1.5 * x - diameter, y - 1.5 * diameter, radius, speed, angleInDegree, Color.RED);
        balls[7] = new Ball(1.5 * x - diameter, y - diameter * 0.5, radius, speed, angleInDegree, Color.RED);
        balls[8] = new Ball(1.5 * x - diameter, y + diameter * 0.5, radius, speed, angleInDegree, Color.RED);
        balls[9] = new Ball(1.5 * x - diameter, y + 1.5 * diameter, radius, speed, angleInDegree, Color.RED);
        balls[10] = new Ball(1.5 * x - 2 * diameter, y + diameter, radius, speed, angleInDegree, Color.RED);
        balls[11] = new Ball(1.5 * x - 2 * diameter, y - diameter, radius, speed, angleInDegree, Color.RED);
        balls[12] = new Ball(1.5 * x - 2 * diameter, y, radius, speed, angleInDegree, Color.RED);
        balls[13] = new Ball(1.5 * x - 3 * diameter, y - diameter * 0.5, radius, speed, angleInDegree, Color.RED);
        balls[14] = new Ball(1.5 * x - 3 * diameter, y + diameter * 0.5, radius, speed, angleInDegree, Color.RED);
        balls[15] = new Ball(1.5 * x - 4 * diameter, y, radius, speed, angleInDegree, Color.RED);
        table = new Table(0, 0, canvasWidth, canvasHeight);

        canvas = new DrawCanvas();
        addMouseListener(this);
        addMouseMotionListener(this);
        this.setLayout(new BorderLayout());
        this.add(canvas, BorderLayout.CENTER);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Component c = (Component) e.getSource();
                Dimension dim = c.getSize();
                canvasWidth = dim.width;
                canvasHeight = dim.height;
                table.set(0, 0, canvasWidth, canvasHeight);
            }
        });
        gameStart();
    }


    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (mouseIsInBall(e, balls[0])) {
            mouseClickedBall = true;
        }

        if (mouseClickedBall) {
            speed = ((balls[0].getX() - e.getX()) * (balls[0].getX() - e.getX()) + (balls[0].getY() - e.getY()) * (balls[0].getY() - e.getY())) * 0.0001;
            angle = Math.toDegrees(Math.atan2(balls[0].getX() - e.getX(), balls[0].getY() - e.getY())) - 90;
            System.out.println(speed);
            System.out.println(angle);
            mouseX = e.getX();
            mouseY = e.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (mouseClickedBall) {
            balls[0].setSpeedAndAngle(speed, angle);
        }

        mouseX = balls[0].getX();
        mouseY = balls[0].getY();
        mouseClickedBall = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Detects collision between two balls by checking distance between center of mass of balls
     * @param ball
     * @param ball2
     * @return Boolean
     */
    public boolean detectCollision(Ball ball, Ball ball2) {
        double dist = Math.sqrt(Math.pow(ball.getX() - ball2.getX(), 2) + Math.pow(ball.getY() - ball2.getY(), 2));
        if (ball.radius + ball2.radius - dist > 0.0001) {
            System.out.println(ball.speedX);
            System.out.println(ball2.speedX);
            System.out.println("Collision");
            return true;
        }
        return false;
    }

    /**
     * Checks for collisions
     * @param ball
     * @param ball2
     */
    public void collision(Ball ball, Ball ball2) {

        //Get positions
        double x = ball.getX();
        double y = ball.getY();
        double x2 = ball2.getX();
        double y2 = ball2.getY();

        //Get speed
        double dx = ball.getSpeedX();
        double dy = ball.getSpeedY();
        double dx2 = ball2.getSpeedX();
        double dy2 = ball2.getSpeedY();

        double diametersquared = (ball.getRadius() + ball2.getRadius()) * (ball.getRadius() + ball2.getRadius());
        double deltaX = x - x2;
        double deltaY = y - y2;

        //Move backwards (forwards if dt < 0) in time until balls are just touching
        double CoefA = (dx - dx2) * (dx - dx2) + (dy - dy2) * (dy - dy2);
        double CoefB = 2 * ((dx - dx2) * (x - x2) + (dy - dy2) * (y - y2));
        double CoefC = (x - x2) * (x - x2) + (y - y2) * (y - y2) - diametersquared;
        double t;
        if (CoefA == 0) {
            t = -CoefC / CoefB;
        } else {
            t = (-CoefB - Math.sqrt(CoefB * CoefB - 4 * CoefA * CoefC)) / (2 * CoefA);
        }

        //Center of momentum coordinates
        double mx = (dx + dx2) / 2;
        double my = (dy + dy2) / 2;
        dx = dx - mx;
        dy = dy - my;
        dx2 = dx2 - mx;
        dy2 = dy2 - my;

        //New center to center line
        double dist = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        deltaX = deltaX / dist;
        deltaY = deltaY / dist;

        //Reflect balls velocity vectors in center to center line
        double OB = -(deltaX * dx + deltaY * dy);
        dx = dx + 2 * OB * deltaX;
        dy = dy + 2 * OB * deltaY;
        OB = -(deltaX * dx2 + deltaY * dy2);
        dx2 = dx2 + 2 * OB * deltaX;
        dy2 = dy2 + 2 * OB * deltaY;

        //Back to moving coordinates with elastic velocity change
        double e = Math.sqrt(1.1);
        dx2 = e * (dx2 + mx);
        dy2 = e * (dy2 + my);

        //First ball velocities and position
        ball.setX(x - t * dx);
        ball.setY(y - t * dy);
        ball.setSpeedX(e * (dx + mx));
        ball.setSpeedY(e * (dy + my));

        x2 = x2 - t * dx2;
        y2 = y2 - t * dy2;

        //Set velocities and speed
        ball2.setSpeedX(dx2);
        ball2.setSpeedY(dy2);
        ball2.setX(x2);
        ball2.setY(y2);
    }

    public boolean pocketed(Ball ball, Pocket[] bottomPocket, Pocket[] topPocket) {
        for (int i = 0; i < bottomPocket.length; i++) {
            double dist = Math.sqrt(Math.pow(ball.getX() - topPocket[i].getxCorner(), 2) + Math.pow(ball.getY() - topPocket[i].getyCorner(), 2));
            double dist2 = Math.sqrt(Math.pow(ball.getX() - bottomPocket[i].getxCorner(), 2) + Math.pow(ball.getY() - bottomPocket[i].getyCorner(), 2));

            if (ball.radius + (topPocket[i].getDiameter() / 2) - dist > 0.001 && ball.getVisible()) {
                System.out.println("Ball Pocketed");
                return true;
            } else if (ball.radius + (bottomPocket[i].getDiameter() / 2) - dist2 > 0.001 && ball.getVisible()) {
                System.out.println("Ball pocketed");
                return true;
            }
        }
        return false;
    }

    public void gameStart() {
        Thread gameThread = new Thread() {
            public void run() {
                while (true) {
                    gameUpdate();
                    repaint();
                    try {
                        Thread.sleep(1000 / 30);
                    } catch (InterruptedException ex) {
                    }
                }
            }
        };
        gameThread.start();
    }

    public void gameUpdate() {
        for (int i = 0; i < MAX_BALLS; i++) {
            balls[i].updateWallCollision(table);
            balls[i].addSuppression();
        }
    }

    public void setBallsSlow(double slow) {
        for (int i = 0; i < MAX_BALLS; i++) {
            balls[i].setSlow(slow);
        }
    }

    public void setBallsRadius(double radius) {
        for (int i = 0; i < MAX_BALLS; i++) {
            balls[i].setRadius(radius);
        }
    }

    public void setBallsColor(Color color) {
        for (int i = 1; i < MAX_BALLS; i++) {
            balls[i].setColor(color);
        }
    }

    public void setColorRandom() {
        Random rand = new Random();
        for (int i = 1; i < MAX_BALLS; i++) {
            int a,b,c;
            a = rand.nextInt(251); // 251 because bounds are 0 - [bound-1]
            b = rand.nextInt(251); // 251 because bounds are 0 - [bound-1]
            c = rand.nextInt(251); // 251 because bounds are 0 - [bound-1]
            Color random = new Color(a,b,c);
            balls[i].setColor(random);
        }
    }

    public boolean mouseIsInBall(MouseEvent e, Ball ball) {
        return ((e.getX() >= ball.getX() - ball.radius && e.getX() <= ball.getX() + ball.radius) && (e.getY() >= ball.getY() - ball.radius && e.getY() <= ball.getY() + ball.radius));
    }

    class DrawCanvas extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            table.draw(g);
            for (int i = 0; i < MAX_BALLS; i++) {
                balls[i].draw(g);
            }
            if (mouseClickedBall) g.drawLine((int) balls[0].getX(), (int) balls[0].getY(), (int) mouseX, (int) mouseY);

            /**
             * Runs collision when collision is detected between each ball
             */
            for (int i = 0; i < MAX_BALLS; i++) {
                for (int j = 0; j < MAX_BALLS; j++) {
                    if (i > j) {
                        if (detectCollision(balls[i], balls[j])) {
                            collision(balls[j], balls[i]);
                        }
                    }
                }
            }

            /**
             * If ball hits pocket, puts away ball
             */
            for (int j = 0; j < MAX_BALLS; j++) {
                if (pocketed(balls[j], table.topPockets, table.bottomPockets)) {
                    if (j == 0) {
                        balls[j].setX(canvasWidth * 0.25);
                        balls[j].setY(canvasHeight * 0.5);
                        balls[j].setSpeedX(0);
                        balls[j].setSpeedY(0);
                        System.out.println("Pocketed white ball");

                    } else {
                        balls[j].setVisible(false);
                        balls[j].setX(-1000);
                        balls[j].setY(-1000);
                        balls[j].setSpeedY(0);
                        balls[j].setSpeedX(0);
                        balls[j].setRadius(0);
                        System.out.println("Pocketed game Ball");
                    }
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return (new Dimension(canvasWidth, canvasHeight));
        }
    }
}
