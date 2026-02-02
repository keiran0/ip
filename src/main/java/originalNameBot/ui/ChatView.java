package originalnamebot.ui;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ChatView extends ScrollPane {

    private VBox dialogueContainer;

    public ChatView() {
        VBox dialogContainer = new VBox();
        this.dialogueContainer = dialogContainer;
        this.dialogueContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        this.setContent(this.dialogueContainer);
        this.setPrefSize(385, 535);
        this.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.setVvalue(1.0);
        this.setFitToWidth(true);
    }

    public void add(DialogBox dialogBox) {
        this.dialogueContainer.getChildren().addAll(dialogBox);
    }


}
