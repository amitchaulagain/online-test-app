package com.sumit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumit.model.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {

}