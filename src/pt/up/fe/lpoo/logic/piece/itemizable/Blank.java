/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.logic.piece.itemizable;

import pt.up.fe.lpoo.logic.Coordinate;
import pt.up.fe.lpoo.logic.piece.Piece;

public class Blank extends Piece {
    private Boolean _isExit = false;
    private Boolean _hasItem = false;

    public Boolean getIsExit() {
        return _isExit;
    }

    public Boolean getHasItem() {
        return _hasItem;
    }

    public void setIsExit(Boolean set) {
        _isExit = set;
    }

    public void setHasItem(Boolean set) {
        _hasItem = set;
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
