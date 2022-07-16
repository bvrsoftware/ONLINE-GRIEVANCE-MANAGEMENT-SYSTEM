package com.bvrsoftware.controller;





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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bvrsoftware.pagination.UserResponsePagination;
import com.bvrsoftware.payloads.ApiResponse;
import com.bvrsoftware.payloads.Constants_Value;
import com.bvrsoftware.payloads.UserDto;
import com.bvrsoftware.service.UserService;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	
//	change password
	@PostMapping("/change-password/{id}")
	public ResponseEntity<UserDto> changePassword(@RequestBody UserDto userDto,@PathVariable Integer id)
	{
		UserDto updatedUser  = this.userService.changeUserPassword(userDto,id);
		return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
	}
	
	
//	update USER
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer id)
	{
		UserDto updatedUser  = this.userService.updateUser(userDto, id);
		return new ResponseEntity<UserDto>(updatedUser, HttpStatus.OK);
	}
	
	
//	delete User
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id)
	{
		this.userService.deleteUser( id);
//		return ResponseEntity.ok(Map.of("Message", "User Deleted successfully"));
		return new ResponseEntity<ApiResponse>(new ApiResponse
					("Deleted successfully", true), HttpStatus.OK);	
	}
	
	
//	Get users
	@GetMapping("/users")
	public ResponseEntity<UserResponsePagination> getAllUsers(@RequestParam(value="pageNumber", defaultValue =Constants_Value.PAGE_NUMBER, required = false) Integer pageNumber ,
			@RequestParam(value = "pageSize", defaultValue =Constants_Value.PAGE_SIZE ,required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue =Constants_Value.SORT_BY ,required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue =Constants_Value.SORT_DIR ,required = false) String sortDir){
		    UserResponsePagination pagination= this.userService.getAllUsers(pageNumber,pageSize,sortBy,sortDir);
		return ResponseEntity.ok(pagination);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer id){
		return ResponseEntity.ok(this.userService.getUserById(id));
	}
	@GetMapping("/current-user/{email}")
	public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){
		return ResponseEntity.ok(this.userService.getUserByEmail(email));
	}

}
