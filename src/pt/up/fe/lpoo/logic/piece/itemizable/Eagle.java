/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.logic.piece.itemizable;

import pt.up.fe.lpoo.logic.Board;
import pt.up.fe.lpoo.logic.Coordinate;

public class Eagle extends ItemizablePiece {

    private Boolean _onGround = false;
    //private Hero _hero;
    protected Coordinate FirstPos = null;

    public Eagle(Boolean stat, Coordinate c1) {
        _onGround = stat;
        FirstPos = c1;
    }


    public void setOnGround() {
        _onGround = true;
    }

    public void setIsFlying() {
        _onGround = false;
    }

    public Boolean isFlying() {
        return !_onGround;
    }


    @Override
    public Boolean move(Board.Direction Dir) throws Exception {
        throw new Exception("This can't be called, bro!");
    }

    private Boolean moveEagle(Board.Direction dir) throws Exception {
        Coordinate crdDiff = new Coordinate(0, 0);

        try {
            _moveSharedCode(dir, crdDiff);
        } catch (Exception exc) {
            throw exc;
        }

        Integer x = crdDiff.x, y = crdDiff.y;

        _position = new Coordinate(_position.x + x, _position.y + y);

        return true;
    }


    public Boolean move(Coordinate c1) throws Exception {
        // Sword on the same line
        try {


            if (_position.y == c1.y) {
                if (c1.x < _position.x)
                    return moveEagle(Board.Direction.LEFT);
                if (c1.x > _position.x)
                    return moveEagle(Board.Direction.RIGHT);
            }
            // Sword on the same Column
            if (_position.x == c1.x) {
                if (c1.y < _position.y)
                    return moveEagle(Board.Direction.UP);
                if (c1.y > _position.y)
                    return moveEagle(Board.Direction.DOWN);
            }
            // Nor the same line or column
            if (_position.x < c1.x) {
                if (_position.y < c1.y) {
                    return (moveEagle(Board.Direction.DOWN) && moveEagle(Board.Direction.RIGHT));
                }
                if (_position.y > c1.y) {
                    return (moveEagle(Board.Direction.UP) && moveEagle(Board.Direction.RIGHT));
                }

            }
            if (_position.x > c1.x) {
                if (_position.y < c1.y) {
                    return (moveEagle(Board.Direction.DOWN) && moveEagle(Board.Direction.LEFT));
                }
                if (_position.y > c1.y) {
                    return (moveEagle(Board.Direction.UP) && moveEagle(Board.Direction.LEFT));
                }
            }
            // Found the Sword
            if (_position.x == c1.x && _position.y == c1.y)
                _item = true;
            return false;
        } catch (Exception exc) {
            throw exc;
        }
    }
}


