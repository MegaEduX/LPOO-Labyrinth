/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.logic.piece;

import pt.up.fe.lpoo.logic.Board;
import pt.up.fe.lpoo.logic.Coordinate;
import pt.up.fe.lpoo.logic.piece.itemizable.Blank;
import pt.up.fe.lpoo.logic.piece.itemizable.Dragon;
import pt.up.fe.lpoo.logic.piece.itemizable.Hero;
import pt.up.fe.lpoo.logic.piece.itemizable.ItemizablePiece;

import java.util.Vector;

public class Piece {
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

    public Boolean move(Board.Direction dir) throws Exception {
        Vector<Piece> near = new Vector<Piece>();

        try {
            near.add(_board.getPiece(new Coordinate(_position.x, _position.y - 1)));
            near.add(_board.getPiece(new Coordinate(_position.x - 1, _position.y)));
            near.add(_board.getPiece(new Coordinate(_position.x, _position.y + 1)));
            near.add(_board.getPiece(new Coordinate(_position.x + 1, _position.y)));
        } catch (Exception exc) {
            return false;
        }

        Piece nextObj;

        int x = 0, y = 0;

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

        if (nextObj instanceof Blank) {
            if (((Blank) nextObj).getIsExit()) {
                if ((this instanceof Hero && !((Hero) this).getHasItem()) || !(this instanceof Hero))
                    return false;

                ((Blank) nextObj).setIsExit(false);

                //  The game has been won by now, honestly.
            } else if (this instanceof ItemizablePiece) {
                if (((ItemizablePiece) nextObj).getHasItem()) {
                    ((Hero) this).setHasItem(true);

                    ((Blank) nextObj).setHasItem(false);
                } else if (this instanceof Dragon && ((ItemizablePiece) this).getHasItem()) {
                    ((Dragon) this).setHasItem(false);

                    ((Blank) nextObj).setHasItem(true);
                }
            }

            nextObj.setCoordinate(_position);

            _position = new Coordinate(_position.x + x, _position.y + y);

            return true;
        }

        return false;
    }
}
