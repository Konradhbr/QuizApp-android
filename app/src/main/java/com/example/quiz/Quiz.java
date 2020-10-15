package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Quiz extends AppCompatActivity {

    TextView questionLabel, questionCountLabel, scoreLabel;
    EditText answerEdt;
    Button submitButton;
    ProgressBar progressBar;
    ArrayList<QuestionModel> questionModelArraylist;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    int currentPosition = 0;
    int numberOfCorrectAnswer = 0;
    String zmienna = "";
    String resultGlobal = "";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        questionCountLabel = findViewById(R.id.numerPytania);
        questionLabel = findViewById(R.id.pytanie);
        scoreLabel = findViewById(R.id.wynik);

        answerEdt = findViewById(R.id.odpowiedz);
        submitButton = findViewById(R.id.zatwierdz);
        progressBar = findViewById(R.id.progres);
        questionModelArraylist = new ArrayList<>();

        setUpQuestion();

        setData();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer();


            }
        });

        answerEdt.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.e("event.getAction()",event.getAction()+"");
                Log.e("event.keyCode()",keyCode+"");
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    checkAnswer();
                    return true;
                }
                return false;
            }
        });

    }

    public void register(int v) {
        Intent register = new Intent(com.example.quiz.Quiz.this, RegisterPanel.class);
        startActivity(register);
    }

    public void checkAnswer(){
        String answerString  = answerEdt.getText().toString().trim();

        if(answerString.equalsIgnoreCase(questionModelArraylist.get(currentPosition).getAnswer())){
            numberOfCorrectAnswer ++;
            new SweetAlertDialog(Quiz.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Good job!")
                    .setContentText("Right Answer")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            currentPosition ++;
                            setData();
                            answerEdt.setText("");
                            sweetAlertDialog.dismiss();

                        }
                    })
                    .show();
        }else {

            new SweetAlertDialog(Quiz.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Zła odpowiedź")
                    .setContentText("Prawidłowa odpowiedź to  : "+questionModelArraylist.get(currentPosition).getAnswer())
                    .setConfirmText("OK")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.dismiss();


                            currentPosition ++;

                            setData();
                            answerEdt.setText("");
                        }
                    })
                    .show();
        }
        int x = ((currentPosition+1) * 100) / questionModelArraylist.size();

        progressBar.setProgress(x);
    }

    public void setUpQuestion(){
        questionModelArraylist.add(new QuestionModel("Oblicz 1 + 2 x 3 ? ","7"));
        questionModelArraylist.add(new QuestionModel("Oblicz 8 x 8 + 12 ? ","76"));
        questionModelArraylist.add(new QuestionModel("Oblicz 9 x 12 + 20 - 5? ","123"));
        questionModelArraylist.add(new QuestionModel("Oblicz 6 x 8 x 3? ","144"));
        questionModelArraylist.add(new QuestionModel("Oblicz 12 / 3 ? ","4"));
        questionModelArraylist.add(new QuestionModel("Oblicz 12 x 3 ? ","36"));
        questionModelArraylist.add(new QuestionModel("Oblicz 12 / 3 / 2 ? ","2"));
        questionModelArraylist.add(new QuestionModel("Oblicz 60 / 3 x 100? ","2000"));
    }

    public void setData(){
        if(questionModelArraylist.size()>currentPosition) {

            questionLabel.setText(questionModelArraylist.get(currentPosition).getQuestionString());

            scoreLabel.setText(numberOfCorrectAnswer + "/"+ questionModelArraylist.size());
            //resultInDataBase = numberOfCorrectAnswer;
            questionCountLabel.setText("Pytanie nr. : " + (currentPosition + 1));
            resultGlobal = String.valueOf(numberOfCorrectAnswer);


        }else{
            new SweetAlertDialog(Quiz.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Poprawnie ukończyłeś Quiz ")
                    .setContentText("Twój wynik : "+ numberOfCorrectAnswer + "/" + questionModelArraylist.size())
                    .setConfirmText("Zagraj")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                           // resultInDataBase = numberOfCorrectAnswer;

                            sDialog.dismissWithAnimation();
                            currentPosition = 0;
                            numberOfCorrectAnswer = 0;
                            progressBar.setProgress(0);
                            setData();

                        }
                    })
                    .setCancelText("Zakończ")
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {

                            sDialog.dismissWithAnimation();
                            finish();
                            Intent intToMain = new Intent(com.example.quiz.Quiz.this, AddPlayer.class);
                            intToMain.putExtra(zmienna , resultGlobal);
                            startActivity(intToMain);
                        }
                    })
                    .show();

        }

    }





}

