package world.ucode;

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Vector;

public class Tree {

  public Vector <ImageView> tree;

  public void initTree() {
    tree = new Vector <ImageView> ();
    Image image_tree = new Image("/tree.png");
    for (int a = 0; a < 4; a++) {
      ImageView ree = new ImageView(image_tree);
      ree.setFitHeight(300);
      ree.setFitWidth(150);
      ree.setLayoutX(-300);
      ree.setLayoutY(300);
      int h = 150 + (350 - 150) * (a - 0) / (3 - 0);
      ree.setFitHeight(h);
      tree.addElement(ree);
    }
  }

  public void makeTree(AnchorPane root, boolean priz) {
    initTree();
    if (priz) {
      for (int a = 0; a < 4; a++) {
        int h = 150 + (350 - 150) * (a - 0) / (3 - 0);
        int y = 300 - h / 2;
        var pathTree = new Path();
        pathTree.setStyle("visibility: hidden;");
        pathTree.getElements().add(new MoveTo(1800, y));
        pathTree.getElements().add(new LineTo(-200, y));
        var ptrTree = new PathTransition();
        ptrTree.setDuration(Duration.seconds(15));
        ptrTree.setDelay(Duration.seconds(5 + a * 10));
        ptrTree.setCycleCount(100);
        ptrTree.setAutoReverse(false);
        ptrTree.setPath(pathTree);
        ptrTree.setNode(tree.elementAt(a));
        ptrTree.play();
        root.getChildren().add(pathTree);
        root.getChildren().add(tree.elementAt(a));
      }
    }
  }
}
