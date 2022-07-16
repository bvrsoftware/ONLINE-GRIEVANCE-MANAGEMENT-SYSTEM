package com.bvrsoftware.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bvrsoftware.entites.Categories;
import com.bvrsoftware.entites.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer>{
	
	public List<SubCategory> findByCategory(Categories categories);

}
