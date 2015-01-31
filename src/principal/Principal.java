package principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;



public class Principal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame..
	 */
	public Principal() {
		

		setTitle("Oscaro Recambios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1260, 660);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		setResizable(false);
		setLocationRelativeTo(null);
		//setDefaultLookAndFeelDecorated(true);
		

		visualizar pantallaVisualizar = new visualizar(contentPane);
		pantallaVisualizar.setVisible(true);
		contentPane.add(pantallaVisualizar);

		SwingUtilities.updateComponentTreeUI(pantallaVisualizar);
	}

}
