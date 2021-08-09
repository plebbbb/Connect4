//Isaac Li
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.*; 
import javax.swing.*;
import java.util.Random;

public class Frame extends Canvas{
  public coincolor[][] coinset; //array which describes our coin positions on the board
  Dimension offDimension; //temp image dimension storage
  Image offImage; //temp image storage buffer
  Graphics2D offGraphics; //buffer graphics2D to prevent premature framebuffer clearing
  Game ref; //reference to game logic class so it can be called upon click
  int coinarealimit = 0;//calculated maximum amount of coins on board. If the coin couter in the game logic class hits this, there are no more possible locations to place coins.
  int coindiam;
  int previndex = -1;
  public int height;
  public int width;
  public int mouseX;
  boolean wasupdated = false;
 // boolean firstrender = false;
  Frame(int width, int height, int diam, Game refer){ //width and height in coins
    super();
    ref = refer;
    coinset = new coincolor[width][height]; //set array to right size
    coinarealimit = width * height; //compute max num of coins
   // RNGgen();

    blankgen(); //fill array with coincolor EMPTY
    gravity(); //edge case test: gravity when no coins exist

    //set size of board to accomindate for coins and padding
    super.setSize((diam+10)*width+10,(diam+10)*height+10);
    coindiam = diam;
    this.height = height;
    this.width = width;


    addMouseMotionListener(new MouseAdapter() {
      public void mouseMoved(MouseEvent e) {
        mouseX = e.getX(); 
        if (!wasupdated){
          if(ref.win) return; //prevent mouse preview if game ended
          if(previndex != ref.getColumn()){ //optimization: rerender preview coin only if the mouse moved to new index
            previndex = ref.getColumn();
            applypreview(previndex,ref.getTurn() == coincolor.YELLOW ?  coincolor.PREVIEW_YELLOW : coincolor.PREVIEW_RED); //applies preview to specified array index
            wasupdated = true;
            repaint();
         //   E.update(E.getGraphics());
          }
        }
      }
    }
    );
    addMouseListener(new MouseListener() {
      @Override
      //calls game state update functions in the game logic class, as well as places the coin
      public void mousePressed(MouseEvent e) {
       // System.out.println("CLICK");
        if(ref.win) return; //prevent mouse preview if game ender
        ref.placeCoin();
        previndex = ref.getColumn();
        applypreview(previndex,ref.getTurn() == coincolor.YELLOW ?  coincolor.PREVIEW_YELLOW : coincolor.PREVIEW_RED);
        wasupdated = true; //optimization: rerender only if coin has been placed
        repaint();
      }

      public void mouseExited(MouseEvent e){}

      public void mouseEntered(MouseEvent e){}

      public void mouseClicked(MouseEvent e){}

      public void mouseReleased(MouseEvent e){}

    });
  }

  //empties array
  void blankgen(){
    for (int y = 0; y < coinset[0].length; y++){
      for (int x = 0; x < coinset.length; x++){
        coinset[x][y] = coincolor.EMPTY;
      }
    }
  }

  //RNG coin generation for rendering tests
  void RNGgen(){
    Random rand = new Random();
    for (int y = 0; y < coinset[0].length; y++){
      for (int x = 0; x < coinset.length; x++){
        coinset[x][y] = coincolor.values()[rand.nextInt(3)];
        System.out.print(String.format("%-7s", coinset[x][y]));
      }
      System.out.println("");
    }
  }

//wipes array, and spawns preview coin in as it was also deleted
  void reroll(){
    //RNGgen();
    //gravity();
    blankgen(); 
    previndex = ref.getColumn();
    applypreview(previndex,ref.getTurn() == coincolor.YELLOW ?  coincolor.PREVIEW_YELLOW : coincolor.PREVIEW_RED);
    wasupdated = true;
    repaint();
  }

  //debug coin array state print
  void displayStateCheck(){
    System.out.println("\nDEBUG: COIN RENDER STATE");
    for (int y = 0; y < coinset[0].length; y++){
      for (int x = 0; x < coinset.length; x++){
        System.out.print(String.format("%-7s", coinset[x][y]));
      }
      System.out.println("");
    }
  }

  //override call to prevent parent class paint() from being called. paint() wipes the entire canvas, causing the entire thing to have to be rerendered.
  public void paint(Graphics g){
    update(g);
  }

