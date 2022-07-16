package com.bvrsoftware.service;

import java.util.List;

import com.bvrsoftware.pagination.ComplaintResponsePagination;
import com.bvrsoftware.payloads.ComplaintsDto;


public interface ComplaintsService {

	public ComplaintsDto createComplaint(ComplaintsDto complaintsDto);
	public ComplaintsDto updateComplaint(ComplaintsDto complaintsDto,Integer id);
	public ComplaintsDto updateComplaintStatus(Integer statusId,Integer cid);
	public ComplaintsDto getByComplaintNumber(Integer number);
	public ComplaintResponsePagination getAllComplaints(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
	public List<ComplaintsDto> getAllComplaintsByStatus(Integer complaintStatusId);
	public List<ComplaintsDto> getAllComplaintsByState(Integer statesId);
	public List<ComplaintsDto> getAllComplaintsByCategory(Integer categoriesId);
	public List<ComplaintsDto> getAllComplaintsBySubCategory(Integer subCategoryId);
	public List<ComplaintsDto> getAllComplaintsByUser(Integer userId);
}
