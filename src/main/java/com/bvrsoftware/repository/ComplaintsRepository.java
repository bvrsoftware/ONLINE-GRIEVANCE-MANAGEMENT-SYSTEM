package com.bvrsoftware.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bvrsoftware.entites.Categories;
import com.bvrsoftware.entites.ComplaintStatus;
import com.bvrsoftware.entites.Complaints;
import com.bvrsoftware.entites.States;
import com.bvrsoftware.entites.SubCategory;
import com.bvrsoftware.entites.User;

public interface ComplaintsRepository extends JpaRepository<Complaints, Integer> {

	public List<Complaints> findByUser(User user);
	public List<Complaints> findByState(States states);
	public List<Complaints> findByCategory(Categories categories);
	public List<Complaints> findBySubcategory(SubCategory subCategory);
	public List<Complaints> findByStatus(ComplaintStatus complaintStatus);
	
}
