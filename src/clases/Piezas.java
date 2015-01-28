package clases;

// Generated 28-ene-2015 16:17:57 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Piezas generated by hbm2java
 */
public class Piezas implements java.io.Serializable {

	private Integer pieCodigo;
	private CatEspecifica catEspecifica;
	private Proveedores proveedores;
	private String pieReferencia;
	private String pieNombre;
	private String pieDescripcion;
	private Integer pieCantidad;
	private Float piePrecio;
	private Set<Proveedores> proveedoreses = new HashSet<Proveedores>(0);
	private Set<Tipos> tiposes = new HashSet<Tipos>(0);
	private Set<LineasPedido> lineasPedidos = new HashSet<LineasPedido>(0);
	private Set<PedidosProveedor> pedidosProveedors = new HashSet<PedidosProveedor>(
			0);

	public Piezas() {
	}

	public Piezas(String pieReferencia) {
		this.pieReferencia = pieReferencia;
	}

	public Piezas(CatEspecifica catEspecifica, Proveedores proveedores,
			String pieReferencia, String pieNombre, String pieDescripcion,
			Integer pieCantidad, Float piePrecio,
			Set<Proveedores> proveedoreses, Set<Tipos> tiposes,
			Set<LineasPedido> lineasPedidos,
			Set<PedidosProveedor> pedidosProveedors) {
		this.catEspecifica = catEspecifica;
		this.proveedores = proveedores;
		this.pieReferencia = pieReferencia;
		this.pieNombre = pieNombre;
		this.pieDescripcion = pieDescripcion;
		this.pieCantidad = pieCantidad;
		this.piePrecio = piePrecio;
		this.proveedoreses = proveedoreses;
		this.tiposes = tiposes;
		this.lineasPedidos = lineasPedidos;
		this.pedidosProveedors = pedidosProveedors;
	}

	public Integer getPieCodigo() {
		return this.pieCodigo;
	}

	public void setPieCodigo(Integer pieCodigo) {
		this.pieCodigo = pieCodigo;
	}

	public CatEspecifica getCatEspecifica() {
		return this.catEspecifica;
	}

	public void setCatEspecifica(CatEspecifica catEspecifica) {
		this.catEspecifica = catEspecifica;
	}

	public Proveedores getProveedores() {
		return this.proveedores;
	}

	public void setProveedores(Proveedores proveedores) {
		this.proveedores = proveedores;
	}

	public String getPieReferencia() {
		return this.pieReferencia;
	}

	public void setPieReferencia(String pieReferencia) {
		this.pieReferencia = pieReferencia;
	}

	public String getPieNombre() {
		return this.pieNombre;
	}

	public void setPieNombre(String pieNombre) {
		this.pieNombre = pieNombre;
	}

	public String getPieDescripcion() {
		return this.pieDescripcion;
	}

	public void setPieDescripcion(String pieDescripcion) {
		this.pieDescripcion = pieDescripcion;
	}

	public Integer getPieCantidad() {
		return this.pieCantidad;
	}

	public void setPieCantidad(Integer pieCantidad) {
		this.pieCantidad = pieCantidad;
	}

	public Float getPiePrecio() {
		return this.piePrecio;
	}

	public void setPiePrecio(Float piePrecio) {
		this.piePrecio = piePrecio;
	}

	public Set<Proveedores> getProveedoreses() {
		return this.proveedoreses;
	}

	public void setProveedoreses(Set<Proveedores> proveedoreses) {
		this.proveedoreses = proveedoreses;
	}

	public Set<Tipos> getTiposes() {
		return this.tiposes;
	}

	public void setTiposes(Set<Tipos> tiposes) {
		this.tiposes = tiposes;
	}

	public Set<LineasPedido> getLineasPedidos() {
		return this.lineasPedidos;
	}

	public void setLineasPedidos(Set<LineasPedido> lineasPedidos) {
		this.lineasPedidos = lineasPedidos;
	}

	public Set<PedidosProveedor> getPedidosProveedors() {
		return this.pedidosProveedors;
	}

	public void setPedidosProveedors(Set<PedidosProveedor> pedidosProveedors) {
		this.pedidosProveedors = pedidosProveedors;
	}

}