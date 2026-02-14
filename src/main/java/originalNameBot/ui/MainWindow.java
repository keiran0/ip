package originalnamebot.ui;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import originalnamebot.bot.BotLines;
import originalnamebot.bot.OriginalNameBot;

public class MainWindow extends AnchorPane {

    private static ChatView chatView = new ChatView();
    private static TextField userInput = new TextField();
    private static Button sendButton = new Button("Send");
    private static boolean isSilenced = false;

    public MainWindow() {

        getChildren().addAll(chatView, userInput, sendButton);
        setPrefSize(400.0, 600.0);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);
        setTopAnchor(chatView, 1.0);
        setBottomAnchor(sendButton, 1.0);
        setRightAnchor(sendButton, 1.0);
        setLeftAnchor(userInput, 1.0);
        setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

    }

    /**
     * Obtains the user input from the input field, calls sendUserMessage, then calls OriginalNameBot.enterCommand.
     * After that, clears the input field.
     */
    public static void handleUserInput() {
        sendUserMessage(userInput.getText());
        OriginalNameBot.enterCommand(userInput.getText());
        userInput.clear();
    }

    /**
     * Creates a new dialog box for the user message. Internal method only used by handleUserInput.
     * @param message Message said by user.
     */
    private static void sendUserMessage(String message) {
        chatView.add(DialogBox.createUserDialogue(message));
    }

    /**
     * Creates a new dialog box for the bot message.
     * @param message Message said by bot.
     */
    public static void sendBotMessage(String message) {
        if (!isSilenced) {
            chatView.add(DialogBox.createBotDialogue(message));
        }
    }


    /**
     * Sets whether the bot should send messages to the chat view.
     * @param bool true to silence the bot, false to allow messages.
     */
    public static void setBotSilence(boolean bool) {
        isSilenced = bool;
    }

    /**
     * Closes the GUI window.
     */
    public static void exit() {
        Platform.exit();
        sendBotMessage(String.valueOf(BotLines.GOODBYE));
    }


}
