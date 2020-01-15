package com.usermanagement.model;

import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public class BasicFieldEntitiy extends BaseEntity {
	private String title;
	private String description;
}
