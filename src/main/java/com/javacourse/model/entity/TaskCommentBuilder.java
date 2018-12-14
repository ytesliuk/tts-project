package com.javacourse.model.entity;

public class TaskCommentBuilder extends TaskRecordBuilder {
    private TaskComment quoteComment;

    public TaskCommentBuilder setQuoteComment(TaskComment quoteComment) {
        this.quoteComment = quoteComment;
        return this;
    }

    @Override
    public TaskComment build(){
        return new TaskComment(id, task, recordTime, comment, recorder, quoteComment);
    }
}
