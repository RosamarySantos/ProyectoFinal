package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import model.ConnectionBD;
import model.User;

public class UserDAO implements IUserDAO {
	private static Connection connection = ConnectionBD.getConnectionBD();
	Statement statement;
	String sql;
	PreparedStatement preparedStatement;
	@Override
	public List<User> getUsers() {
		List<User> listUsers = new ArrayList<>();
		// SELECT * FROM user;
		sql = "SELECT * FROM user";
		try {
			statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()){
				String login = resultset.getString("login");
				String password = resultset.getString("password");
				String code = resultset.getString("code");
				String gender = resultset.getString("gender");
				User user = new User(login, password, code, gender);
				listUsers.add(user);
			}
				
		} catch (SQLException e) {
			System.out.println("Error en la lectura de datos de la BD");
		}
		return listUsers;
	}

	@Override
	public boolean addUser(User user) {
		boolean success = false;
		// insert into user values ("uno1", "dos2", "tres3", "cuatro4");
		sql = "INSERT INTO user VALUES (?, ? , ?, ?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getCode());
			preparedStatement.setString(4, user.getGender());
			int rows = preparedStatement.executeUpdate();
			if (rows != 0)
				success = true;
		} catch (SQLException e) {
			System.out.println("Error en la insercción de datos de la BD");

		}
		return success;
	}

	@Override
	public boolean deleteUser(User user) {
		// delete from user where login = "1";
		boolean success = false;
		sql = "DELETE FROM user WHERE login = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getLogin());
			int rows = preparedStatement.executeUpdate();
			if (rows != 0)
				success = true;
		} catch (SQLException e) {
			System.out.println("Error en el borrado de datos de la BD");

		}
		return success;
	}

	@Override
	public boolean updateUser(User user) {
		boolean success = false;
		// update user set password="pp",code="ccc",gender="ggg" where login = "
		sql = "UPDATE user SET password = ?, code = ?, gender = ?"
				+ "WHERE login = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getPassword());
			preparedStatement.setString(2, user.getCode());
			preparedStatement.setString(3, user.getGender());
			preparedStatement.setString(4, user.getLogin());
			int rows = preparedStatement.executeUpdate();
			if (rows != 0)
				success = true;
		} catch (SQLException e) {
			System.out.println("Error en la actualización de datos de la BD");

		}
		
		return success;
	}
	public static void main(String[] args) {
		UserDAO userDAO = new UserDAO();
		User u = new User("1", "2", "3", "4");
		System.out.println(userDAO.addUser(u));
	//	u.setLogin("55");
	//	System.out.println(userDAO.deleteUser(u));
		User u2 = new User("1", "22", "33", "44");
		System.out.println(userDAO.updateUser(u2));
		List<User> listaUsuarios = userDAO.getUsers();
		System.out.println();
		System.out.println(listaUsuarios);
	}

}
