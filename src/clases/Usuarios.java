package clases;

// Generated 28-ene-2015 16:17:57 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * Usuarios generated by hbm2java
 */
public class Usuarios implements java.io.Serializable {

	private Integer userCodigo;
	private String userAlias;
	private String userPass;
	private Set<DatosPersonales> datosPersonaleses = new HashSet<DatosPersonales>(
			0);
	private Set<Pedidos> pedidoses = new HashSet<Pedidos>(0);

	public Usuarios() {
	}

	public Usuarios(String userAlias, String userPass,
			Set<DatosPersonales> datosPersonaleses, Set<Pedidos> pedidoses) {
		this.userAlias = userAlias;
		this.userPass = userPass;
		this.datosPersonaleses = datosPersonaleses;
		this.pedidoses = pedidoses;
	}

	public Integer getUserCodigo() {
		return this.userCodigo;
	}

	public void setUserCodigo(Integer userCodigo) {
		this.userCodigo = userCodigo;
	}

	public String getUserAlias() {
		return this.userAlias;
	}

	public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
	}

	public String getUserPass() {
		return this.userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public Set<DatosPersonales> getDatosPersonaleses() {
		return this.datosPersonaleses;
	}

	public void setDatosPersonaleses(Set<DatosPersonales> datosPersonaleses) {
		this.datosPersonaleses = datosPersonaleses;
	}

	public Set<Pedidos> getPedidoses() {
		return this.pedidoses;
	}

	public void setPedidoses(Set<Pedidos> pedidoses) {
		this.pedidoses = pedidoses;
	}

}