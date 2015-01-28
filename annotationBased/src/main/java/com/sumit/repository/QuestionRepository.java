package com.sumit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.sumit.model.MainQuestion;
import com.sumit.model.TestSet;

public interface QuestionRepository extends
		JpaRepository<MainQuestion, Integer> {

	@Query("SELECT q FROM MainQuestion q  WHERE q.name LIKE CONCAT('%',:searchString,'%')")
	List<MainQuestion> findInQuestion(@Param("searchString") String searchString);

}