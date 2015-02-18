package com.sumit.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	public Category() {
	}

	@Id
	@GeneratedValue
	private int id;

	private String name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "category")
	@Fetch(value = FetchMode.SELECT)
	private List<MainQuestion> categoryQuestions = new ArrayList<MainQuestion>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MainQuestion> getCategoryQuestions() {
		return categoryQuestions;
	}

	public void setCategoryQuestions(List<MainQuestion> categoryQuestions) {
		this.categoryQuestions = categoryQuestions;
	}

	

}
