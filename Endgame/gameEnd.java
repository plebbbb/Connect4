//Brian Lee
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;
import javax.swing.*;

//JPanel class which hosts the game score, and endgame UI elements
public class gameEnd extends JPanel {
   JLabel label1;
   JPanel tmp;
   JPanel tmp2;
   JPanel tmp3;
   JLabel labelP1;
   JLabel labelP2;
   overFrame ref;
  gameEnd(overFrame p) {
    //set layout, prepare overFrame reference
    super(new GridBagLayout());
    super.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    ref = p;

    //this.setBorder(BorderFactory.createLineBorder(Color.black)); //debug for sizing evaluation

    //config of gridbagconstraints which determines positioning of panels inside this object
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor =  GridBagConstraints.FIRST_LINE_START;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.weightx = 1.0;
    gbc.weighty = 0.3;

    //tmp: combined JPanel holding the end and replay buttons
    tmp = new JPanel();
    endButton button = new endButton(p);
    tmp.add(button);
    replayButton button2 = new replayButton(p);
    tmp.add(button2);
    this.add(tmp, gbc);

    //tmp2: game status Jpanel
    tmp2 = new JPanel();
    label1 = new JLabel("The winner was:");    
    label1.setHorizontalAlignment(SwingConstants.CENTER); //set text centering
    tmp2.add(label1);
    gbc.gridy = 0;
    this.add(tmp2,gbc);
    
    //labelP1, player 1 score label
    gbc.gridy = 2;
    gbc.gridx = 0;
    labelP1 = new JLabel("E ");
    this.add(labelP1, gbc);
    
    //labelP2, player 2 score label
    gbc.gridx = 2;
    labelP2 = new JLabel("E ");
    labelP2.setHorizontalAlignment(SwingConstants.RIGHT);
    this.add(labelP2, gbc);

    getresult(-2, true); //call getresult with flag -2 and player 1 to turn replay invisible
  }

  int player1score = 0;
  int player2score = 0;
 // overFrame ref;
  

  //Text update function, updates text based on result evaluted by Game class and lists current player
  void getresult(int result, boolean playerturn){
  //  System.out.println("RESULTGET " + result); //terminal debug flag for indicating that this fucntion was called
    label1.setText("Player " + (playerturn == true ? "1" : "2") + "'s turn"); //set center textbox to player status
    if(result == -1) return; //early cull of function if result -1, no wins is returned
    tmp.setVisible(true); //sets end/replay buttons visible upon an actual game end result
    tmp2.setVisible(true);

    //switch statement which updates text depending on result flag
    switch(result){
      case 1: { //player 1 win detected by Game
        player1score += 2;
        label1.setText("Player 1 wins!");
        break;
      } 
      case 2: { //player 2 win detected
        player2score += 2;
        label1.setText("Player 2 wins!");
        break;
      }
      case 0: { //A tie
        player1score += 1;
        player2score += 1;
        label1.setText("Tied!");
        break;
      }
      case -2: { //special edge case for restarts to disable end/replay button
        tmp.setVisible(false);
        tmp2.setVisible(true);
      }
    }

    //update player score texts with new scores
    labelP1.setText("Player 1 Score: "+ player1score);
    labelP2.setText("Player 2 Score: "+ player2score);

    //Jframe and Jpanel repainting and resizing function calls to render new text
    super.validate();
    ref.pack();
    ref.repaint();
  }
}