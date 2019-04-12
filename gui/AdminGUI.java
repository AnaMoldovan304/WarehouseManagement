package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

import bll.UserBLL;
import model.Comanda;
import model.Stoc;
import model.User;

public class AdminGUI extends JFrame implements ActionListener {

	
	
	JLabel welcome;
	JButton userBtn;
	JFrame frameUser;
	JTable tabelUseri;
	JPanel tabelUseri1=new JPanel(),tabelUseri2=new JPanel(),tabelUseri3=new JPanel();
	
	private String userEmail;
	private TableModel tabelModel;
	private JButton deleteUser=new JButton("Delete");
	private JLabel idUser=new JLabel("idUser:");
	private JTextField idUserText=new JTextField();
	
	
	public AdminGUI(String uname) {
		userEmail = uname; 
		welcome = new JLabel("Welcome Mr. Administrator: "+ userEmail);
		userBtn = new JButton("Manage Users");
		setLayout(new FlowLayout());
		add(welcome);
		add(userBtn);
		userBtn.addActionListener(this);
		idUserText.setPreferredSize(new Dimension(40,20));
		frameUser = new JFrame();
		
		
		this.setSize(300, 100);
		this.setTitle("Admin Page");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		frameUser.setSize(600, 550);
		frameUser.setTitle("Useri");
		frameUser.setVisible(true);
		frameUser.setResizable(false);
		frameUser.setLayout(new FlowLayout());
		tabelUseri3.add(idUser);
		tabelUseri3.add(idUserText);
		frameUser.add(tabelUseri3);
		tabelUseri2.add(deleteUser);
		frameUser.add(tabelUseri2);

		tabelModel = TableModelCreator.createTableModel(User.class, UserBLL.findAll(userEmail));
		tabelUseri = new JTable(tabelModel);
		tabelUseri1.add(new JScrollPane(tabelUseri));
		frameUser.add(tabelUseri1);
		
		revalidate();


		deleteUser.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(idUserText.getText());
				UserBLL.deleteUser(id);
				idUserText.setText("");

				frameUser.remove(tabelUseri1);
				tabelUseri1 = new JPanel();
				tabelModel = TableModelCreator.createTableModel(User.class, UserBLL.findAll(userEmail));
				tabelUseri = new JTable(tabelModel);
				tabelUseri1.add(new JScrollPane(tabelUseri));
				frameUser.add(tabelUseri1);
				frameUser.revalidate();
			}

		});

	

}

		
	}


