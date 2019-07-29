package com.namimono.securitylogin.demo.business.testA.bean;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name ="dept" )
public class Dept {


   	@Column(name = "id" )
	@Id
	private String id;

   	@Column(name = "name" )
	private String name;

	@ElementCollection
   	List<Emp> empList;

}
