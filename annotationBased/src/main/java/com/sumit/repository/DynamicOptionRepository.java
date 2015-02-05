package com.sumit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sumit.model.DynamicOption;
import com.sumit.model.MainQuestion;
import com.sumit.model.Options;

public interface DynamicOptionRepository extends  JpaRepository<DynamicOption, Integer> {



	
}