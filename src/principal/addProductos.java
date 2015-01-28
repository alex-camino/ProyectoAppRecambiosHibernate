package principal;

import clases.SessionFactoryUtil;
import clases.CatPiezas;
import clases.CatEspecifica;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JLabel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class addProductos extends JFrame {

	private JPanel contentPane;
	private JComboBox comboBoxCategorias, comboBoxSubcategorias;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addProductos frame = new addProductos();
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
	public addProductos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ArrayList<CatPiezas> categorias = obtenerCategorias();
		String[] arrayCategorias = new String[categorias.size()];
		
		for(int i=0;i<categorias.size();i++){
			
			
			arrayCategorias[i]=categorias.get(i).getCatNombre();
		}
		
		comboBoxCategorias = new JComboBox(arrayCategorias);
		comboBoxCategorias.setBounds(112, 23, 112, 27);
		contentPane.add(comboBoxCategorias);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(18, 27, 61, 16);
		contentPane.add(lblCategoria);
		
		JLabel lblSubcategoria = new JLabel("SubCategoria");
		lblSubcategoria.setBounds(18, 60, 88, 16);
		contentPane.add(lblSubcategoria);
		
		comboBoxSubcategorias = new JComboBox();
		comboBoxSubcategorias.setBounds(112, 56, 112, 27);
		contentPane.add(comboBoxSubcategorias);
	}
	
	public static ArrayList<CatPiezas> obtenerCategorias(){
		
		//Creamos la sesion y el transaction para poder ejecutar las acciones a la BBDD.
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();
		Transaction trans = sesion.beginTransaction();
		
		ArrayList<CatPiezas> categorias= new ArrayList<CatPiezas>();
		
		CatPiezas nuevaCategoria = new CatPiezas();
		
		Query q = sesion.createQuery("from catPiezas");
		
		
		List<CatPiezas> lista = q.list();
		Iterator<CatPiezas> iter = lista.iterator();
		
		q.setFetchSize(20);
		
		iter=q.iterate();
		
		while(iter.hasNext()){
			
			nuevaCategoria=(CatPiezas) iter.next();
			
			categorias.add(nuevaCategoria);
		}
		
		
		return categorias;
	}
}
