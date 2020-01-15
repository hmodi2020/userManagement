package com.usermanagement.model;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.usermanagement.enums.AddressType;

import lombok.Data;

//@Entity
@Data
@SQLDelete(sql = "UPDATE address SET deleted=true WHERE id=?", check = ResultCheckStyle.COUNT)
@Where(clause = "deleted = false")
public class Address extends BaseEntity {
	private String address;
	private String city;
	private String country;
	private String state;
	private Long pincode;
	private AddressType addressType;
	private float latitude;
	private float longitude;
}
