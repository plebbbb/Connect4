//Brian Lee
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;

//button to exit game
public class endButton extends JButton{
  overFrame ref;
  endButton(overFrame given){
    super("End");
    ref = given; //refrence to overFrame given for potential expansion oppertunities, such as going back to another menu via the end button.
    ActionListener buttoncheck = new ActionListener() { //if clicked
      public void actionPerformed(ActionEvent e) {
          System.exit(0); //force end program
        }
    };
    this.addActionListener(buttoncheck);
  }
}