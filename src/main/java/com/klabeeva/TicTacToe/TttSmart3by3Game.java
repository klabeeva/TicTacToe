package com.klabeeva.TicTacToe;

/**
 *  The class implements tic-tac-toe game with winning  or draw algorithm for 3x3 game
 * @author dev
 */
public class TttSmart3by3Game implements ITttGame{
    TttBoard mBoard = null;         // game matrix
    boolean mbCross = true;  // true if playing with "x", false if playing with "0"
    TttGameStatus mStatus=null;

    public TttSmart3by3Game(boolean bCross) throws Exception{
        mbCross = bCross;
        mBoard = new TttBoard(3,3);
        mStatus = new TttGameStatus(TttGameStatus.STARTING);;
    }

    /**
     * Constructor called from TttGameJsonandler;
     * data is from input stream
     * @param iBoardDim - board size
     * @param iGameDim - number of consecutive symbols required for winning
     * @throws Exception
     */
    public TttSmart3by3Game(boolean bCross, TttGameStatus status, String boardStr) throws Exception{
        mbCross = bCross;
        mBoard = new TttBoard(3,3);
        if(status == null){
            mStatus =  new TttGameStatus(TttGameStatus.STARTING);
        } else {
            validateStatus(status);
            mStatus = status;
        }
        if(mStatus.getValue() != TttGameStatus.STARTING){
            validateBoardIput(boardStr);
            mBoard.setBoardData(boardStr);
        }

    }

    void validateStatus(TttGameStatus status) throws Exception{
        if(status.isDone()) throw new TttException ("algorithm error: game is done!");
    }

    void validateBoardIput(String boardStr) throws Exception{
        if(boardStr == null || boardStr.isEmpty()) {
            if (!mStatus.isStarting())  throw new TttException ("no board game data");
            return;
        }
    }

    /**
     * algorithm starts the game
     * @throws Exception
     */

     void startGame() throws Exception {
        mBoard.setBoardCenter(mbCross);
    }

     public void play() throws Exception {
         makeMove();
     }

    /**
     *  data for passing to user
     *  implement ITttInterface
     *  */
    public boolean getCross(){
        return mbCross;
    }

    public int getStatusAsInt() {
        return mStatus.getValue();
    }

    public String getBoardAsString(){
        return  mBoard.getBoardDataAsStr();
    }

    public void setBoardData(String str) throws Exception {
        mBoard.setBoardData(str);
    }

    public char [] getBoardArray(){
        return mBoard.getData();
    }


    public int getUsedCellsNumber(){
        return mBoard.getUsedCellsNumber();
    }

    public boolean isDone(){
       return mBoard.isFull();
    }

    /**
     * main algorithm for 3x3 board
     * @return TttGameResult
     * result is set NOT for the user, but the game, i.e. it is "LOST" if user has won
     * @throws Exception
     */
    public TttGameStatus makeMove() throws Exception {
        if(mBoard.isEmpty()){
            startGame();
            mStatus.setPlaying();
        } else if(mBoard.hasWon(!mbCross)){
            mStatus.setLost();
        } else if(mBoard.finish(mbCross)){
            mStatus.setWon();
        } else if(mBoard.preventLoss(mbCross)){
            if (mBoard.isFull()) mStatus.setDraw();
        } else if(mBoard.setBoardCenter(mbCross)){
            if (mBoard.isFull()) mStatus.setDraw();
        } else if(mBoard.setCorner(mbCross)){
            if (mBoard.isFull()) mStatus.setDraw();
        } else  if(mBoard.setAnyCell(mbCross)){
            if (mBoard.isFull()) mStatus.setDraw();
        } else
        // could not set any cell
             mStatus.setDraw();
        return mStatus;
    }
}
