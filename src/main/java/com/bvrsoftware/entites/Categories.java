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


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Category")
public class Categories {

	    @Id	    
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private String categoryName;
	    private String categoryDescription;
	    private String crateionDate;
	    
	    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
		private List<Complaints> complaint = new ArrayList<>();
	    
	    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
		private List<SubCategory> subcategory = new ArrayList<>();
}
