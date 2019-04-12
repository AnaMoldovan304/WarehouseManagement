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
import model.Produs;

public class ProdusDAO {

	protected static final Logger LOGGER = Logger.getLogger(ProdusDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO produs (numeProdus,pretBucata,userEmail)" + "VALUES (?,?,?)";
	private final static String findStatementString = "SELECT * FROM produs WHERE idProdus=?";
	private final static String findAllStatementString = "SELECT * FROM produs WHERE userEmail=?";
	private final static String deleteStatementString = "DELETE FROM produs WHERE idProdus=?";
	private final static String updateStatementString = "UPDATE produs SET pretBucata=? WHERE idProdus=?";

	public static Produs findById(int produsID) {
		Produs produsBack = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;

		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setInt(1, produsID);
			rs = findStatement.executeQuery();
			rs.next();
			String nume = rs.getString("numeProdus");
			int pret = rs.getInt("pretBucata");
			produsBack = new Produs(nume, pret);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProdusDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return produsBack;
	}

	public static ArrayList<Produs> findAll(String userEmail) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		ArrayList<Produs> listaProduse = new ArrayList<Produs>();
		try {
			selectStatement = dbConnection.prepareStatement(findAllStatementString);
			selectStatement.setString(1,userEmail);
			rs = selectStatement.executeQuery();
			while (rs.next()) {
				Produs produs = new Produs();
				produs.setIdProdus(rs.getInt("idProdus"));
				produs.setNumeProdus(rs.getString("numeProdus"));
				produs.setPretBucata(rs.getInt("pretBucata"));
				produs.setUserEmail(userEmail);
				listaProduse.add(produs);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProdusDAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		}
		return listaProduse;
	}

	public static int insert(Produs produs) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, produs.getNumeProdus());
			insertStatement.setInt(2, produs.getPretBucata());
			insertStatement.setString(3, produs.getUserEmail());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProdusDAO:insert " + e.getMessage());
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
			LOGGER.log(Level.WARNING, "ProdusDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		return id;
	}

	public static int update(int id, int pret) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setInt(1, pret);
			updateStatement.setInt(2, id);
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "ProdusDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return id;
	}

}
