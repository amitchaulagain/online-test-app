package com.sumit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sumit.model.MainQuestion;
import com.sumit.model.Options;
import com.sumit.model.TestQuestions;

public interface TestQuestionRepository extends  JpaRepository<TestQuestions, Integer> {

	@Query("SELECT tq FROM TestQuestions tq  WHERE tq.testInTestquestion LIKE CONCAT(:testId)")
	List<TestQuestions> searchByTestId(@Param("testId") int id);

	
}