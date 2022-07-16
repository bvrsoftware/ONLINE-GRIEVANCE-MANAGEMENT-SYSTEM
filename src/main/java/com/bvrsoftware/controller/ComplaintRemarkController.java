package com.bvrsoftware.controller;

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
import com.bvrsoftware.payloads.ComplaintRemarkDto;
import com.bvrsoftware.service.ComplaintRemarkService;

@RestController
@RequestMapping(value = "/api/complaintRemark")
public class ComplaintRemarkController {

	@Autowired
	private ComplaintRemarkService complaintRemarkService;
	
	@PostMapping(value = "/")
	//@PreAuthorize("hasRole('ADMIN_USER')")
	public ResponseEntity<ComplaintRemarkDto> createComplaintRemark(@Valid @RequestBody ComplaintRemarkDto ComplaintRemarkDto){
		ComplaintRemarkDto create=this.complaintRemarkService.createComplaintRemark(ComplaintRemarkDto);
		return new ResponseEntity<ComplaintRemarkDto>(create,HttpStatus.CREATED);
	}
	@PutMapping(value = "/{id}")
	//@PreAuthorize("hasRole('ADMIN_USER')")
	public ResponseEntity<ComplaintRemarkDto> updateComplaintRemark(@Valid @RequestBody ComplaintRemarkDto ComplaintRemarkDto,@PathVariable Integer id){
		ComplaintRemarkDto update=this.complaintRemarkService.updateComplaintRemark(ComplaintRemarkDto, id);
      return new ResponseEntity<ComplaintRemarkDto>(update,HttpStatus.OK);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<ComplaintRemarkDto> getComplaintRemarkById(@PathVariable Integer id){
		ComplaintRemarkDto get=this.complaintRemarkService.getComplaintRemarkById(id);
		 return new ResponseEntity<ComplaintRemarkDto>(get,HttpStatus.FOUND);	
	}
	@DeleteMapping(value = "/{id}")
	//@PreAuthorize("hasRole('ADMIN_USER')")
	public ResponseEntity<ApiResponse> deleteComplaintRemark (@PathVariable Integer id){
		this.complaintRemarkService.deleteComplaintRemark(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Delete Sucessfully !!",true),HttpStatus.OK);
	}
	
}
