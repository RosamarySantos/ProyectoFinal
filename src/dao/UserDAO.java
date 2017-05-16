package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import model.ConnectionBD;
import model.User;

public class UserDAO implements IUserDAO {
	private static Connection connection = ConnectionBD.getConnectionBD();
	Statement statement;
	String sql;
	PreparedStatement preparedStatement;
	@Override
	public Set<User> getUsers() {
		Set<User> listUsers = new HashSet<>();
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	public static void main(String[] args) {
		UserDAO userDAO = new UserDAO();
		User u = new User("1", "2", "3", "4");
		System.out.println(userDAO.addUser(u));
		Set<User> listaUsuarios = userDAO.getUsers();
		System.out.println(listaUsuarios);
	}

}
