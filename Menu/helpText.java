//Viktoriya Zhukovskaya
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;

//helpText class is used to display the instructions text
public class helpText extends JPanel{
  JLabel instructions1;
  JLabel instructions2;
  boolean state = false;
  Menu reference;
  overFrame ref2;
  helpText(Menu given, overFrame G){  //creates helpText object
    super();
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    this.setAlignmentX(Component.CENTER_ALIGNMENT); //aligns text
    //references to the Menu and to the main Overframe:
    ref2 = G;
    reference = given;
    //Two rows of instruction text
    instructions1 = new JLabel("Click on a column to place down a coin of your colour.");
    instructions2 = new JLabel("First to place four of their coins in a row (any direction) wins!");
  }

  void toggletext(){  //toggles text on and off
    this.removeAll();
    if(!state){ //if toggle is on instructions are painted
      this.add(new JPanel().add(instructions1));
      this.add(new JPanel().add(instructions2));
    }
    
    //Paints instructions on screen
    reference.validate();
    reference.repaint();
    ref2.pack();
    state = !state;
  }
}