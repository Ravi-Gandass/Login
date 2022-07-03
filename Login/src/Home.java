import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends LoginForm{
    public JTextArea taName;
    public JTextArea taEmail;
    public JTextArea taPhone;
    public JPanel homePanel;
    public JLabel lName;
    public JLabel lEmail;
    public JLabel lPhone;
    public JLabel lAddress;
    private JButton BACKTOHOMEButton;

    Home(JFrame parent){
        super(parent);
        setTitle("Login");
        setContentPane(homePanel);
        setMinimumSize(new Dimension(500,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        lName.setText(user.name);
        lEmail.setText(user.email);
        lPhone.setText(user.phone);
        lAddress.setText(user.address);


        BACKTOHOMEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Front f = new Front(null);
            }
        });
        setVisible(true);
    }
    }

