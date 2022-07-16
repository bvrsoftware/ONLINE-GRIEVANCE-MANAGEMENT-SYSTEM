package com.bvrsoftware.service;


import com.bvrsoftware.payloads.ComplaintRemarkDto;

public interface ComplaintRemarkService {
	
	ComplaintRemarkDto createComplaintRemark(ComplaintRemarkDto ComplaintRemarkDto);
	ComplaintRemarkDto updateComplaintRemark(ComplaintRemarkDto ComplaintRemarkDto,Integer id);
	ComplaintRemarkDto getComplaintRemarkById(Integer id);
	void deleteComplaintRemark (Integer id);
	
	
}
