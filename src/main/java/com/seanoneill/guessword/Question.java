package com.seanoneill.guessword;

public class Question {
	private int ID;
	private String QUESTION;
	private String CORRECT_ANSWER;


	public Question(){
		ID = 0;
		QUESTION = "";
		CORRECT_ANSWER = "";
	}

	public Question(String quest, String correct_ans){
		QUESTION = quest;
		CORRECT_ANSWER = correct_ans;
	}

	///////////////////////// Setters /////////////////////////

	public void setQUESTION(String quest){
		QUESTION = quest;
	}

	public void setCORRECT_ANSWER(String correct_ans){
		CORRECT_ANSWER = correct_ans;
	}

	///////////////////////// Getters /////////////////////////
	public String getQUESTION(){
		return QUESTION;
	}

	public String getCORRECT_ANSWER(){
		return CORRECT_ANSWER;
	}

	public void setID(int id){
		ID=id;
	}
}
