//Brian Lee
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;

//button which resets the field for a new game
public class replayButton extends JButton{
  overFrame ref;
  replayButton(overFrame given){
    super("Replay");
    ref = given;
    ActionListener buttoncheck = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
          ref.reset(); //wipes game state data and generates new connect 4 board(frame) class
          ref.gamerender.repaint(); //updates the connect 4 board to new data
          ref.endGameScreen.tmp.setVisible(false); //hide end/replay due to new game start
          ref.gameclass.win = false;
          ref.NOR.b.getresult(-2,ref.gameclass.turn); //updates gameEnd so correct player's turn is displayed upon reset
         // ref.endGameScreen.tmp2.setVisible(false);
          ref.pack(); //reformat window for new sizing
          ref.repaint(); //repaint of overFrame to display endgamescreen player playing text
        }
    };
    this.addActionListener(buttoncheck);
  }
}