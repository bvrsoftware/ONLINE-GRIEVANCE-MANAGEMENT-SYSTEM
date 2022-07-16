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
import com.bvrsoftware.payloads.ComplaintStatusDto;
import com.bvrsoftware.service.ComplaintStatusService;

@RestController
@RequestMapping(value = "/api/ComplaintStatus")
public class ComplaintStatusController {
	
	@Autowired
	private ComplaintStatusService complaintStatusService;
	
	@PostMapping(value = "/")
	//@PreAuthorize("hasRole('ADMIN_USER')")
	public ResponseEntity<ComplaintStatusDto>  createComplaintStatus(@RequestBody ComplaintStatusDto ComplaintStatusDto){
		ComplaintStatusDto create= this.complaintStatusService.createComplaintStatus(ComplaintStatusDto);
		return new ResponseEntity<ComplaintStatusDto>(create,HttpStatus.CREATED);
	}
	@PutMapping(value = "/{id}")
	//@PreAuthorize("hasRole('ADMIN_USER')")
	public ResponseEntity<ComplaintStatusDto> updateComplaintStatus(@RequestBody ComplaintStatusDto ComplaintStatusDto,@PathVariable Integer id){
		ComplaintStatusDto create= this.complaintStatusService.updateComplaintStatus(ComplaintStatusDto,id);
		return new ResponseEntity<ComplaintStatusDto>(create,HttpStatus.OK);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<ComplaintStatusDto> getComplaintStatusById(@PathVariable Integer id){
		ComplaintStatusDto create= this.complaintStatusService.getComplaintStatusById(id);
		return new ResponseEntity<ComplaintStatusDto>(create,HttpStatus.OK);
	}
	@GetMapping(value = "/")
	public ResponseEntity<List<ComplaintStatusDto>> getAllComplaintStatus(){
		List<ComplaintStatusDto> list=this.complaintStatusService.getAllComplaintStatus();
		return new ResponseEntity<List<ComplaintStatusDto>>(list,HttpStatus.OK);
	}
	@DeleteMapping(value = "/{id}")
	//@PreAuthorize("hasRole('ADMIN_USER')")
	public ResponseEntity<ApiResponse> deleteComplaintStatus (@PathVariable Integer id){
		this.complaintStatusService.deleteComplaintStatus(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Delete Successfully !!",true),HttpStatus.OK);
	}

}
