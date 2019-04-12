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
import model.Stoc;

public class StocDAO {

	protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO stoc (idProdus, cantitateProdus,userEmail)" + "VALUES (?,?,?)";
	private final static String findStatementString = "SELECT * FROM stoc WHERE idProdus=?";
	private final static String findAllStatementString = "SELECT * FROM stoc WHERE userEmail=?";
	private final static String deleteStatementString = "DELETE FROM stoc WHERE idProdus=?";

	public static Stoc findById(int produsID) {
		Stoc stocBack = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;

		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, produsID);
			rs = findStatement.executeQuery();
			rs.next();
			int cantitate = rs.getInt("cantitateProdus");
			stocBack = new Stoc(cantitate);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "StocDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return stocBack;
	}

	public static ArrayList<Stoc> findAll(String userEmail) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		ArrayList<Stoc> listaStoc = new ArrayList<Stoc>();
		try {
			selectStatement = dbConnection.prepareStatement(findAllStatementString);
			selectStatement.setString(1,userEmail);
			rs = selectStatement.executeQuery();
			while (rs.next()) {
				Stoc stoc = new Stoc();
				stoc.setIdProdus(rs.getInt("idProdus"));
				stoc.setCantitateProdus(rs.getInt("cantitateProdus"));
				stoc.setUserEmail(rs.getString("userEmail"));
				listaStoc.add(stoc);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "StocDAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		}
		return listaStoc;
	}

	public static int insert(Stoc stoc) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, stoc.getIdProdus());
			insertStatement.setInt(2, stoc.getCantitateProdus());
			insertStatement.setString(3, stoc.getUserEmail());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
		
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "StocDAO:insert " + e.getMessage());
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
			LOGGER.log(Level.WARNING, "StocDAO:delete " + e.getMessage());
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
			updateStatement = dbConnection.prepareStatement("UPDATE stoc SET cantitateProdus=? WHERE idProdus=?");
			updateStatement.setInt(1, cantitate);
			updateStatement.setInt(2, id);
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "StocDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return id;
	}

}
