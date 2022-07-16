package com.bvrsoftware.service.implemention;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvrsoftware.entites.ComplaintStatus;
import com.bvrsoftware.exception.ResourceNotFoundException;
import com.bvrsoftware.payloads.ComplaintStatusDto;
import com.bvrsoftware.repository.ComplaintStatusRepository;
import com.bvrsoftware.service.ComplaintStatusService;

@Service
public class ComplaintStatusServiceImplement implements ComplaintStatusService {

	@Autowired	
	ModelMapper modelMapper;
	
	@Autowired
	private ComplaintStatusRepository complaintStatusRepository;
	
	@Override
	public ComplaintStatusDto createComplaintStatus(ComplaintStatusDto ComplaintStatusDto) {
		ComplaintStatus complaintStatus=this.modelMapper.map(ComplaintStatusDto, ComplaintStatus.class);
		ComplaintStatus registerComplaintStatus=this.complaintStatusRepository.save(complaintStatus);
		return this.modelMapper.map(registerComplaintStatus, ComplaintStatusDto.class);
	}

	@Override
	public ComplaintStatusDto updateComplaintStatus(ComplaintStatusDto ComplaintStatusDto, Integer id) {
		ComplaintStatus complaintStatus=this.complaintStatusRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("ComplaintStatus Not Found with this ", " id=",id));
		complaintStatus.setStatusName(ComplaintStatusDto.getStatusName());
		ComplaintStatus updateComplaintStatus=this.complaintStatusRepository.save(complaintStatus);
		return this.modelMapper.map(updateComplaintStatus, ComplaintStatusDto.class);
	}

	@Override
	public ComplaintStatusDto getComplaintStatusById(Integer id) {
	ComplaintStatus complaintStatus=this.complaintStatusRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("ComplaintStatus Not Found with this ", " id=",id));
		return this.modelMapper.map(complaintStatus, ComplaintStatusDto.class);
	}

	@Override
	public List<ComplaintStatusDto> getAllComplaintStatus() {
	   List<ComplaintStatus> list=this.complaintStatusRepository.findAll();
	   List<ComplaintStatusDto> dtoList=list.stream().map(l->this.modelMapper.map(l, ComplaintStatusDto.class)).collect(Collectors.toList());
		return dtoList;
	}

	@Override
	public void deleteComplaintStatus(Integer id) {
	ComplaintStatus complaintStatus=this.complaintStatusRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("ComplaintStatus Not Found with this ", " id=",id));
	 this.complaintStatusRepository.delete(complaintStatus);
	}
}
