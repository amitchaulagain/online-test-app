package com.sumit.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.Resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumit.api.AnsApi;
import com.sumit.api.OptionApi;
import com.sumit.api.QuestionApi;
import com.sumit.api.TestApi;
import com.sumit.model.MainQuestion;
import com.sumit.model.Options;
import com.sumit.model.QuestionAnswer;
import com.sumit.model.QuestionDTO;
import com.sumit.model.TestSet;
import com.sumit.repository.AnsRepository;
import com.sumit.repository.OptionsRepository;
import com.sumit.repository.QuestionRepository;
import com.sumit.repository.TestRipository;
//import com.sumit.api.UserInfoApi;
//import com.sumit.model.UserInfo;

@Service
public class AnsServiceImpl implements AnsService {
	@Resource
	QuestionRepository questionRipo;
	
	@Autowired
	AnsApi ansApi;
	@Autowired
	OptionApi optionApi;

	@Override
	public List<QuestionAnswer> findAnsByQuestion(int id) {
	
		return ansApi.findAnsIdByQuestion(id);
	}

	@Override
	public void saveOption(Options option) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getAnsName(int id) {
		List<QuestionAnswer> answers = ansApi.findAnsIdByQuestion(id);
		List<String> optionNames = new ArrayList<String>();
		for (QuestionAnswer answer : answers) {
			int optionId =answer.getOptionId();
			Options option = optionApi.findOptionById(optionId);
			 String answerName =option.getName();
			 optionNames.add(answerName);
			
		}
		return optionNames;
	}
	
	
	
		
	}

	

	
	


