package com.sumit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.xmlbeans.impl.xb.xsdschema.impl.ListDocumentImpl.ListImpl;
import org.krams.response.JasperDto;
import org.krams.util.ResultDTO;
import org.krams.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.memory.UserMap;
import org.springframework.stereotype.Service;

import com.sumit.dto.SeatPlan;
import com.sumit.dto.SeatPlanningDTO;
import com.sumit.model.StudentExaminationInfo;
import com.sumit.model.StudentResultInfo;
import com.sumit.model.User;
import com.sumit.repository.UserRepository;

@Service
public class JasperDatasourceService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ExaminationService examinationService;
	
	/**
	 * Returns a data source that's wrapped within {@link JRDataSource}
	 * @return
	 */
	public JRDataSource getDataSource() {
		List<User> records = repository.findAll();
		List<JasperDto> dtos = new ArrayList<JasperDto>();
		
		// Map records
		for (User user: records) {
			JasperDto dto = new JasperDto();
			dto.setId(user.getId());
			if(user.getUserInfo()!=null){
				dto.setUsername(user.getUsername());
				dto.setFirstName(user.getUserInfo().getFirstName());
				dto.setLastName(user.getUserInfo().getLastName());
			}
			
			dtos.add(dto);
		}
		// Return wrapped collection
		return new JRBeanCollectionDataSource(dtos);
	}

	public JRDataSource getSeatPlanningDataSource(int examId) {
		List<StudentExaminationInfo> infos=examinationService.getAllSeatPlansByExamId(examId);
		List<SeatPlan> dtos=UserMapper.mapToSeatPlan(infos);
		return new JRBeanCollectionDataSource(dtos);
	}
	public JRDataSource getExaminationResultDataSource(int examId) {
		List<StudentResultInfo> resultInfos = examinationService.getAllExaminationResultByExamId(examId);
//		Exam exam=examinationService.findExaminationByExamId(examId);
		List<ResultDTO> dtos = UserMapper.mapToResultDTO(resultInfos);
		return new JRBeanCollectionDataSource(dtos);
	}

	public JRDataSource getStudentResult(int studentResultInfoId) {
		List<ResultDTO> dtos = new ArrayList<ResultDTO>();
		ResultDTO dto= new ResultDTO();
		dto.setStudentName("Amit Chaulagain");
		dto.setObtainedScore(23);
		dto.setPosition("1st");
		dto.setStatus("PASS");
		dto.setRemarks("Well Done");
		dto.setFullMark(222);
		dto.setPassMark(55);
		dto.setEmail("a@hotmail.com");
		dto.setHighScore(888);
		dtos.add(dto);
		return new JRBeanCollectionDataSource(dtos);
	}

	public HashMap<String, Object> loadDataForPDF(int studentResultInfoId) {
		ResultDTO dto =UserMapper.map(examinationService.findStudentResultInformation(studentResultInfoId));
		HashMap<String, Object> hashMap= new HashMap<String, Object>();
		hashMap.put("name", dto.getName());
		hashMap.put("email", dto.getEmail());
		hashMap.put("fullMark", dto.getFullMark());
		hashMap.put("passMark", dto.getPassMark());
		hashMap.put("obtainedScore", dto.getPassMark());
		hashMap.put("position", dto.getPosition());
		hashMap.put("status", dto.getStatus());
		hashMap.put("remarks", dto.getRemarks());
		
		return hashMap;
	}
}
