/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and Joao Almeida.
 */

package pt.up.fe.lpoo.logic.piece;

import pt.up.fe.lpoo.logic.Board;
import pt.up.fe.lpoo.logic.Coordinate;
import pt.up.fe.lpoo.logic.piece.itemizable.Blank;

import java.io.Serializable;

import java.util.Vector;

public class Piece implements Serializable {
    protected Coordinate _position;

    protected Board _board;

    public Piece() {
        _position = new Coordinate(-1, -1);
    }

    public Piece(Coordinate crd) {
        _position = crd;
    }

    public Piece(int x, int y) {
        _position = new Coordinate(x, y);
    }

    public Coordinate getCoordinate() {
        return _position;
    }

    public Board getBoard() {
        return _board;
    }

    public void setCoordinate(Coordinate crd) {
        _position = crd;
    }

    public void setBoard(Board brd) {
        _board = brd;
    }

    protected Piece _moveSharedCode(Board.Direction dir, Coordinate crdDiff) throws Exception {
        Vector<Piece> near = new Vector<Piece>();

        try {
            near.add(_board.getPiece(new Coordinate(_position.x, _position.y - 1)));
            near.add(_board.getPiece(new Coordinate(_position.x - 1, _position.y)));
            near.add(_board.getPiece(new Coordinate(_position.x, _position.y + 1)));
            near.add(_board.getPiece(new Coordinate(_position.x + 1, _position.y)));
        } catch (Exception exc) {
            throw exc;
        }

        int x = 0, y = 0;

        Piece nextObj;

        switch (dir) {

            case UP:

                nextObj = near.get(0);

                y--;

                break;

            case LEFT:

                nextObj = near.get(1);

                x--;

                break;

            case DOWN:

                nextObj = near.get(2);

                y++;

                break;

            case RIGHT:

                nextObj = near.get(3);

                x++;

                break;

            default:

                throw new Exception("What is this? A new coordinate system!?");

        }

        crdDiff.x = x;
        crdDiff.y = y;

        return nextObj;
    }

    public Boolean move(Board.Direction dir) throws Exception {
        Piece nextObj;
        Coordinate crdDiff = new Coordinate(0, 0);

        try {
            nextObj = _moveSharedCode(dir, crdDiff);
        } catch (Exception exc) {
            throw exc;
        }

        Integer x = crdDiff.x, y = crdDiff.y;

        if (nextObj instanceof Blank) {
            if (((Blank) nextObj).getIsExit())
                return false;

            nextObj.setCoordinate(_position);

            _position = new Coordinate(_position.x + x, _position.y + y);

            return true;
        }

        return false;
    }
}
