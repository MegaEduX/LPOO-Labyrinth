/**
 * Created by MegaEduX on 20/02/14.
 */

package pt.up.fe.lpoo;

public class Board {
    public enum Type {WALL, HERO, EXIT, BLANK}

    ;

    private Type boardRep[][] = {
            {Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL},
            {Type.WALL, Type.HERO, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.WALL},
            {Type.WALL, Type.BLANK, Type.WALL, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL},
            {Type.WALL, Type.BLANK, Type.WALL, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL},
            {Type.WALL, Type.BLANK, Type.WALL, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL},
            {Type.WALL, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.WALL, Type.BLANK, Type.EXIT},
            {Type.WALL, Type.BLANK, Type.WALL, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL},
            {Type.WALL, Type.BLANK, Type.WALL, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL, Type.BLANK, Type.WALL},
            {Type.WALL, Type.BLANK, Type.WALL, Type.WALL, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.BLANK, Type.WALL},
            {Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL, Type.WALL}
    };

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
