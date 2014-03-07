/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.logic.piece.itemizable;

        import pt.up.fe.lpoo.logic.Board;
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
                return false;
            } else {
                if (((ItemizablePiece) nextObj).getHasItem()) {
                    setHasItem(true);

                    ((ItemizablePiece) nextObj).setHasItem(false);
                }
            }

            nextObj.setCoordinate(_position);

            _position = new Coordinate(_position.x + x, _position.y + y);

            return true;
        }

        return false;
    }
}
