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
import org.springframework.stereotype.Service;

import com.bvrsoftware.entites.Categories;
import com.bvrsoftware.entites.ComplaintStatus;
import com.bvrsoftware.entites.Complaints;
import com.bvrsoftware.entites.States;
import com.bvrsoftware.entites.SubCategory;
import com.bvrsoftware.entites.User;
import com.bvrsoftware.exception.ApiException;
import com.bvrsoftware.exception.ResourceNotFoundException;
import com.bvrsoftware.pagination.ComplaintResponsePagination;
import com.bvrsoftware.payloads.ComplaintsDto;
import com.bvrsoftware.payloads.Constants_Value;
import com.bvrsoftware.repository.CategoriesRepository;
import com.bvrsoftware.repository.ComplaintStatusRepository;
import com.bvrsoftware.repository.ComplaintsRepository;
import com.bvrsoftware.repository.StatesRepository;
import com.bvrsoftware.repository.SubCategoryRepository;
import com.bvrsoftware.repository.UserRepository;
import com.bvrsoftware.service.ComplaintsService;

@Service
public class ComplaintsServiceImplement implements ComplaintsService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ComplaintsRepository complaintsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoriesRepository categoriesRepository;
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;
	
	@Autowired
	private ComplaintStatusRepository statusRepository;
	
	@Autowired
	private StatesRepository statesRepository;
	    
	@Override
	public ComplaintsDto createComplaint(ComplaintsDto complaintsDto) {

		Complaints complaints=this.modelMapper.map(complaintsDto, Complaints.class);
        User user=this.userRepository.findByEmail(Constants_Value.getCurrentLoginUsername()).orElseThrow(()->new ResourceNotFoundException("Current you are not login", "username is ="+Constants_Value.getCurrentLoginUsername(),0));
        Categories categories=this.categoriesRepository.findById(complaintsDto.getCategoryId()).orElseThrow(()->new ResourceNotFoundException("Not Found", "Category Id=", complaintsDto.getCategoryId()));
        SubCategory subCategory=this.subCategoryRepository.findById(complaintsDto.getSubcategoryId()).orElseThrow(()->new ResourceNotFoundException("Not Found", "Subcategory Id=", complaintsDto.getSubcategoryId()));
        ComplaintStatus complaintStatus= this.statusRepository.findById(complaintsDto.getStatusId()).orElseThrow(()->new ResourceNotFoundException("Not Found", "Status Id=", complaintsDto.getStatusId()));
        States states= this.statesRepository.findById(complaintsDto.getStateId()).orElseThrow(()->new ResourceNotFoundException("Not Found", "State Id=", complaintsDto.getStateId()));
		complaints.setUser(user);
		complaints.setCategory(categories);
		complaints.setSubcategory(subCategory);
		complaints.setStatus(complaintStatus);
		complaints.setState(states);
		complaints.setRegDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		complaints.setLastUpdationDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		Complaints newComplaints=this.complaintsRepository.save(complaints);
		return this.modelMapper.map(newComplaints, ComplaintsDto.class);
	}

	@Override
	public ComplaintsDto updateComplaint(ComplaintsDto complaintsDto, Integer id) {
		

        User user=this.userRepository.findByEmail(Constants_Value.getCurrentLoginUsername()).orElseThrow(()->new ApiException("Login first account"));
        Complaints complaints=this.complaintsRepository.findById(id).get();
        if(user!=null) {
        	complaints.setComplaintDetails(complaintsDto.getComplaintDetails());
        	complaints.setLastUpdationDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        }
        Complaints updatecomplaints= complaintsRepository.save(complaints);
        return this.modelMapper.map(updatecomplaints, ComplaintsDto.class);
	}

	@Override
	public ComplaintsDto getByComplaintNumber(Integer number) {
		Complaints complaints=this.complaintsRepository.findById(number).get();
		return this.modelMapper.map(complaints, ComplaintsDto.class);
	}

	@Override
	public ComplaintResponsePagination getAllComplaints(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		Pageable p=PageRequest.of(pageNumber, pageSize,sort);   
		Page<Complaints> pageList=this.complaintsRepository.findAll(p);
		List<Complaints> list=pageList.getContent();
		  List<ComplaintsDto> listDto=list.stream().map(l->this.modelMapper.map(l, ComplaintsDto.class)).collect(Collectors.toList());
		ComplaintResponsePagination pagination=new ComplaintResponsePagination();
		pagination.setContent(listDto);
		pagination.setPageNumber(pageList.getNumber());
		pagination.setPageSize(pageList.getSize());
		pagination.setTotalElements(pageList.getTotalElements());
		pagination.setTotalPages(pageList.getTotalPages());
		pagination.setLastpage(pageList.isLast());
		return pagination;
	}

	@Override
	public List<ComplaintsDto> getAllComplaintsByStatus(Integer complaintStatusId) {
	  ComplaintStatus complaintStatus=this.statusRepository.findById(complaintStatusId).orElseThrow(()->new ResourceNotFoundException("Not Found ", "complaintStatusId=", complaintStatusId));
		List<Complaints> complaintsList= this.complaintsRepository.findByStatus(complaintStatus);
		List<ComplaintsDto> complaintsDtoList=complaintsList.stream().map((complaint)->
		this.modelMapper.map(complaint, ComplaintsDto.class)).collect(Collectors.toList());
	  return complaintsDtoList;
	}

	@Override
	public List<ComplaintsDto> getAllComplaintsByState(Integer statesId) {
		States state= this.statesRepository.findById(statesId).orElseThrow(()->new ResourceNotFoundException("Not Found ", "statesId=", statesId));
		List<Complaints> complaintsList= this.complaintsRepository.findByState(state);
		List<ComplaintsDto> complaintsDtoList=complaintsList.stream().map((complaint)->
		this.modelMapper.map(complaint, ComplaintsDto.class)).collect(Collectors.toList());
		return complaintsDtoList;
	}

	@Override
	public List<ComplaintsDto> getAllComplaintsByCategory(Integer categoriesId) {
	    Categories categories=this.categoriesRepository.findById(categoriesId).orElseThrow(()->new ResourceNotFoundException("Not Found ", "categoriesId=", categoriesId));
	    List<Complaints> complaintsList= this.complaintsRepository.findByCategory(categories);
	    List<ComplaintsDto> complaintsDtoList=complaintsList.stream().map((complaint)->
		this.modelMapper.map(complaint, ComplaintsDto.class)).collect(Collectors.toList());
		return complaintsDtoList;
	}

	@Override
	public List<ComplaintsDto> getAllComplaintsBySubCategory(Integer subCategoryId) {
	    SubCategory subCategory=this.subCategoryRepository.findById(subCategoryId).orElseThrow(()->new ResourceNotFoundException("Not Found ", "subCategoryId=", subCategoryId));
		List<Complaints> complaintsList= this.complaintsRepository.findBySubcategory(subCategory);
		List<ComplaintsDto> complaintsDtoList=complaintsList.stream().map((complaint)->
		this.modelMapper.map(complaint, ComplaintsDto.class)).collect(Collectors.toList());
		return complaintsDtoList;
	}

	@Override
	public List<ComplaintsDto> getAllComplaintsByUser(Integer userId) {
		User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("Not Found ", "userId=", userId));
		List<Complaints> complaintsList= this.complaintsRepository.findByUser(user);
		List<ComplaintsDto> complaintsDtoList=complaintsList.stream().map((complaint)->
		this.modelMapper.map(complaint, ComplaintsDto.class)).collect(Collectors.toList());
		return complaintsDtoList;
	}

	@Override
	public ComplaintsDto updateComplaintStatus(Integer statusId, Integer cid) {

        User user=this.userRepository.findByEmail(Constants_Value.getCurrentLoginUsername()).orElseThrow(()->new ApiException("Login first account"));
        Complaints complaints=this.complaintsRepository.findById(cid).get();
        ComplaintStatus status=this.statusRepository.findById(statusId).get();
        if(user!=null) {
        	complaints.setStatus(status);
        	complaints.setLastUpdationDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        }
        Complaints updatecomplaints= complaintsRepository.save(complaints);
        return this.modelMapper.map(updatecomplaints, ComplaintsDto.class);
	}

}
