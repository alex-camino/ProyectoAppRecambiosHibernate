package clases;

// Generated 28-ene-2015 16:17:57 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * CatEspecifica generated by hbm2java
 */
public class CatEspecifica implements java.io.Serializable {

	private Integer catEspCodigo;
	private CatPiezas catPiezas;
	private String catEspNombre;
	private Set<Piezas> piezases = new HashSet<Piezas>(0);

	public CatEspecifica() {
	}

	public CatEspecifica(String catEspNombre) {
		this.catEspNombre = catEspNombre;
	}

	public CatEspecifica(CatPiezas catPiezas, String catEspNombre,
			Set<Piezas> piezases) {
		this.catPiezas = catPiezas;
		this.catEspNombre = catEspNombre;
		this.piezases = piezases;
	}

	public Integer getCatEspCodigo() {
		return this.catEspCodigo;
	}

	public void setCatEspCodigo(Integer catEspCodigo) {
		this.catEspCodigo = catEspCodigo;
	}

	public CatPiezas getCatPiezas() {
		return this.catPiezas;
	}

	public void setCatPiezas(CatPiezas catPiezas) {
		this.catPiezas = catPiezas;
	}

	public String getCatEspNombre() {
		return this.catEspNombre;
	}

	public void setCatEspNombre(String catEspNombre) {
		this.catEspNombre = catEspNombre;
	}

	public Set<Piezas> getPiezases() {
		return this.piezases;
	}

	public void setPiezases(Set<Piezas> piezases) {
		this.piezases = piezases;
	}

}
