/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.logic.piece.itemizable;

        import pt.up.fe.lpoo.logic.Coordinate;
        import pt.up.fe.lpoo.logic.piece.Piece;

public class ItemizablePiece extends Piece {
    protected Boolean _item = false;

    public ItemizablePiece() {
        _position = new Coordinate(-1, -1);
    }

    public ItemizablePiece(Coordinate crd) {
        _position = crd;
    }

    public ItemizablePiece(int x, int y) {
        _position = new Coordinate(x, y);
    }

    public Boolean getHasItem() {
        return _item;
    }

    public void setHasItem(Boolean hi) {
        _item = hi;
    }
}
