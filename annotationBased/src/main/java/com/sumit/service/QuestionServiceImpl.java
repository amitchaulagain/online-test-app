package com.sumit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumit.api.IQuestionApi;
import com.sumit.dto.QuestionJSONDTO;
import com.sumit.model.MainQuestion;
import com.sumit.model.Options;
import com.sumit.model.QuestionAnswer;
import com.sumit.model.QuestionType;
import com.sumit.repository.AnsRepository;
import com.sumit.repository.OptionsRepository;
import com.sumit.repository.QuestionRepository;
import com.sumit.repository.TestRipository;
//import com.sumit.api.UserInfoApi;
//import com.sumit.model.UserInfo;

@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	IQuestionApi questionApi;

	@Resource
	TestRipository testRipo;

	@Resource
	QuestionRepository questionRipo;

	@Resource
	OptionsRepository optionRipo;

	@Resource
	AnsRepository ansRipo;

	@Override
	public List<MainQuestion> getAllQuestion() {
		return questionRipo.findAll();
	}

	@Override
	public MainQuestion findQuestionById(int id) {
		// TODO Auto-generated method stub
		return questionRipo.findOne(id);
	}

	@Override
	public List<MainQuestion> findQuestionByTEst(int id) {

		return questionApi.findQuestionByTEst(id);
	}

	@Override
	public int countNumberOfDatas() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Transactional
	@Override
	public void save_Question_Option_Answer(QuestionJSONDTO questionJSONDto) {
		QuestionType type = checkQuestionType(questionJSONDto.getQuestionType());
		MainQuestion question = new MainQuestion();
		question.setName(questionJSONDto.getQuestionName());
		 question.setQuestionType(type);
		MainQuestion savedQuestion = questionRipo.save(question);
		int count = 0;
		for (String value : questionJSONDto.getListOfOptions()) {
			count++;
			Options options = new Options();
			options.setName(value);
			options.setQuestion(savedQuestion);
			Options savedOption = optionRipo.save(options);

			for (String ansValue : questionJSONDto.getListOfAnswers()) {

				if (count == Integer.parseInt(ansValue)) {

					QuestionAnswer questionAnswers = new QuestionAnswer();

					questionAnswers.setOptionId(savedOption.getId());
					questionAnswers.setQuestion(savedQuestion);
					ansRipo.save(questionAnswers);

				}

			}

		}

	}

	private QuestionType checkQuestionType(String questionType) {
		if(questionType==null || questionType.isEmpty()){
			return null;
		}
		 QuestionType state;
		 state = QuestionType.valueOf(questionType);
		 return state;

	}

	@Override
	public Page<MainQuestion> getDeploymentLogs(Integer pageNumber) {
		// TODO Auto-generated method stub
		return questionApi.getDeploymentLog(pageNumber);
	}
	@Override
	public List<MainQuestion> findInQuestions(String parameter) {
		
		
		return questionApi.findInQuestion(parameter);
	}

}
