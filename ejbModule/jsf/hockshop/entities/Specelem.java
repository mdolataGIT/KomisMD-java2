package jsf.hockshop.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the specelem database table.
 * 
 */
@Entity
@NamedQuery(name="Specelem.findAll", query="SELECT s FROM Specelem s")
public class Specelem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSpecelem;

	private String specElemName;

	private String type;

	//bi-directional many-to-one association to Specyfication
	@OneToMany(mappedBy="specelem")
	private List<Specyfication> specyfications;

	public Specelem() {
	}

	public int getIdSpecelem() {
		return this.idSpecelem;
	}

	public void setIdSpecelem(int idSpecelem) {
		this.idSpecelem = idSpecelem;
	}

	public String getSpecElemName() {
		return this.specElemName;
	}

	public void setSpecElemName(String specElemName) {
		this.specElemName = specElemName;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Specyfication> getSpecyfications() {
		return this.specyfications;
	}

	public void setSpecyfications(List<Specyfication> specyfications) {
		this.specyfications = specyfications;
	}

	public Specyfication addSpecyfication(Specyfication specyfication) {
		getSpecyfications().add(specyfication);
		specyfication.setSpecelem(this);

		return specyfication;
	}

	public Specyfication removeSpecyfication(Specyfication specyfication) {
		getSpecyfications().remove(specyfication);
		specyfication.setSpecelem(null);

		return specyfication;
	}

}