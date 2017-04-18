package com.shoaibanwar.edukid;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.shoaibanwar.edukid.model.Lesson;
import com.shoaibanwar.edukid.model.LessonDataStore;
import com.shoaibanwar.edukid.model.Question;
import com.shoaibanwar.edukid.view.QuestionView;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements QuestionFragment.QuestionEventsHandler{

    private int lessonIndex;
    private List<Question> list_Questions;

    private FrameLayout frameLayout;
    private FragmentManager fragmentManager;

    private int currentQuestion=0;
    private int totalScore=0;
    private int scoreGained=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        getSupportActionBar().hide();

        frameLayout = (FrameLayout) findViewById(R.id.frag_View);

        lessonIndex = getIntent().getIntExtra("LessonPosition",0);
        list_Questions = new LessonDataStore(QuizActivity.this).getLessonList().get(lessonIndex).getTestQuestionsList();

        totalScore = list_Questions.size()*10;

        toShowQuizCompletionScreen();

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Bundle args = new Bundle();
        args.putSerializable("CurrentQuestion",list_Questions.get(currentQuestion));
        currentQuestion++;

        QuestionFragment questionFragment = new QuestionFragment();
        questionFragment.setInteractingActivity(this);
        questionFragment.setArguments(args);

        fragmentTransaction.add(R.id.frag_View,questionFragment);
        fragmentTransaction.commit();
    }


    @Override
    public void OnQuestionAnswerSelected(boolean iftrue) {

        if (iftrue)
        {
            this.scoreGained = this.scoreGained + 10;
        }

        toShowQuizCompletionScreen();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Bundle args = new Bundle();
        args.putSerializable("CurrentQuestion",list_Questions.get(currentQuestion));
        currentQuestion++;

        QuestionFragment questionFragment = new QuestionFragment();
        questionFragment.setInteractingActivity(this);
        questionFragment.setArguments(args);

        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);

        fragmentTransaction.replace(R.id.frag_View,questionFragment);
        fragmentTransaction.commit();
    }

    private void QuizDoneActions(){
        Toast.makeText(QuizActivity.this,"Quiz Done",Toast.LENGTH_LONG).show();
    }

    private boolean toShowQuizCompletionScreen(){
        if (currentQuestion >= this.list_Questions.size())
        {
            currentQuestion=0;
            return true;
        }
        return false;
    }

}
