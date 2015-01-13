package com.sumit.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.Resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class OptionServiceImpl implements OptionService {
	@Autowired
	OptionApi optionApi;
	
	@Override
	public List<Options> findOptionsByQuestion(int id) {
		// TODO Auto-generated method stub
		return optionApi.findOptionsByQuestion(id);
	}

	@Override
	public void saveOption(Options option) {
		optionApi.saveOption(option);
		
	}
	
		
	}

	

	
	


