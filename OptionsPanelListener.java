import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class OptionsPanelListener implements ActionListener, ChangeListener {

    /**
     * Instances
     */
    private JFrame frame;
    private DoilyPanel doilyPanel;
    private GalleryPanel galleryPanel;
    private JSpinner sectorSpinner;
    private JSpinner penSizeSpinner;
    private JButton clearButton;
    private JButton undoButton;
    private JButton redoButton;
    private JButton deleteButton;
    private JButton changePenColorButton;
    private JButton saveButton;
    private JButton toggleSectorLinesButton;
    private JButton toggleReflectionButton;
    private JButton toggleEraserButton;

    /**
     * The constructor of the Listener
     * @param frame
     * @param doilyPanel
     * @param galleryPanel
     * @param penSizeSpinner
     * @param sectorSpinner
     * @param clearButton
     * @param undoButton
     * @param redoButton
     * @param deleteButton
     * @param changePenColorButton
     * @param saveButton
     * @param toggleSectorLinesButton
     * @param toggleReflectionButton
     * @param toggleEraserButton
     */
    public OptionsPanelListener(
            JFrame frame, DoilyPanel doilyPanel, GalleryPanel galleryPanel, JSpinner penSizeSpinner, JSpinner sectorSpinner, JButton clearButton, JButton undoButton, JButton redoButton,
            JButton deleteButton, JButton changePenColorButton, JButton saveButton, JButton toggleSectorLinesButton, JButton toggleReflectionButton, JButton toggleEraserButton)
    {
        this.frame = frame;
        this.doilyPanel = doilyPanel;
        this.galleryPanel = galleryPanel;
        this.penSizeSpinner = penSizeSpinner;
        this.sectorSpinner = sectorSpinner;
        this.clearButton = clearButton;
        this.undoButton = undoButton;
        this.redoButton = redoButton;
        this.deleteButton = deleteButton;
        this.changePenColorButton = changePenColorButton;
        this.saveButton = saveButton;
        this.toggleSectorLinesButton = toggleSectorLinesButton;
        this.toggleReflectionButton = toggleReflectionButton;
        this.toggleEraserButton = toggleEraserButton;
    }

    /**
     * For every action performed checks which element is which an performs the appropriate operations
     * @param e - an action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == clearButton) {
            doilyPanel.removeStrokes();
            doilyPanel.repaint();
        } else if(e.getSource() == undoButton) {
            if (!doilyPanel.getStrokes().isEmpty()) {
                int index = doilyPanel.getStrokesSize() - 1;
                Pen item = doilyPanel.getStrokes().get(index);

                doilyPanel.getRemovedStrokes().push(item);
                doilyPanel.removeStrokes(index);
                doilyPanel.repaint();
            }
        } else if (e.getSource() == redoButton) {
            if (!doilyPanel.getRemovedStrokes().empty()) {
                doilyPanel.getStrokes().add(doilyPanel.getRemovedStrokes().pop());
                doilyPanel.repaint();
            }
        } else if (e.getSource() == changePenColorButton) {
            Color color = JColorChooser.showDialog(frame, "Change pen color", frame.getBackground());
            if (color != null) {
                doilyPanel.setStrokeColor(color);
                doilyPanel.setColor(color);
            }
            doilyPanel.repaint();
        } else if(e.getSource() == saveButton) {
            if (galleryPanel.getDoilies().size() < 12) {
                BufferedImage doily = doilyPanel.resize(doilyPanel.save(doilyPanel), 100, 100);
                galleryPanel.addDoily(new JLabel(new ImageIcon(doily)));
                galleryPanel.revalidate();
            } else {
                JOptionPane.showMessageDialog(null, "The maximum number of doilies you can save is 12.");
            }
        } else if (e.getSource() == deleteButton) {
            if (!galleryPanel.getDoilies().isEmpty()) {
                galleryPanel.getDoilies().remove(galleryPanel.getSelected());
                galleryPanel.getMainPanel().remove(galleryPanel.getSelected());
                galleryPanel.revalidate();
                galleryPanel.repaint();
            }
        } else if (e.getSource() == toggleSectorLinesButton) {
            doilyPanel.toggleSectorLines();
            doilyPanel.repaint();
        } else if(e.getSource() == toggleReflectionButton) {
            doilyPanel.toggleReflectDrawing();
            doilyPanel.repaint();
        } else if (e.getSource() == toggleEraserButton) {
            doilyPanel.toggleEraser();
        }
    }

    /**
     * Changes the size of the pen and increases or decreases the sector lines
     * @param e - a change event
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == penSizeSpinner) {
            doilyPanel.setPenSize((int)penSizeSpinner.getValue());
            doilyPanel.setStrokePenSize((int) penSizeSpinner.getValue());
            doilyPanel.repaint();
        } else if (e.getSource() == sectorSpinner) {
            doilyPanel.setSectors((int) sectorSpinner.getValue());
            doilyPanel.setSectorsAngle(360.0 / doilyPanel.getSectors());
            doilyPanel.repaint();
        }
    }
}
