/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.logic.piece;

import pt.up.fe.lpoo.logic.Coordinate;

public class Wall extends Piece {
    public Wall() {
        _position = new Coordinate(-1, -1);
    }

    public Wall(Coordinate crd) {
        _position = crd;
    }

    public Wall(int x, int y) {
        _position = new Coordinate(x, y);
    }
}
