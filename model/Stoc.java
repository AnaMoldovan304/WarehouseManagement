package model;

public class Stoc {

	private int idProdus;
	private int cantitateProdus;
	private String userEmail;

	public Stoc() {

	}

	public Stoc(int cantitate) {
		this.cantitateProdus = cantitate;
	}

	public Stoc(int id, int cantitate, String userEmail) {
		this.idProdus = id;
		this.cantitateProdus = cantitate;
		this.setUserEmail(userEmail);
		
	}

	public int getIdProdus() {
		return idProdus;
	}

	public void setIdProdus(int id) {
		this.idProdus = id;
	}

	public int getCantitateProdus() {
		return cantitateProdus;
	}

	public void setCantitateProdus(int cantitate) {
		this.cantitateProdus = cantitate;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
