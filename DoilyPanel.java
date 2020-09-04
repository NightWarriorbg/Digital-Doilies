import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;

/**
 * The panel of the doily area
 */
public class DoilyPanel extends JPanel {

    /**
     * How many sectors the doily has
     */
    private int sectors;

    /**
     * The angle between each sector line
     */
    private double sectorsAngle;

    /**
     * How big the size of the pen is
     */
    private int penSize;

    /**
     * The color of the pen
     */
    private Color color;

    /**
     * The background color of the panel
     */
    private Color backgroundColor;

    /**
     * Whether sector lines are activated
     */
    private boolean sectorLines = true;

    /**
     * Whether reflected drawing is activated
     */
    private boolean reflectedDrawing = false;

    /**
     * Whether the eraser is on or not
     */
    private boolean eraser = false;

    /**
     * The drawing area of the doily
     */
    private BufferedImage canvas;

    /**
     * An instance of Pen
     */
    private Pen stroke;

    /**
     * The database of strokes
     */
    private ArrayList<Pen> strokes;

    /**
     * A database to store all removed strokes
     */
    public Stack<Pen> removedStrokes;

    /**
     * |---------------------------------- Methods ----------------------------------|
     */

    public DoilyPanel() {
        this.setSectors(6);
        this.setSectorsAngle(360.0 / getSectors());
        this.setPenSize(2);
        this.setColor(Color.WHITE);
        this.setBackground(Color.BLACK);
        stroke = new Pen(this);
        strokes = new ArrayList<>();
        removedStrokes = new Stack<>();
        canvas = new BufferedImage(Main.WINDOW_WIDTH - 2 * Main.SIDE_MENU_WIDTH, Main.WINDOW_HEIGHT , BufferedImage.TYPE_4BYTE_ABGR);
        this.addMouseListener(new DoilyPanelListener(this));
        this.addMouseMotionListener(new DoilyPanelListener(this));
    }

    /**
     * Get method for sectors property
     * @return the number of sectors
     */
    public int getSectors() {
        return sectors;
    }

    /**
     * Set method for sectors property
     * @param sectors - new number of sectors
     */
    public void setSectors(int sectors) {
        this.sectors = sectors;
    }

    /**
     * Set method for sectorAngle property
     * @param sectorsAngle - the angle of the sectors
     */
    public void setSectorsAngle(double sectorsAngle) {
        this.sectorsAngle = sectorsAngle;
    }

    /**
     * Get method for sectorsAngle property
     * @return the angle of the sectors
     */
    public double getSectorsAngle() {
        return sectorsAngle;
    }

    /**
     * Get method for penSize property
     * @return the size of the pen
     */
    public int getPenSize() {
        return penSize;
    }

    /**
     * Set method for penSize property
     * @param penSize - the new size of the pen
     */
    public void setPenSize(int penSize) {
        this.penSize = penSize;
    }

    /**
     * Get method for stroke property
     * @return stroke
     */
    public Pen getStroke() {
        return stroke;
    }

    /**
     * Set method for stroke property
     * @param stroke - the new stroke
     */
    public void setStroke(Pen stroke) {
        this.stroke = stroke;
    }

    /**
     * Set method for strokeColor property
     * @param color - the new color of the stroke
     */
    public void setStrokeColor(Color color) {
        this.stroke.setColor(color);
    }

    /**
     * Set method for strokePenSize property
     * @param size - the new size
     */
    public void setStrokePenSize(int size) {
        this.stroke.setSize(size);
    }

    /**
     * Set method for color property
     * @param color - the new color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Set method for backgroundColor property
     * @param backgroundColor - the new background color
     */
    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /**
     * Get method for backgroundColor property
     * @return the background color of the panel
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Get method for color property
     * @return the color of the pen
     */
    public Color getColor() {
        return color;
    }

    /**
     * Set method for canvas property
     * @param - canvas - the new canvas
     */
    public void setCanvas(BufferedImage canvas) {
        this.canvas = canvas;
    }

    /**
     * Get method for canvas property
     * @return the canvas
     */
    public BufferedImage getCanvas() {
        return canvas;
    }

    /**
     * Get method for sectorLines property
     * @return the status of sectorLines
     */
    public boolean getSectorLinesStatus() {
        return sectorLines;
    }

    /**
     * Get method for reflectDrawing property
     * @return whether the drawing is reflected or not
     */
    public boolean getReflectDrawingStatus() {
        return reflectedDrawing;
    }

    /**
     * Get method for eraser property
     * @return whether the eraser is on or not
     */
    public boolean getEraserStatus() {
        return eraser;
    }

    /**
     * Adds a new point to the stroke
     * @param event - the current event
     */
    public void addPoint(MouseEvent event) {
        stroke.addPoint(new Point(event.getX() - this.getWidth() / 2, event.getY() - this.getWidth() / 2));
        repaint();
    }

    /**
     * Removes the current selected point
     * @param event - the current event
     */
    public void removePoint(MouseEvent event) {
        stroke.removePoint(new Point(event.getX() - this.getWidth() / 2, event.getY() - this.getWidth() / 2));
        repaint();
    }

    /**
     * Get method for strokes property
     * @return strokes
     */
    public ArrayList<Pen> getStrokes() {
        return strokes;
    }

    /**
     * Gets the size of the database
     * @return size
     */
    public int getStrokesSize() {
        return strokes.size();
    }

    /**
     * Removes an element from the database by given index
     * @param index - the index to be removed
     */
    public void removeStrokes(int index) {
        this.strokes.remove(index);
    }

    /**
     * Clears the whole database
     */
    public void removeStrokes() {
        strokes = new ArrayList<>();
    }

    /**
     * Get method for removedStrokes property
     * @return removedStrokes
     */
    public Stack<Pen> getRemovedStrokes() {
        return removedStrokes;
    }

    /**
     * Turns on/off selection lines
     */
    public void toggleSectorLines() {
        sectorLines = !sectorLines;
    }

    /**
     * Turns on/off reflected drawing
     */
    public void toggleReflectDrawing() {
        reflectedDrawing = !reflectedDrawing;
    }

    /**
     * Turns on/off the eraser
     */
    public void toggleEraser() {
        eraser = !eraser;
    }

    /**
     * The method that paints the doily to the panel
     * @param g - component to paint
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setColor(getBackgroundColor());

        // Centering the image
        graphics2D.translate(this.getWidth() / 2, this.getHeight() / 2);

        if (sectorLines && sectors > 1) {
            for (int i = 0; i < sectors; i++) {
                graphics2D.setColor(Color.WHITE);
                graphics2D.setStroke(new BasicStroke(1));
                graphics2D.drawLine(0, 0, 0, 364);
                graphics2D.rotate(Math.toRadians(sectorsAngle));

            }
        }

        //graphics2D.setColor(Color.BLACK);
        graphics2D.setStroke(new BasicStroke(penSize));

        for (int i = 0; i < strokes.size(); i++) {
            strokes.get(i).paint(graphics2D);
        }
        stroke.paint(graphics2D);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    /**
     * Resizes a given image
     * @param doily - the image to resize
     * @param width - the new width of the image
     * @param height - the new height of the image
     * @return
     */
    public static BufferedImage resize(BufferedImage doily, int width, int height) {
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = resized.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(doily, 0, 0, width, height, null);
        g2d.dispose();
        return resized;
    }

    /**
     * Saves the current doily to a given panel
     * @param panel - where is the doily saved
     * @return the doily
     */
    public BufferedImage save(JPanel panel) {
        BufferedImage doily = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        panel.paint(doily.getGraphics());
        return  doily;
    }
}
