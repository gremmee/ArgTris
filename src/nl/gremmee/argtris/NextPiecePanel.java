package nl.gremmee.argtris;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import nl.gremmee.argtris.Shape.Tetrominos;

public class NextPiecePanel extends JPanel implements ActionListener {
    private Board board;
    private Timer timer;

    public NextPiecePanel(Board aBoard) {
        this.board = aBoard;
        setSize(new Dimension(this.board.getWidth(), 100));
        setPreferredSize(new Dimension(300, 100));

        this.timer = new Timer(400, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Shape nextPiece = board.getNextPiece();
        if (nextPiece.getShape() != Tetrominos.NoShape) {
            for (int i = 0; i < 4; ++i) {
                int x = nextPiece.x(i) + 5;
                int y = nextPiece.y(i) + 1;
                Shape.drawSquare(g, x * board.squareWidth(), (y * board.squareHeight()), this.board,
                        nextPiece.getShape());
            }
        }
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
}
