package originalnamebot.ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * DisplayPicture is an ImageView and helps standardise the image format.
 */
public class DisplayPicture extends ImageView {

    /**
     * Creates a DisplayPicture of 100x100 size using the image in the filepath.
     * @param filepath Image filepath.
     */
    public DisplayPicture(String filepath) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DisplayPicture.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            System.out.println("Something went wrong with the window rendering.");
            e.printStackTrace();
        }
        Image image = new Image(getClass().getResourceAsStream(filepath));
        setImage(image);
    }

}
