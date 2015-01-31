package clases;


import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import clases.Piezas;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class Metodos {
	
	public static void rellenarProductos(DefaultTableModel modelo){
		
		
		//Creamos la sesion y el transaction para poder ejecutar las acciones a la BBDD.
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();
		
				
		Piezas nuevaPieza = new Piezas();
		Proveedores nuevoProveedor = new Proveedores();
		CatEspecifica nuevaSubCat = new CatEspecifica();
		
		Query q = sesion.createQuery("from Piezas order by pieCodigo");
		
		
		List<Piezas> lista = q.list();
		Iterator<Piezas> iter = lista.iterator();
		
		q.setFetchSize(20);
		
		iter=q.iterate();
		
		while(iter.hasNext()){
			
						
			nuevaPieza=(Piezas) iter.next();
			nuevoProveedor=nuevaPieza.getProveedores();
			nuevaSubCat=nuevaPieza.getCatEspecifica();
			Object[] fila = new Object[8];
			
			fila[0]=nuevaPieza.getPieCodigo();
			fila[1]=nuevaPieza.getPieReferencia();
			fila[2]=nuevaPieza.getPieNombre();
			fila[3]=nuevaPieza.getPieDescripcion();
			fila[4]=nuevaPieza.getPieCantidad();
			fila[5]=nuevaPieza.getPiePrecio();
			fila[6]=nuevaPieza.getProveedores().getProNombre();
			fila[7]=nuevaPieza.getCatEspecifica().getCatEspNombre();
					
			
			modelo.addRow(fila);
		}
		
		sesionF.close();
		sesion.close();
	}
	
	public static void borrarProducto(int codPieza){
		
		/*
		 * Variables y objetos necesarios.
		 * Creamos la sesion y el transaction para poder ejecutar las acciones a la BBDD.
		 */
		SessionFactory sesionF = SessionFactoryUtil.getSessionFactory();
		Session sesion = sesionF.openSession();
		Transaction trans = sesion.beginTransaction();
		
		String consulta;
		int filasAfectadas=0;
		
		try{
			Piezas nuevaPieza = new Piezas();
			
			nuevaPieza = (Piezas) sesion.load(Piezas.class, codPieza);
			
			sesion.delete(nuevaPieza);
			trans.commit();
			sesion.close();
				
			
			JOptionPane.showMessageDialog(null, "SE HA BORRADO LA PIEZA CORRECTAMENTE.", "Eliminar Piezas", 1);
		}catch(Exception ex){
			
			JOptionPane.showMessageDialog(null, "Ha habido un error al eliminar la Pieza con código "+codPieza+".", "Eliminar Piezas", 1);
		}
		
				
		
		
	}

	
}
