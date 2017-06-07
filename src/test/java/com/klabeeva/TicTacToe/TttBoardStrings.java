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


}
