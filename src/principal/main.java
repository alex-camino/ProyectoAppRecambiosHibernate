package principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
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
	public main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAadirElementos = new JButton("Añadir Productos");
		btnAadirElementos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				addProducts produc = null;
				
				produc=new addProducts();
				produc.setBounds(100, 100, 525, 333);
				setContentPane(produc);
				
				setVisible(true);
			}
		});
		btnAadirElementos.setBounds(0, 0, 140, 29);
		contentPane.add(btnAadirElementos);
		
		JButton btnActualizarElementos = new JButton("Actualizar Productos");
		btnActualizarElementos.setBounds(129, 0, 157, 29);
		contentPane.add(btnActualizarElementos);
		
		JButton btnNewButton = new JButton("Consultar Productos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnNewButton.setBounds(273, 0, 117, 29);
		contentPane.add(btnNewButton);
	}
}
