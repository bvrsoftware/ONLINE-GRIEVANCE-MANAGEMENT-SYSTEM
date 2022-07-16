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
public class CategoriesDto {

	    private int id;
	    
	   // @NotEmpty(message = "categoryName should not be null or empty")
	    private String categoryName;
	    private String categoryDescription;
	    private String crateionDate;
	    private List<ComplaintsDto> complaint = new ArrayList<>();
	    private List<SubCategoryDto> subcategory = new ArrayList<>();
}
