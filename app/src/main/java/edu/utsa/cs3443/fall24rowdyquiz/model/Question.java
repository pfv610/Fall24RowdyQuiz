package edu.utsa.cs3443.fall24rowdyquiz.model;

public class Question {

    private String question;
    private boolean answer;

    public Question(String question, boolean answer){
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion(){
        return this.question;
    }

    public boolean getAnswer(){
       return this.answer;
    }


}
