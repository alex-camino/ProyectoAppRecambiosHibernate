package principal;
import clases.SessionFactoryUtil;
import clases.CatPiezas;
import clases.CatEspecifica;
import clases.Proveedores;
import clases.Piezas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;


public class ProductosAdd extends JPanel {

	private JPanel contentPane;
	private JComboBox comboBoxSubcategorias, comboBoxCategorias, comboBoxProveedores;
	private JTextField referencia;
	private JTextField nombrePieza;
	private JTextPane descripcionPieza;
	private JTextField cantidad;
	private JTextField precioPieza;

	
	public ProductosAdd(JPanel contentPane) {
		
		this.contentPane=contentPane;
		
		setBounds(100, 100, 1260, 660);
		setLayout(null);
				
		JLabel lblCategorias = new JLabel("Categorias");
		lblCategorias.setBounds(59, 126, 67, 16);
		add(lblCategorias);
		
		JLabel lblSubcategorias = new JLabel("SubCategorias");
		lblSubcategorias.setBounds(59, 195, 90, 16);
		add(lblSubcategorias);
		
				
		comboBoxCategorias = new JComboBox();
		obtenerCategorias();
		comboBoxCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBoxSubcategorias.removeAllItems();;
				obtenerCategoriasEspecificas(comboBoxCategorias.getSelectedIndex()+1);
			}
		});
		
		comboBoxCategorias.setBounds(212, 115, 193, 27);
		add(comboBoxCategorias);
		
		comboBoxSubcategorias = new JComboBox();
		obtenerCategoriasEspecificas(comboBoxCategorias.getSelectedIndex()+1);
		comboBoxSubcategorias.setBounds(212, 191, 193, 27);
		add(comboBoxSubcategorias);
		
		JLabel lblReferencia = new JLabel("Referencia");
		lblReferencia.setBounds(59, 303, 65, 16);
		add(lblReferencia);
		
		referencia = new JTextField();
		referencia.setBounds(212, 297, 193, 28);
		add(referencia);
		referencia.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(59, 374, 50, 16);
		add(lblNombre);
		
		nombrePieza = new JTextField();
		nombrePieza.setBounds(212, 368, 193, 28);
		add(nombrePieza);
		nombrePieza.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(59, 455, 75, 16);
		add(lblDescripcin);
		
		descripcionPieza = new JTextPane();
		descripcionPieza.setBounds(59, 483, 346, 159);
		add(descripcionPieza);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(585, 195, 56, 16);
		add(lblCantidad);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(585, 126, 38, 16);
		add(lblPrecio);
		
		cantidad = new JTextField();
		cantidad.setBounds(700, 189, 185, 28);
		add(cantidad);
		cantidad.setColumns(10);
		
		precioPieza = new JTextField();
		precioPieza.setBounds(700, 120, 185, 28);
		add(precioPieza);
		precioPieza.setColumns(10);
		
		JButton btnAadirProducto = new JButton("Añadir Producto");
		btnAadirProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				addProductoBBDD();
			}

		});
		btnAadirProducto.setBounds(585, 469, 145, 29);
		add(btnAadirProducto);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(585, 303, 62, 16);
		add(lblProveedor);
		
		comboBoxProveedores = new JComboBox();
		obtenerProveedores();
		comboBoxProveedores.setBounds(700, 299, 185, 27);
		add(comboBoxProveedores);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cargarVolver();
			}
		});
		btnVolver.setBounds(800, 469, 145, 29);
		add(btnVolver);

	}
	
	
	public void cargarVolver(){
		
		this.contentPane.removeAll();
		
		visualizar pantallaVisualizar = new visualizar(contentPane);
		pantallaVisualizar.setVisible(true);
		this.contentPane.add(pantallaVisualizar);
		SwingUtilities.updateComponentTreeUI(pantallaVisualizar);
	}
	
	public  void obtenerCategorias(){
		
		//Creamos la sesion y el transaction para poder ejecutar las acciones a la BBDD.
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();
		Transaction trans = sesion.beginTransaction();
		
				
		CatPiezas nuevaCategoria = new CatPiezas();
		
		Query q = sesion.createQuery("from CatPiezas");
		
		
		List<CatPiezas> lista = q.list();
		Iterator<CatPiezas> iter = lista.iterator();
		
		q.setFetchSize(20);
		
		iter=q.iterate();
		
		while(iter.hasNext()){
			
			nuevaCategoria=(CatPiezas) iter.next();
			
			comboBoxCategorias.addItem(nuevaCategoria.getCatNombre());
		}
		
		sesion.close();
		
	}
	
	public void obtenerCategoriasEspecificas(int categoria){
		
		//Creamos la sesion y el transaction para poder ejecutar las acciones a la BBDD.
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();
		Transaction trans = sesion.beginTransaction();
				
		CatEspecifica nuevaCategoria = new CatEspecifica();
		
		Query q = sesion.createQuery("from CatEspecifica ce where ce.catPiezas = :numCategoria").setInteger("numCategoria", categoria);
		
		
		List<CatEspecifica> lista = q.list();
		Iterator<CatEspecifica> iter = lista.iterator();
		
		q.setFetchSize(20);
		
		iter=q.iterate();
		
		while(iter.hasNext()){
			
			nuevaCategoria=(CatEspecifica) iter.next();
			
			comboBoxSubcategorias.addItem(nuevaCategoria.getCatEspNombre());
		}
		
		sesion.close();
	}
	
	public void obtenerProveedores(){
		
		//Creamos la sesion y el transaction para poder ejecutar las acciones a la BBDD.
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();
		Transaction trans = sesion.beginTransaction();
		
		Proveedores nuevoProveedor = new Proveedores();
		
		Query q = sesion.createQuery("from Proveedores");
		
		
		List<Proveedores> lista = q.list();
		Iterator<Proveedores> iter = lista.iterator();
		
		q.setFetchSize(20);
		
		iter=q.iterate();
		
		while(iter.hasNext()){
			
			nuevoProveedor=(Proveedores) iter.next();
			
			comboBoxProveedores.addItem(nuevoProveedor.getProNombre());
		}
		
		sesion.close();
	}
	
	public void addProductoBBDD() {
		
		//Creamos la sesion y el transaction para poder ejecutar las acciones a la BBDD.
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();
		Transaction trans = sesion.beginTransaction();
		
		Piezas nuevaPieza = new Piezas();
		
		Query q = sesion.createQuery("from CatEspecifica ce where ce.catEspNombre = :nomCatEspecifica");
		q.setString("nomCatEspecifica", comboBoxSubcategorias.getSelectedItem().toString());
				
		CatEspecifica nuevaSubCategoria = new CatEspecifica();
		
		List<CatEspecifica> lista = q.list();
		Iterator<CatEspecifica> iter = lista.iterator();
		
		q.setFetchSize(20);
		
		iter=q.iterate();
		
		while(iter.hasNext()){
			
			nuevaSubCategoria=(CatEspecifica) iter.next();
			
			nuevaPieza.setCatEspecifica(nuevaSubCategoria);
		}
		
		nuevaPieza.setPieReferencia(referencia.getText());
		nuevaPieza.setPieNombre(nombrePieza.getText());
		nuevaPieza.setPieDescripcion(descripcionPieza.getText());
		nuevaPieza.setPieCantidad(Integer.parseInt(cantidad.getText()));
		nuevaPieza.setPiePrecio(Float.parseFloat(precioPieza.getText()));
		Proveedores nuevoProveedor = new Proveedores();
		nuevoProveedor.setProCodigo(comboBoxProveedores.getSelectedIndex()+1);
		nuevaPieza.setProveedores(nuevoProveedor);
		
		
		sesion.save(nuevaPieza);
		trans.commit();
		sesion.close();
		
		JOptionPane.showMessageDialog(null, "PIEZA CREADO CORRECTAMENTE.", "CREANDO UNA NUEVA PIEZA", 1);
		
		
	}
	

}
