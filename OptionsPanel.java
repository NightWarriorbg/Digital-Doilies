import javax.swing.*;
import java.awt.*;

/**
 * The panel with all different options you can use use to customize to doily
 */
public class OptionsPanel extends JPanel {

    /**
     * An instance of the DoilyPanel class
     */
    private DoilyPanel doilyPanel;

    /**
     * An instance of JFrame
     */
    private JFrame frame;

    /**
     * An instance of GalleryPanel
     */
    private GalleryPanel galleryPanel;

    /**
     * The constructor of OptionsPanel
     * @param frame - the frame of the application
     * @param doilyPanel - instance of DoilyPanel
     * @param galleryPanel - instance of GalleryPanel
     */
    public OptionsPanel(JFrame frame, DoilyPanel doilyPanel, GalleryPanel galleryPanel) {
        this.doilyPanel = doilyPanel;
        this.frame = frame;
        this.galleryPanel = galleryPanel;

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
     * The main GUI of the panel
     */
    private void initializeGUI() {


        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        this.add(titlePanel, BorderLayout.NORTH);

        // Options title label
        JLabel optionsTitle = new JLabel("Options");
        optionsTitle.setFont(Main.PANEL_TITLE_FONT);
        titlePanel.add(optionsTitle);

        titlePanel.setVisible(true);

        // Main panel
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
        mainPanel.setPreferredSize(new Dimension(Main.SIDE_MENU_WIDTH, Main.WINDOW_WIDTH - titlePanel.getHeight()));
        this.add(mainPanel, BorderLayout.CENTER);

        // Doily sector
        JLabel sectorTitle = new JLabel("Sectors: ");
        mainPanel.add(sectorTitle);

        SpinnerModel sectorSpinnerModel = new SpinnerNumberModel(doilyPanel.getSectors(), 1,100,1);
        JSpinner sectorSpinner = new JSpinner(sectorSpinnerModel);
        mainPanel.add(sectorSpinner);

        // Pen size
        JLabel penSizeTitle = new JLabel("Pen size: ");
        mainPanel.add(penSizeTitle);

        SpinnerModel penSizeSpinnerModel = new SpinnerNumberModel(doilyPanel.getPenSize(), 1,100,1);
        JSpinner penSizeSpinner = new JSpinner(penSizeSpinnerModel);
        mainPanel.add(penSizeSpinner);

        // Clear
        JButton clearButton = new JButton("Clear");
        mainPanel.add(clearButton);

        // Undo
        JButton undoButton = new JButton("Undo");
        mainPanel.add(undoButton);

        // Redo
        JButton redoButton = new JButton("Redo");
        mainPanel.add(redoButton);

        // Change pen color
        JButton changePenColorButton = new JButton("Change pen color");
        mainPanel.add(changePenColorButton);

        // Toggle sector lines
        JButton toggleSectorLinesButton = new JButton("Toggle Sector Lines ");
        mainPanel.add(toggleSectorLinesButton);

        // Toggle reflections
        JButton toggleReflectionButton = new JButton("Toggle Reflections");
        mainPanel.add(toggleReflectionButton);

        // Toggle eraser
        JButton toggleEraserButton = new JButton("Toggle Eraser");
        mainPanel.add(toggleEraserButton);

        // Save
        JButton saveButton = new JButton("Save");
        mainPanel.add(saveButton);

        // Delete
        JButton deleteButton = new JButton("Delete");
        mainPanel.add(deleteButton);

        // Creating a new OptionsPanelListener
        OptionsPanelListener optionsPanelListener = new OptionsPanelListener(
                frame, doilyPanel, galleryPanel, penSizeSpinner, sectorSpinner, clearButton, undoButton, redoButton, deleteButton,
                changePenColorButton, saveButton, toggleSectorLinesButton, toggleReflectionButton, toggleEraserButton
        );

        // Adding all listeners to the components
        sectorSpinner.addChangeListener(optionsPanelListener);
        penSizeSpinner.addChangeListener(optionsPanelListener);
        clearButton.addActionListener(optionsPanelListener);
        undoButton.addActionListener(optionsPanelListener);
        redoButton.addActionListener(optionsPanelListener);
        changePenColorButton.addActionListener(optionsPanelListener);
        toggleSectorLinesButton.addActionListener(optionsPanelListener);
        toggleReflectionButton.addActionListener(optionsPanelListener);
        toggleEraserButton.addActionListener(optionsPanelListener);
        saveButton.addActionListener(optionsPanelListener);
        deleteButton.addActionListener(optionsPanelListener);

        mainPanel.setVisible(true);
    }
}
