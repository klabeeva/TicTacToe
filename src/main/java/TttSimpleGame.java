
import java.util.concurrent.ThreadLocalRandom;



/**
 *  The class implements tic-tac-toe game that randomly sets data on the board 
 *  Currently used for testing only
 *  can be used as a game when allowing user to select type of game (easy/hard) 
 * @author dev
 */

public class TttSimpleGame  extends TttGame {
    
    @Override
    public TttGameResult makeMove() throws Exception {
        if(mBoard.hasFullRow(!mbCross) || mBoard.hasFullColumn(!mbCross) || mBoard.hasFullDiagonal(!mbCross)){
           return new TttGameResult(TttGameResult.LOST);
        }
        
        int randomNum = ThreadLocalRandom.current().nextInt(0, mBoard.mMaxIndex );        
        boolean bAdded = false;
        for(int i = randomNum; i< mBoard.mMaxIndex; i++ ){
            if(mBoard.addMove(i, mbCross)){
                bAdded = true;
                break;
            }
        }
        if(!bAdded){
            for(int i = 0; i < randomNum;  i++ ){
                if(mBoard.addMove(i, mbCross)){
                    bAdded = true;
                    break;
                }
            }
        }
        
        if(!bAdded)
                return new TttGameResult(TttGameResult.DRAW);
            
        if(mBoard.hasFullRow(!mbCross) || mBoard.hasFullColumn(!mbCross) || mBoard.hasFullDiagonal(!mbCross)){
           return new TttGameResult(TttGameResult.WON);
        }
        if(mBoard.isFull()) return new TttGameResult(TttGameResult.DRAW);
        else return new TttGameResult(TttGameResult.PLAYING);
    }

    
}
