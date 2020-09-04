import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Functionality panel for the gallery
 */
public class GalleryPanel extends JPanel {

    /**
     * An instance of DoilyPanel
     */
    private DoilyPanel doilyPanel;

    /**
     * A panel where to add the elements
     */
    private JPanel mainPanel;

    /**
     * The database of the gallery
     */
    private ArrayList<JLabel> doilies;

    /**
     * The currently selected doily
     */
    private JLabel selected;

    /**
     * The constructor of GalleryPanel
     * @param doilyPanel - the doily
     */
    public GalleryPanel(DoilyPanel doilyPanel) {
        this.doilyPanel = doilyPanel;
        this.mainPanel = new JPanel();
        this.doilies = new ArrayList<>();

        /**
         * Start of GUI
         */
        this.setPreferredSize(new Dimension(Main.SIDE_MENU_WIDTH, Main.WINDOW_HEIGHT));
        setLayout(new BorderLayout());

        //Initializing the panel GUI elements
        initializeGUI();

        setVisible(true);

        /**
         * End of GUI
         */

    }

    /**
     * Get method for mainPanel property
     * @return
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Get method for selected property
     * @return
     */
    public JLabel getSelected() {
        return selected;
    }

    /**
     * Gets the database of doilies
     * @return doilies
     */
    public ArrayList<JLabel> getDoilies() {
        return doilies;
    }

    /**
     * Adds a doily do the database and to the panel
     * @param doily
     */
    public void addDoily(JLabel doily) {
        getDoilies().add(doily);
        mainPanel.add(doily);
        removeDoilyGUI(doily);
    }

    /**
     * Deletes a doily from the database by a given index
     * @param index - an index
     */
    public void deleteDoily(int index) {
        getDoilies().remove(index);
        this.remove(index);
    }

    /**
     * The main GUI of the panel
     */
    private void initializeGUI() {
        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        this.add(titlePanel, BorderLayout.NORTH);

        // Gallery title label
        JLabel galleryTitle = new JLabel("Gallery");
        galleryTitle.setFont(Main.PANEL_TITLE_FONT);
        titlePanel.add(galleryTitle);

        titlePanel.setVisible(true);

        // Main panel
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        mainPanel.setPreferredSize(new Dimension(Main.SIDE_MENU_WIDTH, Main.WINDOW_WIDTH - titlePanel.getHeight()));
        this.add(mainPanel, BorderLayout.CENTER);

    }

    private void removeDoilyGUI(JLabel doily) {
        doily.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selected = doily;
                doily.setBorder(BorderFactory.createLineBorder(Color.RED));
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

}
