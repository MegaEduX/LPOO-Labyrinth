/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.logic.piece;

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
}
