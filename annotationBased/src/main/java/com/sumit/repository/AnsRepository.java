package com.sumit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sumit.model.MainQuestion;
import com.sumit.model.QuestionAnswer;
import com.sumit.model.Options;

public interface AnsRepository extends  JpaRepository<QuestionAnswer, Integer> {
	/* @Query("select u from  Questiom u where u.id=?1")
	 Question findRollByUserName(int id);*/
	 @Query("select a from QuestionAnswer a join a.answerQuestion q where  q in (?1)")
	 public List<QuestionAnswer> findAnsByQuestion(MainQuestion question); 


	
}