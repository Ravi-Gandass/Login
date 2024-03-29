import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JDialog {
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnOK;
    private JButton btnCancel;
    private JPanel loginPanel;

    public LoginForm(JFrame parent){
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(550,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnOK.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());
                user = getAuthenticatedUser(email,password);


                if(user != null) {
                    JOptionPane.showMessageDialog(LoginForm.this, "Successfully Login");
                   dispose();
                }
                else{
                    JOptionPane.showMessageDialog(LoginForm.this, "Email or Password Invalid", "Try again", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Front f = new Front(null);
            }
        });
        setVisible(true);
    }
    public User user;
    private User getAuthenticatedUser(String email, String password) {
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/university";
        final String USERNAME = "root";
        final String PASSWORD = "Ravi@0055";

        try{
            Connection conn = DriverManager.getConnection(DB_URL,USERNAME, PASSWORD);
            //Connected to database successfully..

            Statement stmt = conn.createStatement();
            String sql = "SELECT*FROM student_info WHERE email=? AND password=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.name = resultSet.getString("name");
                user.email = resultSet.getString("email");
                user.phone = resultSet.getString("phone");
                user.address = resultSet.getString("address");
                user.password =  resultSet.getString("password");

            }
            stmt.close();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public static void main(String[] args) {
        Home h = new Home(null);
    }
}
