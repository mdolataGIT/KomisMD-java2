package jsf.hockshop.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the photo database table.
 * 
 */
@Entity
@NamedQuery(name="Photo.findAll", query="SELECT p FROM Photo p")
public class Photo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPhoto;

	private String photoDescription;

	private String url;

	//bi-directional many-to-one association to Car
	@ManyToOne
	private Car car;

	public Photo() {
	}

	public int getIdPhoto() {
		return this.idPhoto;
	}

	public void setIdPhoto(int idPhoto) {
		this.idPhoto = idPhoto;
	}

	public String getPhotoDescription() {
		return this.photoDescription;
	}

	public void setPhotoDescription(String photoDescription) {
		this.photoDescription = photoDescription;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Car getCar() {
		return this.car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

}