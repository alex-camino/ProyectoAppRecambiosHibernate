package clases;

// Generated 28-ene-2015 16:17:57 by Hibernate Tools 3.4.0.CR1

/**
 * LineasPedido generated by hbm2java
 */
public class LineasPedido implements java.io.Serializable {

	private Integer linCodigo;
	private Pedidos pedidos;
	private Piezas piezas;
	private String linDescripcion;
	private Integer linCantidad;
	private Float linPrecio;

	public LineasPedido() {
	}

	public LineasPedido(Pedidos pedidos, Piezas piezas, String linDescripcion,
			Integer linCantidad, Float linPrecio) {
		this.pedidos = pedidos;
		this.piezas = piezas;
		this.linDescripcion = linDescripcion;
		this.linCantidad = linCantidad;
		this.linPrecio = linPrecio;
	}

	public Integer getLinCodigo() {
		return this.linCodigo;
	}

	public void setLinCodigo(Integer linCodigo) {
		this.linCodigo = linCodigo;
	}

	public Pedidos getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(Pedidos pedidos) {
		this.pedidos = pedidos;
	}

	public Piezas getPiezas() {
		return this.piezas;
	}

	public void setPiezas(Piezas piezas) {
		this.piezas = piezas;
	}

	public String getLinDescripcion() {
		return this.linDescripcion;
	}

	public void setLinDescripcion(String linDescripcion) {
		this.linDescripcion = linDescripcion;
	}

	public Integer getLinCantidad() {
		return this.linCantidad;
	}

	public void setLinCantidad(Integer linCantidad) {
		this.linCantidad = linCantidad;
	}

	public Float getLinPrecio() {
		return this.linPrecio;
	}

	public void setLinPrecio(Float linPrecio) {
		this.linPrecio = linPrecio;
	}

}