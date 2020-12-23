package jsf.hockshop.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the specyfication database table.
 * 
 */
@Entity
@NamedQuery(name="Specyfication.findAll", query="SELECT s FROM Specyfication s")
public class Specyfication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSpecyfication;

	private String value;

	//bi-directional many-to-one association to Coustomer
	@ManyToOne
	private Coustomer coustomer;

	//bi-directional many-to-one association to Specelem
	@ManyToOne
	private Specelem specelem;

	public Specyfication() {
	}

	public int getIdSpecyfication() {
		return this.idSpecyfication;
	}

	public void setIdSpecyfication(int idSpecyfication) {
		this.idSpecyfication = idSpecyfication;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Coustomer getCoustomer() {
		return this.coustomer;
	}

	public void setCoustomer(Coustomer coustomer) {
		this.coustomer = coustomer;
	}

	public Specelem getSpecelem() {
		return this.specelem;
	}

	public void setSpecelem(Specelem specelem) {
		this.specelem = specelem;
	}

}