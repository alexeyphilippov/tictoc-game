
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    static int xWon = 0;
    static int oWon = 0;
    static Stage stage;
    static Stage scoreStage = new Stage();
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
        this.stage = stage;
        stage.setResizable(false);
        stage.setScene(new Scene(createContent(), 300, 300));
        stage.show();


    }

    static void checkState() {
        for (int k = 0; k < 8; k++) {
            if (combos.get(k).isComplete()) {
                playable = false;
                gameOver(combos.get(k));
                if(combos.get(k).cubes[1].getValue() == "x")
                    xWon ++;
                else
                    oWon ++;
                win();
                break;
            }
        }
    }

    static void win() {
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(getFinalScene(stage1), 200, 100));
        stage1.show();
        scoreStage.setX(870);
        scoreStage.setY(180);
        scoreStage.setScene(new Scene(getScoreScene(), 100, 100));
        scoreStage.show();
    }

    private static void gameOver(Combo combo) {
        for (Cube cube :
                combo.cubes) {
            cube.rectangle.setFill(Color.BLACK);
        }
    }

    static public Parent getFinalScene(Stage stage1) {
        Text text = new Text("Wanna play again?");
        text.setLayoutY(30);
        text.setLayoutX(50);
        text.setTextAlignment(TextAlignment.CENTER);
        Button buttonYes = new Button("yes");
        Button buttonNo = new Button("no");
        buttonNo.setLayoutY(50);
        buttonNo.setLayoutX(50);
        buttonYes.setLayoutY(50);
        buttonYes.setLayoutX(125);
        buttonYes.setOnMouseClicked(e -> {
            combos = new ArrayList<>();
            playable = true;
            clickedX = true;
            stage.close();
            try {
                new Main().start(new Stage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            stage1.close();

        });
        buttonNo.setOnMouseClicked(e -> {
            stage.close();
            stage1.close();
            scoreStage.close();
        });
        return new Pane(buttonNo, buttonYes, text);
    }

    static public Parent getScoreScene (){
        Text xWins = new Text(" X has won : " + xWon);
        Text oWins = new Text(" O has won : " + oWon);
        xWins.setLayoutY(33);
        oWins.setLayoutY(66);

        return new Pane(xWins, oWins);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
