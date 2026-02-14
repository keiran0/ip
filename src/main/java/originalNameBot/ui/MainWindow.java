package originalnamebot.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import originalnamebot.bot.BotLines;
import originalnamebot.bot.OriginalNameBot;

/**
 * An AnchorPane containing the ChatView and input fields.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ChatView chatView;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private boolean isSilenced = false;

    /**
     * Obtains the user input from the input field, calls sendUserMessage, then calls OriginalNameBot.enterCommand.
     * After that, clears the input field.
     */
    @FXML
    public void handleUserInput() {
        sendUserMessage(userInput.getText());
        OriginalNameBot.enterCommand(userInput.getText());
        userInput.clear();
    }

    /**
     * Creates a new dialog box for the user message. Internal method only used by handleUserInput.
     * @param message Message said by user.
     */
    private void sendUserMessage(String message) {
        chatView.add(DialogBox.createUserDialogue(message));
    }

    /**
     * Creates a new dialog box for the bot message.
     * @param message Message said by bot.
     */
    public void sendBotMessage(String message) {
        if (!isSilenced) {
            chatView.add(DialogBox.createBotDialogue(message));
        }
    }

    /**
     * Sets whether the bot should send messages to the chat view.
     * @param bool true to silence the bot, false to allow messages.
     */
    public void setBotSilence(boolean bool) {
        isSilenced = bool;
    }

    /**
     * Closes the GUI window.
     */
    public void exit() {
        Platform.exit();
        sendBotMessage(String.valueOf(BotLines.GOODBYE));
    }

}