  //TBD: coins could be converted into classes
  public void update(Graphics g){
    Dimension d = size();

    //configuration of the image buffer
    if ( (offGraphics == null)
          || (d.width != offDimension.width)
          || (d.height != offDimension.height) ) {
            offDimension = d;
            offImage = createImage(d.width, d.height);
            offGraphics = (Graphics2D)offImage.getGraphics();
    }


    //applypreview(0,coincolor.PREVIEW_YELLOW);
    wasupdated = false;
 //   displayStateCheck();

    //configures game render settings
    Graphics2D gg = offGraphics;
    gg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
  //  if(!firstrender){
      gg.setColor((Color)colorset.BLUE_BACKGROUND.col); 
      gg.fillRoundRect(0,0,super.getWidth(),super.getHeight(),coindiam,coindiam);
    //  firstrender = true;
   // }
    colorset selectedC = colorset.WHITE_EMPTY;
    //iterate through array
    for(int y = 0; y < coinset[0].length; y++){
      for (int x = 0; x < coinset.length; x++){
        try {
          switch(coinset[x][y]){
            case RED:
              selectedC = colorset.RED_TILE;
              break;
            case YELLOW:
              selectedC = colorset.YELLOW_TILE; 
              break;
            case EMPTY : 
              selectedC = colorset.WHITE_EMPTY;
              break;
            case PREVIEW_RED:
              selectedC = colorset.RED_TILE_PREVIEW;
              coinset[x][y] = coincolor.EMPTY; //clear out preview to be readded to the array in applypreview call
              break;
            case PREVIEW_YELLOW:
              selectedC = colorset.YELLOW_TILE_PREVIEW;
              coinset[x][y] = coincolor.EMPTY; //see comment in preview_red
              break;
          }

          //render background color for coin
          gg.setColor(selectedC.BORDER);
          gg.fillOval((x)*(coindiam+10) + 15, (y)*(coindiam+10) + 15, coindiam-10, coindiam-10); 

          //render center color for coin
          gg.setColor(selectedC.CENTER);
          gg.fillOval((x)*(coindiam+10) + 20, (y)*(coindiam+10) + 20, coindiam-20, coindiam-20);

        }
        catch(NullPointerException ex){
          System.out.println(coinset[x][y]);
        }
      }
    }
    //push completed image in buffer to display
    g.drawImage(offImage,0,0,this);
    //repaint();
  }

  //generates preview coin in coin array for rendering
  public void applypreview(int row, coincolor preview){
    if (ref.win) return; //if game over, no need for previews
    if(row < 0 || row > coinset.length) return; //catches out of bound
    for(int i = coinset[row].length-1; i > -1 ; i--){ //iterates upwards from the selected row
      switch(coinset[row][i]){ //exit out of function the moment we identify an empty spot
        case EMPTY: //note: the rendering function auto clears instances of PREVIEW
          coinset[row][i] = preview; //PREVIEW COLOR SHOULD BE PREVIEW_RED OR PREVIEW_YELLOW. ALTERNATE OPTION IS TO CHECK BY PLAYER BUT THAT WOULD REQUIRE THIS TO BE IN A GAME STATE CLASS WHICH DOESNT EXIST YET
          return;
        default: //if not empty
      }
    }
  }
// /*

// wipes preview colors
public void purgepv(){
  //iterate array
    for(int y = 0; y < coinset[0].length; y++){
      for (int x = 0; x < coinset.length; x++){
          switch(coinset[x][y]){
            case PREVIEW_RED: //if one of the preview indicators, remove it.
              coinset[x][y] = coincolor.EMPTY;
              break;
            case PREVIEW_YELLOW:
              coinset[x][y] = coincolor.EMPTY; 
              break;
            default:
          }
      }
  }
}

  //gravity function, which forces all coin elements towards the bottom of the grid.
  public void gravity(){
    for(int x = 0; x < coinset.length; x++){ //goes through all the columns
       for(int y = coinset[0].length-1; y > -1; y--){ //iterate upwards through the column
        if(coinset[x][y] == coincolor.EMPTY){//if an empty spot is detected whihc can be filled
        
          //search for loop, iterates from current empty point upwards until next detected color
          for(int z = y-1; z > -1; z--){
            if(coinset[x][z] != coincolor.EMPTY){ //identification of color
              coinset[x][y] = coinset[x][z]; //transfer over coin from above down
              coinset[x][z] = coincolor.EMPTY; //replace old spot with EMPTY
              break; //continue Y for loop iteration until next empty.
            }
          }
          //no idea why but cant break here without breaking algo
          //break; //exit out of Y for loop if the Z loop reached the top of the column as there are no more coins to sort
        }
      }
    }
  }
}