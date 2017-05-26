import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

// test file
// needs more work
public class testGson {
    
    static public void toFile(String fileName, TttGameHandler gameHandler)
            throws Exception {
        gameHandler.saveGame(new FileWriter(fileName));
    }

    static public void writeGameStateToFile(String fileName, TttGameHandler gameHandler)
            throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true));
        int [] boardArray = gameHandler.getBoardArray();
        for(int i = 0; i < boardArray.length; i++){
            writer.write( String.format("%d ", boardArray[i]));
        }
        writer.close();
    }

    
    static public void fromFile(String fileName, TttGameHandler gameHandler)
            throws Exception {
        gameHandler.loadGame(new FileReader(fileName));
    }

    public static void testUserInput(String fileName)  {
        
    }
    
    public static void testBoth(String fileName, String testFileName)
            throws Exception {
        TttGameHandler gameHandler = new TttGameHandler();
        testBoth(gameHandler,  fileName, testFileName);
    }
    
    public static void testBothSimple(String fileName, String testFileName)
            throws Exception {
        TttGameHandler gameHandler = new TttGameHandler("simple");
        testBoth(gameHandler, fileName, testFileName);
    }

    private static void testBoth(TttGameHandler gameHandler, String fileName,
            String testFileName) throws Exception {
        gameHandler.startGame();
        toFile(fileName, gameHandler);
        writeGameStateToFile(testFileName, gameHandler);
        TttGameResult gameRes = new TttGameResult();
        while(!gameRes.isDone()){
            gameHandler.gameReset();
            fromFile(fileName, gameHandler);
            gameHandler.switchCrossValue();
            gameRes = gameHandler.makeMove();
            toFile(fileName, gameHandler);
            writeGameStateToFile(testFileName, gameHandler);
        }
        // gameHandler.isCross() tbd
        System.out.println(gameRes.getString());
    }

    public static void main(String[] args)  {
        final String fileName     = "ttt1.json";
        final String fileName1    = "ttt2.json";
        final String testFileName = "test1.txt";
        try {
            
            testBoth(fileName, testFileName);
            testBothSimple(fileName1, testFileName);
            
//            testUserInput(fileName1);
            
        } catch(Throwable e){
        	System.out.println("Exception!!!"); 
	        System.out.println(e.getMessage());
                e.printStackTrace();
            
        }
    }

}
