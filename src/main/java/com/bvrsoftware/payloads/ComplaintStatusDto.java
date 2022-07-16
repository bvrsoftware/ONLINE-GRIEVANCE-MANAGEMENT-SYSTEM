package com.bvrsoftware.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintStatusDto {

	private int id;
	//@NotEmpty(message = "statusName should not be Empty")
	//@NotBlank(message = "statusName should not be blank")
	private String statusName;
}
