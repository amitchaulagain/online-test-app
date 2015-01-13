package com.sumit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sumit.model.MainQuestion;
import com.sumit.model.Options;

public interface OptionsRepository extends  JpaRepository<Options, Integer> {
	 @Query("select o from Options o join o.optionQuestion q where  q in (?1)")
	/*select o from Employee o where o.departments.departmentId is null or o.departments.departmentId=:departmentId

*/
	 List<Options> findOptionByquestion(int id);


	
}