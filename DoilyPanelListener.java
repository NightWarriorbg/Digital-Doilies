import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DoilyPanelListener implements MouseListener, MouseMotionListener {

    /**
     * An instance of DoilyPane;
     */
    private DoilyPanel doilyPanel;

    /**
     * The constructor of the class
     * @param doilyPanel
     */
    public DoilyPanelListener(DoilyPanel doilyPanel) {
        this.doilyPanel = doilyPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!doilyPanel.getEraserStatus()) {
            doilyPanel.addPoint(e);
            doilyPanel.getStrokes().add(doilyPanel.getStroke());
        } else {
            doilyPanel.removePoint(e);
            doilyPanel.getStrokes().remove(doilyPanel.getStroke());
            doilyPanel.repaint();
        }
        doilyPanel.setStroke(new Pen(doilyPanel));
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!doilyPanel.getEraserStatus()) {
            doilyPanel.getStrokes().add(doilyPanel.getStroke());
        } else {
            doilyPanel.getStrokes().remove(doilyPanel.getStroke());
            doilyPanel.repaint();
        }
        doilyPanel.setStroke(new Pen(doilyPanel));
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (!doilyPanel.getEraserStatus()) {
            doilyPanel.addPoint(e);
        } else {
            doilyPanel.removePoint(e);
            doilyPanel.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
