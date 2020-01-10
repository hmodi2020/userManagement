package com.usermanagement.model;

import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {
	@JsonIgnore
	private boolean deleted;
}
