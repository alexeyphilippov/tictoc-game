
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    private boolean clickedX = true;
    private boolean playable = true;
    private List<Combo> combos = new ArrayList<>();
    private Cube[][] board = new Cube[3][3];
    // тут прописывается правильный путь до картинки
    String crest = getClass().getResource("i_091.png").toExternalForm();
    String circle = getClass().getResource("circle-thin.png").toExternalForm();

    private Parent createContent() {
        Pane pane = new Pane();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Cube cube = new Cube();
                cube.setTranslateX(i * 100);
                cube.setTranslateY(j * 100);
                pane.getChildren().add(cube);
                board[i][j] = cube;

            }
        }

        //получим три выигрышные строки
        for (int y = 0; y < 3; y++) {
            combos.add(new Combo(board[0][y], board[1][y], board[2][y]));
        }
        //теперь три выигрышных столбца
        for (int x = 0; x < 3; x++) {
            combos.add(new Combo(board[x][0], board[x][1], board[x][2]));
        }
        //и диагональные
        combos.add(new Combo(board[0][0], board[1][1], board[2][2]));
        combos.add(new Combo(board[2][0], board[1][1], board[0][2]));
        return pane;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent(), 600, 600));
        stage.show();
    }

    private void checkState() {
        for (int k = 0; k < 8; k++) {
            if (this.combos.get(k).isComplete()) {
                playable = false;
                gameOver(this.combos.get(k));
                break;
            }
        }
    }

    private void gameOver(Combo combo) {
        for (Cube cube :
                combo.cubes) {
            cube.rectangle.setFill(Color.BLACK);
        }
    }

    public class Combo {
        private Cube[] cubes;

        public Combo(Cube... cubes) {
            this.cubes = cubes;
        }

        public boolean isComplete() {
            if (cubes[0].getValue() == " ")
                return false;

            return cubes[0].getValue() == cubes[1].getValue()
                    && cubes[1].getValue() == cubes[2].getValue();
        }
    }

    private class Cube extends StackPane {
        private Image imageX = new Image(crest);
        private Image imageO = new Image(circle);
        private String value = " ";
        private boolean isPressed = false;
        Rectangle rectangle = new Rectangle(100, 100, Color.WHITE);

        public Cube() {
            rectangle.setStroke(Color.BLACK);
            setAlignment(Pos.CENTER);
            getChildren().addAll(rectangle);
            setOnMouseClicked(e -> {

                System.out.println(playable + this.value + combos.size());
                if (!playable || isPressed)
                    return;
                if (e.getButton() == MouseButton.PRIMARY) {
                    if (!clickedX)
                        return;
                    rectangle.setFill(new ImagePattern(imageX));
                    this.value = "x";
                    clickedX = false;
                    checkState();
                } else {
                    if (clickedX)
                        return;
                    rectangle.setFill(new ImagePattern(imageO));
                    this.value = "o";
                    clickedX = true;
                    checkState();
                }
                e.consume();
                this.isPressed = true;
            });
        }

        public String getValue() {
            return this.value;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
