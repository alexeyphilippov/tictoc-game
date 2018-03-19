
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    static boolean clickedX = true;
    static boolean playable = true;
    static List<Combo> combos = new ArrayList<>();
    Cube[][] board = new Cube[3][3];
    // тут прописывается правильный путь до картинки
    String string1 = "i_091.png";
    String string2 = "circle-thin.png";

    private Parent createContent() {
        Pane pane = new Pane();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Cube cube = new Cube(string1, string2);
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

    static void checkState() {
        for (int k = 0; k < 8; k++) {
            if (combos.get(k).isComplete()) {
                playable = false;
                gameOver(combos.get(k));
                break;
            }
        }
    }

    private static void gameOver(Combo combo) {
        for (Cube cube :
                combo.cubes) {
            cube.rectangle.setFill(Color.BLACK);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
