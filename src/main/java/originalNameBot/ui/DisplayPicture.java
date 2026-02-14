package originalnamebot.ui;

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
        Image image = new Image(getClass().getResourceAsStream(filepath));
        setImage(image);
        setFitWidth(100.0);
        setFitHeight(100.0);
    }

}
