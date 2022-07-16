package com.bvrsoftware.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bvrsoftware.entites.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
