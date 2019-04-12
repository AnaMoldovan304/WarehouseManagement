package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Client;

public class ClientDAO {

	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	
	
	private static final String insertStatementString = "INSERT INTO client (numeClient,adresaClient,telefonClient,userEmail)"
			+ "VALUES (?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM client WHERE idClient=?";
	private final static String findAllStatementString = "SELECT * FROM client WHERE userEmail=?";
	private final static String deleteStatementString = "DELETE FROM client WHERE idClient=?";
	private final static String updateStatementString = "UPDATE client SET adresaClient=? , telefonClient=? WHERE idClient=?";
	
	
	public ClientDAO() {
		
	}
	
	public static Client findById(int clientID, String  userEmail) {
		Client clientBack = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;

		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, clientID);
			rs = findStatement.executeQuery();
			rs.next();
			String nume = rs.getString("numeClient");
			String adresa = rs.getString("adresaClient");
			String telefon = rs.getString("telefonClient");
			clientBack = new Client(nume, adresa, telefon,userEmail);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return clientBack;
	}

	public static ArrayList<Client> findAll(String userEmail) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		ArrayList<Client> listaClienti = new ArrayList<Client>();
		try {
			selectStatement = dbConnection.prepareStatement(findAllStatementString);
			selectStatement.setString(1,userEmail);
			rs = selectStatement.executeQuery();
			while (rs.next()) {
				Client client = new Client();
				client.setIdClient(rs.getInt("idClient"));
				client.setNumeClient(rs.getString("numeClient"));
				client.setAdresaClient(rs.getString("adresaClient"));
				client.setTelefonClient(rs.getString("telefonClient"));
				client.setUserEmail(rs.getString("userEmail"));
				listaClienti.add(client);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		}
		return listaClienti;
	}

	public static int insert(Client client) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, client.getNumeClient());
			insertStatement.setString(2, client.getAdresaClient());
			insertStatement.setString(3, client.getTelefonClient());
			insertStatement.setString(4, client.getUserEmail());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}

	public static int delete(int id) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		try {
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setInt(1, id);
			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		return id;
	}

	public static int update(int id, String adresa, String telefon) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setString(1, adresa);
			updateStatement.setString(2, telefon);
			updateStatement.setInt(3, id);
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ClientDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return id;
	}

}
