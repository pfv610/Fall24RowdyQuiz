package edu.utsa.cs3443.fall24rowdyquiz.model;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import edu.utsa.cs3443.fall24rowdyquiz.MainActivity;

public class QuizBank {

    private ArrayList<Question> questions;
    private int qIndex;
    private Question currentQuestion;

    public QuizBank(){
        questions = new ArrayList<>();
        qIndex = 0;
        currentQuestion = null;
        //currentQuestion = questions.get(0);
    }

    public void setQuestions(ArrayList<Question> questions){
        this.questions = questions;
    }

    public ArrayList<Question> getQuestions(){
        return this.questions;
    }


    public void loadQuestions(MainActivity mainActivity){
        /*Question q1 = new Question("UTSA is an R1 institute?", true);
        Question q2 = new Question("UTSA's mascot is a tiger!", false);
        this.questions.add(q1);
        this.questions.add(q2);*/

        //TODO: We hardcoded the questions, But we need to read them from a file!!!
        AssetManager manager = mainActivity.getAssets();
        //try and catch
        try {
            InputStream file = manager.open("questions.csv");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine().trim();
                String[] elements = line.split(",");
                Question q1 = new Question(elements[0], Boolean.parseBoolean(elements[1]));
                this.questions.add(q1);
            }
        }catch(IOException e){
                System.err.println(e.getMessage());
        }
    }


    public void addQuestion(Question question){
        questions.add(question);
    }
    public Question getQuestionObject(int index){
        return questions.get(index);
    }

    public void updateCurrentQuestion(){
        if(qIndex < questions.size() && qIndex >= 0) {
            currentQuestion = questions.get(qIndex);
            qIndex++;
        }
        else {
            qIndex = 0;
            currentQuestion = questions.get(qIndex);
        }
    }

    public String getCurrentQuestionText(){
        return currentQuestion.getQuestion();
    }

    public Boolean getCurrentQuestionAnswer(){
        return currentQuestion.getAnswer();
    }


    //Create a method that recives a String question and returns the boolean answer
    //This method belongs to an object of QuizBank
    public boolean getQuestionAnswer(String question){
        for(Question q: questions){
            if(q.getQuestion().equalsIgnoreCase(question)){
                return q.getAnswer();
            }
        }
        return false;
    }

}
