package com.namimono.securitylogin.demo.business.testA.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name ="emp" )
public class Emp {


   	@Column(name = "id" )
	@Id
	private String id;

   	@Column(name = "name" )
	private String name;

   	@Column(name = "dept_id" )
	private String deptId;

}
