package com.sumit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sumit.model.Exam;

public interface ExamRepository extends JpaRepository<Exam, Integer> {

}