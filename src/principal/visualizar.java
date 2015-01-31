package principal;

import clases.CatPiezas;
import clases.CatEspecifica;
import clases.Metodos;
import clases.Proveedores;
import clases.Piezas;

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
	

	
	public visualizar(JPanel contentPane) {
		
		this.contentPane=contentPane;
		
		
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
		
		mostrarProductos();
		
		
		panel_2 = new JPanel();
		add(panel_2, BorderLayout.EAST);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{75, 0};
		gbl_panel_2.rowHeights = new int[]{50, 49, 48, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
				this.contentPane.removeAll();
				
				visualizar pantallaVisualizar = new visualizar(contentPane);
				pantallaVisualizar.setVisible(true);
				this.contentPane.add(pantallaVisualizar);
				SwingUtilities.updateComponentTreeUI(pantallaVisualizar);
				
			}

		}catch(Exception ex){
			
			JOptionPane.showMessageDialog(null, "Ha habido un problema al obtener la informacion de la tabla","Advertencia",JOptionPane.WARNING_MESSAGE);
			
		}
		
		
		
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
	
	
	public void mostrarProductos(){
		
		tabla = new JTable();
		
		modelo = new DefaultTableModel(filas, columnas);
		
		
		clases.Metodos.rellenarProductos(modelo);
		tabla.setModel(modelo);
		scrollPane.setViewportView(tabla);
		

		/* Para que no se puedan modificar los campos
		 * Aporte: David.
		 */
		for (int j = 0; j < tabla.getColumnCount(); j++) {

			Class<?> col_class = tabla.getColumnClass(j);
			tabla.setDefaultEditor(col_class, null); // remove editor

		}
	}
}