/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.logic.piece.itemizable;

import pt.up.fe.lpoo.logic.Board;
import pt.up.fe.lpoo.logic.Coordinate;

import java.util.Random;

public class Dragon extends ItemizablePiece {
    protected boolean _sleeping = false;


    @Override
    public Boolean move(Board.Direction dir) throws Exception {
        Random rand = new Random();
        int num = rand.nextInt(19);

        try {
            if (num < 14) {
                _sleeping = false;
                return super.move(dir);
            }

            _sleeping = true;
            return false;
        } catch (Exception exc) {
            return false;
        }

    }

    public Dragon() {
        _position = new Coordinate(-1, -1);
    }

    public Dragon(Coordinate crd) {
        _position = crd;
    }

    public Dragon(int x, int y) {
        _position = new Coordinate(x, y);
    }

    public void setIsSleeping() {
        _sleeping = true;
    }

    public boolean getIsSleeping() {
        return _sleeping;
    }
}
