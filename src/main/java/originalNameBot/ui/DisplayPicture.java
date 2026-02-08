package originalnamebot.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

/**
 * DisplayPicture is a wrapper around an ImageView and helps standardise the image format.
 */
public class DisplayPicture extends Region {

    private ImageView displayPicture;
    private Image image;

    /**
     * Creates a DisplayPicture of 100x100 size using the image in the filepath.
     * @param filepath Image filepath.
     */
    public DisplayPicture(String filepath) {
        image = new Image(getClass().getResourceAsStream(filepath));
        displayPicture = new ImageView(image);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        getChildren().add(displayPicture);
    }

}
