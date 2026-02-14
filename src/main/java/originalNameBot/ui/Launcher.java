package originalnamebot.ui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Entry point of the application. Launches the JavaFX Application.
     * @param args Command line arguments (unused).
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
