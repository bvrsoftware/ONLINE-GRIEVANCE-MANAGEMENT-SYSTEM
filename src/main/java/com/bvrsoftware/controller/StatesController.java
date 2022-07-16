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
import com.bvrsoftware.payloads.StatesDto;
import com.bvrsoftware.service.StatesService;

@RestController
@RequestMapping(value = "/api/state")
public class StatesController {

	@Autowired
	private StatesService service;
	
	// create state
	@PostMapping(value = "/")
	public ResponseEntity<StatesDto> createStates(@Valid @RequestBody StatesDto statesDto){
		StatesDto statesDto2=this.service.createStates(statesDto);
		return new ResponseEntity<>(statesDto2, HttpStatus.CREATED);
	}
   // Update state
	@PutMapping(value = "/{stateId}")
	public ResponseEntity<StatesDto> upadteStates(@Valid @RequestBody StatesDto statesDto,@PathVariable("stateId") Integer stateId){
		StatesDto statesDto2=this.service.updateStates(statesDto,stateId);
		return new ResponseEntity<>(statesDto2, HttpStatus.OK);
	}
   // get By state ID
	@GetMapping(value = "/{stateId}")
	public ResponseEntity<StatesDto> getByStateId(@PathVariable("stateId") Integer stateId){
		StatesDto statesDto2=this.service.getByStateId(stateId);
		return new ResponseEntity<>(statesDto2, HttpStatus.OK);
	}
	// delete By state ID
	@DeleteMapping(value = "/{stateId}")
	public ResponseEntity<ApiResponse> deleteByStateId(@PathVariable("stateId") Integer stateId){
		  this.service.deleteState(stateId);
		  return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted successfully", true), HttpStatus.OK);	
	}
    // All States
	@GetMapping(value = "/")
	public ResponseEntity<List<StatesDto>> getAllState(){
	  List<StatesDto> statesDto2=this.service.getAllStates();
	   return new ResponseEntity<>(statesDto2, HttpStatus.OK);
		}
}
