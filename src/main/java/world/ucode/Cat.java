package world.ucode;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Vector;

public class Cat {

  private ImageView cat;
  private boolean pr[] = new boolean[] {true, true, true, true};
  private boolean if_jump = true;
  private int score = 0;
  private Text scoree;
  private  Timeline tl;
  private  PathTransition ptrCat;

  public void scor(AnchorPane root) {
    scoree = new Text("SCORE: " + score);
    scoree.setFont(Font.font("Cambria", FontWeight.BOLD, 30));
    scoree.setFill(Color.rgb(0, 0, 0));
    AnchorPane.setLeftAnchor(scoree,1300.0);
    AnchorPane.setTopAnchor(scoree,100.0);
    root.getChildren().add(scoree);
  }
  public void collision(ImageView image1, ImageView image_tree, Stage stage, Scene scene) {
    if (image1.getBoundsInParent().intersects(image_tree.getBoundsInParent())) {
      AnchorPane an = new AnchorPane();
      an.setStyle("-fx-background-color: #000;");
      Label game = new Label("GAME OVER");
      game.setFont(Font.font("Arial", FontWeight.BOLD, 30));
      game.setTextFill(Color.rgb(130, 232, 148));
      AnchorPane.setLeftAnchor(game,100.0);
      AnchorPane.setTopAnchor(game,75.0);
      an.getChildren().add(game);
      Button button = new Button("Еще раз");
      button.setPrefSize(200.0, 75.0);
      button.setStyle("-fx-background-color: #82e894; -fx-text-fill: #000; -fx-font-size: 25;");
      AnchorPane.setTopAnchor(button, 250.0);
      AnchorPane.setLeftAnchor(button, 125.0);
      Label your = new Label("Your score: " + score );
      your.setFont(Font.font("Arial", FontWeight.BOLD, 20));
      your.setTextFill(Color.rgb(130, 232, 148));
      AnchorPane.setLeftAnchor(your,125.0);
      AnchorPane.setTopAnchor(your,175.0);
      an.getChildren().add(your);
      an.getChildren().add(button);
      Scene scene3 = new Scene(an, 400, 400);
      stage.setScene(scene3);
      //scene.removeAll();
      button.setOnAction(value -> {
        //scene.addAll();
        stage.setScene(scene);
      });
    }
  }

  public void collision2(ImageView sky, int a) {
    if (cat.getBoundsInParent().intersects(sky.getBoundsInParent()) && pr[a]) {
      pr[a] = false;
      score += 10;
      scoree.setText("SCORE: "+ score);
    }
    if (!cat.getBoundsInParent().intersects(sky.getBoundsInParent()))
      pr[a] = true;
  }

  public void catJump(AnchorPane root, Scene scene, Vector <ImageView> cloud) {
    scene.setOnKeyTyped(new EventHandler<KeyEvent>() {
      public void handle(KeyEvent ke) {
        if (ke.getCharacter().equals(" ") && if_jump) {
          tl.stop();
          if_jump = false;
          var pathCat = new Path();
          pathCat.getElements().add(new MoveTo(50, 150));
          pathCat.getElements().add(new LineTo(50, -200));
          pathCat.setStyle("visibility: hidden;");
          ptrCat = new PathTransition();
          ptrCat.setDuration(Duration.seconds(2));
          ptrCat.setCycleCount(2);
          ptrCat.setAutoReverse(true);
          ptrCat.setPath(pathCat);
          ptrCat.setNode(cat);
          ptrCat.play();
          root.getChildren().add(pathCat);
        }
      }
    });
  }

  public void catAnimated(boolean priz, Stage stage, Vector <ImageView> tre,
      Vector <ImageView> cloud, AnchorPane root, Scene scene) {
    Image image1 = new Image("/1.png");
    Image image2 = new Image("/2.png");
    Image image3 = new Image("/3.png");
    Image image4 = new Image("/4.png");
    Image image5 = new Image("/7.png");
    Image image6 = new Image("/8.png");
    cat = new ImageView();
    cat.setImage(image1);
    cat.setLayoutX(50);
    cat.setLayoutY(400);
    cat.setFitHeight(125);
    cat.setFitWidth(175);
    root.getChildren().add(cat);
    tl = new Timeline(new KeyFrame(Duration.seconds(0.1),
        new EventHandler<ActionEvent>() {
      int im = 0;
            
      @Override
      public void handle(ActionEvent actionEvent) {
        im = (im + 1 > 4) ? 1 : im + 1;
        if (im == 1)
          cat.setImage(image1);
        if (im == 2)
          cat.setImage(image2);
        if (im == 3)
          cat.setImage(image3);
        if (im == 4)
          cat.setImage(image4);
        if (priz == true) {
          for (int a = 0; a < 4; a++)
            collision(cat, tre.elementAt(a), stage, scene);
          for (int a = 0; a < 3; a++)
            collision2(cloud.elementAt(a), a);
        }
      }
    }));
    tl.setCycleCount(2000000);
    tl.setAutoReverse(true);
    tl.play();
    Timeline t2 = new Timeline(new KeyFrame(Duration.seconds(0.1),
        new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent actionEvent) {
        if (ptrCat != null) {
          for (int a = 0; a < 3; a++)
            collision2(cloud.elementAt(a), a);
          if (ptrCat.getCurrentRate() == 1.0 && !if_jump)
            cat.setImage(image5);
          else if(ptrCat.getCurrentRate() == -1.0 && !if_jump)
            cat.setImage(image6);
          if (ptrCat.getCurrentTime().toSeconds() == 0.0) {
            tl.play();
            if_jump = true;
          };
        }
      }
    }));
    t2.setCycleCount(2000000);
    t2.setAutoReverse(true);
    t2.play();
  }
}
