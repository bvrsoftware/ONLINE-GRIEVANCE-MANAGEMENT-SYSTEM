package com.bvrsoftware.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvrsoftware.payloads.ApiResponse;
import com.bvrsoftware.payloads.CategoriesDto;
import com.bvrsoftware.service.CategoriesService;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryConttroller {
	
	@Autowired
	private CategoriesService categoriesService;
	
	@PostMapping(value = "/")
	//@PreAuthorize("hasRole('ADMIN_USER')")
	public ResponseEntity<CategoriesDto> createCategories(@Valid @RequestBody CategoriesDto categoriesDto) {
		CategoriesDto create= this.categoriesService.createCategories(categoriesDto);
		return new ResponseEntity<>(create, HttpStatus.CREATED);
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<CategoriesDto> updateCategories(@Valid @RequestBody CategoriesDto categoriesDto,@PathVariable Integer id) {
		CategoriesDto update= this.categoriesService.updateCategories(categoriesDto, id);
		return new ResponseEntity<>(update, HttpStatus.OK);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoriesDto> getCategoriesById(@PathVariable Integer id) {
		CategoriesDto get= this.categoriesService.getCategoriesById(id);
		return new ResponseEntity<>(get, HttpStatus.OK);
	}
	@GetMapping(value = "/")
	//@PreAuthorize("hasRole('ADMIN_USER')")
	public ResponseEntity<List<CategoriesDto>> getAllCategories(){
		List<CategoriesDto> getAll=this.categoriesService.getAllCategories();
		return new ResponseEntity<>(getAll,HttpStatus.OK);		
	}
	@DeleteMapping(value = "/{id}")
	//@PreAuthorize("hasRole('ADMIN_USER')")
	public ResponseEntity<ApiResponse> deleteCategories (@PathVariable Integer id) {
		this.categoriesService.deleteCategories(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted successfully", true), HttpStatus.OK);	
	}

}
