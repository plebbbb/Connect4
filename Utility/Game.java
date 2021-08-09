//Dylan Ruth
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//game logic class which manages player turns, places coins, and checks for victory
public class Game{ //done by Dylan, debugging/fixes by Isaac
  public boolean turn = true;
  private MouseEvent e;
  //JPanel Ef;
  boolean win = false;
  Frame frame;
  gameEnd E;
  int coincount = 0;

  Game(gameEnd F/*JPanel E*/){
  //  Ef = E;
  E = F;
  //  frame = ref;
  }
  //public coincolor[][] coinSet;


  //if the connect 4 board(frame) has any user interaction, this function is called. It computes the x index which the mouse is currently in for coin preview generation and coin placement.
  public int getColumn() {
    int x = (frame.mouseX-8)/60;
    if(x > frame.coinset.length-1){
      x = frame.coinset.length-1;
    }
    return x;
  }

  //returns coincolor coorsponding to current player. Red is P1, and yellow is P2
  public  coincolor getTurn() {
    if (turn==true) {
      return coincolor.RED;
    } else {
      return coincolor.YELLOW;
    }
  }

  //swaps player turns
  public  void switchTurn() {
    turn = !turn;
  }

  //place coin function, does the nescessary array manipulation to place a new coin onto the coin status array in the connect 4 board class(frame)
  public  void placeCoin() {
    if(win) return; //if the game has ended, we don't need to place coins
//    System.out.println("PLACECOIN");
    if(isColumnFull() == false) { //prevent edge case of overfilling a column
      coincount++; //add to coin placed counter which is used to check for ties.
      if(getTurn() == coincolor.RED){ //places the coin at the top of the array. The integrated gravity function in the connect 4 board will make it fall to where it should be.
        frame.coinset[getColumn()][0] = coincolor.RED;
      } else {
        frame.coinset[getColumn()][0] = coincolor.YELLOW;
      }
      switchTurn(); //switch turn as coin has been placed
      frame.gravity(); //coin falls down to the bottom.
      
      int heightindex = 0;
      //while we already get the column from getColumn, we have no idea where the coin actually landed. We need this for checkwin2 to know the most recent changed coin.

      //loop iterates from top down from the known column until a colored coin is detected. Said coin must be the placed coin, so it's index is the height index we call for checkwin2
      for(int i = frame.coinset[0].length-1; i > -1; i--){
        if(frame.coinset[getColumn()][i] == coincolor.RED || frame.coinset[getColumn()][i] == coincolor.YELLOW) {
          heightindex = i;
        }
      }

      //temp variable which returns the current game state.
      //-1: nothing, 1: p1 win, 2: p2 win.
      int rst = checkWin2(getColumn(), heightindex); //checkwin2 finds the game state

      if(coincount == frame.coinarealimit && rst == -1) rst = 0; //if all slots are filled, and no win is achieved by anyone, set rst to 0, which is the tie state.

      //call getresult to update the gameEnd UI element to display the exit/replay buttons as well as the winner, and add the winner's score.
      E.getresult(rst, turn);

      frame.repaint(); //rerender the connect 4 board with the new coin.
    //  Ef.update(Ef.getGraphics());
    }
  }

  //checks if the selected column is full
  public boolean isColumnFull() {
    //because after each coin placement operation, we use the frame.gravity function to push it down, a coin at height x garantees that all slots under it are filled. Thus, if the topmost slot has a coin, that means that all coins in the column are full.
    if(frame.coinset[getColumn()][0] != coincolor.RED && frame.coinset[getColumn()][0] != coincolor.YELLOW){
      return false;
    } else {
      return true;
    }
  }
  
