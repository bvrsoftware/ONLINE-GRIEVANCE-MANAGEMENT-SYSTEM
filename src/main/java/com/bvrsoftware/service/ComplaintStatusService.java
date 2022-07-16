package com.bvrsoftware.service;

import java.util.List;

import com.bvrsoftware.payloads.ComplaintStatusDto;

public interface ComplaintStatusService {

	ComplaintStatusDto createComplaintStatus(ComplaintStatusDto ComplaintStatusDto);
	ComplaintStatusDto updateComplaintStatus(ComplaintStatusDto ComplaintStatusDto,Integer id);
	ComplaintStatusDto getComplaintStatusById(Integer id);
	List<ComplaintStatusDto> getAllComplaintStatus();
    void deleteComplaintStatus (Integer id);
}
