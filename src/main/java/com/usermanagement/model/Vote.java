package com.usermanagement.model;

import javax.persistence.Entity;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Data;

@Entity
@Data
@SQLDelete(sql ="UPDATE vote SET deleted = true WHERE id = ?" , check = ResultCheckStyle.COUNT)
@Where(clause = "deleted = false")
public class Vote extends BaseEntity{
	private Long upVote;
	private Long downVote;
}