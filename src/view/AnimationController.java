package view;

import sortingvisualizer.HeapSort;
import sortingvisualizer.MergeSort;
import sortingvisualizer.AbstractSort;
import sortingvisualizer.QuickSort;
import sortingvisualizer.SelectionSort;
import sortingvisualizer.BubbleSort;
import sortingvisualizer.InsertionSort;
import bar.Bar;
import util.RandomBars;

import javafx.animation.SequentialTransition;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimationController extends BorderPane {

  public static final int WINDOW_WIDTH = 800;
  public static final int WINDOW_HEIGHT = 500;
  public static final int XGAP = 10;
  public static final int BUTTONROW_BOUNDARY = 100;

  public static int NO_OF_BARS = 40;

  private static AbstractSort abstractSort;

  private Pane display;
  private HBox buttonRow;

  private Button sortButton;
  private Button randomButton;
  private ChoiceBox<AbstractSort> choiceBox;

  private Bar[] bars;

  public AnimationController() {
    this.display = new Pane();
    this.buttonRow = new HBox();

    this.setCenter(display);
    this.setBottom(buttonRow);

    this.sortButton = new Button("Sort");
    this.randomButton = new Button("Random");
    this.choiceBox = new ChoiceBox<>();

    this.bars = RandomBars.randomBars(NO_OF_BARS);

    buttonRow.getChildren().add(sortButton);
    buttonRow.getChildren().add(randomButton);
    buttonRow.getChildren().add(choiceBox);

    buttonRow.setAlignment(Pos.CENTER);

    for (Node b : buttonRow.getChildren()) {
      buttonRow.setMargin(b, new Insets(5, 5, 20, 5));
    }


    List<AbstractSort> abstractSortList = new ArrayList<>();
    abstractSortList.add(new BubbleSort());
    abstractSortList.add(new SelectionSort());
    abstractSortList.add(new InsertionSort());
    abstractSortList.add(new MergeSort());
    abstractSortList.add(new QuickSort());
    abstractSortList.add(new HeapSort());

    display.getChildren().addAll(Arrays.asList(bars));

    sortButton.setOnAction(event -> {
      sortButton.setDisable(true);
      randomButton.setDisable(true);

      abstractSort = choiceBox.getSelectionModel().getSelectedItem();

      SequentialTransition sq = new SequentialTransition();

      sq.getChildren().addAll(abstractSort.startSort(bars));

      sq.setOnFinished(e -> {
        randomButton.setDisable(false);
      });

      sq.play();

    });

    randomButton.setOnAction(event -> {
      sortButton.setDisable(false);
      display.getChildren().clear();

      bars = RandomBars.randomBars(NO_OF_BARS);

      display.getChildren().addAll(Arrays.asList(bars));
    });

    choiceBox.setItems(FXCollections.observableArrayList(
      abstractSortList
    ));

    choiceBox.getSelectionModel().select(5);

    choiceBox.setConverter(new StringConverter<AbstractSort>() {
      @Override
      public String toString(AbstractSort abstractSort) {
        if(abstractSort == null) {
          return "";
        } else {
          return abstractSort.getClass().getSimpleName();
        }
      }

      @Override
      public AbstractSort fromString(String s) {
        return null;
      }
    });

  }

}
