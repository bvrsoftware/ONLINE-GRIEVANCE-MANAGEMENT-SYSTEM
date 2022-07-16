package com.bvrsoftware.payloads;

import java.util.ArrayList;



import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private int id;

	//@NotEmpty(message = "Firstname should not be empty")
    private String firstname;
    private String lastname;
   // @Email(message = "Email not be formatted eg abc@xxx.com")
   // @NotEmpty(message = "Email should not be empty")
    private String email;
    //@NotEmpty(message = "password should not be empty")
   // @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",message = "password must contain at least one lowercase character, one uppercase character, one digit, one special character, and a length between 8 to 20")
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    //@NotEmpty(message = "Contact number should not be empty")
  //  @Size(min = 10,message = "contact number should be 10 digits")
    //@Pattern(regexp = "/^(\\+\\d{1,3}[- ]?)?\\d{10}$/")
    private String contactNo;
    private String address;
    private String gender;
    private String state;
    private String dob;
    private String country;
    private String pinCode;
    private String image;
    private String regDate;
    private String updatationDate;
    private String status;
    private Set<RoleDto> roles = new HashSet<>();
    private List<ComplaintRemarkDto> complaintRemark = new ArrayList<>();
    private List<ComplaintsDto> complaint = new ArrayList<>();
}
