package com.sumit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sumit.model.MainQuestion;
import com.sumit.model.QuestionAnswer;
import com.sumit.model.Options;
@Repository
public interface PageingAndSortingRipo extends  PagingAndSortingRepository<MainQuestion, Long>  {
	/* @Query("select u from  Questiom u where u.id=?1")
	 Question findRollByUserName(int id);*/
	public List<MainQuestion> findByNameContains(@Param("name") String name);


	
}