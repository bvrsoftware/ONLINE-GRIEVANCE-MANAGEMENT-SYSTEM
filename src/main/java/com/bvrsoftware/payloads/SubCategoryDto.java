package com.bvrsoftware.payloads;

import java.util.ArrayList;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryDto {

	private int id;
	//@NotEmpty(message = "categoryId should not be blank or Empty")
	//@NotBlank
	private int categoryId;
	//@NotEmpty(message = "Sub Category Name should not be Empty")
	private String subCategoryName;
	private String creationDate;
	//private CategoriesDto category;
	private List<ComplaintsDto> complaint = new ArrayList<>();
}
