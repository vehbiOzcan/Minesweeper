
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginMine extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginMine frame = new LoginMine();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public LoginMine() {
        setTitle("Mayýn Tarlasý");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 300, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btn_newGame = new JButton("Yeni Oyun");
        btn_newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MayinTarlasi mayin = new MayinTarlasi();
                dispose();
            }
        });
        btn_newGame.setBounds(10, 27, 266, 51);
        contentPane.add(btn_newGame);

        JButton btn_exit = new JButton("Çýkýþ");
        btn_exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btn_exit.setBounds(10, 105, 266, 51);
        contentPane.add(btn_exit);
    }

}
