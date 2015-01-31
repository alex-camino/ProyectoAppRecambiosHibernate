package principal;

import clases.CatPiezas;
import clases.CatEspecifica;
import clases.Metodos;
import clases.Proveedores;
import clases.Piezas;
import clases.SessionFactoryUtil;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;

public class visualizar extends JPanel {

	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panelProductos;
	JScrollPane scrollPane;
	private JTable tabla;
	private DefaultTableModel modelo;
	private Object[][] filas;
	private String[] columnas={"ID", "Referencia", "Nombre", "Descripci\u00F3n", "Stock", "Precio", "Proveedor", "SubCategoria"};
	private JButton buttonEliminar;
	private JPanel panel_3;
	private JLabel lblBuscarPor;
	private JLabel lblSubcategoria;
	private JComboBox comboBoxSubcategorias;
	private JLabel lblReferencia;
	private JTextField txtReferencia;
	private JButton btnBuscar;
	private JButton btnMostrarPiezas;
	

	
	public visualizar(JPanel contentPane) {
		
		this.contentPane=contentPane;
		comboBoxSubcategorias = new JComboBox();
		txtReferencia = new JTextField();	
		
		setBounds(100, 100, 1260, 660);
		
		
		panel = new JPanel();
		//contentPane.add(panel);
		setLayout(new BorderLayout(0, 0));
		
		panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		//panel_1.setBounds(100, 100, 760, 660);
		
		

		scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		mostrarProductos("",0);
		
		
		panel_2 = new JPanel();
		add(panel_2, BorderLayout.EAST);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{75, 0};
		gbl_panel_2.rowHeights = new int[]{50, 49, 48, 55, 55, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JButton btnInsertar = new JButton("Insertar");
		btnInsertar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cargarInsertar();
				
			}
		});
		GridBagConstraints gbc_btnInsertar = new GridBagConstraints();
		gbc_btnInsertar.fill = GridBagConstraints.BOTH;
		gbc_btnInsertar.insets = new Insets(0, 0, 5, 0);
		gbc_btnInsertar.gridx = 0;
		gbc_btnInsertar.gridy = 0;
		panel_2.add(btnInsertar, gbc_btnInsertar);
		
		buttonEliminar = new JButton("Modificar");
		buttonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				obtenerInfoTabla();
			}

			
		});
		GridBagConstraints gbc_buttonEliminar = new GridBagConstraints();
		gbc_buttonEliminar.fill = GridBagConstraints.BOTH;
		gbc_buttonEliminar.insets = new Insets(0, 0, 5, 0);
		gbc_buttonEliminar.gridx = 0;
		gbc_buttonEliminar.gridy = 1;
		panel_2.add(buttonEliminar, gbc_buttonEliminar);
		
		JButton btnModificar = new JButton("Eliminar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cargarBorrarProducto();
				
			}
		});
		GridBagConstraints gbc_btnModificar = new GridBagConstraints();
		gbc_btnModificar.insets = new Insets(0, 0, 5, 0);
		gbc_btnModificar.fill = GridBagConstraints.BOTH;
		gbc_btnModificar.gridx = 0;
		gbc_btnModificar.gridy = 2;
		panel_2.add(btnModificar, gbc_btnModificar);
		
		btnMostrarPiezas = new JButton("Mostrar Piezas");
		btnMostrarPiezas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cargarVisualizar();
			}
		});
		GridBagConstraints gbc_btnMostrarPiezas = new GridBagConstraints();
		gbc_btnMostrarPiezas.insets = new Insets(0, 0, 5, 0);
		gbc_btnMostrarPiezas.fill = GridBagConstraints.VERTICAL;
		gbc_btnMostrarPiezas.gridx = 0;
		gbc_btnMostrarPiezas.gridy = 3;
		panel_2.add(btnMostrarPiezas, gbc_btnMostrarPiezas);
		
		panel_3 = new JPanel();
		add(panel_3, BorderLayout.NORTH);
		
		lblBuscarPor = new JLabel("BUSCAR POR:");
		
		lblSubcategoria = new JLabel("Subcategoria:");
		
				
		//ComboBox SUBCATEGORIAS
		comboBoxSubcategorias.setMaximumRowCount(15);
		obtenerCategoriasEspecificas();
		
		
		//TXTReferencia
		lblReferencia = new JLabel("Referencia:");
		txtReferencia.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					buscarProducto();
					
				} catch (InterruptedException e1) {
					
					
					e1.printStackTrace();
				}
				
			}
		});
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(lblBuscarPor, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblSubcategoria, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBoxSubcategorias, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblReferencia, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtReferencia, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
					.addGap(76)
					.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addGap(575))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
					.addComponent(lblBuscarPor, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblSubcategoria, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
					.addComponent(comboBoxSubcategorias, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addComponent(lblReferencia, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addComponent(txtReferencia, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(btnBuscar)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_3.setLayout(gl_panel_3);
	}
	
	
	public void cargarInsertar(){
		
		this.contentPane.removeAll();
		
		ProductosAdd pantallaInsertar = new ProductosAdd(contentPane);
		pantallaInsertar.setVisible(true);
		this.contentPane.add(pantallaInsertar);
		SwingUtilities.updateComponentTreeUI(pantallaInsertar);
		//dispose();
	}
	
	public void cargarBorrarProducto(){
		
		//Obtengo el codigo de la pieza a partir de la fila seleccionada.
		int opcion = tabla.getSelectedRow();
		
		
		try{
			
			if(opcion==-1){
			
				JOptionPane.showMessageDialog(null, "Debe seleccionar un producto para poder modificarlo.","Advertencia",JOptionPane.WARNING_MESSAGE);
				
			}else{
				
				int codPieza=(int) tabla.getValueAt(opcion, 0);
				Metodos.borrarProducto(codPieza);
				
				cargarVisualizar();
			}

		}catch(Exception ex){
			
			JOptionPane.showMessageDialog(null, "Ha habido un problema al obtener la informacion de la tabla","Advertencia",JOptionPane.WARNING_MESSAGE);
			
		}
		
	}

	public void cargarVisualizar(){
		
		this.contentPane.removeAll();
		
		visualizar pantallaVisualizar = new visualizar(contentPane);
		pantallaVisualizar.setVisible(true);
		this.contentPane.add(pantallaVisualizar);
		SwingUtilities.updateComponentTreeUI(pantallaVisualizar);
		
	}
	public void buscarProducto() throws InterruptedException{
		
		//Averiguamos si no hay seleccionada ninguna opción o si el campo referencia esta vacio
		if(comboBoxSubcategorias.getSelectedIndex()==-1&&txtReferencia.getText().length()==0){
			
			JOptionPane.showMessageDialog(null, "Debe seleccionar una subcategoria o una\n referencia para poder mostrar los productos.","Advertencia",JOptionPane.WARNING_MESSAGE);
			
		}else if(txtReferencia.getText().length()!=0&&comboBoxSubcategorias.getSelectedIndex()==-1){
			
			
			JOptionPane.showMessageDialog(null, "Buscando la pieza por la referencia, por favor espere.....","Advertencia",JOptionPane.WARNING_MESSAGE);
			mostrarProductos(txtReferencia.getText(),0);
			//Thread.sleep(1000);
			
		}else if(txtReferencia.getText().length()==0&&comboBoxSubcategorias.getSelectedIndex()!=-1){
		
			JOptionPane.showMessageDialog(null, "Buscando piezas por subcategoria, por favor espere.....","Advertencia",JOptionPane.WARNING_MESSAGE);
			mostrarProductos("",(comboBoxSubcategorias.getSelectedIndex()+1));
			//Thread.sleep(1000);
			
		}else{
			
			
			JOptionPane.showMessageDialog(null, "Buscando piezas por subcategoria, por favor espere.....","Advertencia",JOptionPane.WARNING_MESSAGE);
			mostrarProductos(txtReferencia.getText(),(comboBoxSubcategorias.getSelectedIndex()+1));
			//Thread.sleep(1000);
		}
	}
	public void obtenerCategoriasEspecificas(){
		
		//Creamos la sesion y el transaction para poder ejecutar las acciones a la BBDD.
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();
		Transaction trans = sesion.beginTransaction();
				
		CatEspecifica nuevaCategoria = new CatEspecifica();
		
		Query q = sesion.createQuery("from CatEspecifica");
		
		
		List<CatEspecifica> lista = q.list();
		Iterator<CatEspecifica> iter = lista.iterator();
		
		q.setFetchSize(20);
		
		iter=q.iterate();
		
		while(iter.hasNext()){
			
			nuevaCategoria=(CatEspecifica) iter.next();
			
			comboBoxSubcategorias.addItem(nuevaCategoria.getCatEspNombre());
		}
		
		comboBoxSubcategorias.setSelectedIndex(-1);
		sesion.close();
	}
	public void obtenerInfoTabla() {
			
		int opcion = tabla.getSelectedRow();
		
		try{
			
			int codPieza, cantidad;
			float precio;
			String referencia, nombre, descripcion, proveedor, subcategoria;
			
			if(opcion==-1){
				
				JOptionPane.showMessageDialog(null, "Debe seleccionar un producto para poder modificarlo.","Advertencia",JOptionPane.WARNING_MESSAGE);
			}else{
				
				Proveedores nuevoProveedor= new Proveedores();
				CatEspecifica nuevaSubcat = new CatEspecifica();
				Piezas pieza1 = new Piezas();
				
				//Obtengo la información de la fila seleccionada.
				codPieza=(int) tabla.getValueAt(opcion, 0);
				referencia=tabla.getValueAt(opcion, 1).toString();
				nombre=tabla.getValueAt(opcion, 2).toString();
				descripcion=tabla.getValueAt(opcion, 3).toString();
				cantidad=(int) tabla.getValueAt(opcion, 4);
				precio=(float) tabla.getValueAt(opcion, 5);
				proveedor=tabla.getValueAt(opcion, 6).toString();
				subcategoria=tabla.getValueAt(opcion, 7).toString();
				
				
				nuevoProveedor.setProNombre(proveedor);
				nuevaSubcat.setCatEspNombre(subcategoria);
				
				pieza1.setPieCodigo(codPieza);
				pieza1.setPieReferencia(referencia);
				pieza1.setPieNombre(nombre);
				pieza1.setPieDescripcion(descripcion);
				pieza1.setPieCantidad(cantidad);
				pieza1.setPiePrecio(precio);
				pieza1.setProveedores(nuevoProveedor);
				pieza1.setCatEspecifica(nuevaSubcat);
				
				
				//Cargo la ventana de modificar Productos.
				this.contentPane.removeAll();
				
				modifyProductos pantallaModificarProductos=new modifyProductos(pieza1, this.contentPane);
				
				pantallaModificarProductos.setVisible(true);
				this.contentPane.add(pantallaModificarProductos);
				SwingUtilities.updateComponentTreeUI(pantallaModificarProductos);
				
				
			}
			
		}catch(Exception ex){
			
			
			JOptionPane.showMessageDialog(null, "Ha habido un problema al obtener la informacion de la tabla","Advertencia",JOptionPane.WARNING_MESSAGE);
		}
				
	}
	
	
	public void mostrarProductos(String referencia, int subcategoria){
		
		boolean encontrado;
		
		tabla = new JTable();
		
		modelo = new DefaultTableModel(filas, columnas);
		
		if(referencia.length()==0&&subcategoria==0){
			
			encontrado=clases.Metodos.rellenarProductos(modelo, "",0);
			
		}else if(referencia.length()==0&&subcategoria!=0){
			
			encontrado=clases.Metodos.rellenarProductos(modelo, "",subcategoria);
		}else if(referencia.length()!=0&&subcategoria==0){
			
			encontrado=clases.Metodos.rellenarProductos(modelo, referencia,0);
		}else{
			
			encontrado=clases.Metodos.rellenarProductos(modelo, referencia,subcategoria);
		}
		
		if(encontrado){
			
			tabla.setModel(modelo);
			scrollPane.setViewportView(tabla);
			
	
			/* Para que no se puedan modificar los campos
			 * Aporte: David.
			 */
			for (int j = 0; j < tabla.getColumnCount(); j++) {
	
				Class<?> col_class = tabla.getColumnClass(j);
				tabla.setDefaultEditor(col_class, null); // remove editor
	
			}
			
			comboBoxSubcategorias.setSelectedIndex(-1);
			txtReferencia.setText("");
			
		}else{
			
			JOptionPane.showMessageDialog(null, "No existen piezas","Advertencia",JOptionPane.WARNING_MESSAGE);
		}
		
		
	}
}