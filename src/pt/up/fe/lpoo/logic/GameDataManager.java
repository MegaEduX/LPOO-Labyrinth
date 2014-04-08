/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and Joao Almeida.
 */

package pt.up.fe.lpoo.logic;

import java.io.*;

public class GameDataManager {
    public static boolean saveGameState(Board board, String filePath) {
        try {
            FileOutputStream os = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(os);

            oos.writeObject(board);

            oos.close();
            os.close();
        } catch (IOException exc) {
            return false;
        }

        return true;
    }

    public static Board loadGameState(String filePath) throws Exception {
        FileInputStream is = new FileInputStream(filePath);
        ObjectInputStream ois = new ObjectInputStream(is);

        Board brd = (Board) ois.readObject();

        ois.close();
        is.close();

        return brd;
    }
}
