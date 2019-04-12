package bll;

import model.Comanda;

import java.util.ArrayList;

import dao.ComandaDAO;

public class ComandaBLL {

	public Comanda findComandaById(int id, String userEmail) {
		Comanda comanda = ComandaDAO.findById(id, userEmail);
		return comanda;
	}

	public int insertComanda(Comanda comanda) {
		return ComandaDAO.insert(comanda);
	}

	public ArrayList<Comanda> findAll(String userEmail) {
		return ComandaDAO.findAll(userEmail);
	}

	public int deleteComanda(int id) {
		return ComandaDAO.delete(id);
	}

	public int updateComanda(int id, int cantitate) {
		return ComandaDAO.update(id, cantitate);
	}
}
