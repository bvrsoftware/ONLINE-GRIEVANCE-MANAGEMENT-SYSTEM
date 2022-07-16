package com.bvrsoftware.service.implemention;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bvrsoftware.entites.States;
import com.bvrsoftware.exception.ResourceNotFoundException;
import com.bvrsoftware.payloads.StatesDto;
import com.bvrsoftware.repository.StatesRepository;
import com.bvrsoftware.service.StatesService;

@Service
public class StatesServiceImplement implements StatesService{

	@Autowired
	private StatesRepository repository;
	@Autowired	
	ModelMapper modelMapper;
	
	   @Override
	   public StatesDto createStates(StatesDto statedDto) {
		States state= this.modelMapper.map(statedDto, States.class);
		state.setStateCreationDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		States registerState= this.repository.save(state);
		return this.modelMapper.map(registerState, StatesDto.class);
	}

	@Override
	public StatesDto updateStates(StatesDto statedDto, Integer id) {
		States states=this.repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not Found", "State Id=", id));
		states.setStateName(statedDto.getStateName());	
		States updateState= this.repository.save(states);
		return this.modelMapper.map(updateState, StatesDto.class);
	}

	@Override
	public StatesDto getByStateId(Integer id) {
		States states=this.repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not Found", "State Id=", id));	
		return this.modelMapper.map(states, StatesDto.class);
	}

	@Override
	public List<StatesDto> getAllStates() {
		List<States> stateList=this.repository.findAll();
		List<StatesDto> statesDtoList=stateList.stream().map(state->
		this.modelMapper.map(state, StatesDto.class)).collect(Collectors.toList());
		return statesDtoList;
	}

	@Override
	public void deleteState(Integer id) {
		States states=this.repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not Found", "State Id=", id));	
        this.repository.delete(states);		
	}

}
