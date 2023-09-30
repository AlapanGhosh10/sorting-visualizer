package sortingvisualizer;

import bar.Bar;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.animation.Transition;
import javafx.scene.paint.Color;

public class QuickSort extends AbstractSort {

  private static final Color PIVOT_COLOR = Color.RED;
  private ArrayList<Transition> transitions;

  public QuickSort() {
    this.transitions = new ArrayList<>();
  }

  private void quickSort(Bar[] arr, int lo, int hi) {
    if (lo < hi) {
      int q = partition(arr, lo, hi);
      quickSort(arr, lo, q - 1);
      quickSort(arr, q + 1, hi);
    }
  }

  //last elt of array chosen as pivot ****Note: change to random
  private int partition(Bar[] arr, int lo, int hi) {
    int i = lo;

    transitions.add(colorBar(arr, PIVOT_COLOR, hi)); 

    for (int j = lo; j < hi; j++) {
      transitions.add(colorBar(arr, SELECT_COLOR, j));
      if (arr[j].getValue() < arr[hi].getValue()) {
        transitions.add(swap(arr, i, j));
        transitions.add(colorBar(arr, START_COLOR, i));
        i++;
      } else {
        transitions.add(colorBar(arr, START_COLOR, j));
      }
    }
    transitions.add(swap(arr, i, hi));
    transitions.add(colorBar(arr, START_COLOR, i));

    return i;
  }

  @Override
  public ArrayList<Transition> startSort(Bar[] arr) {
    quickSort(arr, 0, arr.length - 1);
    transitions.add(colorBar(Arrays.asList(arr), SORTED_COLOR));

    return transitions;
  }
}

