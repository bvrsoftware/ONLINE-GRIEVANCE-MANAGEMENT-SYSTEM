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
public class ComplaintsDto {

	    private int id;
	   // @NotEmpty(message = "categoryId should not be Empty")
	    //@NotBlank(message = "categoryId should not be blank")
	    //@NotNull(message = "categoryId should not be Null")
	    private int categoryId;
	  //  @NotEmpty(message = "subcategoryId should not be Empty")
	  //  @NotBlank(message = "subcategoryId should not be blank")
	  //  @NotNull(message = "subcategoryId should not be Null")
	    private int subcategoryId;
	 //   @NotEmpty(message = "stateId should not be Empty")
	 //   @NotBlank(message = "stateId should not be blank")
	 //   @NotNull(message = "stateId should not be Null")
	    private int stateId;
	 //   @NotEmpty(message = "statusId should not be Empty")
	  //  @NotBlank(message = "statusId should not be blank")
	  //  @NotNull(message = "statusId should not be Null")
	    private int statusId;
	  //  @NotEmpty(message = "complaintDetails should not be Empty")
	  //  @NotBlank(message = "complaintDetails should not be blank")
	  //  @NotNull(message = "complaintDetails should not be Null")
	  //  @Min(value = 50, message =  "complaintDetails minimam should be 50 charecters")
		private String complaintDetails;
	    
	    private String regDate;
	    private String lastUpdationDate;
	    
	    private List<ComplaintRemarkDto> complaintRemark = new ArrayList<>();
}
