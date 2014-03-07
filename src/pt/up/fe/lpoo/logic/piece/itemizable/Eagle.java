/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.logic.piece.itemizable;

import pt.up.fe.lpoo.logic.Board;
import pt.up.fe.lpoo.logic.Coordinate;

public class Eagle extends ItemizablePiece {
    public Boolean _onGround = false;
    protected Coordinate FirstPos;

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

    public Boolean move(Coordinate c1) throws Exception {
        // Sword on the same line
        try {


            if (_position.y == c1.y) {
                if (c1.x < _position.x)
                    return super.move(Board.Direction.LEFT);
                if (c1.x > _position.x)
                    return super.move(Board.Direction.RIGHT);
            }
            // Sword on the same Column
            if (_position.x == c1.x) {
                if (c1.y < _position.y)
                    return super.move(Board.Direction.UP);
                if (c1.y > _position.y)
                    return super.move(Board.Direction.DOWN);
            }
            // Nor the same line or column
            if (_position.x < c1.x) {
                if (_position.y < c1.y) {

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


