package com.bvrsoftware.service.implemention;

import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bvrsoftware.entites.Role;
import com.bvrsoftware.entites.User;
import com.bvrsoftware.exception.ApiException;
import com.bvrsoftware.exception.ResourceNotFoundException;
import com.bvrsoftware.pagination.UserResponsePagination;
import com.bvrsoftware.payloads.Constants_Value;
import com.bvrsoftware.payloads.UserDto;
import com.bvrsoftware.repository.RoleRepository;
import com.bvrsoftware.repository.UserRepository;
import com.bvrsoftware.service.UserService;

@Service
public class UserServiceImplement implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired	
	ModelMapper modelMapper;
	
	@Autowired
    private PasswordEncoder pEncoder;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Override
	public UserDto createUser(UserDto userdto) {
		// TODO Auto-generated method stub
		User user=this.dtoToUser(userdto);
		user.setPassword(pEncoder.encode(userdto.getPassword()));
		user.setStatus("Active");
		User savedUser=this.userRepo.save(user);
		user.setRegDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		user.setUpdatationDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		return this.usertoDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->
		     new ResourceNotFoundException( "User", " id=", userId));
		user.setEmail(userDto.getEmail());
		user.setFirstname(userDto.getFirstname());
		user.setLastname(userDto.getLastname());
		user.setAddress(userDto.getAddress());
		user.setContactNo(userDto.getContactNo());
		user.setPinCode(userDto.getPinCode());
		user.setState(userDto.getState());
		user.setImage(userDto.getImage());
		user.setCountry(userDto.getCountry());
         user.setUpdatationDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date())); 
         user.setStatus(userDto.getStatus());
         user.setGender(userDto.getGender());
		//user.setPassword(pEncoder.encode(userDto.getPassword()));
		
		User updatedUser=this.userRepo.save(user);
		 UserDto userToDto = this.usertoDto(updatedUser);
		return userToDto;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() 
				-> new ResourceNotFoundException( "User", " id=", userId));
		return this.usertoDto(user);
	}

	@Override
	public UserResponsePagination getAllUsers(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable p=PageRequest.of(pageNumber, pageSize,sort);
		Page<User> pageList=this.userRepo.findAll(p);
		List <User> list = pageList.getContent();
		List<UserDto> userDto=list.stream().map(l -> this.usertoDto(l)).collect(Collectors.toList());
		UserResponsePagination pagination=new UserResponsePagination();
		pagination.setContent(userDto);
		pagination.setPageNumber(pageList.getNumber());
		pagination.setPageSize(pageList.getSize());
		pagination.setTotalElements(pageList.getTotalElements());
		pagination.setTotalPages(pageList.getTotalPages());
		pagination.setLastpage(pageList.isLast());
		return pagination;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(() 
				-> new ResourceNotFoundException( "User", " id=", userId));
		user.getRoles().removeAll(user.getRoles());
		this.userRepo.delete(user);
	}
  public User dtoToUser(UserDto userDto) {
		User user= this.modelMapper.map(userDto, User.class);
//		user.setId(userDto.getId());
//		user.setAbout(userDto.getAbout());
//		user.setEmail(userDto.getEmail());
//		user.setName(userDto.getName());
//	    user.setPassword(userDto.getPassword());
	
	    return user;
	  }
    public UserDto usertoDto(User user) {
	
	UserDto userDto = this.modelMapper.map(user, UserDto.class);
//	userDto.setAbout(user.getAbout());
//	userDto.setEmail(user.getEmail());
//	userDto.setId(user.getId());
//	userDto.setName(user.getName());
//	userDto.setPassword(user.getPassword());
    return userDto;
   }

	@Override
	public UserDto registerNewUser(UserDto userDto) {
	User user=this.modelMapper.map(userDto, User.class);
	// encode password
	user.setPassword(this.pEncoder.encode(user.getPassword()));
	user.setRegDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
	user.setUpdatationDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
	//Role 
	user.setStatus("Active");
	Role role=this.roleRepo.findById(Constants_Value.NORMAL_USER_ROLE_ID).get();
	user.getRoles().add(role);
	User newUser=this.userRepo.save(user);
		return this.modelMapper.map(newUser, UserDto.class);
	}

	@Override
	public UserDto getUserByEmail(String email) {
		User user = this.userRepo.findByEmail(email).orElseThrow(() 
				-> new ResourceNotFoundException( "Not Found", " email="+email, 0));
		return this.modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto changeUserPassword(UserDto userDto,Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ApiException("user not found"));
	   
	   user.setPassword(pEncoder.encode(userDto.getPassword()));
	   User updatedUser=this.userRepo.save(user);
	   UserDto userToDto = this.usertoDto(updatedUser);
	return userToDto;
	}

}
