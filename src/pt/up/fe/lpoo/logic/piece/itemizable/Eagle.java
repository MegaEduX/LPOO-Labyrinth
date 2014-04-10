/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and Joao Almeida.
 */

package pt.up.fe.lpoo.logic.piece.itemizable;

import pt.up.fe.lpoo.logic.Board;
import pt.up.fe.lpoo.logic.Coordinate;
import pt.up.fe.lpoo.logic.piece.Piece;

public class Eagle extends ItemizablePiece {
    public Boolean _onGround = false;
    protected Coordinate _firstPosition;

    /**
     * Constructs a new Eagle.
     * 
     * @param onGround true if the eagle starts on the ground, false if not.
     * @param pos The eagle's starting position.
     */

    public Eagle(Boolean onGround, Coordinate pos) {
        super();
        
        _onGround = onGround;
        _firstPosition = new Coordinate(pos.x, pos.y);
        _position = pos;
    }

    /**
     * Lands the eagle.
     */

    public void landEagle() {
        _onGround = true;
    }

    /**
     * Launches the eagle.
     */

    public void launchEagle() {
        _onGround = false;
    }

    /**
     * Getter for the flying state of the eagle.
     *
     * @return true if the eagle is flying, false if not.
     */

    public Boolean isFlying() {
        return !_onGround;
    }

    @Override
    public Boolean move(Board.Direction Dir) throws Exception {
        throw new Exception("This can't be called, bro!");
    }

    private Boolean moveEagle(Board.Direction dir) throws Exception {
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

    /**
     * Tells the eagle to move to a new coordinate.
     *
     * @param c1 The coordinate to move the eagle to.
     * @return true if possible, false if not.
     *
     * @throws Exception Throws an exception if anything went wrong.
     */

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