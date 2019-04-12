package model;

public class User {
	
	private int idUser;
	private String userEmail;
	private String password;
	private String role;
	
	public String getUsername() {
		return userEmail;
	}
	public void setUsername(String username) {
		this.userEmail = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
