package jsf.hockshop.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the coustomer database table.
 * 
 */
@Entity
@Table(name="Coustomer")
@NamedQuery(name="Coustomer.findAll", query="SELECT c  FROM Coustomer c")
public class Coustomer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique = true, nullable= false)
	private Integer idCoustomer;
	
	@Column
	private String coustomerName;
	
	@Column
	private String coustomerSurname;

	@Temporal(TemporalType.DATE)
	private Date purchaseDate;

	//bi-directional many-to-one association to Car
	@OneToMany(mappedBy="coustomer")
	private List<Car> cars;

	//bi-directional many-to-one association to Specyfication
	@OneToMany(mappedBy="coustomer")
	private List<Specyfication> specyfications;

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

	public List<Car> getCars() {
		return this.cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public Car addCar(Car car) {
		getCars().add(car);
		car.setCoustomer(this);

		return car;
	}

	public Car removeCar(Car car) {
		getCars().remove(car);
		car.setCoustomer(null);

		return car;
	}

	public List<Specyfication> getSpecyfications() {
		return this.specyfications;
	}

	public void setSpecyfications(List<Specyfication> specyfications) {
		this.specyfications = specyfications;
	}

	public Specyfication addSpecyfication(Specyfication specyfication) {
		getSpecyfications().add(specyfication);
		specyfication.setCoustomer(this);

		return specyfication;
	}

	public Specyfication removeSpecyfication(Specyfication specyfication) {
		getSpecyfications().remove(specyfication);
		specyfication.setCoustomer(null);

		return specyfication;
	}

}