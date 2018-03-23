import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public  class Cube extends StackPane {

    private String value = " ";
    private boolean isPressed = false;
    Rectangle rectangle = new Rectangle(100, 100, Color.WHITE);

    public Cube(String image1, String image2) {

        String crest = getClass().getResource("i_091.png").toExternalForm();
        String circle = getClass().getResource("circle-thin.png").toExternalForm();
        Image imageX = new Image(crest);
        Image imageO = new Image(circle);
        rectangle.setStroke(Color.BLACK);
        setAlignment(Pos.CENTER);
        getChildren().addAll(rectangle);
        setOnMouseClicked(e -> {

            if (!Main.playable || isPressed)
                return;
            if (e.getButton() == MouseButton.PRIMARY) {
                if (!Main.clickedX)
                    return;
                rectangle.setFill(new ImagePattern(imageX));
                this.value = "x";
                Main.clickedX = false;
                Main.checkState();
            } else {
                if (Main.clickedX)
                    return;
                rectangle.setFill(new ImagePattern(imageO));
                this.value = "o";
                Main.clickedX = true;
                Main.checkState();
            }
            e.consume();
            this.isPressed = true;
        });
    }

    public String getValue() {
        return this.value;
    }
}