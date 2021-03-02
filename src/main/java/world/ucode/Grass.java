package world.ucode;

import javafx.animation.PathTransition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;
import java.util.Random;
import java.util.Vector;

public class Grass {

  private Vector <Arc> grass_arc1;
  private Vector <Arc> grass_arc2;
  private Random rand = new Random(System.currentTimeMillis());

  public void initGrass() {
    grass_arc1 = new Vector <Arc>();
    grass_arc2 = new Vector <Arc>();
    for (int a = 0; a <= 11; a++) {
      for (int b = 0; b < 50; b++) {
        int e = 1900;
        int d = 1000;
        Arc arc = new Arc(e + 7, d + 22, 7, 7, 0, 90);
        Arc arc2 = new Arc(e + 15, d + 13, 3, 10, 90, 180);
        Shape shape = Shape.union(arc, arc2);
        shape.setFill(Color.rgb(0, 149, 37));
        grass_arc1.addElement(arc);
        grass_arc2.addElement(arc2);
      }
    }
  }

  public void makeGrass(AnchorPane root) {
    initGrass();
    for (int a = 0; a <= 11; a++) {
      for (int b = 0; b < 20; b++) {
        var rout = new Path();
        rout.getElements().add(new MoveTo(1700, 350 + a * 30));
        rout.getElements().add(new LineTo(-500, 350 + a * 30));
        rout.setStyle("visibility: hidden;");
        root.getChildren().add(rout);
        var way1 = new PathTransition();
        way1.setDuration(Duration.seconds(15));
        int c = rand.nextInt(500);
        way1.setDelay(Duration.seconds(((float) c) / 10.0));
        way1.setPath(rout);
        Shape shape = Shape.union(grass_arc1.elementAt(b + a * 20),
            grass_arc2.elementAt(b + a * 20));
        shape.setFill(Color.rgb(0, 149, 37));
        way1.setNode(shape);
        way1.setCycleCount(50);
        way1.setAutoReverse(false);
        way1.play();
        root.getChildren().add(shape);
      }
    }
  }
}
