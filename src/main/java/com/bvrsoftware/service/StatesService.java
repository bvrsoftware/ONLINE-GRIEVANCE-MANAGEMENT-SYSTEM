package com.bvrsoftware.service;

import java.util.List;

import com.bvrsoftware.payloads.StatesDto;

public interface StatesService {

	StatesDto createStates(StatesDto statedDto);
	StatesDto updateStates(StatesDto statedDto,Integer id);
	StatesDto getByStateId(Integer id);
	List<StatesDto> getAllStates();
	void deleteState(Integer id);
	
}
