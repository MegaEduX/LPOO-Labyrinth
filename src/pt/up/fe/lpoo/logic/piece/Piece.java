/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.logic.piece;

import pt.up.fe.lpoo.logic.Board;
import pt.up.fe.lpoo.logic.Coordinate;
import pt.up.fe.lpoo.logic.piece.itemizable.Hero;

import java.util.Vector;

public class Piece {
    protected Coordinate _position;
    protected Board _board;

    public Coordinate getCoordinate() {
        return _position;
    }

    public Boolean move(Board.Direction dir) throws Exception {
        Vector<Piece> near = new Vector<Piece>();

        Hero hero;

        try {
            near.add(_board.getPiece(new Coordinate(_position.x, _position.y - 1)));
            near.add(_board.getPiece(new Coordinate(_position.x - 1, _position.y)));
            near.add(_board.getPiece(new Coordinate(_position.x, _position.y + 1)));
            near.add(_board.getPiece(new Coordinate(_position.x + 1, _position.y)));

            hero = (Hero) _board.getPiecesWithType(Board.Type.HERO).get(0);
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
                if (!hero.armed || !(this instanceof Hero))
                    return false;

                //  The game has been won by now, honestly.
            } else if (((Blank) nextObj).getHasItem()) {
                if (this instanceof Hero) {
                    hero.armed = true;

                    ((Blank) nextObj).setHasItem(false);
                }
            }

            return true;
        }

        return false;
    }
}
