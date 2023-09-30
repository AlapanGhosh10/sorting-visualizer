package bar;

import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Bar extends Rectangle {

  private int val;

  public Bar(int n) {
    this.val = n;
  }

  public int getValue() {
    return this.val;
  }

  public TranslateTransition moveX(int x) {
    TranslateTransition t = new TranslateTransition();
    t.setNode(this);
    t.setDuration(Duration.millis(10));
    t.setByX(x);

    return t; 
  }

}
