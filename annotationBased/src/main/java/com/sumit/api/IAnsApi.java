package com.sumit.api;

import java.util.List;

import com.sumit.model.QuestionAnswer;

public interface IAnsApi {
	

		public List<QuestionAnswer> findAnsIdByQuestion(int id);
		
		public void saveOption(QuestionAnswer ans);
		


}