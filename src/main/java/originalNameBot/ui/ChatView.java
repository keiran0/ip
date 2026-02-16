package originalnamebot.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

/**
 * ChatView is the upper portion of the user interface.
 */
public class ChatView extends ScrollPane {

    @FXML
    private VBox dialogueContainer;

    /**
     * Loads ChatView through FXML.
     */
    public ChatView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ChatView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            System.out.println("Something went wrong with the window rendering.");
            e.printStackTrace();
        }
        this.dialogueContainer.heightProperty().addListener((observable) -> this.setVvalue(1.0));
    }

    /**
     * Add a dialogBox to the bottom of the ChatView.
     * @param dialogBox DialogBox to add.
     */
    public void add(DialogBox dialogBox) {
        this.dialogueContainer.getChildren().addAll(dialogBox);
    }


}
