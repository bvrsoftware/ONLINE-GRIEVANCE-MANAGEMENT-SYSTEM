package com.bvrsoftware.service;

import com.bvrsoftware.pagination.UserResponsePagination;
import com.bvrsoftware.payloads.UserDto;

public interface UserService {

	UserDto registerNewUser(UserDto userDto);
	UserDto getUserByEmail(String email);
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto changeUserPassword(UserDto user, Integer userId);
	UserDto getUserById(Integer userId);
	UserResponsePagination getAllUsers(Integer pageNumber, Integer pageSize, String sortBy,String sortDir);
	void deleteUser (Integer userId);
}
