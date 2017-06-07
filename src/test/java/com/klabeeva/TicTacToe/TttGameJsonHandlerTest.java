package com.klabeeva.TicTacToe;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TttGameJsonHandlerTest {

    @Before
    public void setUp() throws Exception {
    }

    /**********************
     * error tests    *
     *********************/
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testWrongStatusType() throws Exception {
    	testProperException(TttBoardStrings.kStatusErrorWrongType);
    }
    
    public void testWrongCrossType() throws Exception {
    	testProperException(TttBoardStrings.kCrossError);
    }
    
    public void testWrongBoardType() throws Exception {
    	testProperException(TttBoardStrings.kBoardErrorWrongType);
    }
    
    @Test
    public void testStatusTooBig() throws Exception {
    	testProperTttException(TttBoardStrings.kStatusErrorTooBig);
    }

    @Test
    public void testStatusTooSmall() throws Exception {
    	testProperTttException(TttBoardStrings.kStatusErrorTooSmall);
    }

    @Test
    public void testStatusWrongValue() throws Exception {
    	testProperTttException(TttBoardStrings.kStatusErrorWrongStatus);
    }

    @Test
    public void testBoardTooShort() throws Exception {
    	testProperTttException(TttBoardStrings.kBoardErrorTooShort);
    }

    @Test
    public void testBoardTooLong() throws Exception {
    	testProperTttException(TttBoardStrings.kBoardErrorTooLong);
    }

    @Test
    public void testBoardWrongChar() throws Exception {
    	testProperTttException(TttBoardStrings.kBoardErrorWrongChar);
    }
    
    @Test
    public void testBoardNoData() throws Exception {
    	testProperTttException(TttBoardStrings.kBoardErrorNoBoard);
    }

    
    /**********************
     * start game tests    *
     *********************/
    @Test
    public void testStartGame() {
        String str = ""; // using default values when starting the game
        byte[] content = str.getBytes();

        Reader reader = new InputStreamReader(new ByteArrayInputStream(content));
        Writer writer = new StringWriter();
        try {
            TttGameJsonHandler.playGame(reader, writer);
        } catch (Exception e) {
            fail("Exception in game handler: " + e.getMessage());
        }
        String gameData = writer.toString();
        assertEquals(TttBoardStrings.kDefaultStartGameRes, gameData);
    }

    /**********************
     * diagonal tests    *
     *********************/
    @Test
    public void testDiagonalMiddleWinLeft() {
    	testAlgorithmCreatingInput(TttBoardStrings.kBeforeDiagonalMiddleWinLeft,
    			TttBoardStrings.kDiagonalLeftWinRes);
    }

    @Test
    public void testDiagonalMiddleWinRight() {
    	testAlgorithmCreatingInput(TttBoardStrings.kBeforeDiagonalMiddleWinRight,
    			TttBoardStrings.kDiagonalRightWinRes);
    }

    @Test
    public void testDiagonalLowCornerLeft() {
    	testAlgorithmCreatingInput(TttBoardStrings.kBeforeDiagonalLowCornerWinLeft,
    			TttBoardStrings.kDiagonalLeftWinRes);
    }

    @Test
    public void testDiagonalUpperCornerWinRight() {
    	testAlgorithmCreatingInput(TttBoardStrings.kBeforeDiagonalUpperCornerWinRight,
    			TttBoardStrings.kDiagonalRightWinRes);
    }

    @Test
    public void testDiagonalMiddlePrevetnLossLeft() {
    	testAlgorithmCreatingInput(TttBoardStrings.kBeforeDiagonalMiddleWinLeft,
    			TttBoardStrings.kDiagonalLeftWinRes);
    }

    
    /**********************
     * rows tests    *
     *********************/
    @Test
    public void testRowTopMiddleWin() {
    	testAlgorithm(TttBoardStrings.kRowTopMiddleWin,
    			TttBoardStrings.kRowTopMiddleWinRes);
    }
    @Test
    public void testRowTopMiddlePrevetnLoss() {
    	testAlgorithm(TttBoardStrings.kRowTopMiddlePreventLoss,
    			TttBoardStrings.kRowTopMiddlePreventLossRes);
    }
    
    @Test
    public void testRowLowLeftWin() {
    	testAlgorithm(TttBoardStrings.kRowLowLeftWin,
    			TttBoardStrings.kRowLowLeftWinRes);
    }
    @Test
    public void testRowLowLeftPrevetnLoss() {
    	testAlgorithm(TttBoardStrings.kRowLowLeftPreventLoss,
    			TttBoardStrings.kRowLowLeftPreventLossRes);
    }
    

    /**********************
     * column tests    *
     *********************/
    @Test
    public void testColumnLeftMiddleWin() {
    	testAlgorithm(TttBoardStrings.kColumnLeftMiddleWin,
    			TttBoardStrings.kColumnLeftMiddleWinRes);
    }
    @Test
    public void testColumnLeftMiddlePrevetnLoss() {
    	testAlgorithm(TttBoardStrings.kColumnLeftMiddlePreventLoss,
    			TttBoardStrings.kColumnLeftMiddlePreventLossRes);
    }
    // TODO more column tests
    
    // TODO test 'draw' game 

    // TODO test play 'winning' game 
     
    
    // utils  -------------------------
    
    // creates input data from board string
    String getGameStr (boolean bCross, String boardStr) throws TttException {
        TttGameStatus status = new TttGameStatus(TttGameStatus.PLAYING);
        TttSmart3by3Game game;
        Writer writer = null;
        try {
            game = new TttSmart3by3Game(bCross, status, boardStr);
            writer = new StringWriter();

            TttGameJsonHandler.storeGame(game, writer);
        } catch (Exception e) {
            fail("Exception in getGameStr: " + e.getMessage());
            return null;
        }
        return writer.toString();
    }
    
    /**
     * util for testing;
     * creates input data from board string
     * used for algorithm testing
     * @throws TttException 
     */
     void testAlgorithmCreatingInput(final String kStr, final String resStr) {
    	try {
    		String str = getGameStr(true, kStr);
    		byte[] content = str.getBytes();

    		Reader reader = new InputStreamReader(new ByteArrayInputStream(content));
    		Writer writer = new StringWriter();
        
            TttGameJsonHandler.playGame(reader, writer);
        String gameData = writer.toString();
        assertEquals(resStr, gameData);
        } catch (Exception e) {
            fail("Exception in game handler: " + e.getMessage());
            return;
        }
    }
     
     /**
      * util for testing;
      * gets input data as q param
      * used for algorithm testing
      * @throws TttException 
      */
      void testAlgorithm(final String kInStr, final String resStr) {
     	try {
     		byte[] content = kInStr.getBytes();

     		Reader reader = new InputStreamReader(new ByteArrayInputStream(content));
     		Writer writer = new StringWriter();
         
             TttGameJsonHandler.playGame(reader, writer);
             String gameData = writer.toString();
             assertEquals(resStr, gameData);
         } catch (Exception e) {
             fail("Exception in game handler: " + e.getMessage());
             return;
         }
     }
      
     // util used for required TttException
     void testProperTttException(final String strWithError) throws Exception {
     	thrown.expect(TttException.class);

         byte[] content = strWithError.getBytes();

         Reader reader = new InputStreamReader(new ByteArrayInputStream(content));
         Writer writer = new StringWriter();
         
         TttGameJsonHandler.playGame(reader, writer);
     }

     // util used for required Exception
     void testProperException(final String strWithError) throws Exception {
         thrown.expect(Exception.class);

         byte[] content = strWithError.getBytes();

         Reader reader = new InputStreamReader(new ByteArrayInputStream(content));
         Writer writer = new StringWriter();
         
         TttGameJsonHandler.playGame(reader, writer);
     }


}
