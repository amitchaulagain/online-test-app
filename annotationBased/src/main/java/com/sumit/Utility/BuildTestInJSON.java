package com.sumit.Utility;

import java.util.List;

import com.sumit.model.DynamicOption;
import com.sumit.model.MainQuestion;
import com.sumit.model.Sections;
import com.sumit.model.Sets;
import com.sumit.model.TestSet;

public class BuildTestInJSON {
	private final TestSet test;
	// required
	private final List<Sets> sets;
	//  required
	private final List<MainQuestion> questions;
	//optional
	private final List<DynamicOption> dynamicOptions;
	// optional
	private final List<Sections> sections;
	
	public static class Builder {
			// Required parameters
			private final TestSet test;
			private final List<Sets> sets;
			// Optional
			private List<MainQuestion> questions=null;
			private List<DynamicOption> dynamicOptions=null;
			private List<Sections> sections=null;
			public Builder(TestSet test,List<Sets> sets) {
			this.test = test;
			this.sets= sets;
			}
			public Builder questions(List<MainQuestion> val)
			{ questions = val;
			return this;
			}
			public Builder dynamicOptions(List<DynamicOption> val)
			{ dynamicOptions = val;
			return this;}
			public Builder sections(List<Sections> val)
			{ sections = val; return this;}
			public BuildTestInJSON build(){
				
				return new BuildTestInJSON(this);
			}
	}
			private BuildTestInJSON(Builder builder) {
			test = builder.test;
			
			sets= builder.sets;
			questions= builder.questions;
			dynamicOptions= builder.dynamicOptions;
			sections= builder.sections;
			}
}
