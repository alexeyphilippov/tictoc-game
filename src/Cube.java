import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


public  class Cube extends StackPane {
    final String crestPath = "i_091.png";
    final String circlePath = "circle-thin.png";
    private String value = " ";
    private boolean isPressed = false;
    Rectangle rectangle = new Rectangle(100, 100, Color.WHITE);

    public Cube() {

        rectangle.setStroke(Color.BLACK);
        setAlignment(Pos.CENTER);
        getChildren().addAll(rectangle);
        setOnMouseClicked(e -> {

            if (!Main.playable || isPressed)
                return;
            if (e.getButton() == MouseButton.PRIMARY) {
                if (!Main.clickedX)
                    return;
                rectangle.setFill(new ImagePattern(new Image(getClass().getResource(crestPath).toExternalForm())));
                this.value = "x";
                Main.clickedX = false;
                Main.checkState();
                Main.checkDraw();
            } else {
                if (Main.clickedX)
                    return;
                rectangle.setFill(new ImagePattern(new Image(getClass().getResource(circlePath).toExternalForm())));
                this.value = "o";
                Main.clickedX = true;
                Main.checkState();
                Main.checkDraw();
            }
            e.consume();
            this.isPressed = true;
        });
    }

    public String getValue() {
        return this.value;
    }
}