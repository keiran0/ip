package originalnamebot.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import originalnamebot.bot.OriginalNameBot;

/**
 * The Main class to run the GUI of the application.
 */
public class Main extends Application {

    private static Scene scene;

    /**
     * Initializes the GUI and sets up event handlers.
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        scene = new Scene(new MainWindow());

        stage.setScene(scene);
        stage.show();

        stage.setTitle("OriginalNameBot");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        OriginalNameBot.init();
    }

}
