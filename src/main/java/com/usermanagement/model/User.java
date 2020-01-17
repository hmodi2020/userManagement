package com.usermanagement.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
@SQLDelete(sql ="UPDATE user SET deleted = true WHERE id = ?" , check = ResultCheckStyle.COUNT)
//@Where(clause = "deleted = false")
public class User extends BaseEntity {
	@Column(unique = true)
	private String email;
	@NotBlank(message = "Display name can not be blank", groups = { BasicValidation.class, AdvanceValidation.class })
	@JsonProperty("display_name")
	private String displayName;
	@JsonProperty("mobile_number")
	@NotBlank(message = "Mobile number can not be blank", groups = { BasicValidation.class, AdvanceValidation.class })
	private String mobileNumber;
	private String socialMediaId;
	private String password;
	private Long rank;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinTable(name = "user_role",joinColumns =@JoinColumn(name = "user_id" ,referencedColumnName="id"), inverseJoinColumns = @JoinColumn(name ="role_id" ,referencedColumnName="id"))
	private List<Role> roles = new ArrayList() ;
	//@JsonIgnore
	// @JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_posts",joinColumns =@JoinColumn(name="user_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name ="post_id" ,referencedColumnName = "id"))
	private List<Post> posts;
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name ="user_badges" ,joinColumns  =@JoinColumn(name="user_id",referencedColumnName = "id"),inverseJoinColumns = @JoinColumn(name="badges_id",referencedColumnName = "id"))
	private List<Badges> badges;
	public User () {
		
	}
	
	
	public User(User user) {
		super(user.getId());
		this.email =user.getEmail();
		this.displayName = user.getDisplayName();
		this.mobileNumber =user.getMobileNumber();
		this.password = user.getPassword();
		this.socialMediaId=user.getSocialMediaId();
		this.roles = user.getRoles();
		this.rank=user.getRank();
		
	}

	public interface BasicValidation {

	}
	
	public interface AdvanceValidation {

	}
}
