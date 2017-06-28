package com.rd.lottery.model;

import java.io.Serializable;

/**
 * @author liuteng
 * @version [2017/6/27 9:53]
 */

public class AnswerInfo implements Serializable {
    private String answerId;
    private String answerName;
    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }
}
