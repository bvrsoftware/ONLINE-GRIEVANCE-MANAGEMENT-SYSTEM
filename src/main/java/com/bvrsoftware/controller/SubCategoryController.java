package com.bvrsoftware.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bvrsoftware.payloads.ApiResponse;
import com.bvrsoftware.payloads.SubCategoryDto;
import com.bvrsoftware.service.SubCategoryService;

@RestController
@RequestMapping(value = "/api/subCategory")
public class SubCategoryController {

	@Autowired
	private SubCategoryService subCategoryService;
	
	@PostMapping(value = "/")
	@PreAuthorize("hasRole('ADMIN_USER')")
	public ResponseEntity<SubCategoryDto> createSubCategory(@Valid @RequestBody SubCategoryDto subCategoryDto){
		SubCategoryDto create=this.subCategoryService.createSubCategory(subCategoryDto);
		return new ResponseEntity<SubCategoryDto>(create,HttpStatus.CREATED);
	}
	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ADMIN_USER')")
	public ResponseEntity<SubCategoryDto> updateSubCategory(@Valid @RequestBody SubCategoryDto subCategoryDto,@PathVariable Integer id){
		SubCategoryDto update=this.subCategoryService.updateSubCategory(subCategoryDto,id);
		return new ResponseEntity<SubCategoryDto>(update,HttpStatus.OK);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<SubCategoryDto> getSubCategoryById(@PathVariable Integer id){
		SubCategoryDto get=this.subCategoryService.getSubCategoryById(id);
		return new ResponseEntity<SubCategoryDto>(get,HttpStatus.OK);

	}
	@GetMapping(value = "/")
	public ResponseEntity<List<SubCategoryDto>> getAllSubCategory(){
		List<SubCategoryDto> list=this.subCategoryService.getAllSubCategory();
		return new ResponseEntity<List<SubCategoryDto>>(list,HttpStatus.OK);
	}
	@GetMapping(value = "/category/{categoryId}")
	public ResponseEntity<List<SubCategoryDto>> getAllSubCategoryByCategory(@PathVariable Integer categoryId){
		List<SubCategoryDto> list=this.subCategoryService.getAllSubCategoryByCategory(categoryId);
		return new ResponseEntity<List<SubCategoryDto>>(list,HttpStatus.OK);
	}
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ADMIN_USER')")
	public ResponseEntity<ApiResponse> deleteSubCategory (@PathVariable Integer id){
		  this.subCategoryService.deleteSubCategory(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Delete Successfully !!",true),HttpStatus.OK); 
	}
}
