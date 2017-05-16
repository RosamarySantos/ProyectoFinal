package dao;

import java.util.Set;

import model.User;

public interface IUserDAO {
	//Set no permite añadir el mismo objeto dos veces
	//no puede haber dos usuarios con el mismo login
	Set<User> getUsers();
	boolean addUser(User user);
	boolean deleteUser(User user);
	boolean updateUser(User user);
}
