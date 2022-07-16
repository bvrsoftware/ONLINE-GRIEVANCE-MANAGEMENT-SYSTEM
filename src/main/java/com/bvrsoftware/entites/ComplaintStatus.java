package com.bvrsoftware.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name ="ComplaintStatus" )
public class ComplaintStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String statusName;
	
	@OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
	private List<ComplaintRemark> complaintRemark = new ArrayList<>();
}
