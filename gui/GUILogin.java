package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bll.UserBLL;
import model.User;
import model.builder.UserBuilder;
 
public class GUILogin extends JFrame implements ActionListener{
 JLabel l1, l2, l3, registerLabel,nameLabel,passLabel;
 JTextField tf1,registerText;
 JButton btn1, btnRegister;
 JPasswordField p1, registerPass;
 JCheckBox checkbox = new JCheckBox("Admin");
 
 public GUILogin() {

  l1 = new JLabel("Login Form");
  l1.setForeground(Color.blue);
  l1.setFont(new Font("Serif", Font.BOLD, 20));
 
  registerLabel = new JLabel("Or Register");
  registerLabel.setForeground(Color.blue);
  registerLabel.setFont(new Font("Serif", Font.BOLD, 20));
 
  
  l2 = new JLabel("Username");
  l3 = new JLabel("Password");
  nameLabel = new JLabel("Username");
  passLabel = new JLabel("Password");
  
  
  tf1 = new JTextField();
  registerText = new JTextField();
  p1 = new JPasswordField();
  registerPass = new JPasswordField();
  btn1 = new JButton("Login");
  btnRegister = new JButton("Register");
  
  registerLabel.setBounds(100, 200, 400, 30);
  nameLabel.setBounds(80, 240, 200, 30);
  passLabel.setBounds(80, 280, 200, 30);
  
  l1.setBounds(100, 30, 400, 30);
  l2.setBounds(80, 70, 200, 30);
  l3.setBounds(80, 110, 200, 30);
  
  
  tf1.setBounds(300, 70, 200, 30);
  
  registerText.setBounds(300, 240, 200, 30);
  p1.setBounds(300, 110, 200, 30);
  registerPass.setBounds(300, 280, 200, 30);
  btn1.setBounds(150, 160, 100, 30);
  btn1.addActionListener(this);
  checkbox.setBounds(60,330,70,30);
  btnRegister.setBounds(150, 330, 100, 30);
  
  btnRegister.addActionListener(this);
  
  add(l1);
  add(l2);
  add(tf1);
  add(l3);
  add(p1);
  add(btn1);
  
  add(registerLabel);
  add(nameLabel);
  add(passLabel);
  add(registerText);
  add(registerPass);
  add(btnRegister);
  add(checkbox);

  setDefaultCloseOperation(EXIT_ON_CLOSE);
  setSize(600, 500);
  setLayout(null);
  setLocationRelativeTo(null);
  setVisible(true);
 }
 public void actionPerformed(ActionEvent ae)
 {
	 if(ae.getSource() == btn1) {
		 String uname = tf1.getText();
		String pass = p1.getText();
		 User user = new UserBuilder()
	                .setUsername(uname)
	                .setPassword(pass)
	                .build();

		 String role = UserBLL.authenticate(user);
		 if(role!=null)
		 {
			 //transmitem userul G
			 if(role.equals("user")) {
			 GUI gui= new GUI(uname);
			 gui.setVisible(true);
			 }
			 else 
			 {
				 AdminGUI ad = new AdminGUI(uname);
				 ad.setVisible(true);
			 }
			 
		 }
		 else
		 {
			 JOptionPane.showMessageDialog(this,"Incorrect login or password",
					 "Error",JOptionPane.ERROR_MESSAGE); 
		 }
		 tf1.setText("");
		 p1.setText("");
		 }
	 else 
		{
		 User user;
		 if(checkbox.isSelected()) {
			 user = new UserBuilder()
				 .setPassword(registerPass.getText())
				 .setUsername(registerText.getText())
				 .setRole("admin")
				 .build();
		 }
		 else {
			 user = new UserBuilder()
					 .setPassword(registerPass.getText())
					 .setUsername(registerText.getText())
					 .setRole("user")
					 .build();
		 }
		 
		 if(!user.getUsername().equals("") && !user.getPassword().equals("")){
		if(UserBLL.register(user)>0) {
			JOptionPane.showMessageDialog(this,"Register successfull",
					 "OK",JOptionPane.INFORMATION_MESSAGE); 
			}
		else {
			JOptionPane.showMessageDialog(this,"Register failed",
					 "Error",JOptionPane.ERROR_MESSAGE); 
		}
		
		
		 }
		else {
			JOptionPane.showMessageDialog(this,"Register failed",
					 "Error",JOptionPane.ERROR_MESSAGE); 
		}
			 
		 }
	 registerText.setText("");
	 registerPass.setText("");
  
  }

}