package com.sumit.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sumit.dto.ExaminationAssignDTO;
import com.sumit.model.MainQuestion;
import com.sumit.model.QuestionJSONDTO;
import com.sumit.model.TestDTO;

public interface QuestionService {

	public List<MainQuestion> getAllQuestion();
	
	public MainQuestion findQuestionById(int id);
	
	public List<MainQuestion> findQuestionByTEst(int id);
	

	int countNumberOfDatas();
	Page<MainQuestion> getDeploymentLogs(Integer pageNumber);

	void save_Question_Option_Answer(QuestionJSONDTO questionJSONDto);

	public List<MainQuestion> findInQuestions(String parameter);

	public List<TestDTO> getAllTests();

	public void deleteCategory(int groupId);

	public void createOrEditCategory(QuestionJSONDTO dto);
}
