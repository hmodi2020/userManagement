package com.usermanagement.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Data;

@Entity
@Data
@SQLDelete(sql ="UPDATE post SET deleted = true WHERE id = ?" , check = ResultCheckStyle.COUNT)
@Where(clause = "deleted = false")
public class Post extends BasicFieldEntitiy{
	private Long views;
	@OneToOne
	private User user;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name= "tag_id",referencedColumnName = "id")
	private List<Tag> tags;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "flag_id",referencedColumnName = "id")
	private Flag flags;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="vote_id")
	private Vote vote;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name ="post_answers" ,joinColumns = @JoinColumn(name="post_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name="answer_id",referencedColumnName = "id"))
	private List<Answer> answers;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name ="post_comments" ,joinColumns = @JoinColumn(name="post_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name="comment_id",referencedColumnName = "id"))
	private List<Comment> comments;
}
