package model;

public class Comanda {

	private int idComanda;
	private int idClient;
	private int idProdus;
	private int cantitateCeruta;
	private String userEmail;

	public Comanda() {

	}

	public Comanda(int idCl, int idP, int cantitate, String userEmail) {
		this.idClient = idCl;
		this.idProdus = idP;
		this.cantitateCeruta = cantitate;
		this.setUserEmail(userEmail);
	}

	public Comanda(int idCo, int idCl, int idP, int cantitate) {
		this.idComanda = idCo;
		this.idClient = idCl;
		this.idProdus = idP;
		this.cantitateCeruta = cantitate;
	}

	public int getIdComanda() {
		return idComanda;
	}

	public void setIdComanda(int id) {
		this.idComanda = id;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int id) {
		this.idClient = id;
	}

	public int getIdProdus() {
		return idProdus;
	}

	public void setIdProdus(int id) {
		this.idProdus = id;
	}

	public int getCantitateCeruta() {
		return cantitateCeruta;
	}

	public void setCantitateCeruta(int cantitate) {
		this.cantitateCeruta = cantitate;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
