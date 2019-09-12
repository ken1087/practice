package com.cos.pratice.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable //Primaryのオブジェクトを作るためのAnnotation
@NoArgsConstructor		
@AllArgsConstructor	//コンストラクタ
@Data	//Getter, Setter
public class UsersPk implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column( name = "num",nullable=false, length=10)
	private int num;
	@Column( name = "userid",nullable=false, length=10)
	private String userid;
	@Column( name = "stdnum",nullable=false, length=10)
	private int stdnum;
	@Column( name = "mynum",nullable=false, length=10)
	private int mynum;
	@Column( name = "phone",nullable=false, length=10)
	private int phone;

}

