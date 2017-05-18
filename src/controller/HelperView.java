package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;
import model.User;
import model.dao.UserDAO;
public class HelperView {
	
	private static UserDAO userDAO = new UserDAO();
	
	public static void showInformation(){
		JOptionPane.showMessageDialog(null, 
				"Apliación desarrollada por Pepito Pérez", "INFORMACIÓN", 
				JOptionPane.INFORMATION_MESSAGE);
	}
	public static List<User> getSetUsers(){
		List<User> listUsers = new ArrayList<>();
		try {
			Scanner in = new Scanner(new File("Datos/user.csv"));
			String cabecera = in.nextLine();
			while(in.hasNextLine()){
				String[] datos = in.nextLine().split(",");
				User user = new User(datos[0].trim(), datos[1].trim(),
						datos[2].trim(),datos[3].trim());
				listUsers.add(user);
			}
		} catch (FileNotFoundException e) {
			System.out.println("No se ha encontrado el fichero");
		}
		saveValueInBD(listUsers);
		return listUsers;
	}
	private static void saveValueInBD(List<User> listUsers){
		for (User user : listUsers) {
			userDAO.addUser(user);
		}
		
		
		
		
		
		
		
	}
}
