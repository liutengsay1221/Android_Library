package com.rd.lottery.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuteng
 * @version [2017/6/27 9:54]
 */

public class QuestionInfo implements Serializable {
    private int dataType;
    private String questionName;
    private String questionId;
    private int hasOther;
    private List<AnswerInfo> answerList = new ArrayList<>();
    private List<MyAnswerInfo> myAnswerIds = new ArrayList<>();
    private String other;

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public int getHasOther() {
        return hasOther;
    }

    public void setHasOther(int hasOther) {
        this.hasOther = hasOther;
    }

    public List<AnswerInfo> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerInfo> answerList) {
        this.answerList = answerList;
    }

    public List<MyAnswerInfo> getMyAnswerIds() {
        return myAnswerIds;
    }

    public void setMyAnswerIds(List<MyAnswerInfo> myAnswerIds) {
        this.myAnswerIds = myAnswerIds;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
