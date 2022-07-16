
package com.bvrsoftware.service.implemention;

import java.text.SimpleDateFormat;




import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvrsoftware.entites.ComplaintRemark;
import com.bvrsoftware.entites.ComplaintStatus;
import com.bvrsoftware.entites.Complaints;
import com.bvrsoftware.entites.User;
import com.bvrsoftware.exception.ResourceNotFoundException;
import com.bvrsoftware.payloads.ApiResponse;
import com.bvrsoftware.payloads.ComplaintRemarkDto;
import com.bvrsoftware.payloads.Constants_Value;
import com.bvrsoftware.repository.ComplaintRemarkRepository;
import com.bvrsoftware.repository.ComplaintStatusRepository;
import com.bvrsoftware.repository.ComplaintsRepository;
import com.bvrsoftware.repository.UserRepository;
import com.bvrsoftware.service.ComplaintRemarkService;

@Service
public class ComplaintRemarkServiceImpletement implements ComplaintRemarkService{

	@Autowired	
	private ModelMapper modelMapper;
	
	@Autowired
	private ComplaintRemarkRepository complaintRemarkRepository;
	
	@Autowired
	private ComplaintStatusRepository complaintStatusRepository;
	
	@Autowired
	private ComplaintsRepository complaintsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public ComplaintRemarkDto createComplaintRemark(ComplaintRemarkDto ComplaintRemarkDto) {
				
		ComplaintStatus complaintStatus=this.complaintStatusRepository.findById(ComplaintRemarkDto.getStatusId()).orElseThrow(()->new ResourceNotFoundException("Not Found", "Status id=", ComplaintRemarkDto.getStatusId()));
		Complaints complaints= this.complaintsRepository.findById(ComplaintRemarkDto.getComplaintId()).orElseThrow(()->new ResourceNotFoundException("Not Found", "Complaint Number=", ComplaintRemarkDto.getComplaintId()));
		User user=this.userRepository.findByEmail(Constants_Value.getCurrentLoginUsername()).orElseThrow(()->new ResourceNotFoundException("Current you are not login", "username is ="+Constants_Value.getCurrentLoginUsername(),0));
		ComplaintRemark complaintRemark=this.modelMapper.map(ComplaintRemarkDto, ComplaintRemark.class);
		
		complaintRemark.setStatus(complaintStatus);
		complaintRemark.setComplaint(complaints);
		complaintRemark.setUser(user);
		complaintRemark.setRemarkDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		ComplaintRemark newComplaintRemark=this.complaintRemarkRepository.save(complaintRemark);
		return this.modelMapper.map(newComplaintRemark, ComplaintRemarkDto.class);
	}

	@Override
	public ComplaintRemarkDto updateComplaintRemark(ComplaintRemarkDto ComplaintRemarkDto, Integer id) {
				
		ComplaintRemark complaintRemark= this.complaintRemarkRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Complaint Remark not Found with this", "id", id));
		User user=this.userRepository.findByEmail(Constants_Value.getCurrentLoginUsername()).orElseThrow(()->new ResourceNotFoundException("Current you are not login", "username is ="+Constants_Value.getCurrentLoginUsername(),0));
		complaintRemark.setRemark(ComplaintRemarkDto.getRemark());
		complaintRemark.setUser(user);
		ComplaintRemark update=	this.complaintRemarkRepository.save(complaintRemark);
		return this.modelMapper.map(update, ComplaintRemarkDto.class);
	}

	@Override
	public ComplaintRemarkDto getComplaintRemarkById(Integer id) {
		ComplaintRemark complaintRemark= this.complaintRemarkRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Complaint Remark not Found with this", "id", id));
		return this.modelMapper.map(complaintRemark, ComplaintRemarkDto.class);
	}
	@Override
	public void deleteComplaintRemark(Integer id) {
		
		User user=this.userRepository.findByEmail(Constants_Value.getCurrentLoginUsername()).orElseThrow(()->new ResourceNotFoundException("Current you are not login", "username is ="+Constants_Value.getCurrentLoginUsername(),0));
		ComplaintRemark complaintRemark= this.complaintRemarkRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Complaint Remark not Found with this", "id", id));
		if(complaintRemark.getUser().getId()==user.getId()) {
		this.complaintRemarkRepository.delete(complaintRemark);
		}else {
		     new ApiResponse("Your are not create this remark",false);
		}
	}
	
}
