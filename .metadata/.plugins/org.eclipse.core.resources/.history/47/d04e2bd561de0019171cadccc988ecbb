package com.cos.pratice.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users {
	
	@EmbeddedId
	private UsersPk userpk;
	
	// Primary Key以外の項目
	@Column(length=10)	
	private String password;
	@Column(length=10)
	private String name;
	@Column(length=40)
	private String title;
	@Column(length=50)
	private String content;
	@Column(length=60)
	private String addr;
	@Column(length=20)
	private String email;
	@Column(length=10)
	private String gender;
	@Column(length=10)
	private String country;
	@Column
	@CreationTimestamp    //自動カウンター
	private LocalDate createDate;
	@Column
	@CreationTimestamp    //自動カウンター
	private LocalDate updateDate;
}
