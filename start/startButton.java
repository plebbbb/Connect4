//Varshaa Maxwell

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;

//Starts the game upon button press
public class startButton extends JButton{
  overFrame ref;
  startButton(overFrame given){ //button constructor
    super("Start");
    ref = given;
    ActionListener buttoncheck = new ActionListener() {
      public void actionPerformed(ActionEvent e) {  //starts game upon click
        //leads to main screen
        ref.togglevisiblity();
        }
    };
    this.addActionListener(buttoncheck);
  }
}