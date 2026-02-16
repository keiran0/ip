package originalnamebot.ui;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box component used by the chat UI.
 * Contains factory methods to create new dialog boxes.
 */
public class DialogBox extends HBox {

    @FXML
    private Label text;

    @FXML
    private DisplayPicture displayPicture;

    /**
     * Creates a DialogBox with text and optional profile picture.
     * @param s The text content to display in the dialog box.
     * @param isBot Whether this dialog box is from the bot (true) or user (false).
     */
    private DialogBox(String s, boolean isBot) {
        this.text = new Label(s);
        this.text.setWrapText(true);
        if (isBot) {
            this.setAlignment(Pos.TOP_LEFT);
            DisplayPicture picture = new DisplayPicture("/images/1.png");
            this.getChildren().addAll(picture, this.text);
        } else {
            this.setAlignment(Pos.TOP_RIGHT);
            DisplayPicture picture = new DisplayPicture("/images/Untitled.png");
            this.getChildren().addAll(text, picture);
        }
    }

    /**
     * Creates a DialogBox for a bot message.
     * @param s The bot message text.
     * @return A DialogBox configured for bot messages.
     */
    public static DialogBox createBotDialogue(String s) {
        return new DialogBox(s, true);
    }

    /**
     * Creates a DialogBox for a user message.
     * @param s The user message text.
     * @return A DialogBox configured for user messages.
     */
    public static DialogBox createUserDialogue(String s) {
        return new DialogBox(s, false);
    }
}
