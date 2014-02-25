/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.logic.piece.itemizable;

import pt.up.fe.lpoo.logic.Coordinate;

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
}
