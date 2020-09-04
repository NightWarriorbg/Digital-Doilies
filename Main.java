import java.awt.*;

/**
 *
 * This is a implementation project for the "Digital Doilies" concept
 *
 * @author  Radoslav Enev
 * @version 1.0
 * @since   2018-03-11
 */
public class Main {

    /**
     * The width of the window
     */
    public static final int WINDOW_WIDTH = 1600;

    /**
     * The height of the window
     */
    public static final int WINDOW_HEIGHT = 845;

    /**
     * The width of each side menu
     */
    public static final int SIDE_MENU_WIDTH = WINDOW_WIDTH / 4;

    /**
     * The title of the window
     */
    public static final String TITLE = "Digital Doily";

    /**
     * The title font of each panel
     */
    public static final Font PANEL_TITLE_FONT = new Font("Arial", Font.BOLD, 16);

    /**
     * Just a main method used to run the JFrame window
     * @param args - main method arguments
     */
    public static void main(String[] args) {
        WindowFrame frame = new WindowFrame(WINDOW_WIDTH, WINDOW_HEIGHT, TITLE);
    }
}
