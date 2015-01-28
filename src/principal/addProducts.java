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

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;


public class addProducts extends JPanel {

	private JComboBox comboBoxSubcategorias, comboBoxCategorias, comboBoxProveedores;
	private JTextField referencia;
	private JTextField nombrePieza;
	private JTextField descripcionPieza;
	private JTextField cantidad;
	private JTextField precioPieza;
	/**
	 * Create the panel.
	 */
	public addProducts() {
		setLayout(null);
		setBounds(100, 100, 525, 333);
		
		JLabel lblCategorias = new JLabel("Categorias");
		lblCategorias.setBounds(27, 28, 72, 16);
		add(lblCategorias);
		
		JLabel lblSubcategorias = new JLabel("SubCategorias");
		lblSubcategorias.setBounds(27, 52, 95, 16);
		add(lblSubcategorias);
		
				
		comboBoxCategorias = new JComboBox();
		obtenerCategorias();
		comboBoxCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBoxSubcategorias.removeAllItems();;
				obtenerCategoriasEspecificas(comboBoxCategorias.getSelectedIndex()+1);
			}
		});
		
		comboBoxCategorias.setBounds(134, 24, 127, 27);
		add(comboBoxCategorias);
		
		comboBoxSubcategorias = new JComboBox();
		obtenerCategoriasEspecificas(comboBoxCategorias.getSelectedIndex()+1);
		comboBoxSubcategorias.setBounds(134, 48, 127, 27);
		add(comboBoxSubcategorias);
		
		JLabel lblReferencia = new JLabel("Referencia");
		lblReferencia.setBounds(27, 108, 72, 16);
		add(lblReferencia);
		
		referencia = new JTextField();
		referencia.setBounds(127, 102, 134, 28);
		add(referencia);
		referencia.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(27, 136, 61, 16);
		add(lblNombre);
		
		nombrePieza = new JTextField();
		nombrePieza.setBounds(127, 130, 134, 28);
		add(nombrePieza);
		nombrePieza.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(27, 164, 82, 16);
		add(lblDescripcin);
		
		descripcionPieza = new JTextField();
		descripcionPieza.setBounds(27, 181, 234, 113);
		add(descripcionPieza);
		descripcionPieza.setColumns(10);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(285, 108, 61, 16);
		add(lblCantidad);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(285, 136, 61, 16);
		add(lblPrecio);
		
		cantidad = new JTextField();
		cantidad.setBounds(358, 102, 106, 28);
		add(cantidad);
		cantidad.setColumns(10);
		
		precioPieza = new JTextField();
		precioPieza.setBounds(358, 130, 106, 28);
		add(precioPieza);
		precioPieza.setColumns(10);
		
		JButton btnAadirProducto = new JButton("Añadir Producto");
		btnAadirProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				addProductoBBDD();
			}

		});
		btnAadirProducto.setBounds(336, 202, 117, 48);
		add(btnAadirProducto);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(285, 28, 72, 16);
		add(lblProveedor);
		
		comboBoxProveedores = new JComboBox();
		obtenerProveedores();
		comboBoxProveedores.setBounds(358, 24, 106, 27);
		add(comboBoxProveedores);

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
	}
	
	private void addProductoBBDD() {
		
		//Creamos la sesion y el transaction para poder ejecutar las acciones a la BBDD.
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();
		Transaction trans = sesion.beginTransaction();
				
		
		Piezas nuevaPieza = new Piezas();
		nuevaPieza.setPieReferencia(referencia.getText());
		nuevaPieza.setPieNombre(nombrePieza.getText());
		nuevaPieza.setPieDescripcion(descripcionPieza.getText());
		nuevaPieza.setPieCantidad(Integer.parseInt(cantidad.getText()));
		nuevaPieza.setPiePrecio(Float.parseFloat(precioPieza.getText()));
		Proveedores nuevoProveedor = new Proveedores();
		nuevoProveedor.setProCodigo(comboBoxProveedores.getSelectedIndex()+1);
		nuevaPieza.setProveedores(nuevoProveedor);
		CatEspecifica nuevaCatEspecifica = new CatEspecifica();
		nuevaCatEspecifica.setCatEspCodigo((comboBoxSubcategorias.getSelectedIndex()+1));
		
		System.out.println("HOLAAAAAA   "+ nuevaCatEspecifica.getCatEspCodigo());
		sesion.save(nuevaPieza);
		trans.commit();
		sesion.close();
		
		JOptionPane.showMessageDialog(null, "PIEZA CREADO CORRECTAMENTE.", "CREANDO UNA NUEVA PIEZA", 1);
		
		
	}
}


