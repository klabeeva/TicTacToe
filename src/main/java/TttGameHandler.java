
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.Reader;
import java.io.Writer;
import java.io.IOException;

/*

*/
public class TttGameHandler {
    /*constants*/
    final String kState = "state";
    final String kStartGame = "start";
    final String kBoardData = "board";
    final String kCross = "bCross";
    TttGame mGame;
    
    public TttGameHandler(){
        mGame = new TttGame();
    }
    
    public TttGameHandler(String gameName){
        if(gameName.equals("simple"))
            mGame = new TttSimpleGame();
        else
            mGame = new TttGame();
    }
    
    public void gameReset(){
        mGame.reset();
    }

    public void setCross(boolean bCross){
        mGame.setCross(bCross);
    }
    
    public void startGame() throws Exception{
        mGame.startGame(); 
    }
    
    public TttGameResult makeMove() throws Exception{
       return mGame.makeMove(); 
    }
    
    public void  switchCrossValue() {
       mGame.switchCrossValue(); 
    }
    
    public int [] getBoardArray(){
        return mGame.getBoardArray();
    }

    public void loadGame(Reader baseReader) throws Exception {
        JsonReader reader;
        reader = new JsonReader(baseReader);
        try {
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();

                if (name.equals(kStartGame)) {
                    gameReset();
                    startGame();
                    break;
                } else if (name.equals(kCross)) {
                    setCross(reader.nextBoolean());
                } else if (name.equals(kBoardData)) {
         	    reader.beginArray();
    		    for(int i =0; reader.hasNext() && i < mGame.mBoard.mMaxIndex; i++)  {
                        mGame.setBoardData(i,reader.nextInt());
        	    }
                    reader.endArray();

                } else if (name.equals(kState)) {
                    reader.skipValue();;
    	        } else {
                    throw new Exception("Unxepected name:" + name);
		    // possibly - reader.skipValue();
	        }
            }
	    reader.endObject();
        } finally {
	    reader.close();
        }
    }

    public void saveGame(Writer baseWriter) throws IOException {
        JsonWriter writer;
        writer = new JsonWriter(baseWriter);
        writer.beginObject();
        if(mGame.isStarting()){
           writer.name(kStartGame).value(true); 
        }
        else {
            writer.name(kCross).value(mGame.getCross());
            int [] boardArray =  mGame.getBoardArray();
            writer.name(kBoardData); 
            writer.beginArray();  
            for(int i = 0; i < boardArray.length; i++) {
                writer.value(boardArray[i]); 
            }
            writer.endArray(); // ]
        }
        writer.endObject(); // }
        writer.close();
    }
}
