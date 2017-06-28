package com.rd.lottery.model;

import java.io.Serializable;

/**
 * @author liuteng
 * @version [2017/6/27 15:27]
 */

public class MyAnswerInfo implements Serializable {
    private String answerId;
    private String answerContent;
    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

}
