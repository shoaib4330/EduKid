package com.shoaibanwar.edukid;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shoaibanwar.edukid.model.Question;
import com.shoaibanwar.edukid.view.QuestionView;

public class QuestionFragment extends Fragment implements QuestionView.AnswerSelectionListener {

    private Question questionPresented;
    private QuestionView questionView;

    private QuestionEventsHandler questionEventsHandler;

    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            questionPresented = (Question) getArguments().getSerializable("CurrentQuestion");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        questionView = (QuestionView) view.findViewById(R.id.questionView);
        questionView.setQuestionToShow(questionPresented);
        questionView.setAnswerSelectionListener(this);
        return view;
    }

    public void setInteractingActivity (QuestionEventsHandler gQuestionsEventHandler)
    {
        questionEventsHandler = gQuestionsEventHandler;
    }

    @Override
    public void OnAnswerSelected(boolean istrue) {
        questionEventsHandler.OnQuestionAnswerSelected(istrue);
    }


    public interface QuestionEventsHandler {
        void OnQuestionAnswerSelected(boolean istrue);
    }
}
