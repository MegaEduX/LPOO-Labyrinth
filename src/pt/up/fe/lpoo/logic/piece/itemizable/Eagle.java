/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.logic.piece.itemizable;

import pt.up.fe.lpoo.logic.Board;
import pt.up.fe.lpoo.logic.Coordinate;
import pt.up.fe.lpoo.logic.piece.Piece;

public class Eagle extends ItemizablePiece {
    public Boolean _onGround = false;
    protected Coordinate FirstPos;

    public Eagle(Boolean onGround, Coordinate pos) {
        super();
        _onGround = onGround;
        FirstPos = new Coordinate(pos.x, pos.y);
        _position = pos;
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

    public Boolean moveEagle(Board.Direction dir) throws Exception {
        Piece nextObj;
        Coordinate crdDiff = new Coordinate(0, 0);

        try {
            nextObj = _moveSharedCode(dir, crdDiff);
        } catch (Exception exc) {
            throw exc;
        }

        Integer x = crdDiff.x, y = crdDiff.y;


        if (((ItemizablePiece) nextObj).getHasItem()) {
            setHasItem(true);

            ((ItemizablePiece) nextObj).setHasItem(false);
        }

        nextObj.setCoordinate(_position);

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
                    moveEagle(Board.Direction.DOWN);
                    return moveEagle(Board.Direction.RIGHT);
                }
                if (_position.y > c1.y) {
                    moveEagle(Board.Direction.UP);
                    return moveEagle(Board.Direction.RIGHT);
                }

            }
            if (_position.x > c1.x) {
                if (_position.y < c1.y) {
                    moveEagle(Board.Direction.DOWN);
                    return moveEagle(Board.Direction.LEFT);
                }
                if (_position.y > c1.y) {
                    moveEagle(Board.Direction.UP);
                    return moveEagle(Board.Direction.LEFT);
                }

            }
            // Found the Sword
            _item = true;
            // If nothing

            return false;
        } catch (Exception exc) {
            throw exc;
        }
    }
}