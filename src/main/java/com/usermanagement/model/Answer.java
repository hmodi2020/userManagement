package com.usermanagement.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Data;

@Entity
@Data
@SQLDelete(sql="UPDATE answer SET deleted = true WHERE id =?" ,check = ResultCheckStyle.COUNT)
@Where(clause = "deleted=false")
public class Answer extends BaseEntity{
	private String answer;
	private boolean accepted;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name ="answer_comments" ,joinColumns = @JoinColumn(name="answer_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name="comment_id",referencedColumnName = "id"))
	private List<Comment> comments;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "user_id",referencedColumnName = "id")
	private User user;
}
