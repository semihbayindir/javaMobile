package org.kasapbasi.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    Button btn;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    TextView tvTime;
    TextView tvCounter;
    TextView tvQuestion;
    TextView tvResult;
    RelativeLayout RelativeLayout2;

    ArrayList<Integer> answers = new ArrayList<Integer>();

    int score=0;
    int questionNo=0;
    int correctAnswer;
    public void play(View v){
        score=0;
        questionNo=0;
        tvTime.setText("30");
        tvCounter.setText("0/0");
        tvResult.setText("");
        generateQuestion();
        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                tvTime.setText(String.valueOf(millisUntilFinished / 1000)+"s");
            }
            @Override
            public void onFinish() {
                tvTime.setText("0s");
                tvResult.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(questionNo));
            }
        }.start();
    }
    public void answer(View v){
        if (v.getTag().toString().equals(Integer.toString(correctAnswer))){
            score++;
            tvResult.setText("Correct!");
        }
        else{
            tvResult.setText("Incorrect!");
        }
        questionNo++;
        tvCounter.setText(Integer.toString(score)+"/"+Integer.toString(questionNo));
        generateQuestion();
    }
    public void generateQuestion(){
        Random rand=new Random();
        int num1=rand.nextInt(51);
        int num2=rand.nextInt(51);
        tvQuestion.setText(Integer.toString(num1)+"+"+Integer.toString(num2));
        correctAnswer=rand.nextInt(4);
        answers.clear();
        int incorrectAnswer;
        for(int i=0;i<4;i++){
            if(i==correctAnswer){
                answers.add(num1+num2);
            }
            else{
                incorrectAnswer= rand.nextInt(101);
                while (incorrectAnswer==num1+num2){
                    incorrectAnswer= rand.nextInt(101);
                }
                answers.add(incorrectAnswer);
            }
        }
        btn1.setText(Integer.toString(answers.get(0)));
        btn2.setText(Integer.toString(answers.get(1)));
        btn3.setText(Integer.toString(answers.get(2)));
        btn4.setText(Integer.toString(answers.get(3)));
    }

    public void start(View v){
        btn.setVisibility(View.INVISIBLE);
        RelativeLayout2.setVisibility(RelativeLayout.VISIBLE);
        play(findViewById(R.id.tvTime));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button)findViewById(R.id.btnGo);
        btn1 = (Button) findViewById(R.id.btnOption1);
        btn2 = (Button) findViewById(R.id.btnOption2);
        btn3 = (Button) findViewById(R.id.btnOption3);
        btn4 = (Button) findViewById(R.id.btnOption4);
        tvCounter=(TextView) findViewById(R.id.tvCounter);
        tvQuestion=(TextView) findViewById(R.id.tvQuestion);
        tvResult=(TextView) findViewById(R.id.tvResult);
        tvTime=(TextView) findViewById(R.id.tvTime);
        RelativeLayout2=(RelativeLayout) findViewById(R.id.RelativeLayout2);
        generateQuestion();
    }
}
