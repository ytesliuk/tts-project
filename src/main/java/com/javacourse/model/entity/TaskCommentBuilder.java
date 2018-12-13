package com.javacourse.model.entity;

public class TaskCommentBuilder extends TaskRecordBuilder {
    private Comment quoteComment;

    public TaskCommentBuilder setQuoteComment(Comment quoteComment) {
        this.quoteComment = quoteComment;
        return this;
    }

    @Override
    public Comment build(){
        return new Comment(id, task, recordTime, comment, recorder, quoteComment);
    }
}
