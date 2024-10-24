package edu.utsa.cs3443.fall24rowdyquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.utsa.cs3443.fall24rowdyquiz.model.QuizBank;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private QuizBank bank;
    //private int qIndex;

    private static final String intentKey = "question";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //define the functionality of all buttons (TRUE and False, Next)
        //setUpButton(R.id.false_button);
        //setUpButton(R.id.true_button);

        int[] buttonIds = {R.id.false_button, R.id.true_button, R.id.next_button, R.id.peek_button};
        for(int buttonId: buttonIds){
            setUpButton(buttonId);
        }


        //Call our MOdel classes, somehow we need to have an object of the QuizBank and Question
        //So we can put that question into the question TextView
        //qIndex = 1;
        createQuizBank();
        displayQuestion();

    }

    private void setUpButton(int buttonID){
        //how can we define a listener for te button with buttonID
        Button button = findViewById(buttonID);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //this v can be either R.id.true_button OR R.id.False_button
        //if the view ID is for TrueButton and the answer of the question is actually true, then show a pop up that says "yaay!"
        //if the view ID is for TrueButton, but answer is false, then pop up shows "CRAP!!"
        //if the view ID is for FalseButton, an the answer is false, then pop up shows "Yaaay!!"
        //if the view ID is for falseButton, but answer is true, then pop up shows "CRAP!!"
        if(view.getId() == R.id.true_button && this.bank.getCurrentQuestionAnswer()){
            //show yay
            //Pop up is Toast
            Toast.makeText(view.getContext(), "Yaaay!!", Toast.LENGTH_LONG).show();
        }
        if(view.getId() == R.id.true_button && !this.bank.getCurrentQuestionAnswer()){
            //show crap
            Toast.makeText(view.getContext(), "CRAP!!", Toast.LENGTH_LONG).show();
        }
        if(view.getId() == R.id.false_button && this.bank.getCurrentQuestionAnswer()){
            //show crap
            Toast.makeText(view.getContext(), "CRAP!!", Toast.LENGTH_LONG).show();
        }
        if(view.getId() == R.id.false_button && !this.bank.getCurrentQuestionAnswer()){
            //show yay
            Toast.makeText(view.getContext(), "Yaaay!!", Toast.LENGTH_LONG).show();
        }

        if(view.getId() == R.id.next_button){
            displayQuestion();
        }

        if(view.getId() == R.id.peek_button){
            Intent intent = new Intent(this, PeekActivity.class);
            //sometimes we have to pass an extra value to the next view
            //We want to send the text for the current question
            //We can only send STRING values
            String passedValue = bank.getCurrentQuestionText() + ": " + String.valueOf(bank.getCurrentQuestionAnswer());
            intent.putExtra(intentKey, passedValue);
            startActivity(intent);
        }

    }

    public static String decodeIntent(){
        return intentKey;
    }

    private void createQuizBank(){
        this.bank = new QuizBank();
        this.bank.loadQuestions(this);
    }

    private void displayQuestion(){
        //Let's create a logical represeantation for the question TextView
        TextView questionText = findViewById(R.id.question_text);
        bank.updateCurrentQuestion();
        questionText.setText(this.bank.getCurrentQuestionText());
        //questionText.setText(this.bank.getQuestionObject(index).getQuestion());
        //we can write a method that takes care of the qIndex increment

    }
}