

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;



public class Score extends StackPane {
    Pane scorePane = new Pane();
    public Score(){
        Text xWonText = new Text("X won " + Main.xWon + " times");
        Text oWonText = new Text("O won " + Main.oWon + " times");
        Text drawWonText = new Text("Draw " + Main.drawWon + " times");
        drawWonText.setTranslateX(10);
        drawWonText.setTranslateY(150);
        xWonText.setTranslateX(10);
        xWonText.setTranslateY(50);
        oWonText.setTranslateX(10);
        oWonText.setTranslateY(100);
        scorePane.setTranslateX(300);
        scorePane.setTranslateY(0);
        this.scorePane.getChildren().addAll(xWonText, oWonText, drawWonText);
    }

    public Pane getScorePane() {
        return scorePane;
    }
}