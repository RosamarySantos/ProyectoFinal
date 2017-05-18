package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.HelperView;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Set;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import model.User;
import model.dao.UserDAO;
public class UserGUI extends JFrame {
	private static List<User> setUsers;
	private String textoTextArea ="";
	private int contadorBoton1 = 0;
	private int contadorBotonMuchos = 0;

	
	private JPanel contentPane;
	private JLabel labelInformacion;
	private JTextArea textAreaDatos;
	private JTextField textFieldLogin;
	private JTextField textFieldPassword;
	private JTextField textFieldCode;
	private JTextField textFieldGender;
	private JButton btnBorrar;
	private JButton btnActualizar;
	private JButton btnAadir;
	private JButton buttonAvanzar1;
	private JButton buttonAvanzarVarios;
	private JButton buttonRetrocederVarios;
	private JButton buttonRetroceder1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserGUI frame = new UserGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public UserGUI() {
		UserDAO userDAO = new UserDAO();
		setUsers = userDAO.getUsers();
		inicializar();
	}
	
	public void inicializar(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 800, 500);
		setTitle("Apliación de usuarios");
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		
			
		JMenu mnArchivo = new JMenu("Archivo");
		//REFACTORIZAR EL LISTENER DEL JMENU
		mnArchivo.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
				labelInformacion.setText(
						"APLICACIÓN DE USUARIOS");
			}
			public void menuSelected(MenuEvent e) {
				labelInformacion.setText(
						"CARGA DE DATOS");
			}
		});
		if (setUsers.size() == 0)
			menuBar.add(mnArchivo);
		
		JMenuItem mntmCargarArchivo = new JMenuItem("Cargar Archivo");
		mntmCargarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setUsers = HelperView.getSetUsers();
				for (int i = 0; i < 35; i++) {
					if (i == setUsers.size())
						break;
					contadorBotonMuchos++;
					textoTextArea += setUsers.get(i)+"\n";
				}
				//añadimos los 35 usuarios al textarea
				textAreaDatos.setText(textoTextArea);
				//añadimos el primer user al formulario
				textFieldLogin.setText(setUsers.get(0).getLogin());
				textFieldPassword.setText(setUsers.get(0).getPassword());
				textFieldCode.setText(setUsers.get(0).getCode());
				textFieldGender.setText(setUsers.get(0).getGender());
				//deshabilitamos el botón cargas datos
				mntmCargarArchivo.setVisible(false);

				
			}
		});
		
		mnArchivo.add(mntmCargarArchivo);
		
		JMenu mnCrud = new JMenu("CRUD");
		mnCrud.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
				labelInformacion.setText(
						"APLICACIÓN DE USUARIOS");
			}
			public void menuSelected(MenuEvent e) {
				labelInformacion.setText(
						"ACCIONES CRUD");
			}
		});
		menuBar.add(mnCrud);
		
		JMenuItem mntmCreate = new JMenuItem("Create");
		mnCrud.add(mntmCreate);
		
		JMenuItem mntmRead = new JMenuItem("Read");
		mnCrud.add(mntmRead);
		
		JMenuItem mntmUpdate = new JMenuItem("Update");
		mnCrud.add(mntmUpdate);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mnCrud.add(mntmDelete);
		
		JMenu mnAbout = new JMenu("ABOUT");
		mnAbout.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent e) {
			}
			public void menuDeselected(MenuEvent e) {
				labelInformacion.setText(
						"APLICACIÓN DE USUARIOS");
			}
			public void menuSelected(MenuEvent e) {
				labelInformacion.setText(
						"INFORMACIÓN DE LA APLICACIÓN");
			}
		});
		
		
		
		menuBar.add(mnAbout);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HelperView.showInformation();
			}
		});
		mnAbout.add(mntmAbout);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		labelInformacion = new JLabel(
				"APLICACIÓN DE USUARIOS");
		labelInformacion.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(labelInformacion, BorderLayout.SOUTH);
		
		JPanel panelIzquierdo = new JPanel();
		textAreaDatos = new JTextArea(35, 20);
		textAreaDatos.setEditable(false);
		panelIzquierdo.add(textAreaDatos);
		JPanel panelDerecho = new JPanel();

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				panelIzquierdo, panelDerecho);
		
		JLabel lblLogin = new JLabel("Login");
		
		textFieldLogin = new JTextField();
		textFieldLogin.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		
		JLabel lblCode = new JLabel("Code");
		
		textFieldCode = new JTextField();
		textFieldCode.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender");
		
		textFieldGender = new JTextField();
		textFieldGender.setColumns(10);
		
		btnBorrar = new JButton("Borrar");
		
		btnActualizar = new JButton("Actualizar");
		
		btnAadir = new JButton("Añadir");
		
		JButton btnSalir = new JButton("Salir ");
		
		buttonAvanzar1 = new JButton(">");
		buttonAvanzar1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contadorBoton1++;
				if (contadorBoton1 == setUsers.size())
					contadorBoton1 = 0;
				
				textFieldLogin.setText(setUsers.get(contadorBoton1).getLogin());
				textFieldPassword.setText(setUsers.get(contadorBoton1).getPassword());
				textFieldCode.setText(setUsers.get(contadorBoton1).getCode());
				textFieldGender.setText(setUsers.get(contadorBoton1).getGender());
				
			}
		});
		
		buttonAvanzarVarios = new JButton(">>");
		buttonAvanzarVarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textoTextArea =""; 
				int inicio = contadorBotonMuchos;
				for (int i = inicio; i < inicio+35; i++) {
					if (i == setUsers.size()){
						contadorBotonMuchos = 0;
						break;
					}
					contadorBotonMuchos++;
					textoTextArea += setUsers.get(i)+"\n";
				}
				//añadimos los 35 usuarios al textarea
				textAreaDatos.setText(textoTextArea);
				
			}
		});
		
		buttonRetrocederVarios = new JButton("<<");
		
		buttonRetroceder1 = new JButton("<");
		GroupLayout gl_panelDerecho = new GroupLayout(panelDerecho);
		gl_panelDerecho.setHorizontalGroup(
			gl_panelDerecho.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDerecho.createSequentialGroup()
					.addGroup(gl_panelDerecho.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_panelDerecho.createSequentialGroup()
							.addGroup(gl_panelDerecho.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelDerecho.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblLogin))
								.addGroup(gl_panelDerecho.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblPassword))
								.addGroup(gl_panelDerecho.createSequentialGroup()
									.addContainerGap()
									.addGroup(gl_panelDerecho.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCode)
										.addComponent(lblGender))))
							.addGap(55)
							.addGroup(gl_panelDerecho.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textFieldGender)
								.addComponent(textFieldCode)
								.addGroup(gl_panelDerecho.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(textFieldPassword, Alignment.LEADING)
									.addComponent(textFieldLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))))
						.addGroup(gl_panelDerecho.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelDerecho.createParallelGroup(Alignment.LEADING)
								.addComponent(btnBorrar)
								.addComponent(buttonAvanzar1))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panelDerecho.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelDerecho.createSequentialGroup()
									.addComponent(buttonAvanzarVarios)
									.addPreferredGap(ComponentPlacement.RELATED, 127, Short.MAX_VALUE)
									.addComponent(buttonRetrocederVarios))
								.addGroup(gl_panelDerecho.createSequentialGroup()
									.addComponent(btnActualizar)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnAadir)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelDerecho.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnSalir)
								.addComponent(buttonRetroceder1))))
					.addContainerGap(152, Short.MAX_VALUE))
		);
		gl_panelDerecho.setVerticalGroup(
			gl_panelDerecho.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDerecho.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_panelDerecho.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLogin))
					.addGap(18)
					.addGroup(gl_panelDerecho.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addGap(18)
					.addGroup(gl_panelDerecho.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCode))
					.addGap(18)
					.addGroup(gl_panelDerecho.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldGender, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGender))
					.addGap(57)
					.addGroup(gl_panelDerecho.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBorrar)
						.addComponent(btnActualizar)
						.addComponent(btnSalir)
						.addComponent(btnAadir))
					.addGap(45)
					.addGroup(gl_panelDerecho.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonAvanzar1)
						.addComponent(buttonAvanzarVarios)
						.addComponent(buttonRetrocederVarios)
						.addComponent(buttonRetroceder1))
					.addContainerGap(103, Short.MAX_VALUE))
		);
		panelDerecho.setLayout(gl_panelDerecho);
		contentPane.add(splitPane, BorderLayout.CENTER);
	}
}
