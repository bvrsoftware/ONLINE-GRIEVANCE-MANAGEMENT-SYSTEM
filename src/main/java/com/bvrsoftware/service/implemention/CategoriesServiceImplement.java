package com.bvrsoftware.service.implemention;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvrsoftware.entites.Categories;
import com.bvrsoftware.exception.ResourceNotFoundException;
import com.bvrsoftware.payloads.CategoriesDto;
import com.bvrsoftware.repository.CategoriesRepository;
import com.bvrsoftware.service.CategoriesService;

@Service
public class CategoriesServiceImplement implements CategoriesService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CategoriesRepository categoriesRepository;
	
	@Override
	public CategoriesDto createCategories(CategoriesDto categoriesDto) {
		Categories categories=this.modelMapper.map(categoriesDto, Categories.class);
		categories.setCrateionDate(new SimpleDateFormat("dd/mm/yyyy").format(new Date()));
		Categories newCategories=this.categoriesRepository.save(categories);
		return this.modelMapper.map(newCategories, CategoriesDto.class);
	}

	@Override
	public CategoriesDto updateCategories(CategoriesDto categoriesDto, Integer id) {
		Categories categories=this.categoriesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not Found", "categoryId=", id));
		categories.setCategoryName(categoriesDto.getCategoryName());
		categories.setCategoryDescription(categoriesDto.getCategoryDescription());
		Categories updateCategories=this.categoriesRepository.save(categories);
		return this.modelMapper.map(updateCategories, CategoriesDto.class);
	}

	@Override
	public CategoriesDto getCategoriesById(Integer id) {
		Categories categories=this.categoriesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not Found", "categoryId=", id));
		return this.modelMapper.map(categories, CategoriesDto.class);
	}

	@Override
	public List<CategoriesDto> getAllCategories() {
		List<Categories> categoriesList=this.categoriesRepository.findAll();
		List<CategoriesDto> categoriesDtoList=categoriesList.stream().map(category->this.modelMapper.map(category, CategoriesDto.class)).collect(Collectors.toList());
		return categoriesDtoList;
	}

	@Override
	public void deleteCategories(Integer id) {
		Categories categories=this.categoriesRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not Found", "categoryId=", id));
	    this.categoriesRepository.delete(categories);
	}
}
