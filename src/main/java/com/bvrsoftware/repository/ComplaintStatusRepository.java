package com.bvrsoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bvrsoftware.entites.ComplaintStatus;

public interface ComplaintStatusRepository extends JpaRepository<ComplaintStatus, Integer> {

}
