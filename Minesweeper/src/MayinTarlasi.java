
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MayinTarlasi implements MouseListener {

    JFrame frame;
    Btn[][] board = new Btn[10][10];
    int openButton;
    int score;
    boolean game;

    public MayinTarlasi() {
        openButton = 0;
        score = 0;
        game = true;
        frame = new JFrame("Mayýn Tarlasý");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(10, 10));
        for (int row = 0; row < board.length; row++) { // tarlaya butonlarý ekleriz ve butonlarýda boarda ekleriz
            for (int col = 0; col < board[0].length; col++) {
                Btn b = new Btn(row, col);

                b.addMouseListener(this);

                frame.add(b);
                board[row][col] = b;// butonlarý bulunmasý gereken yerlere ekliyoruz
            }
        }
        frame.addWindowListener(new WindowListener() {
            public void windowOpened(WindowEvent e) {
                System.out.println("açýldý");
            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {
                LoginMine again = new LoginMine();
                again.setVisible(true);
                System.out.println("kapandý");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowActivated(WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }

        });

        frame.setVisible(true);
        generateMine();
        updateCount();

    }

    public void generateMine() {
        int i = 0;
        while (i < 10) {
            int randRow = (int) (Math.random() * board.length);
            int randCol = (int) (Math.random() * board[0].length);
            while (board[randRow][randCol].isMine()) {// ayný yerde mayýn varsa farklý bir sayý üretiyor
                randRow = (int) (Math.random() * board.length);
                randCol = (int) (Math.random() * board[0].length);
            }
            board[randRow][randCol].setMine(true);
            i++;
        }
    }

    public void print() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col].isMine()) {
                    ImageIcon mineIcon = new ImageIcon(getClass().getResource("/miness.png"));
                    board[row][col].setIcon(mineIcon);
                } else {

                    board[row][col].setEnabled(false);
                    board[row][col].setText(board[row][col].getCount() + "");
                }

            }
        }
    }

    public void printMine() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col].isMine()) {
                    ImageIcon mineIcon = new ImageIcon(getClass().getResource("/miness.png"));
                    board[row][col].setIcon(mineIcon);
                }

            }
        }
    }

    public void updateCount() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col].isMine()) {
                    // Mayýn varsa etrafýnda bulunan yerlerin sayacýný birer artýran fonksiyona gönderir
                    counting(row, col);
                }
            }
        }

    }

    public void counting(int row, int col) {
        int value;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {

                try {
                    value = board[i][j].getCount();
                    board[i][j].setCount(++value);
                } catch (Exception e) {

                }
            }
        }
    }

    public void open(int r, int c) {//mayýnsýz bölgeleri açan recursive fonksiyon
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c].getText().length() > 0 || board[r][c].isEnabled() == false) {
            return;
        } else if (board[r][c].getCount() != 0) {
            openButton++;
            score += 10;
            board[r][c].setText(board[r][c].getCount() + "");
            board[r][c].setEnabled(false);
        } else {
            openButton++;
            score += 10;
            board[r][c].setEnabled(false);
            open(r - 1, c);
            open(r + 1, c);
            open(r, c - 1);
            open(r, c + 1);

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Btn b = (Btn) e.getComponent();

        if (e.getButton() == 1) {

            if (b.isMine()) {
                print();
                JOptionPane.showMessageDialog(frame, "Kahretsin mayýn! Oyun bitti... \nSkorun:" + score);
                print();

                frame.dispose();

            } else {

                open(b.getRow(), b.getCol());//bulunduðu butonun satýr sütunu 
                if (openButton == ((board.length * board[0].length) - 10)) {
                    print();
                    JOptionPane.showMessageDialog(frame, "Tebrikler Kazandýnýz\n Skorun: " + score);
                    frame.dispose();
                }
            }

        } else if (e.getButton() == 3) {

            if (!b.isFlag()) {

                b.setIcon(new ImageIcon(getClass().getResource("/flag.png")));
                b.setFlag(true);
            } else {
                b.setIcon(null);
                b.setFlag(false);
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
