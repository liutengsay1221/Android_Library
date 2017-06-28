package com.rd.lottery.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.baseline.framework.ui.activity.BasicFragment;
import com.android.baseline.framework.ui.activity.annotations.ViewInject;
import com.rd.lottery.R;
import com.rd.lottery.model.QuestionInfo;
import com.rd.lottery.view.ChoiceView;

/**
 * 多选题
 *
 * @author liuteng
 * @version [2017/6/27 10:26]
 */

public class MultipleChoiceFragment extends BasicFragment {
    private QuestionInfo questionInfo;
    private int position;
    @ViewInject(R.id.sc_choice_txt)
    private TextView choiceTxt;
    @ViewInject(R.id.add_view)
    private ChoiceView choiceView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflate(inflater, container, R.layout.fragemt_single_choice_topic, this);
    }
    @Override
    protected void afterSetContentView(View view) {
        super.afterSetContentView(view);
        questionInfo = (QuestionInfo) getArguments().getSerializable("questionInfo");
        position = getArguments().getInt("position", 0);
        if (questionInfo != null) {
            choiceTxt.setText(position + 1 + "." + questionInfo.getQuestionName());
            choiceView.setDataSource(questionInfo,1);
        }
    }

}
