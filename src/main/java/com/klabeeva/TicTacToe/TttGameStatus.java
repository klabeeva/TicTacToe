package com.klabeeva.TicTacToe;
/**
 *  The class implements state of tic-tac-toe game
 *  state PLAYING used for game in progress
 *  other states show the result of the game
 *  returned after each move or after the data is set from the stream
 *
 * 0 - starting
 * 1 - in progress
 * 2 we won (user lost)
 * 3 we lost (user won)
 * 4 draw
 */

public class TttGameStatus {
    int mType;
    public static final int STARTING = 0;
    public static final int PLAYING = 1;
    public static final int WON = 2; // we, user lost
    public static final int LOST = 3; // we, user won
    public static final int DRAW = 4;

    public TttGameStatus(int type) throws TttException {
        setValue(type);
    }

    public int getValue() {
        return mType;
    }

    public final void setValue(int type) throws TttException {
        if(type > DRAW || type < STARTING)
            throw new TttException("wrong status value");
        mType = type;
    }

    public boolean isDone(){
       return mType > PLAYING;
    }

    public boolean isStarting(){
        return mType == STARTING;
     }

    public void setPlaying(){
        mType = PLAYING;
    }

    public void setDraw(){
        mType = DRAW;
    }

    public void setWon(){
        mType = WON;
    }

    public void setLost(){
        mType = LOST;
    }

    public String getUserString(){
        switch(mType){
            case WON:
                return "Sorry, you lost";
            case LOST:
                return "Congrats, winner!";
            case DRAW:
                return "Draw!";
            default:
                return "Still playing";
        }
    }

}
