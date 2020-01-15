package com.usermanagement.model;

import javax.persistence.Entity;

import com.usermanagement.enums.BadgesTypeEnum;

import lombok.Data;

@Entity
@Data
public class Badges extends BasicFieldEntitiy{
private Long score;
private BadgesTypeEnum badgesType;


}
