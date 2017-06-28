package com.rd.lottery.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.baseline.util.APKUtil;
import com.rd.lottery.R;
import com.rd.lottery.model.AnswerInfo;
import com.rd.lottery.model.MyAnswerInfo;
import com.rd.lottery.model.QuestionInfo;

/**
 * @author liuteng
 * @version [2017/6/27 14:25]
 */

public class ChoiceView extends LinearLayout implements View.OnClickListener {
    private LayoutInflater inflater;
    private QuestionInfo questionInfo;
    private TextView answerTxt;
    private ImageView answerImg;
    private int choiceType; //0 单选 1 多选
    private boolean isExist;
    private int position;
    private EditText editText;

    public ChoiceView(Context context) {
        super(context, null);
    }

    public ChoiceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, -1);
    }

    public ChoiceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View view) {
        isExist = false;
        String answerId = view.getTag() == null ? "" : view.getTag().toString();
        MyAnswerInfo myAnswerInfo = new MyAnswerInfo();
        myAnswerInfo.setAnswerId(answerId);
        if (choiceType == 0) {
            if (questionInfo.getMyAnswerIds().size() > 0) {
                questionInfo.getMyAnswerIds().set(0, myAnswerInfo);
            } else {
                questionInfo.getMyAnswerIds().add(myAnswerInfo);
            }
        } else if (choiceType == 1) {
            if (questionInfo.getMyAnswerIds().size() > 0) {
                for (int i = 0; i < questionInfo.getMyAnswerIds().size(); i++) {
                    if (answerId.equals(questionInfo.getMyAnswerIds().get(i).getAnswerId())) {
                        isExist = true;
                        position = i;
                        break;
                    }
                }
                if (isExist) {
                    if (questionInfo.getMyAnswerIds().size() > 1) {
                        questionInfo.getMyAnswerIds().remove(position);
                    }
                } else {
                    questionInfo.getMyAnswerIds().add(myAnswerInfo);
                }
            } else {
                questionInfo.getMyAnswerIds().add(myAnswerInfo);
            }
        }
        refreshViewState();
    }


    public void setDataSource(QuestionInfo questionInfo, int choiceType) {
        this.questionInfo = questionInfo;
        this.choiceType = choiceType;
        setOrientation(LinearLayout.VERTICAL);
        for (AnswerInfo answerInfo : questionInfo.getAnswerList()) {
            inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.layout_question_choice_item, null);
            view.setTag(answerInfo.getAnswerId());
            view.setOnClickListener(this);
            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            layoutParams.topMargin = APKUtil.dip2px(getContext(), 0);
            addView(view, layoutParams);
        }
        if (questionInfo.getHasOther() == 1) {
            View view = inflater.inflate(R.layout.layout_question_other_choice_item, null);
            view.setTag("");
            view.setOnClickListener(this);
            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            layoutParams.topMargin = APKUtil.dip2px(getContext(), 0);
            addView(view, layoutParams);
        }
        refreshViewState();
        // 重新布局
        requestLayout();
    }

    public void refreshViewState() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ViewGroup childView = (ViewGroup) getChildAt(i);
            answerTxt = (TextView) childView.findViewById(R.id.answer_text);
            answerImg = (ImageView) childView.findViewById(R.id.answer_radio);
            editText = (EditText) childView.findViewById(R.id.input_answer_other);
            if (i + 1 <= questionInfo.getAnswerList().size()) {
                AnswerInfo answerInfo = questionInfo.getAnswerList().get(i);
                if (answerInfo != null) {
                    answerTxt.setText(answerInfo.getAnswerName());
                    if (editText != null) {
                        editText.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                            }

                            @Override
                            public void afterTextChanged(Editable editable) {
                                questionInfo.setOther(editable.toString());
                            }
                        });
                    }
                }
            }
            if (choiceType == 0) { //单选
                if (questionInfo.getMyAnswerIds().size() > 0) {
                    if (childView.getTag().toString().equals(questionInfo.getMyAnswerIds().get(0).getAnswerId())) {
                        answerImg.setBackgroundResource(R.mipmap.reg_sel);
                    } else {
                        answerImg.setBackgroundResource(R.mipmap.reg_unsel);
                    }
                }
            } else if (choiceType == 1) { //多选
                if (questionInfo.getMyAnswerIds().size() > 0) {
                    for (MyAnswerInfo myAnswerInfo : questionInfo.getMyAnswerIds()) {
                        if (childView.getTag().toString().equals(myAnswerInfo.getAnswerId())) {
                            answerImg.setBackgroundResource(R.mipmap.reg_sel);
                            break;
                        } else {
                            answerImg.setBackgroundResource(R.mipmap.reg_unsel);
                        }
                    }

                }
            }
            childView.setOnClickListener(this);
        }
    }
}
