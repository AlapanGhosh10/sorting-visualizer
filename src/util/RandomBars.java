package util;

import bar.Bar;
import view.AnimationController;

import java.util.Random;

import javafx.scene.paint.Color;

public class RandomBars {

  public RandomBars() {

  }

  public static Bar[] randomBars(int n) {
    Bar[] arr = new Bar[n];
    Random r = new Random();

    for (int i = 0; i < arr.length; i++) {
      arr[i] = new Bar(1 + r.nextInt(arr.length));
      arr[i].setX(i * (AnimationController.WINDOW_WIDTH / arr.length));
      arr[i].setFill(Color.CRIMSON);
      setBarDimention(arr[i], arr.length);
    }
    return arr;
 
  }

  private static void setBarDimention(Bar cnode, int n) {
    cnode.setWidth(AnimationController.WINDOW_WIDTH / n -
                    AnimationController.XGAP);
    cnode.setHeight(((AnimationController.WINDOW_HEIGHT - AnimationController.BUTTONROW_BOUNDARY) 
                      / n) *
                      cnode.getValue());
  }
}
