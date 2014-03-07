/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.logic.piece.itemizable;

import pt.up.fe.lpoo.logic.Board;
import pt.up.fe.lpoo.logic.Coordinate;
import pt.up.fe.lpoo.logic.piece.Piece;

public class Hero extends ItemizablePiece {
    public Hero() {
        _position = new Coordinate(-1, -1);
    }

    public Hero(Coordinate crd) {
        _position = crd;
    }

    public Hero(int x, int y) {
        _position = new Coordinate(x, y);
    }

    @Override public Boolean move(Board.Direction dir) throws Exception {
        Piece nextObj;
        Integer x = 0, y = 0;

        try {
            nextObj = _moveSharedCode(dir, x, y);
        } catch (Exception exc) {
            throw exc;
        }

        if (nextObj instanceof Blank) {
            if (((Blank) nextObj).getIsExit()) {
                if (!getHasItem())
                    return false;

                ((Blank) nextObj).setIsExit(false);
            } else {
                if (((Blank) nextObj).getHasItem()) {
                    setHasItem(true);

                    ((Blank) nextObj).setHasItem(false);
                }
            }

            nextObj.setCoordinate(_position);

            _position = new Coordinate(_position.x + x, _position.y + y);

            return true;
        }

        return false;
    }
}
