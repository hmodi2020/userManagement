package com.usermanagement.model;

import javax.persistence.Entity;

import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLUpdate;
import org.hibernate.annotations.Where;

import com.usermanagement.enums.BadgesTypeEnum;

import lombok.Data;

@Entity
@Data
@SQLUpdate(sql = "UPDATE badge SET deleted = true WHERE id =?" , check = ResultCheckStyle.COUNT)
@Where(clause = "deleted = false")
public class Badges extends BasicFieldEntitiy {
	private Long score;
	private BadgesTypeEnum badgesType;

}
