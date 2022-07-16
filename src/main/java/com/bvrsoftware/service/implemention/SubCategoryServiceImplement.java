package com.bvrsoftware.service.implemention;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvrsoftware.entites.Categories;
import com.bvrsoftware.entites.SubCategory;
import com.bvrsoftware.exception.ResourceNotFoundException;
import com.bvrsoftware.payloads.SubCategoryDto;
import com.bvrsoftware.repository.CategoriesRepository;
import com.bvrsoftware.repository.SubCategoryRepository;
import com.bvrsoftware.service.SubCategoryService;

@Service
public class SubCategoryServiceImplement implements SubCategoryService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	@Autowired
	private CategoriesRepository categoriesRepository;
	
	@Override
	public SubCategoryDto createSubCategory(SubCategoryDto subCategoryDto) {
		Categories categories=this.categoriesRepository.findById(subCategoryDto.getCategoryId()).orElseThrow(()->new ResourceNotFoundException("please give ", "Category Id =",subCategoryDto.getCategoryId()));
		SubCategory subCategory=this.modelMapper.map(subCategoryDto, SubCategory.class);
		subCategory.setCategory(categories);
	    subCategory.setCreationDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
	    SubCategory newSubCategory=this.subCategoryRepository.save(subCategory);
		return this.modelMapper.map(newSubCategory, SubCategoryDto.class);
	}

	@Override
	public SubCategoryDto updateSubCategory(SubCategoryDto subCategoryDto, Integer id) {
		SubCategory subCategory=this.subCategoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not Found", "subCategory Id=", id));
		subCategory.setSubCategoryName(subCategoryDto.getSubCategoryName());
		Categories categories=this.categoriesRepository.findById(subCategoryDto.getCategoryId()).orElseThrow(()->new ResourceNotFoundException("please give ", "Category Id =",subCategoryDto.getCategoryId()));
		subCategory.setCategory(categories);
		 SubCategory newSubCategory=this.subCategoryRepository.save(subCategory);
		return this.modelMapper.map(newSubCategory, SubCategoryDto.class);
	}

	@Override
	public SubCategoryDto getSubCategoryById(Integer id) {
		SubCategory subCategory=this.subCategoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not Found", "subCategoryId=", id));

		return this.modelMapper.map(subCategory, SubCategoryDto.class);
	}

	@Override
	public List<SubCategoryDto> getAllSubCategory() {
		List<SubCategory> subCategoryList=this.subCategoryRepository.findAll();
		List<SubCategoryDto> subCategoryDtoList=subCategoryList.stream().map(subcategory->this.modelMapper.map(subcategory, SubCategoryDto.class)).collect(Collectors.toList());
		return subCategoryDtoList;
	}

	@Override
	public List<SubCategoryDto> getAllSubCategoryByCategory(Integer categoryId) {
		Categories categories=this.categoriesRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Not Found", "categoryId=", categoryId));
		List<SubCategory> subCategoryList=this.subCategoryRepository.findByCategory(categories);
		List<SubCategoryDto> subCategoryDtoList=subCategoryList.stream().map(subcategory->this.modelMapper.map(subcategory, SubCategoryDto.class)).collect(Collectors.toList());
		return subCategoryDtoList;
	}

	@Override
	public void deleteSubCategory(Integer id) {
		SubCategory subCategory=this.subCategoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not Found", "subCategoryId=", id));
		this.subCategoryRepository.delete(subCategory);
	}

}
