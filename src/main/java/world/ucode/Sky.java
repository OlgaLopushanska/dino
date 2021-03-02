package world.ucode;

import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Vector;

public class Sky {
    
  public Vector <ImageView> sky;

  public void skY() {
    sky = new Vector <ImageView>();
    for (int a = 0; a < 3; a++) {
      Image im = new Image("/sky.png");
      ImageView skyy = new ImageView();
      skyy.setImage(im);
      skyy.setLayoutY(10);
      skyy.setLayoutX(-200);
      sky.addElement(skyy);
    }
  }

  public void makeSky(AnchorPane root) {
    skY();
    for (int a = 0; a < 3; a++) {
      var path = new Path();
      path.getElements().add(new MoveTo(1700, 30 + 70 * a));
      path.getElements().add(new LineTo(-300, 30 + 70 * a));
      path.setStyle("visibility: hidden;");
      var ptr = new PathTransition();
      ptr.setDuration(Duration.seconds(15 + a * 3));
      ptr.setDelay(Duration.seconds(a * 3));
      ptr.setCycleCount(100);
      ptr.setAutoReverse(false);
      ptr.setPath(path);
      ptr.setNode(sky.elementAt(a));
      ptr.play();
      root.getChildren().add(path);
      root.getChildren().add(sky.elementAt(a));
    }
  }
}
