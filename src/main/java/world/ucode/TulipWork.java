package world.ucode;

import javafx.application.Application;
import java.io.FileNotFoundException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TulipWork extends Application {

  public static void main(String[] args) {
    launch(args);
  }
  
  public void start(Stage stage) {
    boolean priz = false;

    AnchorPane root = new AnchorPane();
    AnchorPane anchy = new AnchorPane();
    Rectangle sk = new Rectangle(0, 0, 1700, 350);
    sk.setFill(Color.rgb(140, 221, 255));
    root.setStyle("-fx-background-color: #32261f;");
    Scene scene = new Scene(root, 1500, 700);
    Scene scene2 = new Scene(anchy, 700, 700);
    root.getChildren().add(sk);
    anchy.setStyle("-fx-background-color: #82e894;");
    Image cat_menu = new Image("/cat_menu.png");
    ImageView imageView = new ImageView(cat_menu);
    imageView.setLayoutX(30);
    imageView.setLayoutY(200);
    Button bt_start = new Button("Начать игру");
    Button bt_close = new Button("Закрыть");
    bt_start.setPrefSize(200.0, 75.0);
    bt_close.setPrefSize(200.0, 75.0);
    bt_start.setStyle("-fx-background-color: #000; -fx-text-fill: #82e894; -fx-font-size: 25;");
    bt_close.setStyle("-fx-background-color: #000; -fx-text-fill: #82e894; -fx-font-size: 25;");
    AnchorPane.setTopAnchor(bt_start, 200.0);
    AnchorPane.setLeftAnchor(bt_start, 400.0);
    AnchorPane.setRightAnchor(bt_start, 75.0);
    AnchorPane.setTopAnchor(bt_close, 400.0);
    AnchorPane.setLeftAnchor(bt_close, 400.0);
    AnchorPane.setRightAnchor(bt_close, 75.0);
    Text text = new Text("Весна пришла! Мяу!");
    text.setFill(Color.rgb(255, 91, 255));
    text.setX(100);
    text.setY(100);
    text.setFont(Font.font("Arial", FontWeight.BOLD, 50));
    anchy.getChildren().add(text);
    anchy.getChildren().add(imageView);
    anchy.getChildren().add(bt_start);
    anchy.getChildren().add(bt_close);
    bt_start.setOnAction(value -> {
      stage.setScene(scene);
    });
    bt_close.setOnAction(e -> {
      Platform.exit();
      System.exit(0);
    });
    priz = true;
    Sky cloud = new Sky();
    cloud.makeSky(root);
    Cat cat = new Cat();
    cat.scor(root);
    Grass grass = new Grass();
    grass.makeGrass(root);
    Tulip tulip = new Tulip();
    tulip.makeTulip(root);
    Tree tre = new Tree();
    tre.makeTree(root, priz);
    cat.catAnimated(priz, stage, tre.tree, cloud.sky, root, scene);
    cat. catJump(root, scene, cloud.sky);
    stage.setScene(scene2);
    stage.show();
  }
}

