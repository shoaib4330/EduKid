package com.shoaibanwar.edukid.model;

import android.net.Uri;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shoaibanwar on 4/14/17.
 */

public final class Lesson implements Serializable {

    private String name;
    private String animation_Uri;
    private String presentationText;
    private List<Question> testQuestionsList;

    public Lesson(){

    }

    public Lesson (String name,String animation_Uri,String presentationText,List<Question> testQuestionsList){
        this.name = name;
        this.animation_Uri = animation_Uri;
        this.presentationText = presentationText;
        this.testQuestionsList = testQuestionsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnimation_Uri() {
        return animation_Uri;
    }

    public void setAnimation_Uri(String animation_Uri) {
        this.animation_Uri = animation_Uri;
    }

    public String getPresentationText() {
        return presentationText;
    }

    public void setPresentationText(String presentationText) {
        this.presentationText = presentationText;
    }

    public List<Question> getTestQuestionsList() {
        return testQuestionsList;
    }

    public void setTestQuestionsList(List<Question> testQuestionsList) {
        this.testQuestionsList = testQuestionsList;
    }

    public void addQuestion (Question question){
        if (testQuestionsList==null){
            testQuestionsList = new ArrayList<>();
        }
        testQuestionsList.add(question);
    }
}
