package com.klabeeva.TicTacToe;

public class TttException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	TttException(String message){
		super("Error in tic-tac-toe! " +message);
	}

}
