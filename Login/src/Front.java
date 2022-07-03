import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Front extends JDialog{
    private JButton REGISTERButton;
    private JButton LOGINButton;
    private JPanel homePanel;

    Front(JFrame parent){
        super(parent);
        setTitle("Welcome");
        setContentPane(homePanel);
        setMinimumSize(new Dimension(550,450));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginForm l = new Home(null);
            }
        });


        REGISTERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                register r = new register(null);
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        Front f = new Front(null);
    }
}
