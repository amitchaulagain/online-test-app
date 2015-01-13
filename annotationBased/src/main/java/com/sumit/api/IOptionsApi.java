package com.sumit.api;

import java.util.List;

import com.sumit.model.Options;

public interface IOptionsApi {
	

		public List<Options> findOptionsByQuestion(int id);
		
		public void saveOption(Options option);
		 public Options findOptionById(int optionId);


}