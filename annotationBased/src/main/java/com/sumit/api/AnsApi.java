package com.sumit.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sumit.model.MainQuestion;
import com.sumit.model.QuestionAnswer;
import com.sumit.repository.AnsRepository;
import com.sumit.repository.QuestionRepository;

@Component
public class AnsApi implements IAnsApi{
  @Resource
	QuestionRepository questionRipo;
  @Resource
  AnsRepository ansRipo;
  
 
	@Override
	public List<QuestionAnswer> findAnsIdByQuestion(int id) {
	MainQuestion question = questionRipo.findOne(id);
		
		List<QuestionAnswer> listOfAnswer=ansRipo.findAnsByQuestion(question);
		
		
		return listOfAnswer;
	}

	@Override
	public void saveOption(QuestionAnswer ans) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
