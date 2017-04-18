package com.shoaibanwar.edukid.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shoaibanwar.edukid.R;
import com.shoaibanwar.edukid.model.Question;

/**
 * Created by shoaibanwar on 4/15/17.
 */

public final class QuestionView extends LinearLayout implements View.OnClickListener {

    private TextView questionText;
    private TextView choice1;
    private TextView choice2;
    private TextView choice3;

    private Question questionToShow;

    private AnswerSelectionListener answerSelectionListener;

    public QuestionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.question_view, this);
        questionText = (TextView) view.findViewById(R.id.questionTV);
        choice1 = (TextView) view.findViewById(R.id.choice1TV);
        choice2 = (TextView) view.findViewById(R.id.choice2TV);
        choice3 = (TextView) view.findViewById(R.id.choice3TV);

        choice1.setTag("0");
        choice2.setTag("1");
        choice3.setTag("2");

        choice1.setOnClickListener(this);
        choice2.setOnClickListener(this);
        choice3.setOnClickListener(this);
    }

    public void updateAnswersText() {
        questionText.setText(questionToShow.getQuestionStatement());
        choice1.setText(this.questionToShow.getChoices().get(0));
        choice2.setText(this.questionToShow.getChoices().get(1));
        choice3.setText(this.questionToShow.getChoices().get(2));
    }

    public void setQuestionToShow(Question gQuestionToShow) {
        this.questionToShow = gQuestionToShow;
        this.updateAnswersText();
    }

    public void setAnswerSelectionListener(AnswerSelectionListener gAnswerSelectionListener) {
        this.answerSelectionListener = gAnswerSelectionListener;
    }

    @Override
    public void onClick(View v) {

        Toast.makeText(getContext(), "Answer Selected", Toast.LENGTH_SHORT).show();

        int posClicked = Integer.valueOf(v.getTag().toString());
        if (posClicked==questionToShow.getCorrectAnsIndex())
        {
            ImageView imageView = (ImageView) findViewById (R.id.successImage);
            imageView.setVisibility(VISIBLE);
        }

        answerSelectionListener.OnAnswerSelected(true);
    }

    public interface AnswerSelectionListener {
        public void OnAnswerSelected(boolean istrue);
    }
}
