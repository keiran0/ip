package originalnamebot.ui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * ChatView is the upper portion of the user interface.
 */
public class ChatView extends ScrollPane {

    @FXML
    private VBox dialogueContainer;

    /**
     * Creates a ChatView with a specific size and layout.
     */
    public ChatView() {
        VBox dialogContainer = new VBox();
        this.dialogueContainer = dialogContainer;
        this.dialogueContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        this.dialogueContainer.heightProperty().addListener((observable) -> this.setVvalue(1.0));
        this.setContent(this.dialogueContainer);
        this.setPrefSize(385, 535);
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.setVvalue(1.0);
        this.setFitToWidth(true);
    }

    /**
     * Add a dialogBox to the bottom of the ChatView.
     * @param dialogBox DialogBox to add.
     */
    public void add(DialogBox dialogBox) {
        this.dialogueContainer.getChildren().addAll(dialogBox);
    }


}
