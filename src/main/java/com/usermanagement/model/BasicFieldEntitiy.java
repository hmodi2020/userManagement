package com.usermanagement.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@Data
@MappedSuperclass
public class BasicFieldEntitiy extends BaseEntity {
	private String title;
	private String description;
}
