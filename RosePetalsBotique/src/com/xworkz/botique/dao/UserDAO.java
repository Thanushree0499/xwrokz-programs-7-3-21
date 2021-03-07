package com.xworkz.botique.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.xworkz.botique.dto.UserDTO;

public interface UserDAO {
	
	public Connection getDbConnection() throws SQLException, ClassNotFoundException;
	
	public boolean insertRecord(UserDTO userDTO);
	
	public boolean checkIfUserExist(String emailStr);
}
