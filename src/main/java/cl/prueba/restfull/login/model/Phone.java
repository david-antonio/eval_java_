package cl.prueba.restfull.login.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phone")
public class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "number")
	private String number;

	@Column(name = "citycode")
	private String citycode;

	@Column(name = "countrycode")
	private String countrycode;

	public Phone() {
	}

	public Phone(String title, String citycode, String countrycode) {
		this.number = title;
		this.citycode = citycode;
		this.countrycode = countrycode;
	}

	public String getCitycode() {
		return citycode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	@Override
	public String toString() {
		return "Phone [citycode=" + citycode + ", countrycode=" + countrycode + ", id=" + id + ", number=" + number
				+ "]";
	}

}
