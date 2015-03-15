package com.sumit.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.krams.response.JasperDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumit.model.User;
import com.sumit.repository.UserRepository;

@Service
public class JasperDatasourceService {

	@Autowired
	private UserRepository repository;
	
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
}
