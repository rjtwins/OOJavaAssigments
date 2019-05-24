package Main;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author J.Vedder S4379101 
 * Wrapper for generating and converting Quad Trees
 */
public class QTree {
    QTNode root;
    
    /**
     * Generate a quad tree from a bit string in a Reader
     * @param Reader input
     */
    public QTree( Reader input ) {
        try {
            root = readQTree( input );
        } catch (IOException ex) {
            Logger.getLogger(QTree.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Generate a Quad tree from a bitmap
     * @param Bitmap bitmap to generate from
     */
    public QTree( Bitmap bitmap ) {
        root = bitmap2QTree( 0, 0,  bitmap.getWidth(), bitmap );
    }

    /**
     * Fill a bitmap from a quad tree
     * @param Bitmap bitmap to fill
     */
    public void fillBitmap ( Bitmap bitmap ) {
        root.fillBitmap(0,0, bitmap.getWidth(), bitmap);
    }
    
    /**
     * Write tree into a Writer represented as a bit string.
     * @param Writer sb to write to
     * @throws IOException if the writer is malfunctioning
     */
    public void writeQTree( Writer sb ) throws IOException {
        root.writeNode( sb );
    }
    
    /**
     * Read and generate tree from a bit string
     * @param input
     * @return Qnode quad tree
     * @throws IOException
     */
    private static QTNode readQTree( Reader input ) throws IOException {
        int c = input.read();
        if(c == 48){
            return new QTLeaf(input);
        }else if (c == 49){
            return new QTBranch(input);
        }
        return null;
    }
    
    /**
     * Static factory method for making a quad tree from a bitmap.
     * @param int x starting coordinate
     * @param int y starting coordinate
     * @param int width with of given bitmap
     * @param Bitmap bitmap to generate tree from
     * @return
     */
    public static QTNode bitmap2QTree( int x, int y, int width, Bitmap bitmap ) {
        if(checkBitmap(bitmap)){
            return new QTLeaf(bitmap.getBit(0, 0));
        }else{
            return new QTBranch(bitmap,0, 0, bitmap.getWidth());
        }
    }
    
    /**
     * Check if the bitmap does not already consist of just one color.
     * @param Bitamp bitmap to check
     * @return True if the bitmap consists of one color, fals otherwise
     */
    private static boolean checkBitmap(Bitmap bitmap){
        boolean init = bitmap.getBit(0, 0);
        for(int y = 0; y < bitmap.getHeight(); y++){
            for(int x = 0; x < bitmap.getWidth(); x ++)
                if(init != bitmap.getBit(x, y)){
                    return false;
                }
        }
        return true;
    }

}