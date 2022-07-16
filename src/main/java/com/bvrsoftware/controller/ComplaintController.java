package com.bvrsoftware.controller;

import java.io.IOException;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bvrsoftware.pagination.ComplaintResponsePagination;
import com.bvrsoftware.payloads.ComplaintsDto;
import com.bvrsoftware.payloads.Constants_Value;
import com.bvrsoftware.service.ComplaintsService;

@RestController
@RequestMapping(value = "/api/complaints")
public class ComplaintController {
	
	@Autowired
	private ComplaintsService complaintsService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping(value = "/")
	public ResponseEntity<ComplaintsDto> createComplaint(@RequestBody ComplaintsDto complaintsDto) throws IOException{
		
		ComplaintsDto create=this.complaintsService.createComplaint(complaintsDto);
		return new ResponseEntity<ComplaintsDto>(create, HttpStatus.CREATED);
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<ComplaintsDto> updateComplaint(@RequestBody ComplaintsDto complaintsDto,@PathVariable Integer id)throws IOException{
		ComplaintsDto complaintsDto2=this.complaintsService.getByComplaintNumber(id);
		complaintsDto2.setComplaintDetails(complaintsDto.getComplaintDetails());
		ComplaintsDto update=this.complaintsService.updateComplaint(complaintsDto2, id);
		return new ResponseEntity<ComplaintsDto>(update, HttpStatus.OK);
	}
	@GetMapping(value = "/{number}")
	public ResponseEntity<ComplaintsDto> getByComplaintNumber(@PathVariable Integer number){
		ComplaintsDto get=this.complaintsService.getByComplaintNumber(number);
		return new ResponseEntity<ComplaintsDto>(get, HttpStatus.OK);		
	}
	@GetMapping(value = "/complaints")
	//@PreAuthorize("hasRole('ADMIN_USER')")
	public ResponseEntity<ComplaintResponsePagination> getAllComplaints(@RequestParam(value="pageNumber", defaultValue =Constants_Value.PAGE_NUMBER, required = false) Integer pageNumber ,
			@RequestParam(value = "pageSize", defaultValue =Constants_Value.PAGE_SIZE ,required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue =Constants_Value.SORT_BY ,required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue =Constants_Value.SORT_DIR ,required = false) String sortDir){
		ComplaintResponsePagination list=this.complaintsService.getAllComplaints(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<ComplaintResponsePagination>(list,HttpStatus.OK);
	}
	@GetMapping(value = "/complaintStatus/{complaintStatusId}")
	//@PreAuthorize("hasRole('ADMIN_USER')")
	public ResponseEntity<List<ComplaintsDto>> getAllComplaintsByStatus(@PathVariable Integer complaintStatusId){
		List<ComplaintsDto> list=this.complaintsService.getAllComplaintsByStatus(complaintStatusId);
		return new ResponseEntity<List<ComplaintsDto>>(list,HttpStatus.OK);
	}
	@GetMapping(value = "/states/{statesId}")
	//@PreAuthorize("hasRole('ADMIN_USER')")
	public ResponseEntity<List<ComplaintsDto>> getAllComplaintsByState(@PathVariable Integer statesId){
		List<ComplaintsDto> list=this.complaintsService.getAllComplaintsByState(statesId);
		return new ResponseEntity<List<ComplaintsDto>>(list,HttpStatus.OK);	
	}
	@GetMapping(value = "/category/{categoriesId}")
	//@PreAuthorize("hasRole('ADMIN_USER')")
	public ResponseEntity<List<ComplaintsDto>> getAllComplaintsByCategory(@PathVariable Integer categoriesId){
		List<ComplaintsDto> list=this.complaintsService.getAllComplaintsByCategory(categoriesId);
		return new ResponseEntity<List<ComplaintsDto>>(list,HttpStatus.OK);
	}
	@GetMapping(value = "/subCategory/{subCategoryId}")
	//@PreAuthorize("hasRole('ADMIN_USER')")
	public ResponseEntity<List<ComplaintsDto>> getAllComplaintsBySubCategory(@PathVariable Integer subCategoryId){
		List<ComplaintsDto> list=this.complaintsService.getAllComplaintsBySubCategory(subCategoryId);
		return new ResponseEntity<List<ComplaintsDto>>(list,HttpStatus.OK);
	}
	@GetMapping(value = "/user/{userId}")
	//@PreAuthorize("hasRole('ADMIN_USER')")
	public ResponseEntity<List<ComplaintsDto>> getAllComplaintsByUser(@PathVariable Integer userId){
		List<ComplaintsDto> list=this.complaintsService.getAllComplaintsByUser(userId);
		return new ResponseEntity<List<ComplaintsDto>>(list,HttpStatus.OK);
	}
	@PutMapping(value = "/status/{cid}/{sid}")
	//@PreAuthorize("hasRole('ADMIN_USER')")
	public ResponseEntity<ComplaintsDto> updateComplaintsStatusByCid(@PathVariable Integer sid,@PathVariable Integer cid){
		ComplaintsDto update=this.complaintsService.updateComplaintStatus(sid, cid);
		return new ResponseEntity<ComplaintsDto>(update,HttpStatus.OK);
	}
}
