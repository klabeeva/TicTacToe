package com.klabeeva.TicTacToe;

import java.util.Arrays;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

/**
 *  The class implements tic-tac-toe game board.
 *   All 'logic' function (like finish the row/column/diagonal and win or
 *   prevent user from winning) are implemented here.
 *   Might need additional functionality for bigger board
 *
 */
public class TttBoard {
    int mBoardDim = 3;   // square board dimension;
    int mGameDim = 3;    // number of consecutive elements to win game
    int mMaxIndex = mBoardDim * mBoardDim; // size of mData
    char [] mData;     // game array; "x", "0", " " -free
    int mUsed = 0;    // number of used cells

    /**
     * Constructor
     * @param iBoardDim - board size
     * @param iGameDim - number of consecutive symbols required for winning
     * @throws Exception
     */
    public TttBoard(int iBoardDim,int iGameDim) throws Exception{
        setDimension(iBoardDim, iGameDim);
        resetBoard();
    }

    /**
     * Default 3X3 board
     */
    public TttBoard()  throws Exception{
        if(mBoardDim != 3 || mGameDim != 3)
            setDimension(3,3);
        else
            mData = new char[mMaxIndex];
        resetBoard();
    }

    /**
     * Sets all board cells to spaces
     */
    final void resetBoard (){
        Arrays.fill(mData,' ');
        mUsed = 0;
    }

    /**
     * Initialize board data
     */
    private void setDimension (int iBoardDim, int iGameDim) throws Exception{
        if(iBoardDim < 3 || iGameDim <3 || iGameDim > iBoardDim){
            throw new TttException("attempt to set wrong board dimensions");
        }
        mBoardDim = iBoardDim;
        mGameDim = iGameDim;
        mMaxIndex = mBoardDim*mBoardDim;
        mData = new char[mMaxIndex];
    }

   /************************
    * get data section
    */

    public char [] getData() {
        return mData;
    }

    public int getUsedCellsNumber(){
        return mUsed;
    }

    char getSymbol(boolean bCross){
        return (bCross) ? 'x' : '0';
    }
   /*************  end get data section */


    /**
     * returns index in mData array
     * throws exception for out of range columns/rows
     */
    int getIndex(int iRow, int iCol) throws Exception{
        if(iRow < 0 || iRow >= mBoardDim) throw new TttException("wrong row value");
        if(iCol < 0 || iCol >= mBoardDim) throw new TttException("wrong column value");
        return iRow * mBoardDim + iCol;
    }

    /**
     * returns true if the cell has been used
     * false otherwise
     */
    private boolean isUsed(int index) throws Exception{
        if(index < 0 || index >= mMaxIndex)
            throw new TttException("cell index is out of range");

        return mData[index] != ' ';
    }


    /**
     * returns true if the cell has been used
     * false otherwise
     */
    private boolean isCellEmpty(int index) throws Exception{
        if(index < 0 || index >= mMaxIndex)
            throw new TttException("cell index is out of range");

        return mData[index] == ' ';
    }

    /**
     * returns true if all cells have been used
     * false otherwise
     */
     public boolean isFull() {
        return mUsed ==  mMaxIndex;
    }

     /**
      * returns true if none of the cells have been used
      * false otherwise
      */
    public boolean isEmpty() {
        return mUsed == 0;
    }

    /**
     * sets current board state:
     * sets data received from input stream to the board
     *
     */
    public void setBoardData(String str) throws Exception {
            if(str == null || str.isEmpty()){
                resetBoard();
                return;
            }
            if(str.length()!= mMaxIndex)
                throw new TttException ("wrong size board data");

            if(!str.matches("^[0x ]+$"))
                throw new TttException ("wrong symbols in board data");

            mData = str.toCharArray();
            mUsed = mMaxIndex - (int) str.chars().filter(ch -> ch ==' ').count();
    }

    /**
     * formats board data to String for output stream
     * @return
     */
    public String getBoardDataAsStr(){
            return new String(mData);
    }

    /**
     * makes a move
     * sets cell to 1 (cross) or 2 (zero)
     */
    boolean addMove(int index, char symb) throws Exception {
        if(isUsed(index)) return false;
        mData[index] = symb;
        mUsed++;
        return true;
    }

    /**
     * makes a move
     * sets cell to "cross" or "zero"
     */
    public boolean addMove(int index, boolean bCross) throws Exception {
        char sym = getSymbol(bCross);
        return addMove(index, sym);
    }

    /**
     * sets middle cell to "cross" or "zero"
     * returns false if cell is already taken
     */
    public boolean setBoardCenter(boolean bCross) throws Exception {

        int index = mMaxIndex/2;
        if(!isUsed(index)){
            addMove(index, getSymbol(bCross));
            return true;
        }
        return false;
    }

