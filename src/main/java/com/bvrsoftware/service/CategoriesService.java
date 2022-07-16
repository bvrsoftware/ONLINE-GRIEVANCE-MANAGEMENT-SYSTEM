package com.bvrsoftware.service;

import java.util.List;

import com.bvrsoftware.payloads.CategoriesDto;

public interface CategoriesService {

	CategoriesDto createCategories(CategoriesDto categoriesDto);
	CategoriesDto updateCategories(CategoriesDto categoriesDto,Integer id);
	CategoriesDto getCategoriesById(Integer id);
	List<CategoriesDto> getAllCategories();
	void deleteCategories (Integer id);
}
