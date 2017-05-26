
import java.util.Arrays;

/**
 *  The class implements tic-tac-toe game board.
 *   All 'logic' function are implemented here.
 * @author dev
 */
public class TttBoard {
    int mDim = 3;
    int mMaxIndex = 9;
    int [] mData;
    int mUsed = 0;
    
    public TttBoard(int iDim){
        setDimension(iDim);
        mData = new int[mDim * mDim];
        reset();
    }
    
    public TttBoard(){
        mData = new int[mDim * mDim];
        reset();
    }
    
    public final void reset (){
        Arrays.fill(mData,0);
        mUsed = 0;
    }
    
    private boolean setDimension (int iDim){
        if(iDim < 3){
            // add error
            return false;
        }
        mDim = iDim;
        mMaxIndex = mDim*mDim;
        return true;
    }
    
    public int [] getData() {
        return mData;
    }
    
    public int getUsedCellsNumber(){
        return mUsed;
    
    }

    private int getIndex(int iRow, int iCol) throws Exception{
        if(iRow < 0 || iRow >= mDim) throw new Exception("wrong row value");
        if(iCol < 0 || iCol >= mDim) throw new Exception("wrong column value");
        return iRow * mDim + iCol;
    }
    
    private boolean isUsed(int index) throws Exception{
        if(index < 0 || index >= mMaxIndex)
            throw new Exception("move is out of range");
        return mData[index] != 0;
    }
    
    public boolean isFull() {
        return mUsed ==  mMaxIndex;
    }
    
    public boolean isEmpty() {
        return mUsed == 0;
    }
    
    // sets data from json
    public void setBoardData(int index, int symb) throws Exception {
        mData[index] = symb;
        if(symb!= 0)
            mUsed++;
    }
    
    public boolean addMove(int index, int symb) throws Exception {
        if(isUsed(index)) return false;
        mData[index] = symb;
        mUsed++;
        return true;
    }
    public boolean addMove(int index, boolean bCross) throws Exception {
        int iSym = (bCross) ? 1 : 2;
        return addMove(index, iSym);
    }
    
    public boolean makeFirstMove(boolean bCross) throws Exception {
        
        int index = mMaxIndex/2;
        if(!isUsed(index)){
            addMove(index, (bCross) ? 1 : 2);
            return true;
        }
        return false;
    }
    
    /* check after getting data from json if you lost*/
    public boolean hasFullRow(boolean bOne) throws Exception{
        return  hasFull(bOne, false);
    }
    
    public boolean hasFullColumn(boolean bOne) throws Exception{
        return  hasFull(bOne, true);
    }
    
    private  boolean hasFull(boolean bOne, boolean bColumn) throws Exception{
        // add try/catch
        int iSym = (bOne) ? 1 : 2;
        for (int i = 0 ; i < mDim; i++){
            boolean bNo = false;
            for(int j =0; j < mDim; j++){
                int index = (bColumn) ? getIndex(j,i) : getIndex(i, j);
                if(mData[index] != iSym ){
                    bNo = true;
                    break;
                }
            }
            if(bNo == false) return true;
        }
        return false;
    }

    public boolean hasFullDiagonal(boolean bOne) throws Exception{
       return hasFullDiagonal(bOne, true) || hasFullDiagonal(bOne, false);
    }
    
    private boolean hasFullDiagonal(boolean bOne, boolean bLeft) throws Exception{
        int iSym = (bOne) ? 1 : 2;
        boolean bNo = false;
        for (int i = 0 ; i < mDim; i++){
            int index = (bLeft) ? getIndex(i,i) : getIndex(i,mDim - i-1);
            if(mData[index] != iSym ){
                    bNo = true;
                    break;
            }
        }
        return !bNo;
    }

    /* if there is one step before the row/column wins, do it*/
    public boolean finishRow(boolean bOne) throws Exception{
        return finish(bOne, false);
    }

    public boolean finishColumn(boolean bOne) throws Exception{
        return finish(bOne, true);
    }

