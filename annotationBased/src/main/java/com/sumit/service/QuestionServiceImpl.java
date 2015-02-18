package com.sumit.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sumit.api.IQuestionApi;
import com.sumit.convert.ConvertUtils;
import com.sumit.model.MainQuestion;
import com.sumit.model.Options;
import com.sumit.model.QuestionAnswer;
import com.sumit.model.QuestionJSONDTO;
import com.sumit.model.OptionType;
import com.sumit.model.TestDTO;
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
 
		OptionType type = checkQuestionType(questionJSONDto.getMainquestion().getOptionType().toString());
		MainQuestion question = new MainQuestion();
		question.setName(questionJSONDto.getMainquestion().getName());
		 question.setOptionType(type);
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

	private OptionType checkQuestionType(String questionType) {
		if(questionType==null || questionType.isEmpty()){
			return null;
		}
		 OptionType state;
		 state = OptionType.valueOf(questionType);
		 return state;

	}

	@Override
	public Page<MainQuestion> getDeploymentLogs(Integer pageNumber) {
		return questionApi.getDeploymentLog(pageNumber);
	}
	@Override
	public List<MainQuestion> findInQuestions(String parameter) {
		
		
		return questionApi.findInQuestion(parameter);
	}

	@Override
	public List<TestDTO> getAllTests() {
		return ConvertUtils.convertToTestDTOs(testRipo.findAll());
	}

}
