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
import pt.up.fe.lpoo.logic.piece.Wall;
import pt.up.fe.lpoo.logic.piece.itemizable.Blank;
import pt.up.fe.lpoo.logic.piece.itemizable.Dragon;
import pt.up.fe.lpoo.logic.piece.itemizable.Eagle;
import pt.up.fe.lpoo.logic.piece.itemizable.Hero;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.*;

public class LabyrinthBuilder extends JPanel implements MouseListener {
    private Board _board;

    public Coordinate _window = new Coordinate(500, 500);

    public LabyrinthBuilder() {
        try {
            BoardGenerator bg = new BoardGenerator(10, 10, 0);

            Vector<Piece> ooBoard = bg.generateBoard();

            for (Piece pc : ooBoard)
                pc.setBoard(_board);

            _board.setBoardPieces(ooBoard);
            _board.setWidth(10);
            _board.setHeight(10);
        } catch (Exception exc) {

        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int sepX = _window.x / _board.getWidth();
        int sepY = _window.y / _board.getHeight();

        for (int i = 1; i <= _board.getWidth(); i++) {
            g.drawLine(sepX * i, 0, sepX * i, _window.y);
            g.drawLine(0, sepY * i, _window.x, sepY * i);
        }

        File img = new File("HootHoot.png");

        try {
            BufferedImage bimg = ImageIO.read(img);

            g.drawImage(bimg, 0, 0, sepX, sepY, null);
        } catch (Exception e) {

        }

        for (int i = 0; i < _board.getHeight(); i++) {
            for (int j = 0; j < _board.getWidth(); j++) {
                Piece pc;

                try {
                    pc = _board.getPiece(new Coordinate(j, i));
                } catch (Exception exc) {
                    System.out.print(" ");

                    continue;
                }

                if (pc instanceof Wall) {
                    File wood = new File("wooden_bg.jpg");

                    try {
                        BufferedImage woodbg = ImageIO.read(wood);

                        g.drawImage(woodbg, 0 + sepX * j, 0 + sepY * i, sepX, sepY, null);
                    } catch (Exception e) {

                    }
                } else {
                    File gr = new File("grass.png");

                    try {
                        BufferedImage gri = ImageIO.read(gr);

                        g.drawImage(gri, 0 + sepX * j, 0 + sepY * i, sepX, sepY, null);
                    } catch (Exception e) {

                    }

                    if (pc instanceof Hero) {
                        File hero = new File("teemo.png");

                        try {
                            BufferedImage heroi = ImageIO.read(hero);

                            g.drawImage(heroi, 0 + sepX * j, 0 + sepY * i, sepX, sepY, null);
                        } catch (Exception e) {

                        }
                    } else if (pc instanceof Blank && ((Blank) pc).getHasItem()) {
                        File sw = new File("sword.png");

                        try {
                            BufferedImage esp = ImageIO.read(sw);

                            g.drawImage(esp, 0 + sepX * j, 0 + sepY * i, sepX, sepY, null);
                        } catch (Exception e) {

                        }
                    } else if (pc instanceof Eagle)
                        System.out.print(((Eagle) pc).isFlying() ? "X" : "E");
                    else if (pc instanceof Dragon) {
                        File dragon = new File(((Dragon) pc).getIsSleeping() ? "gyarados_zzz.png" : "gyarados.png");

                        try {
                            BufferedImage drg = ImageIO.read(dragon);

                            g.drawImage(drg, 0 + sepX * j, 0 + sepY * i, sepX, sepY, null);
                        } catch (Exception e) {

                        }
                    } else if (pc instanceof Blank && ((Blank) pc).getIsExit()) {
                        File exit = new File("door.png");

                        try {
                            BufferedImage exi = ImageIO.read(exit);

                            g.drawImage(exi, 0 + sepX * j, 0 + sepY * i, sepX, sepY, null);
                        } catch (Exception e) {

                        }
                    } else {

                    }
                }
            }
        }
    }

    //
    //  Mouse Implementation
    //

    public void mousePressed(MouseEvent e) {
        Point clickPoint = e.getPoint();

        int xUnit = _board.getWidth() / _window.x;
        int yUnit = _board.getHeight() / _window.y;

        int xPoint = (int) Math.floor(clickPoint.x / xUnit);
        int yPoint = (int) Math.floor(clickPoint.y / yUnit);

        System.out.println("Pressed (" + xPoint + ", " + yPoint + ").");
    }
}
