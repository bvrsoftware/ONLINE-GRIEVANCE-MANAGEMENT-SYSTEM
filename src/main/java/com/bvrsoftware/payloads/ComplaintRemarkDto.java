package com.bvrsoftware.payloads;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ComplaintRemarkDto {

    private int id;
   // @NotEmpty(message = "statusId most not be null or empty")
   // @NotBlank(message = "statusId most not be blank")
    private int statusId;
  //  @NotEmpty(message = "complaintId most not be null or empty")
   // @NotBlank(message = "complaintId most not be blank")
    private int complaintId;
   // @NotEmpty(message = "remark most not be null or empty")
   // @NotBlank(message = "remark most not be blank")
  //  @Min(value =20, message = "remark should be minmum 20 charecters ")
	private String remark;
	private String remarkDate;
}
