import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Pen {
    /**
     * An instance of DoilyPanel
     */
    private DoilyPanel doilyPanel;

    /**
     * The size of the pen
     */
    private int size;

    /**
     * The color of the pen
     */
    private Color color;

    /**
     * Whether reflections are on/off
     */
    private boolean isReflectedOn;

    /**
     * Database of points
     */
    private ArrayList<Point> points;

    /**
     * The constructor of Pen
     * @param doilyPanel
     */
    public Pen(DoilyPanel doilyPanel) {
        this.doilyPanel = doilyPanel;
        setSize(doilyPanel.getPenSize());
        setColor(doilyPanel.getColor());
        setReflectedOn(doilyPanel.getReflectDrawingStatus());
        points = new ArrayList<>();
    }

    /**
     * Set method for size property
     * @param size - the new size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Get method for size property
     * @return the size of the pen
     */
    public int getSize() {
        return size;
    }

    /**
     * Set method for color property
     * @param color - new color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * get method for color property
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Set method for reflectedOn property
     * @param reflectedOn - whether reflected or not
     */
    public void setReflectedOn(boolean reflectedOn) {
        isReflectedOn = reflectedOn;
    }

    /**
     * Set method for points property
     * @param points - the new database
     */
    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    /**
     * Get method for points property
     * @return points
     */
    public ArrayList<Point> getPoints() {
        return points;
    }

    /**
     * Adds a point to the database
     * @param point - to be added
     */
    public void addPoint(Point point) {
        this.points.add(point);
    }

    /**
     * Removes a point from the database
     * @param point - to be removed
     */
    public void removePoint(Point point) {
        this.points.remove(point);
    }

    /**
     * Paints on screen
     * @param graphics
     */
    public void paint(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(color);

        for (int i = 0; i < doilyPanel.getSectors(); i++) {
            graphics2D.setStroke(new BasicStroke(size));
            Point point1;
            if (points.size() == 1) {
                point1 = points.get(0);
                graphics2D.drawLine((int)point1.getX(), (int)point1.getY(),(int)point1.getX(), (int)point1.getY());

                // Draw Reflections if they are on
                if (isReflectedOn) {
                    graphics2D.drawLine(-(int)point1.getX(), (int)point1.getY(), -(int)point1.getX(), (int)point1.getY());
                }
                graphics2D.rotate(Math.toRadians(doilyPanel.getSectorsAngle()));
            } else if (points.size() > 1){
                Iterator<Point> pointsIterator = points.iterator();
                point1 = pointsIterator.next();

                while (pointsIterator.hasNext()) {
                    Point point2 = pointsIterator.next();
                    graphics2D.drawLine((int)point1.getX(), (int)point1.getY(), (int)point2.getX(), (int)point2.getY());

                    // Draw Reflections if they are on
                    if (isReflectedOn) {
                        graphics2D.drawLine(-(int)point1.getX(), (int)point1.getY(), -(int)point2.getX(), (int)point2.getY());
                    }

                    point1 = point2;
                }
                graphics2D.rotate(Math.toRadians(doilyPanel.getSectorsAngle()));
            }
        }
    }
}
