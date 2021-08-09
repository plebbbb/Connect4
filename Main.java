/*
* Connect4
* Isaac Li, Varshaa Maxwell, Dylan Ruth, Brian Lee, Viktoriya Zhukovskaya
* ISC4U1
* June 18, 2021
*/

import java.awt.*; 
import javax.swing.*;
import java.util.*;

class Main {
  static overFrame test = new overFrame(7,6,50); //constructs 7x6 connect 4 board, 50px coin diameter
  public static void main(String[] args) {
    /*
    //Test for score updating function call
    for(;;){
        try{
              Thread.sleep(2000);
          }
          catch(InterruptedException ex){
              Thread.currentThread().interrupt();
          }
    test.endGameScreen.getresult();
    }*/

    //BELOW: tests for various class functions
    //test.generatenewFrame(7,6);
   // frameratetest();
    // test.gamerender.repaint();

    //while(true){ //test loop
      //Game.coinPreview();
      //test.gamerender.repaint();
    //}
  }

  //just a test to test for single buffer flickering
  static void frameratetest(){
    while(true){
        try{
              Thread.sleep(100);
          }
          catch(InterruptedException ex){
              Thread.currentThread().interrupt();
          }
          test.gamerender.RNGgen();
          test.gamerender.repaint();
    }
  }

  //refrence test
  static void testref(){
      int i = 0;
    while(true){
      try{
        Thread.sleep(100);
          }
      catch(InterruptedException ex){
              Thread.currentThread().interrupt();
        }
 //       test.value = Integer.toString(i++);
      }
  }
}