package com.bvrsoftware.entites;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "Complaints")
public class Complaints {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	private String complaintDetails;
    private String regDate;
    private String lastUpdationDate;
    
    @OneToOne
    @JoinColumn(name = "status_id")
    private ComplaintStatus status;
    
    @ManyToOne
    private User user;
    
    @ManyToOne
    private Categories category;
    
    @ManyToOne
    private SubCategory subcategory;
    
    @ManyToOne
    private States state;
    
    @OneToMany(mappedBy = "complaint", cascade = CascadeType.ALL)
	private List<ComplaintRemark> complaintRemark = new ArrayList<>();
}
