package com.usermanagement.modle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.usermanagement.service.AdvanceValidation;
import com.usermanagement.service.BasicValidation;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class User {
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


}
