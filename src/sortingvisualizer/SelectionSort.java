package sortingvisualizer;

import bar.Bar;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;

public class SelectionSort extends AbstractSort {

  private static final Color MININDX_COLOR = Color.CYAN;
  private static final Color NEW_MININDX_COLOR = Color.LIGHTGREEN;

  private ParallelTransition colorBar(Bar[] arr, int a, int b, Color colorA, Color colorB) {
    ParallelTransition pt = new ParallelTransition();

    pt.getChildren().addAll(colorBar(arr, colorA, a), colorBar(arr, colorB, b));

    return pt;
  }

  @Override
  public ArrayList<Transition> startSort(Bar[] arr) {
    ArrayList<Transition> transitions = new ArrayList<>();
    int minIndx;

    for (int i = 0; i < arr.length - 1; i++) {
      minIndx = i;
      transitions.add(colorBar(arr, NEW_MININDX_COLOR, minIndx));

      for (int j = i + 1; j < arr.length; j++) {
        transitions.add(colorBar(arr, SELECT_COLOR, j));
        if (arr[j].getValue() < arr[minIndx].getValue()) {
          if (minIndx == i) {
            transitions.add(colorBar(arr, minIndx, j, MININDX_COLOR, NEW_MININDX_COLOR));
          } else {
            transitions.add(colorBar(arr, minIndx, j, Color.CRIMSON, NEW_MININDX_COLOR));
          }
          minIndx = j;
        } else {
          transitions.add(colorBar(arr, START_COLOR, j));
        }
      }

      if (minIndx != i) {
        transitions.add(swap(arr, i, minIndx));
      }

        transitions.add(colorBar(arr, START_COLOR, i, minIndx));
    }

    transitions.add(colorBar(Arrays.asList(arr), SORTED_COLOR));

    return transitions;
  }
}
