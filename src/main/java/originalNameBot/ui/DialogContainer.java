package originalnamebot.ui;

import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class DialogContainer extends VBox {

    private VBox dialogContainer;

    public DialogContainer() {
        this.dialogContainer = new VBox();
        this.dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    }

    public void add(DialogBox dialogBox) {
        this.getChildren().addAll(dialogBox);
    }
}
