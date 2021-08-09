//Viktoriya Zhukovskaya
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;

//smallButton class creates a button that upon click changes the grid size to 5x5
public class smallButton extends JButton{
  overFrame reference;
  smallButton(overFrame given){ //creates a small button object
    super("5x5 Field");
    reference = given; //reference to Overframe
    ActionListener buttoncheck = new ActionListener() {
      public void actionPerformed(ActionEvent e) {  //sets grid size to 5x5
         reference.generatenewFrame(5,5);
         reference.gamerender.repaint(); //repaints the new field
        }
    };
    this.addActionListener(buttoncheck);
  }
}