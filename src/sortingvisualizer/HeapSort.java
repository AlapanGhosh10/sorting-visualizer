package sortingvisualizer;

import bar.Bar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class HeapSort extends AbstractSort {
  
  private static final Color ROOT_COLOR = Color.GOLD;
  private ArrayList<Transition> transitions;

  public HeapSort() {
    this.transitions = new ArrayList<>();
  }

  private void heapify(Bar[] arr, int i, int n) {
    int left = 2 * i + 1;
    int right = 2 * i + 2;
    int max = i;

    if (left < n && arr[max].getValue() < arr[left].getValue()) {
      max = left;
    }

    if (right < n && arr[max].getValue() < arr[right].getValue()) {
      max = right;
    }

    if (max != i) {
      transitions.add(swap(arr, i, max));
      heapify(arr, max, n);
    }

  }

  private void heapSort(Bar[] arr) {
    //build initial max heap
    transitions.add(colorCNode(selectSubTree(arr, arr.length), SELECT_COLOR));
    for (int i = arr.length / 2 - 1; i >= 0; i--) {
      heapify(arr, i, arr.length);
    }
    transitions.add(colorCNode(selectSubTree(arr, arr.length), START_COLOR));

    //swap root node with final elt, heapify subarray
    for (int i = arr.length - 1; i > 0; i--) {
      transitions.add(colorCNode(arr, ROOT_COLOR, 0));
      transitions.add(swap(arr, 0, i));
      transitions.add(colorCNode(arr, START_COLOR, i));
      transitions.add(colorCNode(selectSubTree(arr, i), SELECT_COLOR));
      heapify(arr, 0, i);
      transitions.add(colorCNode(selectSubTree(arr, i), START_COLOR));
    }
  }

  private ArrayList<Bar> selectSubTree(Bar[] arr, int n) {
    ArrayList<Bar> list = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      list.add(arr[i]);
    }

    return list;
  }

  @Override
  public ArrayList<Transition> startSort(Bar[] arr) {
    heapSort(arr);

    transitions.add(colorCNode(Arrays.asList(arr), Color.ROYALBLUE));
    return transitions;
  }
}
