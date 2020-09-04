import javax.swing.*;
import java.awt.*;

/**
 * The frame of the application
 */
public class WindowFrame extends JFrame {

    /**
     * The width of the window
     */
    private int width;

    /**
     * The height of the window
     */
    private int height;

    /**
     * The main constructor of the window
     * @param width - width of the window
     * @param height - height of the window
     * @param title - title of the window
     */
    public WindowFrame(int width, int height, String title) {
        super(title);
        this.setSize(new Dimension(width, height));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setFont(new Font("Times New Roman", Font.PLAIN, 12));

        // Initializing the main GUI elements
        initializeGUI();

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * The main GUI of the window frame
     */
    private void initializeGUI() {
        JPanel window = new JPanel(new BorderLayout());
        this.setContentPane(window);

        //Doily panel
        DoilyPanel doilyPanel = new DoilyPanel();
        doilyPanel.setBackground(Color.BLACK);
        window.add(doilyPanel, BorderLayout.CENTER);

        // Gallery panel
        GalleryPanel galleryPanel = new GalleryPanel(doilyPanel);
        galleryPanel.setBackground(Color.getColor("DDDDDD"));
        window.add(galleryPanel, BorderLayout.EAST);

        // Options panel
        OptionsPanel optionPanel = new OptionsPanel(this, doilyPanel, galleryPanel);
        optionPanel.setBackground(Color.getColor("DDDDDD"));
        window.add(optionPanel, BorderLayout.WEST);

        window.setVisible(true);

    }
}
