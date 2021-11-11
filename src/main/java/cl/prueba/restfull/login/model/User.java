package cl.prueba.restfull.login.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "user")
public class User {
	
	@JsonInclude(Include.NON_DEFAULT)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "name")
	private String name;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "email")
	private String email;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "password")
	private String password;

	@JsonInclude(Include.NON_EMPTY)
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Phone> phone;
	
	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;
	
	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified")
	private Date modified;

	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastlogin")
	private Date lastlogin;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "isactive")
	private Boolean isactive;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "uuid")
	private String uuid;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}


	public Collection<Phone> getPhone() {
		return phone;
	}

	public void setPhone(Collection<Phone> phone) {
		this.phone = phone;
	}

	public Date getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

	public Boolean getIsactive() {
		return isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public User() {
	}

	public User(String name, String email, String password, Collection<Phone> phone, Date created,
			Date modified, Date lastlogin) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.created = created;
		this.modified = modified;
		this.lastlogin = lastlogin;
	}

	@Override
	public String toString() {
		return "User [created=" + created + ", email=" + email + ", id=" + id + ", isactive=" + isactive
				+ ", lastlogin=" + lastlogin + ", modified=" + modified + ", name=" + name + ", password=" + password
				+ ", phones=" + phone + ", uuid=" + uuid + "]";
	}
	
}