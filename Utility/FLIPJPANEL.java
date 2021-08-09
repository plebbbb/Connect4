//Isaac Li
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//container class for a welcomescreen and a gameend, because the java container which overFrame uses allows only one panel at each of its three center-aligned positions. This overrides that bottleneck, and lets us keep the welcome screen centered as well.
public class FLIPJPANEL extends JPanel{ //done by Isaac
  welcomeScreen a;
  gameEnd b;

  FLIPJPANEL(welcomeScreen e, gameEnd f){
    super();
    a = e; b = f;
    this.add(a); //add the elements to the JPanel. Their visiblity is determined by overFrame functions
    this.add(f);
  }

}