package com.usermanagement.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLUpdate;
import org.hibernate.annotations.Where;

import lombok.Data;

@Entity
@Data
@SQLUpdate(sql = "UPDATE comment SET deleted=true WHERE id=?",check = ResultCheckStyle.COUNT)
@Where(clause = "deleted  = false")
public class Comment extends BaseEntity{
	private String comment;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "flag_id",referencedColumnName = "id")
	private Flag flag;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "user_id",referencedColumnName = "id")
	private User user;
	
}
