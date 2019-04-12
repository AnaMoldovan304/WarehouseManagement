package bll;

import model.Client;

import java.util.ArrayList;

import dao.ClientDAO;

public class ClientBLL {

	public Client findClientById(int id, String userEmail) {
		Client client = ClientDAO.findById(id, userEmail);
		return client;
	}

	public int insertClient(Client client) {
		return ClientDAO.insert(client);
	}
	//TODO: propagarea usernameului printre layere
	public ArrayList<Client> findAll(String userEmail) {
		return ClientDAO.findAll(userEmail);
	}

	public int deleteClient(int id) {
		return ClientDAO.delete(id);
	}

	public int updateClient(int id, String adresa, String telefon) {
		return ClientDAO.update(id, adresa, telefon);
	}
}
