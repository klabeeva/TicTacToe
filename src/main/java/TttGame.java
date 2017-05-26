
/**
 *  The class implements tic-tac-toe game with winning  or draw algorithm for 3x3 game
 * @author dev
 */
public class TttGame {
    TttBoard mBoard;         // game matrix
    boolean mbCross = true;  // true if playing with "x", false if playing with "0"
    
    public TttGame(){
        mbCross = true;
        mBoard = new TttBoard();
    }
    
    public void reset(){
        mbCross = true;
        mBoard = new TttBoard();
    }
    
    public void setCross(boolean bCross){
        mbCross = bCross;
    }
   
    public void  switchCrossValue() {
       mbCross = ! mbCross;
    }
    
    public boolean isStarting(){
        return mBoard.isEmpty();
    }
    
    public void startGame() throws Exception{
        mBoard.reset();
        mBoard.makeFirstMove(mbCross);
    }
    
    public boolean getCross(){
        return mbCross;
    }
    
    public int [] getBoardArray(){
        return mBoard.getData();
    
    }
    public void setBoardData(int index, int symb) throws Exception {
        mBoard.setBoardData(index, symb);
    }
    
    public int getUsedCellsNumber(){
        return mBoard.getUsedCellsNumber();
    }

    public boolean isDone(){
        return mBoard.isFull();
    }
    
    public TttGameResult makeMove() throws Exception {
        if(mBoard.hasFullRow(!mbCross) || mBoard.hasFullColumn(!mbCross) || mBoard.hasFullDiagonal(!mbCross)){
           return new TttGameResult(TttGameResult.LOST);
        }
        
        if(mBoard.finishRow(mbCross) || mBoard.finishColumn(mbCross) || mBoard.finishDiagonal(mbCross)){
           return new TttGameResult(TttGameResult.WON);
        }
        if(mBoard.preventFinishRow(mbCross) || mBoard.preventFinishColumn(mbCross) || mBoard.preventFinishDiagonal(mbCross)){
          if(mBoard.isFull()) return new TttGameResult(TttGameResult.DRAW);
          else return new TttGameResult(TttGameResult.PLAYING);
        }

        if(mBoard.setCorner(mbCross)){
          if(mBoard.isFull()) return new TttGameResult(TttGameResult.DRAW);
          else return new TttGameResult(TttGameResult.PLAYING);
        }
    
        if(mBoard.setMiddle(mbCross)){
          if(mBoard.isFull()) return new TttGameResult(TttGameResult.DRAW);
          else return new TttGameResult(TttGameResult.PLAYING);
        }
    
        return new TttGameResult(TttGameResult.DRAW);
    }

    
}
