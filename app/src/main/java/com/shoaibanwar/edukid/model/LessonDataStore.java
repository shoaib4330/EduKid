package com.shoaibanwar.edukid.model;

import android.content.Context;
import android.net.Uri;

import com.shoaibanwar.edukid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shoaibanwar on 4/15/17.
 */

public final class LessonDataStore {

    private Context context;

    public LessonDataStore(Context context){
        this.context = context.getApplicationContext();
    }

    public List<Lesson> getLessonList(){

        Question question1 = new Question();
        question1.setQuestionStatement("What is a bully?");
        question1.addAnswer("Bully is a Bully",false);
        question1.addAnswer("Bully is a Bully1",false);
        question1.addAnswer("Bully is a Bully2",true);

        Question question2 = new Question();
        question2.setQuestionStatement("What is a bully 2?");
        question2.addAnswer("Bully is a Bully",false);
        question2.addAnswer("Bully is a Bully1",false);
        question2.addAnswer("Bully is a Bully2",true);

        Question question3 = new Question();
        question3.setQuestionStatement("What is a bully 3?");
        question3.addAnswer("Bully is a Bully",false);
        question3.addAnswer("Bully is a Bully1",false);
        question3.addAnswer("Bully is a Bully2",true);

        Question question4 = new Question();
        question4.setQuestionStatement("What is a bully 4?");
        question4.addAnswer("Bully is a Bully",false);
        question4.addAnswer("Bully is a Bully1",false);
        question4.addAnswer("Bully is a Bully2",true);

        List<Lesson> lessonList = new ArrayList<>();
        String path = "android.resource://" + context.getPackageName() + "/" + R.raw.court;

        Lesson lesson1 = new Lesson();
        lesson1.setName("What is a Bully?");
        lesson1.setPresentationText("You want to learn about bullies?");
        lesson1.setAnimation_Uri(path);

        Lesson lesson2 = new Lesson();
        lesson2.setName("How to deal with a Bully?");
        lesson2.setPresentationText("Learn dealing bullies?");
        lesson2.setAnimation_Uri(path);

        Lesson lesson3 = new Lesson();
        lesson3.setName("A B C D E F?");
        lesson3.setPresentationText("You want to learn Maybe ABC?");
        lesson3.setAnimation_Uri(path);

        Lesson lesson4 = new Lesson();
        lesson4.setName("What is a Bully Again?");
        lesson4.setPresentationText("You want to learn about bullies again?");
        lesson4.setAnimation_Uri(path);

        lesson1.addQuestion(question1);
        lesson1.addQuestion(question2);
        lesson1.addQuestion(question3);
        lesson1.addQuestion(question4);

        lesson2.addQuestion(question1);
        lesson2.addQuestion(question2);
        lesson2.addQuestion(question3);
        lesson2.addQuestion(question4);

        lesson3.addQuestion(question1);
        lesson3.addQuestion(question2);
        lesson3.addQuestion(question3);
        lesson3.addQuestion(question4);

        lessonList = new ArrayList<>();
        lessonList.add(lesson1);
        lessonList.add(lesson2);
        lessonList.add(lesson3);
        lessonList.add(lesson4);

        return lessonList;
    }

}
