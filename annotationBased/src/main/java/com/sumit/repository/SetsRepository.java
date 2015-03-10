package com.sumit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sumit.model.Sets;

public interface SetsRepository extends JpaRepository<Sets, Integer> {

	// @Query("select count(s) from Sets s where s.test.id=:testId")
	// public int findNumberOfSets(@Param("testId") Integer testId);

	@Query("select ss from Sets ss where ss.test.id=?1")
	public List<Sets> findAllSetsByTestId(int testId);

}