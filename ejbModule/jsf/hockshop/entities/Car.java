package jsf.hockshop.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the car database table.
 * 
 */
@Entity
@Table(name="Car")
@NamedQuery(name="Car.findAll", query="SELECT c FROM Car c")
public class Car implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Integer idCar;

	@Column
	private byte accidentFree;

	@Column
	private String brand;

	@Column
	private int capacity;

	@Column
	private String description;

	@Column
	private String firstRegistration;

	@Column
	private String model;

	@Column
	private int power;

	//bi-directional many-to-one association to Company
	@ManyToOne
	private Company company;

	//bi-directional many-to-one association to Coustomer
	@ManyToOne
	private Coustomer coustomer;

	//bi-directional many-to-one association to Photo
	@OneToMany(mappedBy="car")
	private List<Photo> photos;
	
	
	public Car() {
	}

	public  Integer getIdCar() {
		return this.idCar;
	}

	public void setIdCar(Integer idCar) {
		this.idCar = idCar;
	}

	public byte getAccidentFree() {
		return this.accidentFree;
	}

	public void setAccidentFree(byte accidentFree) {
		this.accidentFree = accidentFree;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFirstRegistration() {
		return this.firstRegistration;
	}

	public void setFirstRegistration(String firstRegistration) {
		this.firstRegistration = firstRegistration;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getPower() {
		return this.power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Coustomer getCoustomer() {
		return this.coustomer;
	}

	public void setCoustomer(Coustomer coustomer) {
		this.coustomer = coustomer;
	}

	public List<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public Photo addPhoto(Photo photo) {
		getPhotos().add(photo);
		photo.setCar(this);

		return photo;
	}

	public Photo removePhoto(Photo photo) {
		getPhotos().remove(photo);
		photo.setCar(null);

		return photo;
	}

}