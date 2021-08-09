//Viktoriya Zhukovskaya
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;

//largeButton class creates a button that upon click changes the grid size to 7x6
public class largeButton extends JButton{
  overFrame reference;

  largeButton(overFrame given){ //creates large button object on screen
    super("7x6 Field");
    reference = given; //reference to the Overframe
    ActionListener buttoncheck = new ActionListener() {
      public void actionPerformed(ActionEvent e) {  //sets grid size to 7x6 upon click
         reference.generatenewFrame(7,6); 
         reference.gamerender.repaint(); //repaints the new field
        }
    };
    this.addActionListener(buttoncheck);
  }
}