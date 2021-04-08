package shop.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "[Admin]")

public class admin {
	@Id
	@Column(name="AdminName")
	public String username;
	@Column(name="Password")
	public String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public admin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public admin() {
		super();
		// TODO Auto-generated constructor stub
	}

}
