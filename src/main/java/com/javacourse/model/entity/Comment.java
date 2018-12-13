package com.javacourse.model.entity;

import lombok.Getter;

import java.time.Instant;

/**
 * @author Yuliia Tesliuk
 */
@Getter
public class Comment extends TaskRecord {
    private Comment quoteComment;

    public Comment(long id, Task task, Instant recordTime, String comment, User recorder, Comment quoteComment) {
        super(id, task, recordTime, comment, recorder);
        this.quoteComment = quoteComment;
    }

}