  //converts coincolors into player numbers for checkwin2 return.
  private int checkColor(coincolor a){
    if(a == coincolor.RED) return 1;
    else return 2;
  }

//full array iteration check, all possible lines in the grid are tested. Due to its inefficiency and also difficulty to debug, we have elected to switch to an alternate algo.
/*
public int checkWin() {
    System.out.println("CHECKCOIN");
    coincolor checkCoin;
    boolean win = false;
   // int result;
    boolean isfullyfilled = true;

    for(int y=0; y < frame.coinset[0].length; y++){
      for(int x=0; x < frame.coinset.length-5; x++){
        checkCoin = frame.coinset[x][y];
        System.out.println(checkCoin);
        if(checkCoin != coincolor.RED && checkCoin != coincolor.YELLOW){
          System.out.println("IFSTATEMENTCHECK");
          isfullyfilled = false;
          continue;
        }

        if(y<=3){
          for(int i=0; i < 4; i++){
            if(frame.coinset[x][y+i] != checkCoin){
              win = false;
            }
          }
          if(win==true){
            return checkColor(checkCoin);
          }
          win = true;
          for(int i=0; i < 4; i++){
            if(frame.coinset[x+i][y+i] != checkCoin){
              win = false;
            }
          }
          if(win==true){
            return checkColor(checkCoin);
          }
          win = true;
        } else {
          for(int i=0; i < 4; i++){
            if(frame.coinset[x+i][y-i] != checkCoin){
              win = false;
            }
          }
          if(win==true){
            return checkColor(checkCoin);
          }
          win = true;
        }
        for(int i=0; i < 4; i++){
          if(frame.coinset[x+i][y] != checkCoin){
            win = false;
          }
        }
        if(win==true){
          return checkColor(checkCoin);
        }
        win = true;
      }
    }
    for(int y=0; y < frame.coinset[0].length-5; y++){
      for(int x=4; x < frame.coinset.length; x++){
        checkCoin = frame.coinset[x][y];
        if(checkCoin != coincolor.RED && checkCoin != coincolor.YELLOW){
          isfullyfilled = false;
          continue;
        }
        for(int i=0; i < 4; i++){
          if(frame.coinset[x][y+i] != checkCoin){
            win = false;
          }
        }
        if(win==true){
          return checkColor(checkCoin);
        }
        win = true;
      }
    }
    //once all loops have been checked
    if(isfullyfilled == true) {return 0;} //board is filled, tie
    return -1; //if no one has won and no tie has been forced
  }
*/
//check function, optimized to check only the possible lines which can be formed from the inserted coin. Params: x and y of inserted coin
  public int checkWin2(int x, int y){
    coincolor Ccolor = frame.coinset[x][y];
    int directionalscore[] = {-1,-1,-1,-1}; //counter for score in each direction. 
    //there exists a forwards and backwards direction loop for each, which both count the current coin once, thus requiring us to use -1 to offset the double counting of the placed coin. Note that we can't just shift the for loops, because we will end up going out of array bounds at the edges of the board.

    /*
    System.out.println("x: " + x + "  y: " + y);
    System.out.println("X RIGHT LIMIT " + (frame.coinset.length - x));
    System.out.println("X LEFT LIMIT " + (x + 1));
    System.out.println("Y DOWN LIMIT " + (frame.coinset[0].length - y));
    System.out.println("Y UP LIMIT " + (y + 1));
    */

//horizontal line checks, left and right from placed
    for(int i = 0; i < frame.coinset.length - x; i++){
      if(frame.coinset[x+i][y] != Ccolor) break; //stop counting the moment a non-color is detected
      directionalscore[0]++;
    }
    for(int i = 0; i < x+1; i++){
      if(frame.coinset[x-i][y] != Ccolor) break;
      directionalscore[0]++;
    }


//vertical line checks, up and down from placed
    for(int i = 0; i < frame.coinset[0].length - y; i++){
      if(frame.coinset[x][y+i] != Ccolor) break;
      directionalscore[1]++;
    }
    for(int i = 0; i < y+1; i++){
      if(frame.coinset[x][y-i] != Ccolor) break;
      directionalscore[1]++;
    }


//Nw/SE line checks
    for(int i = 0; i < Math.min(frame.coinset.length - x, frame.coinset[0].length - y); i++){
      if(frame.coinset[x+i][y+i] != Ccolor) break;
      directionalscore[2]++;
    }
    for(int i = 0; i < Math.min(x+1,y+1); i++){
      if(frame.coinset[x-i][y-i] != Ccolor) break;
      directionalscore[2]++;
    }


//NE/SW line checks
    for(int i = 0; i < Math.min(x+1, frame.coinset[0].length - y ); i++){
      if(frame.coinset[x-i][y+i] != Ccolor) break;
      directionalscore[3]++;
    }
    for(int i = 0; i < Math.min(frame.coinset.length - x , y + 1); i++){
      if(frame.coinset[x+i][y-i] != Ccolor) break;
      directionalscore[3]++;
    }

    int returnsum = 0; //if we were to expand the project, we would add combos, for longer lines, etc. Score would be incremented by the length of the formed line
   // System.out.println("\n" + Ccolor);

    //iterates through all line types to see if any exceed the count of 4
    for(int i : directionalscore){
      //System.out.print(i + " ");
      if (i >= 4) returnsum+=i; //adds to returnsum if counts as win
    }

    //In order to lock up the placement and preview functions, we purge the preview coin from our coin state array in frame, toggle the win boolean to true to halt execution of on click actions, and rerender the connect 4 board after the preview coin has been removed
    if(returnsum != 0) {
      win = true;
      frame.purgepv();
      frame.repaint();
      return checkColor(Ccolor); //returns the player whose coin had caused a win
    }
    else return -1; //fail case, no wins.
  }
};