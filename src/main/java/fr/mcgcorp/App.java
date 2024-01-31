package fr.mcgcorp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
  private Button[][] board; // array representation of the game board
  private String player; // player X or O
  private int playerXCount; // counter for the number of turns player X has taken
  private int playerOCount; // counter for the number of turns player O has taken
  private Label lbl;

  public App() {
    board = new Button[3][3];
    player = "X";
    playerXCount = 0;
    playerOCount = 0;
    lbl = new Label("Turn: X");
  }

  public static void main(String[] args) {
    launch(App.class);
  }

  @Override
  public void start(Stage stage) throws Exception {

    BorderPane layout = new BorderPane();
    GridPane grid = new GridPane();

    lbl.setFont(Font.font("Monospaced", 40));
    createButtons(grid, lbl);

    grid.setVgap(10);
    grid.setHgap(10);
    grid.setAlignment(Pos.CENTER);
    layout.setTop(lbl);
    layout.setCenter(grid);

    Scene scene = new Scene(layout, 300, 300);
    stage.setScene(scene);
    stage.show();

  }

  public void createButtons(GridPane grid, Label lbl) { // create buttons and handle button presses

    for (int col = 0; col < 3; col++) {
      for (int row = 0; row < 3; row++) {
        Button btn = new Button(" ");
        btn.setFont(Font.font("Monospaced", 40));
        grid.add(btn, col, row);
        board[row][col] = btn;

        btn.setOnAction(e -> {

          if (btn.getText().equals(" ")) {
            btn.setText(player);
            if (player.equals("X")) {
              player = "O";
              lbl.setText("Turn: O");
              playerXCount++; // incement counter after every turn
              System.out.println("X: " + playerXCount); // ignore: used for debugging
              System.out.println("O: " + playerOCount); // ignore: used for debugging
              if (playerXCount % 3 == 0) { // checks for winner if the number of turns completed are in multiples of 3
                                           // (i.e. 3, 6, or 9)
                checkForWinner();
                System.out.println("Checking for Winner....");// ignore: used for debugging
              }

            } else {
              player = "X";
              lbl.setText("Turn: X");
              playerOCount++;
              System.out.println("X: " + playerXCount); // ignore: used for debugging
              System.out.println("O: " + playerOCount); // ignore: used for debugging
              if (playerOCount % 3 == 0) {// checks for winner if the number of turns completed are in multiples of 3
                                          // (i.e. 3, 6, or 9)
                checkForWinner();
                System.out.println("Checking for Winner...."); // ignore: used for debugging
              }

            }
            if (playerOCount + playerXCount == 9) { // end game if stalemate reached (max total turns of 9 played) with
                                                    // no winner
              endgame();
            }
          }

        });

      }
    }

  }

  public void checkForWinner() {
    checkRowsForWinner();
    checkColumnsForWinner();
    checkDiagonalLeftForWinner();
    checkDiagonalRightForWinner();
  }

  private void checkRowsForWinner() { // go through all three rows to check for winner and end game if symbols are the
                                      // same on a row
    for (int row = 0; row < 3; row++) {
      if (board[row][0].getText().equals(board[row][1].getText()) &&
          board[row][0].getText().equals(board[row][2].getText()) &&
          // !board[row][0].getText().isBlank()){ // encountered "Cannot find sysmbol"
          // error for isBlank()
          !board[row][0].getText().equals(" ")) { // to avoid registering three blank buttons in a line as a win
        endgame();
      }
    }

  }

  private void checkColumnsForWinner() { // go through all three columns to check for winner and end game if symbols are
                                         // the same in a column
    for (int col = 0; col < 3; col++) {
      if (board[0][col].getText().equals(board[1][col].getText()) &&
          board[0][col].getText().equals(board[2][col].getText()) &&
          // !board[0][col].getText().isBlank()){ // encountered "Cannot find sysmbol"
          // error for isBlank()
          !board[0][col].getText().equals(" ")) { // to avoid registering three blank buttons in a line as a win
        endgame();
      }
    }
  }

  private boolean checkDiagonalLeftForWinner() {
    if (board[0][0].getText().equals(board[1][1].getText()) &&
        board[0][0].getText().equals(board[2][2].getText()) &&
        // !board[0][0].getText().isBlank()){ // encountered "Cannot find sysmbol" error
        // for isBlank()
        !board[0][0].getText().equals(" ")) { // to avoid registering three blank buttons in a line as a win
      endgame();
    }

    return false;
  }

  private boolean checkDiagonalRightForWinner() {
    if (board[0][2].getText().equals(board[1][1].getText()) &&
        board[0][2].getText().equals(board[2][0].getText()) &&
        // !board[0][2].getText().isBlank()){ // encountered "Cannot find sysmbol" error
        // for isBlank()
        !board[0][2].getText().equals(" ")) { // to avoid registering three blank buttons in a line as a win
      endgame();
    }
    return false;
  }

  private void endgame() {
    lbl.setText("The end!");
    for (int row = 0; row < 3; row++) {
      for (int col = 0; col < 3; col++) {
        board[row][col].setDisable(true); // disable buttons
      }
    }

  }
}