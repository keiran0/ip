package originalnamebot.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import originalnamebot.bot.OriginalNameBot;

/**
 * The Main class to run the GUI of the application.
 */
public class Main extends Application {

    private static Scene scene;
    private static MainWindow controller;

    /**
     * Initializes the GUI and sets up event handlers.
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        System.out.println("loading");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));

        try {
            scene = new Scene(loader.load());
            controller = loader.getController();

            stage.setScene(scene);
            stage.show();

            stage.setTitle("OriginalNameBot");
            stage.setResizable(false);
            stage.setMinHeight(600.0);
            stage.setMinWidth(400.0);
            OriginalNameBot.init();
        } catch (IOException e) {
            System.out.println("Something went wrong with the window rendering.");
            e.printStackTrace();
        }

    }

    public static MainWindow getMainWindow() {
        return controller;
    }

}
