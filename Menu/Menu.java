//Viktoriya Zhukovskaya
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
public class Menu extends JPanel{
  JPanel textRow;
  JPanel textRow2;
  JPanel buttonRow;

  Menu(overFrame ref){
    super();

    //initializes the necessary JPanels for the buttons and text
    buttonRow = new JPanel();

    textRow = new JPanel();
    textRow2 = new JPanel();

    helpText text = new helpText(this,ref); //instructions text

    text.setBorder(new EmptyBorder(5,10,5,10)); //padding to prevent the text squeezing the sides of the window

    //Creates a large, small, and help button
    helpButton HButton = new helpButton("Instructions",text);
    largeButton largeFieldButton = new largeButton(ref);
    smallButton smallFieldButton = new smallButton(ref);

    //adds the three buttons to a jpanel/row
    buttonRow.add(smallFieldButton);
    buttonRow.add(largeFieldButton);
    buttonRow.add(HButton);

    //aligns the buttons
    this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    this.setAlignmentX(Component.CENTER_ALIGNMENT);

    //adds the row w/ buttons and the instruction text to the screen
    this.add(buttonRow);
    this.add(text);
  }
}