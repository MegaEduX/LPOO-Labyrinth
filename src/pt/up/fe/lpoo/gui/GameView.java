/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.gui;

import pt.up.fe.lpoo.logic.Board;
import pt.up.fe.lpoo.logic.BoardGenerator;
import pt.up.fe.lpoo.logic.Coordinate;
import pt.up.fe.lpoo.logic.piece.Piece;
import pt.up.fe.lpoo.logic.piece.itemizable.Dragon;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

public class GameView {
    static private JFrame frame;

    public static void main(String[] args) {
        Board brd;

        try {
            brd = new Board();

            BoardGenerator gen = new BoardGenerator(10, 10, 1);

            Vector<Piece> ooBoard = gen.getDefaultBoard();

            for (Piece pc : ooBoard)
                pc.setBoard(brd);

            brd.setBoardPieces(ooBoard);
            brd.setWidth(10);
            brd.setHeight(10);

            Dragon drag = (Dragon) brd.getPiecesWithType(Board.Type.DRAGON).get(0);

            drag.setBehavior(Dragon.Behavior.STOP);

            frame = new JFrame("Labyrinth Game View");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(500, 522));
            frame.getContentPane().add(new GamePanel(brd, new Coordinate(500, 500)));

            //

            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {

        }
    }
}
