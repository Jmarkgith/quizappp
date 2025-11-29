package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvQuestion;
    Button btnOption1, btnOption2, btnOption3, btnOption4;

    ArrayList<Question> questionList;
    int currentQuestion = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvQuestion = findViewById(R.id.tvQuestion);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);

        // Add questions
        questionList = new ArrayList<>();
        questionList.add(new Question("What is Android Studio used for?",
                new String[]{"Web development", "Mobile app development", "Game development only", "Database management"}, 1));
        questionList.add(new Question("Which language is officially recommended for Android development?",
                new String[]{"Python", "Kotlin", "JavaScript", "C++"}, 1));
        questionList.add(new Question("What is an Activity in Android?",
                new String[]{"A type of database", "A screen in an app", "A background service", "A layout file"}, 1));
        questionList.add(new Question("Which file contains the app layout?",
                new String[]{"MainActivity.java", "AndroidManifest.xml", "activity_main.xml", "build.gradle"}, 2));
        questionList.add(new Question("What does R.id.button1 refer to?",
                new String[]{"A resource ID for a button", "A Java class", "A layout file", "A library"}, 0));
        questionList.add(new Question("Which method is called when an Activity is first created?",
                new String[]{"onStart()", "onResume()", "onCreate()", "onPause()"}, 2));
        questionList.add(new Question("What is the default layout type in Android Studio when creating a new activity?",
                new String[]{"RelativeLayout", "LinearLayout", "ConstraintLayout", "FrameLayout"}, 1));
        questionList.add(new Question("Where do you declare permissions for an Android app?",
                new String[]{"MainActivity.java", "AndroidManifest.xml", "gradle.build", "activity_main.xml"}, 1));
        questionList.add(new Question("Which of the following is used to display text on the screen?",
                new String[]{"Button", "TextView", "ImageView", "EditText"}, 1));
        questionList.add(new Question("What file type is used to store app resources like images and strings?",
                new String[]{".java", ".xml", ".gradle", ".kt"}, 1));

        showQuestion();

        btnOption1.setOnClickListener(v -> checkAnswer(0));
        btnOption2.setOnClickListener(v -> checkAnswer(1));
        btnOption3.setOnClickListener(v -> checkAnswer(2));
        btnOption4.setOnClickListener(v -> checkAnswer(3));
    }

    private void showQuestion() {
        if (currentQuestion < questionList.size()) {
            Question q = questionList.get(currentQuestion);
            tvQuestion.setText(q.getQuestion());
            btnOption1.setText(q.getOptions()[0]);
            btnOption2.setText(q.getOptions()[1]);
            btnOption3.setText(q.getOptions()[2]);
            btnOption4.setText(q.getOptions()[3]);
        } else {
            // Go to ResultActivity
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
            finish();
        }
    }

    private void checkAnswer(int selectedOption) {
        Question q = questionList.get(currentQuestion);
        if (selectedOption == q.getCorrectAnswer()) {
            score++;
        }
        currentQuestion++;
        showQuestion();
    }
}
