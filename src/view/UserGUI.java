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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

public class UserGUI extends JFrame {

	private JPanel contentPane;
	private JLabel labelInformacion;
	private JTextArea textAreaDatos;

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
		inicializar();
	}
	
	public void inicializar(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 600, 500);
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
		menuBar.add(mnArchivo);
		
		JMenuItem mntmCargarArchivo = new JMenuItem("Cargar Archivo");
		
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
		textAreaDatos = new JTextArea(30, 20);
		textAreaDatos.setEditable(false);
		panelIzquierdo.add(textAreaDatos);
		JPanel panelDerecho = new JPanel();

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				panelIzquierdo, panelDerecho);
		contentPane.add(splitPane, BorderLayout.CENTER);
	}
	

}
