package sortingvisualizer;

import bar.Bar;
import view.AnimationController;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public abstract class AbstractSort {

  final Color START_COLOR = Color.SKYBLUE;
  final Color SELECT_COLOR = Color.WHITE;
  final Color SORTED_COLOR = Color.CYAN;

  static int DX;
  static {
    DX = AnimationController.WINDOW_WIDTH / AnimationController.NO_OF_BARS;
  }

  ParallelTransition colorBar(Bar[] arr, Color color, int...a) {
    ParallelTransition pt = new ParallelTransition();
    
    for (int i = 0; i < a.length; i++) {
      FillTransition ft = new FillTransition();
      ft.setShape(arr[a[i]]);
      ft.setToValue(color);
      ft.setDuration(Duration.millis(100));
      pt.getChildren().add(ft);
    }
    return pt;
  }

  ParallelTransition colorBar(List<Bar> list, Color color) {
    ParallelTransition pt = new ParallelTransition();
    
    for (Bar c : list) {
      FillTransition ft = new FillTransition();
      ft.setShape(c);
      ft.setToValue(color);
      ft.setDuration(Duration.millis(100));
      pt.getChildren().add(ft);
    }

    return pt;
  }

  ParallelTransition swap(Bar[] arr, int i, int j) {
    ParallelTransition pt = new ParallelTransition();

    int dxFactor = j - i;

    pt.getChildren().addAll(arr[i].moveX(DX * dxFactor), arr[j].moveX(-DX * dxFactor));

    Bar tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;

    return pt;
  }

  public abstract ArrayList<Transition> startSort(Bar[] arr);
}
