/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.cli;

import pt.up.fe.lpoo.logic.Board;
import pt.up.fe.lpoo.logic.Coordinate;

import pt.up.fe.lpoo.logic.piece.*;
import pt.up.fe.lpoo.logic.piece.itemizable.*;

public class Printer {
    private Board _board = null;

    private Printer() {
        //  You shall not... call this!
    }

    public Printer(Board board) {
        _board = board;
    }

    public void printBoard() {
        for (int i = 0; i < _board.getHeight(); i++)
            for (int j = 0; j < _board.getWidth(); j++) {
                Piece pc;

                try {
                    pc = _board.getPiece(new Coordinate(j, i));
                } catch (Exception exc) {
                    System.out.print(" ");

                    continue;
                }

                if (pc instanceof Wall)
                    System.out.print("█");
                else if (pc instanceof Hero)
                    System.out.print(((Hero) pc).getHasItem() ? "A" : "H");
                else if (pc instanceof Blank && ((Blank) pc).getHasItem())
                    System.out.print("E");
                else if (pc instanceof Dragon)
                    System.out.print(((Dragon) pc).getHasItem() ? "F" : "D");
                else if (pc instanceof Blank && ((Blank) pc).getIsExit())
                    System.out.print("S");
            }
    }
}
