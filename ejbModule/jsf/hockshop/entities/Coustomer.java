package jsf.hockshop.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the coustomer database table.
 * 
 */
@Entity
@NamedQuery(name="Coustomer.findAll", query="SELECT c FROM Coustomer c")
public class Coustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idCoustomer;

	private String coustomerName;

	private String coustomerSurname;

	@Temporal(TemporalType.DATE)
	private Date purchaseDate;

	//bi-directional many-to-one association to Car
	@ManyToOne
	private Car car;

	public Coustomer() {
	}

	public Integer getIdCoustomer() {
		return this.idCoustomer;
	}

	public void setIdCoustomer(Integer idCoustomer) {
		this.idCoustomer = idCoustomer;
	}

	public String getCoustomerName() {
		return this.coustomerName;
	}

	public void setCoustomerName(String coustomerName) {
		this.coustomerName = coustomerName;
	}

	public String getCoustomerSurname() {
		return this.coustomerSurname;
	}

	public void setCoustomerSurname(String coustomerSurname) {
		this.coustomerSurname = coustomerSurname;
	}

	public Date getPurchaseDate() {
		return this.purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

}