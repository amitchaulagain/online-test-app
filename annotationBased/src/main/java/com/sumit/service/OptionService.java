package com.sumit.service;

import java.util.List;

import com.sumit.model.MainQuestion;
import com.sumit.model.Options;
import com.sumit.model.QuestionAnswer;
import com.sumit.model.QuestionDTO;
import com.sumit.model.TestSet;

public interface OptionService {

	public List<Options> findOptionsByQuestion(int id);
	
	public void saveOption(Options option);
}
