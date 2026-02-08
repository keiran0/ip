package originalnamebot.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box component used by the chat UI.
 * Contains factory methods to create new dialog boxes.
 */
public class DialogBox extends HBox {
    private Label text;

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

    public static DialogBox createBotDialogue(String s) {
        return new DialogBox(s, true);
    }

    public static DialogBox createUserDialogue(String s) {
        return new DialogBox(s, false);
    }
}
