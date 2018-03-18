
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
import java.util.LinkedList;
import java.util.List;

//
////
////import javafx.application.Application;
////import javafx.scene.Parent;
////import javafx.scene.Scene;
////import javafx.scene.layout.Pane;
////import javafx.scene.paint.Color;
////import javafx.scene.shape.Rectangle;
////import javafx.stage.Stage;
////
////public class Main extends Application {
////    private Parent createContent() {
////        Rectangle box = new Rectangle(100, 50, Color.BLUE);
////
////            transform(box);
////        return new Pane(box);
////
////    }
////
////    private void transform(Rectangle box) {
////        box.setTranslateX(100);
////        box.setTranslateY(100);
////
////            box.setRotate(30);
//////        box.setScaleX(1.5);
//////        box.setScaleY(1.5);
//////        box.setRotate(30);
////
////    }
////
////    @Override
////    public void start(Stage stage) {
////
////            stage.setScene(new Scene(createContent(), 300, 300, Color.GRAY));
////        for(int i = 1; i < 5 ; i++){
////
////            stage.show();}
////    }
////
////    public static void main(String[] args) {
////        launch(args);
////    }
////
////}
//
//
////import javafx.application.Application;
////import javafx.event.ActionEvent;
////import javafx.event.EventHandler;
////import javafx.fxml.FXMLLoader;
////import javafx.scene.Parent;
////import javafx.scene.Scene;
////import javafx.scene.control.Button;
////import javafx.scene.layout.StackPane;
////import javafx.stage.Stage;
////
////public class Main extends Application {
////
////    @Override
////    public void start(Stage primaryStage) throws Exception{
////
////        Button button = new Button("click me");
////        button.setOnAction(actionEvent -> System.out.println("Hello World!"));
////        StackPane pane = new StackPane();
////        pane.getChildren().add(button);
////        primaryStage.setScene(new Scene(pane,300,300));
////        primaryStage.show();
////    }
////
////    public static void main(String[] args) {
////        launch(args);
////    }
////}
////import javafx.application.Application;
////        import javafx.geometry.Insets;
////        import javafx.scene.Scene;
////        import javafx.scene.control.Label;
////        import javafx.scene.layout.FlowPane;
////        import javafx.stage.Stage;
////
////public class Main extends Application {
////
////    @Override
////    public void start(Stage primaryStage) throws Exception {
////
////        Label label = new Label("My Label");
////
////        FlowPane root = new FlowPane();
////        root.setPadding(new Insets(10));
////        root.getChildren().add(label);
////
////        Scene scene = new Scene(root, 200, 100);
////
////        primaryStage.setTitle("JavaFX Label (o7planning.org)");
////        primaryStage.setScene(scene);
////        primaryStage.show();
////    }
////
////    public static void main(String[] args) {
////        Application.launch(args);
////    }
////
////}
////
//
//import javafx.application.Application;
//import javafx.scene.Group;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.image.Image;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.StackPane;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.ImagePattern;
//import javafx.scene.shape.Rectangle;
//import javafx.stage.Stage;
//
////public class Main extends Application {
////    Rectangle rectangle = new Rectangle(50, 100);
////
////
////
////    @Override
////    public void start(Stage primaryStage) throws Exception {
////        Pane stackPane = new Pane();
////        rectangle.setLayoutX(213);
////        rectangle.setLayoutY(235);
////        stackPane.getChildren().add(rectangle);
////        rectangle.setOnMouseClicked(e -> {
////            rectangle.setRotate(45);
////
////            e.consume();
////        });
////        Scene scene = new Scene(stackPane, 400, 400);
////        primaryStage.setScene(scene);
////        primaryStage.show();
////
////    }
////}
////import javafx.application.Application;
////import javafx.scene.Scene;
////import javafx.scene.control.Button;
////import javafx.scene.layout.BorderPane;
////import javafx.scene.layout.Pane;
////import javafx.scene.layout.StackPane;
////import javafx.scene.paint.Color;
////import javafx.scene.shape.Rectangle;
////import javafx.stage.Stage;
////
////import static java.lang.Math.PI;
////
////
//////public class Main extends Application {
//////    private Rectangle rectangle = new Rectangle(100, 200, Color.BLUE);
//////
//////    @Override
//////    public void start(Stage primaryStage) throws Exception {
////////        StackPane sp = new StackPane();
////////        sp.getChildren().add(rectangle);
//////        Pane borderPane = new Pane();
//////
//////        Button button = new Button("afsd");
//////        button.setLayoutX(142);
//////        button.setLayoutY(234);
//////        borderPane.getChildren().add(button);
//////
//////
//////        rectangle.setOnMouseClicked(e -> {
////////            rectangle.setRotate(rectangle.getRotate() + 90);
////////            rectangle.setLayoutX(rectangle.getLayoutX() + 110);
//////
//////            e.consume();
//////        });
//////
//////        Scene scene = new Scene(borderPane, 500, 500);
//////        primaryStage.setScene(scene);
//////
//////        primaryStage.show();
//////    }
//////}

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
                pane.getChildren().addAll(cube);
                board[i][j] = cube;

            }
        }
        //получим три выигрышные строки
        for (int y = 0; y < 3; y++) {
            combos.add((new Combo(board[0][y], board[1][y], board[2][y])));
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

    public void checkState() {
        for (Combo combo : combos) {
            if (combo.isComplete())
                playable = false;
            break;
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
            else
                return cubes[0].getValue() == cubes[1].getValue() && cubes[1].getValue() == cubes[2].getValue();
        }
    }

    private class Cube extends StackPane {
        private Image imageX = new Image(crest);
        private Image imageO = new Image(circle);
        private String value = " ";

        public Cube() {
            Rectangle rectangle = new Rectangle(100, 100, Color.WHITE);
            rectangle.setStroke(Color.BLACK);
            setAlignment(Pos.CENTER);
            getChildren().addAll(rectangle);
            setOnMouseClicked(e -> {
                System.out.println(playable + this.value);
                if (!playable)
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
//public class Main extends Application {
//    @Override
//    public void start(Stage stage) throws Exception {
//        Rectangle rectangle1 = new Rectangle(0,0,99, 99 );
//        Rectangle rectangle2 = new Rectangle(100,0,99, 99 );
//        Rectangle rectangle3 = new Rectangle(200,0,99, 99 );
//        Rectangle rectangle4 = new Rectangle(0,100,99, 99 );
//        Rectangle rectangle5 = new Rectangle(100,100,99, 99 );
//        Rectangle rectangle6 = new Rectangle(200,100,99, 99 );
//        Rectangle rectangle7 = new Rectangle(0,200,99, 99 );
//        Rectangle rectangle8 = new Rectangle(100,200,99, 99 );
//        Rectangle rectangle9 = new Rectangle(200,200,99, 99 );
//        rectangle1.setFill(Color.WHITE);
//        rectangle2.setFill(Color.WHITE);
//        rectangle3.setFill(Color.WHITE);
//        rectangle4.setFill(Color.WHITE);
//        rectangle5.setFill(Color.WHITE);
//        rectangle6.setFill(Color.WHITE);
//        rectangle7.setFill(Color.WHITE);
//        rectangle8.setFill(Color.WHITE);
//        rectangle9.setFill(Color.WHITE);
//
//
//        Line line1 = new Line(0, 100, 300, 100);
//        Line line2 = new Line(0, 200, 300, 200);
//        Line line3 = new Line(100, 0, 100, 300);
//        Line line4 = new Line(200, 0, 200, 300);
//
//        Pane pane = new Pane();
//        pane.getChildren().addAll(line1, line2, line3, line4, rectangle1, rectangle2, rectangle3,
//                rectangle4, rectangle5, rectangle6, rectangle7, rectangle8, rectangle9);
//        Scene scene = new Scene(pane, 300, 300);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}