package com.klabeeva.TicTacToe;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TttBoardTest {
	
	TttBoard mBoard;

	@Before
	public void setUp() throws Exception {
		mBoard = new TttBoard(3,3);
	}

	
	/**
	 * We start the game
	 */
	@Test
	public void testStartBoard() {
		try{
			mBoard.getBoardDataAsStr();
			String boardStr = mBoard.getBoardDataAsStr();
			assertEquals(boardStr, TttBoardStrings.kEmptyBoard);
		} catch(Exception e){
			fail("Exception on empty board");
		}
	}

	/// testing diagonals
	/**
	 * Left diagonal middle x - win
	 */
	@Test
	public void testDiagonalLeftFinish() {
		try{
			boolean bCross = true;
			mBoard.setBoardData(TttBoardStrings.kBeforeDiagonalLowCornerWinLeft);
			boolean bret = mBoard.finish(bCross);
			String boardStr = mBoard.getBoardDataAsStr();
			assertTrue(bret);
			assertEquals(boardStr, TttBoardStrings.kDiagonalLeftWin);
		} catch(Exception e){
			fail("Exception on empty board");
		}
	}
	
	/**
	 * Right diagonal middle x - win
	 */
	@Test
	public void testDiagonalRightFinish() {
		try{
			boolean bCross = true;
			mBoard.setBoardData(TttBoardStrings.kBeforeDiagonalMiddleWinRight);
			boolean bret = mBoard.finish(bCross);
			String boardStr = mBoard.getBoardDataAsStr();
			assertTrue(bret);
			assertEquals(boardStr, TttBoardStrings.kDiagonalRightWin);
		} catch(Exception e){
			fail("Exception on empty board");
		}
	}

	/**
	 * Left diagonal corner x - win
	 */
	@Test
	public void testDiagonalLeftCornerFinish() {
		try{
			boolean bCross = true;
			mBoard.setBoardData(TttBoardStrings.kBeforeDiagonalLowCornerWinLeft);
			boolean bret = mBoard.finish(bCross);
			String boardStr = mBoard.getBoardDataAsStr();
			assertTrue(bret);
			assertEquals(boardStr, TttBoardStrings.kDiagonalLeftWin);
		} catch(Exception e){
			fail("Exception on empty board");
		}
	}
	
	/**
	 * Right diagonal corner x - win
	 */
	@Test
	public void testDiagonalRightCornerFinish() {
		try{
			boolean bCross = true;
			mBoard.setBoardData(TttBoardStrings.kDiagonalCorner2);
			boolean bret = mBoard.finish(bCross);
			String boardStr = mBoard.getBoardDataAsStr();
			assertTrue(bret);
			assertEquals(boardStr, TttBoardStrings.kDiagonalRightWin2);
		} catch(Exception e){
			fail("Exception on empty board");
		}
	}

}
