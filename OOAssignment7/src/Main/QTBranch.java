/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
/**
 * Node for non terminal nodes
 * Contains 4 nodes each can be a terminal (leaf) or non-terminal, branch node.
 * @author J.Vedder S4379101
 */
public class QTBranch implements QTNode{
    QTNode[] nodes;

    /**
     * Constructor from bitmap
     * @param Bitmap bitmap, a bitmap representation of an image
     * @param int x starting x coord
     * @param int y starting y coord
     * @param int size of the bitmap
     */
    public QTBranch(Bitmap sector, int x, int y, int size){
        nodes = new QTNode[4];
        genNodes(sector, x, y, size);
    }

    /**
     * Constructor from bit string
     * @param Readert in the reader to read bits from
     * @throws IOException if reader is malfunctioning
     */
    public QTBranch(Reader in) throws IOException{
        nodes = new QTNode[4];
        genNodes(in);
    }
    
    /**
     * Check if given quadrant is full of false or true;
     * @param int[][] quadrant to check
     * @return true if of one color false if not
     */
    private boolean checkQuadrant(boolean[][] quadrant){
        boolean first = quadrant[0][0];
        for(int i = 0; i < quadrant.length; i ++){
            for(int j = 0; j < quadrant.length; j ++){
                if(quadrant[i][j] != first){
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Fill a given bitmap from the quad tree.
     * @param int x starting coordinate
     * @param int y starting coordinate
     * @param int width of the area
     * @param Bitmap bitmap to fill
     */
    @Override
    public void fillBitmap(int x, int y, int width, Bitmap bitmap) {
        int[] xoffset = {0,width/2,0,width/2};
        int[] yoffset = {0,0,width/2,width/2};
        
        for(int i = 0; i < 4; i ++){
            QTNode n = nodes[i];
            n.fillBitmap(x + xoffset[i], y + yoffset[i], width/2, bitmap);
        }
    }

    /**
     * Writes the node/quad tree into a Writer.
     * @param Writer out to be written to
     * @throws IOException if the writer is malfunctioning
     */
    @Override
    public void writeNode(Writer out) throws IOException {
        out.append('1');
        for(int i = 0; i < 4; i ++){
            QTNode n = nodes[i];
            n.writeNode(out);
        }
    }

    /**
     * Generate nodes via reading a bitmaps given area.
     * @param Bitmap sector to read from
     * @param int x starting x coordinate
     * @param int y starting y coordinate
     * @param int size the size of this quad
     */
    private void genNodes(Bitmap sector, int x, int y, int size) {
        
        boolean[][] quadrant1 = new boolean[size/2][size/2];
        boolean[][] quadrant2 = new boolean[size/2][size/2];
        boolean[][] quadrant3 = new boolean[size/2][size/2];
        boolean[][] quadrant4 = new boolean[size/2][size/2];
        for(int j = 0; j < size/2; j++){
            for(int i = 0; i < size/2; i++){
                quadrant1[i][j] = sector.getBit(i+x, j+y);
                quadrant2[i][j] = sector.getBit(i+x+size/2, j+y);
                quadrant3[i][j] = sector.getBit(i+x+size/2, j+y+size/2);
                quadrant4[i][j] = sector.getBit(i+x, j+y+size/2);
            }
        }

        ArrayList<boolean[][]> quadrants = new ArrayList<>();
        quadrants.add(quadrant1);
        quadrants.add(quadrant2);
        quadrants.add(quadrant3);
        quadrants.add(quadrant4);
        int[] xoffsets = {x, x + size/2, x + size/2, x};
        int[] yoffsets = {y, y, y + size/2 , y + size/2};
        int[] index = {0,1,2,3};
        
        for(int i:index){
            boolean[][] q = quadrants.get(i);
            if(checkQuadrant(q)){
                nodes[i] = new QTLeaf(q[0][0]);
            }else{
                nodes[i] = new QTBranch(sector, xoffsets[i], yoffsets[i], size/2);
            }
        }
    }
    
    /**
     * Generate nodes from a bit string in Reader format
     * @param Reader in to read bits from
     * @throws IOException if the reader is malfunctioning
     */
    private void genNodes(Reader in) throws IOException{
        for(int i = 0; i < 4; i++){
            int c = in.read();
            if(c == 49){
            //is branch
                nodes[i] = new QTBranch(in);
            }else if (c == 48){
            //is leaf
                nodes[i] = new QTLeaf(in);
            }
        }
    }
}
