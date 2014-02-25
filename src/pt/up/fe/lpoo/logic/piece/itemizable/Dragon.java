/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.logic.piece.itemizable;

import pt.up.fe.lpoo.logic.Coordinate;

public class Dragon extends ItemizablePiece {
    public Dragon() {
        _position = new Coordinate(-1, -1);
    }

    public Dragon(Coordinate crd) {
        _position = crd;
    }

    public Dragon(int x, int y) {
        _position = new Coordinate(x, y);
    }
}
