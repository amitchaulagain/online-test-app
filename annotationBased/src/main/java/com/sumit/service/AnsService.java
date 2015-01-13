package com.sumit.service;

import java.util.List;

import com.sumit.model.MainQuestion;
import com.sumit.model.Options;
import com.sumit.model.QuestionAnswer;
import com.sumit.model.QuestionDTO;
import com.sumit.model.TestSet;

public interface AnsService {

	public List<QuestionAnswer> findAnsByQuestion(int id);
	
	public void saveOption(Options option);
	public List<String> getAnsName(int id);
}
