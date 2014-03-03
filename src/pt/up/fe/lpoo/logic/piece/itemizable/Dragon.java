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
    private Boolean _sleeping = false;
    private Behavior _behavior = Behavior.SLEEP;

    public enum Behavior {STOP, NO_SLEEP, SLEEP};

    @Override public Boolean move(Board.Direction dir) throws Exception {
        if (_behavior == Behavior.STOP)
            return false;
        else if (_behavior == Behavior.NO_SLEEP)
            return super.move(dir);

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
            throw exc;
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

    public Behavior getBehavior() {
        return _behavior;
    }

    public void setBehavior(Behavior bhv) {
        _behavior = bhv;
    }
}
