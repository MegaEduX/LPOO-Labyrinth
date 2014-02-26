/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.logic.piece.itemizable;

import pt.up.fe.lpoo.logic.Coordinate;
import pt.up.fe.lpoo.logic.piece.Piece;

public class Blank extends ItemizablePiece {
    private Boolean _isExit = false;

    public Boolean getIsExit() {
        return _isExit;
    }

    public void setIsExit(Boolean set) {
        _isExit = set;
    }

    public Blank() {
        _position = new Coordinate(-1, -1);
    }

    public Blank(Coordinate crd) {
        _position = crd;
    }

    public Blank(int x, int y) {
        _position = new Coordinate(x, y);
    }
}
