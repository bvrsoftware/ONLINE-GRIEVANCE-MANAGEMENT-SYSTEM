package com.bvrsoftware.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "complaintremark")
public class ComplaintRemark {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String remark;
	private String remarkDate;
	
	@ManyToOne
	private Complaints complaint;
	
	@ManyToOne
	private ComplaintStatus status;
	
	@ManyToOne
	private User user;
}
