package originalnamebot.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Region;

public class DisplayPicture extends Region {

    private ImageView displayPicture;
    private Image image;

    public DisplayPicture(String filepath) {
        image = new Image(getClass().getResourceAsStream(filepath));
        displayPicture = new ImageView(image);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        getChildren().add(displayPicture);
    }

}
