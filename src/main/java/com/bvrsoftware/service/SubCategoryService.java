package com.bvrsoftware.service;

import java.util.List;

import com.bvrsoftware.payloads.SubCategoryDto;

public interface SubCategoryService {

	SubCategoryDto createSubCategory(SubCategoryDto subCategoryDto);
	SubCategoryDto updateSubCategory(SubCategoryDto subCategoryDto,Integer id);
	SubCategoryDto getSubCategoryById(Integer id);
	List<SubCategoryDto> getAllSubCategory();
	List<SubCategoryDto> getAllSubCategoryByCategory(Integer categoryId);
	void deleteSubCategory (Integer id);
}