    public boolean finish(boolean bOne, boolean bColumn) throws Exception{
        int iSym = (bOne) ? 1 : 2;
        for (int i = 0 ; i < mDim; i++){
            int iCount = 0;
            boolean bNo =false;
            int theIndex = -1;
            for(int j =0; j < mDim; j++){
                int index = (bColumn) ? getIndex(j,i) : getIndex(i,j);
            
                if(mData[index] == iSym )
                    continue;
                if(mData[index] != 0 || iCount > 0){
                        bNo = true;
                        break;
                } else {
                    iCount = 1;
                    theIndex = index;
                }
            }
            if(bNo == false && theIndex >= 0){
                addMove(theIndex, iSym);
                return true;
            }
        }
        return false;
    }

    public boolean finishDiagonal(boolean bOne) throws Exception{
        return finishDiagonal(bOne, true) || finishDiagonal(bOne, false);
    }
    
    private boolean finishDiagonal(boolean bOne, boolean bLeft) throws Exception{
        // add try/catch
        int iSym = (bOne) ? 1 : 2;
            int iCount = 0;
            boolean bNo =false;
            int theIndex = -1;
        for (int i = 0 ; i < mDim; i++){
            int index = (bLeft) ? getIndex(i,i) : getIndex(i,mDim - i-1);
            
                if(mData[index] == iSym ) continue;

                if(mData[index] != 0 || iCount > 0){
                        bNo = true;
                        break;
                } 
                else {
                    iCount = 1;
                    theIndex = index;
                }
            }
            if(bNo == false && theIndex >= 0){
                addMove(theIndex, iSym);
                return true;
            }
        return false;
    }
    
    /* if there is one step before the enemy wins, save it*/
    public boolean preventFinishRow(boolean bOne) throws Exception{
        return preventFinish(bOne, false);
    }
    public boolean preventFinishColumn(boolean bOne) throws Exception{
        return preventFinish(bOne, true);
    }
    
    public boolean preventFinish(boolean bOne, boolean bColumn) throws Exception{
        // add try/catch
        int iSym = (bOne) ? 2 : 1;
        int iMySym = (bOne) ? 1 : 2;
        for (int i = 0 ; i < mDim; i++){
            int iCount = 0;
            boolean bNo =false;
            int theIndex = -1;
            for(int j =0; j < mDim; j++){
                int index = (bColumn) ? getIndex(j,i) : getIndex(i,j);
                if(mData[index] == iSym )
                    continue;
                if(mData[index] != 0 || iCount > 0 ){
                    bNo = true;
                    break;
                } else {
                    iCount = 1;
                    theIndex = index;
                }
            }
            if(bNo == false && theIndex >= 0){
                addMove(theIndex, iMySym);
                return true;
            }
        }
        return false;
    }

    public boolean preventFinishDiagonal(boolean bOne) throws Exception{
        return preventFinishDiagonal(bOne, true) || preventFinishDiagonal(bOne, false);
    }
    public boolean preventFinishDiagonal(boolean bOne, boolean bLeft) throws Exception{
        // add try/catch
        int iSym = (bOne) ? 2 : 1;
        int iMySym = (bOne) ? 1 : 2;
            int iCount = 0;
            boolean bNo =false;
            int theIndex = -1;
        for (int i = 0 ; i < mDim; i++){
            int index = (bLeft) ? getIndex(i,i) : getIndex(i,mDim - 1 - i);
            if(mData[index] == iSym )
                continue;
            if(mData[index] != 0 || iCount > 0){
                bNo = true;
                break;
            } else {
                iCount = 1;
                theIndex = index;
            }
        }
        if(bNo == false && theIndex >= 0){
            addMove(theIndex, iMySym);
            return true;
        }
        return false;
    }

    /*just sets corner if there is an empty one*/
    public boolean setCorner(boolean bOne) throws Exception{
        // add try/catch
        int iSym = (bOne) ? 1 : 2;
        int index = getIndex(0,0);
        if(addMove(index, iSym))
            return true;
        index = getIndex(0,mDim-1);
        if(addMove(index, iSym))
            return true;
        index = getIndex(mDim-1,0);
        if(addMove(index, iSym))
            return true;
        index = getIndex(mDim-1,mDim-1);
        return addMove(index, iSym);
    }
    
    /*just sets midpoint if there is an empty one*/
    public boolean setMiddle(boolean bOne) throws Exception{
        // add try/catch
        int iSym = (bOne) ? 1 : 2;
        for(int i=1; i < mMaxIndex-1; i ++){
            if(addMove(i, iSym))
                return true;
        }
        return false;
    }
}
