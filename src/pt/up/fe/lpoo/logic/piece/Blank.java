/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.logic.piece;

import pt.up.fe.lpoo.logic.Coordinate;

public class Blank extends Piece {
    private Boolean _isExit;
    private Boolean _hasItem;

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
