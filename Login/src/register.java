import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;

public class register extends JDialog {
    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfPhone;
    private JTextField tfAddress;
    private JPasswordField pfPassword;
    private JPasswordField pfConfirmPassword;
    private JButton btnRegister;
    private JButton btnCancel;
    private JPanel registerPanel;

    public register(JFrame parent){
        super(parent);
        setTitle("Create a new Account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(840,574));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Front f= new Front(null);
            }
        });
        setVisible(true);
    }

    public void registerUser() {
        String check = tfEmail.getText();
        String name = tfName.getText();
       String email = tfEmail.getText();
       String phone = tfPhone.getText();
       String address = tfAddress.getText();
       String password = String.valueOf(pfPassword.getPassword());
       String confirmPassword = String.valueOf(pfConfirmPassword.getPassword());

       if(name.isEmpty()|| email.isEmpty() || phone.isEmpty() || address.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
           JOptionPane.showMessageDialog(this, "Please Enter ALL Fields","Try Again", JOptionPane.ERROR_MESSAGE);
           return;
       }
       if(!password.equals(confirmPassword)){
           JOptionPane.showMessageDialog(this, "Confirm Password does not match", "Try Again", JOptionPane.ERROR_MESSAGE);
           return;
       }
       String w = checkDatabase(check);
       user = addUsertoDatabase(name, email, phone, address, password);

       try {
           if (Objects.equals(w, user.email)) {
               dispose();
               JOptionPane.showMessageDialog(this, "Email ID is Already Registered");
               Front f = new Front(null);
           }
           else if(user!=null){
               dispose();
               JOptionPane.showMessageDialog(this, "New User Successfuly Registered");
               Front f = new Front(null);
           }
           else{
               JOptionPane.showMessageDialog(this, "Failed to register New User", "Try Again", JOptionPane.ERROR_MESSAGE);
           }
       }
       catch (Exception e){
           System.out.println(e);
       }
    }
    public User user;
    public User addUsertoDatabase(String name, String email, String phone, String address, String password) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/university";
        final String USERNAME = "root";
        final String PASSWORD = "Ravi@0055";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "INSERT INTO student_info (name, email, phone, address, password)" + "VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, password);
            int addedRows = preparedStatement.executeUpdate();


            if (addedRows > 0) {
                user = new User();
                user.name = name;
                user.email = email;
                user.phone = phone;
                user.address = address;
                user.password = password;
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();

        }
        return user;

    }
    public String checkDatabase(String check){
        final String DB_URL = "jdbc:mysql://localhost:3306/university";
        final String USERNAME = "root";
        String a = null;
        final String PASSWORD = "Ravi@0055";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            Statement stmt2 = conn.createStatement();
            String sql = "SELECT*FROM student_info WHERE email=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,check);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                a = resultSet.getString("email");
            }
            stmt2.close();
            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();

        }
        return a;
    }

    public static void main(String[] args) {
        register r = new register(null);

    }
}
