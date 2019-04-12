package model;

public class Produs {

	public Produs() {
		
	}
	
	private int idProdus;
	private String numeProdus;
	private int pretBucata;
	private String userEmail;

	public Produs(String nume, int pret) {
		this.numeProdus = nume;
		this.pretBucata = pret;
	}

	public Produs(String nume, int pret, String userEmail) {
		this.numeProdus = nume;
		this.pretBucata = pret;
		this.setUserEmail(userEmail);
	}

	public int getIdProdus() {
		return idProdus;
	}

	public void setIdProdus(int id) {
		this.idProdus = id;
	}

	public String getNumeProdus() {
		return numeProdus;
	}

	public void setNumeProdus(String nume) {
		this.numeProdus = nume;
	}

	public int getPretBucata() {
		return pretBucata;
	}

	public void setPretBucata(int pret) {
		this.pretBucata = pret;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
