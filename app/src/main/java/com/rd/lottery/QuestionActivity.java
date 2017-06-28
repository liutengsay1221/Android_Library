package com.rd.lottery;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.baseline.framework.ui.activity.BasicActivity;
import com.android.baseline.framework.ui.activity.annotations.ViewInject;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.rd.lottery.adapter.QuestionFragmentAdapter;
import com.rd.lottery.model.AnswerInfo;
import com.rd.lottery.model.QuestionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuteng
 * @version [2017/6/27 10:07]
 */

public class QuestionActivity extends BasicActivity {
    @ViewInject(R.id.question_num)
    private TextView questionNum;   //题号
    @ViewInject(R.id.viewpager)
    private ViewPager viewPager;
    @ViewInject(R.id.question_on_topic)
    private Button onTopicBtn;       //上一题
    @ViewInject(R.id.next_question)
    private Button nextQuestion;     //下一题
    private QuestionFragmentAdapter fragmentAdapter;
    private List<QuestionInfo> infos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Fresco.initialize(this);
    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();
        setData();
        onTopicBtn.setVisibility(View.GONE);
        nextQuestion.setVisibility(View.VISIBLE);
        nextQuestion.setTag("0");
        FragmentManager fm = getSupportFragmentManager();
        //初始化自定义适配器
        fragmentAdapter = new QuestionFragmentAdapter(fm, infos);
        //绑定自定义适配器
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setCurrentItem(0);
        questionNum.setText(1 + "/" + infos.size());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                questionNum.setText(position + 1 + "/" + infos.size());
                if (position + 1 == infos.size()) {
                    onTopicBtn.setVisibility(View.VISIBLE);
                    nextQuestion.setVisibility(View.VISIBLE);
                    nextQuestion.setText("完成答题");
                    nextQuestion.setTag("1");
                } else if (position != 0) {
                    onTopicBtn.setVisibility(View.VISIBLE);
                    nextQuestion.setVisibility(View.VISIBLE);
                    nextQuestion.setTag("0");
                    nextQuestion.setText("下一题");
                } else {
                    onTopicBtn.setVisibility(View.GONE);
                    nextQuestion.setVisibility(View.VISIBLE);
                    nextQuestion.setText("下一题");
                    nextQuestion.setTag("0");
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tag = (String) nextQuestion.getTag();
                if (tag.equals("0")) { //下一题
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                } else if (tag.equals("1")) { //完成答题
                    infos.toString();
                }
            }
        });
        onTopicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
            }
        });
    }

    public void setData() {
        for (int i = 0; i < 5; i++) {
            QuestionInfo ipQuestionInfo = new QuestionInfo();
            ipQuestionInfo.setDataType(0);
            ipQuestionInfo.setHasOther(0);
            ipQuestionInfo.setQuestionId(i + "ip");
            ipQuestionInfo.setQuestionName("（问答）您发现自己的血压升高多长时间了？");
            infos.add(ipQuestionInfo);
        }
        for (int j = 0; j < 5; j++) {
            QuestionInfo scQuestionInfo = new QuestionInfo();
            scQuestionInfo.setDataType(1);
            scQuestionInfo.setHasOther(1);
            scQuestionInfo.setQuestionId(j + "sc");
            scQuestionInfo.setQuestionName("（单选）您发现自己的血压升高多长时间了？");
            List<AnswerInfo> answerInfos = new ArrayList<>();
            for (int k = 0; k < 5; k++) {
                AnswerInfo answerInfo = new AnswerInfo();
                answerInfo.setAnswerId(k + "sca");
                answerInfo.setAnswerName("A. 140~159/90~99mmHg");
                answerInfos.add(answerInfo);
            }
            scQuestionInfo.setAnswerList(answerInfos);
            infos.add(scQuestionInfo);

        }

        for (int j = 0; j < 5; j++) {
            QuestionInfo mcQuestionInfo = new QuestionInfo();
            mcQuestionInfo.setDataType(2);
            mcQuestionInfo.setHasOther(1);
            mcQuestionInfo.setQuestionId(j + "mc");
            mcQuestionInfo.setQuestionName("(多选)您发现自己的血压升高多长时间了？");
            List<AnswerInfo> answerInfos = new ArrayList<>();
            for (int k = 0; k < 5; k++) {
                AnswerInfo answerInfo = new AnswerInfo();
                answerInfo.setAnswerId(k + "mca");
                answerInfo.setAnswerName("A. 140~159/90~99mmHg");
                answerInfos.add(answerInfo);
            }
            mcQuestionInfo.setAnswerList(answerInfos);
            infos.add(mcQuestionInfo);

        }
        for (int i = 0; i < 5; i++) {
            QuestionInfo tpQuestionInfo = new QuestionInfo();
            tpQuestionInfo.setDataType(3);
            tpQuestionInfo.setHasOther(0);
            tpQuestionInfo.setQuestionId(i + "tp");
            tpQuestionInfo.setQuestionName("（拍照）您发现自己的血压升高多长时间了？");
            infos.add(tpQuestionInfo);
        }
    }

}
