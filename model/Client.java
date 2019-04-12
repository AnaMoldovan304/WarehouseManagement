package model;

public class Client {

	private int idClient;
	private String numeClient;
	private String adresaClient;
	private String telefonClient;
	private String userEmail;

	public Client() {
		
	}
	
	public Client(int id, String nume, String adresa, String telefon, String userEmail) {
		this.idClient = id;
		this.numeClient = nume;
		this.adresaClient = adresa;
		this.telefonClient = telefon;
		this.userEmail = userEmail;
	}

	public Client(String nume, String adresa, String telefon, String userEmail) {
		this.numeClient = nume;
		this.adresaClient = adresa;
		this.telefonClient = telefon;
		this.userEmail = userEmail;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int id) {
		this.idClient = id;
	}

	public String getNumeClient() {
		return numeClient;
	}

	public void setNumeClient(String nume) {
		this.numeClient = nume;
	}

	public String getAdresaClient() {
		return adresaClient;
	}

	public void setAdresaClient(String adresa) {
		this.adresaClient = adresa;
	}

	public String getTelefonClient() {
		return telefonClient;
	}

	public void setTelefonClient(String telefon) {
		this.telefonClient = telefon;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
