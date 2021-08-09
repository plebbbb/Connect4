//Isaac Li
import java.awt.Color;

//enum of awt Color objects for rendering the connect 4 board
public enum colorset{
   YELLOW_TILE(new Color(244,232,86), new Color(239,219,68)),
   RED_TILE(new Color(201,78,71),new Color(194,51,45)),
   RED_TILE_PREVIEW(new Color(201, 154, 151),new Color(194, 124, 122)),
   YELLOW_TILE_PREVIEW(new Color(245, 240, 184), new Color(240, 231, 163)),
   BLUE_BACKGROUND(new Color(103,134,188)),
   WHITE_EMPTY(new Color(255,255,255),new Color(255,255,255));

   Color BORDER;
   Color CENTER;
   colorset(Color f, Color b){ //constructor for coins, with foregound and background colors
     CENTER = f;
     BORDER = b;
   }

   Color col;
   colorset(Color prim){ //constructor for monotone colors
     col = prim;
   }
};
