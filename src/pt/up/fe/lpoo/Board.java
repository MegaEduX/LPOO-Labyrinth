/**
 * Created by MegaEduX on 20/02/14.
 */

package pt.up.fe.lpoo;

import pt.up.fe.lpoo.Coordinate;

public class Board {
    public enum Type {WALL, HERO, SWORD, EXIT, BLANK};
    public enum Direction {UP, LEFT, DOWN, RIGHT};
    public enum State {RUNNING, LOST, WON};

    private State gameState = State.RUNNING;

    private Hero hero = new Hero();

    private Type boardRep[][] = {
            {Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL},
            {Type.WALL, Type.HERO, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.WALL},
            {Type.WALL, Type.BLANK, Type.WALL, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL},
            {Type.WALL, Type.BLANK, Type.WALL, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL},
            {Type.WALL, Type.BLANK, Type.WALL, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL},
            {Type.WALL, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.WALL, Type.BLANK, Type.EXIT},
            {Type.WALL, Type.BLANK, Type.WALL, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL},
            {Type.WALL, Type.BLANK, Type.WALL, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL},
            {Type.WALL, Type.SWORD, Type.WALL, Type.WALL, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.WALL},
            {Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL}
    };

    public Coordinate getLocation(Type piece) throws Exception {
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (boardRep[i][j] == piece)
                    return new Coordinate(j, i);

        throw new Exception("Piece Not Found.");
    }

    public State getGameState() {
        return gameState;
    }

    public Boolean getHeroIsArmed() {
        return hero.armed;
    }

    public Boolean moveHeroTo(Direction dir) {
        if (gameState != State.RUNNING)
            return false;

        Coordinate loc;

        try {
            loc = getLocation(Type.HERO);
        } catch (Exception exc) {
            return false;
        }

        switch (dir) {

            case UP:

                if (boardRep[loc.y - 1][loc.x] == Type.BLANK || boardRep[loc.y - 1][loc.x] == Type.EXIT || boardRep[loc.y - 1][loc.x] == Type.SWORD) {
                    if (boardRep[loc.y - 1][loc.x] == Type.EXIT) {
                        if (!hero.armed)
                            return false;

                        gameState = State.WON;
                    } else if (boardRep[loc.y - 1][loc.x] == Type.SWORD)
                        hero.armed = true;

                    boardRep[loc.y - 1][loc.x] = Type.HERO;
                    boardRep[loc.y][loc.x] = Type.BLANK;

                    return true;
                }

                break;

            case LEFT:

                if (boardRep[loc.y][loc.x - 1] == Type.BLANK  || boardRep[loc.y][loc.x - 1] == Type.EXIT || boardRep[loc.y][loc.x - 1] == Type.SWORD) {
                    if (boardRep[loc.y][loc.x - 1] == Type.EXIT) {
                        if (!hero.armed)
                            return false;

                        gameState = State.WON;
                    } else if (boardRep[loc.y][loc.x - 1] == Type.SWORD)
                        hero.armed = true;

                    boardRep[loc.y][loc.x - 1] = Type.HERO;
                    boardRep[loc.y][loc.x] = Type.BLANK;

                    return true;
                }

                break;

            case DOWN:

                if (boardRep[loc.y + 1][loc.x] == Type.BLANK || boardRep[loc.y + 1][loc.x] == Type.EXIT || boardRep[loc.y + 1][loc.x] == Type.SWORD) {
                    if (boardRep[loc.y + 1][loc.x] == Type.EXIT) {
                        if (!hero.armed)
                            return false;

                        gameState = State.WON;
                    } else if (boardRep[loc.y + 1][loc.x] == Type.SWORD)
                        hero.armed = true;

                    boardRep[loc.y + 1][loc.x] = Type.HERO;
                    boardRep[loc.y][loc.x] = Type.BLANK;

                    return true;
                }

                break;

            case RIGHT:

                if (boardRep[loc.y][loc.x + 1] == Type.BLANK || boardRep[loc.y][loc.x + 1] == Type.EXIT || boardRep[loc.y][loc.x + 1] == Type.SWORD) {
                    if (boardRep[loc.y][loc.x + 1] == Type.EXIT) {
                        if (!hero.armed)
                            return false;

                        gameState = State.WON;
                    } else if (boardRep[loc.y][loc.x + 1] == Type.SWORD)
                        hero.armed = true;

                    boardRep[loc.y][loc.x + 1] = Type.HERO;
                    boardRep[loc.y][loc.x] = Type.BLANK;

                    return true;
                }

                break;

        }

        return false;
    }

    public void printBoard() {
        for (int i = 0; i < 10; i++) {
            String outStr = "";

            for (int j = 0; j < 10; j++)
                switch (boardRep[i][j]) {
                    case WALL:

                        outStr += "=";

                        break;

                    case HERO:

                        outStr += "H";

                        break;

                    case SWORD:

                        outStr += "E";

                        break;

                    case EXIT:

                        outStr += "S";

                        break;

                    case BLANK:

                        outStr += " ";

                        break;
                }

            System.out.println(outStr);
        }
    }
}
