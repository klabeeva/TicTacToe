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
    	testAlgorithm(TttBoardStrings.kBeforeDiagonalMiddleWinLeft,
    			TttBoardStrings.kDiagonalLeftWinRes);
    }

    @Test
    public void testDiagonalMiddleWinRight() {
    	testAlgorithm(TttBoardStrings.kBeforeDiagonalMiddleWinRight,
    			TttBoardStrings.kDiagonalRightWinRes);
    }

    @Test
    public void testDiagonalLowCornerLeft() {
    	testAlgorithm(TttBoardStrings.kBeforeDiagonalLowCornerWinLeft,
    			TttBoardStrings.kDiagonalLeftWinRes);
    }

    @Test
    public void testDiagonalUpperCornerWinRight() {
    	testAlgorithm(TttBoardStrings.kBeforeDiagonalUpperCornerWinRight,
    			TttBoardStrings.kDiagonalRightWinRes);
    }

    @Test
    public void testDiagonalMiddlePrevetnLossLeft() {
    	testAlgorithm(TttBoardStrings.kBeforeDiagonalMiddleWinLeft,
    			TttBoardStrings.kDiagonalLeftWinRes);
    }

    
    // utils
    
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
     void testAlgorithm(final String kStr, final String resStr) {
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
