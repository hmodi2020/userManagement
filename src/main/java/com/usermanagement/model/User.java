package com.usermanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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

	public interface BasicValidation {

	}
	
	public interface AdvanceValidation {

	}
}
