package com.sumit.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sumit.model.MainQuestion;
import com.sumit.model.Options;
import com.sumit.repository.OptionsRepository;
import com.sumit.repository.QuestionRepository;

@Component
public class OptionApi implements IOptionsApi{
	@Resource
	OptionsRepository optionRipo;
	@Resource
	QuestionRepository questionRipo;
	
	Options option;

	@Override
	public List<Options> findOptionsByQuestion(int id) {
			
		MainQuestion question = questionRipo.findOne(id);
		
		int ids = question.getId();
		List<Options> optionsOfQuestion =  question.getOptions();
				//optionRipo.findOptionByquestion(ids);
	
		
		 
		return optionsOfQuestion;
	}

	@Override
	public void saveOption(Options option) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Options findOptionById(int optionId) {
		
		return optionRipo.findOne(optionId);
	}
	
}
