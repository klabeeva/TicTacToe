package com.klabeeva.TicTacToe;


public class TttBoardStrings {
// constants
	
//  game staring	
	static final String kEmptyBoard = "         ";
	static final String kStartBoard = "    x    ";
	
	
/****************
 * error in input data	
 */
	static final String kStatusErrorTooBig =  
			"{\"state\":10,\"bCross\":true,\"board\":\"00x x x  \"}";
	static final String kStatusErrorTooSmall =  
			"{\"state\":-1,\"bCross\":true,\"board\":\"00x x x  \"}";
	static final String kStatusErrorWrongType =  
			"{\"state\":\"text\",\"bCross\":true,\"board\":\"00x x x  \"}";
	static final String kStatusErrorWrongStatus =  
			"{\"state\":3,\"bCross\":true,\"board\":\"00x x x  \"}";

	static final String kCrossError =  
			"{\"state\":2,\"bCross\":25,\"board\":\"00x x x  \"}";

	static final String kBoardErrorWrongType =  
			"{\"state\":2,\"bCross\":true,\"board\":1}";
	static final String kBoardErrorTooShort =  
			"{\"state\":2,\"bCross\":true,\"board\":\"00x  \"}";
	static final String kBoardErrorWrongChar =  
			"{\"state\":2,\"bCross\":true,\"board\":\"a0x x x  \"}";
	static final String kBoardErrorTooLong =  
			"{\"state\":2,\"bCross\":true,\"board\":\"00x x x  000\"}";
	
	static final String kBoardErrorNoBoard =  
	"{\"state\":1,\"bCross\":true}";

/**************** board state before 'play' method 
*/
/****************
 * diagonals	
 */
	static final String kBeforeDiagonalLowCornerWinLeft = 
			                        "x00"
			+ 						" x "
			+                       "   ";
	
	static final String kBeforeDiagonalMiddleWinLeft =  
			                        "x00"
			+ 				        "   "
			+                       "  x";
	
	
	static final String kBeforeDiagonalUpperCornerWinRight = 
                        "00 "
+ 						" x "
+                       "x  ";
	
	static final String kBeforeDiagonalMiddleWinRight =  
			                        "00x"
			+ 				        "   "
			+                       "x  ";
	
	static final String kDiagonalLeftWin = 
										"x00"
				+ 				        " x "
				+                       "  x";
	
	static final String kDiagonalRightWin =  
                        "00x"
+ 				        " x "
+                       "x  ";
	
	
	static final String kDiagonalCorner2 =  "0 x"
			+ 				         "0x "
			+                        "   ";
	static final String kDiagonalRightWin2 =  
			                        "0 x"
			+ 				        "0x "
			+                       "x  ";

	
	/**************** output string after 'play' method 
	*/
	/****************
	 * diagonals	
	 */
	static final String kDefaultStartGameRes = 
			"{\"state\":1,\"bCross\":true,\"board\":\"    x    \"}";
	static final String kDiagonalMiddlePrevetnLossLeftStream = 
			"{\"state\":1,\"bCross\":false,\"board\":\"x00 0   x\"}";
	static final String kDiagonalLeftWinRes =  
			"{\"state\":2,\"bCross\":true,\"board\":\"x00 x   x\"}";
	static final String kDiagonalRightWinRes=  
			"{\"state\":2,\"bCross\":true,\"board\":\"00x x x  \"}";

	/**************** 
	 * input/result strings for testing rows 
	*/
	static final String kRowTopMiddleWin = 
			"{\"state\":1,\"bCross\":true,\"board\":\"x x0  0  \"}";
	static final String kRowTopMiddleWinRes = 
			"{\"state\":2,\"bCross\":true,\"board\":\"xxx0  0  \"}";
	static final String kRowTopMiddlePreventLoss = 
			"{\"state\":1,\"bCross\":false,\"board\":\"x x0  0  \"}";
	static final String kRowTopMiddlePreventLossRes = 
			"{\"state\":1,\"bCross\":false,\"board\":\"x0x0  0  \"}";

	static final String kRowMiddleRightWin = 
			"{\"state\":1,\"bCross\":true,\"board\":\"0  xx 0  \"}";
	static final String kRowMiddleRightWinRes = 
			"{\"state\":2,\"bCross\":true,\"board\":\"0  xxx0  \"}";
	static final String kRowMiddleRightPreventLoss = 
			"{\"state\":1,\"bCross\":false,\"board\":\"0  xx 0  \"}";
	static final String kRowMiddleRightPreventLossRes = 
			"{\"state\":1,\"bCross\":false,\"board\":\"0  xx00  \"}";

	static final String kRowLowLeftWin = 
			"{\"state\":1,\"bCross\":true,\"board\":\"0  0   xx\"}";
	static final String kRowLowLeftWinRes = 
			"{\"state\":2,\"bCross\":true,\"board\":\"0  0  xxx\"}";
	static final String kRowLowLeftPreventLoss = 
			"{\"state\":1,\"bCross\":false,\"board\":\"0   0  xx\"}";
	static final String kRowLowLeftPreventLossRes = 
			"{\"state\":1,\"bCross\":false,\"board\":\"0   0 0xx\"}";

	/**************** 
	 * input/result strings for testing columns 
	*/
	static final String kColumnLeftMiddleWin = 
			"{\"state\":1,\"bCross\":true,\"board\":\"x0  0 x  \"}";
	static final String kColumnLeftMiddleWinRes = 
			"{\"state\":2,\"bCross\":true,\"board\":\"x0 x0 x  \"}";
	static final String kColumnLeftMiddlePreventLoss = 
			"{\"state\":1,\"bCross\":false,\"board\":\"x   0 x  \"}";
	static final String kColumnLeftMiddlePreventLossRes = 
			"{\"state\":1,\"bCross\":false,\"board\":\"x  00 x  \"}";

	static final String kColumnMiddleTopWin = 
			"{\"state\":1,\"bCross\":true,\"board\":\"  0 x  x \"}";
	static final String kColumnMiddleTopWinRes = 
			"{\"state\":2,\"bCross\":true,\"board\":\" x0 x  x \"}";
	static final String kColumnMiddleTopPreventLoss = 
			"{\"state\":1,\"bCross\":false,\"board\":\"0 x0    x\"}";
	static final String kColumnMiddleTopPreventLossRes = 
			"{\"state\":1,\"bCross\":false,\"board\":\"0 x0 0   x\"}";

	static final String kColumnRightLowWin = 
			"{\"state\":1,\"bCross\":true,\"board\":\"0  0   xx\"}";
	static final String kColumnRightLowWinRes = 
			"{\"state\":2,\"bCross\":true,\"board\":\"0  0  xxx\"}";
	static final String kColumnRightLowPreventLoss = 
			"{\"state\":1,\"bCross\":false,\"board\":\"0   0  xx\"}";
	static final String kColumnRightLowPreventLossRes = 
			"{\"state\":1,\"bCross\":false,\"board\":\"0   0 0xx\"}";

	
}
