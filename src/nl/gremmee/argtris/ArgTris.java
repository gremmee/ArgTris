package nl.gremmee.argtris;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ArgTris extends JFrame {
    private JLabel statusBar;
    private NextPiecePanel nextPiecePanel;

    public ArgTris() {
        statusBar = new JLabel("Lines: 0 - ArgTris: 0 - Level: 0 - Score: 0");
        add(statusBar, BorderLayout.SOUTH);
        Board board = new Board(this);
        add(board);
        nextPiecePanel = new NextPiecePanel(board);
        add(nextPiecePanel, BorderLayout.NORTH);

        board.start();
        nextPiecePanel.start();

        setSize(300, 600);
        setPreferredSize(new Dimension(350, 700));
        setTitle("ArgTris :D");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        ArgTris myArgTris = new ArgTris();
        myArgTris.setLocationRelativeTo(null);
        myArgTris.setVisible(true);
        myArgTris.pack();
    }

    public JLabel getStatusBar() {
        return statusBar;
    }
}
