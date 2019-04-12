package bll;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import dao.ClientDAO;
import dao.UserDAO;
import model.User;

public class UserBLL {
	
	
	public static String authenticate(User user) {
		
		UserDAO udao = new UserDAO();
		User toLogin = udao.authenticateDatabase(user.getUsername());
		if(toLogin==null) {
			return null;
		}
		else if(toLogin.getPassword().equals(hashPassword(user.getPassword()))) 
		{
			return toLogin.getRole();
		}
		return null;
	}
	
	public static int register(User user) {
		
		user.setPassword(hashPassword(user.getPassword()));
		return UserDAO.register(user);
		
	}
	
	private static String hashPassword(String passwordToHash) {
		String generatedPassword = null;
		try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
		return generatedPassword;
	}

	public static List findAll(String userEmail) {
		// TODO Auto-generated method stub
		return UserDAO.findAll(userEmail);
	}

	public static int deleteUser(int id) {
		// TODO Auto-generated method stub
		return UserDAO.delete(id);
		
	}

}
