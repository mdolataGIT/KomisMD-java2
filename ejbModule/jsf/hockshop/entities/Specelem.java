package jsf.hockshop.entities;

import java.io.Serializable;
import javax.persistence.*;


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
	private Integer idSpecelem;

	private String specName;

	private String specValue;

	//bi-directional many-to-one association to Car
	@ManyToOne
	private Car car;

	public Specelem() {
	}

	public Integer getIdSpecelem() {
		return this.idSpecelem;
	}

	public void setIdSpecelem(Integer idSpecelem) {
		this.idSpecelem = idSpecelem;
	}

	public String getSpecName() {
		return this.specName;
	}

	public void setSpecName(String specName) {
		this.specName = specName;
	}

	public String getSpecValue() {
		return this.specValue;
	}

	public void setSpecValue(String specValue) {
		this.specValue = specValue;
	}

	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

}