package ooassigment4;

/**
 * @author J.Vedder S4379101
 * Class for the board,
 * contains the board model, can ceck itself for wins and if the board
 * is full.
 */
public class Board {
    private Field[][] field;
    public Board(){
        field = new Field[3][3];
    }
    
    /**
     * A constructor to aid in coping the board.
     * @param field
     */
    public Board(Field[][] field){
        this.field = field;
    }
    
    /**
     * Try a move, will make the move if its valid.
     * @param x coordinate on board
     * @param y coordinate on board
     * @param color of player
     * @return succes of move
     */
    public boolean play(int x, int y, Field color){
        if(x >= 3 || y >= 3)
            return false;
        if(field[x][y] != null)
            return false;
        field[x][y] = color;
        return true;
    }

    /**
     * Check if current state is winning state.
     * @return Boolean is win
     */
    public boolean winning(){
        if(checkRow() || checkCol() || checkDiag())
            return true;
        return false;
    }
    
    /**
     * Check if board is full.
     * @return boolean is full
     */
    public boolean boardFull(){
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if(this.field[i][j] == null)
                    return false;
            }
        }
        return true;
    }
    
    /**
     * Make a copy of current board.
     * @return copy of current board
     */
    public Board copy(){
        Field[][] cField = new Field[3][3];
        for(int i = 0; i < 3; i ++){
            for(int j = 0; j < 3; j++){
                cField[i][j] = this.field[i][j];
            }
        }
        return new Board(cField);
    }
    
    /**
     * Returns a printable representation of the board in a string.
     * @return String representation of the board.
     */
    @Override
    public String toString(){
        String s = "";
        //s = field[0][0].toString() + field[0][1].toString() + field[0][2].toString();
        for(int i = 0; i < 3; i ++){
            for(int j = 0; j < 3; j++){
                if(field[i][j] != null)
                    s += " " + this.field[i][j].toString() + " ";
                else
                    s += " + ";
            }
            s += "\n";
        }
        return s;
    }

    /**
     * Check if there is a row win.
     * @return boolean row win
     */
    private boolean checkRow() {
        for(int i = 0; i < 3; i++){
            if((this.field[i][0] == Field.YELLOW && this.field[i][1] == Field.YELLOW && this.field[i][2] == Field.YELLOW)
                    || this.field[i][0] == Field.RED && this.field[i][1] == Field.RED && this.field[i][2] == Field.RED){
                return true;
            }
        }
        return false;
    }

    /**
     * Check if there is a column win.
     * @return boolean column win
     */
    private boolean checkCol() {
        for(int i = 0; i < 3; i++){
            if((this.field[0][i] == Field.YELLOW && this.field[1][i] == Field.YELLOW && this.field[2][i] == Field.YELLOW)
                    || this.field[0][i] == Field.RED && this.field[1][i] == Field.RED && this.field[2][i] == Field.RED){
                return true;
            }
        }
        return false;    
    }

    /**
     * Check if there is a diagonal win.
     * @return boolean diagonal win
     */
    private boolean checkDiag() {
        for(int i = 0; i < 3; i++){
            if((this.field[0][0] == Field.YELLOW && this.field[1][1] == Field.YELLOW && this.field[2][2] == Field.YELLOW)
                    || this.field[0][0] == Field.RED && this.field[1][1] == Field.RED && this.field[2][2] == Field.RED){
                return true;
            }
        }
        return false;  
    }
}
