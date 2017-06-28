package com.rd.lottery.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rd.lottery.fragment.InputTopicFragment;
import com.rd.lottery.fragment.MultipleChoiceFragment;
import com.rd.lottery.fragment.SingleChoiceFragment;
import com.rd.lottery.fragment.TakePhotoFragment;
import com.rd.lottery.model.QuestionInfo;

import java.util.List;

/**
 * @author liuteng
 * @version [2017/6/27 11:16]
 */

public class QuestionFragmentAdapter extends FragmentPagerAdapter {
    private List<QuestionInfo> questionInfos;

    public QuestionFragmentAdapter(FragmentManager fm, List<QuestionInfo> questionInfos) {
        super(fm);
        this.questionInfos = questionInfos;
    }

    @Override
    public Fragment getItem(int position) {
        QuestionInfo questionInfo = questionInfos.get(position);
        switch (questionInfo.getDataType()) {
            case 0:
                InputTopicFragment topicFragment = new InputTopicFragment();
                Bundle args0 = new Bundle();
                args0.putInt("position", position);
                args0.putSerializable("questionInfo",questionInfo);
                topicFragment.setArguments(args0);
                return topicFragment;
            case 1:
                SingleChoiceFragment singleChoiceFragment = new SingleChoiceFragment();
                Bundle args1 = new Bundle();
                args1.putInt("position", position);
                args1.putSerializable("questionInfo",questionInfo);
                singleChoiceFragment.setArguments(args1);
                return singleChoiceFragment;
            case 2:
                MultipleChoiceFragment choiceFragment = new MultipleChoiceFragment();
                Bundle args2 = new Bundle();
                args2.putInt("position", position);
                args2.putSerializable("questionInfo",questionInfo);
                choiceFragment.setArguments(args2);
                return choiceFragment;
            case 3:
                TakePhotoFragment photoFragment = new TakePhotoFragment();
                Bundle args3 = new Bundle();
                args3.putInt("position", position);
                args3.putSerializable("questionInfo",questionInfo);
                photoFragment.setArguments(args3);
                return photoFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return questionInfos.size();
    }
}
