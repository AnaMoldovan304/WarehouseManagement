package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.UnsupportedEncodingException;

import javax.swing.*;
import javax.swing.table.TableModel;

import bll.*;
import model.*;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private int nrBill = 0;
	PrintWriter tiparire;
	private TableModel tabelModel;

	private ClientBLL clientBLL = new ClientBLL();
	private ComandaBLL comandaBLL = new ComandaBLL();
	private ProdusBLL produsBLL = new ProdusBLL();
	private StocBLL stocBLL = new StocBLL();

	private JFrame frameClient = new JFrame();
	private JFrame frameProdus = new JFrame();
	private JFrame frameComanda = new JFrame();

	private JButton client = new JButton("Clienti");
	private JButton produs = new JButton("Produse");
	private JButton comanda = new JButton("Comenzi");
	private JPanel principal = new JPanel();

	private JTable tabelClienti, tabelProduse, tabelComenzi;
	private JPanel tabelClienti1 = new JPanel();
	private JPanel tabelClienti2 = new JPanel();
	private JPanel tabelClienti3 = new JPanel();
	private JPanel tabelProduse1 = new JPanel();
	private JPanel tabelProduse3 = new JPanel();
	private JPanel tabelProduse2 = new JPanel();
	private JPanel tabelComenzi1 = new JPanel();
	private JPanel tabelComenzi2 = new JPanel();
	private JPanel tabelComenzi3 = new JPanel();

	private JButton addClient = new JButton("Adaugare");
	private JButton updateClient = new JButton("Modificare");
	private JButton deleteClient = new JButton("Stergere");
	private JButton addProdus = new JButton("Adaugare");
	private JButton updateProdus = new JButton("Modificare");
	private JButton deleteProdus = new JButton("Stergere");
	private JButton addComanda = new JButton("Adaugare");
	private JButton updateComanda = new JButton("Modificare");
	private JButton deleteComanda = new JButton("Stergere");

	private JLabel idClient = new JLabel("IdClient:");
	private JTextField idClientt = new JTextField("", 5);
	private JLabel numeClient = new JLabel("NumeClient:");
	private JTextField numeClientt = new JTextField("", 5);
	private JLabel adresaClient = new JLabel("AdresaClient:");
	private JTextField adresaClientt = new JTextField("", 5);
	private JLabel telefonClient = new JLabel("TelefonClient:");
	private JTextField telefonClientt = new JTextField("", 5);

	private JLabel idProdus = new JLabel("IdProdus:");
	private JTextField idProduss = new JTextField("", 5);
	private JLabel numeProdus = new JLabel("NumeProdus:");
	private JTextField numeProduss = new JTextField("", 5);
	private JLabel pretBucata = new JLabel("PretBucata:");
	private JTextField pretBucataa = new JTextField("", 5);
	private JLabel cantitateProdus = new JLabel("StocProdus:");
	private JTextField cantitateProduss = new JTextField("", 5);

	private JLabel idProdusC = new JLabel("IdProdus:");
	private JTextField idProdusCC = new JTextField("", 5);
	private JLabel idComanda = new JLabel("IdComanda:");
	private JTextField idComandaa = new JTextField("", 5);
	private JLabel idClientC = new JLabel("IdClient:");
	private JTextField idClientCC = new JTextField("", 5);
	private JLabel cantitateCeruta = new JLabel("CantitateCeruta:");
	private JTextField cantitateCerutaa = new JTextField("", 5);
	private String userEmail;
	
	
	public GUI(String userEmail) {

		this.userEmail=userEmail;
		formareMeniu();
		principal.add(client);
		principal.add(produs);
		principal.add(comanda);
		this.add(principal);

		client.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				frameClient.setSize(600, 550);
				frameClient.setTitle("Clienti");
				frameClient.setVisible(true);
				frameClient.setResizable(false);
				frameClient.setLayout(new FlowLayout());
				tabelClienti3.add(idClient);
				tabelClienti3.add(idClientt);
				tabelClienti3.add(numeClient);
				tabelClienti3.add(numeClientt);
				tabelClienti3.add(adresaClient);
				tabelClienti3.add(adresaClientt);
				tabelClienti3.add(telefonClient);
				tabelClienti3.add(telefonClientt);
				frameClient.add(tabelClienti3);
				tabelClienti2.add(addClient);
				tabelClienti2.add(updateClient);
				tabelClienti2.add(deleteClient);
				frameClient.add(tabelClienti2);

				tabelModel = TableModelCreator.createTableModel(Client.class, clientBLL.findAll(userEmail));
				tabelClienti = new JTable(tabelModel);
				tabelClienti1.add(new JScrollPane(tabelClienti));
				frameClient.add(tabelClienti1);
				revalidate();

				addClient.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						String nume = numeClientt.getText();
						String adresa = adresaClientt.getText();
						String telefon = telefonClientt.getText();
						Client client1 = new Client(nume, adresa, telefon,userEmail);
						clientBLL.insertClient(client1);

						numeClientt.setText("");
						adresaClientt.setText("");
						telefonClientt.setText("");
						idClientt.setText("");

						frameClient.remove(tabelClienti1);
						tabelClienti1 = new JPanel();
						tabelModel = TableModelCreator.createTableModel(Client.class, clientBLL.findAll(userEmail));
						tabelClienti = new JTable(tabelModel);
						tabelClienti1.add(new JScrollPane(tabelClienti));
						frameClient.add(tabelClienti1);
						frameClient.revalidate();
					}

				});

				updateClient.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						int id = Integer.parseInt(idClientt.getText());
						String adresa = adresaClientt.getText();
						String telefon = telefonClientt.getText();
						clientBLL.updateClient(id, adresa, telefon);

						numeClientt.setText("");
						adresaClientt.setText("");
						telefonClientt.setText("");
						idClientt.setText("");

						frameClient.remove(tabelClienti1);
						tabelClienti1 = new JPanel();
						tabelModel = TableModelCreator.createTableModel(Client.class, clientBLL.findAll(userEmail));
						tabelClienti = new JTable(tabelModel);
						tabelClienti1.add(new JScrollPane(tabelClienti));
						frameClient.add(tabelClienti1);
						frameClient.revalidate();
					}

				});

				deleteClient.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						int id = Integer.parseInt(idClientt.getText());
						clientBLL.deleteClient(id);

						numeClientt.setText("");
						adresaClientt.setText("");
						telefonClientt.setText("");
						idClientt.setText("");

						frameClient.remove(tabelClienti1);
						tabelClienti1 = new JPanel();
						tabelModel = TableModelCreator.createTableModel(Client.class, clientBLL.findAll(userEmail));
						tabelClienti = new JTable(tabelModel);
						tabelClienti1.add(new JScrollPane(tabelClienti));
						frameClient.add(tabelClienti1);
						frameClient.revalidate();
					}

				});

			}

		});

		produs.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frameProdus.setSize(600, 550);
				frameProdus.setTitle("Produse");
				frameProdus.setVisible(true);
				frameProdus.setResizable(false);
				frameProdus.setLayout(new FlowLayout());
				tabelProduse3.add(idProdus);
				tabelProduse3.add(idProduss);
				tabelProduse3.add(numeProdus);
				tabelProduse3.add(numeProduss);
				tabelProduse3.add(pretBucata);
				tabelProduse3.add(pretBucataa);
				tabelProduse3.add(cantitateProdus);
				tabelProduse3.add(cantitateProduss);
				frameProdus.add(tabelProduse3);
				tabelProduse2.add(addProdus);
				tabelProduse2.add(updateProdus);
				tabelProduse2.add(deleteProdus);
				frameProdus.add(tabelProduse2);

				tabelModel = TableModelCreator.createTableModel(Produs.class, produsBLL.findAll(userEmail));
				tabelProduse = new JTable(tabelModel);
				tabelProduse1.add(new JScrollPane(tabelProduse));
				frameProdus.add(tabelProduse1);
				revalidate();

				addProdus.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						String nume = numeProduss.getText();
						int pret = Integer.parseInt(pretBucataa.getText());
						int cantitate = Integer.parseInt(cantitateProduss.getText());
						Produs produs = new Produs(nume, pret,userEmail);
						
						int idIntrodus = produsBLL.insertProdus(produs);
						Stoc stoc = new Stoc(idIntrodus,cantitate,userEmail);
						stocBLL.insertStoc(stoc);

						numeProduss.setText("");
						pretBucataa.setText("");
						cantitateProduss.setText("");
						idProduss.setText("");

						frameProdus.remove(tabelProduse1);
						tabelProduse1 = new JPanel();
						tabelModel = TableModelCreator.createTableModel(Produs.class, produsBLL.findAll(userEmail));
						tabelProduse = new JTable(tabelModel);
						tabelProduse1.add(new JScrollPane(tabelProduse));
						frameProdus.add(tabelProduse1);
						frameProdus.revalidate();
					}

				});

				updateProdus.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						int id = Integer.parseInt(idProduss.getText());
						int pret = Integer.parseInt(pretBucataa.getText());
						int stoc = Integer.parseInt(cantitateProduss.getText());
						produsBLL.updateProdus(id, pret);
						stocBLL.updateStoc(id, stoc);

						numeProduss.setText("");
						pretBucataa.setText("");
						cantitateProduss.setText("");
						idProduss.setText("");

						frameProdus.remove(tabelProduse1);
						tabelProduse1 = new JPanel();
						tabelModel = TableModelCreator.createTableModel(Produs.class, produsBLL.findAll(userEmail));
						tabelProduse = new JTable(tabelModel);
						tabelProduse1.add(new JScrollPane(tabelProduse));
						frameProdus.add(tabelProduse1);
						frameProdus.revalidate();
					}

				});

				deleteProdus.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						int id = Integer.parseInt(idProduss.getText());
						produsBLL.deleteProdus(id);

						numeProduss.setText("");
						pretBucataa.setText("");
						cantitateProduss.setText("");
						idProduss.setText("");

						frameProdus.remove(tabelProduse1);
						tabelProduse1 = new JPanel();
						tabelModel = TableModelCreator.createTableModel(Produs.class, produsBLL.findAll(userEmail));
						tabelProduse = new JTable(tabelModel);
						tabelProduse1.add(new JScrollPane(tabelProduse));
						frameProdus.add(tabelProduse1);
						frameProdus.revalidate();
					}

				});

			}

		});

		comanda.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frameComanda.setSize(600, 550);
				frameComanda.setTitle("Comenzi");
				frameComanda.setVisible(true);
				frameComanda.setResizable(false);
				frameComanda.setLayout(new FlowLayout());
				tabelComenzi3.add(idComanda);
				tabelComenzi3.add(idComandaa);
				tabelComenzi3.add(idClientC);
				tabelComenzi3.add(idClientCC);
				tabelComenzi3.add(idProdusC);
				tabelComenzi3.add(idProdusCC);
				tabelComenzi3.add(cantitateCeruta);
				tabelComenzi3.add(cantitateCerutaa);
				frameComanda.add(tabelComenzi3);
				tabelComenzi2.add(addComanda);
				tabelComenzi2.add(updateComanda);
				tabelComenzi2.add(deleteComanda);
				frameComanda.add(tabelComenzi2);

				tabelModel = TableModelCreator.createTableModel(Comanda.class, comandaBLL.findAll(userEmail));
				tabelComenzi = new JTable(tabelModel);
				tabelComenzi1.add(new JScrollPane(tabelComenzi));
				frameComanda.add(tabelComenzi1);
				revalidate();

				addComanda.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						int idclient = Integer.parseInt(idClientCC.getText());
						int idprodus = Integer.parseInt(idProdusCC.getText());
						int cantitate = Integer.parseInt(cantitateCerutaa.getText());
						String useremail= userEmail;
						Comanda comanda = new Comanda(idclient, idprodus, cantitate, useremail);
						String numeFisier = "Factura Nr." + nrBill + ".txt";

						idClientCC.setText("");
						idProdusCC.setText("");
						cantitateCerutaa.setText("");
						idComandaa.setText("");

						Stoc stoc = stocBLL.findStocById(idprodus);

						if (stoc.getCantitateProdus() - cantitate < 0) {
							JOptionPane.showMessageDialog(null, "UNDER STOCK", "UNDER STOCK",
									JOptionPane.WARNING_MESSAGE);
						} else {

							try {
								tiparire = new PrintWriter(numeFisier, "UTF-8");
							} catch (FileNotFoundException | UnsupportedEncodingException e1) {
								e1.printStackTrace();
							}
							comandaBLL.insertComanda(comanda);
							tiparire.print("Factura Nr." + nrBill + ": Clientul " + idprodus + " a comandat Produsul "
									+ idprodus + " Cantitatea Ceruta fiind " + cantitate);

							nrBill++;

							stocBLL.updateStoc(idprodus, stoc.getCantitateProdus() - cantitate);

							frameComanda.remove(tabelComenzi1);
							tabelComenzi1 = new JPanel();
							tabelModel = TableModelCreator.createTableModel(Comanda.class, comandaBLL.findAll(userEmail));
							tabelComenzi = new JTable(tabelModel);
							tabelComenzi1.add(new JScrollPane(tabelComenzi));
							frameComanda.add(tabelComenzi1);
							frameComanda.revalidate();

							tiparire.close();
						}

					}

				});

				updateComanda.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						int id = Integer.parseInt(idComandaa.getText());
						int cantitate = Integer.parseInt(cantitateCerutaa.getText());
						stocBLL.updateStoc(id, cantitate);

						idClientCC.setText("");
						idProdusCC.setText("");
						cantitateCerutaa.setText("");
						idComandaa.setText("");

						frameComanda.remove(tabelComenzi1);
						tabelComenzi1 = new JPanel();
						tabelModel = TableModelCreator.createTableModel(Comanda.class, comandaBLL.findAll(userEmail));
						tabelComenzi = new JTable(tabelModel);
						tabelComenzi1.add(new JScrollPane(tabelComenzi));
						frameComanda.add(tabelComenzi1);
						frameComanda.revalidate();
					}

				});

				deleteComanda.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						int id = Integer.parseInt(idComandaa.getText());
						comandaBLL.deleteComanda(id);

						idClientCC.setText("");
						idProdusCC.setText("");
						cantitateCerutaa.setText("");
						idComandaa.setText("");

						frameComanda.remove(tabelComenzi1);
						tabelComenzi1 = new JPanel();
						tabelModel = TableModelCreator.createTableModel(Comanda.class, comandaBLL.findAll(userEmail));
						tabelComenzi = new JTable(tabelModel);
						tabelComenzi1.add(new JScrollPane(tabelComenzi));
						frameComanda.add(tabelComenzi1);
						frameComanda.revalidate();
					}

				});

			}

		});

	}

	public void formareMeniu() {
		this.setSize(300, 75);
		this.setTitle("Baza de date");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

}
