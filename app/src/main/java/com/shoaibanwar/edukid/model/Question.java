package com.shoaibanwar.edukid.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shoaibanwar on 4/14/17.
 */

public final class Question implements Serializable{

    private String questionStatement;
    private List<String> choices;
    private int correctAnsIndex;

    public Question()
    {
        questionStatement="";
        choices=null;
        correctAnsIndex=-1;
    }

    public Question(String questionStatement, List<String> stringList,int correctAnsIndex){
        this.questionStatement = questionStatement;
        this.choices = stringList;
        this.correctAnsIndex = correctAnsIndex;
    }

    public String getQuestionStatement() {
        return questionStatement;
    }

    public void setQuestionStatement(String questionStatement) {
        this.questionStatement = questionStatement;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public void addAnswer(String possAns,boolean istrue)
    {
        if (choices==null)
        {
            choices = new ArrayList<>();
        }

        if (choices.size()<=3)
        {
            choices.add(possAns);
            if (istrue)
            {
                correctAnsIndex = choices.size()-1;
            }
        }
    }

    public int getCorrectAnsIndex ()
    {
        return correctAnsIndex;
    }


}
