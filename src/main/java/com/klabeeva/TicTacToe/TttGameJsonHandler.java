package com.klabeeva.TicTacToe;

/*import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
*/
import java.io.Reader;
import java.io.Writer;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
// import org.codehaus.jackson.map.JsonMappingException;

/*

*/
/**
 * writes to/ reads from json stream
 *
 *  format for input/output:
 * "state" integer; current game state:
 *          values:  0 - starting
 *                   1 - in progress
 *                   2 we won (user lost)
 *                   3 we lost (user won)
 *                   4 draw
 * "bCross" boolean; what symbol we play in game
 *          values: true - we play cross ("x"), user plays zeroes ("0")
 *                  false - user plays cross ("x"), we play zeroes ("0")
 * "board" String; game board data
 *          values: string (size of board)
 *                  where 'x' stays for each cross move,
 *                  where '0' stays for each zero move,
 *                  where ' ' stays for empty cell
 *          for instance for 3x3 board
 *          | 0 | 0 | x|
 *          |   | x |  |
 *          | x |   |  |
 *
 * will be represented by string "00x x x  "
 */

public class TttGameJsonHandler {
    /*constants*/
    final static String kGameName = "gameName";       // for future: if we want different type of games
    final static String kBoardSize = "boardSize";     // for future: size of Board ; default is 3x3;
    final static String kWinningSize = "winningSize";  // for future: size of winning streak; default is 3;

    final static String kGameStatus = "state";       //current game status
    final static String kBoardData = "board";         // board data
    final static String kCross = "bCross";            // playing "x" if true, "0" otherwise


    /**
     * main function for playing game
     * create game from input stream data
     * makes a move
     * saves game to output stream
     * current format for input/output:
     *
     * @param baseReader
     * @param baseWriter
     * @throws Exception
     */
    public static void playGame(Reader baseReader, Writer baseWriter) throws Exception{
            if(baseReader == null)
                throw new TttException("parameters error: reader is null");
            if(baseWriter == null)
                throw new TttException("parameters error: writer is null");

            ITttGame game = loadGame(baseReader);
            game.play();
            storeGame(game, baseWriter);

    }

     /**
      * Gets game data from input stream and creats the game
      * for further development different type of games should be created here
     * @param baseReader
     * @return game
     * @throws Exception
      */
    static ITttGame loadGame(Reader baseReader) throws Exception {

        JsonFactory jasonFactory = new JsonFactory();
        JsonParser jsonParser = jasonFactory.createJsonParser(baseReader);

        TttGameStatus status = null;
        boolean bCross = true;
        String boardStr = "";

        JsonToken token;
        while ((token = jsonParser.nextToken()) != JsonToken.END_OBJECT && token != null) {
               //get the current token
               String fieldname = jsonParser.getCurrentName();
               if (kGameStatus.equals(fieldname)) {
                   jsonParser.nextToken();
                   status = new TttGameStatus(jsonParser.getIntValue());
               } else if(kCross.equals(fieldname)){
                   jsonParser.nextToken();
                   bCross = jsonParser.getBooleanValue();
               } else if(kBoardData.equals(fieldname)){
                   jsonParser.nextToken();
                   boardStr = jsonParser.getText();
               }
        }

        return new TttSmart3by3Game(bCross, status, boardStr);
    }

     /**
      * Puts game data in output stream
      * @param game
      * @param baseWriter
      * @throws Exception
      */
    public static void storeGame(ITttGame game, Writer baseWriter) throws Exception {
        if(game == null) throw new TttException("game is null");
        JsonFactory jasonFactory = new JsonFactory();
        JsonGenerator jsonGenerator = jasonFactory.createJsonGenerator(baseWriter);

        jsonGenerator.writeStartObject();

        /* game state:
         * 0 - starting
         * 1 - in progress
         * 2 we won (user lost)
         * 3 we lost (user won)
         * 4 draw
         */
        jsonGenerator.writeNumberField(kGameStatus, game.getStatusAsInt());

        jsonGenerator.writeBooleanField(kCross, game.getCross());
        jsonGenerator.writeStringField(kBoardData, game.getBoardAsString());

        jsonGenerator.writeEndObject();
        jsonGenerator.close();
    }

}
