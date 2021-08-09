//Varshaa Maxwell

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Random;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*; 
public class welcomeScreen extends JPanel {
   JLabel welcomemsg;
   JPanel start2;
   ImageIcon icon;

  welcomeScreen(overFrame p) {  
    super();
    getLOGO(0.5f);
    //formatting position of icon
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    //generate start button
    JPanel start1 = new JPanel();
    startButton start = new startButton(p);
    start1.add(start);
    this.add(start1);    
    
    //display icon
    JPanel ef = new JPanel();
    ef.add(new JLabel(icon));
    this.add(ef, SwingConstants.CENTER);

    //print start comment
    start2 = new JPanel();
    welcomemsg = new JLabel("Click 'Start!' to begin!"); //start comment   
    start2.add(welcomemsg);
    this.add(start2);
  }

  /**getLOGO method 
  * gets a buffered image, scales it, and then updates icon with an imageicon
  */
  void getLOGO(float scaleperc){
    BufferedImage img;
    try {
      img = ImageIO.read(new File("start/LOGO.png"));
    } catch(IOException e) {return;}
    int Y = img.getHeight();
    int X = img.getWidth();
    img = resize(img,(int)(X*scaleperc), (int)(Y*scaleperc));
    icon = new ImageIcon(img);
  }

  //resizes bufferedimage input
  BufferedImage resize(BufferedImage image, int width, int height) {
    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
    Graphics2D g2d = (Graphics2D) bi.createGraphics();
    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
    g2d.drawImage(image, 0, 0, width, height, null);
    g2d.dispose();
    return bi;
  }
}