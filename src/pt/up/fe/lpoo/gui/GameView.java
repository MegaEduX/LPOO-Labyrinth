/**
 * Labyrinth
 *
 * Created by Eduardo Almeida and João Almeida.
 */

package pt.up.fe.lpoo.gui;

import pt.up.fe.lpoo.logic.Board;
import pt.up.fe.lpoo.logic.BoardGenerator;
import pt.up.fe.lpoo.logic.Coordinate;
import pt.up.fe.lpoo.logic.piece.Piece;
import pt.up.fe.lpoo.logic.piece.itemizable.Dragon;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

public class GameView {
    private class RestartListener implements ActionListener {
        RestartListener() {

        }

        public void actionPerformed(ActionEvent arg0) {

        }
    }

    static private JFrame frame;

    public static void main(String[] args) {
        Board brd;

        try {
            brd = new Board();

            BoardGenerator gen = new BoardGenerator(10, 10, 1);

            Vector<Piece> ooBoard = gen.generateBoard();

            for (Piece pc : ooBoard)
                pc.setBoard(brd);

            brd.setBoardPieces(ooBoard);
            brd.setWidth(10);
            brd.setHeight(10);

            Dragon drag = (Dragon) brd.getPiecesWithType(Board.Type.DRAGON).get(0);

            drag.setBehavior(Dragon.Behavior.NO_SLEEP);

            frame = new JFrame("Labyrinth Game View");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(500, 600));
            frame.getContentPane().setLayout(new BorderLayout());

            final GamePanel gp = new GamePanel(brd, new Coordinate(500, 500));

            frame.getContentPane().add(gp, BorderLayout.CENTER);

            JPanel newPanel = new JPanel(new FlowLayout());

            JButton restartGameButton = new JButton("Restart Game");
            restartGameButton.setPreferredSize(new Dimension(200, 35));
            restartGameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        Board newBoard = new Board();

                        BoardGenerator gen = new BoardGenerator(10, 10, 1);

                        Vector<Piece> ooBoard = gen.getDefaultBoard();

                        for (Piece pc : ooBoard)
                            pc.setBoard(newBoard);

                        newBoard.setBoardPieces(ooBoard);
                        newBoard.setWidth(10);
                        newBoard.setHeight(10);

                        Dragon drag = (Dragon) newBoard.getPiecesWithType(Board.Type.DRAGON).get(0);

                        drag.setBehavior(Dragon.Behavior.STOP);

                        gp.restartGame(newBoard);
                    } catch (Exception exc) {

                    }

                }
            });

            restartGameButton.setFocusable(false);

            newPanel.add(restartGameButton);

            JButton finishButton = new JButton("Exit Game");
            finishButton.setPreferredSize(new Dimension(200, 35));
            finishButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //  System.exit(0);
                    labyrinthConfigurationDialog();
                }
            });

            finishButton.setFocusable(false);

            newPanel.add(finishButton);

            frame.getContentPane().add(newPanel, BorderLayout.SOUTH);

            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {

        }
    }

    public static void labyrinthConfigurationDialog() {
        JTextField xField = new JTextField(5);
        JTextField yField = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Board X:"));
        myPanel.add(xField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Board Y:"));
        myPanel.add(yField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Labyrinth Configuration", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("Labyrinth X: " + xField.getText());
            System.out.println("Labyrinth Y: " + yField.getText());
        }

        JFrame frame = new JFrame("Options");

        frame.setPreferredSize(new Dimension(100, 300));
        frame.getContentPane().setLayout(new BorderLayout());

        frame.getContentPane().add(myPanel);

        JDialog dialog = new JDialog(frame, "Options");

        dialog.setVisible(true);
    }
}
