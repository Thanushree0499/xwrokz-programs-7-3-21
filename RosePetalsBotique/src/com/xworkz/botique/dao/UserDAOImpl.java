package com.xworkz.botique.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.xworkz.botique.dto.UserDTO;

public class UserDAOImpl implements UserDAO {

	@Override
	public Connection getDbConnection() throws SQLException, ClassNotFoundException {

		String url = "jdbc:mysql://localhost:3306/rosepetalsbotique";
		String username = "root";
		String password = "root123";
		Connection connection=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
			System.out.println(connection);
			return connection;
		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new SQLException();
	}

	@Override
	public boolean insertRecord(UserDTO userDTO) {
		Connection connection=null;
		try {
			connection = getDbConnection();
			
			String query="insert into usertable values(?,?,?,?,?) ;";
			
			PreparedStatement preparedStatement=connection.prepareStatement(query);
			
			preparedStatement.setString(1, userDTO.getFirstName());
			preparedStatement.setString(2, userDTO.getLastName());
			preparedStatement.setString(3, userDTO.getEmail());
			preparedStatement.setString(4, userDTO.getPassword());
			preparedStatement.setString(5, userDTO.getConfirmPassword());
			
			int result=preparedStatement.executeUpdate();
			if(result>=1){
				System.out.println("record inserted successfully");
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean checkIfUserExist(String newemail) {
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		
		try {
			
		    connection=getDbConnection();
			
			String query1="select COUNT(*) from usertable where exists (select * from usertable where email=?)";
            
		    preparedStatement=connection.prepareStatement(query1);
		    
		    preparedStatement.setString(1, newemail);
		   
		    ResultSet result=preparedStatement.executeQuery();
		    int rows=0;
		    while(result.next()) {
		    	rows=result.getInt(1);
		    	
		    }if(rows>=1){
		    	System.out.println("rows are"+rows);
		    	return true;
		    }else {
		    	System.out.println("no rows");
		    	return false;
		    }
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/*String firstname = userDTO.getFirstName();
			String lastname = userDTO.getLastName();
			String email = userDTO.getEmail();
			String password = userDTO.getPassword();
			String confirmPassword = userDTO.getConfirmPassword();
			
			String query = "insert into usertable values(" + firstname + " ," + lastname + "," + email + "," + password
					+ "," + confirmPassword + ");";

			Statement statement = connection.createStatement();

			int result = statement.executeUpdate(query);

			if (result >= 1) {
				return true;
			} else {
				return false;
			}*/

}
