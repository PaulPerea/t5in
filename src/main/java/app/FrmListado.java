package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class FrmListado extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;

	static EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("jpa_sesion04");
	// permite que las entidades trabajen con la unidad..
	static EntityManager em = fabrica.createEntityManager();
	private JScrollPane scrollPane;
	private JTextArea textSalida;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmListado frame = new FrmListado();
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
	public FrmListado() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buscar();
			}
		});
		btnNewButton.setBounds(324, 29, 89, 23);
		contentPane.add(btnNewButton);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(122, 30, 161, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblNewLabel = new JLabel("Usuario :");
		lblNewLabel.setBounds(10, 33, 102, 14);
		contentPane.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 78, 464, 236);
		contentPane.add(scrollPane);
		
		textSalida = new JTextArea();
		scrollPane.setViewportView(textSalida);
		
	}

	void registrar() {
		/*
		 * // leer los campos String usuario = txtUsuario.getText(); String clave =
		 * txtClave.getText(); // validacion // select * from xxx // 1. establecer
		 * conexion -> que unidad de persistencia usarás EntityManagerFactory fabrica =
		 * Persistence.createEntityManagerFactory("jpa_sesion04"); // permite que las
		 * entidades trabajen con la unidad.. EntityManager em =
		 * fabrica.createEntityManager();
		 * 
		 * try { Usuario u = em.
		 * createQuery("select u from Usuario u where u.correo = :xmail and u.clave = :xcla"
		 * , Usuario.class) .setParameter("xmail", usuario).setParameter("xcla",
		 * clave).getSingleResult(); System.out.println(u);
		 * JOptionPane.showMessageDialog(null, "Bienvenido: " + u.getNom_usua()); }
		 * catch (Exception e) { JOptionPane.showMessageDialog(null, "Error: " + e); }
		 */
	}

	void buscar() {// leer los campos
		int tipo = Integer.parseInt(txtUsuario.getText());
		// validacion
		// select * from xxx

		/*
		 * List<Usuario> lstUsuario =
		 * em.createQuery("select u from Usuario u where u.idtipo = :xtipo",
		 * Usuario.class) .setParameter("xtipo", tipo).getSingleResult();
		 */

		List<Usuario> lstUsuario = em.createQuery("select u from Usuario u where u.idtipo = :xtipo", Usuario.class)
				.setParameter("xmail", tipo).getResultList();
		for ( Usuario p : lstUsuario) {
			imprimir("codigo: " + p.getCod_usua());
			imprimir("nombre: " + p.getNom_usua());
			imprimir("apellido: " + p.getApe_usua());
			imprimir("Correo: " + p.getCorreo());
		}
		
	}



	void imprimir(String s) {
		textSalida.append(s + "\n");
	}
}
