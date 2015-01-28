package com.sumit.api;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;


import com.sumit.model.MainQuestion;
import com.sumit.model.TestSet;
import com.sumit.repository.QuestionRepository;
import com.sumit.repository.TestRipository;

@Component
public class QuestionApi implements IQuestionApi{
	@Resource
	QuestionRepository questionRipo ;
	
	@Resource
	TestRipository testripo;

	@Override
	public List<MainQuestion> getAllQuestion() {
		
		return questionRipo.findAll();
	}

	@Override
	public MainQuestion findQuestionById(int id) {
		MainQuestion question =questionRipo.findOne(id);
		return question ;
	}

	@Override
	public int countNumberOfDatas() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MainQuestion> findQuestionByTEst(int id) {
		TestSet test = testripo.findOne(id);
	//List<MainQuestion> questioninTest = questionRipo.FindQuestionInTest(test);
		return null;
	}
	
	

	static int PAGE_SIZE=5;
	public Page<MainQuestion> getDeploymentLog(Integer pageNumber) {
        PageRequest pageRequest =
            new PageRequest(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "name");
        return questionRipo.findAll(pageRequest);
    }
	@Override
	public List<MainQuestion> findInQuestion(String parameter) {
		return questionRipo.findInQuestion(parameter);
	}
	
	

}