    /**
     * checks if cross or zero has lost
     */
    public boolean hasLost(boolean bCross){
        return hasWon(!bCross);
    }
    /**
     * checks if cross or zero has won
     * if mBoardDim == mGameDim uses hasFull algorithm
     */
    public boolean hasWon(boolean bCross){
        char iSym = getSymbol(bCross);
        if(mBoardDim == mGameDim)
            return hasFull(iSym);

        return hasWon(iSym, (i, j) -> i * mBoardDim +j)
                || hasWon(iSym, (i, j) -> j * mBoardDim +i)
                || hasWonDiagonal(iSym, (i) -> i * mBoardDim +i)
                || hasWonDiagonal(iSym, (i) -> i * mBoardDim + mBoardDim - i-1)
                ;

    }

    /**
     * checks  if 'iSym' (cross or zero)  has  won in row or column
     * (has mGameDim consecutive symbols)
     */
     boolean hasWon(char iSym, IntBinaryOperator getTheIndex){
        int iCount = 0;
        for (int i = 0 ; i < mBoardDim; i++){
            for(int j =0; j < mBoardDim; j++){
                int index = getTheIndex.applyAsInt(i,j);
                if(mData[index] == iSym )
                    iCount++;
                else
                    iCount =0;
            }
        }
        return iCount >= mGameDim;
    }

    /**
     * checks  if 'iSym' (cross or zero)  has  won in diagonal
     * (has mGameDim consecutive symbols)
     */
     boolean hasWonDiagonal(char iSym, IntUnaryOperator getTheIndex){
        int iCount = 0;
        for (int i = 0 ; i < mBoardDim; i++){
            int index = getTheIndex.applyAsInt(i);
            if(mData[index] == iSym )
                    iCount++;
            else
                    iCount =0;
        }
        return iCount >= mGameDim;
    }

     /**
      * checks if  cross or zero has won
      * for mBoardDim == mGameDim case
      */
    boolean hasFull(char iSym){
        return hasFull(iSym, (i, j) -> i * mBoardDim +j)
                || hasFull(iSym, (i, j) -> j * mBoardDim +i)
                || hasFullDiagonal(iSym, (i) -> i * mBoardDim +i)
                || hasFullDiagonal(iSym, (i) -> i * mBoardDim + mBoardDim - i-1)
                ;
    }

    /**
     * checks  if 'iSym' (cross or zero)  fills up column or row
      * for mBoardDim == mGameDim case
     */
     boolean hasFull(char iSym, IntBinaryOperator getTheIndex){
        for (int i = 0 ; i < mBoardDim; i++){
            boolean bNo = false;
            for(int j =0; j < mBoardDim; j++){
                int index = getTheIndex.applyAsInt(i,j);
                if(mData[index] != iSym ){
                    bNo = true;
                    break;
                }
            }
            if(bNo == false) return true;
        }
        return false;
    }

     /**
      * checks  if 'iSym' (cross or zero)  fills up diagonal
      * for mBoardDim == mGameDim case
      */
    boolean hasFullDiagonal(char iSym, IntUnaryOperator getTheIndex) {
        boolean bNo = false;
        for (int i = 0 ; i < mBoardDim; i++){
            int index = getTheIndex.applyAsInt(i);
            if(mData[index] != iSym ){
                    bNo = true;
                    break;
            }
        }
        return !bNo;
    }

    /**
     * if there is one step before the row/column/diagonal wins, do it (and win)
     * @param bCross
     * @return true if won else otherwise
     * @throws Exception
     */

    public boolean finish(boolean bCross) throws Exception{
        char iSym = getSymbol(bCross);
        return doFinish(iSym, (i, j) -> i * mBoardDim +j)
                || doFinish(iSym, (i, j) -> j * mBoardDim +i)
                || doFinishDiagonal(iSym, (i) -> i * mBoardDim +i)
                || doFinishDiagonal(iSym, (i) -> i * mBoardDim + mBoardDim - i-1)
                ;
    }

    /**
     * if there is one step before the row/column wins, do it and win
     * @param iSym
     * @param getTheIndex
     * @return
     * @throws Exception
     */
    boolean doFinish(char iSym , IntBinaryOperator getTheIndex) throws Exception {
        int index = this.findUnfinishedRowOrColumn(iSym, getTheIndex);

        if(index>=0) {
            addMove(index, iSym);
            return true;
        }
        return false;
    }

    /**
     * if there is one step before the row/column wins, do it and win
     * @param iSym
     * @param getTheIndex
     * @return
     * @throws Exception
     */
    boolean doFinishDiagonal(char iSym , IntUnaryOperator getTheIndex) throws Exception {
        int index = findUnfinishedDiagonal(iSym, getTheIndex);
        if(index>=0) {
            addMove(index, iSym);
            return true;
        }
        return false;
    }


    /**
     * if there is one step before losing the game, save it
     * @param bCross
     * @return
     * @throws Exception
     */

    public boolean preventLoss(boolean bMyCross) throws Exception{
        char iSym = getSymbol(!bMyCross);
        char iMySym = getSymbol(bMyCross);
        return preventLoss(iSym, iMySym,  (i, j) -> i * mBoardDim +j)
                || preventLoss(iSym, iMySym, (i, j) -> j * mBoardDim +i)
                || preventLossDiagonal(iSym, iMySym, (i) -> i * mBoardDim +i)
                || preventLossDiagonal(iSym, iMySym, (i) -> i * mBoardDim + mBoardDim - i-1)
                ;
    }

