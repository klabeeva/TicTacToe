package com.klabeeva.TicTacToe;

/**
 * Interface for Tic-tac-toe game
 * Used in TttGameJsonHandler
 * Current implementation - TttSmart3by3Game
 *
 */
public interface ITttGame {
    /**
     * @return true if algorithm uses 'cross' ('x')
     *         false otherwise
     */
    public boolean getCross();

    /**
     * @return board data in String format
     */
    public String getBoardAsString();

    /**
     * @return current game state
     */
    public int getStatusAsInt();

    /**
     * main method:
     *   checks if game is done
     *   makes move (if not done)
     *   sets new game state
     * @throws Exception
     */
    public void play() throws Exception;

}
