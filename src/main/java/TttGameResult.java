/**
 *  The class implements state of tic-tac-toe game 
 *  state PLAYING used for game in progress
 *  other states show the result of the game
 *  returned after each move 
 * @author dev
 */

public class TttGameResult {
    int mType;
    public static final int PLAYING = 0;
    public static final int WON = 1;
    public static final int LOST = 2;
    public static final int DRAW = 3;
    
    public TttGameResult() {
        setValue(PLAYING);
    }
    public TttGameResult(int type) {
        setValue(type);
    }
    public int getValue() {
        return mType;
    }
    
    public final void setValue(int type) {
        if(type > 3 || type < 0)
            type = 0;
        mType = type;
    }
    
    public boolean isDone(){
       return mType != PLAYING;
    }
    
    public String getString(){
        switch(mType){
            case 1: 
                return "Won!";
            case 2: 
                return "Lost!";
            case 3: 
                return "Draw!";
            default: 
                return "Still playing";
        }
    }
    
}
