package world.ucode;

import javafx.animation.PathTransition;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;
import java.util.Random;
import java.util.Vector;

public class Tulip {

  private Vector <Arc> tulip_arc1;
  private Vector <Arc> tulip_arc2;
  private Vector <Ellipse> tulip_ellipse;
  private Random rand = new Random(System.currentTimeMillis());

  public void tulipInit() {
    tulip_arc1 = new Vector <Arc>();
    tulip_arc2 = new Vector <Arc>();
    tulip_ellipse = new Vector <Ellipse> ();

    for (int a = 0; a <= 3; a++) {
      for (int b = 0; b < 100; b++) {
        int e = 1900;
        int d = 1000;
        Group gr = new Group();
        Ellipse ellipse = new Ellipse(e, d, 7.5, 15);
        ellipse.setFill(Color.rgb(187, 0, 0)); // красный
        if (b % 2 == 0)  // фиолетовый
          ellipse.setFill(Color.rgb(230, 0, 230));
        if (b % 3 == 0)  // желтый
          ellipse.setFill(Color.rgb(255, 255, 0));
        Arc arc1 = new Arc(e - 7.5, d + 15, 7.5, 37.5, 0, -90);
        arc1.setFill(Color.TRANSPARENT);
        arc1.setStroke(Color.rgb(91, 255, 133));
        Arc arc2 = new Arc(e - 22.5, d + 52.5, 15, 30, 0, 90);
        arc2.setFill(Color.rgb(91, 255, 133));
        arc2.setType(ArcType.CHORD);
        Shape shape = Shape.union(ellipse, arc2);
        Shape shape2 = Shape.union(shape, arc1);
        shape2.setFill(Color.GREEN);
        tulip_ellipse.addElement(ellipse);
        tulip_arc1.addElement(arc1);
        tulip_arc2.addElement(arc2);
      }
    }
  }

  public void makeTulip(AnchorPane root) {
    tulipInit();
    for (int a = 0; a <= 3; a++) {
      for (int b = 0; b < 100; b++) {
        var rout1 = new Path();
        rout1.getElements().add(new MoveTo(1700, 320 + a * 100));
        rout1.getElements().add(new LineTo(-500, 320 + a * 100));
        rout1.setStyle("visibility: hidden;");
        var rout2 = new Path();
        rout2.getElements().add(new MoveTo(1700 - 10, 350 + a * 100));
        rout2.getElements().add(new LineTo(-510, 350 + a * 100));
        rout2.setStyle("visibility: hidden;");
        Shape shape = Shape.union(tulip_arc1.elementAt(b + a * 100),
            tulip_arc2.elementAt(b + a * 100));
        shape.setFill(Color.rgb(91, 255, 133));
        var way1 = new PathTransition();
        way1.setDuration(Duration.seconds(15));
        int c = rand.nextInt(3500);
        way1.setDelay(Duration.seconds(((float) c) / 10.0));
        way1.setPath(rout1);
        way1.setNode(tulip_ellipse.elementAt(b + a * 100));
        way1.setCycleCount(30);
        way1.setAutoReverse(false);
        way1.play();
        var way2 = new PathTransition();
        way2.setDuration(Duration.seconds(15));
        way2.setDelay(Duration.seconds(((float) c) / 10.0));
        way2.setPath(rout2);
        way2.setNode(shape);
        way2.setCycleCount(30);
        way2.setAutoReverse(false);
        way2.play();
        root.getChildren().add(rout1);
        root.getChildren().add(rout2);
        root.getChildren().add(tulip_ellipse.elementAt(b + a * 100));
        root.getChildren().add(shape);
      }
    }
  }
}
