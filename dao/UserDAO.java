package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import connection.ConnectionFactory;
import model.Client;
import model.Stoc;
import model.User;

public class UserDAO {
	
	public User authenticateDatabase(String username) 
	{
	
			User user = null;

			Connection dbConnection = ConnectionFactory.getConnection();
			PreparedStatement findStatement = null;
			ResultSet rs = null;

			try {
				findStatement = dbConnection.prepareStatement("SELECT * FROM user WHERE userEmail=?");
				findStatement.setString(1, username);
				rs = findStatement.executeQuery();
				rs.next();
				String pass = rs.getString("password");
				String role = rs.getString("role");
				user = new User();
				user.setPassword(pass);
				user.setRole(role);
				
			} catch (SQLException e) {
			
			} finally {
				ConnectionFactory.close(rs);
				ConnectionFactory.close(findStatement);
				ConnectionFactory.close(dbConnection);
			}
			return user;
		}
	
	public static int register(User user) 
	{
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement("INSERT INTO user (userEmail,password,role) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, user.getUsername());
			insertStatement.setString(2, user.getPassword());
			insertStatement.setString(3, user.getRole());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			
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
			deleteStatement = dbConnection.prepareStatement("Delete From user Where idUser=?");
			deleteStatement.setInt(1, id);
			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		return id;
	}

	public static List findAll(String userEmail) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		ArrayList<User> listaUseri = new ArrayList<User>();
		try {
			selectStatement = dbConnection.prepareStatement("SELECT * FROM user WHERE role=?");
			selectStatement.setString(1,"user");
			rs = selectStatement.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setIdUser(rs.getInt("idUser"));
				user.setUsername(rs.getString("userEmail"));
				listaUseri.add(user);
			}
		} catch (SQLException e) {
		} finally {
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		}
		return listaUseri;

	}
}
