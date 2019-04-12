package bll;

import model.Stoc;

import java.util.ArrayList;

import dao.StocDAO;

public class StocBLL {

	public Stoc findStocById(int id) {
		Stoc stoc = StocDAO.findById(id);
		return stoc;
	}

	public int insertStoc(Stoc stoc) {
		return StocDAO.insert(stoc);
	}

	public ArrayList<Stoc> findAll(String userEmail) {
		return StocDAO.findAll(userEmail);
	}

	public int deleteStoc(int id) {
		return StocDAO.delete(id);
	}

	public int updateStoc(int id, int cantitate) {
		return StocDAO.update(id, cantitate);
	}
}
