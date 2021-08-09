//Isaac Li
import java.awt.*;
import javax.swing.*;

//Parent JFrame class which renders everything.
public class overFrame extends JFrame{
  Game gameclass; //game logic checking class

  JPanel game;    //JPanel wrapper layer which contains the Frame object
  Frame gamerender;

  FLIPJPANEL NOR; //JPanel container object so we can render endGameScreen and welcomeS on the same Jframe panel location.
  gameEnd endGameScreen; //displays player scores, shows replay button
  welcomeScreen welcomeS; //logo and start button

  Menu menu; //bottom menu for reading instructions and changing field size
  boolean visibility = true; //in case we ever want to go back to the welcome screen, we have it set as a toggle


  overFrame(int x, int y, int diam){
    super("Connect 4"); //name window connect 4

    endGameScreen = new gameEnd(this); //construct and assign our welcomescreen and gameend
    welcomeS = new welcomeScreen(this);
    NOR = new FLIPJPANEL(welcomeS, endGameScreen); //pass references through to NOR for top panel rendering

    gameclass = new Game(endGameScreen); //game logic class, takes reference to end game screen

    game = new JPanel(); 
    //game field render Jpanel. This is layered on top of the Frame's canvas object is so that we can keep everything centered properly
    gamerender = new Frame(x,y,diam,gameclass);
   // game.setDoubleBuffered(true);
    game.add(gamerender);
    gameclass.frame = gamerender; //gives game logic class reference to frame

    menu = new Menu(this); //aconstructs menu
/*
    exampleJPanel tt = new exampleJPanel();
*/
/*
    //filler text Jpanel
    JPanel title = new JPanel();
    JLabel e = new JLabel("Filler text"); //filler text
    title.add(e);
*/

    //add JPanel classes to the Jframe
    super.getContentPane().add(BorderLayout.NORTH,NOR);
    super.getContentPane().add(BorderLayout.SOUTH, menu);
    super.getContentPane().add(BorderLayout.CENTER, game);
  //  super.getContentPane().add(BorderLayout.NORTH,title); 
    super.pack(); //scale so all fits
    super.setVisible((true));
    togglevisiblity(); //update class to determine whether welcomeS or endgameScreen is visible
  }
  

  //to minimize references, objects with overFrame references can call this to affect gamerender if they dont have the reference
  void reset(){
    gamerender.reroll();
  }


  //toggles welcome/in game screen
  void togglevisiblity(){
    if (visibility){ //determines which screens to show based on visiblity variable
      NOR.a.setVisible(true);
      menu.setVisible(false);
      NOR.b.setVisible(false);
      game.setVisible(false);
    } else {
      menu.setVisible(true);
      NOR.b.setVisible(true);
      game.setVisible(true);
      NOR.a.setVisible(false);
    }
    visibility = !visibility;
    super.pack(); //reformat
    validate(); 
    repaint(); //rerender
  }

  //generates a new Frame of specified size
  void generatenewFrame(int x, int y){
    gameclass.win = false;
    game.removeAll(); //wipes out all references to frames
    gamerender = new Frame(x,y,50,gameclass); //generates a new frame of specified size
    gamerender.coinarealimit = x * y; //computes coin placement limit, for tie counting
    gameclass.coincount = 0; //resets coin placement counter in game logic class as new board
    gameclass.frame = gamerender; //adds back a reference to frame for gameclass
    game.add(gamerender); //adds new frame to game parent Jpanel

    //rescaling and rerendering functions
    super.pack();
    validate();
    repaint();
  }
}