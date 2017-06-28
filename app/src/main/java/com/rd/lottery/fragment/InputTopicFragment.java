package com.rd.lottery.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.baseline.framework.ui.activity.BasicFragment;
import com.android.baseline.framework.ui.activity.annotations.ViewInject;
import com.rd.lottery.R;
import com.rd.lottery.model.MyAnswerInfo;
import com.rd.lottery.model.QuestionInfo;

/**
 * 输入题
 *
 * @author liuteng
 * @version [2017/6/27 10:26]
 */

public class InputTopicFragment extends BasicFragment {
    private QuestionInfo questionInfo;
    private int position;
    @ViewInject(R.id.input_topic_txt)
    private TextView inputTopicTxt;
    @ViewInject(R.id.input_answer)
    private EditText inputAnswer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflate(inflater, container, R.layout.fragemt_input_topic, this);
    }

    @Override
    protected void afterSetContentView(View view) {
        super.afterSetContentView(view);
        questionInfo = (QuestionInfo) getArguments().getSerializable("questionInfo");
        position = getArguments().getInt("position", 0);
        if (questionInfo != null) {
            inputTopicTxt.setText(position + 1 + "." + questionInfo.getQuestionName());
            if (questionInfo.getMyAnswerIds().size() > 0) {
                inputAnswer.setText(questionInfo.getMyAnswerIds().get(0).getAnswerContent());
            }
        }
        inputAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                MyAnswerInfo myAnswerInfo = new MyAnswerInfo();
                myAnswerInfo.setAnswerContent(editable.toString());
                if (questionInfo.getMyAnswerIds().size() > 0) {
                    questionInfo.getMyAnswerIds().set(0, myAnswerInfo);
                } else {
                    questionInfo.getMyAnswerIds().add(myAnswerInfo);
                }
            }
        });
    }
}
