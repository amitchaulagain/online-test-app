package com.sumit.api;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sumit.model.MainQuestion;

public interface IQuestionApi {
	

	public List<MainQuestion> getAllQuestion();
	
	public MainQuestion findQuestionById(int id);
	
	public List<MainQuestion> findQuestionByTEst(int id);
	
	
	public Page<MainQuestion> getDeploymentLog(Integer pageNumber);

	
	
	int countNumberOfDatas();

	public List<MainQuestion> findInQuestion(String parameter);

	


}