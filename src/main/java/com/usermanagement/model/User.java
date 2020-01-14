package com.usermanagement.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
@SQLDelete(sql ="UPDATE user SET deleted = true WHERE id = ?" , check = ResultCheckStyle.COUNT)
@Where(clause = "deleted = false")
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private String email;
	@NotBlank(message = "First name can not be blank", groups = { BasicValidation.class, AdvanceValidation.class })
	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("last_name")
	@NotBlank(message = "Last name can not be blank", groups = { BasicValidation.class })
	private String lastName;
	@JsonProperty("mobile_number")
	@NotBlank(message = "Mobile number can not be blank", groups = { BasicValidation.class, AdvanceValidation.class })
	private String mobileNumber;
	private String address;
	private byte age;
	@NotBlank(message = "Technology can not be blank", groups = { BasicValidation.class })
	private String technology;
	private String password;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",joinColumns =@JoinColumn(name = "user_id" ,referencedColumnName="id"), inverseJoinColumns = @JoinColumn(name ="role_id" ,referencedColumnName="id"))
	private List<Role> roles = new ArrayList() ;

	public User () {
		
	}
	
	
	public User(User user) {
		this.id = user.getId();
		this.email =user.getEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.mobileNumber =user.getMobileNumber();
		this.address = user.getAddress();
		this.age = user.getAge();
		this.technology = user.getTechnology();
		this.password = user.getPassword();
		this.roles = user.getRoles();
	}

	public interface BasicValidation {

	}
	
	public interface AdvanceValidation {

	}
}