        boolean preventLoss(char iSym , char iMySym, IntBinaryOperator getTheIndex) throws Exception {
            int index = this.findUnfinishedRowOrColumn(iSym, getTheIndex);

            if(index>=0) {
                addMove(index, iMySym);
                return true;
            }
            return false;
        }

        boolean preventLossDiagonal(char iSym , char iMySym,  IntUnaryOperator getTheIndex) throws Exception {
            int index = findUnfinishedDiagonal(iSym, getTheIndex);
            if(index>=0) {
                addMove(index, iMySym);
                return true;
            }
            return false;
    }

    /**
     * find if there is a row or a column missing just one iSym to win
     * @param iSym
     * @param getTheIndex
     * @return index where the iSym could be inserted to win game or -1 if not found
     * @throws Exception
     */
    int findUnfinishedRowOrColumn(char iSym , IntBinaryOperator getTheIndex) throws Exception {

        for (int i = 0 ; i < mBoardDim; i++){
            int iCountSym = 0;
            int iCountEmpty = 0;
            int emptyCellIndex = -1;
            for(int j =0; j < mBoardDim; j++){
                int index = getTheIndex.applyAsInt(i, j);
                if(mData[index] == iSym) {
                    iCountSym++;
                    iCountEmpty = 0;
                    if(iCountSym == mGameDim -1){ // found required consecutive line
                        if(emptyCellIndex != -1){
                            return emptyCellIndex;
                        }
                    } else if(iCountSym >= mGameDim){
                            throw new TttException("algorithm error: the game is done!");
                    }

                }else{
                   //check if we have a winner
                    if(iCountSym == mGameDim -1){ // found required consecutive line
                        if(emptyCellIndex != -1){
                            return emptyCellIndex;
                        } else if (isCellEmpty(index)) {
                            return index;
                        }
                    } else  if (isCellEmpty(index)) {
                        emptyCellIndex = index;
                        if(iCountEmpty>0)
                            iCountSym = 0;
                        iCountEmpty++;
                    } else {
                        emptyCellIndex = -1;
                        iCountSym = 0;
                        iCountEmpty = 0;
                    }
                }
            }
        }
        return -1;
    }

    /**
     * find if there is a row r a column missing just one iSym to win
     * @param iSym
     * @param getTheIndex
     * @return
     * @throws Exception
     */
    int findUnfinishedDiagonal(char iSym , IntUnaryOperator getTheIndex) throws Exception {

        int iCountSym = 0;
        int iCountEmpty = 0;
        int emptyCellIndex = -1;

        for (int i = 0 ; i < mBoardDim; i++){
                int index = getTheIndex.applyAsInt(i);
                if(mData[index] == iSym) {
                    iCountSym++;
                    iCountEmpty = 0;
                    if(iCountSym == mGameDim -1){ // found required consecutive line
                        if(emptyCellIndex != -1){
                            return emptyCellIndex;
                        }
                    } else if(iCountSym >= mGameDim){
                            throw new TttException("algorithm error: the game is done!");
                    }

                }else{
                   //check if we have a winner
                    if(iCountSym == mGameDim -1){ // found required consecutive line
                        if(emptyCellIndex != -1){
                            return emptyCellIndex;
                        } else if (isCellEmpty(index)) {
                            return index;
                        }
                    } else  if (isCellEmpty(index)) {
                        emptyCellIndex = index;
                        if(iCountEmpty>0)
                            iCountSym = 0;
                        iCountEmpty++;
                    } else {
                        emptyCellIndex = -1;
                        iCountSym = 0;
                        iCountEmpty = 0;
                    }
                }

        }
        return -1;
    }

    /**
     * just sets corner if there is an empty one
     * this move makes sense for 3x3 board only
     * @param bCross
     * @return
     * @throws Exception
     */
    /*just sets corner if there is an empty one*/
    public boolean setCorner(boolean bCross) throws Exception{
        char iSym = getSymbol(bCross);
        int index = getIndex(0,0);
        if(addMove(index, iSym))
            return true;
        index = getIndex(0,mBoardDim-1);
        if(addMove(index, iSym))
            return true;
        index = getIndex(mBoardDim-1,0);
        if(addMove(index, iSym))
            return true;
        index = getIndex(mBoardDim-1,mBoardDim-1);
        return addMove(index, iSym);
    }

    /**
     * just sets first available non-corner cell
     * this move makes sense for 3x3 board only when it's going to be a draw
     * @param bCross
     * @return
     */
    /*just sets midpoint if there is an empty one*/
    public boolean setAnyCell(boolean bCross) throws Exception{
        char iSym = getSymbol(bCross);
        for(int i=1; i < mMaxIndex-1; i ++){
            if(addMove(i, iSym))
                return true;
        }
        return false;
    }
}
