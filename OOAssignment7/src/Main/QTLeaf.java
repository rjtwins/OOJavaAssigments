/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * Leaf node for coloring a area/pixel a given color.
 * @author J.Vedder S4379101
 */
public class QTLeaf implements QTNode{
    private boolean color;

    /**
     * Constructor from a boolean color value.
     * @param Boolean color of the leaf (false black, true white)
     */
    QTLeaf(boolean color) {
        this.color = color;

    }

    /**
     * Constructor from a bit string
     * @param Reader input to read a bit from
     * @throws IOException if the reader is malfunctioning
     */
    QTLeaf(Reader input) throws IOException {
        int c = input.read();
        this.color = (boolean)(c == 49);
    }

    /**
     * Fill a bitmaps given area with the color of this node.
     * @param int x coordinate to start from
     * @param int y coordinate to start from
     * @param int width of the area to fill
     * @param Bitmap bitmap to fill
     */
    @Override
    public void fillBitmap(int x, int y, int width, Bitmap bitmap) {
        bitmap.fillArea(x, y, width, this.color);
    }

    /**
     * To write this node to a bit string Writer
     * @param Writer out writer to write this nodes bit to
     * @throws IOException if the writer is malfunctioning
     */
    @Override
    public void writeNode(Writer out) throws IOException{
        if(color){
            out.append("01");
        }else{
            out.append("00");
        }
    }    
}
