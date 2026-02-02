package originalnamebot.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import originalnamebot.bot.BotLines;
import originalnamebot.bot.OriginalNameBot;

public class Main extends Application {

    private static TextField userInput;
    private static Button sendButton;
    private static Scene scene;
    private static ChatView chatView;

    @Override
    public void start(Stage stage) {
        // Setting up required components
        // Most of these code are from the tutorial given
        userInput = new TextField();
        sendButton = new Button("Send");

        chatView = new ChatView();

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

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        OriginalNameBot.init();

    }

    public static void exit() {
        Platform.exit();
        sendBotMessage(String.valueOf(BotLines.GOODBYE));
    }

    public static void handleUserInput() {
        sendUserMessage(userInput.getText());
        OriginalNameBot.enterCommand(userInput.getText());
        userInput.clear();
    }

    public static void sendBotMessage(String message) {
        chatView.add(DialogBox.createBotDialogue(message));
    }

    public static void sendUserMessage(String message) {
        chatView.add(DialogBox.createUserDialogue(message));
    }
}
