package bll;

import model.Produs;

import java.util.ArrayList;

import dao.ProdusDAO;

public class ProdusBLL {

	public Produs findProdusById(int id) {
		Produs produs = ProdusDAO.findById(id);
		return produs;
	}

	public int insertProdus(Produs produs) {
		return ProdusDAO.insert(produs);
	}

	public ArrayList<Produs> findAll(String userEmail) {
		return ProdusDAO.findAll(userEmail);
	}

	public int deleteProdus(int id) {
		return ProdusDAO.delete(id);
	}

	public int updateProdus(int id, int pret) {
		return ProdusDAO.update(id, pret);
	}
}
