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
import model.Comanda;

public class ComandaDAO {

	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO comanda (idClient,idProdus,cantitateCeruta,userEmail)"
			+ "VALUES (?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM comanda WHERE idComanda=?";
	private final static String findAllStatementString = "SELECT * FROM comanda WHERE userEmail=?";
	private final static String deleteStatementString = "DELETE FROM comanda WHERE idComanda=?";
	private final static String updateStatementString = "UPDATE comanda SET cantitateCeruta=? WHERE idComanda=?";

	public static Comanda findById(int comandaID, String userEmail) {
		Comanda comandaBack = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;

		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, comandaID);
			rs = findStatement.executeQuery();
			rs.next();
			int idclient = rs.getInt("idClient");
			int idprodus = rs.getInt("idProdus");
			int cantitate = rs.getInt("cantitateCeruta");
			comandaBack = new Comanda(idclient, idprodus, cantitate,userEmail);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ComandaDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return comandaBack;
	}

	public static ArrayList<Comanda> findAll(String userEmail) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		ArrayList<Comanda> listaComenzi = new ArrayList<Comanda>();
		try {
			selectStatement = dbConnection.prepareStatement(findAllStatementString);
			selectStatement.setString(1,userEmail);
			rs = selectStatement.executeQuery();
			
			while (rs.next()) {
				Comanda comanda = new Comanda();
				comanda.setIdComanda(rs.getInt("idComanda"));
				comanda.setIdClient(rs.getInt("idClient"));
				comanda.setIdProdus(rs.getInt("idProdus"));
				comanda.setCantitateCeruta(rs.getInt("cantitateCeruta"));
				comanda.setUserEmail(rs.getString("userEmail"));
				listaComenzi.add(comanda);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ComandaDAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		}
		return listaComenzi;
	}

	public static int insert(Comanda comanda) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, comanda.getIdClient());
			insertStatement.setInt(2, comanda.getIdProdus());
			insertStatement.setInt(3, comanda.getCantitateCeruta());
			insertStatement.setString(4, comanda.getUserEmail());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ComandaDAO:insert " + e.getMessage());
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
			LOGGER.log(Level.WARNING, "ComandaDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		return id;
	}

	public static int update(int id, int cantitate) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setInt(1, cantitate);
			updateStatement.setInt(2, id);
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ComandaDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return id;
	}
}


