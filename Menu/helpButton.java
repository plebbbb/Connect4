//Viktoriya Zhukovskaya
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;

//When clicked the help button displays game instructions found in helpText.java
public class helpButton extends JButton{
  JLabel instructions1;
  JLabel instructions2;
  helpButton(String text, helpText given){
    super(text); //button label passed as text, says "instructions"
    ActionListener buttoncheck = new ActionListener() { 
      public void actionPerformed(ActionEvent e) {
        given.toggletext(); //toggles instructions on and off when button is clicked
      }
    };
    this.addActionListener(buttoncheck);
  }
}