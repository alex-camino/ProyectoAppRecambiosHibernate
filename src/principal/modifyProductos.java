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
import javax.swing.SwingUtilities;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JTextPane;

public class modifyProductos extends JPanel {

	private JPanel contentPane;
	private JComboBox comboBoxSubcategorias, comboBoxCategorias, comboBoxProveedores;
	private JTextField referencia;
	private JTextField nombrePieza;
	private JTextPane descripcionPieza;
	private JTextField cantidad;
	private JTextField precioPieza;
	

	

	/**
	 * Create the frame.
	 */
	public modifyProductos(final Piezas nuevaPieza, JPanel contentPane) {
		
		this.contentPane=contentPane;
		
	
		
		setBounds(100, 100, 1260, 660);
		setLayout(null);
				
		JLabel lblCategorias = new JLabel("Categorias");
		lblCategorias.setBounds(66, 121, 75, 16);
		add(lblCategorias);
		
				
		comboBoxCategorias = new JComboBox();
		obtenerCategorias();
		comboBoxCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBoxSubcategorias.removeAllItems();;
				obtenerCategoriasEspecificas(comboBoxCategorias.getSelectedIndex()+1);
			}
		});
		
		comboBoxCategorias.setBounds(223, 117, 168, 27);
		add(comboBoxCategorias);
		
		comboBoxSubcategorias = new JComboBox();
		obtenerCategoriasEspecificas(comboBoxCategorias.getSelectedIndex()+1);
		comboBoxSubcategorias.setBounds(223, 190, 168, 27);
		add(comboBoxSubcategorias);
		
		JLabel lblReferencia = new JLabel("Referencia");
		lblReferencia.setBounds(66, 293, 65, 16);
		add(lblReferencia);
		
		referencia = new JTextField();
		referencia.setBounds(223, 287, 168, 28);
		add(referencia);
		referencia.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(66, 366, 50, 16);
		add(lblNombre);
		
		nombrePieza = new JTextField();
		nombrePieza.setBounds(223, 360, 168, 28);
		add(nombrePieza);
		nombrePieza.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripción");
		lblDescripcin.setBounds(66, 449, 75, 16);
		add(lblDescripcin);
		
		descripcionPieza = new JTextPane();
		descripcionPieza.setBounds(66, 477, 325, 177);
		add(descripcionPieza);
		
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(593, 194, 56, 16);
		add(lblCantidad);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(593, 121, 38, 16);
		add(lblPrecio);
		
		cantidad = new JTextField();
		cantidad.setBounds(723, 188, 152, 28);
		add(cantidad);
		cantidad.setColumns(10);
		
		JLabel lblSubcategorias = new JLabel("SubCategorias");
		lblSubcategorias.setBounds(66, 194, 90, 16);
		add(lblSubcategorias);
		
		precioPieza = new JTextField();
		precioPieza.setBounds(723, 115, 152, 28);
		add(precioPieza);
		precioPieza.setColumns(10);
		
		JButton btnAadirProducto = new JButton("Modificar Producto");
		btnAadirProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				modifyProductoBBDD(nuevaPieza.getPieCodigo());
			}

		});
		btnAadirProducto.setBounds(556, 444, 163, 29);
		add(btnAadirProducto);
		
		JLabel lblProveedor = new JLabel("Proveedor");
		lblProveedor.setBounds(593, 293, 62, 16);
		add(lblProveedor);
		
		comboBoxProveedores = new JComboBox();
		obtenerProveedores();
		comboBoxProveedores.setBounds(723, 289, 152, 27);
		add(comboBoxProveedores);
		
		JButton btnVolver = new JButton("Volver ");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cargarVolver();
				
				
			}
		});
		btnVolver.setBounds(779, 444, 134, 29);
		add(btnVolver);
		
		//Agregar Informacion al formulario
		addInfoPantalla(nuevaPieza);

	}
	
	public void cargarVolver(){
		
		this.contentPane.removeAll();
		
		visualizar pantallaVisualizar = new visualizar(contentPane);
		pantallaVisualizar.setVisible(true);
		this.contentPane.add(pantallaVisualizar);
		SwingUtilities.updateComponentTreeUI(pantallaVisualizar);
	}
	
	public void addInfoPantalla(Piezas nuevaPieza){
		
		referencia.setText(nuevaPieza.getPieReferencia());
		nombrePieza.setText(nuevaPieza.getPieNombre());
		descripcionPieza.setText(nuevaPieza.getPieDescripcion());
		cantidad.setText(nuevaPieza.getPieCantidad().toString());
		precioPieza.setText(nuevaPieza.getPiePrecio().toString());
		
		
		String subCategoria = nuevaPieza.getCatEspecifica().getCatEspNombre(), proveedor = nuevaPieza.getProveedores().getProNombre(), categoria="";
		int codCategoria;
		
		obtenerCategorias();
		for(int i=0; i<comboBoxCategorias.getItemCount();i++){
			
			obtenerCategoriasEspecificas(i);
		}
		
		codCategoria=codCategoria(subCategoria);
		
		switch(codCategoria){
		
		case 1:
				categoria="Partes Motor";
			break;
		case 2:
				categoria="Frenado";
			break;
		case 3:
				categoria="Suspension-Dirección";
			break;
		case 4:
				categoria="Piezas Termicas y Climatización";
			break;
		default:
		}
		
		
		for(int i=0; i<comboBoxCategorias.getItemCount();i++){
			
			if(categoria.equalsIgnoreCase(comboBoxCategorias.getItemAt(i).toString())){
				
				comboBoxCategorias.setSelectedIndex(i);
				break;
			}
		}
		
		
		for(int i=0; i<comboBoxSubcategorias.getItemCount();i++){
			
			
			if(subCategoria.equalsIgnoreCase(comboBoxSubcategorias.getItemAt(i).toString())){
				
				comboBoxSubcategorias.setSelectedIndex(i);
				
				break;
			}
		}
		
	
		
		for(int i=0; i<comboBoxProveedores.getItemCount();i++){
			
					
			if(proveedor.equalsIgnoreCase(comboBoxProveedores.getItemAt(i).toString())){
				
				comboBoxProveedores.setSelectedIndex(i);
				break;
			}
		}
	}
		
		
	
	public int codCategoria(String subCategoria){
		
		//Creamos la sesion y el transaction para poder ejecutar las acciones a la BBDD.
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();
		Transaction trans = sesion.beginTransaction();
		
		int categoria=-1;
		
		CatEspecifica nuevaCategoria = new CatEspecifica();
		
		Query q = sesion.createQuery("from CatEspecifica ce where ce.catEspNombre = :nomSubCategoria").setString("nomSubCategoria", subCategoria);
		
		
		List<CatEspecifica> lista = q.list();
		Iterator<CatEspecifica> iter = lista.iterator();
		
		q.setFetchSize(20);
		
		iter=q.iterate();
		
		while(iter.hasNext()){
			
			nuevaCategoria=(CatEspecifica) iter.next();
			
			
			categoria=nuevaCategoria.getCatPiezas().getCatCodigo();
		}
		
		sesion.close();
		
		return categoria;
		
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
		
		//comboBoxCategorias.setSelectedIndex(2);
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
	
	public void modifyProductoBBDD(int codPieza) {
		
		//Creamos la sesion y el transaction para poder ejecutar las acciones a la BBDD.
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();
		Transaction trans = sesion.beginTransaction();
		
		Piezas nuevaPieza = new Piezas();
		nuevaPieza = (Piezas) sesion.load(Piezas.class, (int) codPieza);
		
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
		
		JOptionPane.showMessageDialog(null, "PIEZA MODIFICADA CORRECTAMENTE.", "MODIFICANDO PIEZA", 1);
		
		
	}
}
