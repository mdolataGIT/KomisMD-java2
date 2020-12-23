package jsf.hockshop.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the company database table.
 * 
 */
@Entity
@Table(name="Company")
@NamedQuery(name="Company.findAll", query="SELECT c FROM Company c")
public class Company implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer idCompany;

	@Column
	private String address;

	@Column
	private byte arch;

	@Column
	private String city;

	@Column
	private String name;

	//bi-directional many-to-one association to Car
	@OneToMany(mappedBy="company")
	private List<Car> cars;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public Company() {
	}

	public Integer getIdCompany() {
		return this.idCompany;
	}

	public void setIdCompany(Integer idCompany) {
		this.idCompany = idCompany;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public byte getArch() {
		return this.arch;
	}

	public void setArch(byte arch) {
		this.arch = arch;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Car> getCars() {
		return this.cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public Car addCar(Car car) {
		getCars().add(car);
		car.setCompany(this);

		return car;
	}

	public Car removeCar(Car car) {
		getCars().remove(car);
		car.setCompany(null);

		return car;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}