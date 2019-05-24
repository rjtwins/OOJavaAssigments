package Main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Jorre Vedder S4379101
 * 
 * Modified class, Added a static array of directions ease of use.
 * Added an heuristic and predecessor for heuristic search and path traceback.
 * Modified toString for more equally spaced representation.
 * Implemented isSolution, equals, successors, compare to, pathfromroot and hashCode.
 * Added isSolution, flatten, copyBoard, isSolvable, setParent
 */

/**
 * @author Pieter Koopman, Sjaak Smetsers
 * @version 1.3
 * @date 07-03-2016
 * A template implementation of a sliding game 
 * implementing the Graph interface
 */
public class SlidingGame implements Configuration {

    public static final int N = 4, SIZE = N * N, HOLE = SIZE;
    public static final Direction[] DIRECTIONS = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
    /**
     * The board is represented by a 2-dimensional array; the position of the
     * hole is kept in 2 variables holeX and holeY
     * There is the heuristic value h of this node to the end state.
     */
    private int[][] board;
    private int holeX, holeY;
    private int h;
    private Configuration predecessor;

    /**
     * A constructor that initializes the board with the specified array
     * Also calculates the heuristic to the N,N coordinate
     * @param start: a one dimensional array containing the initial board. The
     * elements of start are stored row-wise.
     */
    public SlidingGame(int[] start) {
        board = new int[N][N];

        assert start.length == N * N : "Length of specified board incorrect";

        for (int p = 0; p < start.length; p++) {
            board[p % N][p / N] = start[p];
            if (start[p] == HOLE) {
                holeX = p % N;
                holeY = p / N;
            }
        }
        this.h =  Math.abs(N-1 - this.holeX) + Math.abs(N-1 - this.holeY);
    }

    /**
     * Converts a board into a printable representation. The hole is displayed
     * as a empty space.
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                int puzzel = board[col][row];
                buf.append(puzzel == HOLE ? "\t" : puzzel + "\t");
            }
            buf.append("\n\n");
        }
        return buf.toString();
    }

    /**
     * Checks if this configuration is equal to a given object.
     * Checks if the object given was not null, of equal class and of equal
     * content. If equal returns true, else returns false.
     * @param o
     * @return true if o is not null, has the same class and the same content
     */
    @Override
    public boolean equals(Object o) {
        if( o == null){
            return false;
        }
        if (this.getClass() != o.getClass()){
            return false;
        }
        SlidingGame B = (SlidingGame) o;
        if(this.holeX != B.holeX || this.holeY != B.holeY)
            return false;
        
        for(int x = 0; x < N; x ++){
            for(int y = 0; y < N; y ++){
                if(this.board[x][y] != B.board[x][y])
                    return false;
            }
        }
        return true;
    }

    /**
     * Checks if this configuration is a valid solution.
     * Does so by counting up with the index of a flat representation of
     * the sliding game board.
     * @return true if valid solution false if not.
     */
    @Override
    public boolean isSolution() {
        int i = 1;
        for(int row = 0; row < N; row++){
            for(int col = 0; col < N; col++){
                if(this.board[col][row] != i){
                    return false;
                }
                i++;
            }
        }
        return true;
    }

    /**
     * Generates all possible successors to the current configuration.
     * Does so by looping over directions and applying dx,dy to the hole locations
     * in a copy of the the current board. Then generating a new configuration 
     * with this new board.
     * @return a list of generated successor configurations.
     */
    @Override
    public Collection<Configuration> successors() { 
        ArrayList<Configuration> succesors = new ArrayList<>();
        int[][] newBoard;
        for(Direction dir:DIRECTIONS){
            newBoard = copyBoard();
            int newX = this.holeX + dir.GetDX();
            int newY = this.holeY + dir.GetDY();
            if(newX >= N || newY >= N || newX < 0 || newY < 0)
                continue;
            newBoard[this.holeX][this.holeY] = newBoard[newX][newY];
            newBoard[newX][newY] = HOLE;
            Configuration newGame = new SlidingGame(this.flatten(newBoard));
            newGame.setParent(this);
            succesors.add(newGame);
        }
        return succesors;
    }

    /**
     * Compare two objects for a priority queue.
     * If this object is the same state as being compared to, return 0
     * If this objects heuristic is smaller, bigger, equal then the 
     * object being compared to return -1, 1, 0.
     * @param g
     * @return o if equal, -1 if smaller 1 if bigger 0 if same "size".
     */
    @Override
    public int compareTo(Configuration g) {
        if(this.equals(g)){
            return 0;
        }else if(this.h < g.getH()){
            return -1;
        }else if(this.h > g.getH()){
            return 1;
        }
        return 0;
    }

    /**
     * Getter for this configurations parent.
     * @return this configurations parent configuration
     */
    @Override
    public Configuration parent() {
        return this.predecessor;
    }
    
    /**
     * Generates a flattened representation of a given 2d array.
     * @param int[][] array to be flattened
     * @return flattened int[] representation of given int[][] array
     */
    private int[] flatten(int[][] array){
        int[] flatArray = new int[SIZE];
        int flatIndex = 0;
        for(int row = 0; row < this.N; row ++){
            for(int col = 0; col < this.N; col ++){
                flatArray[flatIndex] = array[col][row];
                flatIndex++;
                //System.out.print(array[col][row]);
            }
        }
        //System.out.println(flatArray.length);
        return flatArray;
    }
    
    /**
     * Copy the board over by value and return it.
     * @return int[][] array copy of board
     */
    private int[][] copyBoard(){
        int[][] copy = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                copy[i][j] = this.board[i][j];
            }
        }
        return copy;
    }
    
    /**
     * Setter for the parent.
     * @param Configuration parent to set parent to
     */
    @Override
    public void setParent(Configuration parent) {
        this.predecessor = parent;
    }
    
    /**
     * Gets the path that lead to this configuration by calling this method in
     * its parent and adding itself to the list.
     * @param List<Configuration> path from root to this
     */
    @Override
    public List<Configuration> pathFromRoot() {
        List<Configuration> c;
        if(this.parent() != null){
            c = this.parent().pathFromRoot();
        }else{
            c = new ArrayList<>();
        }
        c.add(this);
        return c;
    }

    /**
     * Generate a hash code from the content of this configuration
     * @return int hashcode
     */
    @Override
    public int hashCode() {
        int hash = 0;
        for ( int x = N-1; x >= 0; x-- ) {
            for ( int y = N-1; y >= 0; y-- ) {
                hash = 31 * hash + board[x][y];
            }
        }
        return hash; 
    }

    
    @Override
    public int getH() {
        return this.h;
    }
    
    /**
     * Checks solvability by counting the number of inversions.
     * An inversion is the amount of lower numbers a higher number proceeds
     * in a flat array. If the number of inversions is even there is a solution.
     * Else there is no solution.
     * @return
     */
    public boolean isSolvable(){
        int[] flat = flatten(this.board);
        int inversions = 0;
        for(int i = 0; i < flat.length; i++){
            if(flat[i] == N){
                continue;
            } 
            for(int j = i; j < flat.length; j ++){
                if(flat[j] > flat[i]){
                    inversions++;
                }
            }
        }
        if(inversions % 2 == 1){
            return true;
        }else{
            return false;
        }
    }
}