package originalnamebot.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    @Override
    public void start(Stage stage) {
        // Setting up required components
        // Most of these code are from the tutorial given
        this.userInput = new TextField();
        this.sendButton = new Button("Send");

        ChatView chatView = new ChatView();
        chatView.add(DialogBox.createUserDialogue("test"));
        chatView.add(DialogBox.createBotDialogue("tes11t"));
        chatView.add(DialogBox.createUserDialogue("test"));
        chatView.add(DialogBox.createUserDialogue("test"));
        chatView.add(DialogBox.createUserDialogue("test"));
        chatView.add(DialogBox.createUserDialogue("test"));
        chatView.add(DialogBox.createUserDialogue("test"));


        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(chatView, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("OriginalNameBot");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(chatView, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //More code to be added here later
    }
}
