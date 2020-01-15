package com.usermanagement.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@MappedSuperclass
@NoArgsConstructor
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@JsonIgnore
	private boolean deleted;
	@JsonIgnore
	@CreatedDate
	@Column(name="created_at",updatable = false)
	private LocalDateTime createdAt;
	@JsonIgnore
	@LastModifiedDate
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	public BaseEntity(Long id) {
		super();
		this.id = id;
	}
	
	
	
}
