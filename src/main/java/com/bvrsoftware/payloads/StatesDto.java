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
public class StatesDto {

	private int id;
	//@NotEmpty(message = "stateName should not be empty")
	private String stateName;
	private String stateCreationDate;
	private List<ComplaintsDto> complaint = new ArrayList<>();
}
